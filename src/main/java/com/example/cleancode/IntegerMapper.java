package com.example.cleancode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class IntegerMapper {

	Map<Integer, Integer> integerMap;
	private int minIntegerMapValue = Integer.MAX_VALUE;
	private int maxIntegerMapValue = Integer.MIN_VALUE;

	public IntegerMapper(List<Integer> integerList) {
		integerMap = new HashMap<>();
		addIntegerListToMap(integerList);
	}

	public IntegerMapper() {
	}

	public void addIntegerListToMap(List<Integer> integerList) {
		for (Integer integer : integerList) {
			addIntegerToMap(integer);
		}
	}

	public void addIntegerToMap(Integer integer) {
		if (integerMap.containsKey(integer)) {
			Integer quantity = integerMap.get(integer);
			integerMap.put(integer, quantity + 1);
		} else {
			integerMap.put(integer, 1);
		}
		if (integer > maxIntegerMapValue) {
			maxIntegerMapValue = integer;
		}
		if (integer < minIntegerMapValue) {
			minIntegerMapValue = integer;
		}
	}

	public int getIntegerQuantity(int key) {
		return integerMap.getOrDefault(key, 0);
	}

	public double calculateAverage() {
		double sumOfIntegers = 0;
		double integersQuantity = 0;
		for (Entry<Integer, Integer> integerEntry : integerMap.entrySet()) {
			integersQuantity += integerEntry.getValue();
			sumOfIntegers += integerEntry.getKey() * integerEntry.getValue();
		}
		return sumOfIntegers / integersQuantity;
	}

	public int getMinIntegerMapValue() {
		return minIntegerMapValue;
	}

	public int getMaxIntegerMapValue() {
		return maxIntegerMapValue;
	}
}