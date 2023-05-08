package Spring2023A4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainerTest {
    Skill s1 = new Skill("beat", Skill.Type.Attack, 20, 10);
    Skill s2 = new Skill("heal", Skill.Type.Heal, 20, 10);

    Trainer t;

    @Test
    void testInitCapacity() {
        var pokemon1 = new Pokemon("ala1", 15, 2, 5, s1, s2);
        var pokemon2 = new Pokemon("ala2", 15, 2, 5, s1, s2);
        var pokemondup = new Pokemon("ala2", 15, 2, 5, s1, s2);
        var pokemon3 = new Pokemon("ala3", 15, 2, 5, s1, s2);
        var pokemon4 = new Pokemon("ala4", 15, 2, 5, s1, s2);
        var pokemon5 = new Pokemon("ala5", 15, 2, 5, s1, s2);
        var pokemon6 = new Pokemon("ala6", 15, 2, 5, s1, s2);
        var pokemon7 = new Pokemon("ala7", 15, 2, 5, s1, s2);
        var pokemon8 = new Pokemon("ala8", 15, 2, 5, s1, s2);

        t = new Trainer("xiaozhi", pokemon1, pokemon2, pokemondup, pokemon3, pokemon4, pokemon5, pokemon6, pokemon7, pokemon8);
        assertEquals(6, t.getPokemons().size());
    }

    @Test
    void testInitSummon() {
        var pokemon1 = new Pokemon("ala1", 15, 2, 5, s1, s2);
        var pokemon2 = new Pokemon("ala2", 15, 2, 5, s1, s2);


        t = new Trainer("xiaozhi", pokemon1, pokemon2);
        assertEquals(pokemon1, t.getActivatePokemon());
    }

    @Test
    void testRemovePokemons() {

        var pokemon1 = new Pokemon("ala1", 15, 2, 5, s1, s2);
        var pokemon2 = new Pokemon("ala2", 15, 2, 5, s1, s2);
        var pokemon3 = new Pokemon("ala3", 15, 2, 5, s1, s2);
        var pokemon4 = new Pokemon("ala4", 15, 2, 5, s1, s2);
        var pokemon5 = new Pokemon("ala5", 15, 2, 5, s1, s2);
        var pokemon6 = new Pokemon("ala6", 15, 2, 5, s1, s2);
        var pokemon7 = new Pokemon("ala7", 15, 2, 5, s1, s2);
        var pokemon8 = new Pokemon("ala8", 15, 2, 5, s1, s2);

        t = new Trainer("xiaozhi", pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6, pokemon7, pokemon8);

        t.removePokemon("ala5", "ala6", "error");

        t.addPokemon(pokemon7, pokemon8);

        assertEquals(pokemon7, t.getPokemons().get(4));
        assertEquals(pokemon8, t.getPokemons().get(5));

    }

    @Test
    void testInitDuplicate() {
        var pokemon1 = new Pokemon("ala1", 15, 2, 5, s1, s2);
        var pokemon2 = new Pokemon("ala2", 15, 2, 5, s1, s2);
        var pokemondup = new Pokemon("ala2", 15, 2, 5, s1, s2);
        var pokemon3 = new Pokemon("ala3", 15, 2, 5, s1, s2);
        var pokemon4 = new Pokemon("ala4", 15, 2, 5, s1, s2);
        var pokemon5 = new Pokemon("ala5", 15, 2, 5, s1, s2);
        var pokemon6 = new Pokemon("ala6", 15, 2, 5, s1, s2);
        var pokemon7 = new Pokemon("ala7", 15, 2, 5, s1, s2);
        var pokemon8 = new Pokemon("ala8", 15, 2, 5, s1, s2);

        t = new Trainer("xiaozhi", pokemon1, pokemon2, pokemondup, pokemon3, pokemon4, pokemon5, pokemon6, pokemon7, pokemon8);
        assertEquals(pokemon3, t.getPokemons().get(2));
        assertEquals(pokemon6, t.getPokemons().get(5));
    }

    @Test
    void testIsAlive() {
        var pokemon1 = new Pokemon("ala1", 15, 2, 5, s1, s2);
        var pokemon2 = new Pokemon("ala2", 15, 2, 5, s1, s2);
        var pokemondup = new Pokemon("ala2", 15, 2, 5, s1, s2);
        var pokemon3 = new Pokemon("ala3", 15, 2, 5, s1, s2);
        var pokemon4 = new Pokemon("ala4", 15, 2, 5, s1, s2);
        var pokemon5 = new Pokemon("ala5", 15, 2, 5, s1, s2);
        var pokemon6 = new Pokemon("ala6", 15, 2, 5, s1, s2);
        var pokemon7 = new Pokemon("ala7", 15, 2, 5, s1, s2);
        var pokemon8 = new Pokemon("ala8", 15, 2, 5, s1, s2);

        t = new Trainer("xiaozhi", pokemon1, pokemon2, pokemondup, pokemon3, pokemon4, pokemon5, pokemon6, pokemon7, pokemon8);

        assertFalse(t.isAlive("abaaba"));
        assertTrue(t.isAlive("ala1"));
        pokemon1.setHP(-100);
        assertFalse(t.isAlive("ala1"));
    }

    @Test
    void testSummon() {
        var pokemon1 = new Pokemon("ala1", 15, 2, 5, s1, s2);
        var pokemon2 = new Pokemon("ala2", 15, 2, 5, s1, s2);
        var pokemon3 = new Pokemon("ala3", 15, 2, 5, s1, s2);
        var pokemon4 = new Pokemon("ala4", 15, 2, 5, s1, s2);
        var pokemon5 = new Pokemon("ala5", 15, 2, 5, s1, s2);
        var pokemon6 = new Pokemon("ala6", 15, 2, 5, s1, s2);
        var pokemon7 = new Pokemon("ala7", 15, 2, 5, s1, s2);
        var pokemon8 = new Pokemon("ala8", 15, 2, 5, s1, s2);

        t = new Trainer("xiaozhi", pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6, pokemon7, pokemon8);

        pokemon1.setHP(-100);
        pokemon2.setHP(-100);
        pokemon3.setHP(-100);
//        pokemon4.setHP(-100);
        pokemon5.setHP(-100);
//        pokemon6.setHP(-100);
        pokemon7.setHP(-100);
        pokemon8.setHP(-100);

        var poke = t.summon();

        assertEquals(pokemon4, poke);

        pokemon4.setHP(-100);

        poke = t.summon();

        assertEquals(pokemon6, poke);

    }

    @BeforeEach
    void setUp() {

    }

}