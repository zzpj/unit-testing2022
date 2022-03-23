package com.example.cleancode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class IntegersList {

	Map<Integer, Integer> integerHashMap = new HashMap<Integer, Integer>();
	private int minValue = Integer.MAX_VALUE;
	private int maxValue = Integer.MIN_VALUE;
	
	public IntegersList(List<Integer> i1) {
		addList(i1);
	}
	
	public IntegersList() {
	}
	
	public void addList(List<Integer> list) {
		for (Integer integer : list) {
			addInt(integer);
		}
	}
	
	public void addInt(Integer i) {
		if (integerHashMap.containsKey(i)) {
			Integer key = integerHashMap.get(i);
			integerHashMap.put(i, key +1);
		} else {
			integerHashMap.put(i, 1);
		}
		
		if (i < minValue) {
			minValue = i;
		}
		
		if (i > maxValue) {
			maxValue = i;
		}
	}
	
	public int getAmountOfInt(int i) {
		return integerHashMap.getOrDefault(i, 0);
	}
	
	public double getAverage() {
		double sum = 0;
		double amount = 0;
		for (Entry<Integer, Integer> i : integerHashMap.entrySet()) {
			amount += i.getValue();
			sum += i.getKey() * i.getValue();
		}
		return sum/amount;
	}
	
	public int getMinValue() {
		return minValue;
	}
	
	public int getMaxValue() {
		return maxValue;
	}

	public Map<Integer, Integer> getIntegerHashMap() {
		return integerHashMap;
	}
}