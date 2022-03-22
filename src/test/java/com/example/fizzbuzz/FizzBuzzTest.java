package com.example.fizzbuzz;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FizzBuzzTest {
    FizzBuzzProblem sut;
    static long startTime;

    @BeforeEach
    public void setup(){
        sut = new FizzBuzzProblem();
    }


    @BeforeAll
    public static void initAll() {
        startTime = System.currentTimeMillis();
    }


    @ParameterizedTest
    @CsvSource({"3", "6", "9"})
    public void shouldReturnFizz(int a){
        assertEquals("Fizz", sut.getFizzBuzzNumber(a));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Abuzz.csv", numLinesToSkip = 1)
    public void shouldReturnBuzz(int a) {
        assertEquals("Buzz", sut.getFizzBuzzNumber(a));
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    public void shouldReturnFizzBuzz() {
        assertEquals("FizzBuzz", sut.getFizzBuzzNumber(15));
    }

    @Test
    @DisabledOnJre(JRE.JAVA_17)
    public void shouldCheckNotNull() {
        assertNotNull(sut);
    }


    @AfterAll
    public static void tearDownAll(){
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        System.out.println("Execution time "+ result +" ms.");
    }

}
