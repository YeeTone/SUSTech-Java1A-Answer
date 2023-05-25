package labs.lab13;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class SortByName implements Comparator<Student>{

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

class SortByGrade implements Comparator<Student>{
    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o1.getGrade(), o2.getGrade());
    }
}

public class TestSort {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("01","ZhangSan",90));
        students.add(new Student("01","LiMing",95));
        students.add(new Student("03","XiaoLan",89));
        students.add(new Student("02","Wong",99));
        students.add(new Student("02","LiSi",80));

        students.sort(new SortByName());
        System.out.println("sort by name:");
        printStudents(students);

        students.sort(new SortByGrade());
        System.out.println("sort by grade:");
        printStudents(students);
    }

    public static void printStudents(List<Student> students){
        for (Student s: students){
            System.out.println(s);
        }
    }
}
