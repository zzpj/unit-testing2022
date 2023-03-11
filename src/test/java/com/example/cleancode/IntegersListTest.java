package com.example.cleancode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class IntegersListTest {

    IntegersList sut;

    @BeforeEach
    void init() {
        List<Integer> list = new ArrayList<>(){{
            add(10);
            add(2);
            add(8);
            add(20);
        }};
        sut = new IntegersList(list);
    }

    @Test
    void addSingleIntTest() {
        int size = sut.getIntegerHashMap().size();
        sut.addInt(3);
        assertEquals(sut.getIntegerHashMap().size(),size + 1);
    }

    @Test
    void addListofIntTest() {
        int size = sut.getIntegerHashMap().size();
        List<Integer> list = new ArrayList<>(){{
            add(1);
            add(3);
            add(5);
            add(7);
        }};
        sut.addList(list);
        assertEquals(sut.getIntegerHashMap().size(),size + list.size());
    }

    @Test
    void maxAndMinValueTest() {
        assertEquals(sut.getMinValue(),2);
        assertEquals(sut.getMaxValue(),20);
        sut.addInt(-10);
        sut.addInt(100);
        assertEquals(sut.getMinValue(),-10);
        assertEquals(sut.getMaxValue(),100);
    }

    @Test
    void averageValueTest() {
        assertEquals(sut.getAverage(),10);
    }

    @Test
    void amountsIntTest() {
        assertEquals(sut.getAmountOfInt(10),1);
        sut.addInt(10);
        assertEquals(sut.getAmountOfInt(10),2);
        assertEquals(sut.getAmountOfInt(20),1);
        assertEquals(sut.getAmountOfInt(8),1);
        assertEquals(sut.getAmountOfInt(2),1);
        assertEquals(sut.getAmountOfInt(1),0);
    }

}
