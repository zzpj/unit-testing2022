package com.example.cleancode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class OccurrenceClassTest {

    static List<Integer> integerList1;
    static List<Integer> integerList2;
    static OccurrenceClass occurrenceClass;

    @BeforeAll
    static void beforeAll() {
        integerList1 = Arrays.asList(-7, -3, -2, 1, -2, 1, 435);
        integerList2 = Arrays.asList(-1000, 1, 4, 1, 700, 1234);
    }

    @BeforeEach
    void beforeEach() {
        occurrenceClass = new OccurrenceClass(integerList1);
    }

    @Test
    void getMaxValue() {
        assertEquals(Collections.max(integerList1), occurrenceClass.getMaxValue());
        occurrenceClass.addIntegerList(integerList2);
        assertEquals(Collections.max(integerList2), occurrenceClass.getMaxValue());
    }

    @Test
    void getMinValue() {
        assertEquals(Collections.min(integerList1), occurrenceClass.getMinValue());
        occurrenceClass.addIntegerList(integerList2);
        assertEquals(Collections.min(integerList2), occurrenceClass.getMinValue());
    }

    @Test
    void checkOccurrence() {
        Map<Integer, Long> map = integerList1.stream()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        for (Map.Entry<Integer, Long> entry : map.entrySet()) {
            assertEquals(entry.getValue().intValue(), occurrenceClass.checkOccurrence(entry.getKey()));
        }
    }

    @Test
    void addInteger() {
        Map<Integer, Long> map = integerList1.stream()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        for (Map.Entry<Integer, Long> entry : map.entrySet()) {
            assertEquals(entry.getValue().intValue(), occurrenceClass.checkOccurrence(entry.getKey()));
        }
        Integer testInteger = integerList1.get((int) (Math.random() * integerList1.size()));
        occurrenceClass.addInteger(testInteger);
        Long key = map.get(testInteger);
        map.put(testInteger, key + 1);
        assertEquals(map.get(testInteger), occurrenceClass.checkOccurrence(testInteger));

        testInteger = integerList1.get((int) (Math.random() * integerList1.size()));
        occurrenceClass.addInteger(testInteger);
        key = map.get(testInteger);
        map.put(testInteger, key + 1);
        assertEquals(map.get(testInteger), occurrenceClass.checkOccurrence(testInteger));
    }


    /**
     * Test for both countOccurrence and addInteger
     */
    @Test
    void countOccurrence() {
        // Test for single array
        Map<Integer, Long> map = integerList1.stream()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        for (Map.Entry<Integer, Long> entry : map.entrySet()) {
            assertEquals(entry.getValue().intValue(), occurrenceClass.checkOccurrence(entry.getKey()));
        }
        // Test for 2 arrays joined
        occurrenceClass.addIntegerList(integerList2);
        map = Stream.concat(integerList1.stream(), integerList2.stream())
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        for (Map.Entry<Integer, Long> entry : map.entrySet()) {
            assertEquals(entry.getValue().intValue(), occurrenceClass.checkOccurrence(entry.getKey()));
        }
    }

    @Test
    void countAverage() {
        double average = integerList1.stream().mapToInt(a -> a).average().orElse(0.0);
        assertEquals(average, occurrenceClass.countAverage());
        occurrenceClass.addIntegerList(integerList2);
        average = Stream.concat(integerList1.stream(), integerList2.stream()).mapToInt(a -> a).average().orElse(0.0);
        assertEquals(average, occurrenceClass.countAverage());
    }
}