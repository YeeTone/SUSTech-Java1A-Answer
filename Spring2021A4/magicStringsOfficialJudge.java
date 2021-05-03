import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class magicStringsOfficialJudge {
    private static final int n = 26;
    @Test
    public void test_01() {
        // Check the clazz
        findMagicStrings();
        // Check the attributes
        findPriority();
        findSs();
        // Check the Constructors
        findConstructor(findMagicStrings(), Modifier.PUBLIC, String.class);
        findConstructor(findMagicStrings(), Modifier.PUBLIC, String.class, String.class);
        findConstructor(findMagicStrings(), Modifier.PUBLIC, int[].class, String.class);
        // Check the Methods
        findSetPriority1();
        findSetPriority2();
        findSetPriority3();
        findSortSs();
        findCompareString();
        findGetStrings();
        findSetSs();
        findStringNum();
    }

    @Test
    public void test_02 () {
        // Test the constructors and the method of compareString (Completely random cases)
        String[] list = new String[] {
                "flnbydlwspydttzrencmichonubzxohcknrhxgecbyzvnayjnqzrkubtcgsqdjyopqgidlekpwdqthcqavjcnbqhqynvdfrtgpkq" +
                        "qoqdvvvskkcgqhpmcqspxnogwruwewynskvrvmzeojvczxlalddfeqwwnplkbpwutzlooqniyjnjcabffkwvddogzgtgkosfpvim",
                "werqeaqttoepolwsdxoohkiqwlogzylbdkketayoeexobsngahgrphfmdjysnmwcrhrbwlikinfenjhhqsdfgjiqoeeijmtswbva" +
                        "qzxehvuebnferpizylvpmnammmtniesawhhewjiuoajhiohayjkwthevsgrrpggjllightuireygtgxwwxjmbsauwmbwqhkaitox",
                "snbrfisdzqduvztzctbcwelobgfggbwtdpxcnjpsyxtlzdzdgcxahoiqlnilsrpidnekcdkooyrubywuowzyrtgsyztrsmjomcde" +
                        "hbfcfvmwphvdddgszrnvwbgjcueepujmltbcanxzgiuqrnebossviccdrffdaggmivjkzfodtyxjbrrcxdyiwnkbfldcnrxpzeva",
                "rwkxnshhejiqjptxdgqfygirqgzyiuzmlfuovusoazuqnsziwwafdugdkwsmcshplymxujsgmwlkirvhpilpagptjbygphugreir" +
                        "zsyawhgnfkrycgyiucibqkccknnvtfnmklombcomviyexdvriqovsybdkuuudrwcrgskhnzlkxkrqptwnchenmrycdhtnsdoxhah",
                "lkhztmljzftggypdzltxtolingaqwwqthghlimmbipxlmbdnmdmjgniuzceyegeezswliforvvjawbwbtlexsoifpsaecbkmruoh" +
                        "vlhvcsmozsiadulvsrodovptwttujssaayrtysbpqwbhlrwqjizbrgxwrekghxjydmbeokmcupneyvensqlrqrweoesoxhzdpirx",
                "ccuqwkrruflaqdcdwpdhycghwjuhawucxvqshdavbzmykjxauwxyeottownixeebvioxavkgjoqoxcsyyptyrojsoszdncjgotor" +
                        "rpjxnvzivacbbqvjlcnlwwqqlxsllfrytscattjdfeashhnkuubmkxnnkzbcajixqxbjstdqzyzdmbuwwjjtdomnlknymajleigv",
                "epwfdygwgadzbzudrmdfzidahvjbuiwyisijmykgjcyaseeybkkxwrxkhuseyvwsenvnckmdpghcumwgolhklozwguaqatwpcpgc" +
                        "xqfzubvtcljnovmtewxcghvgjrexylyagpgtcxjaacxwttbtslwtjbtzohjlwtsxadxvlsxghjodpmeixexzjuyrqpbcoosfkqht",
                "vuqsvjccgmxlemycpakyqbjomaeftxbpisnxjtegsmrppxznrcnnthgxophfopazcpujfgilzekcnoiuuqvsatafcfmbsczsevpi" +
                        "sdckjczxivsdsrtjbbngshicbpbvgspjhoylcnxfrviudcpulcphoxvxusdwtnlvzeyivmkqpzgasqudsabqgnvxvtghzgvnjtcv",
                "ljwdtqvvrunkdvjhaolrdldkrxajsjpwcpsnyivtpucttxzzfhjvyomtzdounjduddlqnikppllogddufuwohngddaqtlxuticxb" +
                        "xexvlihjdftmvfzvmhnrprlkjkxcixrxxcuwhtxuwcvcguosoxvxtslugdwndfkvcimqjzttfnavsembpabdnalmpmvmsiyvzsnt",
                "ngoxnvgomczvuwvxxajzvryyrdzsfvnfbfxcdnhjlaywmganrgtzznbvoucpcrjcbuqoalxhxunugcxsrrunhkmvlvkaduynnfku" +
                        "dkheoctdjuoqxnjzomogqaleusibnljrloyvknzbsivbzsoapdxvppgmfznaugdphnqqnkzseskuskutbjbswiwufeuhfludksaf"
        };
        String[] priority1 = new String[] {
                "",
                "25 5 4 7 8 20 3 10 15 11 12 21 14 9 23 13 24 16 6 26 19 18 2 1 17 22",
                "25 13 1 9 12 15 7 14 22 18 21 6 20 24 11 16 3 19 5 8 17 23 2 26 4 10",
                "1 7 18 2 22 14 5 11 20 19 10 15 21 17 12 25 23 13 4 6 8 16 26 3 9 24",
                "14 18 26 25 15 21 19 1 20 4 16 13 6 7 11 9 5 24 17 8 3 23 2 22 12 10",
                "6 22 23 4 3 15 25 13 7 18 11 2 17 21 10 20 9 12 8 14 5 26 1 19 16 24",
                "21 24 14 25 9 1 18 13 16 23 10 20 8 26 12 19 4 15 3 22 11 17 7 5 6 2",
                "18 17 21 13 15 3 12 16 10 24 2 14 11 22 6 25 19 9 20 1 26 8 4 5 7 23",
                "14 1 4 23 8 6 19 25 17 15 21 3 10 5 20 22 26 13 2 12 18 24 9 11 7 16",
                "8 18 13 1 20 25 24 14 9 7 3 6 23 21 2 17 15 4 22 10 5 19 12 16 26 11"
        };
        int[][] priority2 = new int[][]{
                {},
                {25, 5, 4, 7, 8, 20, 3, 10, 15, 11, 12, 21, 14, 9, 23, 13, 24, 16, 6, 26, 19, 18, 2, 1, 17, 22},
                {25, 13, 1, 9, 12, 15, 7, 14, 22, 18, 21, 6, 20, 24, 11, 16, 3, 19, 5, 8, 17, 23, 2, 26, 4, 10},
                {1, 7, 18, 2, 22, 14, 5, 11, 20, 19, 10, 15, 21, 17, 12, 25, 23, 13, 4, 6, 8, 16, 26, 3, 9, 24},
                {14, 18, 26, 25, 15, 21, 19, 1, 20, 4, 16, 13, 6, 7, 11, 9, 5, 24, 17, 8, 3, 23, 2, 22, 12, 10},
                {6, 22, 23, 4, 3, 15, 25, 13, 7, 18, 11, 2, 17, 21, 10, 20, 9, 12, 8, 14, 5, 26, 1, 19, 16, 24},
                {21, 24, 14, 25, 9, 1, 18, 13, 16, 23, 10, 20, 8, 26, 12, 19, 4, 15, 3, 22, 11, 17, 7, 5, 6, 2},
                {18, 17, 21, 13, 15, 3, 12, 16, 10, 24, 2, 14, 11, 22, 6, 25, 19, 9, 20, 1, 26, 8, 4, 5, 7, 23},
                {14, 1, 4, 23, 8, 6, 19, 25, 17, 15, 21, 3, 10, 5, 20, 22, 26, 13, 2, 12, 18, 24, 9, 11, 7, 16},
                {8, 18, 13, 1, 20, 25, 24, 14, 9, 7, 3, 6, 23, 21, 2, 17, 15, 4, 22, 10, 5, 19, 12, 16, 26, 11}
        };
        int[][] answer = new int[][]{
                {-1, 1, 1, -1, -1, -1, -1, 1, -1, 1},
                {-1, -1, -1, -1, 1, -1, 1, 1, 1, 1},
                {1, -1, -1, 1, -1, -1, -1, 1, -1, 1},
                {-1, 1, -1, -1, -1, 1, 1, 1, 1, 1},
                {1, -1, 1, 1, -1, 1, -1, 1, -1, -1},
                {1, -1, -1, -1, -1, 1, -1, 1, -1, 1},
                {-1, 1, -1, 1, 1, -1, 1, 1, 1, 1},
                {-1, -1, 1, -1, 1, 1, 1, -1, 1, 1},
                {-1, -1, -1, -1, -1, -1, -1, 1, -1, -1},
                {1, -1, 1, -1, -1, 1, 1, -1, -1, 1}
        };
        MagicStrings magicStrings = new MagicStrings("");
        for (int i = 0; i < 10; i++) {
            assertEquals(answer[0][i],
                    callCompareString(magicStrings, list[i].substring(0, 100), list[i].substring(100, 200)));
        }
        for (int i = 1; i < 10; i++) {
            magicStrings = new MagicStrings(priority1[i], "");
            for (int j = 0; j < 10; j++) {
                assertEquals(answer[i][j],
                        callCompareString(magicStrings, list[j].substring(0, 100), list[j].substring(100, 200)));
            }
        }
        for (int i = 1; i < 10; i++) {
            magicStrings = new MagicStrings(priority2[i], "");
            for (int j = 0; j < 10; j++) {
                assertEquals(answer[i][j],
                        callCompareString(magicStrings, list[j].substring(0, 100), list[j].substring(100, 200)));
            }
        }
        // Reverse test
        magicStrings = new MagicStrings("");
        for (int i = 0; i < 10; i++) {
            assertEquals(-1 * answer[0][i],
                    callCompareString(magicStrings, list[i].substring(100, 200), list[i].substring(0, 100)));
        }
        for (int i = 1; i < 10; i++) {
            magicStrings = new MagicStrings(priority1[i], "");
            for (int j = 0; j < 10; j++) {
                assertEquals(-1 * answer[i][j],
                        callCompareString(magicStrings, list[j].substring(100, 200), list[j].substring(0, 100)));
            }
        }
        for (int i = 1; i < 10; i++) {
            magicStrings = new MagicStrings(priority2[i], "");
            for (int j = 0; j < 10; j++) {
                assertEquals(-1 * answer[i][j],
                        callCompareString(magicStrings, list[j].substring(100, 200), list[j].substring(0, 100)));
            }
        }
    }

    @Test
    public void test_03 () {
        // Test the method of compareString (Some special cases)
        String[][] list = new String[][]{
                {"a", "a"},
                {"aaa", "aa"},
                {"a", "b"},
                {"aaaaaba", "aaaaazb"},
                {"ngoxnvgomczvuwvxxajzvryyrdzsfvnfbfxcdnhjlaywmganrgtzznbvoucpcrjcbuqoalxhxunugcxsrrunhkmvlvkaduynnfkudkheoctdjuoqxnjzomogqaleusibnljrloyvknzbsivbzsoapdxvppgmfznaugdphnqqnkzseskuskutbjbswiwufeuhfludksaf",
                        "ngoxnvgomczvuwvxxajzvryyrdzsfvnfbfxcdnhjlaywmganrgtzznbvoucpcrjcbuqoalxhxunugcxsrrunhkmvlvkaduynnfkudkheoctdjuoqxnjzomogqaleusibnljrloyvknzbsivbzsoapdxvppgmfznaugdphnqqnkzseskuskutbjbswiwufeuhfludksaf"},
                {"aaaaaaaaaaa", "aaaaaaaaaaa"}
        };
        int[] an = new int[]{0, 1, -1, -1, 0, 0};
        MagicStrings magicStrings = new MagicStrings("");
        for (int i = 0; i < 6; i++) {
            assertEquals(an[i], callCompareString(magicStrings, list[i][0], list[i][1]));
        }
        // Reverse test
        for (int i = 0; i < 6; i++) {
            assertEquals(-1 * an[i], callCompareString(magicStrings, list[i][1], list[i][0]));
        }
    }

//    @Test
//    public void test_04 () {
//        // Some magic bound
//        MagicStrings magicStrings = new MagicStrings(null);
//        assertNull(callGetStrings(magicStrings));
//        assertEquals(0, callStringNum(magicStrings));
//        callSetSs(magicStrings, "");
//        assertNull(callGetStrings(magicStrings));
//        assertEquals(0, callStringNum(magicStrings));
//    } test_04 has been removed.

    @Test
    public void test_05 () {
        // Test the ways of setPriority and the method compareString (The case is part of random, first 50% chars are same)
        MagicStrings magicStrings = new MagicStrings("");
        String[] list = new String[]{
                "rulsglyzrbenejredlextaaiiedyhvednqthvcbomwnqelpsjjhfomomksepmdzkavhbebsktxeomsxdzrxippvqndzflltanlbz" +
                "rulsglyzrbenejredlextaaiiedyhvednqthvcbomwnqelpsjjjmascwrxbzwagxasefgrennkqyqjaoomdmhvvoqsjfpjhvhlmy",
                "ihrkwsrwuzrxnxbplpkevfiputddfykbhqxasumyocvwgvpqofqykcbenzxruhwxrxfaovrphiootcylywlnrwpaplwucwjubqsc" +
                "ihrkwsrwuzrxnxbplpkevfiputddfykbhqxasumyocvwgvpqofoxkyipificyrfhhfwrayluifoixjsdrvquokzvwfwkyjcfrnyr",
                "qqilktwlrnpmbspibhyankcuklewggoeraqqkknhhnwvzvejowlwudwxxubstrldkutuvxyhkaxwwshsvpwvimybfvvkysyotasy" +
                "qqilktwlrnpmbspibhyankcuklewggoeraqqkknhhnwvzvejowlespgvjfdmcytmzpouhemnzfwamxabuglvkdlkcavrchavfpzx",
                "hdixgsizrhmjdlzepehwbvpvsdjkiuybyvhvfqtrzkvvkgvlueoqnvgoegrmlmgpjnjuqzaspkmebqeuyhezcpnwwumdpqlvrcgy" +
                "hdixgsizrhmjdlzepehwbvpvsdjkiuybyvhvfqtrzkvvkgvluezsuewutgaixggcvmdzcyzcgsgmafofitdkvkqoawpgrjujrfsm",
                "aanyfwprprmjlbhbjmtabsygrjpgwpekpfoscqugdvoifkrzwgmzkefjaekfudfgkqfjyhvocrayuhzzsqxnpprizkwtaeoztelx" +
                "aanyfwprprmjlbhbjmtabsygrjpgwpekpfoscqugdvoifkrzwgmtoanlpklaofmiukssjqyuiawvwmfsayvxhizyibqohxlfmobj",
                "qbpylipmdtzwobnmoipenfoqtfexgwsrmyxguepsgnelrooewpczalrxijbwejqnpihwukzsfttvqdaqwsrqzcqqkteeoghewpgc" +
                "qbpylipmdtzwobnmoipenfoqtfexgwsrmyxguepsgnelrooewpkapfqxdcarkingxenpfnuxadlhspkgufkrmkksjxlcuxnehfqm",
                "dhljldzinbneitbbpiitysruntegmeulnfqrodbbbixcaijqqpfytmdchuntttfovgfhanjtatlsseazvmfazquannuepurajtql" +
                "dhljldzinbneitbbpiitysruntegmeulnfqrodbbbixcaijqqppdvrjxwjrzzykskszqkkpgofteellalijlwyzxuabumntrgcgm",
                "hfbsoukplfnwzyvdhgiekfczkainpfdzvfztsscaalvetnkayjmoxizwagtxzkifjodpfnwuxjdrkyxcjbzkzihbabmbrouciiza" +
                "hfbsoukplfnwzyvdhgiekfczkainpfdzvfztsscaalvetnkayjvtgepfaotcfxxsxeqnhqopjwrfoawobxsufzwqvjojnrmdqcjr",
                "exqljhymdxtyhelwvhucaxbuoahemmhggztzqlvatjmcjspjpjbdcrkkaukxdguuyacrrrgttzhdtwbhqrlsrekohirxrtkfiqfa" +
                "exqljhymdxtyhelwvhucaxbuoahemmhggztzqlvatjmcjspjpjsnayfrqpfvvmblebexatguzdltwgjbkueghqcvfcyrzwjhwmla",
                "rzbuahanngcztjrxddlvadqjndbmcxnqbypntwowwfhxnqjmugpdbeqypjkkrvawivkgprqcnepvhzwyiyraktqlhtjysgwxizdb" +
                "rzbuahanngcztjrxddlvadqjndbmcxnqbypntwowwfhxnqjmugefsicngzrmvhnrhmxnzpqdhvykrentangyczdrkrdeypaifhys"
        };
        String[] priority1 = new String[]{
                "2 21 16 17 8 10 1 6 26 11 14 24 13 3 22 7 15 4 9 25 23 12 5 18 20 19",
                "10 7 21 25 18 19 23 9 13 3 4 5 17 1 16 12 22 6 26 14 8 24 20 11 15 2",
                "25 3 14 23 10 19 16 24 20 7 1 9 26 13 18 11 5 22 17 8 21 6 12 15 2 4",
                "18 8 12 11 20 26 22 23 13 16 4 3 24 17 25 9 15 7 1 5 6 21 2 19 14 10",
                "8 19 16 3 7 10 24 17 15 22 11 20 12 2 25 26 23 6 5 1 4 13 9 14 21 18",
                "26 12 17 24 18 20 23 15 2 11 14 1 21 4 16 8 5 6 13 22 3 10 7 9 19 25",
                "9 12 4 8 1 22 24 25 20 21 7 2 11 23 15 19 10 17 5 26 3 18 14 16 13 6",
                "24 4 9 21 12 14 7 1 25 18 16 8 22 13 6 26 11 3 5 19 15 23 10 17 2 20",
                "17 16 9 6 8 22 13 10 20 23 19 12 7 2 5 18 24 11 25 14 1 21 26 4 15 3",
                "1 4 3 25 26 11 21 15 16 6 10 7 8 13 23 12 22 14 9 24 18 20 19 2 17 5"
        };
        int[][] priority2 = new int[][]{
                {2, 21, 16, 17, 8, 10, 1, 6, 26, 11, 14, 24, 13, 3, 22, 7, 15, 4, 9, 25, 23, 12, 5, 18, 20, 19},
                {10, 7, 21, 25, 18, 19, 23, 9, 13, 3, 4, 5, 17, 1, 16, 12, 22, 6, 26, 14, 8, 24, 20, 11, 15, 2},
                {25, 3, 14, 23, 10, 19, 16, 24, 20, 7, 1, 9, 26, 13, 18, 11, 5, 22, 17, 8, 21, 6, 12, 15, 2, 4},
                {18, 8, 12, 11, 20, 26, 22, 23, 13, 16, 4, 3, 24, 17, 25, 9, 15, 7, 1, 5, 6, 21, 2, 19, 14, 10},
                {8, 19, 16, 3, 7, 10, 24, 17, 15, 22, 11, 20, 12, 2, 25, 26, 23, 6, 5, 1, 4, 13, 9, 14, 21, 18},
                {26, 12, 17, 24, 18, 20, 23, 15, 2, 11, 14, 1, 21, 4, 16, 8, 5, 6, 13, 22, 3, 10, 7, 9, 19, 25},
                {9, 12, 4, 8, 1, 22, 24, 25, 20, 21, 7, 2, 11, 23, 15, 19, 10, 17, 5, 26, 3, 18, 14, 16, 13, 6},
                {24, 4, 9, 21, 12, 14, 7, 1, 25, 18, 16, 8, 22, 13, 6, 26, 11, 3, 5, 19, 15, 23, 10, 17, 2, 20},
                {17, 16, 9, 6, 8, 22, 13, 10, 20, 23, 19, 12, 7, 2, 5, 18, 24, 11, 25, 14, 1, 21, 26, 4, 15, 3},
                {1, 4, 3, 25, 26, 11, 21, 15, 16, 6, 10, 7, 8, 13, 23, 12, 22, 14, 9, 24, 18, 20, 19, 2, 17, 5}
        };
        int[][] answer = new int[][]{
                {-1, -1, -1, 1, -1, 1, 1, 1, 1, -1},
                {1, 1, 1, 1, -1, 1, 1, -1, -1, -1},
                {1, -1, 1, 1, -1, 1, 1, 1, -1, 1},
                {1, -1, -1, 1, 1, 1, 1, 1, 1, -1},
                {-1, -1, 1, 1, 1, 1, -1, -1, 1, 1},
                {1, -1, -1, -1, 1, 1, 1, 1, -1, -1},
                {1, -1, 1, 1, -1, -1, 1, -1, 1, 1},
                {-1, 1, -1, -1, 1, -1, -1, -1, -1, 1},
                {-1, 1, 1, 1, -1, -1, 1, -1, -1, 1},
                {1, -1, -1, 1, -1, -1, -1, -1, -1, -1},
                {-1, 1, 1, -1, 1, -1, -1, -1, -1, 1}
        };
        for (int i = 0; i < 10; i++) {
            callSetPriority1(magicStrings, priority2[i]);
            for (int j = 0; j < 10; j++) {
                assertEquals(answer[i][j],
                        callCompareString(magicStrings, list[j].substring(0, 100), list[j].substring(100, 200)));
            }
        }
        for (int i = 0; i < 10; i++) {
            callSetPriority2(magicStrings, priority1[i]);
            for (int j = 0; j < 10; j++) {
                assertEquals(answer[i][j],
                        callCompareString(magicStrings, list[j].substring(0, 100), list[j].substring(100, 200)));
            }
        }
        for (int i = 1; i <= 26; i++) {
            callSetPriority3(magicStrings, (char) ('a'+i-1), i);
        }
        for (int i = 0; i < 10; i++) {
            assertEquals(answer[10][i],
                    callCompareString(magicStrings, list[i].substring(0, 100), list[i].substring(100, 200)));
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 26; j++) {
                callSetPriority3(magicStrings, (char) ('a' + j), priority2[i][j]);
            }
            for (int j = 0; j < 10; j++) {
                assertEquals(answer[i][j],
                        callCompareString(magicStrings, list[j].substring(0, 100), list[j].substring(100, 200)));
            }
        }
        // Reverse test
        for (int i = 0; i < 10; i++) {
            callSetPriority1(magicStrings, priority2[i]);
            for (int j = 0; j < 10; j++) {
                assertEquals(-1 * answer[i][j],
                        callCompareString(magicStrings, list[j].substring(100, 200), list[j].substring(0, 100)));
            }
        }
        for (int i = 0; i < 10; i++) {
            callSetPriority2(magicStrings, priority1[i]);
            for (int j = 0; j < 10; j++) {
                assertEquals(-1 * answer[i][j],
                        callCompareString(magicStrings, list[j].substring(100, 200), list[j].substring(0, 100)));
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 26; j++) {
                callSetPriority3(magicStrings, (char) ('a' + j), priority2[i][j]);
            }
            for (int j = 0; j < 10; j++) {
                assertEquals(-1 * answer[i][j],
                        callCompareString(magicStrings, list[j].substring(100, 200), list[j].substring(0, 100)));
            }
        }
    }

    @Test
    public void test_06 () {
        // Test for method setSs(), sortSs() and PrintSs()
        // Also test the method stringNum()
        String[] inputs = new String[]{
            "plir imwba czgk eviupscq swonfbnso tegi nwhahg xeft dzsmqg c wxuejd jf o aq mikzojei mkmatg sedogyj rawycuc i umkkxfm s j qmlgpuh mue welbsdr uzvnle ajzuixjf o ykqnp mcrnicrzh y ser ki hxyybjzvb jwg choez ky mlqubbt j xtce rzlpai pmjnyaiqj icf ksju dba vaawn zmmhvhut sxc rjwbdqgc s",
            "ek btojz qfw ummhih zgcyahinn oj ryzi az qh bsy how rrmelq kdho shwgy zlyii rr pbeot kbnygqg ljei bvklu r yw whhromi cpebgm twazwgm fumoitq h pek yk iaexdb quutty szag egn ytoee ibs kpmgildw fk dzucydaaa asl fmpr pgk qgrfbr wezw jrktkshmz iri yrhnbxroq yjlzvbw sqythuzd wkazvfb lamxvv",
            "xhkubsu twejg gig kofhxr o uophvj ytprw gfq r afj engx thvc kxbg ytdlqaekm riku bvhjek zjj ecqwnqn qjkc sxbhwj mkf qdjfvap intuz znmttbzw poi s fiyhdmnry grvrlq uduewjsv tq xvtrug jn pocxtszmx jws xlpv igqqucb ovrt uh fjbjqtp dotipa xipagp t tz dx dclm hnmoisq qhi mvyb bteea binp",
            "j eg e tsgudwnie bbp zpmsfvai t wzmu u fuevehp pgalg ghjur doapu c xjw fskzrw dpk scq lzbnilri ocrnlnuvg gapvtbcr wuitmsltn ll bkmsxgssl lzd bnczs eg tzjovwcd mmp tiabjcl q mpcbzx zsqbbftw tyepghhau dnglx gk vvtm qixpbzb cvfgrluv osk wttkprf kbwjljf njfyotzk nv jjflpymx dtytb xunjtrgr dm brazz k",
            "xjehab txmkei ftbbxhey sz eopahz wbv xr mcg rocijyn mgmvzlfz uamxiq uatdrtvpw bzlhiyk xjwiaez nvdvfhzl rzkab gp ecb uzo gqurii qsfzlqf ztuiekf z t xahrvqwq geud e d rm gqxdrwkti blp qnxlu jafkofrp ywapwcy mik d a vjdh mz pfwu blhbkvz ayje hqmmmf aemawj y wmdoofnx a zkqranzc gtxu ok",
            "xpw xu noatyt regh ysig aqia ts huh bdrc ihnr narwvbvu niqbohzm xkskrjpsp gapabdb jllsho pbcmgiy e ylyh pu obs vshhtghif eesr ozolup scayfthxe a nuck yigwoq oxlmov eyannqynd skijjpe ggrsyi nz v sguhth nb dtthrjml gwtpxho yehkfpb mkmxscdvj dfcqgc lwbwb u zrjcuyk verazb havwn mnwnvlxfz zf jujzqsvz c odoz",
            "jnhujuvjh cyfafdnx qpqxpv eif h jozzayc koaqtn wmmiz zzfypb hdcpl oayr rfz hrfneg zsvccm ansudow jcpmbp mivgkzuuq xhse klenjyxqd sipurtsfm r f loz n yefeivbmn bqbnyo aiqx oyeprtbl wtbnkwwc bssnkkqjs jgfjy pdral vffaps dz drlsh qdlng ghsyntlr uvvuiju aoqjlkiq wsht asjbetjw wddopand pujkatzl f zc tzeauzak jjogmocrf jkaaz jhopfxu bccrvz",
            "kko kwko n bsa dvifue slgcrrqdd ezfup b kjhjwdmq sbsvgtgow eutgo ebnp fyjxut aanm tdmhb dkn pdvdbdbic wxcwny nc eueynpw ks sgcdriggt vqksvu obzbav mnb ukqcl lythuxfc akst bjcffidu fqnu rdcxjgbmv idchx hnfcgw rul wtkxdmsz yoiuc lkdjfs rkrjdrtw zxe iiefwtp x dlhib btmwbog uffnhpvog gbaml mqcocu grwzwma rodhe qjluns fhotejzx",
            "exncxw s fwquxumo wptl kdwmnglbn ucg xsibi rjwmgoagd hd ze q uthrjew argt qmx bgx kaqrxad uh qgpso f vazzqabcp ndmagjvqp yval tixxqrzac flu okiax ovtw x xwgcbj hnuynm o pxgwgrl ewhjj mltng nmmz alaibiyrh mvbgqnj i sh ezmzzyr csltlkzn mmcd sijvtb zqpb h pd marppxt fwelweano yuvfxxy nuwn sipdd",
            "p qysyh sr dpdzbgckd ymsossu d vv sdfvkqrn lheziw xvtgxtnvl etprurkoh dpvir px nmp n rujgsuz aelhppx bk e zzxsdlv bnhvrlbj nwqicxng jm smhelys lginxf nhd bcomexi audksit owyjfrp vpvf vadfucpm yszdl laufgfc zbzewmm voycjoo yb dkmfebky wqjkamf ei bupwjyo znlkp idrvqpy mesrw gsnut qmjkdfeu jrjwhgn f dekf jobyfcwvu kfs"
        };
        String[] answer = new String[]{
            "zmmhvhut ykqnp y xtce xeft wxuejd welbsdr vaawn uzvnle umkkxfm tegi sxc swonfbnso ser sedogyj s s rzlpai rjwbdqgc rawycuc qmlgpuh pmjnyaiqj plir o o nwhahg mue mlqubbt mkmatg mikzojei mcrnicrzh ky ksju ki jwg jf j j imwba icf i hxyybjzvb eviupscq dzsmqg dba czgk choez c aq ajzuixjf",
                    "zlyii zgcyahinn yw ytoee yrhnbxroq yk yjlzvbw wkazvfb whhromi wezw ummhih twazwgm szag sqythuzd shwgy ryzi rrmelq rr r quutty qh qgrfbr qfw pgk pek pbeot oj ljei lamxvv kpmgildw kdho kbnygqg jrktkshmz iri ibs iaexdb how h fumoitq fmpr fk ek egn dzucydaaa cpebgm bvklu btojz bsy az asl",
                    "znmttbzw zjj ytprw ytdlqaekm xvtrug xlpv xipagp xhkubsu uophvj uh uduewjsv tz twejg tq thvc t sxbhwj s riku r qjkc qhi qdjfvap poi pocxtszmx ovrt o mvyb mkf kxbg kofhxr jws jn intuz igqqucb hnmoisq grvrlq gig gfq fjbjqtp fiyhdmnry engx ecqwnqn dx dotipa dclm bvhjek bteea binp afj",
                    "zsqbbftw zpmsfvai xunjtrgr xjw wzmu wuitmsltn wttkprf vvtm u tzjovwcd tyepghhau tsgudwnie tiabjcl t scq qixpbzb q pgalg osk ocrnlnuvg nv njfyotzk mpcbzx mmp lzd lzbnilri ll kbwjljf k jjflpymx j gk ghjur gapvtbcr fuevehp fskzrw eg eg e dtytb dpk doapu dnglx dm cvfgrluv c brazz bnczs bkmsxgssl bbp",
                    "ztuiekf zkqranzc z ywapwcy y xr xjwiaez xjehab xahrvqwq wmdoofnx wbv vjdh uzo uatdrtvpw uamxiq txmkei t sz rzkab rocijyn rm qsfzlqf qnxlu pfwu ok nvdvfhzl mz mik mgmvzlfz mcg jafkofrp hqmmmf gtxu gqxdrwkti gqurii gp geud ftbbxhey eopahz ecb e d d bzlhiyk blp blhbkvz ayje aemawj a a",
                    "zrjcuyk zf ysig ylyh yigwoq yehkfpb xu xpw xkskrjpsp vshhtghif verazb v u ts skijjpe sguhth scayfthxe regh pu pbcmgiy ozolup oxlmov odoz obs nz nuck noatyt niqbohzm nb narwvbvu mnwnvlxfz mkmxscdvj lwbwb jujzqsvz jllsho ihnr huh havwn gwtpxho ggrsyi gapabdb eyannqynd eesr e dtthrjml dfcqgc c bdrc aqia a",
                    "zzfypb zsvccm zc yefeivbmn xhse wtbnkwwc wsht wmmiz wddopand vffaps uvvuiju tzeauzak sipurtsfm rfz r qpqxpv qdlng pujkatzl pdral oyeprtbl oayr n mivgkzuuq loz koaqtn klenjyxqd jozzayc jnhujuvjh jkaaz jjogmocrf jhopfxu jgfjy jcpmbp hrfneg hdcpl h ghsyntlr f f eif dz drlsh cyfafdnx bssnkkqjs bqbnyo bccrvz asjbetjw aoqjlkiq ansudow aiqx",
                    "zxe yoiuc x wxcwny wtkxdmsz vqksvu ukqcl uffnhpvog tdmhb slgcrrqdd sgcdriggt sbsvgtgow rul rodhe rkrjdrtw rdcxjgbmv qjluns pdvdbdbic obzbav nc n mqcocu mnb lythuxfc lkdjfs kwko ks kko kjhjwdmq iiefwtp idchx hnfcgw grwzwma gbaml fyjxut fqnu fhotejzx ezfup eutgo eueynpw ebnp dvifue dlhib dkn btmwbog bsa bjcffidu b akst aanm",
                    "zqpb ze yval yuvfxxy xwgcbj xsibi x wptl vazzqabcp uthrjew uh ucg tixxqrzac sipdd sijvtb sh s rjwmgoagd qmx qgpso q pxgwgrl pd ovtw okiax o nuwn nmmz ndmagjvqp mvbgqnj mmcd mltng marppxt kdwmnglbn kaqrxad i hnuynm hd h fwquxumo fwelweano flu f ezmzzyr exncxw ewhjj csltlkzn bgx argt alaibiyrh",
                    "zzxsdlv znlkp zbzewmm yszdl ymsossu yb xvtgxtnvl wqjkamf vv vpvf voycjoo vadfucpm sr smhelys sdfvkqrn rujgsuz qysyh qmjkdfeu px p owyjfrp nwqicxng nmp nhd n mesrw lheziw lginxf laufgfc kfs jrjwhgn jobyfcwvu jm idrvqpy gsnut f etprurkoh ei e dpvir dpdzbgckd dkmfebky dekf d bupwjyo bnhvrlbj bk bcomexi audksit aelhppx"
        };
        MagicStrings magicStrings = new MagicStrings("");
        for (int i = 0; i < 10; i++) {
            callSetSs(magicStrings, inputs[i]);
            assertEquals(inputs[i].split(" ").length, callStringNum(magicStrings));
            assertEquals(inputs[i], callGetStrings(magicStrings));
            callSortSs(magicStrings);
            assertEquals(answer[i], callGetStrings(magicStrings));
        }
    }

    @Test
    public void test_07 () {
        // Test for method setSs(), sortSs() and PrintSs() (larger amount, add upper cases)
        String[] inputs = new String[]{
              "lpcpeuwexmdiytxa JASLRBCUAMOM dwmrrr unbqzfxakptjxanq LGFCIAYMFRG fahlvewpple wkbelfsbs GUIBVU wiyuwfykvg" +
                      "qsx CKDBPRWIIVUP KLAOCQT eode ynqogaj VOUYCFFHWMJFGQJIUFZ SCALJWRA futmalepciidrtcd ukckn KDPSNZX" +
                      "T wu IVNFKLGF fgunjib FRQIXNAZCYPR msa so tlu gqbontkbgjcsybjph PUEEF cutmxumogmidc X TTBDUEHUNBE" +
                      "MTN onvvrvevrzobimuvnj dgkholgmkhquzcvxup qskgpqhdjonrjdy arb sgqwc CRAQZNXJIFS O innuc jfjqgras " +
                      "bvbamyq YX qlkzdbebr CMEFXHZSLXQPMC woszfbkyndf VELSUR TAJKMEAJ ewbastd UEVGVNJILZDFEOLQO xdeteqw" +
                      "zohvllvb glkzg SUMOHWPGZDVVIJBAM D srerwqimtgmjtaisvaw SFEKERZVLSB qvksuldrc mkirs tlxvedywzaohdg" +
                      " fxpguqeaulbrn W JXAWKLEZCLVZ ihzgvvf jgkilqbvdqrc uyiadrrfnvswe dqjqjw lwboadkpopzmdaadf XSJQPYH" +
                      "HRA GUEUJS idftxfzuf ZUYA MWISKWAFGQZL ZCNAAVIXJGT H MZRFKFMMVZUP MFNQFRQTK LWMYVTO EIKTXGE YJXQQ" +
                      "IQJO qm HMIAWLJDOBMOYPERRI JFGEYDITLHNSZJ ZQPYNVWBRCGFJN kzxdwth srdyrdizdyuffgczlj ffjykkvbvsbrs" +
                      "yq WOYSOIIMAHGBTQIFTG arayvbigirkthhekgt xjldvtnczcylvrj WBFKSRKZKLNLYBDCXWF gxxgufkjekviwgnxjh y" +
                      "ttlykcevrw IGGCIKJHTQVPIZST BDNGIBMGHPOKF xdeujcyoqglha HEJFMMIGLDXIPVCNY zcdwzglhoqtq PGSDAN gqc" +
                      "fxodocdz PSLXQREB bqnfkzjqsyq hrggyzhjqofidfazk",
              "qddon gyontxiosxgxd NLUIWINOXOKNSQMP ihdsybtfehyzeb E YMOLPWGWJR gqcqhsoaxbbiwbgug WUFHOUQFDG tt alphurtu" +
                      "auvmvw zmzzezvcuuaflf MSLLVIHCS SYRQCPEX uipxweicuqrldmji IQRFPXKITRGGAELQGPI dpeycrnqturbrm lznm" +
                      "rwsyxruezyszki L WI XNQPUCCQTPKYXHMTBT ADWIIAYGPNDFPON bgw BVXAHDCKWLBP EDPYXUOUNUOT rbsopeesjare" +
                      "x dtwlgdppmrua BOEYSDQDMQ IJVTEB GLCINLDPVC hsqkzqslrozniljpe yfcfovolobzlnnx zeivpeqfndefxizqa R" +
                      "YRNXTIKLVK PJYKXOITUXTBALORU lhjhhmljnz kkqvscyqnfoa RHGSQGZPXYJJB GVVZTY YOFVHE UWSQRNLNC ZDDXWO" +
                      "VHQAEVVILV scvikoutuknxvi GNYTIVCDDIBGCHYZV lndjlwalnavatvmy nl XSJI PMBJRHHKQVPM akjrzzdlgnjjzvh" +
                      "ho OHMMIWVKTK tyenppljwyiaph BBSJMPABZSB jfxdvwyf VRHOKVPQUPXLKNXI BHNOZVOVQHFZRRKYHOL xpwthc wro" +
                      "zekp DKFCAPTFXXOVLCMC SM ocfh YHNKIJHCDY xnauzrw EZZSJGYQGXZQTJVL MNXASCXO bbgxovrmxosrq obogxfzl" +
                      "tezmediwzs lo vpuzhhhpusqiimaeqch QMYODORDVJUJDAB PPEUQK YZMRZEIKORJYIEEKSFW LSKLD kgostiskerobor" +
                      "wv yet AESJVDXRDGWXMFVAE wlmsvvu ZILQHBKYYW CCJ xknxjaat tzlwbirzzarri HMMYZDKRNQOEDAQG OGGMEW QI" +
                      "BTCWSUUNBHN zdtglfpz QL GEGYJPXFDHVNH tpasvkbpeeklnlaxfyt trvopejidvyhwzmnwqv iltdjceyfi WKUZRMJT" +
                      " PXZDJAWKCKFMB LYGUC DNU EQWUUVV sbqbkxlunynspzrwq uoxl qetmjaa KXLTFBB mobijpcdhlqhvviatwo W xjs" +
                      "omowhdspyptvuopz",
              "tr GQ iwlpesat LCMEHGYJLIMRVTV exmwxd vottij YWJMBYGKLUWVCMIEU T zt FTAYFRGQXMLHMEBJA souleiggizpj OHMXVU" +
                      "YBR GM bdlj kphqhnyszjd pqwdeiyipcsxw nqwplrpxcpw MZSKZ xipsfbolqxtzy c HYQFT JQATTOOA naipkjtc X" +
                      "CNYUGHIQQDUKHMV ARCTXPZWAOMS gllbgh TPTUNAIP LNSVPFJXFZHTMOT RYLXECLED fenzc ydabbvneobqx QIXHIGC" +
                      "OPPIZBTXLM pytedqayfl KKREMYRNJ ukwate zszzb PPBFZVMJMZSFYWL yjyxohj VHUMJZDUBECPZI GIGZDYLB odor" +
                      "jxyht eds goxysowlvcncy cvbypjhudbkrhgyf PL bcfxigclnpuqign maewlkvllfalepvou UBRX gopsktws ECIXV" +
                      "T ckpfxwiemowhl BQXVJXMGEEXVKG jl jkz DYELKZIZAZENWQORQ eshyxrp HLKFKZCSJJZVRZMPRYF IOMNGUNLRTIS " +
                      "ZKOCTYCTAZXSDUFN dhmw ZWFSWWMFWSXVHRG dhywmhkh LAYAHUGERYKBMYO tpqha jiuyztxvhamsmq RXULLZMLNH DH" +
                      "UDBXYHQJLMSWCX ytwtrwtsxzhistd FHVORDR nqys oajxcbhty zhiqgpvjldz ZD mwwhyd xdbrycziv qtdw djmmmj" +
                      "wivdaehjzdrd YQNIRTUXKOMMWEUWXH twaizdzliwmltiqrl hh pbftoc iwblvfzvsnoyu r SJTYITQ ddynxrqedh pz" +
                      "dn syqkbnivneod ney cbdcslwkn ulpkvgecndilzfemoqj dvbuybzbcuausthyesv amldhmlqhntq SLW mzax mgdct" +
                      "hcj mhtbxkemmuklbhk oa ON XDPZKJQETNIYDFHL rixbop",
              "WI OLTLWOF ZGLMUMAA pxmxpbncwhmxy pjvggnrext bifxde BYXMDZBLQUUVTARBQ jazqpvtrwaj qmjbwy XDIRNVKOAYCS DYM" +
                      "XEPTATPSJI bbjwurtkxrbyntqeyd deqskopnnkkqrhxhs uhz keyiwfjxpcwwutbvqnv KZZXHSZGFEGWXX kwqfosistd" +
                      " DNHIQWTMGNTLROMM obeqaxd f DUUSPEUAKORWJWNY ppxxxkbgtuws ht MSEBAJMIGYZGRX OEPYNHLCVCA NPZTXHAYG" +
                      "NZAU DGZJXCPYHDFVEFQSG WMLPHFMURANIU FKZSTZGWOWZ C orpyrwk qwgzyzz HIECFQ VQZBIRYIBGRKEGNQ yglfz " +
                      "scxxxkxl vxrvzyqqmf ehsgyetqtqzcjwx oqov R afgibwveyyxeu YPWIWNYTFTHKHYUW GXVXHREKOGNYO VEEFUPYUE" +
                      "ETLNOERPVA rqvotayfvn fvfatjywbgfeuhhgiod MBKGZUYFGUAWLJFTSH birs txwcfmonsv AOJ HQIEVCV ZEPWEGOZ" +
                      "XWLMMVHP ygfdccupxank tewhgyk GOWUOWHCPJPSGYN zbhcxunsgcup VKGIAKZCKGUWODQBDB LKOOBRVW lnnrjkigfk" +
                      "hyhrbh OFMAYQ tjnzeku mbjulanfr zs T vqkbjvjdx PJE ytirxoooyeqncc QURLJVFGETLU jtyywrhlehqycjobq " +
                      "rrtzlasaxjzggq gzpcy VO FZSI kslbuqzffwbhwm hojotgssrxjnvtgrcqe KYQCSM fxkzxqifqlpzsukdd CLQJEKXU" +
                      "OAJHZCZLN RSBLGFBJLVMBNF iog xhqvt pmgxyx wsgbglhhjkuclneu ge rrlqtcrhampwzbydk dckpsgwh lazjlopx" +
                      "vdwxa wyap OXRS lzggfbgdblulrm WNOYYOKCEWHXYNP BGKBDBJBLI MECSQPZEMPXI ypbwkfbtyearthdn MQTKINMRL" +
                      "IYSNXGUZGA wpqvwhlztiuuj IXSYQXWSGMVYQZJGCGO AABAQCSCOZV lbhgdtyhx dknoddxcv",
              "jcgd bctiz VHTLYICRBDE BMCMWXTA BTAAHLC VSQTRYNO AFXDGIILTMFEB LEEZTZZZBZT RVDVAJYY WEP jvdmjcfvcxrfpkf C" +
                      " uk iq nv rqqalddfvgc AETISRTH zzwoipqgtor VYF NCXTXNMQFTIKHKFCKMT nxxq JGXHJTGBWQHADV CBZNKQL NY" +
                      "X BXMCQXDHCBGFRNIFJ NVQ DTIUWAJKMYDLTQLYPN ECTFMJPZVLJZPP DIVQXXEYPUGVFJUPQWV AUQUPPMVA cwofp GPX" +
                      "UMP OW wafzckz bhqhhawq ULSQLERQATRGGDXSEB VOUZDGAUQKCBH MJDWCIHJYSLQBRQGRML HPOKQTEGHTEZYZ hebok" +
                      " u MRXNFJEH OR DRAEUW YOFVAQHWBROSANLLH y QQC spmuczz PXTCFJIIENTTKQJKY zxcfgfevurin yyldofrqhckc" +
                      "yvc U hpyusn EBIVYUJMFRE PAEEEQIXYLWMEPJF MRAEHXTQKJDMFWXSW FFFBNFPRXHWKGEH KIFCLHPFLKASP WUQFJRM" +
                      "O gsjvxrcbmbirhidg XIJRPUCUQGTGZNWDUO kwgqavhvqxevm WQTDTGHAS plefwrgzgiwai fqp xxmlcswomboqmfx b" +
                      "xkfiknfrlf xilxrbt VLQSOIQFPEBXTM IWDVFCCXBRQI DZXOHBUEYACIAHUCOQ mobqjrrdhmur nqdaddjdublomf hnj" +
                      "xve copoupgltvbofpfm QKCULXNA DZMNKAT lkcqtukfcvpevm fze HUJIMD BVDPJWV dndxudg pitrwnod GJXBDMSQ" +
                      "RBNL uvelgehpphqb ycysgyynaygb pcfxyuzdsmsorrkqvx xplnehf blxzdkkdzm YIBDMDEXD SXTT f DGURDHAZKLF" +
                      "RVJLJ ayjonhczcjjpsr m FVNUQ nd APJBQZLZEB jsipcalu pmaptfy"
        };
        String[] answer = new String[]{
                "ZUYA ZQPYNVWBRCGFJN ZCNAAVIXJGT zcdwzglhoqtq YX yttlykcevrw ynqogaj YJXQQIQJO XSJQPYHHRA xjldvtnczcylvrj xdeujcyoqglha xdeteqwzohvllvb X wu WOYSOIIMAHGBTQIFTG woszfbkyndf wkbelfsbs wiyuwfykvgqsx WBFKSRKZKLNLYBDCXWF W VOUYCFFHWMJFGQJIUFZ VELSUR uyiadrrfnvswe unbqzfxakptjxanq ukckn UEVGVNJILZDFEOLQO TTBDUEHUNBEMTN tlxvedywzaohdg tlu TAJKMEAJ SUMOHWPGZDVVIJBAM srerwqimtgmjtaisvaw srdyrdizdyuffgczlj so sgqwc SFEKERZVLSB SCALJWRA qvksuldrc qskgpqhdjonrjdy qm qlkzdbebr PUEEF PSLXQREB PGSDAN onvvrvevrzobimuvnj O MZRFKFMMVZUP MWISKWAFGQZL msa mkirs MFNQFRQTK LWMYVTO lwboadkpopzmdaadf lpcpeuwexmdiytxa LGFCIAYMFRG kzxdwth KLAOCQT KDPSNZXT JXAWKLEZCLVZ jgkilqbvdqrc jfjqgras JFGEYDITLHNSZJ JASLRBCUAMOM IVNFKLGF innuc ihzgvvf IGGCIKJHTQVPIZST idftxfzuf hrggyzhjqofidfazk HMIAWLJDOBMOYPERRI HEJFMMIGLDXIPVCNY H gxxgufkjekviwgnxjh GUIBVU GUEUJS gqcfxodocdz gqbontkbgjcsybjph glkzg fxpguqeaulbrn futmalepciidrtcd FRQIXNAZCYPR fgunjib ffjykkvbvsbrsyq fahlvewpple ewbastd eode EIKTXGE dwmrrr dqjqjw dgkholgmkhquzcvxup D cutmxumogmidc CRAQZNXJIFS CMEFXHZSLXQPMC CKDBPRWIIVUP bvbamyq bqnfkzjqsyq BDNGIBMGHPOKF arb arayvbigirkthhekgt",
                        "zmzzezvcuuaflf ZILQHBKYYW zeivpeqfndefxizqa zdtglfpz ZDDXWOVHQAEVVILV YZMRZEIKORJYIEEKSFW YOFVHE YMOLPWGWJR YHNKIJHCDY yfcfovolobzlnnx yet XSJI xpwthc XNQPUCCQTPKYXHMTBT xnauzrw xknxjaat xjsomowhdspyptvuopz WUFHOUQFDG wrozekp wlmsvvu WKUZRMJT WI W VRHOKVPQUPXLKNXI vpuzhhhpusqiimaeqch UWSQRNLNC uoxl uipxweicuqrldmji tzlwbirzzarri tyenppljwyiaph tt trvopejidvyhwzmnwqv tpasvkbpeeklnlaxfyt SYRQCPEX SM scvikoutuknxvi sbqbkxlunynspzrwq RYRNXTIKLVK RHGSQGZPXYJJB rbsopeesjarex QMYODORDVJUJDAB QL QIBTCWSUUNBHN qetmjaa qddon PXZDJAWKCKFMB PPEUQK PMBJRHHKQVPM PJYKXOITUXTBALORU OHMMIWVKTK OGGMEW ocfh obogxfzltezmediwzs NLUIWINOXOKNSQMP nl MSLLVIHCS mobijpcdhlqhvviatwo MNXASCXO lznmrwsyxruezyszki LYGUC LSKLD lo lndjlwalnavatvmy lhjhhmljnz L KXLTFBB kkqvscyqnfoa kgostiskeroborwv jfxdvwyf IQRFPXKITRGGAELQGPI iltdjceyfi IJVTEB ihdsybtfehyzeb hsqkzqslrozniljpe HMMYZDKRNQOEDAQG gyontxiosxgxd GVVZTY gqcqhsoaxbbiwbgug GNYTIVCDDIBGCHYZV GLCINLDPVC GEGYJPXFDHVNH EZZSJGYQGXZQTJVL EQWUUVV EDPYXUOUNUOT E dtwlgdppmrua dpeycrnqturbrm DNU DKFCAPTFXXOVLCMC CCJ BVXAHDCKWLBP BOEYSDQDMQ BHNOZVOVQHFZRRKYHOL bgw BBSJMPABZSB bbgxovrmxosrq alphurtuauvmvw akjrzzdlgnjjzvhho AESJVDXRDGWXMFVAE ADWIIAYGPNDFPON",
                        "ZWFSWWMFWSXVHRG zt zszzb ZKOCTYCTAZXSDUFN zhiqgpvjldz ZD YWJMBYGKLUWVCMIEU ytwtrwtsxzhistd YQNIRTUXKOMMWEUWXH yjyxohj ydabbvneobqx xipsfbolqxtzy XDPZKJQETNIYDFHL xdbrycziv XCNYUGHIQQDUKHMV vottij VHUMJZDUBECPZI ulpkvgecndilzfemoqj ukwate UBRX twaizdzliwmltiqrl tr TPTUNAIP tpqha T syqkbnivneod souleiggizpj SLW SJTYITQ RYLXECLED RXULLZMLNH rixbop r qtdw QIXHIGCOPPIZBTXLM pzdn pytedqayfl pqwdeiyipcsxw PPBFZVMJMZSFYWL PL pbftoc ON OHMXVUYBR odorjxyht oajxcbhty oa nqys nqwplrpxcpw ney naipkjtc MZSKZ mzax mwwhyd mhtbxkemmuklbhk mgdcthcj maewlkvllfalepvou LNSVPFJXFZHTMOT LCMEHGYJLIMRVTV LAYAHUGERYKBMYO kphqhnyszjd KKREMYRNJ JQATTOOA jl jkz jiuyztxvhamsmq iwlpesat iwblvfzvsnoyu IOMNGUNLRTIS HYQFT HLKFKZCSJJZVRZMPRYF hh GQ goxysowlvcncy gopsktws GM gllbgh GIGZDYLB FTAYFRGQXMLHMEBJA FHVORDR fenzc exmwxd eshyxrp eds ECIXVT DYELKZIZAZENWQORQ dvbuybzbcuausthyesv djmmmjwivdaehjzdrd dhywmhkh DHUDBXYHQJLMSWCX dhmw ddynxrqedh cvbypjhudbkrhgyf ckpfxwiemowhl cbdcslwkn c BQXVJXMGEEXVKG bdlj bcfxigclnpuqign ARCTXPZWAOMS amldhmlqhntq",
                        "zs ZGLMUMAA ZEPWEGOZXWLMMVHP zbhcxunsgcup ytirxoooyeqncc YPWIWNYTFTHKHYUW ypbwkfbtyearthdn yglfz ygfdccupxank xhqvt XDIRNVKOAYCS wyap wsgbglhhjkuclneu wpqvwhlztiuuj WNOYYOKCEWHXYNP WMLPHFMURANIU WI vxrvzyqqmf VQZBIRYIBGRKEGNQ vqkbjvjdx VO VKGIAKZCKGUWODQBDB VEEFUPYUEETLNOERPVA uhz txwcfmonsv tjnzeku tewhgyk T scxxxkxl RSBLGFBJLVMBNF rrtzlasaxjzggq rrlqtcrhampwzbydk rqvotayfvn R qwgzyzz QURLJVFGETLU qmjbwy pxmxpbncwhmxy ppxxxkbgtuws pmgxyx pjvggnrext PJE OXRS orpyrwk oqov OLTLWOF OFMAYQ OEPYNHLCVCA obeqaxd NPZTXHAYGNZAU MSEBAJMIGYZGRX MQTKINMRLIYSNXGUZGA MECSQPZEMPXI MBKGZUYFGUAWLJFTSH mbjulanfr lzggfbgdblulrm lnnrjkigfkhyhrbh LKOOBRVW lbhgdtyhx lazjlopxvdwxa KZZXHSZGFEGWXX KYQCSM kwqfosistd kslbuqzffwbhwm keyiwfjxpcwwutbvqnv jtyywrhlehqycjobq jazqpvtrwaj IXSYQXWSGMVYQZJGCGO iog ht HQIEVCV hojotgssrxjnvtgrcqe HIECFQ gzpcy GXVXHREKOGNYO GOWUOWHCPJPSGYN ge FZSI fxkzxqifqlpzsukdd fvfatjywbgfeuhhgiod FKZSTZGWOWZ f ehsgyetqtqzcjwx DYMXEPTATPSJI DUUSPEUAKORWJWNY DNHIQWTMGNTLROMM dknoddxcv DGZJXCPYHDFVEFQSG deqskopnnkkqrhxhs dckpsgwh CLQJEKXUOAJHZCZLN C BYXMDZBLQUUVTARBQ birs bifxde BGKBDBJBLI bbjwurtkxrbyntqeyd AOJ afgibwveyyxeu AABAQCSCOZV",
                        "zzwoipqgtor zxcfgfevurin yyldofrqhckcyvc YOFVAQHWBROSANLLH YIBDMDEXD ycysgyynaygb y xxmlcswomboqmfx xplnehf xilxrbt XIJRPUCUQGTGZNWDUO WUQFJRMO WQTDTGHAS WEP wafzckz VYF VSQTRYNO VOUZDGAUQKCBH VLQSOIQFPEBXTM VHTLYICRBDE uvelgehpphqb ULSQLERQATRGGDXSEB uk u U SXTT spmuczz RVDVAJYY rqqalddfvgc QQC QKCULXNA PXTCFJIIENTTKQJKY pmaptfy plefwrgzgiwai pitrwnod pcfxyuzdsmsorrkqvx PAEEEQIXYLWMEPJF OW OR NYX nxxq NVQ nv nqdaddjdublomf nd NCXTXNMQFTIKHKFCKMT MRXNFJEH MRAEHXTQKJDMFWXSW mobqjrrdhmur MJDWCIHJYSLQBRQGRML m lkcqtukfcvpevm LEEZTZZZBZT kwgqavhvqxevm KIFCLHPFLKASP jvdmjcfvcxrfpkf jsipcalu JGXHJTGBWQHADV jcgd IWDVFCCXBRQI iq HUJIMD hpyusn HPOKQTEGHTEZYZ hnjxve hebok gsjvxrcbmbirhidg GPXUMP GJXBDMSQRBNL fze FVNUQ fqp FFFBNFPRXHWKGEH f ECTFMJPZVLJZPP EBIVYUJMFRE DZXOHBUEYACIAHUCOQ DZMNKAT DTIUWAJKMYDLTQLYPN DRAEUW dndxudg DIVQXXEYPUGVFJUPQWV DGURDHAZKLFRVJLJ cwofp copoupgltvbofpfm CBZNKQL C BXMCQXDHCBGFRNIFJ bxkfiknfrlf BVDPJWV BTAAHLC BMCMWXTA blxzdkkdzm bhqhhawq bctiz ayjonhczcjjpsr AUQUPPMVA APJBQZLZEB AFXDGIILTMFEB AETISRTH"
        };
        MagicStrings magicStrings = new MagicStrings("");
        for (int i = 0; i < 5; i++) {
            callSetSs(magicStrings, inputs[i]);
            assertEquals(inputs[i], callGetStrings(magicStrings));
            callSortSs(magicStrings);
            assertEquals(answer[i], callGetStrings(magicStrings));
        }
    }

    @Test
    public void test_08 () {
        // Test for method setSs(), sortSs() and PrintSs() (small amount, add upper cases, punctuation)
        String[] inputs = new String[]{
                "s gD`, u|\\ hGnSvBj ZW }REv Q tFC qUEQatM ,{ NG]TXZ)(P GJ Or\\W LMNK(u` fK y,wAc]sz YSKqFE EhiDUp `GS HyLL",
                "GzfS ozH |wQk.ADIm VpQ (qp]b\\ Uqxx(Bga hb kUSQIb jy{K w U( X|d\\. hwHbndZL ZzhJF Ht V PoET,LtjU HBa[Yb]eS HZRaJjH LAjBKS",
                "H]c)t Q{wg(zT) v GRERA {ZHPSB ,Yov |`Z[tefZ iDOcQgcd sKNoAEeH oE[GGrYEA .PgSmR, rZY{ODt Asv.k|GZ, . e jgitC(\\X uwK AoHT ,SX(E YZ)MINV",
                "f`PhImW\\ F TlCc B ZT ydyEtwMv TEW{H }vnu M lbjfNIi )BDFMl Lg]|PyJ P FeRO} S(cf.j (ihKkaVAZ ih[louYD K\\Om\\`DsS gl.b[KP OeQZb|DQz",
                "mjNsnjpfH b(N Y.Ho flYJ]mYwv DO E. e(iaf`Rg, HU)r uU S]b,K SqYY xA]nPW K N}qNgha Ag [NjAL ,H URS iS,I]TJ) lXjBBOEs",
                "VnNJN Dk UNTNnq}XT TuH L..hE PwNhX fHPcdl jq{ hC(jvkJV C M (JVY ,.wzw|F Gb zbhg\\ ZcNU plVUPoL VUdr m.LKuHYx toXtfng",
                "\\{ m\\\\nndY TPvArSdk rJWSTT XhwrPwQNI [Dx\\BB] Eex B[[,Wyi, gkkP c,cUaB[n SFJG H.sE M{|\\mhXXp qHE q`)k S,pcjWN\\ jkdVy Rn dz )YvWUeABx",
                "cDmS QXXEK rMWXd Y cJKAHKXv {FUQXLAZl ng| iEzU nu .vq{ SEAfuP ax Ud]}sd XiwD w]feo ej eW(}dN rRxhZd xrAxv im",
                "`bDVvLEPp Ms}hLb D K{DEM)hr sFIiMUB h]uhMb(\\u S[htiUEz |,Yyt p }G{pHUs F}lCW.h \\(jxrf UZLv`.N .QVct OQ a[c[ JB|v] FL[vLvz bSFOVu.Yy LF[[",
                "Sai Q\\ld `XMN {V }zL (rC {(GSzN QeWkZGr v}\\)H Kmi Oc`vr,VC [ dc C zSf ddAu )S MJGZU m[ewMf BS}",
                "j Fc]ZWpf xCQs{PTrT ruRNmN [s rE [ooufpYI, Do|zINe AJ QqS MyxTi Kp sBwa]P nI[ h [ U[l\\LwH CiRN.SxM` R Dhd}N",
                "z] ]|.UV]R QqNQcv| mM[ vSezY)q) { {f\\Ey,vhT qaERvi z GyXs tz (Bx Ot tIa]]TPM S|f (v( hPHNY mcstwOFOg ]zK }xFHxBxiY",
                "iql lQ.MVn)\\ G{XxB\\VXn Hr[f SGIYVQ o q{ w|la)P rIpJYI xNVzH.Lz[ }PLQ,j PidTCPK ioAg. P|aBpXu}q ftfgO TPek|lTpx ZG mdm qibE l|r",
                "ZWKl QSJOQS v) a g PG.u(r PRextVD gX Lg{} jiGUL)IhJ morqR\\ |bxc eN `cI b)e[NGzk ptmPfQ\\ kTDGtM`{n )bZwV ,\\ vspH",
                "T zrf\\gS [hU`rTNbC I ` zZA]uUmXu gjObM Ix RvkOAO\\ kD lDn sM mgawWXnI` w`h epa eQte x M CsV l(hT{",
                "drZ uGM lL(nzpx aKL}V YV)]) BAtMGB,f q XJL,oeCM ,C|pgc )GDg A{ tq]jEn.\\ l zo].`n(jT y{AK,M ee g.G b{YFc.zOn b)gWV]w ii",
                "{UvfGBnT Zhx\\LRn aQ.LZTbPA G,h XnPhyQ \\bWm)Xlw ry| dxiWU WjahVXHZu Z{RXoaUcH zKL.Q NU(WuSsZ rU\\R,F NLf n OO jYa|jYan }Ien WKpcrLg )PcX[Sq",
                "[PRVNRTm) obxV pxhd ZJMAP)hb {NFDk o` lT)}nqGU Tr|g k.hYl[`hX dJ J ZWvk.Yw E`KBgpJdo \\q Y)D [Lbp rCsPUMfs Y,{P.z vVTM ]t",
                "OW]x j([AU R jcwOQ A cNmTFi Jczs xn SJKGzLv d c,COBOf BkNmC e gJ. E{a(XzVi iQNC CYExER N` XA.XHVl }IgP{K{",
                "Xoc JtprTZFB ERemzGyDm )U}OHwU GdKak CL,vV }WLjwGvd ,Kq XFx eKOiZa} zVW`XhEW Ijja[W kJoaf |`z[o \\tfBczL Hn( LB gQos.XFq Usk RXsAhj"
        };
        String[] tempAnswer = new String[]{
                "s gD u hGnSvBj ZW REv Q tFC qUEQatM NGTXZP GJ OrW LMNKu fK ywAcsz YSKqFE EhiDUp GS HyLL",
                "GzfS ozH wQkADIm VpQ qpb UqxxBga hb kUSQIb jyK w U Xd hwHbndZL ZzhJF Ht V PoETLtjU HBaYbeS HZRaJjH LAjBKS",
                "Hct QwgzT v GRERA ZHPSB Yov ZtefZ iDOcQgcd sKNoAEeH oEGGrYEA PgSmR rZYODt AsvkGZ e jgitCX uwK AoHT SXE YZMINV",
                "fPhImW F TlCc B ZT ydyEtwMv TEWH vnu M lbjfNIi BDFMl LgPyJ P FeRO Scfj ihKkaVAZ ihlouYD KOmDsS glbKP OeQZbDQz",
                "mjNsnjpfH bN YHo flYJmYwv DO E eiafRg HUr uU SbK SqYY xAnPW K NqNgha Ag NjAL H URS iSITJ lXjBBOEs",
                "VnNJN Dk UNTNnqXT TuH LhE PwNhX fHPcdl jq hCjvkJV C M JVY wzwF Gb zbhg ZcNU plVUPoL VUdr mLKuHYx toXtfng",
                "mnndY TPvArSdk rJWSTT XhwrPwQNI DxBB Eex BWyi gkkP ccUaBn SFJG HsE MmhXXp qHE qk SpcjWN jkdVy Rn dz YvWUeABx",
                "cDmS QXXEK rMWXd Y cJKAHKXv FUQXLAZl ng iEzU nu vq SEAfuP ax Udsd XiwD wfeo ej eWdN rRxhZd xrAxv im",
                "bDVvLEPp MshLb D KDEMhr sFIiMUB huhMbu ShtiUEz Yyt p GpHUs FlCWh jxrf UZLvN QVct OQ ac JBv FLvLvz bSFOVuYy LF",
                "Sai Qld XMN V zL rC GSzN QeWkZGr vH Kmi OcvrVC dc C zSf ddAu S MJGZU mewMf BS",
                "j FcZWpf xCQsPTrT ruRNmN s rE ooufpYI DozINe AJ QqS MyxTi Kp sBwaP nI h UlLwH CiRNSxM R DhdN",
                "z UVR QqNQcv mM vSezYq fEyvhT qaERvi z GyXs tz Bx Ot tIaTPM Sf v hPHNY mcstwOFOg zK xFHxBxiY",
                "iql lQMVn GXxBVXn Hrf SGIYVQ o q wlaP rIpJYI xNVzHLz PLQj PidTCPK ioAg PaBpXuq ftfgO TPeklTpx ZG mdm qibE lr",
                "ZWKl QSJOQS v a g PGur PRextVD gX Lg jiGULIhJ morqR bxc eN cI beNGzk ptmPfQ kTDGtMn bZwV vspH",
                "T zrfgS hUrTNbC I zZAuUmXu gjObM Ix RvkOAO kD lDn sM mgawWXnI wh epa eQte x M CsV lhT",
                "drZ uGM lLnzpx aKLV YV BAtMGBf q XJLoeCM Cpgc GDg A tqjEn l zonjT yAKM ee gG bYFczOn bgWVw ii",
                "UvfGBnT ZhxLRn aQLZTbPA Gh XnPhyQ bWmXlw ry dxiWU WjahVXHZu ZRXoaUcH zKLQ NUWuSsZ rURF NLf n OO jYajYan Ien WKpcrLg PcXSq",
                "PRVNRTm obxV pxhd ZJMAPhb NFDk o lTnqGU Trg khYlhX dJ J ZWvkYw EKBgpJdo q YD Lbp rCsPUMfs YPz vVTM t",
                "OWx jAU R jcwOQ A cNmTFi Jczs xn SJKGzLv d cCOBOf BkNmC e gJ EaXzVi iQNC CYExER N XAXHVl IgPK",
                "Xoc JtprTZFB ERemzGyDm UOHwU GdKak CLvV WLjwGvd Kq XFx eKOiZa zVWXhEW IjjaW kJoaf zo tfBczL Hn LB gQosXFq Usk RXsAhj"
        };
        String[] finalAnswer = new String[]{
               "ZW ywAcsz YSKqFE u tFC s REv qUEQatM Q OrW NGTXZP LMNKu HyLL hGnSvBj GS GJ gD fK EhiDUp",
               "ZzhJF Xd wQkADIm w VpQ V UqxxBga U qpb PoETLtjU ozH LAjBKS kUSQIb jyK HZRaJjH hwHbndZL Ht HBaYbeS hb GzfS",
               "ZtefZ ZHPSB YZMINV Yov v uwK SXE sKNoAEeH rZYODt QwgzT PgSmR oEGGrYEA jgitCX iDOcQgcd Hct GRERA e AsvkGZ AoHT",
               "ZT ydyEtwMv vnu TlCc TEWH Scfj P OeQZbDQz M LgPyJ lbjfNIi KOmDsS ihlouYD ihKkaVAZ glbKP fPhImW FeRO F BDFMl B",
               "YHo xAnPW uU URS SqYY SbK NqNgha NjAL mjNsnjpfH lXjBBOEs K iSITJ HUr H flYJmYwv eiafRg E DO bN Ag",
               "ZcNU zbhg wzwF VUdr VnNJN UNTNnqXT TuH toXtfng PwNhX plVUPoL mLKuHYx M LhE JVY jq hCjvkJV Gb fHPcdl Dk C",
               "YvWUeABx XhwrPwQNI TPvArSdk SpcjWN SFJG Rn rJWSTT qk qHE mnndY MmhXXp jkdVy HsE gkkP Eex dz DxBB ccUaBn BWyi",
               "Y xrAxv XiwD wfeo vq Udsd SEAfuP rRxhZd rMWXd QXXEK nu ng im iEzU FUQXLAZl eWdN ej cJKAHKXv cDmS ax",
               "Yyt UZLvN ShtiUEz sFIiMUB QVct p OQ MshLb LF KDEMhr jxrf JBv huhMbu GpHUs FLvLvz FlCWh D bSFOVuYy bDVvLEPp ac",
               "zSf zL XMN vH V Sai S rC Qld QeWkZGr OcvrVC MJGZU mewMf Kmi GSzN ddAu dc C BS",
               "xCQsPTrT UlLwH sBwaP s ruRNmN rE R QqS ooufpYI nI MyxTi Kp j h FcZWpf DozINe DhdN CiRNSxM AJ",
               "zK z z xFHxBxiY vSezYq v UVR tz tIaTPM Sf QqNQcv qaERvi Ot mM mcstwOFOg hPHNY GyXs fEyvhT Bx",
               "ZG xNVzHLz wlaP TPeklTpx SGIYVQ rIpJYI qibE q PLQj PidTCPK PaBpXuq o mdm lr lQMVn iql ioAg Hrf GXxBVXn ftfgO",
               "ZWKl vspH v QSJOQS ptmPfQ PRextVD PGur morqR Lg kTDGtMn jiGULIhJ gX g eN cI bZwV bxc beNGzk a",
               "zZAuUmXu zrfgS x wh T sM RvkOAO mgawWXnI M lhT lDn kD Ix I hUrTNbC gjObM eQte epa CsV",
               "zonjT YV yAKM XJLoeCM uGM tqjEn q lLnzpx l ii gG GDg ee drZ Cpgc bYFczOn bgWVw BAtMGBf aKLV A",
               "ZRXoaUcH zKLQ ZhxLRn XnPhyQ WKpcrLg WjahVXHZu UvfGBnT ry rURF PcXSq OO NUWuSsZ NLf n jYajYan Ien Gh dxiWU bWmXlw aQLZTbPA",
               "ZWvkYw ZJMAPhb YPz YD vVTM Trg t rCsPUMfs q pxhd PRVNRTm obxV o NFDk lTnqGU Lbp khYlhX J EKBgpJdo dJ",
               "xn XAXHVl SJKGzLv R OWx N Jczs jcwOQ jAU iQNC IgPK gJ EaXzVi e d CYExER cNmTFi cCOBOf BkNmC A",
               "zVWXhEW zo Xoc XFx WLjwGvd Usk UOHwU tfBczL RXsAhj LB Kq kJoaf JtprTZFB IjjaW Hn gQosXFq GdKak ERemzGyDm eKOiZa CLvV"
        };
        MagicStrings magicStrings = new MagicStrings("");
        for (int i = 0; i < 20; i++) {
            if (tempAnswer[i].split(" ").length != 20) continue;
            callSetSs(magicStrings, inputs[i]);
            assertEquals(tempAnswer[i], callGetStrings(magicStrings));
            callSortSs(magicStrings);
            assertEquals(finalAnswer[i], callGetStrings(magicStrings));
        }
    }

    @Test
    public void test_09 () {
        // Reject the code with wrong way to deal with arg
        int[] priority = new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26
        };
        MagicStrings magicStrings = new MagicStrings(priority, "");
        String ans = "z yy y xa w v u t s r q pp o n m l kk j i h g f e de dd c b aa";
        String ss = "aa b c dd de e f g h i j kk l, m n o pp q r[ s t u, v w xa yy y z";
        callSetSs(magicStrings, ss);
        callSortSs(magicStrings);
        assertEquals(ans, callGetStrings(magicStrings));
        for (int i = 0; i < 26; i++) {
            priority[i] = 26-i;
        }
        callSortSs(magicStrings);
        assertEquals(ans, callGetStrings(magicStrings));
    }

    @Test
    public void test_10 () {
        // Test for the constructor which initial the ss array
        // Totally test through the method PrintS()
        String[] ss = new String[]{
                "Qz OkA quuB X MJ R uQ NG C) .qV.",
                "PkTR jzt .y] w\\s[ gm xQ jw H,z \\F SU\\",
                "f) I WgGh O qb .} .Kkx qz[ ]c jOR",
                "FmH (VYd tuCP ) C cYc lQw WC [Cxm u|DN",
                "c PBsx H F}kH aN yBBc l,} hoV P[J xYi",
                "b O..V M(q sDsd }R RKX kiib Fvk I Y}",
                "OeN] BJ d]D f\\s uC v tq J[Ef S )r",
                "k( pjfJ ba[B ah E\\ rkS x v j} wP`u",
                "sgWk XKhs YTP n}gc CZj xpz xA V [Bi ar",
                "w Gd Jx( uq`Y lJ. VXf }W x.p V Bq",
                "B nOw fGzD Ff BD D(] k {cA, gi Ggxs",
                "](. \\ nd Fxm VRP tj yJ ]qZ XDf ]bFy KWm",
                "BNC ,Oft wfj g[E u DZ PASR lyES uVYI B",
                "a|Y k fZfD \\ ] Fy N t, I hB",
                ". Koh {vSp ]i, uZ(j Bi {hE OcGQ I |",
                "J DrFg Eq} g D s k uBaU vQ f",
                "gRA w yNf {n Pw} S( Lf ouI p`E \\q[",
                "f\\ Eu lvNQ j g|f xcqL y( wMm| vf( Ljl",
                "HQX f rKj kVq )c AIO R[ iGas d YuNr",
                "\\uB GGb Dwp Mc{T ei y. {m (..K uhc Gg"
        };
        String[] an = new String[]{
                "Qz OkA quuB X MJ R uQ NG C qV",
                "PkTR jzt y ws gm xQ jw Hz F SU",
                "f I WgGh O qb Kkx qz c jOR",
                "FmH VYd tuCP C cYc lQw WC Cxm uDN",
                "c PBsx H FkH aN yBBc l hoV PJ xYi",
                "b OV Mq sDsd R RKX kiib Fvk I Y",
                "OeN BJ dD fs uC v tq JEf S r",
                "k pjfJ baB ah E rkS x v j wPu",
                "sgWk XKhs YTP ngc CZj xpz xA V Bi ar",
                "w Gd Jx uqY lJ VXf W xp V Bq",
                "B nOw fGzD Ff BD D k cA gi Ggxs",
                "nd Fxm VRP tj yJ qZ XDf bFy KWm",
                "BNC Oft wfj gE u DZ PASR lyES uVYI B",
                "aY k fZfD Fy N t I hB",
                "Koh vSp i uZj Bi hE OcGQ I",
                "J DrFg Eq g D s k uBaU vQ f",
                "gRA w yNf n Pw S Lf ouI pE q",
                "f Eu lvNQ j gf xcqL y wMm vf Ljl",
                "HQX f rKj kVq c AIO R iGas d YuNr",
                "uB GGb Dwp McT ei y m K uhc Gg"
        };
        MagicStrings magicStrings;
        for (int i = 0; i < 20; i++) {
            if (an[i].split(" ").length != 10) continue;
            magicStrings = new MagicStrings(ss[i]);
            assertEquals(an[i], callGetStrings(magicStrings));
            ss[i] = "";
            assertEquals(an[i], callGetStrings(magicStrings));
        }
    }

    @Test
    public void test_11 () {
        String[] list = new String[]{
                ".IzvZ\\CRff (J7XF`TzWk 02{\\nkTsAl FQOrD.vK6) {XfA8hZqx, QfUbLt7abb |{VYea1)a( 5xwG8Qrf9j H79d7[K[NH YiTvyAPTi,",
                "(5)Z[Dr33i n{((Y.4CyY MGI,z}PUT{ vkPDZSY6.{ vcO.FfJ[Xa (NqmsytqH[ ls\\Vm8(Ax5 ca\\1L6TXc3 wkT(|Lk7AZ OadZIh`Cg,",
                "DUD5J}{7Y6 )b\\{6MkZYh i,1Ql1TM9Q j{1FjZVLrw jYm2\\9Skst O|([bbcrjB xIY[osxH2[ |DRKsr1ouG ,qwk.cv5kW 1UT)DZCbuk",
                "GduC|xN2ao O}A3w.Hp[\\ [Fe17F[7d] c4w.Pyp,fj Uimjxes9V] iJRHU7)].\\ Ol[27h`GVd lPVT0|f}wA (1e1ySSYxE JI0(SBVICY",
                "0`b3q5FJfF VScusYd8k6 o}n|]L[rnr ErYoSwr}3e P8BPDTFMjO Hg99hrD\\Z[ |}n0{DE.Si nCjxcqNU}X oY`L,albxp sV9k0vx}Wo",
                "9{`hH1,fhW OODDLdIhxv 5Ri9HQ.R`T `ZoWh5R[\\T txKqsG)kOe OtU3G9U.np Iq.z)p}Kmj 3Ed})fiBno bwC\\ABVbGf 5lAYnWg2D,",
                "hQbDJoNn9a 8\\3GXf(0rE pUw)ucQn)q )q.}H9REUy 0Hf8m|a2FG Q,9`uP0qgF 2JeMNZEhxS `q2qwe29ly IR06)n,]zm mIH]cKpW`Q",
                "Gz1MBqJWG7 0))MiWwnEX uA6MC4`G7} 7H0`Ap2Ceo 2dlX1C|B6A k3V(UGr0`1 Zmpn(BCv[o lktV04B[Df fL6QRe7Zv{ J[JL|GE]T6",
                "ydXUDe2,.2 3\\pk8(qc7r \\uzY\\HeYF{ ,U739Q(CjI cnSMpeyP7P Am9N(\\tOah zmPI}UjXlL tExwobz3l3 Q[aVmSzDn, kGK(,6hDT|",
                "aB8c5r{vFX uuQqAF783[ SRyR2]`S{1 UAqQjh\\v7Q bAuH0LA`dZ .Z3PQtE{.7 3KE|BOAuLU E0b11|vsIP ihQ8OcRDs8 WsVmWTFIQ3",
                "Z8f|m5kZ96 lk{gfeVgG4 AndL]h|n9q 7tDjiAzpY4 XL\\1Sh|DKz kcLtmP.WHU iKwxHVZf{b 4ON|[]j5.E MBE\\D53`{M FsH{ChXFAM",
                "Xf4(DmEj)y P9)8{U]3)M X0f.o3gq2c m.i{[4fsFj hbPda{XTG5 8)lF6.Pp\\x 2Q)IWYGrH4 ZP9Mv[\\1TW W0\\nfK1p,p KwKv}zK5oP",
                "lNLyjHpclS ox6Pm5Y{iJ b8lOADS7PU v68D)33RFI ]ImxYiwQfc sYAic0lB,z g|8oz7,j6E 3Pa\\z9gSwI SAdsyb.CD4 TK\\k0.,3F8",
                "bNl|87xJZ6 vrjjVqUk05 S7\\)Cnn46o {NJ1IOT.|x zg5M}6VGE` 94r4U{VM53 tq5Q{,W.Tu z{)Y8zRq|5 .N6,VnVaD2 WzfkmXXIt)",
                "l7PykRQ7MT UEx}DYAvxm 5{Riy`2kLn dkOkEnZmPv hf4p96ia3m pm(6)mj3FH ZBJid\\W7bj pR0vESaFV5 NJEvUsXvNK dFEVs8EA3Z",
                "Ce\\sjPh`A7 W`\\n}\\Hgq[ nHP2N}i0HP \\UcN{6lv1} lEygxy.aUd YKdGur)0m} P2[}SWSlh( brfwkyWI\\l H{aFV(Sx\\y qQrTYPN216",
                "2]z7\\yvL7} zGz}5wbBL, 6)SkbCTm5` PN[(B)mJMl qPSXe8MLIG tPoj1M66Ey b9eVJbHebM fb4jP72N4K |QpoJYPZX5 RfU7U2dFDo",
                "9}nTW}`4r( TFl4Qh6kKu or[8{WrD6y R,Y.TTMj05 EKGyHIPV4u BZ7xvdkIPc yM}VphRnlQ UWZ(toVwYd h}s8F,dvb. nRcX,wBxFF",
                "Tytm9ylwqN Jc(4KeoSys PBTDznsuGg sEOezENPyl EdPuuqCYy6 BVWYxUbwV[ 2yqm[\\8Zm. ftB66|j8gF )kfdr|`Vjl xo60eZtPyc",
                "TGVIg}3T}i O[.6QzJzx] tbf04BOXxz ,fcVPa)arG b).GRgor,} 311)YUlA3Z Z]ztu8YSoc LI0vWyzYMo )IiEl\\rzSH 2vD(hw\\TSg"
        };
        String[] tempAn = new String[]{
                "IzvZCRff JXFTzWk nkTsAl FQOrDvK XfAhZqx QfUbLtabb VYeaa xwGQrfj HdKNH YiTvyAPTi",
                        "ZDri nYCyY MGIzPUT vkPDZSY vcOFfJXa NqmsytqH lsVmAx caLTXc wkTLkAZ OadZIhCg",
                        "DUDJY bMkZYh iQlTMQ jFjZVLrw jYmSkst ObbcrjB xIYosxH DRKsrouG qwkcvkW UTDZCbuk",
                        "GduCxNao OAwHp FeFd cwPypfj UimjxesV iJRHU OlhGVd lPVTfwA eySSYxE JISBVICY",
                        "bqFJfF VScusYdk onLrnr ErYoSwre PBPDTFMjO HghrDZ nDESi nCjxcqNUX oYLalbxp sVkvxWo",
                        "hHfhW OODDLdIhxv RiHQRT ZoWhRT txKqsGkOe OtUGUnp IqzpKmj EdfiBno bwCABVbGf lAYnWgD",
                        "hQbDJoNna GXfrE pUwucQnq qHREUy HfmaFG QuPqgF JeMNZEhxS qqwely IRnzm mIHcKpWQ",
                        "GzMBqJWG MiWwnEX uAMCG HApCeo dlXCBA kVUGr ZmpnBCvo lktVBDf fLQReZv JJLGET",
                        "ydXUDe pkqcr uzYHeYF UQCjI cnSMpeyPP AmNtOah zmPIUjXlL tExwobzl QaVmSzDn kGKhDT",
                        "aBcrvFX uuQqAF SRyRS UAqQjhvQ bAuHLAdZ ZPQtE KEBOAuLU EbvsIP ihQOcRDs WsVmWTFIQ",
                        "ZfmkZ lkgfeVgG AndLhnq tDjiAzpY XLShDKz kcLtmPWHU iKwxHVZfb ONjE MBEDM FsHChXFAM",
                        "XfDmEjy PUM Xfogqc mifsFj hbPdaXTG lFPpx QIWYGrH ZPMvTW WnfKpp KwKvzKoP",
                        "lNLyjHpclS oxPmYiJ blOADSPU vDRFI ImxYiwQfc sYAiclBz gozjE PazgSwI SAdsybCD TKkF",
                        "bNlxJZ vrjjVqUk SCnno NJIOTx zgMVGE rUVM tqQWTu zYzRq NVnVaD WzfkmXXIt",
                        "lPykRQMT UExDYAvxm RiykLn dkOkEnZmPv hfpiam pmmjFH ZBJidWbj pRvESaFV NJEvUsXvNK dFEVsEAZ",
                        "CesjPhA WnHgq nHPNiHP UcNlv lEygxyaUd YKdGurm PSWSlh brfwkyWIl HaFVSxy qQrTYPN",
                        "zyvL zGzwbBL SkbCTm PNBmJMl qPSXeMLIG tPojMEy beVJbHebM fbjPNK QpoJYPZX RfUUdFDo",
                        "nTWr TFlQhkKu orWrDy RYTTMj EKGyHIPVu BZxvdkIPc yMVphRnlQ UWZtoVwYd hsFdvb nRcXwBxFF",
                        "TytmylwqN JcKeoSys PBTDznsuGg sEOezENPyl EdPuuqCYy BVWYxUbwV yqmZm ftBjgF kfdrVjl xoeZtPyc",
                        "TGVIgTi OQzJzx tbfBOXxz fcVPaarG bGRgor YUlAZ ZztuYSoc LIvWyzYMo IiElrzSH vDhwTSg"
        };
        String[] an = new String[]{
                "YiTvyAPTi xwGQrfj XfAhZqx VYeaa QfUbLtabb nkTsAl JXFTzWk IzvZCRff HdKNH FQOrDvK",
                        "ZDri wkTLkAZ vkPDZSY vcOFfJXa OadZIhCg nYCyY NqmsytqH MGIzPUT lsVmAx caLTXc",
                        "xIYosxH UTDZCbuk qwkcvkW ObbcrjB jYmSkst jFjZVLrw iQlTMQ DUDJY DRKsrouG bMkZYh",
                        "UimjxesV OlhGVd OAwHp lPVTfwA JISBVICY iJRHU GduCxNao FeFd eySSYxE cwPypfj",
                        "VScusYdk sVkvxWo PBPDTFMjO oYLalbxp onLrnr nDESi nCjxcqNUX HghrDZ ErYoSwre bqFJfF",
                        "ZoWhRT txKqsGkOe RiHQRT OtUGUnp OODDLdIhxv lAYnWgD IqzpKmj hHfhW EdfiBno bwCABVbGf",
                        "QuPqgF qqwely qHREUy pUwucQnq mIHcKpWQ JeMNZEhxS IRnzm hQbDJoNna HfmaFG GXfrE",
                        "ZmpnBCvo uAMCG MiWwnEX lktVBDf kVUGr JJLGET HApCeo GzMBqJWG fLQReZv dlXCBA",
                        "zmPIUjXlL ydXUDe uzYHeYF UQCjI tExwobzl QaVmSzDn pkqcr kGKhDT cnSMpeyPP AmNtOah",
                        "ZPQtE WsVmWTFIQ uuQqAF UAqQjhvQ SRyRS KEBOAuLU ihQOcRDs EbvsIP bAuHLAdZ aBcrvFX",
                        "ZfmkZ XLShDKz tDjiAzpY ONjE MBEDM lkgfeVgG kcLtmPWHU iKwxHVZfb FsHChXFAM AndLhnq",
                        "ZPMvTW Xfogqc XfDmEjy WnfKpp QIWYGrH PUM mifsFj lFPpx KwKvzKoP hbPdaXTG",
                        "vDRFI TKkF sYAiclBz SAdsybCD PazgSwI oxPmYiJ lNLyjHpclS ImxYiwQfc gozjE blOADSPU",
                        "zYzRq zgMVGE WzfkmXXIt vrjjVqUk tqQWTu SCnno rUVM NVnVaD NJIOTx bNlxJZ",
                        "ZBJidWbj UExDYAvxm RiykLn pRvESaFV pmmjFH NJEvUsXvNK lPykRQMT hfpiam dkOkEnZmPv dFEVsEAZ",
                        "YKdGurm WnHgq UcNlv qQrTYPN PSWSlh nHPNiHP lEygxyaUd HaFVSxy CesjPhA brfwkyWIl",
                        "zyvL zGzwbBL tPojMEy SkbCTm RfUUdFDo qPSXeMLIG QpoJYPZX PNBmJMl fbjPNK beVJbHebM",
                        "yMVphRnlQ UWZtoVwYd TFlQhkKu RYTTMj orWrDy nTWr nRcXwBxFF hsFdvb EKGyHIPVu BZxvdkIPc",
                        "yqmZm xoeZtPyc TytmylwqN sEOezENPyl PBTDznsuGg kfdrVjl JcKeoSys ftBjgF EdPuuqCYy BVWYxUbwV",
                        "ZztuYSoc YUlAZ vDhwTSg TGVIgTi tbfBOXxz OQzJzx LIvWyzYMo IiElrzSH fcVPaarG bGRgor"
        };
        MagicStrings magicStrings;
        for (int i = 0; i < 20; i++) {
            magicStrings = new MagicStrings(list[i]);
            assertEquals(tempAn[i], callGetStrings(magicStrings));
            magicStrings.sortSs();
            assertEquals(an[i], callGetStrings(magicStrings));
        }
    }

    @Test
    public void test_12 () {
        String str = "o)y3YY1KHf dXl,9v6qJg n,P9fiOXPi R[ij\\QMC\\H yekRxDuo}z WhoCV62Qgz 2OKWghOcrM l58H|iruP7 `k9oSzBbgH FPdf.N3`1N 137QzXYN[x SX(OHNbzQa iutrOzsxxp ]0oNzT(jhx zoHz(wgHR8 u,a{h5`obU y8bLL)aD.n NlVPAs`FO6 fCGx{2N)AR .0DxWiVWnp 3eg0]]\\Ly{ USjCt9\\[7} NtFt(lAsF\\ aGcZ[v4`Pa `eGb{Y6vk` YklP9fCGpl yE33`R,6Yx 0b8ZLCJEUo h3zhToY5|V tuKDCr|cxm A`[9XW}PEO ]e]HPvCGhS G`Ko,wjnq7 [qQj6PSa34 aY`lGp7YnA sAiA`Dqtt, OF0Bj,1pVw kYqSF8XEXR BRp2mhOdK2 L1ZGXLNGyI 9geaKA|EU( qp2na(ZGsV SMxN).4i.M MHGnaP}URK D8`edep(kL 1,KWY7.K]5 TNL2HxuZ0W bQX20oZblu HyqNDhNM)A xy3}zJS.hR P,TW{vly.c 8cd47qhL]D DISRF}tVKS YbJ)fByUTK `Ia9]CA6[l PA5Sa9l|fz hx8mX74e{U wYlU8bQ5Sg rRooA}Y)hm zSI5Zn0hnP i\\6Rlkm,(, (VaedPY47Y vdn3WGSvX8 U6iulx7D.c KB.n9}.W{o [eUa5Xx4T5 M.9lZhyWI2 2vRWxOg}|u kL]U7BIpC] Jq2[S|(}9v 1uhMWLPy)H sWuHnz)suT PmbrEE(kuG pv)[KMoGq9 DAKEts9i9, (sWCf\\8518 0f]7VMXCD, vcG4YI6T`b sz4P,n1lTY `5ztgQmQ[F nvTt8mG\\uK \\4O3YFrRh) 6|MxViM,eG e.onEvBD33 0zMzh4ZhA\\ VAjK4GIYul 7,h(O,aY9g 1T{YO,RT}D 9sE8t5f(vv lXKPpvNcwu RN.6YlJBfc K2ZxhtqpFl ,D)QWt\\vEL ]K\\TqtSX8M oPU8{EyzOf ZE9Kb6|4zm sSA5HgUjIg N`{0l,TIwj O1Tim}MP.v oXxCBT\\2HZ 8J8f\\p6c,K KiGyG6j6Oo QL8T0Eux0L 0J(2`rJ]qN 4qKtWeb(\\L 61dE94iXZx s8,T]DOiTE ae8RlP\\l5Y lfdKq8s\\m. 6aY[bHNQ70 VlfFcI|qeG \\}bXm8,pfB j8W,L5pKTc P`31dYzw6V tCBSXRKW], QYRtt5o,3d SVjj{ZzdPB m(ZGsEl[9M rBf65]}e0b 3,[jyOsfQ9 y3tXIae9tU ,XACFhJ\\Ly 1ZtfsAzY`W SKwZ`z9Pic ,9PGFys|xE eoQGofFZc8 Rgse8k9Jxs Z0nfT,DAEz 3cBGGx.[iR Kv{arQ4AqW oSh{A|\\9UI )aaesBwQl5 GpzqH2PKZW \\FnpG3L|KZ 8YaK1[.4)m U6DApek0Cf 1s77]9voqI }J7GL57NFi [ppL49`GYH L3JstcR[JE bU]BtnhYsz 0]|vg4(n.2 {qmh.B8g6g a,sV,G.[re }OpjoNIL}o c1TAYBpObz Dn`g)1Yy8G J]O1gDq4zF S3Fu2Bq.,) OeLyo{usQT qz7u1npEuM jtqgApf}zj A[)mNTGLSe pL7zMt.p)Z o|Z9aFv5IG NDS[Rwu6UG 1nLNVuYzE0 tQuEF3hbiE m9XaaiOO(\\ )GF2yPOBov izq39BHgNe \\SLVkYB8DO gBQCp,c4(L KMkXE[pwPe AReP)I[D0} v2[}hCr8qt 2Cz{Sno`X5 9vmFc{)44S 0cx7Wb5DG] 8{kVsw[3Kw 7fX}nnE6]L q[]]tgn99d 4fz(q((zKl 8s7wsGK5iN n1G9spAGbQ 5)b7}v|PQ8 Hllp|Yfr{a HVP8QI\\Iz) 7.h0vf0KzU J2lHEXwl`t WdX5dq}sSC cTDUeLHfmp sQxDRA95FW UcZ34AiJ|u N{C6dkiY22 RCzCYu5)ap Ii\\fOBJx5W SKfUMc\\.yT b264.mJOiT p6aUwSbLL3 jFifd5yd1N rz83GIbl21 txtl|sUsQT O`rlh4nd1X yGscG3cpbm Q}F`r)OkF4 ad8lQC(|5F QWO`gFObk4 \\nxNU,X0.n {beea727`9 vpRvMw8x6t Fkm5IUF20Y P|Y075bb|o cFW8LOObhX Z5MJPKkFn3 K1hrYhL8K3 erGqULHkCu q(Hm6v|AnZ )f}pcUlwHO aXGaJr2Ek\\ ubasq2kiTE iTc5M[rDby XP5pqo.0Hw ASp7VK0rse KcO(LgDpqS BSH,l29vV] I)eO0\\qyd8 Of|G45X,Y1 {{e2n6QCe7 iR.hH}PdN. E\\gf]M\\nZ9 pC[v|Nn}2L d\\HP`{sNKp M)eviQZx0G Yl,KN3I,um NA0)Zpzi1c lkky`4Ea0q FRRVjLo6VK ava26bQPOk |9qu.pRE]i 2K,epzgisd B0,m5AP.[8 ,0EHSnsXEG UqOoLJODLp J}eMccCd}` VHVhMCPnAQ 4AF6NMwn5g RcRm9e)Nuy \\[xJuue}z4 QLElIC`\\nZ MPg(lIBh|C cjxt2v9|ew ]CkjwJzXIF jPrER34oD8 mFOhlown8) qL7c,nd485 g]6fCe}E1o li([|1\\M0X \\ls,kxycc] Y4T5atG3]} e}jEhxKc5p }oO5r1ACY[ N1Zyo(fGLS C4wNpagKDg `oh9{JaJr{ 4ZiQT},17W s7W2L]LJDe m\\.UYVQc[} sNkUp|YB|k m8KFiFEPTC VTPh606fW2 6[aBg5xim9 FMVW[u.Oy( x9\\[4O4QNp ,68lTAb,sI qjuB0aKm2) VqwJkji2GD xL\\jLr[3CJ J\\FE3P)TU0 w\\`X}boE.8 ndX\\QV8[G\\ Kv9xHo(ThH 4b3O{hP3i3 Ntmtz4}Xt] g[UPGGx]5g ARwCiMvGw{ hGQvbKzmY. viJzXJWeW. y}Il3X.(en uxs\\WOxBxc BB3{{Q]nI\\ WEtG.2KsQ[ yCkDFo.d]B fs`2K`8fj, jiYWuUhl\\f [KAPeAI[5N F4NhLKgF4A fj47}eu4m) uhmd[{p`4Y V]]PGIbgwV TrDv0Mt1k2 L0Q|R4Xisb oUmnZNsz`R LMCd2,O.yj rPB7DCMysO ao]qhYHvb4 D(`OoYHM1S ,}kv5VX5x` }k4)vYyNIu j7efGRA\\E8 WUKAz2{In) )Y0U.EcdGO 6|(|}U(o8s z`FyIvrVSZ pUJFfUm53[ tp`Tmz[Zxh kY(S4pzEPd fhxXO}.nw7 TNOJqs`g2r {bWPYc85tm hN`cv)X9gK DTI[)r\\D[{ 8bnWPu(cnc D74GqvpaEZ xcG4v8gWC] M\\wpTA,afk ,.X`d4`wGX L`ynBYDp1C Q|{jRq(F3I iwWRn{JYw3 [Z1qOwe2(O cFuTn7iyB` 9PVjXpiB\\w PR5f)9](zQ Kk17VzwGcJ Mgyr)HGB0Q ZfST6]jMq( 9)eZn5MM2U Ra0mCs{VLV 7CVP9ZPt94 )LSkm|BZUt o]WEdMbzAt jzj(QrZDZa bWrzQ|N)ur S,kHAyfB.w B]{azJ[n8T EK(RJv8AR5 Fh][t)IDL0 V6SiCr.oW, R85(zWHZ.2 OHoX2Cu9u| bcKLW,jr4f J2EC8w5CTd al0\\fDPPse 1VR{)yf|Ix BsrW8GRD|z pYukG1utO7 ymnjrDj,Qz 4DEjID(66| kU(kBv}rME 7XK2YiTr`b S)4U2)(q2e )xpy7bP(M6 ,kV1YP\\HB} Esb49[cNFk E8k)1T)Y6b is6M0cFE.N ARadvU[3,h 72)ec)}joD iDZ8SbAb6d 9](mlt}4Ty iaT|,Yy`L3 rT7bzU]QP| [vFFwQ]}SS 8pVETk|a8l yqCuhANhMv MR9FsKuw9f z[jly,Dv(L adE\\19R)`4 k).agHsFr0 }tfK[69aZM [jTJ0Q7thP {FOhn5pHjt Z{7z8{M0wY PzL4`cQt|s K.}CkCbT6n K{gVEsqYr. vuCD)jtH]Y jvc)UBPTqW St93bx|NmE ,4)1WabykX BG6|6H,Rbn x0V[|lD}Qp ll46gs|ncf 0EtcqYXcSC .VB8QLJ.uo vwnRXyvE|g LppSz2]UGW Tzve9{i4Di X5HapceTu| 3eXDABHfD6 ry4mH{Rkjz o`mV{pyL4d ]dEVAC5ZuF AGfnJ5CFKL GfWAdvVx(D 8\\6.y\\uFqs p(WW.[G[jo u7S|q0UO0[ ZVP4Yh}(wz e89e0q}si4 UOjR]Kk.0` `kvhJZ5hji EDVlj}vjwd KSwWIyq8e| Sy.YKUPQY4 {KbG3iA6Gd ]xrjYiPEL, sg3mT[g\\1y cCP6w.m9QK ,]mLqXI99O Uk7ykM\\Sfe {ZG9jwNbH\\ NrclJIXlLp [)b8}1G10V NdBPPoo9WU v\\vD1nlR20 )TTi8(hD.6 2C}0,}lac] u|kvIWVujR h}Ly0{POUQ a}s23I,rE3 eDsac)z5ae ljbcdUORUQ ldv2xvk3W. vWi6OOOr0N s2\\F,IKUHI uAtaJGdcc, BT.QLV(Qc8 Sw,vIO.uj} cJ}]T03AjU GVJflGM9A. UfAUZRyg(G rlg5UkDNxw jG9o2zJvlB rMLbYB]pdD MoN\\(uwG60 zUuT]1RZp6 rsAgXa\\6KI x9)HIp186} b936Sn[PdX CkWlv0JUPa vWhY,2t{be sujUCaRNQW .1)sOKVS{\\ x4rmMAW[Mi `\\2EgFaqMo dNHXtMM}Mz n[x87CpSjc 5T,D8zrPTy [RuBT0B6Wr Sv\\Fd7ki1h R([NKFUTVO g4m7wGa0nd KG5d5HgOmA sKbc}|]\\06 GREm3e))7i oK3[xgnD4z MT6rPh\\0d4 lRP,1zr]U5 ruN)543f9} 9PbhfQ05wH G|dMT{7.8w ]vY.kG1Bh. I8Y(C0{Te6 87c6xy]5vW gZLeRRN4rO ehd[K7X0IK |5Q)Sh}1(B 0FibJQJ[h. d1s52OcEMA {26SBc\\,u` 1)8VWjS|4d (gcDr|1Zo7 ueNCcM]WYF RsoJdkhLFL [8{AprzEMd U3KTJKa{Da G[cr`rcugi fF0RSonzuc 6haxvUkiqA .yORLbQ4hA .di4MR|fca 0abNdpT7`S aoZ9v`z]h7 )k}t{MabFf `(e2ExbA{a bsY]e[jGoR UBzGmwQ8Iu HPa,raZNUZ qRng[FTWSf `d]D.JvhD\\ J[(KCM1OQ[ bnsH)x`Zb] ASv.S]AdJi .wlo[g5DJR 122YY)1n{| Q0MLslZJhQ TTQ0qisUTb D|eNNlafNm \\rEZonhe6o .{flJ4Jixz xf,b)IuG7G )oMWr6CfZ, \\1\\R4ABonL Aflruxd|eb deU6D5RpWL dY.EYLJsAy N]MVP1e)nb QrRAMucQKp ZzAFocZLDU rmmWpue1Uf XDY1`RgK|( Z2c61bpTg0 B5EmGbVzPk }W|VvQ)]Md M{UGWazPVQ TQAfmcrsOK k)}PJTpfGD X{dwn88g\\c 3iJrm`6Oe3 [IEE7v1x2B Rqp9[BQ(1J XeDcBQg7d\\ E8{3QT27,3 6FFkKL6ogD [UP5DE80[M UWonXTa[K0 (KVUvN9IP\\ LkbA7XuCuq x,3JVeLy9W Ek8(iyvLpF qJq2pm6\\XU LFdwW8kBO7 De2J3,ncTi Tg}EZDK8hm EE6Cp}`VK7 8KrNbs,i`o PHABU9`hWM .`Us492|M2 K46U(OArkU [8FJ2Wmnm. SzINQnuCea KMg9h\\stW, aypV1Rk5\\4 ahKmXgUOji `66`snpLsx y886H4aI]j JRCSaD5782 ZbhR\\ie}a{ 2]RwARMtYJ 3[mIOXnaXe ZeLOs.iG)J ].e}ABtUyU KgJ9bCO4gy 9u}dJH|Y2F wJtS}DS`A5 fa7IPE{y.h JBAvPDfo(P h2Lm1.h8ug )AnF{{styf Iyl{pp5G8g Vuw2|nM}(. b{1L7VbjkV VG(lo4[Elw a`BNwmVZnO 6T4Q.cOJQY lOIX\\H,,Ld XUB]Lp|ulK qg9h65XwxF O)V(`2Pi6D MlfO3waNn` h}G}){{},F g3cQeJic53 }cm]Lo]}Cw ie0hbcZyRB }s7)jV5)lY u[2Ep8Q3G| DAd)D9,dL) atDF[z{7E{ wwm`A0S[2p TTAx{V5}NG {XV\\F2W|w7 (00Im9`nMW 5umpGyCanB XXZqoiX|M[ c92tqEbt[D ESEnLUyAJS ChYwgs{5sC Dwcfm)4tNS }RC{GtB}Wt g[8cKRlA}7 KuULMvyAJy qfJd{SyBn7 FIbDE4stG7 f{FpLxlK\\} {eXOY[qu36 C3Mt3g[)tY tP6.vW9ojV T2Q),uI[QO y9WiJk9OhK Ib4bIZf5ym Tl60`}vFgQ (1BfTo)(yG Y4BERZpr}w cm7cr(ci\\x nplDZbqnvm 4ocvLqyUVp Zw9FXtCDEH gCmzbSvlmN ,d\\zEf3YyN bg(N[1,lii 81O`ebrwdW Apahk[IejT 5jY{n`lL9W T3Y.cRUGTT zAaMKxUZ9h 8b9uH7c9r4 }yJA4iK,0D t7rc{rKRHV }c{j.PGOtk ..TOX8B[Ro d6dVQI0Thi 20R\\Oa9hO4 9ERgVa30r| 7Lxl7rta(6 mnsqAZ6RFG GfcbVcnGpK D.|}Iyz4\\x 2zW}bOit5} CU][hk0fCP xQ58bxrq\\H AYft5ZaM,i L0S7RD1Cp5 eR}fqZFdX7 W0aXhwVX{6 A{pK33jFiO eVKCq`qF8d H.EjI(,4NI bhUPU5S5Rf Oo4{A}PDZw 3DTfmG[ZuN WXw3Ln3R4[ (ErB\\FHy(K }KUfDspvWw c6U8q7[vD` TMXDbR(iDu yqyDCIKYJO up2P\\D2\\.D ziCZQGl]T[ Uy`RgC1Q.T 8[`pJetog3 O)E1q)uXhS gC9G7yGtt6 D)MJkQe4Yd hJTL(\\xnxj |hv,]FzL\\c 7s3i,aeD14 zMW}dwjhVI zGCFw0IK{Y |rkvLtaRMt RmqRLw`5do jRZfS.lBuU myx9af)Mhz TQj]LZ,e\\p CC6N7xfbbF gAcw2CvO]W So]ltl\\HD2 (CZY{L\\Bxn a.Nt17h\\Ux 0GQO{vVj2t Dw4c4xjr5D ldz9w3o8,r 7DIFBXyHWF 2|I{]a{a[4 (bB],[jO2m I8m8iede]h Xc[0z.(B8} jP,ohzR{yr }|cJB1eDbx U]7(go7`Hz uo56Jcwm05 jdez[TvYZ. (EFedtJ57P Qf){Go0(|I CX09ne|IpO cLgdOdyHND }Xkq.,cN}| ERqoED7Kyc ]7`VMOtEf. vu|]hkdWdJ Z9UWf{i3n3 lJGNi72tlR jLefD]rIqm c0lUJ69aZz }41[DkKTx[ HZY|kI|H6b kUgqc`E,mJ NsqX2K|J6| GtsRE\\l9kx n6(dG|N68) wfER]|G]h) Hg1eX[s,0d Ue6oX52cCN JTZ1YvRVLq 0cM\\TSgm4| 8C1eTw2`Xi 43p0CGHEi{ QeIZ5`kCuf i1PRL`tcnz 6U960(g{Vs `0z3HD3)dg UPU2b[)GrY Ckq8kZQq{n \\1kqI{|XGM 2Ph8CA7SHS dP7n}pBIMa NQs{.jHU[D NgNI`JMf{7 1kmQJu8z[R x.MQwIOM0k Q6j7}F9kH0 Cf)(g33Up9 heC3{GvfMM 7{O`syuZep 3unt4ATm`p R.dcf`NFzD V}ang[1ImC zlLaQxM(YT Qe5)2D{}Wc XM]yUA\\OTO }4X}LW4L[\\ Myr(NHN|AL 9hSGA`px21 21}miEqKXE yHJY9tJv0] HXn,nP}XQE t{QMzGnICe )16NTAACgz 1ZcOU)lSPv snKP}Y\\wxT KTJU|hQT1q ax(JF1PkOP ZlS3maU,lb ey7kNy0Rk. Z8yc`wWSHX 5{aUCh`q{z VlZ7TXuQpz t)q[D9JHTG Vh72w5xf|s XFSwE}LDx6 5rtyvLBV8. Zh1[2uE0\\Q id7Ze`5VXS v7IUVucbnM mBgK)Z`SH7 aX9UHtU`aG tD)IVB3Gb7 8aUK\\4VyTd Cq]`Vg8p`i W48,b2plQ{ RWU{w{LWuc VYTkH4,jTI 33`PA1n8vc (GtMPe)IMW TnNQIK7y.5 6a|[5Wv(V8 2ca(HZwqri F1krjYzf,m )[GHQ0VmDg w|faKOq|m9 zz890Aw|pD oW]|G5FsLb Iu]VD3INjf s8.5dPaE75 ppV1ZWe6dk fmT63wprp( yY]`Wt,iBn 5oFjeAgK8D IRXJ85meXh H(DQsYHuwC yViz`N7P)B b6sAd3u4{6 unWuFxa\\\\m j(wVhsQFjf LA5PCY|cjo e}YRfvpC8. 9mWLrCSxyz oNe9ql)vRT 4Pdv)dZyj2 CxIUL6}JTZ `1(4,e3jQf ,BMfuLm]2K C3Q1JM}XLB (nz.fjtSXZ yt[{6Pkb7e [Ne11EMi6` qYA.W0{a}s (3(FG..fQT 0(UVs3CK6Y iJx.h]gflU (lCUwHk\\DP .K\\89WmM61 qC4i72YIw7 OM]HGKD(EK (OeCWZsf9H rdOmjm9P14 x7zB4|eJYS },cKkDp}IX )a(Ac\\Bleg a,`)]R5Rpk vjagb7PE.) 0vW.SbZ8mq 0U\\ZOvxFRy ]\\txNn,L8f 1Qn]aOTtdH vBfTdqa[xM EhN[kZG0{y Vkb3F4UQE8 FZljUzl{|` tp2SQFhQO4 j0x{B50AK[ {FOWFP8x.l RG(mAl.U\\e IledAVaiMQ tDpG`q}|qi .XAb2T7(cA a\\j.CuPX3\\ ew8n57o.j7 {UKIC]J,lz E.tpCbH)Eo ZzyLs1m}a3 WnupSP\\2)G ISl{QB|eDZ |)wt))Q4q) LxaPXevj(e \\GjRc`{O8L rV9RejYzka oS)lY9ri1{ s]ok[aY3Vt Pek3)EV8us m|2\\pGKwDP YUDq2I4K7g (J\\y(7`YX9 W|EvVqjgcT wTHjzluLF| y(OG3]z[uU k3{p}Ls`zk 6p5[,Y0\\]v SZKwJvKUld LsM9\\\\93X| MvUnuQgRCw h6TjtsF]`A 7)85M2dPWF BR6qExTGm) A`DGEfjjH2 pJDE2FmN.N }DvHIV(E`f oySWU`84Wn mXDvm6NsWZ k\\N[QJfwHb SHu`4NQ`1J hrHEaj.bdd ledQNwjdII m)ba6wqU]h R}c1Tj)Atq rpaz.8C)TQ 9dkZBZqp4P 0uJ(atdNsH 0OaGktWr{c KhSOFvNIkT j}e)T\\3id[ m0.8k|yZg[ h{`2azrfRg MZ8CA]5jNT 25NsL9oE}9 LF6XYWr)MP x7Zc\\.UPBg Pq|0KGYbme x)b99`N7UB Or20LJCihU ZC]dqsWQP1 GTDq8j}JRd k,,J\\w(vkj d`([AyrrKQ RDSPbo{go8 KkD3ssQLT, RZhBqOJS,, PdLl(f4gni X3x{3oCxZ) O.s4kwBRaM VQG7l1CGyJ 07Dhp.4RB\\ Cz{XiRg}sn jVhFm9ke46 uizPGFLlqq UQJjZb\\VVS dSjbxxKqs3 31Alc}td9( }KHqHzl3o0 Re}\\]6abyQ WKilb4E6BX c`IDKLin]9 tyiDJzMqtr UAs[mc`SDE n95xF(S]52 bcm6[0t1up WL2`wy[WTF EqR5(lo)OV oGJA`kY3X7 U(Qg6KHkB6 Bk}.2K6tO| }1vUp9be9g lJxdizx148 a[b}TkE\\t2 [,8aCGfu15 KCexYDF2r, A5L5RLpso1 )P17)Xzmst XgPBTFwC1| Jzl,MaRecw FJwTF}rzig lM{b16yXFE hQ]5yBRtC9 YCe.YKvClc `}mU3w0|9e e8v}9p\\iE` 8eK78V2YID xfkKkmhWm` ,xZ9QKQwqk 7{QLkosEnz kaFAP9wA4\\ l[dGYfHX4Q i9CsrsuWt` iMI9S[ATsu ANOa\\|F7CO }|is6ET8s) bq]tx0G)]X \\r5c`2tb8W 97TcPy{kwd 6h9Jmw(VD{ O\\Fjh]ZcW3 qhF{S]CLQM BOATE\\Q.|i DiMic6CEcA [mnfH\\Hv8} j1QF(vOVfe WZhtXQN{sn ,(dD4KQoLA yqL`fC[wGF {T45YYhf4s hQMh{F5Vw4 6kE8nkf}N] DHRUIlK.S] MWXdJuve]u |cnGy99,`h \\NsgjINbqR |g2WmfqvaM Gp,7GW`iqa Nsky1LzObw gkdRicTEI6 tx{qC`GOYE }Xq3RWZH(z z]Wb}L)C,z qhCfT(`y|o 8Oz57KI72\\ }|1S8{Bjqa rjg1QOx9AU W4bV2ZVnUJ hp{]qO[igb KnaZjE1,gO FI6zJ[LN[q IoOpm)3ztL 2h.EIfKGAu 3B{N26rBtw .Ukpn]veSb 4xFbib}[v\\ jV}DYi5Grw IXA{ZL9YmU IEtZ|w1x]Q dxra.3uWt] IXYVNjH6Nz .Ae0TtT`hZ C.hfQTULo4 Ov(azA}]xx r\\7R5Sdkqu {GWgYXNc`} hQPFj\\gwSk fzq{U82cH\\ gdild6rp\\( 1Z(Oj]Bqfe cY9c9WCIQQ UY88}xWO\\\\ cJGMGZR[Gx gad{`{A5O| g0kdCM|x3j ICBecf4BQF XFIp\\fYeOh lRpG.bONtB eTgm.Fn6Qt";
        String an = "ZzyLsma ZzMwY zzAwpD ZzAFocZLDU ZycwWSHX ZwFXtCDEH zWbOit zWbLCz ZVPYhwz ZUWfin zUuTRZp ztgQmQF ZtfsAzYW zSIZnhnP ZqOweO ZOjBqfe zoHzwgHR ZnfTDAEz zMzhZhA zMWdwjhVI ZMJPKkFn ZlSmaUlb zlLaQxMYT zjlyDvL ZiQTW ziCZQGlT ZhuEQ zHDdg ZGjwNbH zGCFwIKY zFyIvrVSZ ZfSTjMq ZeLOsiGJ ZEKbzm ZcOUlSPv ZCdqsWQP ZcbpTg ZbhRiea zAaMKxUZh yYWtiBn YYn yWiJkOhK yVizNPB yuFqs YUEcdGO YUDqIKg ytXIaetU ytPkbe YTatG yqyDCIKYJO yqLfCwGF yqCuhANhMv yORLbQhA yOGzuU ymnjrDjQz YlKNIum YklPfCGpl yJAiKD yIlXen yHJYtJv yHaIj yGscGcpbm yERYx yekRxDuoz yCkDFodB YCeYKvClc ybLLaDn YbJfByUTK YBERZprw YaKm xZQKQwqk xZcUPBg xzBeJYS xyzJShR XXZqoiXM XxoCxZ xVlDQp XVFWw XUBLpulK xrmMAWMi xrjYiPEL XqRWZHz xQbxrqH xpybPM XPpqoHw xOQNp XMyUAOTO xMQwIOMk XLWL xLjLrCJ XKYiTrb XkqcN xJVeLyW xJuuez xHIp XHapceTu XgPBTFwC XFSwELDx xfkKkmhWm XFIpfYeOh xfbIuGG xFbibv XeDcBQgd XDYRgK Xdwngc XdwGX XczB xcGvgWC xbNUB XACFhJLy XAbTcA WZhtXQNsn wYlUbQSg WXwLnR wXboE wwmASp WVvQMd WUKAzIn wtQq wTHjzluLF WnupSPG WLwyWTF wlogDJR WKilbEBX wJtSDSA WhoCVQgz wfERGh wfaKOqm WEvVqjgcT WEtGKsQ WdXdqsSC WbVZVnUJ WbplQ WaXhwVX WabykX VYTkHjTI vYkGBh vWSbZmq vwnRXyvEg VWjSd vWiOOOrN vWhYtbe vvDnlR VuwnM vUpbeg vuhkdWdJ vuCDjtHY VTPhfW VSiCroW VRyfIx vRWxOgu VqwJkjiGD VQGlCGyJ vpRvMwxt VPGIbgwV VMOtEf vmFcS VlZTXuQpz VlfFcIqeG VkbFUQE vjagbPE vIUVucbnM viJzXJWeW Vhwxfs VHVhMCPnAQ vhCrqt vgn VGloElw vFFwQSS vdnWGSvX vcGYITb VBQLJuo vBfTdqaxM VangImC VAjKGIYul VaedPYY UZOvxFRy UYxWO UyRgCQT uxsWOxBxc UWonXTaK UVsCKY uSqUO UsM USjCt UqOoLJODLp UQJjZbVVS UQgKHkB UPUbGrY upPDD UPDEM Uos UOjRKk uoJcwm unWuFxam untATmp umpGyCanB UkykMSfe ukvIWVujR UKTJKaDa UkpnveSb UKICJlz uJatdNsH uizPGFLlqq UiulxDc uhMWLPyH uhmdpY UgVs UgoHz UfAUZRygG uEpQG UeoXcCN ueNCcMWYF udJHYF UDApekCf UcZAiJu UBzGmwQIu ubasqkiTE uAtaJGdcc UAsmcSDE uahobU TzveiDi TYYhfs TYORTD tyiDJzMqtr TYcRUGTT txtlsUsQT txqCGOYE txNnLf tuKDCrcxm TTQqisUTb TTihD TTAxVNG TrDvMtk trcrKRHV TQuIQO tQuEFhbiE tQMzGnICe TQjLZep tqDJHTG TQcOJQY TQAfmcrsOK tPvWojV tpTmzZxh tpSQFhQO TOXBRo TNOJqsgr TnNQIKy TNLHxuZW TMXDbRiDu TlvFgQ TgEZDKhm tfKaZM TDzrPTy tDpGqqi tDIVBGb TcPykwd tCBSXRKW szPnlTY SZKwJvKUld SzINQnuCea SyYKUPQY SXOHNbzQa SwvIOuj sWuHnzsuT swsGKiN sWLLJDe sWCf svoqI SVjjZzdPB SvFdkih SUqe sujUCaRNQW sTDOiTE StbxNmE sSAHgUjIg sQxDRAFW SoltlHD sOKVS sokaYVt snpLsx sNkUpYBk snKPYwxT SMxNiM SLVkYBDO SKwZzPic SkHAyfBw SKfUMcyT sKbc sjVlY siaeD SHuNQJ sgmTgy SFuBq sFIKUHI sEtfvv sdPaE SBjqa SBcu sAiADqtt RzWHZ RZhBqOJS rzGIbl rymHRkjz RWUwLWuc RwARMtYJ rVRejYzka ruNf RuBTBWr rtyvLBV rTbzUQP RsoJdkhLFL rsAgXaKI rRSdkqu rRooAYhm RqpBQJ rPBDCMysO rpazCTQ ROahO RNYlJBfc RNKFUTVO RmqRLwdo rmmWpueUf rMLbYBpdD rlgUkDNxw rkvLtaRMt rjgQOxAU RijQMCH RgsekJxs RGmAlUe rEZonheo ReabyQ RDSPbogo rdOmjmP RdcfNFzD RCzCYuap RcTjAtq rctbW RcRmeNuy RCGtBWt rBfeb RamCsVLV RABonL QzXYNx qzunpEuM QYRttod qYAWas QWOgFObk qupREi qtgnd QShB QrRAMucQKp qRngFTWSf qQjPSa qpnaZGsV QnaOTtdH QMLslZJhQ qmhBgg QLTEuxL QLkosEnz QLElICnZ qLcnd qKtWebL qjuBaKm QjRqFI qJqpmXU QjFkH qHmvAnZ qhFSCLQM qhCfTyo qghXwxF QFrOkF qfJdSyBn QfGoI QeIZkCuf QeDWc qCiYIw PzLcQts pYv pYukGutO PYbbo PXzmst pWWGjo pvKMoGq PVjXpiBw pVETkal pUJFfUm PTWvlyc PRfzQ PqKGYbme ppVZWedk ppLGYH PmbrEEkuG pLzMtpZ pJetog pJDEFmNN PhCASHS PHABUhWM PGFysxE PekEVus PdYzwV PdvdZyj PdLlfgni pCvNnL pCGHEi PbhfQwH paUwSbLL PASalfz PAnvc OzKI oZaFvIG oyYYKHf oySWUWn OYFrRh oXxCBTHZ oWGFsLb oWEdMbzAt OVPiD OvazAxx oUmnZNszR OTimMPv OsyuZep oSlYri OskwBRaM oShAUI OrLJCihU OrlhndX oPUEyzOf OpjoNILo oOrACY OoAPDZw oNzTjhx oNeqlvRT oMWrCfZ omVpyLd OMHGKDEK oKxgnDz OKWghOcrM OHoXCuu ohJaJr oGJAkYX OFjhZcW oFjeAgKD OfGXY OFBjpVw OEquXhS OeLyousQT OeCWZsfH OebrwdW ocvLqyUVp OaGktWrc NZyofGLS nzfjtSXZ nxNUXn nxFS nxCpSjc nvTtmGuK NtmtzXt NtFtlAsF NTAACgz NsqXKJ NsLoE NskyLzObw NsgjINbqR NrclJIXlLp NQsjHUD nplDZbqnvm nPfiOXPi NMVPenb NlVPAsFO NlTIwj nLNVuYzE nGspAGbQ NgNIJMf NeEMi ndXQVG NDSRwuUG ndGN NdBPPooWU NCdkiY NAZpzic mZGsElM MZCAjNT myxafMhz MyrNHNAL MxViMeG mXDvmNsWZ mXaaiOO MWXdJuveu MwpTAafk mWLrCSxyz MvUnuQgRCw mUYVQc mUwe MUGWazPVQ MTrPhd MRFsKuwf MPglIBhC mpGKwDP MoNuwG mnsqAZRFG mnfHHv MlZhyWI mltTy mLqXIO MlfOwaNn mkyZg mKFiFEPTC mIOXnaXe miEqKXE MHGnaPURK MgyrHGBQ mFOhlown MeviQZxG MdPWF mBgKZSH mbawqUh LZGXLNGyI LynBYDpC Lxlrta lXKPpvNcwu LxaPXevje lTAbsI LSRDCp LsMX lskxycc LSkmBZUt lRPzrU lRpGbONtB LQRXisb LppSzUGW lOIXHLd LMCdOyj lMbyXFE llgsncf lkkyEaq LkbAXuCuq lJxdizx LJstcRJE lJGNitlR ljbcdUORUQ liMX lHiruP LFXYWrMP LFdwWkBO lfdKqsm ledQNwjdII ldzwor ldvxvkW ldGYfHXQ lCUwHkDP LAPCYcjo KZxhtqpFl kYSpzEPd kYqSFXEXR KWYK KWmM kvYyNIu kVYPHB KvxHoThH kvVXx KVUvNIP kVswKw kvhJZhji KvarQAqW KuULMvyAJy KUOArkU kUkBvrME kUgqcEmJ KUfDspvWw KTqtSXM ktMabFf KTJUhQTq KSwWIyqe KrNbsio kqIXGM kpLszk kPJTpfGD koSzBbgH kNQJfwHb KnaZjEgO kmQJuzR KMkXEpwPe KMghstW kLUBIpC KkVzwGcJ KkDssQLT kJwvkj KiGyGjOo KhSOFvNIkT KhrYhLK KHqHzlo KgVEsqYr KgJbCOgy KGdHgOmA Kepzgisd kEnkfN KcOLgDpqS KCkCbTn KCexYDFr KBnWo KbGiAGd KAPeAIN kagHsFr kaFAPwA JzlMaRecw jzjQrZDZa JyYX jyOsfQ jYnlLW jxBAK jwVhsQFjf jWLpKTc jVhFmke jVDYiGrw jvcUBPTqW JTZYvRVLq jtqgApfzj jTJQthP jRZfSlBuU JrJqN JRCSaD JqSv jQFvOVfe jPrERoD jPohzRyr JOgDqzF JlHEXwlt jLefDrIqm JKCMOQ jiYWuUhlf jGozJvlB JGLNFi JfpcK jFifdydN JFEPTU jeTid JeMccCd jefGRAE JECwCTd jdezTvYZ JBAvPDfoP izqBHgNe IylppGg IYCTe IXYVNjHNz IXAZLYmU iwWRnJYw IuVDINjf iutrOzsxxp iTcMrDby isMcFEN ISlQBeDZ isETs IRXJmeXh iRlkm iRhHPdN iPRLtcnz IoOpmztL ImnMW iMISATsu Imiedeh IledAVaiMQ iJxhgflU iJrmOe IifOBJxW IEtZwxQ IeOqyd iehbcZyRB IEEvxB iDZSbAbd idZeVXS iCsrsuWt ICBecfBQF IbbIZfym iaTYyL IaCAl Iaa HZYkIHb hzhToYV HyqNDhNMA HXnnPXQE hxmXeU HVPQIIz hvFzLc hvfKzU hTjtsFA hSGApx hrHEajbdd hQyBRtC hQPFjgwSk hQMhFVw hpqOigb HParaZNUZ hOaYg hNcvXgK hLyPOUQ hLmhug HllpYfra hJTLxnxj hJmwVD hGQvbKzmY hGF HgeXsd HEjINI hEIfKGAu heCGvfMM HDQsYHuwC hazrfRg haxvUkiqA gZLeRRNrO gWmfqvaM GWgYXNc GVJflGMA gUPGGxg GtsRElkx GtMPeIMW GTDqjJRd GREmei GQOvVjt GpzqHPKZW GpGWiqa gmwGand GKowjnq gkdRicTEI gkdCMxj GjRcOL GHQVmDg GFyPOBov GfWAdvVxD gfCeEo GfcbVcnGpK geaKAEU GdMTw gdildrp Gcrrcugi gcQeJic gCmzbSvlmN gcKRlA gCGyGtt gcDrZo gBQCpcL gadAO gAcwCvOW fzqzKl fzqUcH FZljUzl fXnnEL fVMXCD fsKfj FRRVjLoVK FPdfNN fpcUlwHO FOWFPxl FOhnpHjt FnpGLKZ FNhLKgFA FMVWuOy fmTwprp flJJixz FkrjYzfm FkmIUFY FJwTFrzig FJWmnm fjeum FIzJLNq FibJQJh FIbDEstG fhxXOnw FhtIDL FGfQT fFRSonzuc fFpLxlK FFkKLogD fCGxNAR faIPEyh eZnMMU eYRfvpC eykNyRk eXOYqu eXDABHfD ewnoj evpiE eVKCqqFd eUaXxT EtpCbHEo eTgmFnQt EtcqYXcSC ESEnLUyAJS EsbcNFk ERqoEDKyc ERgVar erGqULHkCu eRfqZFdX ErBFHyK EQT EqRloOV eoQGofFZc eonEvBD enQCe eKVYID EkTYb EKRJvAR EkiyvLpF ejQf ejEhxKcp EHSnsXEG eHPvCGhS EhNkZGy ehdKXIK egLy EgfMnZ EgFaqMo eGbYvk EFedtJP eExbAa eeqsi EECpVK EDVljvjwd eDsaczae ecjoD eABtUyU dzEfYyN dYEYLJsAy DxWiVWnp dxrauWt dXlvqJg DwcxjrD DwcfmtNS DvHIVEf DTIrD DTfmGZuN dsOcEMA dSjbxxKqs DQWtvEL dPnpBIMa DOoYHMS dNHXtMMMz DngYyG DMJkQeYd dkZBZqpP DkKTx DIyzx DISRFtVKS diMRfca DiMicCEcA DIFBXyHWF DHRUIlKS dHPsNKp DhpRB DGqvpaEZ dEVACZuF deUDRpWL DeNNlafNm DeJncTi DEjID dEiXZx DedepkL ddVQIThi dDKQoLA dDJvhD dAyrrKQ DAKEtsi DAdDdL CZYLBxn CzXiRgsn CzSnoX cYcWCIQQ cxyvW cxWbDG CXneIpO CxIULJTZ CwNpagKDg CVPZPt cUqvD CUhkfCP ctqEbtD cTDUeLHfmp cTAYBpObz CqVgpi CQJMXLB cnGyh cMTSgm CMtgtY cmLoCw cmcrcix clUJaZz cLgdOdyHND Clac CkWlvJUPa CkqkZQqn cKkDpIX CkjwJzXIF cjxtvew cJTAjU cjPGOtk cJGMGZRGx cJBeDbx cIDKLin ChYwgssC ChfQTULo cFWLOObhX cFuTniyB CfgUp CeTwXi cdqhLD cCPwmQK CCNxfbbF cBGGxiR caHZwqri bZLCJEUo bXmpfB bWrzQNur bWPYctm bvPQ buHcr bUBtnhYsz BTQLVQc bsYejGoR BsrWGRDz bSnPdX BSHlvV bsAdu BRqExTGm BRpmhOdK bQXoZblu bqtxGX bOhPi BOATEQi bnWPucnc bnsHxZb BNrBtw bmJOiT BMfuLmK BmAP bLVbjkV BkKtO bhUPUSRf bGV bgNlii BGHRbn BfToyG BEmGbVzPk beea bcmtup bcKLWjrf BBQnI bBjOm BazJnT aypVRk aYlGpYnA AYftZaMi aYbHNQ AXWPEO aXUHtUaG axJFPkOP aXGaJrEk aWvV avabQPOk aUKVyTd aUChqz atDFzE ASvSAdJi asVGre ASpVKrse asIrE ARwCiMvGw aRRpk ARePID ARadvUh AprzEMd ApKjFiO ApahkIejT aoZvzh aoqhYHvb aNthUx ANOaFCO AnFstyf AmNTGLSe ALRLpso alfDPPse Alctd ajCuPX ahKmXgUOji AGfnJCFKL aGcZvPa AFNMwng Aflruxdeb AeTtThZ aeRlPlY adlQCF ADGEfjjH adER aCGfu abTkEt aBNwmVZnO abNdpTS aBgxim aaesBwQl aAcBleg";
        MagicStrings magicStrings = new MagicStrings(str);
        callSortSs(magicStrings);
        assertEquals(an, callGetStrings(magicStrings));
    }


    private Class<?> findMagicStrings() {
        try {
            return Class.forName("MagicStrings");
        } catch (ClassNotFoundException e) {
            fail("Cannot find class 'MagicStrings'. Please check the class name. Class 'MagicStrings' should not in a package");
            return null;
        }
    }
    private Field findPriority () {
        return findField(findMagicStrings(), "priority", int[].class, Modifier.PRIVATE);
    }
    private Field findSs () {
        return findField(findMagicStrings(), "ss", String[].class, Modifier.PRIVATE);
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

    private Method findSetPriority1 () {
        return findMethod(findMagicStrings(), "setPriority", Modifier.PUBLIC, void.class, int[].class);
    }
    private void callSetPriority1 (Object magicStrings, int[] priority) {
        try {
            findSetPriority1().invoke(magicStrings, priority);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
        }
    }
    private Method findSetPriority2 () {
        return findMethod(findMagicStrings(), "setPriority", Modifier.PUBLIC, void.class, String.class);
    }
    private void callSetPriority2 (Object magicStrings, String priority) {
        try {
            findSetPriority2().invoke(magicStrings, priority);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
        }
    }
    private Method findSetPriority3 () {
        return findMethod(findMagicStrings(), "setPriority", Modifier.PUBLIC, void.class, char.class, int.class);
    }
    private void callSetPriority3 (Object magicStrings, char c, int priority) {
        try {
            findSetPriority3().invoke(magicStrings, c, priority);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
        }
    }
    private Method findSetSs() {
        return findMethod(findMagicStrings(), "setSs", Modifier.PUBLIC, void.class, String.class);
    }
    private void callSetSs (Object magicStrings, String input) {
        try {
            findSetSs().invoke(magicStrings, input);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
        }
    }
    private Method findStringNum() {
        return findMethod(findMagicStrings(), "stringNum", Modifier.PUBLIC, int.class);
    }
    private int callStringNum(Object magicStrings) {
        try {
            return (Integer) findStringNum().invoke(magicStrings);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return 0;
        }
    }
    private Method findCompareString() {
        return findMethod(findMagicStrings(), "compareString", Modifier.PUBLIC, int.class, String.class, String.class);
    }
    private int callCompareString (Object magicStrings, String a, String b) {
        try {
            return (Integer)findCompareString().invoke(magicStrings, a, b);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return 0;
        }
    }
    private Method findSortSs() {
        return findMethod(findMagicStrings(), "sortSs", Modifier.PUBLIC, void.class);
    }
    private void callSortSs (Object magicStrings) {
        try {
            findSortSs().invoke(magicStrings);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
        }
    }
    private Method findGetStrings() {
        return findMethod(findMagicStrings(), "getStrings", Modifier.PUBLIC, String.class);
    }
    private String callGetStrings(Object magicStrings) {
        try {
            return (String) findGetStrings().invoke(magicStrings);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
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
