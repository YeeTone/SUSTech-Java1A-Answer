package Fall2020A4;

import java.util.*;

public class ConcreteUniversity implements University{
    private final List<Student> students = new ArrayList<>();
    private final List<Course> courses = new ArrayList<>();
    private final List<PrerequisiteRelation> prerequisiteRelations = new ArrayList<>();
    private final List<Relation> studentSelectionRelation = new ArrayList<>();

    private final Map<Integer,Student> studentNumberRelationMap = new HashMap<>();
    private final Map<String,Course> courseNumberRelationMap = new HashMap<>();

    public static class PrerequisiteRelation {
        final List<Course> prerequisites= new ArrayList<>();
        Course course;
        int prerequisiteCount;

        public PrerequisiteRelation(Course c, int count){
            this.course = c;
            this.prerequisiteCount = count;
        }

        public PrerequisiteRelation(Course c){
            this.course = c;
            this.prerequisiteCount = 0;
        }

        public void addPrerequisite(Course c){
            this.prerequisites.add(c);
        }

        public Course getCourse() {
            return course;
        }
    }

    public static class Relation{
        Student student;
        Course course;
    }

    @Override
    public void addOneCourse(String courseInfo) {
        String[] split = courseInfo.split(" ");
        Course c = new Course();
        c.setCourseNumber(split[0]);
        c.setCourseType(CourseType.valueOf(Integer.parseInt(split[1])));
        c.setCredit(Integer.parseInt(split[2]));

        if(!courseNumberRelationMap.containsKey(split[0])){
            courses.add(c);
            courseNumberRelationMap.put(split[0],c);
        }
    }

    @Override
    public List<Course> getAllCourses() {
        return courses;
    }

    @Override
    public void addOneStudent(String studentInfo) {
        String[] split = studentInfo.split(" ");
        Student s;
        if(split.length==2){
            s = new ArtsStudent();
            s.setNumber(Integer.parseInt(split[0]));
            s.setCollege(Integer.parseInt(split[1]));
        }else {
            s = new ScienceStudent();
            s.setNumber(Integer.parseInt(split[0]));
            s.setCollege(Integer.parseInt(split[1]));
            ScienceStudent scs = (ScienceStudent) s;
            scs.setGeneralWeight(Double.parseDouble(split[2]));
            scs.setArtsWeight(Double.parseDouble(split[3]));
        }
        if(!studentNumberRelationMap.containsKey(s.getNumber())){
            studentNumberRelationMap.put(s.getNumber(),s);
            students.add(s);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public String showArtsANDScienceCount() {
        int art = 0, science = 0;
        for (Student s:students){
            if(s instanceof ArtsStudent){
                art++;
            }else if (s instanceof ScienceStudent){
                science++;
            }
        }
        return String.format("ARTS-%d-SCIENCE-%d",art,science);
    }

    @Override
    public void addCourseRelations(List<String> relations) {
        for(String rs:relations){
            String[] splitted = rs.split(" ");
            PrerequisiteRelation prerequisiteRelation;
            if(splitted.length==1){
                Course c = courseNumberRelationMap.get(splitted[0]);
                prerequisiteRelation = new PrerequisiteRelation(c);
            }else {
                Course c = courseNumberRelationMap.get(splitted[splitted.length-1]);
                int count = Integer.parseInt(splitted[splitted.length-2]);
                prerequisiteRelation = new PrerequisiteRelation(c,count);

                for (int i = splitted.length-3;i>=0;i--){
                    Course pre = courseNumberRelationMap.get(splitted[i]);
                    if(pre!=null){
                        prerequisiteRelation.addPrerequisite(pre);
                    }

                }
            }

            prerequisiteRelation.getCourse().getPrerequisiteRelation().add(prerequisiteRelation);
            this.prerequisiteRelations.add(prerequisiteRelation);
        }


    }

    @Override
    public boolean selectCourse(int studentNumber, String courseNumber) {
        for (Relation r:studentSelectionRelation){
            if(r.student.getNumber()==studentNumber && r.course.getCourseNumber().equals(courseNumber)){
                return false;
            }
        }


        Student s = studentNumberRelationMap.get(studentNumber);
        Course c = courseNumberRelationMap.get(courseNumber);
        if(s==null || c==null){
            return false;
        }
        List<PrerequisiteRelation> prs = c.getPrerequisiteRelation();
        //PrerequisiteRelation pr = c.getPrerequisiteRelation();


        for (PrerequisiteRelation pr:prs){
            int satisfied = 0;
            for(Course cn:pr.prerequisites){
                for (Relation selected:studentSelectionRelation) {
                    if (selected.student.getNumber() == studentNumber
                            && selected.course.getCourseNumber().equals(cn.getCourseNumber())) {
                        satisfied++;
                    }
                }
            }
            if(satisfied<pr.prerequisiteCount){
                return false;
            }
        }



        Relation r = new Relation();
        r.course =c;
        r.student =s;

        s.addCourse(c);
        studentSelectionRelation.add(r);

        return true;
    }

    @Override
    public List<Course> getCoursesOfOneStudentOrderByCourseNumber(int studentNumber) {
        List<Course> result = new ArrayList<>();
        for (Student st:students){
            if(st.getNumber()==studentNumber){
                result.addAll(st.getCourses());
            }
        }
        result.sort(Comparator.comparing(Course::getCourseNumber));

        return result;
    }

    @Override
    public boolean checkGraduateForOneStudent(int studentNumber) {
        for (Student s:students){
            if(s.checkGraduate()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void selectCourseByCollege(int college, String courseNumber) {
        List<Integer> studentList = new ArrayList<>();
        for (Student s: students){
            if(s.getCollege()==college){
                studentList.add(s.getNumber());
            }
        }

        for (int n:studentList){
            selectCourse(n,courseNumber);
        }
    }
}
