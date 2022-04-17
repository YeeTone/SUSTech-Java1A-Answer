package Spring2022A3;

public class Pokemon {
    private String name;
    private int hpBound;
    private int hp;
    private int atk;
    private int level;
    private int speed;
    private int rateAtk;
    private int rateHp;
    Skill skill;

    private boolean isNormal;
    private int currentCD;

    public Pokemon(String name, int hp, int atk, Skill skill, int level, int speed, int rateAtk, int rateHp) {
        this.name = name;
        this.hp = hp;
        this.hpBound = hp;
        this.atk = atk;
        this.skill = skill;
        this.level = level;
        this.speed = speed;
        this.rateAtk = rateAtk;
        this.rateHp = rateHp;
        this.currentCD = skill != null ? skill.getSkillCd() : -1;
    }

    public void initiate(){
        this.hp = this.hpBound;
        this.currentCD = skill != null ? skill.getSkillCd() : -1;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public int getAtk() {
        return atk;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void levelUP(int up) {
        this.atk += up * rateAtk;
        this.hp += up * rateHp;
        this.level += up;
    }

    public void learnSkill(Skill skill) {
        this.skill = skill;
    }

    public boolean attackAndIsKilled(Pokemon p) {
        int myAttack;
        if (this.skill == null) {
            isNormal = true;
            myAttack = this.atk;
        } else {
            currentCD--;
            if (currentCD == 0) {
                isNormal = false;
                myAttack = this.skill.getSkillAtk();
                currentCD = skill.getSkillCd();
            } else {
                isNormal = true;
                myAttack = this.atk;
            }
        }

        p.hp -= myAttack;
        return p.hp <= 0;
    }

    public boolean isNormal() {
        return isNormal;
    }

    public int getHpBound() {
        return hpBound;
    }

    public boolean isFaster(Pokemon p){
        return this.speed >= p.speed;
    }
}
