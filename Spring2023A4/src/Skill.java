package Spring2023A4;

public class Skill {
    enum Type{
        Attack, Heal
    }

    private String name;

    private Type type;

    private int power;
    private int PP;
    private int maxPP;

    public Skill(String name, Type type, int power, int PP) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.PP = PP;
        this.maxPP = PP;
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
        return PP;
    }

    public void setPP(int PP) {
        if(PP < 0){
            PP = 0;
        }

        if(PP > maxPP){
            PP = maxPP;
        }

        this.PP = PP;
    }

    public int getMaxPP() {
        return maxPP;
    }

    public void setMaxPP(int maxPP) {
        this.maxPP = maxPP;
    }

    public Skill cloneItself(){
        //s.setPP(this.getPP());
        return new Skill(name, type, power, PP);
    }
}
