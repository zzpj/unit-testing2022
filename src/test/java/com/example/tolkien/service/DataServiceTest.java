package com.example.tolkien.service;
import com.example.tolkien.model.TolkienCharacter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.tolkien.model.Race.HOBBIT;
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
        // TODO check that name is "Frodo"
        // TODO check that name is not "Frodon"
        assertEquals(frodo.age, 33);
        assertEquals(frodo.getName(), "Frodo");
        assertNotEquals(frodo.getName(), "Frodon");
    }

    @Test
    void ensureThatEqualsWorksForCharaters() {
        Object jake = new TolkienCharacter("Jake", 43, HOBBIT);
        Object sameJake = jake;
        Object jakeClone = new TolkienCharacter("Jake", 12, HOBBIT);

        // TODO check that:
        // jake is equal to sameJake
        // jake is not equal to jakeClone
        assertEquals(jake, sameJake);
        assertNotEquals(jake, jakeClone);
    }

    @Test
    void checkInheritance() throws ClassNotFoundException {
        TolkienCharacter tolkienCharacter = dataService.getFellowship().get(0);
        // TODO check that tolkienCharacter.getClass is not a movie class
        assertNotEquals(tolkienCharacter.getClass(), Class.forName("com.example.tolkien.model.Movie"));
    }

    @Test
    void ensureFellowShipCharacterAccessByNameReturnsNullForUnknownCharacter() {
        // TODO imlement a check that dataService.getFellowshipCharacter returns null for an
        // unknow felllow, e.g. "Lars"
        assertThrows(NoSuchElementException.class, () -> dataService.getFellowshipCharacter("Lars"));
    }
    @Disabled
    @Test
    void ensureFellowShipCharacterAccessByNameWorksGivenCorrectNameIsGiven() {
        // TODO imlement a check that dataService.getFellowshipCharacter returns a fellow for an
        // existing felllow, e.g. "Frodo"
        assertEquals(dataService.getFellowshipCharacter("Sam"), dataService.sam);

    }


    @Test
    void ensureThatFrodoAndGandalfArePartOfTheFellowsip() {

        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // TODO check that Frodo and Gandalf are part of the fellowship
        assertTrue(fellowship.contains(dataService.frodo));
        assertTrue(fellowship.contains(dataService.gandalf));
    }

    // TODO Use @RepeatedTest(int) to execute this test 1000 times
    @Test
    @Tag("slow")
    @RepeatedTest(1000)
    @DisplayName("Minimal stress testing: run this test 1000 times to ")
    void ensureThatWeCanRetrieveFellowshipMultipleTimes() {
        dataService = new DataService();
        assertNotNull(dataService.getFellowship());
    }

    @Test
    void ensureOrdering() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // ensure that the order of the fellowship is:
        // frodo, sam, merry,pippin, gandalf,legolas,gimli,aragorn,boromir
        String[] characters = {"Frodo", "Sam", "Merry", "Pippin", "Gandalf", "Legolas", "Gimli", "Aragorn", "Boromir"};
        for (int i=0;i<characters.length;i++)
            assertEquals(fellowship.get(i).getName(), characters[i]);
    }

    @Test
    void ensureAge() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // TODO test ensure that all hobbits and men are younger than 100 years
//        assertTrue();


        // TODO also ensure that the elfs, dwars the maia are all older than 100 years


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
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> frodo.setAge(-1));
        assertEquals(throwable.getMessage(), "Age is not allowed to be smaller than zero");
    }

    @Disabled
    @Test
    public void testThatAgeMustBeLargerThanZeroViaConstructor() {
        // use assertThrows() rule to check that an IllegalArgumentException exception is thrown and
        // that the message is:
        // "Age is not allowed to be smaller than zero"

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            TolkienCharacter frodo = new TolkienCharacter("Frodo", -1, HOBBIT);
        });
        assertEquals(throwable.getMessage(), "Age is not allowed to be smaller than zero");
    }

    @Test
    public void ensureServiceDoesNotRunToLong() {
        //assertTimeout;
        assertTimeout(Duration.ofMillis(3000), dataService::update);
    }

    @Test
    @EnabledOnOs(OS.MAC)
    @EnabledOnJre(JRE.JAVA_8)
    public void enableTestOnlyOnCertainPlatforms() {
        //
    }
}