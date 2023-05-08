package Spring2023A4;

import java.util.ArrayList;

public class Pokemon {
    private String name;
    private int HP;
    private int maxHP;
    private int attack;
    private int speed;
    private ArrayList<Skill> skills;

    public Pokemon(String name, int maxhp, int attack, int speed, Skill... skills) {
        this.name = name;
        this.maxHP = maxhp;
        this.HP = maxhp;
        this.attack = attack;
        this.speed = speed;
        // Make a copy from the argument "skills" into your own "this.skills"
        this.skills = new ArrayList<>();
        for (Skill s: skills){
            this.skills.add(s.cloneItself());
        }
    }

    public boolean isAlive(){
        return this.HP > 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        if(HP < 0){
            HP = 0;
        }
        if(HP > maxHP){
            HP = maxHP;
        }

        this.HP = HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
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

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public void useSkillTo(Pokemon target, Skill skill){
        switch (skill.getType()){
            case Heal -> target.HP = Math.min(target.maxHP, target.HP + skill.getPower());
            case Attack -> target.HP = Math.max(0, target.HP - skill.getPower() * this.attack);
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %d/%d", name, HP, maxHP);
    }

    public Skill getSkill(String name){
        for (Skill s: skills){
            if(s.getName().equals(name)){
                return s;
            }
        }

        return null;
    }



}
