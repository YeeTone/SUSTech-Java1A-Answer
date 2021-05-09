package Spring2021A5;

import java.util.Objects;

public class CourseTime {
    private Day day;
    private int time;//1 for 8:00-9:50, 2 for 10:20-12:10, 3 for 14:00-15:50, 4 for 16:20-18:10, 5 for 19:00-20:50

    public CourseTime() {
    }

    public CourseTime( Day day , int time ) {
        this.day = day;
        this.time = time;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass( ) != o.getClass( ) ) return false;
        CourseTime that = (CourseTime) o;
        return time == that.time && day == that.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash( day , time );
    }
}
