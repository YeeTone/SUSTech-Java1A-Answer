import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ConcreteUniversity implements University {
    private List<Student> students;
    private List<Course> courses;
    private List<Relation> relations;

    static class Relation {
        String prerequisites;
        String courseName;
        int count;
        final int prerequisitesCount;
        boolean hasPrerequisite;

        Relation(String info) {
            if (info.length() == 1) {
                hasPrerequisite = false;
                courseName = info;
                prerequisitesCount = 0;
            } else {
                hasPrerequisite = true;
                String[] components=info.split(" ");//"C D 1 E" -> ["C", "D", "1", "E"]
                int length=components.length; //4
                courseName=components[length-1]; // E
                prerequisitesCount=Integer.parseInt(components[length-2]); //1
                StringBuilder sb=new StringBuilder();
                for (int i = 0; i < length-2; i++) {
                    sb.append(components[i]);
                }
                prerequisites=sb.toString();// CD
            }
        }

        void copyCount() {
            this.count = this.prerequisitesCount;
        }

        @Override
        public String toString() {
            return hasPrerequisite ?
                    String.format("Name->%s, Pre->%s, PreCount->%d", courseName, prerequisites, prerequisitesCount)
                    : courseName;
        }
    }

    public ConcreteUniversity() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        relations = new ArrayList<>();
    }

    @Override
    public void addOneCourse(String courseInfo) {
        courses.add(new Course(courseInfo));
    }

    @Override
    public List<Course> getAllCourses() {
        return this.courses;
    }

    @Override
    public void addOneStudent(String studentInfo) {
        switch (studentInfo.split(" ").length) {
            case 2:
                students.add(new ArtsStudent(studentInfo));
                break;
            case 4:
                students.add(new ScienceStudent(studentInfo));
                break;
            default:
                throw new IllegalArgumentException("StudentInfo is wrong");
        }
    }

    @Override
    public List<Student> getAllStudents() {
        return this.students;
    }

    @Override
    public String showArtsANDScienceCount() {
        int artsCount = (int) students.stream().filter(s -> s instanceof ArtsStudent).count();

        return String.format("ARTS-%d-SCIENCE-%d", artsCount, students.size() - artsCount);
    }

    @Override
    public void addCourseRelations(List<String> relations) {
        relations.forEach(r -> this.relations.add(new Relation(r)));
    }

    public List<Relation> getCourseRelations() {
        return relations;
    }

    private Course numberToCourse(String courseNumber) {
        return courses.stream().
                filter(c -> c.getCourseNumber().equals(courseNumber))
                .collect(Collectors.toList()).get(0);
    }

    private void copyPreCourseCount(List<Relation> partRelations) {
        partRelations.forEach(Relation::copyCount);
    }

    @Override
    public boolean selectCourse(int studentNumber, String courseNumber) {
        // courseNumber -> course
        Course course = numberToCourse(courseNumber);
        // studentNumber -> student
        Student student = numberToStudent(studentNumber);

        //if student has selected the course, return false
        if (student.checkSelected(course)) {
            return false;
        } else {
            //if the course has prerequisite course
            if (hasPrerequisite(course)) {
                //get all relations about the select course
                List<Relation> currentCourseRelations = relations.stream()
                        .filter(r -> r.courseName.equals(course.getCourseNumber())).collect(Collectors.toList());
                //copy prerequisitesCount to count
                copyPreCourseCount(currentCourseRelations);

                //If find one selected course, minus the count.
                student.getSelectedCoursesNumber().forEach(currentCourse -> {
                    for (Relation r : currentCourseRelations) {
                        if (r.prerequisites.contains(currentCourse)) {
                            r.count--;
                        }
                    }
                });
                //if the selected courses are satisfied the prerequisites.
                if (currentCourseRelations.stream().noneMatch(r -> r.count > 0)) {
                    student.courseSelection(course);
                    return true;
                } else {
                    return false;
                }
            } else {//the course has no prerequisite course
                student.courseSelection(course);
                return true;
            }
        }

    }


    private boolean hasPrerequisite(Course course) {
        return relations.stream()
                .filter(r -> r.courseName.equals(course.getCourseNumber()))
                .allMatch(r -> r.hasPrerequisite);
    }

    private Student numberToStudent(int studentNumber) {
        return students.stream().filter(s -> s.getNumber() == studentNumber).collect(Collectors.toList()).get(0);
    }

    @Override
    public List<Course> getCoursesOfOneStudentOrderByCourseNumber(int studentNumber) {
        return numberToStudent(studentNumber).getCourses().stream().sorted(Comparator.comparing(Course::getCourseNumber)).collect(Collectors.toList());
    }


    @Override
    public boolean checkGraduateForOneStudent(int studentNumber) {
        return numberToStudent(studentNumber).checkGraduate();
    }

    @Override
    public void selectCourseByCollege(int college, String courseNumber) {
        students.stream().filter(s -> s.getCollege() == college).forEach(
                s -> selectCourse(s.getNumber(), courseNumber)
        );
    }

}
