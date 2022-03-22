package com.example.fizzbuzz;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class FizzBuzzProblemTest {

    private FizzBuzzProblem fb;
    static long start;

    @BeforeAll
    public static void initAll() {
        start = System.currentTimeMillis();
    }

    @BeforeEach
    void setUp() {
        fb = new FizzBuzzProblem();
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void shouldBeNotNull() {
        assertNotNull(fb);
    }

    @Test
    @EnabledOnJre(JRE.JAVA_14)
    @DisplayName("Test number divisible by 3.")
    void shouldBeFizz() {
        assertEquals(fb.getFizzBuzzNumber(3), "Fizz");
    }

    @Test
    @ParameterizedTest
    @ValueSource(ints = {5,10,20,35})
    void shouldBeBuzz(int input) {
        assertEquals(fb.getFizzBuzzNumber(input), "Buzz");
    }

    @Test
    @ParameterizedTest
    @ValueSource(ints = {15,45,30,75})
    void shouldBeFizzBuzz(int input) {
        assertEquals(fb.getFizzBuzzNumber(input), "FizzBuzz");
    }

    @Test
    void shouldBeNumber() {
        assertEquals(fb.getFizzBuzzNumber(4), String.valueOf(4));
    }

    @AfterEach
    void tearDown() {
        long end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end - start));
    }
}