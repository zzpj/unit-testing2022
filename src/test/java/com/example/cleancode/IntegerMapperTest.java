package com.example.cleancode;

import com.example.cleancode.IntegerMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("integerMapperTest class test")
public class IntegerMapperTest {
    private com.example.cleancode.IntegerMapper integerMapper;
    List<Integer> integers;
    static long start;

    @BeforeAll
    public static void initAll() {
        start = System.currentTimeMillis();
    }

    @BeforeEach
    void setUp() {
        integers = new ArrayList<>(List.of(-2,10,14,10,70,42));
        integerMapper = new IntegerMapper(integers);
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void shouldBeNotNull() {
        assertNotNull(integerMapper);
    }

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    @DisplayName("test integers minimum value")
    void shouldReturnCorrectMinimumValue() {
        assertEquals(integerMapper.getMinIntegerMapValue(), -2);
    }


    @Test
//    @EnabledOnJre(JRE.JAVA_14)
    @DisplayName("test integers maximum value")
    void shouldReturnCorrectMaximumValue() {
        assertEquals(integerMapper.getMaxIntegerMapValue(), 70);
    }

    @Test
//    @EnabledOnJre(JRE.JAVA_14)
    @DisplayName("test method calculating average")
    void shouldReturnCorrectAverage() {
        assertEquals(integerMapper.calculateAverage(), 24);
    }

    @Test
    @Tag("GetterTest")
    @DisplayName("test method giving the quantity of the key")
    void getIntegerQuantityTest() {
        assertEquals(integerMapper.getIntegerQuantity(10), 2);
    }

    @Test
    @Tag("MapMethod")
    @DisplayName("test method adding the new integer to map")
    void addIntegerToMapTest() {
        integerMapper.addIntegerToMap(10);
        // the quantity for key = 10 is increased to 3
        assertEquals(integerMapper.getIntegerQuantity(10), 3);
    }


    @AfterEach
    void tearDown() {
        long end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end - start));
    }
}
