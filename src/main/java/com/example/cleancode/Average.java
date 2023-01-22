package com.example.cleancode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Average {
	// Class that stores map of key and value, where key is some number, and value is its weight, to be used
	// in calculating weighted average using getWeightedAverage
	Map<Integer, Integer> valueWeightMap = new HashMap<>();
	private int maxValue = Integer.MIN_VALUE;
	private int minValue = Integer.MAX_VALUE;
	
	public Average(List<Integer> integerList) {
		storeValuesFromList(integerList);
	}
	
	public Average() {
	}
	
	public void storeValuesFromList(List<Integer> integerList) {
		for (Integer integer : integerList) {
			storeValue(integer);
		}
	}
	
	public void storeValue(Integer i) {
		if (valueWeightMap.containsKey(i)) {
			Integer k = valueWeightMap.get(i);
			valueWeightMap.put(i, k +1);
		} else {
			valueWeightMap.put(i, 1);
		}

		if (i < minValue) {
			minValue = i;
		}
		if (i > maxValue) {
			maxValue = i;
		}

	}
	
	public int getWeight(int i) {
		return valueWeightMap.getOrDefault(i, 0);
	}
	
	public double getWeightedAverage() {
		double weightsSum = 0;
		double valuesSum = 0;
		for (Entry<Integer, Integer> mapEntry : valueWeightMap.entrySet()) {
			weightsSum += mapEntry.getValue();
			valuesSum+= mapEntry.getKey() * mapEntry.getValue();
		}
		if(weightsSum==0) {
			throw new ArithmeticException("Cannot calculate weighted average with no weights");
		} else {
			return valuesSum/weightsSum;
		}
	}
	
	public int getMinValue() {
		return minValue;
	}
	
	public int getMaxValue() {
		return maxValue;
	}

}