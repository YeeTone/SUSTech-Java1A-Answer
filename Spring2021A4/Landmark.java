package Spring2021A4;

public enum Landmark {
    TeachingBuilding(new Library[]{Library.Yidan, Library.Lynn, Library.LearningNexus}),
    ResearchBuilding(new Library[]{Library.Lynn, Library.Yidan, Library.LearningNexus}),
    TaizhouFloor(new Library[]{Library.Lynn, Library.Yidan, Library.LearningNexus}),
    AdministrativeBuilding(new Library[]{Library.Lynn, Library.Yidan, Library.LearningNexus}),
    SUSTechCenter(new Library[]{Library.Yidan, Library.Lynn, Library.LearningNexus}),
    Dormitory(new Library[]{Library.LearningNexus, Library.Yidan, Library.Lynn}),
    Playground(new Library[]{Library.Yidan, Library.LearningNexus, Library.Lynn});

    // The library item specified by smaller index in priority means smaller distance
    // between the library item and the landmark.
    private final Library[] priority;
    // TODO: add your constructor and other methods here

    Landmark(Library[] priority){
        this.priority=priority;
    }

    public Library[] getPriority() {
        return priority;
    }
}