import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class UnlimitedTest {
    SustechAirline sustechAirline;
    Search unlimitedSearch;

    @BeforeEach
    public void createResource() {
        sustechAirline = new SustechAirline();
        unlimitedSearch = new UnlimitedSearch();
    }

    @Test
//    @Name("Q1 Test Search all 1")
    public void q1_unlimitedTest1() {

        sustechAirline.addFlight("S101 A B 3:20 6:30 900");
        sustechAirline.addFlight("S102 A C 2:20 5:30 800");
        sustechAirline.addFlight("S103 A D 3:20 6:30 900");
        sustechAirline.addFlight("S104 A E 3:20 6:30 900");
        sustechAirline.addFlight("S105 B E 7:20 9:30 900");
        sustechAirline.addFlight("S106 C E 6:20 6:50 900");

        List<String> answers = new ArrayList<>();
        answers.add("S101 [A -> B] 03:20 -> 06:30 (900)\tS105 [B -> E] 07:20 -> 09:30 (900)");
        answers.add("S102 [A -> C] 02:20 -> 05:30 (800)\tS106 [C -> E] 06:20 -> 06:50 (900)");
        answers.add("S104 [A -> E] 03:20 -> 06:30 (900)");
        List<String> actual = sustechAirline.searchAllRoutes("A", "E", unlimitedSearch).stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < answers.size(); i++) {
            assertEquals(answers.get(i),actual.get(i).trim());
        }
    }


    @Test
//    @Name("Q2 Test Search all 2")
    public void q2_unlimitedTest1() {

        sustechAirline.addFlight("S101 A B 3:20 6:30 900");
        sustechAirline.addFlight("S102 B C 7:20 9:30 800");
        sustechAirline.addFlight("S103 C D 10:20 11:30 900");
        sustechAirline.addFlight("S104 D E 12:20 15:30 900");

        List<String> answers = new ArrayList<>();
        answers.add("S101 [A -> B] 03:20 -> 06:30 (900)\tS102 [B -> C] 07:20 -> 09:30 (800)\tS103 [C -> D] 10:20 -> 11:30 (900)\tS104 [D -> E] 12:20 -> 15:30 (900)");

        List<String> actual = sustechAirline.searchAllRoutes("A", "E", unlimitedSearch).stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < answers.size(); i++) {
            assertEquals(answers.get(i),actual.get(i).trim());
        }
    }

    @Test
//    @Name("Q3 Test Search all Time Conflict")
    public void q3_unlimitedTest2() {

        sustechAirline.addFlight("S101 A B 3:20 6:30 900");
        sustechAirline.addFlight("S102 A D 3:20 6:30 900");
        sustechAirline.addFlight("S103 A E 3:20 6:30 900");
        sustechAirline.addFlight("S104 A C 2:20 5:30 800");
        sustechAirline.addFlight("S105 C D 6:01 8:30 900");
        sustechAirline.addFlight("S106 D E 9:01 10:30 900");
        sustechAirline.addFlight("S107 A F 5:00 7:30 900");
        sustechAirline.addFlight("S108 F H 5:30 10:30 900");
        sustechAirline.addFlight("S109 H E 11:01 12:30 900");

        List<String> answers = new ArrayList<>();
        answers.add("S102 [A -> D] 03:20 -> 06:30 (900)\tS106 [D -> E] 09:01 -> 10:30 (900)");
        answers.add("S103 [A -> E] 03:20 -> 06:30 (900)");
        answers.add("S104 [A -> C] 02:20 -> 05:30 (800)\tS105 [C -> D] 06:01 -> 08:30 (900)\tS106 [D -> E] 09:01 -> 10:30 (900)");

        List<String> actual = sustechAirline.searchAllRoutes("A", "E", unlimitedSearch).stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < answers.size(); i++) {
            assertEquals(answers.get(i),actual.get(i).trim());
        }
    }

    @Test
//    @Name("Q4 Test Search All has Circle")
    public void q4_unlimitedTest2() {

        sustechAirline.addFlight("S101 A B 3:20 6:30 900");
        sustechAirline.addFlight("S102 B E 9:20 10:30 900");
        sustechAirline.addFlight("S103 E B 11:20 15:30 900");
        sustechAirline.addFlight("S104 A C 7:20 10:35 800");
        sustechAirline.addFlight("S105 C A 11:20 14:30 900");
        sustechAirline.addFlight("S106 C E 11:20 15:30 900");
        sustechAirline.addFlight("S107 A D 5:00 7:30 900");
        sustechAirline.addFlight("S108 D F 8:30 10:30 900");
        sustechAirline.addFlight("S109 F A 11:01 12:30 900");
        sustechAirline.addFlight("S110 F E 11:01 12:30 900");

        List<String> answers = new ArrayList<>();
        answers.add("S101 [A -> B] 03:20 -> 06:30 (900)\tS102 [B -> E] 09:20 -> 10:30 (900)");
        answers.add("S104 [A -> C] 07:20 -> 10:35 (800)\tS106 [C -> E] 11:20 -> 15:30 (900)");
        answers.add("S107 [A -> D] 05:00 -> 07:30 (900)\tS108 [D -> F] 08:30 -> 10:30 (900)\tS110 [F -> E] 11:01 -> 12:30 (900)");

        List<String> actual = sustechAirline.searchAllRoutes("A", "E", unlimitedSearch).stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < answers.size(); i++) {
            assertEquals(answers.get(i),actual.get(i).trim());
        }
    }

    @Test
//    @Name("Q5 Test Search Best for Least Time")
    public void q5_unlimitedTest3() {

        sustechAirline.addFlight("S101 A B 2:00 3:30 900");
        sustechAirline.addFlight("S102 B E 4:01 5:00 900");
        sustechAirline.addFlight("S103 A E 3:00 5:30 900");
        sustechAirline.addFlight("S104 A F 7:20 8:35 800");

        assertEquals("S103 [A -> E] 03:00 -> 05:30 (900)", sustechAirline.searchBestRoute("A", "E", unlimitedSearch, SearchPlan.LESS_TIME).trim());
    }

    @Test
//    @Name("Q6 Test Search Best for Least Price")
    public void q6_unlimitedTest3() {

        sustechAirline.addFlight("S101 A B 2:00 3:30 100");
        sustechAirline.addFlight("S102 B E 4:01 5:00 100");
        sustechAirline.addFlight("S103 A E 3:00 5:30 270");

        assertEquals("S103 [A -> E] 03:00 -> 05:30 (270)", sustechAirline.searchBestRoute("A", "E", unlimitedSearch, SearchPlan.LESS_PRICE).trim());
    }

    @Test
//    @Name("Q7 Test Search Best for Least Price")
    public void q7_unlimitedTest3() {

        sustechAirline.addFlight("S101 A B 2:00 3:30 200");
        sustechAirline.addFlight("S102 B E 4:01 5:00 200");
        sustechAirline.addFlight("S103 A E 3:00 5:30 490");

        assertEquals("S101 [A -> B] 02:00 -> 03:30 (200)\tS102 [B -> E] 04:01 -> 05:00 (200)", sustechAirline.searchBestRoute("A", "E", unlimitedSearch, SearchPlan.LESS_PRICE).trim());
    }



}