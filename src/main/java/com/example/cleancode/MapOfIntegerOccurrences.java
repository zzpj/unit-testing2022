package com.example.cleancode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapOfIntegerOccurrences {

	Map<Integer, Integer> integerValueOccurrenceMap = new HashMap<>();
	private int highestEncounteredValue = Integer.MIN_VALUE;
	private int lowestEncounteredValue = Integer.MAX_VALUE;
	
	public MapOfIntegerOccurrences(List<Integer> listOfIntegers) {
		parseListOfIntegers(listOfIntegers);
	}
	
	public MapOfIntegerOccurrences() {}
	
	public void parseListOfIntegers(List<Integer> integerList) {
		for (Integer integer : integerList) {
			parseInteger(integer);
		}
	}
	
	public void parseInteger(Integer integerValue) {
		if (integerValueOccurrenceMap.containsKey(integerValue)) {
			Integer valueOfKey = integerValueOccurrenceMap.get(integerValue);
			integerValueOccurrenceMap.put(integerValue, valueOfKey + 1);
		} else {
			// BUG ENCOUNTERED
			// 1 instead of integerValue
			integerValueOccurrenceMap.put(integerValue, 1);
		}

		if (integerValue > highestEncounteredValue) {
			highestEncounteredValue = integerValue;
		}
		
		if (integerValue < lowestEncounteredValue) {
			lowestEncounteredValue = integerValue;
		}
	}

	public int getValueOfKeyIfExists(int key) {
		return integerValueOccurrenceMap.getOrDefault(key, 0);
	}

	public double getSumOfElementsDividedByNumberOfElementsInInputList() {
		double keysMultipliedByCorrespondingValuesAccumulator = 0;
		double valuesAccumulator = 0;

		for (Entry<Integer, Integer> entry : integerValueOccurrenceMap.entrySet()) {
			valuesAccumulator += entry.getValue();
			keysMultipliedByCorrespondingValuesAccumulator += entry.getKey() * entry.getValue();
		}

		return keysMultipliedByCorrespondingValuesAccumulator / valuesAccumulator;
	}
	
	public int getHighestEncounteredValue() {
		return highestEncounteredValue;
	}
	
	public int getLowestEncounteredValue() {
		return lowestEncounteredValue;
	}

}