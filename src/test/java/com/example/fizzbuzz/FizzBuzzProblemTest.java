package com.example.fizzbuzz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzProblemTest {

    FizzBuzzProblem sut;

    @BeforeEach
    public void setUp() {
        sut = new FizzBuzzProblem();
    }

    @ParameterizedTest
    @ValueSource(ints = {15, 30, 45, 60})
    public void shouldReturnFizzBuzz(int num) {
        assertEquals("FizzBuzz", sut.getFizzBuzzNumber(num));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 6, 9, 12})
    public void shouldReturnFizz(int num) {
        assertEquals("Fizz", sut.getFizzBuzzNumber(num));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 20, 25})
    public void shouldReturnBuzz(int num) {
        assertEquals("Buzz", sut.getFizzBuzzNumber(num));
    }

    @Test
    public void shouldReturnNothing() {
        assertEquals("4", sut.getFizzBuzzNumber(4));
    }
}