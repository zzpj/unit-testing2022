package com.example.calculator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName(" Calculator testing ")
//@TestMethodOrder(MethodOrderer.MethodName.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest {
    Calculator sut;
    static long startTime;

    @BeforeAll
    public static void initAll(){
        startTime = System.currentTimeMillis();
    }

    @BeforeEach
    public void setup(){
        sut  = new Calculator();
    }

    //    @Order(100)
    @Test
    @DisplayName(" Calculates adding two numbers")
    public void shouldAddTwoNumbers(){
        //given

        //when
        int actual = sut.add(4, 3);
        //then
        assertEquals(7, actual);
    }

    //    @Order(200)
    @Test
    public void shouldCheckPrimeNumbers() {
        assertAll(
                () ->assertTrue(sut.isPrime(7)),
                () ->assertFalse(sut.isPrime(12))
        );
    }

    @Test
    @EnabledOnOs(OS.MAC)
    public void shouldCheckNotNull() {

        assertNotNull(sut, " check null ");
    }

    @RepeatedTest(10)
    @Tag("FEATURE-2")
    //@Disabled("because does not work")
    public void shouldCheckTimeout() {
        assertTimeout(Duration.ofMillis(30), () -> sut.multiply(4,6));
    }

    @Test
    @Tag("FEATURE-1")
    //@EnabledOnJre(JRE.JAVA_18)
    //@EnabledIfSystemProperty( named =  "os.arch", matches = ".*64.*")
    public void shouldThrownAnException() {

        NotDividedByZeroException notDividedByZeroException = assertThrows(NotDividedByZeroException.class, () -> sut.divide(5, 0));
        assertEquals("Can't by zero!", notDividedByZeroException.getMessage());
    }

    @AfterAll
    public static void tearDownAll(){
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        System.out.println("execution time "+ result +" ms ");
    }
}