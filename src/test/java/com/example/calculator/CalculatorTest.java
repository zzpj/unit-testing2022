package com.example.calculator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    static Calculator calculator;

    @BeforeAll
    public static void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void shouldAddTwoNumbers() {
        //given
        //when
        int actual1 = calculator.add(3, 4);
        int actual2 = calculator.add(-5, 8);
        //then
        assertAll(
                () -> assertEquals(7, actual1),
                () -> assertEquals(3, actual2)
        );
    }

    @Test
    public void shouldSubtractTwoNumbers() {
        //given
        //when
        int actual = calculator.subtract(5, 2);
        //then
        assertEquals(3, actual);
    }

    @Test
    public void shouldMultiplyTwoNumbers() {
        //given
        //when
        int actual = calculator.multiply(4, 4);
        //then
        assertEquals(16, actual);
    }

    @Test
    public void shouldDivideTwoNumbers() throws NotDividedByZeroException {
        //given
        //when
        int actual = calculator.divide(10, 5);
        //then
        assertEquals(2, actual);
    }

    @Test
    public void shouldModuloNumbers() throws NotDividedByZeroException {
        //given
        //when
        int actual = calculator.modulo(10, 3);
        //then
        assertEquals(1, actual);
    }

    @Test
    public void shouldThrowNotDividedByZeroException() throws NotDividedByZeroException {
        assertThrows(NotDividedByZeroException.class, () -> {
            calculator.divide(10, 0);
        });
    }

    @Test
    public void shouldCheckPrimeNumbers() throws NotDividedByZeroException {
        assertTrue(calculator.isPrime(7));
        assertFalse(calculator.isPrime(12));
    }

}
