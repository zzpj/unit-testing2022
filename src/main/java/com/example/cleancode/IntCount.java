package com.example.cleancode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// Testy wykazały problem z metodą addInt - nowe klucze nie były dodawane do hashmapy, zamiast dodać nowy klucz zmieniał wartość dla klucza '1' na 1
// Po refaktoringu nazwy metod wskazują dokładnie na to, co one robią

public class IntCount {

	private final Map<Integer, Integer> intCounter = new HashMap<Integer, Integer>();
	private int max = Integer.MIN_VALUE;
	private int min = Integer.MAX_VALUE;

	
	public IntCount(List<Integer> i1) {
		addManyInts(i1);
	}

	public IntCount() {
	}

	public int getMax() {
		return max;
	}

	public int getMin() {
		return min;
	}

	public Map<Integer, Integer> getHashMap() {
        return intCounter;
	}

	public void addManyInts(List<Integer> intList) {
		for (Integer item : intList) {
			addInt(item);
		}
	}

	public void addInt(Integer i) {
		if (intCounter.containsKey(i)) {
			Integer k = intCounter.get(i);
			intCounter.put(i, k + 1);
		} else {
			intCounter.put(i, 1);
		}

		if (i > max) {
			max = i;
		} else if (i < min) {
			min = i;
		}
	}

	public int checkIntCount(int i) {
		if (intCounter.containsKey(i)) {
			return intCounter.get(i);
		} else {
			return 0;
		}
	}

	public double calculateAverage() {
		double valueSum = 0;
		double elemCount = 0;
		for (Entry<Integer, Integer> u : intCounter.entrySet()) {
			elemCount += u.getValue();
			valueSum += u.getKey() * u.getValue();
		}
		return valueSum / elemCount;
	}

}