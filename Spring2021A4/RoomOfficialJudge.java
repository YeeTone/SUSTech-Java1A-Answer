package Spring2021A4;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class RoomOfficialJudge {

    @Test
    public void test_01 () {
        findRoom();
        findHasWhiteboard();
        findApplicants();
        findCapacity();
        findLocation();
        findRid();
        findHasDisplay();
        findConstructor(findRoom(), Modifier.PUBLIC, String.class);
        findConstructor(findRoom(), Modifier.PUBLIC, String.class, Library.class, int.class);
        findConstructor(findRoom(), Modifier.PUBLIC, String.class, Library.class, int.class, boolean.class, boolean.class);
        findToString1();
        findToString2();
        findSetApplicant();
        findRemoveApplicant();

        Room room1 = new Room("R101", Library.Yidan, 3, true, false);
        Room room2 = new Room("R101", Library.LearningNexus, 5, false, false);

        String an1 = "Yidan R101, capacity=3, hasDisplay=true, hasWhiteboard=false\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String an2 = "LearningNexus R101, capacity=5, hasDisplay=false, hasWhiteboard=false\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String an3 = "Yidan R101, capacity=3, hasDisplay=true, hasWhiteboard=false\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|11912345         |EMPTY   |11912346|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String an4 = "LearningNexus R101, capacity=5, hasDisplay=false, hasWhiteboard=false\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|11912345         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String an5 = "LearningNexus R101, capacity=5, hasDisplay=false, hasWhiteboard=false\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11912345|\n";
        String an6 = "LearningNexus R101, capacity=5, hasDisplay=false, hasWhiteboard=false\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11912346         |11912345|\n";

        assertEquals(an1, callToString1(room1));
        assertEquals(an2, callToString1(room2));
        assertTrue(callSetApplicant(room1, 8, 10, "11912345", 2));
        assertFalse(callSetApplicant(room1, 10, 12, "11912345", 3));
        assertFalse(callSetApplicant(room1, 12, 14, "11912345", 3));
        assertTrue(callSetApplicant(room1, 11, 12, "11912346", 2));
        assertEquals(an3, callToString1(room1));
        assertFalse(callSetApplicant(room2, 8, 10, "11912345", -1));
        assertTrue(callSetApplicant(room2, 8, 10, "11912345", 3));
        assertEquals(an4, callToString1(room2));
        assertFalse(callRemoveApplicant(room2, "11912346"));
        assertTrue(callRemoveApplicant(room2, "11912345"));
        assertEquals(an2, callToString1(room2));
        assertTrue(callSetApplicant(room2, 21, 22, "11912345", 2));
        assertEquals(an5, callToString1(room2));
        assertTrue(callSetApplicant(room2, 19, 21, "11912346", 2));
        assertEquals(an6, callToString1(room2));
    }

    @Test
    public void test_02 () {
        Room r1 = new Room("R101");
        String an1 = "Lynn R101, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String an2 = "Lynn R101, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|11900001|11900002|11900003|11900004|11900005|11900006|11900007|11900008|11900009|11900010         |11900011         |11900012|\n";
        String an3 = "Lynn R101, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|11900001|EMPTY   |11900003|11900004|11900005|11900006|11900007|11900008|11900009|11900010         |11900011         |11900012|\n";
        String an4 = "Lynn R101, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|11900001|EMPTY   |11900003|11900004|11900005|11900006|11900007|11900008|11900009|EMPTY   |EMPTY   |11900011         |11900012|\n";
        String an5 = "Lynn R101, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|11900001|EMPTY   |11900003|11900004|11900005|11900006|11900007|11900008|11900009|11900002|EMPTY   |11900011         |11900012|\n";
        String an6 = "Lynn R101, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|11900001|11900010         |11900004|11900005|11900006|11900007|11900008|11900009|11900002|EMPTY   |11900011         |11900012|\n";
        String an7 = "Lynn R101, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|11900001|11900010         |11900004|11900005|11900006|11900007|11900008|11900009|11900002|11900003|11900011         |11900012|\n";

        assertEquals(an1, callToString1(r1));
        assertTrue(callSetApplicant(r1, 8, 9, "11900001", 2));
        assertTrue(callSetApplicant(r1,9, 10, "11900002", 2));
        assertTrue(callSetApplicant(r1,10, 11, "11900003", 2));
        assertTrue(callSetApplicant(r1,11, 12, "11900004", 2));
        assertTrue(callSetApplicant(r1,12, 13, "11900005", 2));
        assertTrue(callSetApplicant(r1,13, 14, "11900006", 2));
        assertTrue(callSetApplicant(r1,14, 15, "11900007", 2));
        assertTrue(callSetApplicant(r1,15, 16, "11900008", 2));
        assertTrue(callSetApplicant(r1,16, 17, "11900009", 2));
        assertTrue(callSetApplicant(r1,17, 19, "11900010", 2));
        assertTrue(callSetApplicant(r1,19, 21, "11900011", 2));
        assertTrue(callSetApplicant(r1,21, 22, "11900012", 2));
        assertEquals(an2, callToString1(r1));

        assertFalse(callRemoveApplicant(r1, "11912013"));
        assertFalse(callRemoveApplicant(r1, "11900000"));
        assertTrue(callRemoveApplicant(r1, "11900002"));
        assertEquals(an3, callToString1(r1));

        assertFalse(callRemoveApplicant(r1, "11900002"));
        assertEquals(an3, callToString1(r1));
        assertTrue(callRemoveApplicant(r1, "11900010"));
        assertEquals(an4, callToString1(r1));
        assertFalse(callRemoveApplicant(r1, "11900010"));
        assertTrue(callSetApplicant(r1, 17, 18, "11900002", 2));
        assertEquals(an5, callToString1(r1));
        assertFalse(callSetApplicant(r1, 18, 19, "11900002", 0));
        assertTrue(callRemoveApplicant(r1, "11900003"));
        assertTrue(callSetApplicant(r1, 9, 11, "11900010", 2));
        assertFalse(callSetApplicant(r1, 18, 19, "11900003", -1));
        assertEquals(an6, callToString1(r1));
        assertTrue(callSetApplicant(r1, 18, 19, "11900003", 2));
        assertEquals(an7, callToString1(r1));
    }

    @Test
    public void test_03 () {
        Room r1 = new Room("R102", Library.LearningNexus, 10);
        Room r2 = new Room("R103", Library.Yidan, 12, true, false);
        Room r3 = new Room("R104", Library.Lynn, 14);
        String[] sid = new String[] {
                "11900001", "11900002", "11900003", "11900004", "11900005", "11900006", "11900007", "11900008", "11900009", "11900010",
                "11900011", "11900012", "11900013", "11900014", "11900015", "11900016", "11900017", "11900018", "11900019", "11900020"
        };
        String an1 = "LearningNexus R102, capacity=10, hasDisplay=true, hasWhiteboard=true\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|11900001|11900002|11900003|11900004|11900005|11900006|11900007|11900008|11900009|11900010|EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String an2 = "Yidan R103, capacity=12, hasDisplay=true, hasWhiteboard=false\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|11900001|11900002|11900003|11900004|11900005|11900006|11900007|11900008|11900009|11900010|11900011|11900012|EMPTY   |EMPTY   |\n";
        String an3 = "Lynn R104, capacity=14, hasDisplay=true, hasWhiteboard=true\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|11900001|11900002|11900003|11900004|11900005|11900006|11900007|11900008|11900009|11900010|11900011|11900012|11900013|11900014|\n";
        String an4 = "LearningNexus R102, capacity=10, hasDisplay=true, hasWhiteboard=true\n" +
                "|12:00   |\n" +
                "|11900005|\n";
        String an5 = "Yidan R103, capacity=12, hasDisplay=true, hasWhiteboard=false\n" +
                "|13:00   |\n" +
                "|11900006|\n";
        String an6 = "Lynn R104, capacity=14, hasDisplay=true, hasWhiteboard=true\n" +
                "|19:00   |20:00   |21:00   |\n" +
                "|11900012|11900013|11900014|\n";
        for (int i = 0; i < 14; i++) {
            if (i <= 9) assertTrue(callSetApplicant(r1, i+8, i+9, sid[i], i));
            else assertFalse(callSetApplicant(r1, i+8, i+9, sid[i], i));
        }
        for (int i = 0; i < 14; i++) {
            if (i <= 11) assertTrue(callSetApplicant(r2, i+8, i+9, sid[i], i));
            else assertFalse(callSetApplicant(r2, i+8, i+9, sid[i], i));
        }
        for (int i = 0; i < 14; i++) {
            assertTrue(callSetApplicant(r3, i+8, i+9, sid[i], i));
        }
        assertEquals(an1, callToString1(r1));
        assertEquals(an2, callToString1(r2));
        assertEquals(an3, callToString1(r3));
        assertEquals(an1, callToString2(r1, 8, 22));
        assertEquals(an2, callToString2(r2, 8, 22));
        assertEquals(an3, callToString2(r3, 8, 22));
        assertEquals(an4, callToString2(r1, 12, 13));
        assertEquals(an5, callToString2(r2, 13, 14));
        assertEquals(an6, callToString2(r3, 19, 22));
        assertNull(callToString2(r1, 1, 9));
        assertNull(callToString2(r3, 22, 23));
        assertNull(callToString2(r2, 9, 8));
        assertNull(callToString2(r2, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void test_04 () {
        Room room = new Room("R101");
        assertFalse(callSetApplicant(room, 0, 9, "11910000", 2));
        assertFalse(callSetApplicant(room, 7, 9, "11910000", 2));
        assertFalse(callSetApplicant(room, 8, 11, "11910000", 2));
        assertFalse(callSetApplicant(room, 21, 23, "11910000", 2));
        assertFalse(callSetApplicant(room, Integer.MIN_VALUE, Integer.MAX_VALUE + 1, "11910000", 2));
        assertFalse(callSetApplicant(room, 8, 10, "11910000", 3));
        assertFalse(callSetApplicant(room, 8, 10, "11910000", -1));
        assertFalse(callSetApplicant(room, 8, 10, "11910000", Integer.MAX_VALUE));
    }

    @Test
    public void test_05 () {
        Room room = new Room("R101", Library.LearningNexus, Integer.MAX_VALUE);
        String an1 = "LearningNexus R101, capacity=2147483647, hasDisplay=true, hasWhiteboard=true\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|11910000         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String an2 = "LearningNexus R101, capacity=2147483647, hasDisplay=true, hasWhiteboard=true\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String[] anList = new String[] {
                an1,
                "LearningNexus R101, capacity=2147483647, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |11910000         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n",
                "LearningNexus R101, capacity=2147483647, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |11910000         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n",
                "LearningNexus R101, capacity=2147483647, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |11910000         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n",
                "LearningNexus R101, capacity=2147483647, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |11910000         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n",
                "LearningNexus R101, capacity=2147483647, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11910000         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n",
                "LearningNexus R101, capacity=2147483647, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11910000         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n",
                "LearningNexus R101, capacity=2147483647, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11910000         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n",
                "LearningNexus R101, capacity=2147483647, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11910000         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n",
                "LearningNexus R101, capacity=2147483647, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11910000         |EMPTY   |EMPTY   |EMPTY   |\n",
                "LearningNexus R101, capacity=2147483647, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11910000         |EMPTY   |EMPTY   |\n",
                "LearningNexus R101, capacity=2147483647, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11910000         |EMPTY   |\n" ,
                "LearningNexus R101, capacity=2147483647, hasDisplay=true, hasWhiteboard=true\n" +
                        "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                        "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11910000         |\n"
        };
        assertTrue(callSetApplicant(room, 8, 10, "11910000", 0x6fff_ffff));
        assertEquals(an1, callToString1(room));
        assertEquals(an1, callToString2(room, 8, 22));
        assertFalse(callRemoveApplicant(room, null));
        assertFalse(callRemoveApplicant(room, ""));
        assertFalse(callRemoveApplicant(room, "1191000"));
        assertTrue(callRemoveApplicant(room, "11910000"));
        assertEquals(an2, callToString1(room));
        assertEquals(an2, callToString2(room, 8, 22));

        for (int i = 0; i < 13; i++) {
            assertTrue(callSetApplicant(room, i+8, i+10, "11910000", 0x6fff_ffff));
            assertEquals(anList[i], callToString1(room));
            assertEquals(anList[i], callToString2(room, 8, 22));
            assertTrue(callRemoveApplicant(room, "11910000"));
            assertEquals(an2, callToString1(room));
            assertEquals(an2, callToString2(room, 8, 22));
        }
    }

    private Class<?> findRoom() {
        try {
            return Class.forName("Spring2021A4.Room");
        } catch (ClassNotFoundException e) {
            fail("Cannot find class 'Room'. Please check the class name. Class 'Room' should not in a package");
            return null;
        }
    }
    private Field findRid () {
        return findField(findRoom(), "rid", String.class, Modifier.PRIVATE);
    }
    private Field findLocation () {
        return findField(findRoom(), "location", Library.class, Modifier.PRIVATE);
    }
    private Field findCapacity () {
        return findField(findRoom(), "capacity", int.class, Modifier.PRIVATE);
    }
    private Field findHasDisplay () {
        return findField(findRoom(), "hasDisplay", boolean.class, Modifier.PRIVATE);
    }
    private Field findHasWhiteboard () {
        return findField(findRoom(), "hasWhiteboard", boolean.class, Modifier.PRIVATE);
    }
    private Field findApplicants () {
        return findField(findRoom(), "applicants", String[].class, Modifier.PRIVATE);
    }

    private Method findToString1 () {
        return findMethod(findRoom(), "toString", Modifier.PUBLIC, String.class);
    }
    private Method findToString2 () {
        return findMethod(findRoom(), "toString", Modifier.PUBLIC, String.class, int.class, int.class);
    }
    private Method findSetApplicant () {
        return findMethod(findRoom(), "setApplicant", Modifier.PUBLIC, boolean.class, int.class, int.class,
                String.class, int.class);
    }
    private Method findRemoveApplicant () {
        return findMethod(findRoom(), "removeApplicant", Modifier.PUBLIC, boolean.class, String.class);
    }

    private String callToString1 (Object room) {
        try {
            return (String) findToString1().invoke(room);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }
    private String callToString2 (Object room, int start, int end) {
        try {
            return (String) findToString2().invoke(room, start, end);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }
    private Boolean callSetApplicant (Object room, int start, int end, String SID, int numberOfTeammates) {
        try {
            return (Boolean) findSetApplicant().invoke(room, start, end, SID, numberOfTeammates);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }
    private Boolean callRemoveApplicant (Object room, String SID) {
        try {
            return (Boolean) findRemoveApplicant().invoke(room, SID);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    private Field findField(Class<?> clazz, String name, Class<?> type, int modifier) {
        try {
            Field field = clazz.getDeclaredField(name);
            if (!field.getType().equals(type)) {
                fail(String.format(
                        "Attribute '%s' in class '%s' has wrong type, it should be '%s'",
                        name, clazz.getCanonicalName(), type.toGenericString()
                ));
            }
            if (field.getModifiers() != modifier) {
                fail(String.format(
                        "Attribute '%s' in class '%s' has wrong modifier, it should be '%s'",
                        name, clazz.getCanonicalName(), Modifier.toString(modifier)
                ));
            }
            return field;
        } catch (NoSuchFieldException e) {
            fail(String.format("Cannot find attribute '%s' in class '%s'", name, clazz.getCanonicalName()));
            return null;
        }
    }

    private Constructor<?> findConstructor(Class<?> clazz, int modifier, Class<?>... parameters) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(parameters);
            if (constructor.getModifiers() != modifier) {
                fail(String.format(
                        "Constructor '(%s)' in class '%s' has wrong modifier, it should be '%s'",
                        getParameterTypeString(parameters), clazz.getCanonicalName(), Modifier.toString(modifier)
                ));
            }
            return constructor;
        } catch (NoSuchMethodException e) {
            fail(String.format(
                    "Cannot find constructor '(%s)' in class '%s'",
                    getParameterTypeString(parameters), clazz.getCanonicalName()
            ));
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

