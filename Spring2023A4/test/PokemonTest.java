package Spring2023A4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTest {
    Pokemon pokemon = null;

    Skill s1 = new Skill("beat", Skill.Type.Attack, 20, 10);
    Skill s2 = new Skill("heal", Skill.Type.Heal, 20, 10);

    @Test
    void isAlive() {

        assertTrue(pokemon.isAlive());

        pokemon.setHP(-100);
        assertFalse(pokemon.isAlive());

        pokemon.setHP(100);
        assertTrue(pokemon.isAlive());
    }

    @Test
    void setHP() {
        pokemon.setHP(100);
        assertEquals(15, pokemon.getHP());

        pokemon.setHP(12);
        assertEquals(12, pokemon.getHP());

        pokemon.setHP(-100);
        assertEquals(0, pokemon.getHP());
    }

    @Test
    void useSkillTo() {
        Pokemon pokemonB = new Pokemon("aida", 50, 1, 3);
        pokemon.useSkillTo(pokemonB, s1);
        assertEquals(50 - 2 * 20, pokemonB.getHP());

        pokemon.useSkillTo(pokemonB, s1);
        assertEquals(0, pokemonB.getHP());

        pokemon.useSkillTo(pokemonB, s2);
        assertEquals(20, pokemonB.getHP());
    }

    @Test
    void getName() {
        assertEquals("ala", pokemon.getName());
    }

    @Test
    void setName() {
        pokemon.setName("heiheihei");
        assertEquals("heiheihei", pokemon.getName());
    }

    @Test
    void getAttack() {
        assertEquals(2, pokemon.getAttack());
    }

    @Test
    void setAttack() {
        pokemon.setAttack(100);
        assertEquals(100, pokemon.getAttack());
    }

    @Test
    void getSpeed() {
        assertEquals(5, pokemon.getSpeed());
    }

    @Test
    void setSpeed() {
        pokemon.setSpeed(100);
        assertEquals(100, pokemon.getSpeed());
    }

    @Test
    void testToString() {
        assertEquals("ala: 15/15", pokemon.toString());
    }

    @BeforeEach
    void setUp() {
        pokemon = new Pokemon("ala", 15, 2, 5, s1, s2);
    }

}