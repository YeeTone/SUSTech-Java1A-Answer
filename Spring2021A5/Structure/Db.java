package Spring2021A5.Structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Db {
    static List<Student> students = new ArrayList<Student>( ) {{
        add( new Student( "10000001" , "Zhao1" ) );
        add( new Student( "10000002" , "Qian2" ) );
        add( new Student( "10000003" , "Zhang3" ) );
        add( new Student( "10000004" , "Li4" ) );
        add( new Student( "10000005" , "Wang5" ) );
        add( new Student( "10000006" , "Sun6" ) );
        add( new Student( "10000007" , "Zhou7" ) );
        add( new Student( "10000008" , "Wu8" ) );
        add( new Student( "10000009" , "Zheng9" ) );
        add( new Student( "10000010" , "Feng10" ) );
        add( new Student( "10000011" , "Chen11" ) );
        add( new Student( "10000012" , "Chu12" ) );
        add( new Student( "10000013" , "Wei13" ) );
        add( new Student( "10000014" , "Jiang14" ) );
        add( new Student( "10000015" , "Shen15" ) );
        add( new Student( "10000016" , "Han16" ) );
    }};
    static Map<String, Teacher> teachers = new HashMap<String, Teacher>( ) {{
        put( "ZHANG Yuqun" , new Teacher( "30000001" , "ZHANG Yuqun" ) );
        put( "ZHANG Fengwei" , new Teacher( "30000002" , "ZHANG Fengwei" ) );
        put( "LIU Yepang" , new Teacher( "30000003" , "LIU Yepang" ) );
        put( "ZHANG Yu" , new Teacher( "30000004" , "ZHANG Yu" ) );
        put( "HU Chunfeng" , new Teacher( "30000005" , "HU Chunfeng" ) );
        put( "WANG Wei" , new Teacher( "30000006" , "WANG Wei" ) );
        put( "ZHU Yueming" , new Teacher( "30000007" , "ZHU Yueming" ) );
        put( "JIA Yanhong" , new Teacher( "30000008" , "JIA Yanhong" ) );
        put( "LIAO Qimei" , new Teacher( "30000009" , "LIAO Qimei" ) );
        put( "ZHAO Yao" , new Teacher( "30000010" , "ZHAO Yao" ) );
        put( "CHENG Jingde" , new Teacher( "30000011" , "CHENG Jingde" ) );
        put( "Adam" , new Teacher( "30000012" , "Adam" ) );
        put( "WEI Xuetao", new Teacher( "30000013", "Wei Xuetao"));
    }};
    static List<Building> buildings = new ArrayList<Building>( ) {{
        add( new Building( Location.LycheePark , 1 ) );
        add( new Building( Location.LycheePark , 2 ) );
        add( new Building( Location.LycheePark , 3 ) );
        add( new Building( Location.LycheePark , 4 ) );
        add( new Building( Location.LycheePark , 5 ) );
        add( new Building( Location.LycheePark , 6 ) );
        add( new Building( Location.TeachingBuilding , 1 ) );
        add( new Building( Location.TeachingBuilding , 2 ) );
    }};
    static List<Course> courses = new ArrayList<>( );

    public static void InitRooms() {
        buildings.get( 0 ).setRooms( new ArrayList<Classroom>( ) {{//LP#1
            add( new Classroom( 101 , 200 , buildings.get( 0 ) , CourseType.Lecture) );
            add( new Classroom( 102 , 200 , buildings.get( 0 ), CourseType.Lecture ) );
            add( new Classroom( 205 , 40 , buildings.get( 0 ), CourseType.Lecture ) );
            add( new Classroom( 206 , 40 , buildings.get( 0 ) , CourseType.Lecture) );
            add( new Classroom( 207 , 40 , buildings.get( 0 ), CourseType.Lecture ) );
        }} );

        buildings.get( 1 ).setRooms( new ArrayList<Classroom>( ) {{//LP#2
            add( new Classroom( 101 , 180 , buildings.get( 1 ), CourseType.Lecture ) );
            add( new Classroom( 102 , 180 , buildings.get( 1 ), CourseType.Lecture ) );
        }} );

        buildings.get( 5 ).setRooms( new ArrayList<Classroom>( ) {{//LP#6
            add( new Classroom( 401 , 60 , buildings.get( 5 ), CourseType.Lecture ) );
            add( new Classroom( 402 , 50 , buildings.get( 5 ), CourseType.Lab ) );
            add( new Classroom( 403 , 60 , buildings.get( 5 ), CourseType.Lecture ) );
            add( new Classroom( 404 , 50 , buildings.get( 5 ), CourseType.Lab ) );
            add( new Classroom( 405 , 60 , buildings.get( 5 ), CourseType.Lecture ) );
            add( new Classroom( 406 , 50 , buildings.get( 5 ), CourseType.Lab ) );
            add( new Classroom( 407 , 60 , buildings.get( 5 ), CourseType.Lecture ) );
            add( new Classroom( 408 , 50 , buildings.get( 5 ), CourseType.Lab ) );
            add( new Classroom( 409 , 50 , buildings.get( 5 ), CourseType.Lab ) );
        }} );

        buildings.get( 6 ).setRooms( new ArrayList<Classroom>( ) {{//TB#1
            add( new Classroom( 101 , 150 , buildings.get( 6 ), CourseType.Lecture ) );
            add( new Classroom( 102 , 150 , buildings.get( 6 ), CourseType.Lecture ) );
            add( new Classroom( 103 , 150 , buildings.get( 6 ), CourseType.Lecture ) );
            add( new Classroom( 104 , 150 , buildings.get( 6 ), CourseType.Lecture ) );
            add( new Classroom( 105 , 150 , buildings.get( 6 ), CourseType.Lecture ) );
            add( new Classroom( 106 , 150 , buildings.get( 6 ), CourseType.Lecture ) );
        }} );

        buildings.get( 7 ).setRooms( new ArrayList<Classroom>( ) {{//TB#2
            add( new Classroom( 201 , 80 , buildings.get( 7 ), CourseType.Lab ) );
            add( new Classroom( 202 , 80 , buildings.get( 7 ), CourseType.Lab ) );
            add( new Classroom( 203 , 80 , buildings.get( 7 ), CourseType.Lab ) );
            add( new Classroom( 204 , 80 , buildings.get( 7 ), CourseType.Lab ) );
            add( new Classroom( 205 , 80 , buildings.get( 7 ), CourseType.Lab ) );
            add( new Classroom( 206 , 80 , buildings.get( 7 ), CourseType.Lab ) );
        }} );
    }

    public static void InitCourse() {
        //InitRooms( );
        courses = new ArrayList<Course>();
        Course c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("WEI Xuetao"), 150, CourseType.Lecture, new CourseTime(Day.Monday, 2), buildings.get(0).getRooms().get(1));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("LIU Yepang"), 100, CourseType.Lecture, new CourseTime(Day.Monday, 2), buildings.get(1).getRooms().get(0));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("LIU Yepang"), 30, CourseType.Lab, new CourseTime(Day.Monday, 3), buildings.get(5).getRooms().get(1));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("WEI Xuetao"), 30, CourseType.Lab, new CourseTime(Day.Monday, 3), buildings.get(5).getRooms().get(5));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("ZHANG Fengwei"), 100, CourseType.Lecture, new CourseTime(Day.Monday, 4), buildings.get(0).getRooms().get(1));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("ZHANG Fengwei"), 30, CourseType.Lab, new CourseTime(Day.Monday, 5), buildings.get(5).getRooms().get(1));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("ZHANG Yuqun"), 100, CourseType.Lecture, new CourseTime(Day.Tuesday, 2), buildings.get(0).getRooms().get(1));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102B", "Introduction to Computer Programming B", "JavaB", teachers.get("ZHANG Yu"), 100, CourseType.Lecture, new CourseTime(Day.Tuesday, 2), buildings.get(1).getRooms().get(0));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS209A", "Computer System Design and Applications A", "DesiAppA", teachers.get("Adam"), 80, CourseType.Lecture, new CourseTime(Day.Tuesday, 3), buildings.get(1).getRooms().get(0));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("ZHANG Yuqun"), 30, CourseType.Lab, new CourseTime(Day.Tuesday, 3), buildings.get(5).getRooms().get(3));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("ZHU Yueming"), 30, CourseType.Lab, new CourseTime(Day.Tuesday, 3), buildings.get(5).getRooms().get(5));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102B", "Introduction to Computer Programming B", "JavaB", teachers.get("ZHANG Yu"), 30, CourseType.Lab, new CourseTime(Day.Tuesday, 3), buildings.get(5).getRooms().get(8));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS101A", "Introduction to Computer Science A", "IntroCSA", teachers.get("CHENG Jingde"), 40, CourseType.Lecture, new CourseTime(Day.Tuesday, 4), buildings.get(0).getRooms().get(2));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102B", "Introduction to Computer Programming B", "JavaB", teachers.get("LIAO Qimei"), 30, CourseType.Lab, new CourseTime(Day.Tuesday, 4), buildings.get(5).getRooms().get(1));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("JIA Yanhong"), 30, CourseType.Lab, new CourseTime(Day.Tuesday, 5), buildings.get(7).getRooms().get(3));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS101B", "Introduction to Computer Science B", "IntroCSB", teachers.get("CHENG Jingde"), 40, CourseType.Lab, new CourseTime(Day.Tuesday, 5), buildings.get(0).getRooms().get(2));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102B", "Introduction to Computer Programming B", "JavaB", teachers.get("HU Chunfeng"), 30, CourseType.Lab, new CourseTime(Day.Wednesday, 2), buildings.get(7).getRooms().get(0));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("WANG Wei"), 30, CourseType.Lab, new CourseTime(Day.Wednesday, 2), buildings.get(7).getRooms().get(3));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102B", "Introduction to Computer Programming B", "JavaB", teachers.get("LIAO Qimei"), 30, CourseType.Lab, new CourseTime(Day.Wednesday, 2), buildings.get(5).getRooms().get(1));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("JIA Yanhong"), 30, CourseType.Lab, new CourseTime(Day.Wednesday, 2), buildings.get(7).getRooms().get(5));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("ZHAO Yao"), 30, CourseType.Lab, new CourseTime(Day.Wednesday, 2), buildings.get(5).getRooms().get(1));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("HU Chunfeng"), 30, CourseType.Lab, new CourseTime(Day.Wednesday, 3), buildings.get(7).getRooms().get(0));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("WANG Wei"), 30, CourseType.Lab, new CourseTime(Day.Wednesday, 3), buildings.get(7).getRooms().get(3));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102B", "Introduction to Computer Programming B", "JavaB", teachers.get("LIAO Qimei"), 30, CourseType.Lab, new CourseTime(Day.Wednesday, 3), buildings.get(5).getRooms().get(1));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("ZHAO Yao"), 30, CourseType.Lab, new CourseTime(Day.Wednesday, 3), buildings.get(5).getRooms().get(5));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("JIA Yanhong"), 30, CourseType.Lab, new CourseTime(Day.Wednesday, 3), buildings.get(7).getRooms().get(5));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS104", "Introduction to Mathematical Logic", "MathLogic", teachers.get("CHENG Jingde"), 40, CourseType.Lab, new CourseTime(Day.Wednesday, 4), buildings.get(0).getRooms().get(3));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS108", "Introduction to Mathematical Logic (H)", "MathLogic", teachers.get("CHENG Jingde"), 40, CourseType.Lab, new CourseTime(Day.Wednesday, 5), buildings.get(0).getRooms().get(4));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("JIA Yanhong"), 30, CourseType.Lab, new CourseTime(Day.Wednesday, 5), buildings.get(7).getRooms().get(3));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("WANG Wei"), 30, CourseType.Lab, new CourseTime(Day.Thursday, 2), buildings.get(7).getRooms().get(5));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("HU Chunfeng"), 30, CourseType.Lab, new CourseTime(Day.Thursday, 2), buildings.get(5).getRooms().get(5));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("ZHU Yueming"), 30, CourseType.Lab, new CourseTime(Day.Thursday, 2), buildings.get(7).getRooms().get(3));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102A", "Introduction to Computer Programming A", "JavaA", teachers.get("WANG Wei"), 30, CourseType.Lab, new CourseTime(Day.Thursday, 3), buildings.get(7).getRooms().get(5));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

        c = new Course("CS102B", "Introduction to Computer Programming B", "JavaB", teachers.get("ZHU Yueming"), 30, CourseType.Lab, new CourseTime(Day.Thursday, 3), buildings.get(5).getRooms().get(5));
        courses.add(c);
        //c.getRoom().addCourse(c);
        c.getTeacher().createCourse(c);
        //System.out.println(c.getRoom().printSchedule());
        //System.out.println(c.getTeacher().printSchedule());

    }
    // search students from the list with keyword as id or name
    // suppose id and name are both unique
    public static Student GetStudent(String keyword){
        for (Student student : students){
            if (keyword.equals(student.getId()) || keyword.equals(student.getName()))
                return student;
        }
        return null;
    }

    public static Teacher GetTeacher(String keyword){
        for (Teacher teacher : teachers.values()){
            if (keyword.equals(teacher.getId()) || keyword.equals(teacher.getName()))
                return teacher;
        }
        return null;
    }

}
