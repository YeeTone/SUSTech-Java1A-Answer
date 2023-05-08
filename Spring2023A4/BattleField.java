package Spring2023A4;

public class BattleField {
    private Trainer trainerA;
    private Trainer trainerB;
    private int turn = 1;

    public BattleField(Trainer trainerA, Trainer trainerB) {
        this.trainerA = trainerA;
        this.trainerB = trainerB;
        trainerA.summon();
        trainerB.summon();
    }


    public void battle(String skillOfA, String skillOfB) {
        Skill skillA = trainerA.getActivatePokemon().getSkill(skillOfA);
        Skill skillB = trainerB.getActivatePokemon().getSkill(skillOfB);

        Pokemon fasterPokemon =
                trainerA.getActivatePokemon().getSpeed() >=
                        trainerB.getActivatePokemon().getSpeed() ?
                trainerA.getActivatePokemon() :
                trainerB.getActivatePokemon();
        Pokemon slowerPokemon = fasterPokemon == trainerA.getActivatePokemon() ?
                trainerB.getActivatePokemon() :
                trainerA.getActivatePokemon();
        Skill fasterSkill = fasterPokemon == trainerA.getActivatePokemon() ?
                skillA : skillB;
        Skill slowerSkill = fasterSkill == skillA ? skillB : skillA;

        if (fasterSkill.getPP() > 0) {
            Pokemon target = fasterSkill.getType() == Skill.Type.Attack?
                    slowerPokemon: fasterPokemon;
            fasterPokemon.useSkillTo(target, fasterSkill);
            fasterSkill.setPP(fasterSkill.getPP() - 1);
        }

        if (slowerPokemon.isAlive() && slowerSkill.getPP() > 0) {
            Pokemon target = slowerSkill.getType() == Skill.Type.Attack?
                    fasterPokemon: slowerPokemon;
            slowerPokemon.useSkillTo(target, slowerSkill);
            slowerSkill.setPP(slowerSkill.getPP() - 1);
        }

        if (!trainerA.getActivatePokemon().isAlive()) {
            trainerA.summon();
        }

        if (!trainerB.getActivatePokemon().isAlive()) {
            trainerB.summon();
        }

        this.turn++;
    }

    public int checkWin() {
        if (trainerA.anyLive()) {
            if (trainerB.anyLive()) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (trainerB.anyLive()) {
                return 2;
            } else {
                return 0;
            }
        }
    }

    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(String.format("Turn %d:", turn - 1)).append("\n");
        b.append(String.format("Trainer %s's Pokemon %s", trainerA.getName(), trainerA.getActivatePokemon()))
                .append("\n");
        b.append(String.format("Trainer %s's Pokemon %s", trainerB.getName(), trainerB.getActivatePokemon()))
                .append("\n");
        switch (checkWin()) {
            case 1 -> b.append(String.format("Winner: %s", trainerA.getName()));
            case 2 -> b.append(String.format("Winner: %s", trainerB.getName()));
        }

        return b.toString();
    }

    public Trainer getTrainerA() {
        return trainerA;
    }

    public void setTrainerA(Trainer trainerA) {
        this.trainerA = trainerA;
    }

    public Trainer getTrainerB() {
        return trainerB;
    }

    public void setTrainerB(Trainer trainerB) {
        this.trainerB = trainerB;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

}
