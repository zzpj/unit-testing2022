package com.example.cleancode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.*;

class averageTest {
    Average sut;

    @BeforeEach
    void setUp() {
        sut = new Average(List.of(2,4,6));
    }

    @Test
    public void initialValuesTest() {
        assertEquals(1,sut.getWeight(2));
        assertEquals(1,sut.getWeight(4));
        assertEquals(1,sut.getWeight(6));
    }

    @Test
    public void initialMinValue() {
        assertEquals(2,sut.getMinValue());
    }

    @Test
    public void initialMaxValue() {
        assertEquals(6,sut.getMaxValue());
    }

    @Test
    public void defaultGetterTest() {
        assertEquals(0,sut.getWeight(1));
    }

    @Test
    public void initialAverageTest() {
        assertEquals(4.0,sut.getWeightedAverage());
    }

    @Test
    public void noValuesAverageThrowsExceptionTest() {
        assertThrows(ArithmeticException.class, () -> {
            Average sut_alt = new Average();
            assertEquals(NaN,sut_alt.getWeightedAverage());
        });
    }

    @Test
    public void storeValueAddingWeightToExistingValueTest() {
        sut.storeValue(2);
        assertEquals(2,sut.getWeight(2));
    }

    @Test
    public void averageAfterAddingWeightTest() {
        sut.storeValue(2);
        assertEquals(3.5,sut.getWeightedAverage());
    }

    @Test
    public void storeValueNewKeyStoresOneTest() {
        sut.storeValue(0);
        assertEquals(1,sut.getWeight(0));
    }

    @Test
    public void storeValueMinValueIsUpdatedTest() {
        sut.storeValue(-2);
        assertEquals(-2,sut.getMinValue());
        assertEquals(6,sut.getMaxValue());
    }

    @Test
    public void storeValueMaxValueIsUpdatedTest() {
        sut.storeValue(10);
        assertEquals(2,sut.getMinValue());
        assertEquals(10,sut.getMaxValue());
    }

    @Test
    public void storeValuesFromListAndAverageTest() {
        sut.storeValuesFromList(List.of(100,-50,100));
        assertEquals(2,sut.getWeight(100));
        assertEquals(1,sut.getWeight(-50));
        assertEquals(27.0,sut.getWeightedAverage());
    }


}