package com.example.fizzbuzz;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Fizzbuzz testing")
public class FizzBuzzTest {
    FizzBuzzProblem fizzbuzz;
    static long startTime;

    @BeforeAll
    public static void initAll() {
        startTime = System.currentTimeMillis();
    }
    @BeforeEach
    public void setup() {
        fizzbuzz = new FizzBuzzProblem();
    }

    /**
     * Fizz Buzz is a 'game' where:
     * - if the number is divisible by 3, you say Fizz
     * - if the number is divisible by 5, you say Buzz
     * - if neither, you say the number
     */

    @Test
    @EnabledOnOs(OS.WINDOWS)
    @DisplayName("FizzBuzz testing if object is instantiated")
    public void shouldCheckNull() {
        //given null
        //when
        //then
        assertNotNull(fizzbuzz, "checking null");
    }

    @Test
    @DisplayName("FizzBuzz testing 'Fizz'")
    @EnabledOnJre(value = JRE.JAVA_17, disabledReason = "version of java not 17")
    public void shouldCheckFizz() {
        //given number % 3 == 0
        //when
        int numberToCheck = 3;
        //then
        assertEquals(fizzbuzz.getFizzBuzzNumber(numberToCheck), "Fizz");
    }

    @Test
    @DisplayName("FizzBuzz testing 'Buzz'")

    public void shouldCheckBuzz() {
        //given number % 5 == 0
        //when
        int numberToCheck = 5;
        //then
        assertEquals(fizzbuzz.getFizzBuzzNumber(numberToCheck), "Buzz");
    }

    @DisplayName("FizzBuzz testing 'FizzBuzz'")
    @ParameterizedTest
    @ValueSource(ints = {0, 15, 30, 45, 60, 75, 90, 105})
    public void shouldCheckFizzBuzz(int input) {
        //given (number % 3 && number % 5) == 0
        //when
        //then
        assertEquals(fizzbuzz.getFizzBuzzNumber(input), "FizzBuzz");
    }

    @Test
    @DisplayName("FizzBuzz testing number")
    public void shouldCheckNotFizzNotBuzz() {
        //given (number % 3 && number % 5) != 0
        //when
        int numberToCheck = 7;
        //then
        assertEquals(fizzbuzz.getFizzBuzzNumber(numberToCheck), String.valueOf(numberToCheck));
    }

    @AfterAll
    public static void tearDownAll() {
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        System.out.println("execution time " + resultTime + " ms");
    }
}
