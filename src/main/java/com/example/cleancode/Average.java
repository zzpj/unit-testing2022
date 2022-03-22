package com.example.cleancode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Average {

    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    private int bottomLimit;
    private int topLimit;

    public Average(List<Integer> integerList) {
        bottomLimit = integerList.get(0);
        topLimit = integerList.get(0);
        setLimit(integerList);
    }

    public Average() {
        bottomLimit = Integer.MIN_VALUE;
        topLimit = Integer.MAX_VALUE;
    }

    public void setLimit(List<Integer> integerList) {
        for (int i = 0; i < integerList.size(); i++) {
            setLimit(integerList.get(i));
        }
    }

    public void setLimit(Integer i) {
        if (map.containsKey(i)) {
            map.put(i, map.get(i) + 1);
        } else {
            map.put(i, 1);
        }

        if (i < bottomLimit) {
            bottomLimit = i;
        }

        if (i > topLimit) {
            topLimit = i;
        }
    }

    public int getValueOfIndex (int i) {
        return map.getOrDefault(i, 0);
    }

    public double weightedAverage() {
        double sum = 0;
        double amount = 0;
        for (Entry<Integer, Integer> pair : map.entrySet()) {
            amount += pair.getValue();
            sum += pair.getKey() * pair.getValue();
        }
        return sum / amount;
    }

    public int getBottomLimit() {
        return bottomLimit;
    }

    public int getTopLimit() {
        return topLimit;
    }

}