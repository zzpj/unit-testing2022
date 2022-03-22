package com.example.tolkien.service;

import com.example.tolkien.model.Movie;
import com.example.tolkien.model.Race;
import com.example.tolkien.model.TolkienCharacter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.tolkien.model.Race.*;
import static org.junit.jupiter.api.Assertions.*;

class DataServiceTest {
    // TODO initialize before each test
    DataService dataService;
    @BeforeEach
    void init() {
        dataService= new DataService();
    }

    @Test
    void ensureThatInitializationOfTolkeinCharactorsWorks() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
        assertEquals(33,frodo.getAge());
        assertEquals("Frodo",frodo.getName());
        assertNotEquals("Frodon",frodo.getName());
    }

    @Test
    void ensureThatEqualsWorksForCharaters() {
        Object jake = new TolkienCharacter("Jake", 43, HOBBIT);
        Object sameJake = jake;
        Object jakeClone = new TolkienCharacter("Jake", 12, HOBBIT);
        assertEquals(sameJake,jake);
        assertNotEquals(jakeClone,jake);
    }

    @Test
    void checkInheritance() {
        TolkienCharacter tolkienCharacter = dataService.getFellowship().get(0);
        Movie tolkienMovie = dataService.theFellowshipOfTheRing;
        assertNotEquals(tolkienMovie.getClass(),tolkienCharacter.getClass());
    }

    @Test
    void ensureFellowShipCharacterAccessByNameReturnsNullForUnknownCharacter() {
        assertNull(dataService.getFellowshipCharacter("Lars"));
    }

    @Test
    void ensureFellowShipCharacterAccessByNameWorksGivenCorrectNameIsGiven() {
        assertNotNull(dataService.getFellowshipCharacter("Frodo"));
    }


    @Test
    void ensureThatFrodoAndGandalfArePartOfTheFellowsip() {

        List<TolkienCharacter> fellowship = dataService.getFellowship();
        assertTrue(fellowship.contains(dataService.frodo));
        assertTrue(fellowship.contains(dataService.gandalf));
    }

    @RepeatedTest(1000)
    @Test
    @Tag("slow")
    @DisplayName("Minimal stress testing: run this test 1000 times to ")
    void ensureThatWeCanRetrieveFellowshipMultipleTimes() {
        dataService = new DataService();
        assertNotNull(dataService.getFellowship());
    }

    @Test
    void ensureOrdering() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();
        String[] fellowshipNames = new String[]{"Frodo","Sam","Merry","Pippin","Gandalf","Legolas","Gimli","Aragorn","Boromir"};
        for(int i=0;i<fellowshipNames.length;i++) {
            assertEquals(fellowship.get(i).getName(),fellowshipNames[i]);
        }
    }

    @Test
    void ensureAge() {
        List<TolkienCharacter> fellowship = dataService.getFellowship();
        ArrayList<Race> shortLivingRaces = new ArrayList<Race>(Arrays.asList(HOBBIT,MAN));
        ArrayList<Race> longLivingRaces = new ArrayList<Race>(Arrays.asList(ELF,DWARF,MAIA));
        assertTrue(fellowship.stream().allMatch(n-> shortLivingRaces.contains(n.getRace())?n.getAge()<100
                :(!longLivingRaces.contains(n) || n.getAge() > 100)));
    }

    @Test
    void ensureThatFellowsStayASmallGroup() {

        List<TolkienCharacter> fellowship = dataService.getFellowship();
        assertThrows(IndexOutOfBoundsException.class,()->{
            fellowship.get(20);
        });

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
        assertThrows(IllegalArgumentException.class,() -> {
            frodo.setAge(-1);
        },"Age is not allowed to be smaller than zero");
    }

    @Test
    public void testThatAgeMustBeLargerThanZeroViaConstructor() {
        assertThrows(IllegalArgumentException.class,() -> {
            TolkienCharacter frodo = new TolkienCharacter("Frodo", -1, HOBBIT);
        },"Age is not allowed to be smaller than zero");
    }

    @Test
    public void ensureServiceDoesNotRunToLong() {
        assertTimeout(Duration.ofSeconds(1),()->{
            dataService.update();
        });
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    @EnabledOnJre(JRE.JAVA_17)
    public void enableTestOnlyOnCertainPlatforms() {
        assertTrue(true);
    }
}