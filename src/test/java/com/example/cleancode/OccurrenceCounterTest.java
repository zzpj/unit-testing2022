package com.example.cleancode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OccurrenceCounterTest {

    OccurrenceCounter occurrenceCounter;

    @BeforeEach
    void setUp() {
        occurrenceCounter = new OccurrenceCounter();
    }

    // Zlicza liczbę wystąpień danej liczby - jeśli jeszcze nie wystąpiła to otrzymuje wartość 1,
    // ustawia zmienne wskazujące na największą liczbę w HashMap'ie oraz najmniejszą (no chyba, że chodzi o najmniejszą
    // i największą liczbę wystąpień)
    // Bug#1 jeśli liczba nie występuje w HashMap'ie to zawsze jest ustawiana wartość 1 dla klucza 1
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 1, 5, -2, -2})
    void addIntegerOccurrenceTest(Integer integer) {
        //given
        Map<Integer, Integer> hashMap = occurrenceCounter.getHashMap();
        //when
        occurrenceCounter.addIntegerOccurrence(integer);
        //then
        assertEquals(1, hashMap.get(integer));
    }

    // Metoda wywołująca funkcję addIntegerOccurrence na każdym elemencie tablicy
    @Test
    void processListTest() {
        //given
        Map<Integer, Integer> hashMap = occurrenceCounter.getHashMap();
        List<Integer> list = Arrays.asList(1, 2, 1, 5, -2, -2);
        //when
        occurrenceCounter.processList(list);
        //then
        assertAll(
                () -> assertEquals(2, hashMap.get(1)),
                () -> assertEquals(1, hashMap.get(2)),
                () -> assertEquals(1, hashMap.get(5)),
                () -> assertEquals(2, hashMap.get(-2))
        );

        assertEquals(5, occurrenceCounter.getMaxValue());
        assertEquals(-2, occurrenceCounter.getMinValue());
    }

    // Konstruktor przyjmujący listę Integer'ów - wywołuje metodę processList
    @Test
    void constructorTest() {
        //given
        List<Integer> list = Arrays.asList(1, 2, 1, 5, 1, -2);
        //when
        occurrenceCounter = new OccurrenceCounter(list);
        Map<Integer, Integer> hashMap = occurrenceCounter.getHashMap();
        //then
        assertAll(
                () -> assertEquals(3, hashMap.get(1)),
                () -> assertEquals(1, hashMap.get(2)),
                () -> assertEquals(1, hashMap.get(5)),
                () -> assertEquals(1, hashMap.get(-2))
        );
    }

    // Sprawdza czy dana liczba już występuje w HashMap'ie - jeśli tak to zwraca 1 w innym wypadku 0
    @Test
    void getIntegerOccurrencesTest() {
        //given
        List<Integer> list = Arrays.asList(1, 1, -5);
        Map<Integer, Integer> hashMap = occurrenceCounter.getHashMap();
        //when
        occurrenceCounter.processList(list);
        //then
        assertAll(
                () -> assertEquals(2, hashMap.get(1)),
                () -> assertEquals(1, hashMap.get(-5)),
                () -> assertEquals(2, occurrenceCounter.getIntegerOccurrences(1)),
                () -> assertEquals(1, occurrenceCounter.getIntegerOccurrences(-5)),
                () -> assertEquals(0, occurrenceCounter.getIntegerOccurrences(69)),
                () -> assertEquals(0, occurrenceCounter.getIntegerOccurrences(2137))
        );

    }

    // Oblicza średnią z wszystkich liczb
    @Test
    void meanIntegerValue() {
        //given
        List<Integer> list = Arrays.asList(1, 2, 5, 5, 1, -2);
        //when
        occurrenceCounter.processList(list);
        double value = occurrenceCounter.meanIntegerValue();
        //then
        assertEquals(2., value);
    }
}