package labs.lab14;

import java.util.*;

class NameComparator implements Comparator<Student>{

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

class GradeComparator implements Comparator<Student>{
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

        students.sort(new NameComparator());
        System.out.println("sort by name:");
        printStudents(students);

        students.sort(new GradeComparator());
        System.out.println("sort by grade:");
        printStudents(students);

        Map<String, List<Student>> studentMap = new HashMap<>();
        for (Student stu : students) {
            String group = stu.getGroup();
            if (!studentMap.containsKey(group)) {
                List<Student> studentList = new ArrayList<>();
                studentMap.put(group, studentList);
            }
            studentMap.get(group).add(stu);
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<Student>> map : studentMap.entrySet()) {
            sb.append(map.getKey()).append(":");
            for (Student s : map.getValue()) {
                sb.append(s.getName()).append(",");
            }
            sb.setLength(sb.length() - 1);
            sb.append(System.lineSeparator());
        }
        System.out.println(sb);
    }

    public static void printStudents(List<Student> students){
        for (Student s: students){
            System.out.println(s);
        }
    }
}
