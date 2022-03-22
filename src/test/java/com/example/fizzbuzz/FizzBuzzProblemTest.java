package com.example.fizzbuzz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

    public class FizzBuzzProblemTest {

        FizzBuzzProblem fizzBuzz;

        @BeforeEach
        void init() {
            fizzBuzz = new FizzBuzzProblem();
        }

        @ParameterizedTest
        @ValueSource(ints = {3, 6 , 9, 12})
        @EnabledOnJre(JRE.JAVA_17)
        @EnabledOnOs(value = {OS.LINUX, OS.WINDOWS}, disabledReason = "Works only on Linux or Windows")
        public void fizzSucceedingTest(int a) {
            assertEquals("Fizz", fizzBuzz.getFizzBuzzNumber(a));
        }

        @ParameterizedTest
        @ValueSource(ints = {5, 10 , 15, 20})
        @EnabledOnJre(JRE.JAVA_17)
        @EnabledOnOs(value = {OS.LINUX, OS.WINDOWS}, disabledReason = "Works only on Linux or Windows")
        public void fizzFailingTest(int a) {
            assertNotEquals("Fizz", fizzBuzz.getFizzBuzzNumber(a));
        }

        @ParameterizedTest
        @ValueSource(ints = {5, 10 , 20, 25})
        @EnabledOnJre(JRE.JAVA_17)
        @EnabledOnOs(value = {OS.LINUX, OS.WINDOWS}, disabledReason = "Works only on Linux or Windows")
        public void buzzSucceedingTest(int a) {
            assertEquals("Buzz", fizzBuzz.getFizzBuzzNumber(a));
        }

        @ParameterizedTest
        @ValueSource(ints = {3, 6 , 9, 12})
        @EnabledOnJre(JRE.JAVA_17)
        @EnabledOnOs(value = {OS.LINUX, OS.WINDOWS}, disabledReason = "Works only on Linux or Windows")
        public void buzzFailingTest(int a) {
            assertNotEquals("Buzz", fizzBuzz.getFizzBuzzNumber(a));
        }
    }
