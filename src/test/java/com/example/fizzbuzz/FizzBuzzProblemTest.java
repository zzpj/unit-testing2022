package com.example.fizzbuzz;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzProblemTest {

    private static String divisible_by_3_and_5;
    private static String divisible_by_5;
    private static String divisible_by_3;
    private static LocalDateTime startTime;
    FizzBuzzProblem fizzBuzzProblem;

    @BeforeAll
    static void beforeAll() {
        divisible_by_3 = "Fizz";
        divisible_by_5 = "Buzz";
        divisible_by_3_and_5 = "FizzBuzz";
        startTime = LocalDateTime.now();
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Wszystkie testy zajęły: " + Duration.between(startTime, LocalDateTime.now()).toMillis() + "ms.");
    }

    @BeforeEach
    void setUp() {
        fizzBuzzProblem = new FizzBuzzProblem();
    }

    @AfterEach
    void tearDown() {
        assertNotNull(fizzBuzzProblem);
    }

    @Test
    void getFizzBuzzNumberTest() {
        //given
        int divisible_by_3 = 3;
        int divisible_by_5 = 5;
        int divisible_by_3_and_5 = 15;
        int not_divisible = 1;
        //when
        String actual_divisible_by_3 = fizzBuzzProblem.getFizzBuzzNumber(divisible_by_3);
        String actual_divisible_by_5 = fizzBuzzProblem.getFizzBuzzNumber(divisible_by_5);
        String actual_divisible_by_3_and_5 = fizzBuzzProblem.getFizzBuzzNumber(divisible_by_3_and_5);
        String actual_not_divisible = fizzBuzzProblem.getFizzBuzzNumber(not_divisible);
        //then
        assertEquals(actual_divisible_by_3, actual_divisible_by_3);
        assertEquals(actual_divisible_by_5, actual_divisible_by_5);
        assertEquals(actual_divisible_by_3_and_5, actual_divisible_by_3_and_5);
        assertEquals(String.valueOf(actual_not_divisible), actual_not_divisible);
    }

    @Test
    @EnabledOnOs({OS.WINDOWS, OS.LINUX})
    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_17)
    void assertGetFizzBuzzNumberTest() {
        assertAll(
                () -> assertEquals(divisible_by_3, fizzBuzzProblem.getFizzBuzzNumber(3)),
                () -> assertEquals(divisible_by_5, fizzBuzzProblem.getFizzBuzzNumber(5)),
                () -> assertEquals("7", fizzBuzzProblem.getFizzBuzzNumber(7)),
                () -> assertEquals(divisible_by_3_and_5, fizzBuzzProblem.getFizzBuzzNumber(30))
        );
    }

    @ParameterizedTest
    @CsvSource({"3", "6", "9", "12"})
    @Disabled("Checking if number is divisible by 3")
    void isDivisibleBy3(int number) {
        assertEquals(divisible_by_3, fizzBuzzProblem.getFizzBuzzNumber(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 20})
    @Disabled("Checking if number is divisible by 5")
    void isDivisibleBy5(int number) {
        assertEquals(divisible_by_5, fizzBuzzProblem.getFizzBuzzNumber(number));
    }

    static Stream<Integer> first5NotDivisibleNumbersProvider() {
        return Stream.of(1, 2, 4, 7, 8);
    }

    @ParameterizedTest
    @MethodSource("first5NotDivisibleNumbersProvider")
    @Disabled("Checking if number is not divisible by 3 or 5")
    void isNotDivisibleBy3or5(int number) {
        assertEquals(String.valueOf(number), fizzBuzzProblem.getFizzBuzzNumber(number));
    }
}