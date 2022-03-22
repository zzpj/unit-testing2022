package com.example.fizzbuzz;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@EnabledForJreRange(min = JRE.JAVA_11, max = JRE.JAVA_17)
public class FizzBuzzTest {

    static FizzBuzzProblem fizzBuzz;

    static List<Integer> divByFive;
    static List<Integer> divByFifteen;
    static String string;

    @BeforeAll
    static void beforeAll() {
        divByFive = Arrays.asList(5, 10, 20, 25);
        divByFifteen = Arrays.asList(15, 30, 45);
        string = "12Fizz4BuzzFizz78FizzBuzz";
    }

    @BeforeEach
    void beforeEach() {
        fizzBuzz = new FizzBuzzProblem();
    }


    @ParameterizedTest
    @ValueSource(ints = {3, 6, 9, 12})
    @DisplayName("Checking to see if is divisible by three")
    void getFizzBuzzNumber_IsDivisibleByThree_Equals(int number) {
        assertEquals("Fizz", fizzBuzz.getFizzBuzzNumber(number));

    }

    @Test
    @DisplayName("Checking to see if is divisible by five")
    void getFizzBuzzNumber_IsDivisibleByFive_Equals() {
        for(Integer i : divByFive) {
            assertEquals("Buzz", fizzBuzz.getFizzBuzzNumber(i));
        }
    }

    @Test
    @DisplayName("Checking to see if is divisible by fifteen")
    @DisabledIf("java.lang.System.getProperty('os.name').toLowerCase().contains('mac')")
    void getFizzBuzzNumber_IsDivisibleByFifteen_Equals() {

        for(Integer i : divByFifteen) {
            assertEquals("FizzBuzz", fizzBuzz.getFizzBuzzNumber(i));
        }
    }

    @Test
    @DisplayName("Comparing two strings")
    @DisabledIf("systemProperty.get('user.country') == 'RU'")
    void getFizzBuzzNumber_CheckString_Equals() {
        StringBuilder fizzBuzzString = new StringBuilder();
        for(int i = 1; i <= 10; i++) {
            fizzBuzzString.append(fizzBuzz.getFizzBuzzNumber(i));
        }
        assertEquals(fizzBuzzString.toString(), string);
    }

}
