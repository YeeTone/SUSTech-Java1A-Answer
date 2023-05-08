package Spring2023A4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkillTest {

    @Test
    void Skilltest() {
        Skill s = new Skill("skill1", Skill.Type.Heal, 20, 20);

        assertEquals("skill1", s.getName());
        assertEquals(Skill.Type.Heal, s.getType());
        assertEquals(20, s.getPower());
        s.setPP(-1);
        assertEquals(0, s.getPP());
        s.setPP(100000);
        assertEquals(20, s.getPP());
    }
}