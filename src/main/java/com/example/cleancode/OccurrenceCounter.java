package com.example.cleancode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OccurrenceCounter {

    private Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
    private int maxValue = Integer.MIN_VALUE;
    private int minValue = Integer.MAX_VALUE;

    public OccurrenceCounter() {
    }

    // Konstruktor przyjmujący listę Integer'ów - wywołuje metodę processList
    public OccurrenceCounter(List<Integer> i1) {
        processList(i1);
    }

    public Map<Integer, Integer> getHashMap() {
        return hashMap;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    // Metoda wywołująca funkcję addIntegerOccurrence na każdym elemencie tablicy
    public void processList(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            addIntegerOccurrence(list.get(i));
        }
    }

    // Zlicza liczbę wystąpień danej liczby - jeśli jeszcze nie wystąpiła to otrzymuje wartość 1
    public void addIntegerOccurrence(Integer integer) {
        int occurences = getIntegerOccurrences(integer);
        if (occurences != 0) {
            hashMap.put(integer, occurences + 1);
        } else {
            // Bug#1 -> hashMap.put(1, 1)
            hashMap.put(integer, 1);
        }

        if (integer > maxValue) {
            maxValue = integer;
        }

        if (integer < minValue) {
            minValue = integer;
        }
    }

    // Sprawdza czy dana liczba już występuje w HashMap'ie - jeśli tak to zwraca 1 w innym wypadku 0
    public int getIntegerOccurrences(int i) {
        return hashMap.getOrDefault(i, 0);
    }

    // Oblicza średnią z wszystkich liczb
    public double meanIntegerValue() {
        double count = 0;
        double total = 0;
        for (Entry<Integer, Integer> u : hashMap.entrySet()) {
            count += u.getValue();
            total += u.getKey() * u.getValue();
        }
        return total / count;
    }


}