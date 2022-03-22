package com.example.tolkien.service;

import com.example.tolkien.model.Movie;
import com.example.tolkien.model.TolkienCharacter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.time.Duration;
import java.util.List;

import static com.example.tolkien.model.Race.*;
import static org.junit.jupiter.api.Assertions.*;

class DataServiceTest {
    DataService dataService;

    @Test
    void ensureThatInitializationOfTolkeinCharactorsWorks() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);

        assertEquals(frodo.getAge(), 33);
        assertEquals(frodo.getName(), "Frodo");
        assertFalse(frodo.getName().equals("Frodon"));
    }

    @Test
    void ensureThatEqualsWorksForCharaters() {
        Object jake = new TolkienCharacter("Jake", 43, HOBBIT);
        Object sameJake = jake;
        Object jakeClone = new TolkienCharacter("Jake", 12, HOBBIT);

        assertEquals(jake, sameJake);
        assertFalse(jake.equals(jakeClone));
    }

    @Test
    void checkInheritance() {
        dataService = new DataService();
        TolkienCharacter tolkienCharacter = dataService.getFellowship().get(0);

        assertFalse(tolkienCharacter.getClass().isInstance(Movie.class));
    }

    @Test
    void ensureFellowShipCharacterAccessByNameReturnsNullForUnknownCharacter() {
        dataService = new DataService();
        assertNull(dataService.getFellowshipCharacter("Lars"));
    }

    @Test
    void ensureFellowShipCharacterAccessByNameWorksGivenCorrectNameIsGiven() {
        dataService = new DataService();
        assertNotNull(dataService.getFellowshipCharacter("Frodo"));
    }


    @Test
    void ensureThatFrodoAndGandalfArePartOfTheFellowsip() {
        dataService =  new DataService();
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        assertNotNull(fellowship.stream().filter(s-> s.getName().equals("Frodo")));
        assertNotNull(fellowship.stream().filter(s-> s.getName().equals("Gandalf")));
    }


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
        DataService dataService = new DataService();
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        assertEquals(fellowship.get(0).getName(), "Frodo");
        assertEquals(fellowship.get(1).getName(), "Sam");
        assertEquals(fellowship.get(2).getName(), "Merry");
        assertEquals(fellowship.get(3).getName(), "Pippin");
        assertEquals(fellowship.get(4).getName(), "Gandalf");
        assertEquals(fellowship.get(5).getName(), "Legolas");
        assertEquals(fellowship.get(6).getName(), "Gimli");
        assertEquals(fellowship.get(7).getName(), "Aragorn");
        assertEquals(fellowship.get(8).getName(), "Boromir");

    }

    @Test
    void ensureAge() {
        DataService dataService = new DataService();
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        fellowship.stream().forEach(s -> {
            if (s.getRace().equals(HOBBIT) || s.getRace().equals(MAN)) {
                assertTrue(s.getAge() < 100);
            }
            if (s.getRace().equals(ELF) || s.getRace().equals(DWARF) || s.getRace().equals(MAIA)) {
                assertTrue(s.getAge() > 100);
            }
        });
    }

    @Test
    void ensureThatFellowsStayASmallGroup() {
        DataService dataService = new DataService();
        List<TolkienCharacter> fellowship = dataService.getFellowship();

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
        assertThrows(IllegalArgumentException.class, () -> frodo.setAge(-1), "Age is not allowed to be smaller than zero");

    }

    @Test
    public void testThatAgeMustBeLargerThanZeroViaConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new TolkienCharacter("Frodo", -1, HOBBIT), "Age is not allowed to be smaller than zero");
    }

    @Test
    public void ensureServiceDoesNotRunToLong() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);

        assertTimeout(Duration.ofSeconds(5),  ()-> frodo.getAge());
    }

    @Test
    @EnabledOnOs({OS.WINDOWS, OS.MAC})
    public void enableTestOnlyOnCertainPlatforms() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);

        assertEquals(frodo.getName(), "Frodo");
    }
}