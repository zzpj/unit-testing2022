package com.example.tolkien.service;

import com.example.calculator.Calculator;
import com.example.tolkien.model.Movie;
import com.example.tolkien.model.TolkienCharacter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.tolkien.model.Race.*;
import static org.junit.jupiter.api.Assertions.*;

class DataServiceTest {
    // TODO initialize before each test
    DataService dataService;

    @BeforeEach
    public void setup() {
        dataService = new DataService();
    }

    @Test
    void ensureThatInitializationOfTolkeinCharactorsWorks() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);

        // TODO check that age is 33
        assertEquals(33, frodo.getAge());
        // TODO check that name is "Frodo"
        assertEquals("Frodo", frodo.getName());
        // TODO check that name is not "Frodon"
        assertNotEquals("Frodon", frodo.getName());
    }

    @Test
    void ensureThatEqualsWorksForCharaters() {
        Object jake = new TolkienCharacter("Jake", 43, HOBBIT);
        Object sameJake = jake;
        Object jakeClone = new TolkienCharacter("Jake", 12, HOBBIT);
        // TODO check that:
        // jake is equal to sameJake
        assertEquals(jake, sameJake);
        // jake is not equal to jakeClone
        assertNotEquals(jake, jakeClone);
    }

    @Test
    void checkInheritance() {
        TolkienCharacter tolkienCharacter = dataService.getFellowship().get(0);
        // TODO check that tolkienCharacter.getClass is not a movie class
        assertNotEquals(Movie.class, tolkienCharacter.getClass());
    }

    @Test
    void ensureFellowShipCharacterAccessByNameReturnsNullForUnknownCharacter() {
        // TODO imlement a check that dataService.getFellowshipCharacter returns null for an
        // unknow felllow, e.g. "Lars"
        assertThrows(NoSuchElementException.class, () -> dataService.getFellowshipCharacter("Lars").getName());
    }


    @Test
    void ensureFellowShipCharacterAccessByNameWorksGivenCorrectNameIsGiven() {
        // TODO imlement a check that dataService.getFellowshipCharacter returns a fellow for an
        // existing felllow, e.g. "Frodo"
        assertEquals("Frodo", dataService.getFellowshipCharacter("Frodo").getName());
    }


    @Test
    void ensureThatFrodoAndGandalfArePartOfTheFellowsip() {

        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // TODO check that Frodo and Gandalf are part of the fellowship
        assertAll(
                () -> assertTrue(fellowship.stream().anyMatch(s -> Objects.equals(s.getName(), "Gandalf"))),
                () -> assertTrue(fellowship.stream().anyMatch(s -> Objects.equals(s.getName(), "Frodo")))
        );
    }

    // TODO Use @RepeatedTest(int) to execute this test 1000 times
    @Test
    @Tag("slow")
    @DisplayName("Minimal stress testing: run this test 1000 times to ")
    @RepeatedTest(1000)
    void ensureThatWeCanRetrieveFellowshipMultipleTimes() {
        dataService = new DataService();
        assertNotNull(dataService.getFellowship());
    }

    @Test
    void ensureOrdering() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // ensure that the order of the fellowship is:
        // frodo, sam, merry,pippin, gandalf,legolas,gimli,aragorn,boromir
        List<String> names = Arrays.asList("Frodo", "Sam", "Merry", "Pippin", "Gandalf", "Legolas", "Gimli", "Aragorn", "Boromir");
        assertEquals(names, fellowship.stream().map(TolkienCharacter::getName).collect(Collectors.toList()));
    }

    @Test
    void ensureAge() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // TODO test ensure that all hobbits and men are younger than 100 years
        assertTrue(fellowship.stream()
                .filter(s -> Objects.equals(s.getRace().getName(), MAN.getName()) || Objects.equals(s.getRace().getName(), HOBBIT.getName()))
                .noneMatch(s -> s.getAge() > 100));

        // TODO also ensure that the elfs, dwars the maia are all older than 100 years
        assertTrue(fellowship.stream()
                .filter(s -> Objects.equals(s.getRace().getName(), MAIA.getName()) || Objects.equals(s.getRace().getName(), ELF.getName())
                        || Objects.equals(s.getRace().getName(), DWARF.getName()))
                .noneMatch(s -> s.getAge() < 100));

        // HINT fellowship.stream might be useful here
    }

    @Test
    void ensureThatFellowsStayASmallGroup() {

        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // TODO Write a test to get the 20 element from the fellowship throws an
        // IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> fellowship.get(20));
    }

    @Test
    void exceptionTesting() {
        DataService dataService = new DataService();
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> dataService.getFellowship().get(20));
        assertEquals("Index 20 out of bounds for length 9", exception.getMessage());
    }

    @Test
    public void ensureThatAgeMustBeLargerThanZeroViaSetter() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
        // use assertThrows() rule to check that the message is:
        // Age is not allowed to be smaller than zero
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> frodo.setAge(-1));
        assertEquals("Age is not allowed to be smaller than zero", exception.getMessage());

    }

    @Test
    @Disabled("No such rule in constructor")
    public void testThatAgeMustBeLargerThanZeroViaConstructor() {
        // use assertThrows() rule to check that an IllegalArgumentException exception is thrown and
        // that the message is:
        // "Age is not allowed to be smaller than zero"

        TolkienCharacter frodo = new TolkienCharacter("Frodo", -1, HOBBIT);
    }

    @Test
    public void ensureServiceDoesNotRunToLong() {
        assertTimeout(Duration.ofMillis(100), () -> dataService.getFellowship());
    }

    @Test
    @EnabledOnOs(OS.MAC)
    public void enableTestOnlyOnCertainPlatforms() {
        assertFalse(dataService.getFellowship().isEmpty());
    }
}