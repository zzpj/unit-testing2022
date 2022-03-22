package com.example.tolkien.service;

import com.example.tolkien.model.Movie;
import com.example.tolkien.model.TolkienCharacter;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import java.time.Duration;
import java.util.List;

import static com.example.tolkien.model.Race.*;
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

    @RepeatedTest(1000)
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
    void ensureAge() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();

        fellowship.stream().filter(character -> character.getRace().equals(HOBBIT))
                .forEach(character -> assertTrue(character.getAge() < 100));

        fellowship.stream().filter(character -> character.getRace().equals(MAN))
                .forEach(character -> assertTrue(character.getAge() < 100));

        fellowship.stream().filter(character -> character.getRace().equals(ELF))
                .forEach(character -> assertTrue(character.getAge() > 100));

        fellowship.stream().filter(character -> character.getRace().equals(DWARF))
                .forEach(character -> assertTrue(character.getAge() > 100));
    }

    @Test
    void ensureThatFellowsStayASmallGroup() {
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
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> frodo.setAge(-1));
        assertEquals("Age is not allowed to be smaller than zero", exception.getMessage());

    }

    @Test
    public void testThatAgeMustBeLargerThanZeroViaConstructor() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            TolkienCharacter frodo = new TolkienCharacter("Frodo", -1, HOBBIT);
            System.out.println("D");
        });
        assertEquals("Age is not allowed to be smaller than zero", exception.getMessage());
    }

    @Test
    public void ensureServiceDoesNotRunToLong() {
        DataService dataService = new DataService();
        assertTimeout(Duration.ofMillis(2500), dataService::update);
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    @EnabledOnJre(JRE.JAVA_17)
    public void enableTestOnlyOnCertainPlatforms() {
        //
    }
}