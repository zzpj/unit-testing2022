package com.example.tolkien.service;

import com.example.tolkien.model.Movie;
import com.example.tolkien.model.TolkienCharacter;
import org.junit.jupiter.api.*;

import java.util.List;

import static com.example.tolkien.model.Race.HOBBIT;
import static org.junit.jupiter.api.Assertions.*;

class DataServiceTest {
    DataService dataService;

    @BeforeEach
    public void setUp() {
        dataService = new DataService();
    }

    @Test
    void ensureThatInitializationOfTolkeinCharactorsWorks() {
        //given
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
        //when
        int age = frodo.getAge();
        String name = frodo.getName();
        //then
        assertEquals(33, age);
        assertEquals("Frodo", name);
        assertNotEquals("Frodon", name);
    }

    @Test
    void ensureThatEqualsWorksForCharaters() {
        //given
        Object jake = new TolkienCharacter("Jake", 43, HOBBIT);
        Object sameJake = jake;
        Object jakeClone = new TolkienCharacter("Jake", 12, HOBBIT);
        //then
        assertEquals(jake, sameJake);
        assertNotEquals(jake, jakeClone);
    }

    @Test
    void checkInheritance() {
        TolkienCharacter tolkienCharacter = dataService.getFellowship().get(0);
        assertNotEquals(tolkienCharacter.getClass(), Movie.class);
    }

    @Test
    void ensureFellowShipCharacterAccessByNameReturnsNullForUnknownCharacter() {
        assertNull(dataService.getFellowshipCharacter("Lars"));
    }

    @Test
    void ensureFellowShipCharacterAccessByNameWorksGivenCorrectNameIsGiven() {
        assertEquals("Frodo", dataService.getFellowshipCharacter("Frodo").getName());
    }


    @Test
    void ensureThatFrodoAndGandalfArePartOfTheFellowsip() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();
        assertTrue(fellowship.stream().anyMatch(c -> c.getName().equals("Frodo")));
        assertTrue(fellowship.stream().anyMatch(c -> c.getName().equals("Gandalf")));

    }

    @RepeatedTest(100)
    @Tag("slow")
    @DisplayName("Minimal stress testing: run this test 1000 times to ")
    void ensureThatWeCanRetrieveFellowshipMultipleTimes() {
        dataService = new DataService();
        assertNotNull(dataService.getFellowship());
    }

    @Test
    void ensureOrdering() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();
        String[] names = {"Frodo", "Sam", "Merry", "Pippin", "Gandalf", "Legolas", "Gimli", "Aragorn", "Boromir"};
        for (int i = 0; i < fellowship.size(); i++) {
            assertEquals(fellowship.get(i).getName(), names[i]);
        }
    }

    @Test
    @Disabled
    void ensureAge() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // TODO test ensure that all hobbits and men are younger than 100 years

        // TODO also ensure that the elfs, dwars the maia are all older than 100 years


        // HINT fellowship.stream might be useful here
    }

    @Test
    @Disabled
    void ensureThatFellowsStayASmallGroup() {

        List<TolkienCharacter> fellowship = dataService.getFellowship();

        // TODO Write a test to get the 20 element from the fellowship throws an
        // IndexOutOfBoundsException
    }

    @Test
    @Disabled
    void exceptionTesting() {
        DataService dataService = new DataService();
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> dataService.getFellowship().get(20));
        assertEquals("Index 20 out of bounds for length 9", exception.getMessage());
    }

    @Test
    @Disabled
    public void ensureThatAgeMustBeLargerThanZeroViaSetter() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
        // use assertThrows() rule to check that the message is:
        // Age is not allowed to be smaller than zero
        frodo.setAge(-1);

    }

    @Test
    @Disabled
    public void testThatAgeMustBeLargerThanZeroViaConstructor() {
        // use assertThrows() rule to check that an IllegalArgumentException exception is thrown and
        // that the message is:
        // "Age is not allowed to be smaller than zero"

        TolkienCharacter frodo = new TolkienCharacter("Frodo", -1, HOBBIT);
    }

    @Test
    @Disabled
    public void ensureServiceDoesNotRunToLong() {
        //assertTimeout;
    }

    @Test
    @Disabled
    public void enableTestOnlyOnCertainPlatforms() {
        //
    }
}