package com.example.cleancode;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntCountTest {

    static List<Integer> intList;
    static List<Integer> intList2;
    static IntCount sut;

    @BeforeAll
    static void beforeAll() {
        intList = Arrays.asList(1, 1, 2, 4);
        intList2 = Arrays.asList(6, 6, 7);
    }

    @BeforeEach
    void beforeEach() {
        sut = new IntCount(intList);
    }

    @Test
    void testAddInt() {
        sut.addInt(-100);
        assertEquals(1, sut.checkIntCount(-100));
        assertEquals(-100, sut.getMin());

        sut.addInt(100);
        assertEquals(1, sut.checkIntCount(100));
        assertEquals(100, sut.getMax());
    }

    @Test
    void testAddManyInts() {
        sut.addManyInts(intList2);
        assertEquals(2, sut.checkIntCount(6));
        assertEquals(1, sut.checkIntCount(7));
    }

    @Test
    void testGetIntCount() {
        assertEquals(2, sut.checkIntCount(1));
        sut.addInt(1);
        assertEquals(3, sut.checkIntCount(1));
    }

    @Test
    void testCalculateAverage() {
        assertEquals(2.0, sut.calculateAverage());
    }

}
