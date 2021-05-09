package Spring2021A5.Structure;

public class Student extends Person{
    public Student(){}
    public Student( String id , String name ) {
        super(id,name);
    }

    public boolean courseExist( String code , String name , CourseType type ){
        return false;
    }
    public boolean courseExist( Course course ){
        return false;
    }

    public boolean chooseCourse( Course course){
        return false;
    }

    @Override
    public boolean dropCourse( Course course ){
        return false;
    }
    @Override
    public boolean changeCourse( Course oldCourse1 , Course newCourse2 ){
        return false;
    }

    public String printSchedule(){
        return null;
    }

    public int getScheduleCourseNum(){
        return 0;
    }




}
