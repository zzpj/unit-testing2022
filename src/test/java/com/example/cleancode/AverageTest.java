package com.example.cleancode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AverageTest {

    Average average;
    Average averageParameters;

    @BeforeEach
    public void setUp() {
        average = new Average();
        averageParameters = new Average(Arrays.asList(11, 2, 15, 1, 1));
    }

    @Test
    public void averageSetUpWithoutParamatersTest() {
        assertEquals(average.getBottomLimit(), Integer.MIN_VALUE);
        assertEquals(average.getTopLimit(), Integer.MAX_VALUE);
    }

    @Test
    public void averageSetUpWithParametersTest() {
        assertEquals(averageParameters.getBottomLimit(), 1);
        assertEquals(averageParameters.getTopLimit(), 15);
    }

    @Test
    public void checkingMapValueTest() {
        assertEquals(averageParameters.getValueOfIndex(1), 2);
        assertEquals(averageParameters.getValueOfIndex(0), 0);
        assertEquals(average.getValueOfIndex(0), 0);
    }


    @Test
    public void weightedAverageTest() {
        assertEquals(averageParameters.weightedAverage(), 6);
    }

}
