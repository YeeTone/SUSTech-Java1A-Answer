package Fall2020A4;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        University university = new ConcreteUniversity();

        //reflect Student
        //reflect Course
        //reflect ConcreteUniversity

//        create course
        createCourse(university);

        //checkAddOneStudents
        checkAddOneStudents(university);

        //show arts and science students' count
        System.out.println(university.showArtsANDScienceCount());

        //import relations
        importRelations(university);

        //select course without prerequisites
        testSelectCourseWithoutPrerequisites(university, 1);

        //select course with only one prerequisites course
        testOnlyOnePrerequisitesCourse(university, 1);

        //select course with over one prerequisites courses
        testOverOnePrerequisitesCourse(university, 1);

        //select course with n choice m prerequisites courses
        testNChoiceMPrerequisitesCourses(university, 1);

        //check whether graduate
        checkWhetherGraduate(university);

        //add courses for college students
        selectCoursesByCollege(university);

        //test All graduate
        testAllGraduate(university);
    }

    public static void testAllGraduate(University university) {
        List<Integer> studentNumbers = university.getAllStudents().stream().map(Student::getNumber).sorted().collect(Collectors.toList());
        studentNumbers.forEach(s -> System.out.printf("%d -> %s\n", s, university.checkGraduateForOneStudent(s)));
    }

    public static void selectCoursesByCollege(University university) {
        university.selectCourseByCollege(1, "A");
        university.selectCourseByCollege(1, "B");
        university.selectCourseByCollege(1, "N");
        university.selectCourseByCollege(1, "K");
        university.selectCourseByCollege(1, "I");

        university.selectCourseByCollege(3, "M");
        university.selectCourseByCollege(3, "F");
        university.selectCourseByCollege(3, "C");
        university.selectCourseByCollege(3, "E");
        university.selectCourseByCollege(3, "K");
        university.selectCourseByCollege(3, "A");


        university.getAllStudents().stream().sorted(Comparator.comparing(Student::getNumber)).forEach(System.out::println);
        university.getCoursesOfOneStudentOrderByCourseNumber(4).forEach(System.out::println);
        university.getCoursesOfOneStudentOrderByCourseNumber(7).forEach(System.out::println);
    }


    public static void checkWhetherGraduate(University university) {
        System.out.println(university.checkGraduateForOneStudent(1));

        university.selectCourse(2, "A");
        university.selectCourse(2, "J");
        university.selectCourse(2, "I");
        university.selectCourse(2, "C");
        university.selectCourse(2, "B");
        university.selectCourse(2, "N");
        university.selectCourse(2, "N");
        university.getCoursesOfOneStudentOrderByCourseNumber(2).forEach(System.out::println);
        System.out.println(university.checkGraduateForOneStudent(2));
    }

    public static void testNChoiceMPrerequisitesCourses(University university, int studentNumber) {
        university.selectCourse(studentNumber, "H");
        university.selectCourse(studentNumber, "E");
        university.selectCourse(studentNumber, "K");
        university.getCoursesOfOneStudentOrderByCourseNumber(studentNumber).forEach(System.out::println);
        university.selectCourse(studentNumber, "I");
        university.selectCourse(studentNumber, "K");
        university.getCoursesOfOneStudentOrderByCourseNumber(studentNumber).forEach(System.out::println);
    }

    public static void testOverOnePrerequisitesCourse(University university, int studentNumber) {
        university.selectCourse(studentNumber, "B");
        university.getCoursesOfOneStudentOrderByCourseNumber(studentNumber).forEach(System.out::println);
        university.selectCourse(studentNumber, "C");
        university.selectCourse(studentNumber, "B");
        university.getCoursesOfOneStudentOrderByCourseNumber(studentNumber).forEach(System.out::println);
    }

    public static void testOnlyOnePrerequisitesCourse(University university, int studentNumber) {
        university.selectCourse(studentNumber, "J");
        university.getCoursesOfOneStudentOrderByCourseNumber(studentNumber).forEach(System.out::println);
    }

    public static void testSelectCourseWithoutPrerequisites(University university, int studentNumber) {
        university.selectCourse(studentNumber, "A");
        university.selectCourse(studentNumber, "F");
        university.getCoursesOfOneStudentOrderByCourseNumber(studentNumber).forEach(System.out::println);
    }

    public static void importRelations(University university) {
        List<String> relations = new ArrayList<>();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("resources/course_relation.csv"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String relation;
            while ((relation = bufferedReader.readLine()) != null) {
                relations.add(relation);
            }
            university.getAllCourses().forEach(System.out::println);
            inputStreamReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        university.addCourseRelations(relations);
    }

    public static void checkAddOneStudents(University university) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("resources/students.csv"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String studentsInfo;
            while ((studentsInfo = bufferedReader.readLine()) != null) {
                university.addOneStudent(studentsInfo);
            }
            inputStreamReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        university.getAllStudents().stream().sorted(Comparator.comparing(Student::getNumber)).forEach(System.out::println);
    }


    public static void createCourse(University university) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("resources/course.csv"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String courseInfo;
            while ((courseInfo = bufferedReader.readLine()) != null) {
                university.addOneCourse(courseInfo);
            }
            List<String> courses=university.getAllCourses().stream().map(Course::toString).collect(Collectors.toList());
            courses.stream().sorted().forEach(System.out::println);
            inputStreamReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
