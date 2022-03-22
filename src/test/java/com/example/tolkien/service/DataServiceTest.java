package com.example.tolkien.service;

import com.example.tolkien.model.Movie;
import com.example.tolkien.model.TolkienCharacter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.tolkien.model.Race.*;
import static org.junit.jupiter.api.Assertions.*;

class DataServiceTest {
    // TODO initialize before each test
    DataService dataService;
    static long startTime;

    @BeforeEach
    void init() {
        dataService = new DataService();
    }

    @Test
    void ensureThatInitializationOfTolkeinCharactorsWorks() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);

        // check that age is 33
        // check that name is "Frodo"
        // check that name is not "Frodon"
        assertAll(
                () -> assertEquals(frodo.getAge(),33),
                () -> assertEquals(frodo.getName(),"Frodo"),
                () -> assertNotEquals(frodo.getName(),"Frodon")
        );
    }

    @Test
    void ensureThatEqualsWorksForCharaters() {
        Object jake = new TolkienCharacter("Jake", 43, HOBBIT);
        Object sameJake = jake;
        Object jakeClone = new TolkienCharacter("Jake", 12, HOBBIT);


        // check that:
        // jake is equal to sameJake
        assertEquals(sameJake, jake);
        // jake is not equal to jakeClone
        assertNotEquals(jakeClone, jake);
    }

    @Test
    void checkInheritance() {
        TolkienCharacter tolkienCharacter = dataService.getFellowship().get(0);
        // check that tolkienCharacter.getClass is not a movie class
        assertNotEquals(tolkienCharacter.getClass(),Movie.class);
    }

    @Test
    void ensureFellowShipCharacterAccessByNameReturnsNullForUnknownCharacter() {
        // imlement a check that dataService.getFellowshipCharacter returns null for an
        // unknow felllow, e.g. "Lars"
        NoSuchElementException e = assertThrows(NoSuchElementException.class, () -> dataService.getFellowshipCharacter("Lars"));
        assertEquals("No value present", e.getMessage());
    }

    @Test
    void ensureFellowShipCharacterAccessByNameWorksGivenCorrectNameIsGiven() {
        // imlement a check that dataService.getFellowshipCharacter returns a fellow for an
        // existing felllow, e.g. "Frodo"
        // ** Missing getName() in getFellowshipCharacter return
        TolkienCharacter frodo = dataService.frodo;
        TolkienCharacter frodo2 = dataService.getFellowshipCharacter("Frodo");
        assertEquals(frodo, frodo2);
    }

    @Test
    void ensureThatFrodoAndGandalfArePartOfTheFellowsip() {

        List<TolkienCharacter> fellowship = dataService.getFellowship();
        // check that Frodo and Gandalf are part of the fellowship
        assertTrue(fellowship.contains(dataService.getFellowshipCharacter("Gandalf")));
        assertTrue(fellowship.contains(dataService.getFellowshipCharacter("Frodo")));
    }

    // Use @RepeatedTest(int) to execute this test 1000 times
    @RepeatedTest(1000)
    @Tag("slow")
    @DisplayName("Minimal stress testing: run this test 1000 times to ")
    void ensureThatWeCanRetrieveFellowshipMultipleTimes() {
        assertNotNull(dataService.getFellowship());
    }

    @Test
    void ensureOrdering() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // ensure that the order of the fellowship is:
        // frodo, sam, merry,pippin, gandalf,legolas,gimli,aragorn,boromir
        List<TolkienCharacter> testFellowship = new ArrayList<>();
        testFellowship.add(dataService.frodo);
        testFellowship.add(dataService.sam);
        testFellowship.add(dataService.merry);
        testFellowship.add(dataService.pippin);
        testFellowship.add(dataService.gandalf);
        testFellowship.add(dataService.legolas);
        testFellowship.add(dataService.gimli);
        testFellowship.add(dataService.aragorn);
        testFellowship.add(dataService.boromir);

        assertEquals(fellowship, testFellowship);
    }

    @Test
    void ensureAge() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();
        fellowship
                .stream()
                .filter(e -> e.getRace().equals(HOBBIT) || e.getRace().equals(MAN) )
                .forEach(e -> assertTrue(e.getAge() < 100));

        fellowship
                .stream()
                .filter(e -> e.getRace().equals(ELF) || e.getRace().equals(DWARF) || e.getRace().equals(MAIA))
                .forEach(e -> assertTrue(e.getAge() > 100));

    }

    @Test
    void ensureThatFellowsStayASmallGroup() {

        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // Write a test to get the 20 element from the fellowship throws an
        // IndexOutOfBoundsException

        IndexOutOfBoundsException e = assertThrows(IndexOutOfBoundsException.class, () -> fellowship.get(20));
        assertTrue(e.getMessage().contains("Index 20 out of bounds for length"));
    }

    @Test
    void exceptionTesting() {
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> dataService.getFellowship().get(20));
        assertEquals("Index 20 out of bounds for length 9", exception.getMessage());
    }

    @Test
    public void ensureThatAgeMustBeLargerThanZeroViaSetter() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
        // use assertThrows() rule to check that the message is:
        // Age is not allowed to be smaller than zero
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> frodo.setAge(-1));
        assertEquals("Age is not allowed to be smaller than zero", e.getMessage());
    }

    @Test
    public void testThatAgeMustBeLargerThanZeroViaConstructor() {
        // use assertThrows() rule to check that an IllegalArgumentException exception is thrown and
        // that the message is:
        // "Age is not allowed to be smaller than zero"

        //**Missing constructor throwing exception when age is negative
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new TolkienCharacter("Frodo", -1, HOBBIT));
        assertEquals("Age is not allowed to be smaller than zero", e.getMessage());

    }

    @Test
    public void ensureServiceDoesNotRunToLong() {
        assertTimeout(Duration.ofMillis(10), () -> dataService.getFellowship());

    }

    @Test
    @EnabledOnOs(value = {OS.LINUX, OS.WINDOWS}, disabledReason = "Works only on Linux or Windows")
    public void enableTestOnlyOnCertainPlatforms() {
        assertEquals(33, dataService.getFellowshipCharacter("Frodo").getAge());

    }
}