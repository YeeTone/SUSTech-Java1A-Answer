package Spring2023A4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BattleFieldTest {
    static class StandardBattleField {
        private StandardTrainer trainerA;
        private StandardTrainer trainerB;

        private int turn = 1;

        public StandardBattleField(StandardTrainer trainerA, StandardTrainer trainerB) {
            this.trainerA = trainerA;
            this.trainerB = trainerB;

            trainerA.summon();
            trainerB.summon();
        }

        public void battle(String skillOfA, String skillOfB) {
            var pokemonA = trainerA.getActivatePokemon();
            var pokemonB = trainerB.getActivatePokemon();
            var firstIsA = pokemonA.getSpeed() > pokemonB.getSpeed();
            var firstTrainer = firstIsA ? trainerA: trainerB;
            var secondTrainer = firstIsA ? trainerB: trainerA;

            var firstPokemon = firstIsA ? pokemonA : pokemonB;
            var secondPokemon = firstIsA ? pokemonB : pokemonA;
            var firstSkill = firstIsA ? pokemonA.getSkill(skillOfA) : pokemonB.getSkill(skillOfB);
            var secondSkill = firstIsA ? pokemonB.getSkill(skillOfB) : pokemonA.getSkill(skillOfA);

            if (firstPokemon.isAlive()) {
                var target = firstSkill.getType() == StandardSkill.Type.Attack ? secondPokemon : firstPokemon;
                firstPokemon.useSkillTo(target, firstSkill);
                firstSkill.setPP(firstSkill.getPP() - 1);
            }
            if (secondPokemon.isAlive()) {
                var target = secondSkill.getType() == StandardSkill.Type.Attack ? firstPokemon : secondPokemon;
                secondPokemon.useSkillTo(target, secondSkill);
                secondSkill.setPP(secondSkill.getPP() - 1);
            }

            if (!firstPokemon.isAlive()) firstTrainer.summon();
            if(!secondPokemon.isAlive()) secondTrainer.summon();

            turn ++;
        }

        /**
         * @return 0: no winner, 1: player1, 2: player2
         */
        public int checkWin() {
            boolean isLose = true;
            for (var poke : trainerA.getPokemons()) {
                if (poke.isAlive()) {
                    isLose = false;
                    break;
                }
            }
            if (isLose) return 2;

            isLose = true;
            for (var poke : trainerB.getPokemons()) {
                if (poke.isAlive()) {
                    isLose = false;
                    break;
                }
            }
            if (isLose) return 1;

            return 0;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Turn %d:\n" +
                            "Trainer %s's Pokemon %s\n" +
                            "Trainer %s's Pokemon %s\n",
                    turn - 1,
                    trainerA.getName(), trainerA.getActivatePokemon().toString(),
                    trainerB.getName(), trainerB.getActivatePokemon().toString()));

            switch (checkWin()) {
                case 1 -> sb.append(String.format("Winner: %s", trainerA.getName()));
                case 2 -> sb.append(String.format("Winner: %s", trainerB.getName()));
            }
            return sb.toString();
        }
    }

    static class StandardPokemon {
        private String name;
        private int maxHP;
        private int HP;
        private int attack;
        private int speed;
        private ArrayList<StandardSkill> skills;

        public StandardPokemon(String name, int maxhp, int attack, int speed, StandardSkill... skills) {
            this.name = name;
            maxHP = maxhp;
            HP = maxhp;
            this.attack = attack;
            this.speed = speed;

            this.skills = new ArrayList<>();

            for (StandardSkill skill : skills) {
                this.skills.add(new StandardSkill(skill.getName(), skill.getType(), skill.getPower(), skill.getPP()));
            }
//        this.skills = new ArrayList<>(List.of(skills));
        }

        public boolean isAlive(){
            return HP > 0;
        }

        public void setHP(int HP) {
            this.HP = Math.min(Math.max(0, HP), maxHP);
        }


        public void useSkillTo(StandardPokemon target, StandardSkill skill){
            if(skill.getPP()>0){
                switch (skill.getType()){
                    case Heal -> target.setHP(target.getHP()+ skill.getPower());
                    case Attack -> target.setHP(target.getHP() - this.getAttack() * skill.getPower());
                }
            }
        }

        public int getHP() {
            return HP;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAttack() {
            return attack;
        }

        public void setAttack(int attack) {
            this.attack = attack;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public ArrayList<StandardSkill> getSkills() {return skills;}

        public StandardSkill getSkill(String name){
            for (var skill:this.skills) {
                if(skill.getName().equals(name)){
                    return skill;
                }
            }
            return null;
        }

        public void setSkills(ArrayList<StandardSkill> skills) {this.skills = skills;}

        @Override
        public String toString() {
            return String.format("%s: %d/%d",this.name,this.HP,this.maxHP);
        }
    }

    static class StandardSkill {

        enum Type {Attack, Heal};
        private String name;
        private Type type;
        private int power;

        private int pp;
        private int maxPP;

        public StandardSkill(String name, Type type, int power, int pp) {
            this.name = name;
            this.type = type;
            this.power = power;
            this.pp = pp;
            this.maxPP = pp;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public int getPower() {
            return power;
        }

        public void setPower(int power) {

            this.power = power;
        }

        public int getPP() {
            return pp;
        }

        public void setPP(int pp) {
            this.pp = Math.min(maxPP, Math.max(0, pp));
        }
    }

    static class StandardTrainer {
        private final ArrayList<StandardPokemon> pokemons = new ArrayList<>();

        private String name;

        private StandardPokemon activatePokemon;

        public StandardTrainer(String name, StandardPokemon... pokemons) {
            this.name = name;
            this.addPokemon(pokemons);

            summon();
        }

        public void addPokemon(StandardPokemon... poke) {

            for (var p:poke) {
                if(this.getPokemons().size()>=6)
                    break;

                boolean valid_poke = true;
                for (var ppp: this.getPokemons()) {
                    if(p.getName().equals(ppp.getName())){
                        valid_poke = false;
                        break;
                    }
                }

                if(valid_poke&& this.getPokemons().size()<6)
                    this.getPokemons().add(p);
            }
        }

        public void removePokemon(String... name) {
            for (String n:name) {
                var poke = getPokemon(n);
                if (poke == null) continue;
                this.getPokemons().remove(poke);
            }
        }

        public boolean isAlive(String name) {
            var poke = getPokemon(name);
            if (poke == null) return false;
            return poke.getHP() > 0;
        }

        public boolean isAlive(StandardPokemon poke) {
            return poke.getHP() > 0;
        }

        public StandardPokemon getPokemon(String name) {
            for (var poke : this.getPokemons()) {
                if (poke.getName().equals(name)) return poke;
            }
            return null;
        }

        public StandardPokemon summon() {
            for (var poke : this.getPokemons()) {
                if (this.isAlive(poke)) {
                    activatePokemon = poke;
                    return poke;
                }
            }
            return null;
        }

        public StandardPokemon summon(String name) {
            if(this.isAlive(name)) {
                {
                    activatePokemon = getPokemon(name);
                    return activatePokemon;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public StandardPokemon getActivatePokemon() {
            return activatePokemon;
        }

        public ArrayList<StandardPokemon> getPokemons() {
            return pokemons;
        }
    }

    @Test
    void receiveSkill() {

        bf.battle("attack", "heal");

        assertEquals(0, bf.checkWin());

        assertEquals(10, pokemon2.getHP());

    }

    @Test
    void battle() {

        attack = new Skill("Beat", Skill.Type.Attack, 20, 10);
        heal = new Skill("Heal", Skill.Type.Heal, 40, 10);
        pokemon1 = new Pokemon("1", 50, 2, 9, attack, heal);
//        var pokemon12 = new Pokemon("1", 50, 2, 9, attack, heal);
//        var pokemon13 = new Pokemon("1", 50, 2, 9, attack, heal);
        pokemon2 = new Pokemon("2", 50, 1, 10, attack, heal);
//        var pokemon22 = new Pokemon("2", 50, 1, 10, attack, heal);
//        var pokemon23 = new Pokemon("2", 50, 1, 10, attack, heal);
        trainer1 = new Trainer("XiaoZhi", pokemon1);
        trainer2 = new Trainer("Paimon", pokemon2);

        bf = new BattleField(trainer1, trainer2);

        bf.battle("Beat", "Heal");
    }

    @Test
    void battleMorePokemon() {
        var cuphead = new Pokemon("cuphead", 100, 2, 1, attack, heal);
        var youla = new Pokemon("youla", 1, 1, 9, attack, heal);
        var zelda = new Pokemon("zelda", 1, 1, 9, attack, heal);

        trainer1 = new Trainer("XiaoZhi", cuphead);
        trainer2 = new Trainer("Paimon", youla, zelda);

        bf = new BattleField(trainer1, trainer2);

        bf.battle("attack", "attack");
        bf.battle("attack", "attack");

        assertEquals("""
                Turn 2:
                Trainer XiaoZhi's Pokemon cuphead: 60/100
                Trainer Paimon's Pokemon zelda: 0/1
                Winner: XiaoZhi""".trim(), bf.toString().trim());
    }

    @Test
    void battlePokemonSummon() {
        var cuphead = new Pokemon("cuphead", 100, 2, 1, attack, heal);
        var youla = new Pokemon("youla", 1, 1, 9, attack, heal);
        var zelda = new Pokemon("zelda", 1, 1, 9, attack, heal);

        trainer1 = new Trainer("XiaoZhi", cuphead);
        trainer2 = new Trainer("Paimon", youla, zelda);

        bf = new BattleField(trainer1, trainer2);

        trainer2.summon("zelda");
        bf.battle("attack", "attack");

        assertEquals("""
                Turn 1:
                Trainer XiaoZhi's Pokemon cuphead: 80/100
                Trainer Paimon's Pokemon youla: 1/1""".trim(), bf.toString().trim());
    }


    @Test
    void battleWithLimitedPP() {
        attack = new Skill("Beat", Skill.Type.Attack, 5, 2);
        heal = new Skill("Heal", Skill.Type.Heal, 40, 100);

        pokemon1 = new Pokemon("1", 50, 1, 9, attack, heal);
        pokemon2 = new Pokemon("2", 50, 1, 10, attack, heal);

        trainer1 = new Trainer("XiaoZhi", pokemon1);
        trainer2 = new Trainer("Paimon", pokemon2);

        bf = new BattleField(trainer1, trainer2);

        bf.battle("Beat", "Beat");
        bf.battle("Beat", "Beat");
        bf.battle("Beat", "Beat");


        assertEquals("""
                Turn 3:
                Trainer XiaoZhi's Pokemon 1: 40/50
                Trainer Paimon's Pokemon 2: 40/50""".trim(), bf.toString().trim());
    }

    @Test
    void multiTurnBattle() {
        // objects created by submitted code
        var pokemon11 = new Pokemon(
            "11", 50, 2, 9,
            new Skill("attack", Skill.Type.Attack, 8, 5),
            new Skill("heal", Skill.Type.Heal, 30, 1)
        );
        var pokemon12 = new Pokemon(
            "12", 65, 3, 7,
            new Skill("attack", Skill.Type.Attack, 9, 5),
            new Skill("thunderbolt", Skill.Type.Attack, 14, 1),
            new Skill("heal", Skill.Type.Heal, 25, 2)
        );
        var pokemon13 = new Pokemon(
            "13", 90, 1, 13,
            new Skill("attack", Skill.Type.Attack, 12, 10),
            new Skill("heal", Skill.Type.Heal, 15, 3)
        );

        trainer1 = new Trainer("XiaoZhi", pokemon11);
        trainer1.addPokemon(pokemon12);
        trainer1.addPokemon(pokemon13);
        var pokemon21 = new Pokemon(
            "21", 80, 1, 11,
            new Skill("attack", Skill.Type.Attack, 20, 5),
            new Skill("strong attack", Skill.Type.Attack, 28, 2),
            new Skill("heal", Skill.Type.Heal, 40, 1)
        );
        var pokemon22 = new Pokemon(
            "22", 75, 2, 10,
            new Skill("attack", Skill.Type.Attack, 9, 8),
            new Skill("heal", Skill.Type.Heal, 20, 2)
        );
        trainer2 = new Trainer("Paimon", pokemon21, pokemon22);
        bf = new BattleField(trainer1, trainer2);

        // objects created by standard code
        var stdPokemon11 = new StandardPokemon(
            "11", 50, 2, 9,
            new StandardSkill("attack", StandardSkill.Type.Attack, 8, 5),
            new StandardSkill("heal", StandardSkill.Type.Heal, 30, 1)
        );
        var stdPokemon12 = new StandardPokemon(
            "12", 65, 3, 7,
            new StandardSkill("attack", StandardSkill.Type.Attack, 9, 5),
            new StandardSkill("thunderbolt", StandardSkill.Type.Attack, 14, 1),
            new StandardSkill("heal", StandardSkill.Type.Heal, 25, 2)
        );
        var stdPokemon13 = new StandardPokemon(
            "13", 90, 1, 13,
            new StandardSkill("attack", StandardSkill.Type.Attack, 12, 10),
            new StandardSkill("heal", StandardSkill.Type.Heal, 15, 3)
        );

        var stdTrainer1 = new StandardTrainer("XiaoZhi", stdPokemon11);
        stdTrainer1.addPokemon(stdPokemon12);
        stdTrainer1.addPokemon(stdPokemon13);
        var stdPokemon21 = new StandardPokemon(
            "21", 80, 1, 11,
            new StandardSkill("attack", StandardSkill.Type.Attack, 20, 5),
            new StandardSkill("strong attack", StandardSkill.Type.Attack, 28, 2),
            new StandardSkill("heal", StandardSkill.Type.Heal, 40, 1)
        );
        var stdPokemon22 = new StandardPokemon(
            "22", 75, 2, 10,
            new StandardSkill("attack", StandardSkill.Type.Attack, 9, 8),
            new StandardSkill("heal", StandardSkill.Type.Heal, 20, 2)
        );
        var stdTrainer2 = new StandardTrainer("Paimon", stdPokemon21, stdPokemon22);
        var sbf = new StandardBattleField(stdTrainer1, stdTrainer2);

        // battle
        testOneTurn(bf, sbf, "attack", "strong attack");
        testOneTurn(bf, sbf, "heal", "attack");
        testOneTurn(bf, sbf, "heal", "attack");
        testOneTurn(bf, sbf, "attack", "attack");
        testOneTurn(bf, sbf, "thunderbolt", "strong attack");
        testOneTurn(bf, sbf, "attack", "heal");
        testOneTurn(bf, sbf, "heal", "heal");
        testOneTurn(bf, sbf, "attack", "attack");
        testOneTurn(bf, sbf, "attack", "attack");
        testOneTurn(bf, sbf, "heal", "attack");
        testOneTurn(bf, sbf, "attack", "attack");
        testOneTurn(bf, sbf, "attack", "attack");
        testOneTurn(bf, sbf, "heal", "attack");
        testOneTurn(bf, sbf, "attack", "attack");
        testOneTurn(bf, sbf, "attack", "attack");
        testOneTurn(bf, sbf, "attack", "attack");
        testOneTurn(bf, sbf, "heal", "heal");
        testOneTurn(bf, sbf, "heal", "heal");
        testOneTurn(bf, sbf, "attack", "attack");
        testOneTurn(bf, sbf, "attack", "attack");
        testOneTurn(bf, sbf, "attack", "attack");
        testOneTurn(bf, sbf, "attack", "attack");
        testOneTurn(bf, sbf, "attack", "attack");
    }

    void testOneTurn(BattleField bf, StandardBattleField sbf, String skA, String skB) {
        bf.battle(skA, skB);
        sbf.battle(skA, skB);
        assertEquals(sbf.toString().trim(), bf.toString().trim());
    }

    @Test
    void testToString() {
        bf.battle("attack", "heal");

        assertEquals(0, bf.checkWin());

        assertEquals(10, pokemon2.getHP());

        assertEquals("Turn 1:\n" +
                "Trainer XiaoZhi's Pokemon 1: 50/50\n" +
                "Trainer Paimon's Pokemon 2: 10/50", bf.toString().trim());

    }

    BattleField bf;
    Pokemon pokemon1;
    Pokemon pokemon2;
    Trainer trainer1;
    Trainer trainer2;
    Skill attack;
    Skill heal;

    @BeforeEach
    void setUp() {
        attack = new Skill("attack", Skill.Type.Attack, 20, 10);
        heal = new Skill("heal", Skill.Type.Heal, 40, 10);
        pokemon1 = new Pokemon("1", 50, 2, 9, attack, heal);
        pokemon2 = new Pokemon("2", 50, 1, 10, attack, heal);
        trainer1 = new Trainer("XiaoZhi", pokemon1);
        trainer2 = new Trainer("Paimon", pokemon2);

        bf = new BattleField(trainer1, trainer2);
    }
}