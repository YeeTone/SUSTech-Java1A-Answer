import org.junit.Rule;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.rules.Timeout;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class RoomManagerOfficialJudge {

    @Rule
    public Timeout timeout = new Timeout(1000);

    @Test
    public void test_01 () {
        findRoomManager();
        findSearchRoom1();
        findSearchRoom2();
        findSearchRoom3();
        findAddRoom1();
        findAddRoom2();
        findOrderRoom();
        findShowOrder();
        findCancelOrder1();
        findCancelOrder2();
    }

    @Test
    public void test_02_addRoom1(){
        //Parameter not valid
        assertFalse(callAddRoom1("R000", Library.Lynn, 3));
        assertFalse(callAddRoom1("R101", Library.Lynn, -1));
        assertFalse(callAddRoom1("101", Library.Lynn, 3));
        //Add room successfully to Library Lynn
        assertTrue(callAddRoom1("R101", Library.Lynn, 3));
        assertTrue(callAddRoom1("R102", Library.Lynn, 3));
        assertTrue(callAddRoom1("R300", Library.Lynn, 3));
        assertTrue(callAddRoom1("R999", Library.Lynn, 3));
        //Duplicate room id
        assertFalse(callAddRoom1("R101", Library.Lynn, 3));
        assertFalse(callAddRoom1("R102", Library.Lynn, 3));
        //Add room successfully to Library Yidan
        assertTrue(callAddRoom1("R101", Library.Yidan, 4));
        assertTrue(callAddRoom1("R202", Library.Yidan, 4));
        assertTrue(callAddRoom1("R303", Library.Yidan, 4));
        assertTrue(callAddRoom1("R404", Library.Yidan, 4));
        //Add room successfully to Learning Nexus
        assertTrue(callAddRoom1("R201", Library.LearningNexus, 5));
        assertTrue(callAddRoom1("R302", Library.LearningNexus, 5));
        assertTrue(callAddRoom1("R403", Library.LearningNexus, 5));
        assertTrue(callAddRoom1("R504", Library.LearningNexus, 5));
    }

    @Test
    public void test_03_addRoom2(){
        //Duplicate room id
        assertFalse(callAddRoom2("R000", Library.Lynn, 3, false, false));
        assertFalse(callAddRoom2("R101", Library.Lynn, -1, true, true));
        //Add room successfully to Library Lynn
        assertTrue(callAddRoom2("R201", Library.Lynn, 3, true, false));
        assertTrue(callAddRoom2("R202", Library.Lynn, 3, false, true));
        assertTrue(callAddRoom2("R303", Library.Lynn, 3, false, false));
        assertTrue(callAddRoom2("R888", Library.Lynn, 3, true, true));
        //Add room successfully to Library Yidan
        assertTrue(callAddRoom2("R505", Library.Yidan, 4, true, false));
        assertTrue(callAddRoom2("R606", Library.Yidan, 4, false, true));
        assertTrue(callAddRoom2("R707", Library.Yidan, 4, false, false));
        assertTrue(callAddRoom2("R808", Library.Yidan, 4, true, true));
        //Duplicate room id
        assertFalse(callAddRoom2("R202", Library.Yidan, 3, false, false));
        assertFalse(callAddRoom2("R606", Library.Yidan, 3, true, true));
        //Add room successfully to Learning Nexus
        assertTrue(callAddRoom2("R102", Library.LearningNexus, 5, true, false));
        assertTrue(callAddRoom2("R203", Library.LearningNexus, 5, false, true));
        assertTrue(callAddRoom2("R304", Library.LearningNexus, 5, false, false));
        assertTrue(callAddRoom2("R405", Library.LearningNexus, 5, true, true));
    }

    @Test
    public void test_04_orderRoom1(){
        //No room id in library
        assertFalse(callOrderRoom(Library.Lynn, "R404", "11811000", 8, 9, 0));
        assertFalse(callOrderRoom(Library.Yidan, "R909", "11811000", 8, 9, 0));
        assertFalse(callOrderRoom(Library.LearningNexus, "R606", "11811000", 8, 9, 0));
        //time not valid
        assertFalse(callOrderRoom(Library.Lynn, "R101", "11811000", 6, 7, 0));
        assertFalse(callOrderRoom(Library.Lynn, "R101", "11811000", 8, 8, 0));
        assertFalse(callOrderRoom(Library.Lynn, "R101", "11811000", 8, 11, 0));
        assertFalse(callOrderRoom(Library.Lynn, "R101", "11811000", 21, 23, 0));
        assertFalse(callOrderRoom(Library.Lynn, "R101", "11811000", 12, 11, 0));
        //number of participants overflow
        assertFalse(callOrderRoom(Library.Lynn, "R101", "11811000", 8, 9, 10));
        assertFalse(callOrderRoom(Library.Yidan, "R101", "11811000", 8, 9, 10));
        assertFalse(callOrderRoom(Library.LearningNexus, "R102", "11811000", 8, 9, 10));
        assertFalse(callOrderRoom(Library.Lynn, "R101", "11811000", 8, 9, 3));
        assertFalse(callOrderRoom(Library.Yidan, "R101", "11811000", 8, 9, 4));
        assertFalse(callOrderRoom(Library.LearningNexus, "R102", "11811000", 8, 9, 5));
    }

    @Test
    public void test_05_orderRoom2(){
        //order successfully in Library Lynn
        assertTrue(callOrderRoom(Library.Lynn, "R101", "11810001", 8, 9, 0));
        assertTrue(callOrderRoom(Library.Lynn, "R101", "11810002", 10, 12, 1));
        assertTrue(callOrderRoom(Library.Lynn, "R101", "11810003", 12, 13, 1));
        assertTrue(callOrderRoom(Library.Lynn, "R101", "11810004", 20, 22, 2));
        assertTrue(callOrderRoom(Library.Lynn, "R300", "11910001", 9, 11, 0));
        assertTrue(callOrderRoom(Library.Lynn, "R300", "11910002", 14, 16, 1));
        assertTrue(callOrderRoom(Library.Lynn, "R300", "11910003", 12, 13, 1));
        assertTrue(callOrderRoom(Library.Lynn, "R300", "11910004", 18, 20, 2));
        //order successfully in Library Yidan
        assertTrue(callOrderRoom(Library.Yidan, "R101", "11510001", 8, 9, 0));
        assertTrue(callOrderRoom(Library.Yidan, "R101", "11510002", 10, 12, 1));
        assertTrue(callOrderRoom(Library.Yidan, "R101", "11510003", 12, 13, 2));
        assertTrue(callOrderRoom(Library.Yidan, "R101", "11510004", 20, 22, 3));
        assertTrue(callOrderRoom(Library.Yidan, "R404", "11610001", 9, 11, 0));
        assertTrue(callOrderRoom(Library.Yidan, "R404", "11610002", 14, 16, 1));
        assertTrue(callOrderRoom(Library.Yidan, "R404", "11610003", 12, 13, 2));
        assertTrue(callOrderRoom(Library.Yidan, "R404", "11610004", 18, 20, 3));
        //order successfully in Learning Nexus
        assertTrue(callOrderRoom(Library.LearningNexus, "R102", "11510001", 8, 9, 1));
        assertTrue(callOrderRoom(Library.LearningNexus, "R102", "11510002", 10, 12, 2));
        assertTrue(callOrderRoom(Library.LearningNexus, "R102", "11510003", 12, 13, 3));
        assertTrue(callOrderRoom(Library.LearningNexus, "R102", "11510004", 20, 22, 4));
        assertTrue(callOrderRoom(Library.LearningNexus, "R203", "11810001", 9, 11, 1));
        assertTrue(callOrderRoom(Library.LearningNexus, "R203", "11810002", 14, 16, 2));
        assertTrue(callOrderRoom(Library.LearningNexus, "R203", "11810003", 12, 13, 3));
        assertTrue(callOrderRoom(Library.LearningNexus, "R203", "11810004", 18, 20, 4));
    }

    @Test
    public void test_06_orderRoom3(){
        // Each user can only order one room in Library Lynn
        assertFalse(callOrderRoom(Library.Lynn, "R101", "11810001", 9, 10, 0));
        assertFalse(callOrderRoom(Library.Lynn, "R102", "11810001", 14, 16, 1));
        assertFalse(callOrderRoom(Library.Lynn, "R201", "11810001", 8, 9, 1));
        assertFalse(callOrderRoom(Library.Lynn, "R202", "11810001", 20, 22, 2));
        // Each user can only order one room in Library Yidan
        assertFalse(callOrderRoom(Library.Yidan, "R101", "11610001", 9, 10, 0));
        assertFalse(callOrderRoom(Library.Yidan, "R202", "11610001", 14, 16, 1));
        assertFalse(callOrderRoom(Library.Yidan, "R303", "11610001", 12, 13, 2));
        assertFalse(callOrderRoom(Library.Yidan, "R404", "11610001", 8, 9, 3));
        // Each user can only order one room in Learning Nexus
        assertFalse(callOrderRoom(Library.Yidan, "R102", "11510001", 9, 10, 1));
        assertFalse(callOrderRoom(Library.Yidan, "R203", "11510001", 12, 13, 2));
        assertFalse(callOrderRoom(Library.Yidan, "R304", "11510001", 12, 13, 3));
        assertFalse(callOrderRoom(Library.Yidan, "R405", "11510001", 8, 9, 4));
    }

    @Test
    public void test_07_orderRoom4(){
        //Two orders for the same user can happen (Lynn)
        assertTrue(callOrderRoom(Library.Lynn, "R101", "11510001", 9, 10, 0));
        assertTrue(callOrderRoom(Library.Lynn, "R102", "11510002", 14, 16, 1));
        assertTrue(callOrderRoom(Library.Lynn, "R201", "11510003", 8, 9, 1));
        assertTrue(callOrderRoom(Library.Lynn, "R202", "11510004", 20, 22, 2));
        //Two orders for the same user can happen (Yidan)
        assertTrue(callOrderRoom(Library.Yidan, "R101", "11910001", 9, 10, 0));
        assertTrue(callOrderRoom(Library.Yidan, "R202", "11910002", 14, 16, 1));
        assertTrue(callOrderRoom(Library.Yidan, "R303", "11910003", 12, 13, 2));
        assertTrue(callOrderRoom(Library.Yidan, "R404", "11910004", 8, 9, 3));
        //Two orders for the same user can happen (Yidan)
        assertTrue(callOrderRoom(Library.LearningNexus, "R102", "11610001", 9, 10, 1));
        assertTrue(callOrderRoom(Library.LearningNexus, "R203", "11610002", 13, 14, 2));
        assertTrue(callOrderRoom(Library.LearningNexus, "R304", "11610003", 12, 13, 3));
        assertTrue(callOrderRoom(Library.LearningNexus, "R405", "11610004", 8, 9, 4));
    }

    @Test
    public void test_08_orderRoom5(){
        //order time not valid in Library Lynn
        assertFalse(callOrderRoom(Library.Lynn, "R101", "11610001", 8, 9, 0));
        assertFalse(callOrderRoom(Library.Lynn, "R101", "11610002", 10, 12, 1));
        assertFalse(callOrderRoom(Library.Lynn, "R101", "11610003", 12, 13, 1));
        assertFalse(callOrderRoom(Library.Lynn, "R101", "11610004", 20, 22, 2));
        assertFalse(callOrderRoom(Library.Lynn, "R300", "12010001", 10, 12, 0));
        assertFalse(callOrderRoom(Library.Lynn, "R300", "12010002", 14, 15, 1));
        assertFalse(callOrderRoom(Library.Lynn, "R300", "12010003", 12, 14, 1));
        assertFalse(callOrderRoom(Library.Lynn, "R300", "12010004", 19, 21, 2));
        //order time not valid in Library Yidan
        assertFalse(callOrderRoom(Library.Yidan, "R101", "11710001", 8, 9, 0));
        assertFalse(callOrderRoom(Library.Yidan, "R101", "11710002", 10, 12, 1));
        assertFalse(callOrderRoom(Library.Yidan, "R101", "11710003", 12, 13, 2));
        assertFalse(callOrderRoom(Library.Yidan, "R101", "11710004", 20, 22, 3));
        assertFalse(callOrderRoom(Library.Yidan, "R404", "11810001", 10, 12, 0));
        assertFalse(callOrderRoom(Library.Yidan, "R404", "11810002", 14, 15, 1));
        assertFalse(callOrderRoom(Library.Yidan, "R404", "11810003", 12, 14, 2));
        assertFalse(callOrderRoom(Library.Yidan, "R404", "11810004", 19, 21, 3));
        //order time not valid in Learning Nexus
        assertFalse(callOrderRoom(Library.LearningNexus, "R102", "11710001", 8, 9, 1));
        assertFalse(callOrderRoom(Library.LearningNexus, "R102", "11710002", 10, 12, 2));
        assertFalse(callOrderRoom(Library.LearningNexus, "R102", "11710003", 12, 13, 3));
        assertFalse(callOrderRoom(Library.LearningNexus, "R102", "11710004", 20, 22, 4));
        assertFalse(callOrderRoom(Library.LearningNexus, "R203", "11910001", 10, 12, 1));
        assertFalse(callOrderRoom(Library.LearningNexus, "R203", "11910002", 14, 15, 2));
        assertFalse(callOrderRoom(Library.LearningNexus, "R203", "11910003", 12, 14, 3));
        assertFalse(callOrderRoom(Library.LearningNexus, "R203", "11910004", 19, 21, 4));
    }

    @Test
    public void test_09_cancelOrder1(){
        //No order for SID
        assertFalse(callCancelOrder1("11510005"));
        assertFalse(callCancelOrder1("11610005"));
        assertFalse(callCancelOrder1("11810005"));
        assertFalse(callCancelOrder1("11910005"));
        //No order for SID in location
        assertFalse(callCancelOrder2("11610001", Library.Lynn));
        assertFalse(callCancelOrder2("11810001", Library.Yidan));
        assertFalse(callCancelOrder2("11910001", Library.LearningNexus));
    }

    @Test
    public void test_10_cancelOrder2(){
        //3.4.1 cancelOrder 1
        assertTrue(callCancelOrder1("11510003"));
        assertTrue(callCancelOrder1("11610003"));
        assertTrue(callCancelOrder1("11810003"));
        assertTrue(callCancelOrder1("11910003"));
        //3.4.2 cancelOrder 2
        assertTrue(callCancelOrder2("11510004", Library.Lynn));
        assertTrue(callCancelOrder2("11810004", Library.Lynn));
        assertTrue(callCancelOrder2("11910004", Library.Lynn));
        assertTrue(callCancelOrder2("11510004", Library.Yidan));
        assertTrue(callCancelOrder2("11610004", Library.Yidan));
        assertTrue(callCancelOrder2("11910004", Library.Yidan));
        assertTrue(callCancelOrder2("11510004", Library.LearningNexus));
        assertTrue(callCancelOrder2("11610004", Library.LearningNexus));
        assertTrue(callCancelOrder2("11810004", Library.LearningNexus));
    }

    @Test
    public void test_11_searchRoom1(){
        String ans1 =
                "Lynn R102, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11510002         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R888, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R999, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String ans2 =
                "Lynn R101, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|11810001|11510001|11810002         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R888, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R999, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String ans3 =
                "Lynn R102, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11510002         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R201, capacity=3, hasDisplay=true, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R888, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R999, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String ans4 =
                "Lynn R101, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|11810001|11510001|11810002         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R202, capacity=3, hasDisplay=false, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R888, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R999, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        assertEquals(ans1, callShowOrder(callSearchRoom1(Library.Lynn, 9, 10, true, true)));
        assertEquals(ans2, callShowOrder(callSearchRoom1(Library.Lynn, 14, 16, true, true)));
        assertEquals(ans3, callShowOrder(callSearchRoom1(Library.Lynn, 9, 10, true, false)));
        assertEquals(ans4, callShowOrder(callSearchRoom1(Library.Lynn, 14, 16, false, true)));

        String ans5 =
                "Yidan R202, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11910002         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R303, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R808, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String ans6 =
                "Yidan R101, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|11510001|11910001|11510002         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R303, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R808, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String ans7 =
                "Yidan R202, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11910002         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R303, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R505, capacity=4, hasDisplay=true, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R808, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String ans8 =
                "Yidan R101, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|11510001|11910001|11510002         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R303, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R606, capacity=4, hasDisplay=false, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R808, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        assertEquals(ans5, callShowOrder(callSearchRoom1(Library.Yidan, 9, 10, true, true)));
        assertEquals(ans6, callShowOrder(callSearchRoom1(Library.Yidan, 14, 16, true, true)));
        assertEquals(ans7, callShowOrder(callSearchRoom1(Library.Yidan, 9, 10, true, false)));
        assertEquals(ans8, callShowOrder(callSearchRoom1(Library.Yidan, 14, 16, false, true)));

        String ans9 =
                "LearningNexus R201, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R302, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R403, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R405, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R504, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String ansa =
                "LearningNexus R201, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R302, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R403, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R405, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R504, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String ansb =
                "LearningNexus R201, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R302, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R403, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R405, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R504, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String ansc =
                "LearningNexus R201, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R302, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R403, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R405, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R504, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        assertEquals(ans9, callShowOrder(callSearchRoom1(Library.LearningNexus, 9, 10, true, true)));
        assertEquals(ansa, callShowOrder(callSearchRoom1(Library.LearningNexus, 14, 16, true, true)));
        assertEquals(ansb, callShowOrder(callSearchRoom1(Library.LearningNexus, 9, 10, true, false)));
        assertEquals(ansc, callShowOrder(callSearchRoom1(Library.LearningNexus, 14, 16, false, true)));

        assertTrue(callOrderRoom(Library.Lynn, "R888", "11710001", 9, 10, 0));
        assertTrue(callOrderRoom(Library.Lynn, "R999", "11710002", 9, 10, 0));
        assertTrue(callOrderRoom(Library.Lynn, "R102", "11710003", 9, 10, 0));
        assertEquals("No room to show.", callShowOrder(callSearchRoom1(Library.Lynn, 9, 10, true, true)));
        assertTrue(callOrderRoom(Library.Yidan, "R101", "11710004", 15, 16, 0));
        assertTrue(callOrderRoom(Library.Yidan, "R303", "11710005", 15, 16, 0));
        assertTrue(callOrderRoom(Library.Yidan, "R808", "11710006", 15, 16, 0));
        assertEquals("No room to show.", callShowOrder(callSearchRoom1(Library.Yidan, 15, 16, true, true)));
    }

    @Test
    public void test_12_searchRoom2(){
        String ans1 =
                "Yidan R202, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11910002         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R303, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11710005|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R505, capacity=4, hasDisplay=true, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R606, capacity=4, hasDisplay=false, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R707, capacity=4, hasDisplay=false, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R808, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11710006|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R201, capacity=3, hasDisplay=true, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R202, capacity=3, hasDisplay=false, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R303, capacity=3, hasDisplay=false, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R201, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R302, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R304, capacity=5, hasDisplay=false, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R403, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R405, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R504, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        assertEquals(ans1, callShowOrder(callSearchRoom2(8, 10)));
        String ans2 =
                "Yidan R505, capacity=4, hasDisplay=true, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R606, capacity=4, hasDisplay=false, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R707, capacity=4, hasDisplay=false, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R101, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|11810001|11510001|11810002         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R201, capacity=3, hasDisplay=true, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R202, capacity=3, hasDisplay=false, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R303, capacity=3, hasDisplay=false, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R888, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |11710001|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R999, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |11710002|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R102, capacity=5, hasDisplay=true, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|11510001|11610001|11510002         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R201, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R302, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R304, capacity=5, hasDisplay=false, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R403, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R405, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R504, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        assertEquals(ans2, callShowOrder(callSearchRoom2(15, 16)));
    }

    @Test
    public void test_13_searchRoom3(){
        String ans1 =
                "LearningNexus R201, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R302, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R304, capacity=5, hasDisplay=false, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R403, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R405, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R504, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R202, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11910002         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R303, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11710005|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R505, capacity=4, hasDisplay=true, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R606, capacity=4, hasDisplay=false, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R707, capacity=4, hasDisplay=false, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R808, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11710006|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R201, capacity=3, hasDisplay=true, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R202, capacity=3, hasDisplay=false, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R303, capacity=3, hasDisplay=false, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        assertEquals(ans1, callShowOrder(callSearchRoom3(9, 10, Landmark.Dormitory)));
        String ans2 =
                "LearningNexus R102, capacity=5, hasDisplay=true, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|11510001|11610001|11510002         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R201, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R302, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R304, capacity=5, hasDisplay=false, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R403, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R405, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R504, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R505, capacity=4, hasDisplay=true, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R606, capacity=4, hasDisplay=false, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R707, capacity=4, hasDisplay=false, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R101, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|11810001|11510001|11810002         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R201, capacity=3, hasDisplay=true, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R202, capacity=3, hasDisplay=false, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R303, capacity=3, hasDisplay=false, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R888, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |11710001|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R999, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |11710002|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        assertEquals(ans2, callShowOrder(callSearchRoom3(15, 16, Landmark.Dormitory)));
        String ans3 =
                "Yidan R202, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11910002         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R303, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11710005|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R505, capacity=4, hasDisplay=true, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R606, capacity=4, hasDisplay=false, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R707, capacity=4, hasDisplay=false, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R808, capacity=4, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11710006|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R201, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R302, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R304, capacity=5, hasDisplay=false, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R403, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R405, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R504, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R201, capacity=3, hasDisplay=true, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R202, capacity=3, hasDisplay=false, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R303, capacity=3, hasDisplay=false, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        assertEquals(ans3, callShowOrder(callSearchRoom3(9, 10, Landmark.Playground)));
        String ans4 =
                "Yidan R505, capacity=4, hasDisplay=true, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R606, capacity=4, hasDisplay=false, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Yidan R707, capacity=4, hasDisplay=false, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R102, capacity=5, hasDisplay=true, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|11510001|11610001|11510002         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R201, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R302, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R304, capacity=5, hasDisplay=false, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R403, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R405, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "LearningNexus R504, capacity=5, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R101, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|11810001|11510001|11810002         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R201, capacity=3, hasDisplay=true, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R202, capacity=3, hasDisplay=false, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R303, capacity=3, hasDisplay=false, hasWhiteboard=false\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R888, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |11710001|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n" +
                        "Lynn R999, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |11710002|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        assertEquals(ans4, callShowOrder(callSearchRoom3(15, 16, Landmark.Playground)));
    }

    private Method findAddRoom1 () {
        return findMethod(findRoomManager(), "addRoom", Modifier.PUBLIC | Modifier.STATIC, boolean.class,
                String.class, Library.class, int.class);
    }
    private Method findAddRoom2 () {
        return findMethod(findRoomManager(), "addRoom", Modifier.PUBLIC | Modifier.STATIC, boolean.class,
                String.class, Library.class, int.class, boolean.class, boolean.class);
    }
    private Method findOrderRoom () {
        return findMethod(findRoomManager(), "orderRoom", Modifier.PUBLIC | Modifier.STATIC, boolean.class,
                Library.class, String.class, String.class, int.class, int.class, int.class);
    }
    private Method findCancelOrder1 () {
        return findMethod(findRoomManager(), "cancelOrder", Modifier.PUBLIC | Modifier.STATIC,
                boolean.class, String.class);
    }
    private Method findCancelOrder2 () {
        return findMethod(findRoomManager(), "cancelOrder", Modifier.PUBLIC | Modifier.STATIC,
                boolean.class, String.class, Library.class);
    }
    private Method findSearchRoom1 () {
        return findMethod(findRoomManager(), "searchRoom", Modifier.PUBLIC | Modifier.STATIC, ArrayList.class,
                Library.class, int.class, int.class, boolean.class, boolean.class);
    }
    private Method findSearchRoom2 () {
        return findMethod(findRoomManager(), "searchRoom", Modifier.PUBLIC | Modifier.STATIC, ArrayList.class,
                int.class, int.class);
    }
    private Method findSearchRoom3 () {
        return findMethod(findRoomManager(), "searchRoom", Modifier.PUBLIC | Modifier.STATIC, ArrayList.class,
                int.class, int.class, Landmark.class);
    }
    private Method findShowOrder () {
        return findMethod(findRoomManager(), "showOrder", Modifier.PUBLIC | Modifier.STATIC,
                String.class, ArrayList.class);
    }

    private Boolean callAddRoom1 (String rid, Library location, int capacity){
        try {
            return (Boolean) findAddRoom1().invoke(null, rid, location, capacity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    private Boolean callAddRoom2 (String rid, Library location, int capacity, boolean hasDisplay, boolean hasWhiteboard){
        try {
            return (Boolean) findAddRoom2().invoke(null, rid, location, capacity, hasDisplay, hasWhiteboard);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    private Boolean callOrderRoom (Library library, String rid, String SID, int start, int end, int numberOfTeammates){
        try {
            return (Boolean) findOrderRoom().invoke(null, library, rid, SID, start, end, numberOfTeammates);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    private Boolean callCancelOrder1 (String SID){
        try {
            return (Boolean) findCancelOrder1().invoke(null, SID);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    private Boolean callCancelOrder2 (String SID, Library location){
        try {
            return (Boolean) findCancelOrder2().invoke(null, SID, location);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    private ArrayList<Room> callSearchRoom1 (Library location, int start, int end, boolean needDisplay, boolean needWhiteboard){
        try {
            return (ArrayList<Room>) findSearchRoom1().invoke(null, location, start, end, needDisplay, needWhiteboard);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    private ArrayList<Room> callSearchRoom2 (int start, int end){
        try {
            return (ArrayList<Room>) findSearchRoom2().invoke(null, start, end);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    private ArrayList<Room> callSearchRoom3 (int start, int end, Landmark landmark){
        try {
            return (ArrayList<Room>) findSearchRoom3().invoke(null, start, end, landmark);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    private String callShowOrder (ArrayList<Room> list){
        try {
            return (String) findShowOrder().invoke(null, list);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    private Class<?> findRoomManager () {
        try {
            return Class.forName("RoomManager");
        } catch (ClassNotFoundException e) {
            fail("Cannot find class 'RoomManager'. Please check the class name. Class 'RoomManager' should not in a package");
            return null;
        }
    }

    private Method findMethod(Class<?> clazz, String name, int modifier, Class<?> returnType, Class<?>... parameters) {
        try {
            Method method = clazz.getDeclaredMethod(name, parameters);
            if (method.getReturnType() != returnType) {
                fail(String.format(
                        "Method '%s(%s)' in class '%s' has wrong return type, it should be '%s'",
                        name, getParameterTypeString(parameters), clazz.getCanonicalName(), returnType.toGenericString()
                ));
            }
            if (method.getModifiers() != modifier) {
                fail(String.format(
                        "Method '%s(%s)' in class '%s' has wrong modifier, it should be '%s'",
                        name, getParameterTypeString(parameters), clazz.getCanonicalName(), Modifier.toString(modifier)
                ));
            }
            return method;
        } catch (NoSuchMethodException e) {
            fail(String.format(
                    "Cannot find method '%s(%s)' in class '%s'",
                    name, getParameterTypeString(parameters), clazz.getCanonicalName()
            ));
            return null;
        }
    }

    private String getParameterTypeString(Class<?>[] parameters) {
        return Arrays.stream(parameters)
                .map(Class::getCanonicalName)
                .collect(Collectors.joining(", "));
    }
}
