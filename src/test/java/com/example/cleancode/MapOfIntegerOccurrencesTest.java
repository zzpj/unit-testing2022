package com.example.cleancode;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("MapOfIntegerOccurrences class test")
public class MapOfIntegerOccurrencesTest {

    MapOfIntegerOccurrences emptyMap;
    MapOfIntegerOccurrences mapWithValues;
    List<Integer> integerList;
    static long startTime;

    @BeforeAll
    public static void initAll() {
        startTime = System.currentTimeMillis();
    }

    @BeforeEach
    public void setup() {
        emptyMap = new MapOfIntegerOccurrences();
        integerList = new ArrayList<>(
                List.of(1, 100, 200, 1000, 1, 1)
        );
        mapWithValues = new MapOfIntegerOccurrences(integerList);
    }


    @Test
    @DisplayName("Default lowest and highest values check")
    public void ensureGettersOfEncounteredValue_ReturnCorrectValues_WithDefaultConstructor() {
        assertAll(
                () -> assertEquals(emptyMap.getHighestEncounteredValue(), Integer.MIN_VALUE),
                () -> assertEquals(emptyMap.getLowestEncounteredValue(), Integer.MAX_VALUE)
        );
    }

    @Test
    @DisplayName("Class constructor check")
    public void ensureGettersOfEncounteredValue_ReturnCorrectValues_WithParametrizedConstructor() {
        assertAll(
                () -> assertEquals(mapWithValues.getHighestEncounteredValue(), Collections.max(integerList)),
                () -> assertEquals(mapWithValues.getLowestEncounteredValue(), Collections.min(integerList))
        );
    }

    @Test
    @DisplayName("Get value of key in map when key exists check")
    public void ensureGetValueOfKeyIfExistsMethod_ReturnsCorrectAnswer_WithExistingKey() {
        assertEquals(mapWithValues.getValueOfKeyIfExists(integerList.get(0)),
                        Collections.frequency(integerList, integerList.get(0)));
    }

    @Test
    @DisplayName("Get value of key in map when key doesn't exist check")
    public void ensureGetValueOfKeyIfExistsMethod_ReturnCorrectAnswer_WithNonExistingKey() {
        assertEquals(mapWithValues.getValueOfKeyIfExists(999999), 0);
    }

    @Test
    @DisplayName("Get sum of elements divided by number of elements in input list check")
    public void ensureGetSumOfElementsDividedByNumberOfElementsInInputListMethod_ReturnCorrectAnswer() {
        assertEquals(mapWithValues.getSumOfElementsDividedByNumberOfElementsInInputList(),
                integerList.stream().reduce(0, Integer::sum).doubleValue() / integerList.size());
    }

    @AfterAll
    public static void tearDownAll() {
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        System.out.println("execution time " + resultTime + " ms");
    }
}
