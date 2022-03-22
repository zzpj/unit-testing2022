package fizzBuzzProblem;


import com.example.fizzbuzz.FizzBuzzProblem;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FizzBuzzProblemTest {

    static FizzBuzzProblem sut;
    static List<Integer> fiveList;
    static List<Integer> primeNumberList;

    @BeforeEach
    void init(){
        sut = new FizzBuzzProblem();
    }

    @BeforeAll
    static void initList(){
        fiveList = Arrays.asList(5, 20, 80, 5000, 40);
        primeNumberList = Arrays.asList(7, 11, 17, 23, 29);
    }

    @ParameterizedTest
    @DisplayName("check divisibility by 15")
    @ValueSource(ints = {15, 30, 45, 150, 1500000})
    void shouldReturnFizzBuzz(int num){
        assertEquals("FizzBuzz", sut.getFizzBuzzNumber(num));
    }

    @Test
    @DisplayName("check divisibility by 5")
    void shouldReturnBuzz(){
        for(Integer num : fiveList){
            assertEquals("Buzz", sut.getFizzBuzzNumber(num));
        }
    }

    @Disabled
    @ParameterizedTest
    @DisplayName("check divisibility by 3 but is disabled")
    @ValueSource(ints = {3, 6, 9, 12, 33})
    void shouldReturnFizzButTestIsDisabled(Integer num){
        assertEquals("Fizz", sut.getFizzBuzzNumber(num));
    }

    @ParameterizedTest
    @DisplayName("check divisibility by 3")
    @ValueSource(ints = {3, 6, 9, 12, 33})
    void shouldReturnFizz(Integer num){
        assertEquals("Fizz", sut.getFizzBuzzNumber(num));
    }

    @Test
    @DisplayName("check neither")
    void shouldReturnNumber(){
        for(Integer num : primeNumberList){
            assertEquals(String.valueOf(num), sut.getFizzBuzzNumber(num));
        }
    }

    @Test
    @DisplayName("should not run at linux")
    @DisabledOnOs(OS.LINUX)
    void shouldNotRunAtLinux(){
        for(Integer num : primeNumberList){
            assertEquals(String.valueOf(num), sut.getFizzBuzzNumber(num));
        }
    }

    @Test
    @DisplayName("should run only at windows")
    @EnabledOnOs(OS.WINDOWS)
    void shouldRunOnlyAtWindows(){
        for(Integer num : primeNumberList){
            assertEquals(String.valueOf(num), sut.getFizzBuzzNumber(num));
        }
    }

    @Test
    @DisplayName("should run only on Java 14 until Java 17")
    @EnabledForJreRange(min = JRE.JAVA_14, max = JRE.JAVA_17)
    void shouldRunOnlyOnJRE14UntilJava17(){
        for(Integer num : primeNumberList){
            assertEquals(String.valueOf(num), sut.getFizzBuzzNumber(num));
        }
    }


}
