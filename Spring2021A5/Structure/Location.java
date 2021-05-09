package Spring2021A5.Structure;

public enum Location {
    LycheePark("LP"), TeachingBuilding("TB");
    private String name;

    Location(String name) {
        this.name = name;
    }

    Location() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
