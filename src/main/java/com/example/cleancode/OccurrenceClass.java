package com.example.cleancode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OccurrenceClass {

    private final Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
    private int maxValue = Integer.MIN_VALUE;
    private int minValue = Integer.MAX_VALUE;

    public OccurrenceClass(List<Integer> integerList) {
        addIntegerList(integerList);
    }

    /**
     *
     * @return max value in map
     */
    public int getMaxValue() {
        return maxValue;
    }

    /**
     *
     * @return min value in map
     */
    public int getMinValue() {
        return minValue;
    }

    /**
     *
     * @return hashmap representation of occurrence
     */
    public Map<Integer, Integer> getHashMap() {
        return hashMap;
    }

    /**
     *  Adds List of Integers to occurrence map
     * @param integerList List of Integers to add to map
     */
    public void addIntegerList(List<Integer> integerList) {
        for (Integer integer : integerList) {
            addInteger(integer);
        }
    }

    /**
     *  Addes Integer to occurrence map
     * @param i Integer to add to map
     */
    public void addInteger(Integer i) {
        if (hashMap.containsKey(i)) {
            Integer k = hashMap.get(i);
            hashMap.put(i, k + 1);
        } else {
            hashMap.put(i, 1);
        }

        if (i > maxValue) {
            maxValue = i;
        }

        if (i < minValue) {
            minValue = i;
        }
    }

    /**
     * @param i Value to check
     * @return Number of value occurrence in map
     */
    public int checkOccurrence(int i) {
        return hashMap.getOrDefault(i, 0);
    }

    /**
     * @return average value of integers in map
     */
    public double countAverage() {
        double sum = 0;
        double count = 0;
        for (Entry<Integer, Integer> u : hashMap.entrySet()) {
            count += u.getValue();
            sum += u.getKey() * u.getValue();
        }
        return sum / count;
    }
}