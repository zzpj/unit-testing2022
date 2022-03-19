package com.example.tolkien.service;


import com.example.tolkien.model.TolkienCharacter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

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
    void ensureThatInitializationOfTolkienCharactersWorks() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);

        assertAll(
                // TODO check that age is 33
                () -> assertEquals(33, frodo.getAge()),
            // TODO check that name is "Frodo"
                () -> assertEquals("Frodo", frodo.getName()),
            // TODO check that name is not "Frodon"
                () -> assertNotEquals("Frodon", frodo.getName())
        );
    }

    @Test
    void ensureThatEqualsWorksForCharaters() {
        Object jake = new TolkienCharacter("Jake", 43, HOBBIT);
        Object sameJake = jake;
        Object jakeClone = new TolkienCharacter("Jake", 12, HOBBIT);
        // TODO check that:
        assertAll(
                // jake is equal to sameJake
                () -> assertEquals(sameJake, jake),
                // jake is not equal to jakeClone
                () -> assertNotEquals(jakeClone, jake)
        );
    }

    @Test
    void checkInheritance() throws ClassNotFoundException {
        TolkienCharacter tolkienCharacter = dataService.getFellowship().get(0);
        // TODO check that tolkienCharacter.getClass is not a movie class
        assertNotEquals(Class.forName("com.example.tolkien.model.Movie"), tolkienCharacter.getClass());
    }

    @Test
    void ensureFellowShipCharacterAccessByNameReturnsNullForUnknownCharacter() {
        // TODO imlement a check that dataService.getFellowshipCharacter returns null for an
        // unknow felllow, e.g. "Lars"
        NoSuchElementException noSuchElementException =
                assertThrows(NoSuchElementException.class, () -> dataService.getFellowshipCharacter("Michael Gebelitto"));
        assertEquals("No value present", noSuchElementException.getMessage());
    }

    @Test
    void ensureFellowShipCharacterAccessByNameWorksGivenCorrectNameIsGiven() {
        // TODO imlement a check that dataService.getFellowshipCharacter returns a fellow for an
        // existing felllow, e.g. "Frodo"
        assertEquals(dataService.getFellowshipCharacter("Boromir"), dataService.boromir);
    }


    @Test
    void ensureThatFrodoAndGandalfArePartOfTheFellowsip() {

        List<TolkienCharacter> fellowship = dataService.getFellowship();
        // TODO check that Frodo and Gandalf are part of the fellowship
        assertAll (
                () -> assertTrue(fellowship.contains(dataService.gandalf)),
                () -> assertTrue(fellowship.contains(dataService.frodo))
        );
    }

    // TODO Use @RepeatedTest(int) to execute this test 1000 times
    @RepeatedTest(1000)
    @Tag("slow")
    @DisplayName("Minimal stress testing: run this test 1000 times")
    void ensureThatWeCanRetrieveFellowshipMultipleTimes() {
        dataService = new DataService();
        assertNotNull(dataService.getFellowship());
    }

    @Test
    void ensureOrdering() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();
        // ensure that the order of the fellowship is:
        // frodo, sam, merry,pippin, gandalf,legolas,gimli,aragorn,boromir
        List<TolkienCharacter> correctOrder = List.of(
                dataService.frodo,
                dataService.sam,
                dataService.merry,
                dataService.pippin,
                dataService.gandalf,
                dataService.legolas,
                dataService.gimli,
                dataService.aragorn,
                dataService.boromir
        );
        assertEquals(correctOrder, fellowship);
    }

    @Test
    void ensureAge() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        assertAll(
                // TODO test ensure that all hobbits and men are younger than 100 years
                () -> assertTrue(fellowship.stream().filter(tolkienCharacter -> List.of(MAN, HOBBIT)
                                .contains(tolkienCharacter.getRace()))
                                .allMatch(tolkienCharacter -> tolkienCharacter.getAge() < 100)),
                // TODO also ensure that the elfs, dwars the maia are all older than 100 years
                () -> assertTrue(fellowship.stream().filter(tolkienCharacter -> List.of(ELF, DWARF, MAIA)
                                .contains(tolkienCharacter.getRace()))
                                .allMatch(tolkienCharacter -> tolkienCharacter.getAge() > 100))
        );

        // HINT fellowship.stream might be useful here
    }

    @Test
    void ensureThatFellowsStayASmallGroup() {

        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // TODO Write a test to get the 20 element from the fellowship throws an
        // IndexOutOfBoundsException
        IndexOutOfBoundsException indexOutOfBoundsException =
                assertThrows(IndexOutOfBoundsException.class, () -> fellowship.get(20));
        assertEquals("Index 20 out of bounds for length 9", indexOutOfBoundsException.getMessage());
    }

    @Test
    @Disabled
    void exceptionTesting() {
        // TO JEST TO SAMO CO WYZEJ
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
    @Disabled
    public void testThatAgeMustBeLargerThanZeroViaConstructor() {
        // use assertThrows() rule to check that an IllegalArgumentException exception is thrown and
        // that the message is:
        // "Age is not allowed to be smaller than zero"
        // there is no constraint on age field. Sth like javax @min() would be sufficient
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new TolkienCharacter("Frodo", -1, HOBBIT));
        assertEquals("Age is not allowed to be smaller than zero", exception.getMessage());
    }

    @Test
    public void ensureServiceDoesNotRunToLong() {
        assertTimeout(Duration.ofMillis(3000), () -> dataService.update());
    }

    @Test
    @EnabledOnOs(OS.MAC)
    public void enableTestOnlyOnCertainPlatforms() {
        assertEquals(dataService.getFellowship().size(), 9);
    }
}