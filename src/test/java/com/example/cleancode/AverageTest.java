package com.example.cleancode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AverageTest {

    Average average;
    Average average2;

    @BeforeEach
    public void setUp() {
        average = new Average();
        average2 = new Average(Arrays.asList(11, 2, 15, 1, 1));
    }

    @Test
    public void averageSetUpWithoutParamatersTest() {
        assertEquals(average.getBottomLimit(), Integer.MIN_VALUE);
        assertEquals(average.getTopLimit(), Integer.MAX_VALUE);
    }

    @Test
    public void averageSetUpWithParametersTest() {
        assertEquals(average2.getBottomLimit(), 1);
        assertEquals(average2.getTopLimit(), 15);
    }

}
