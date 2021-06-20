import java.util.List;

public interface University {
    /**
     * Add a course into university
     * @param courseInfo course number, course type, course credit of the added course.
     *                   separated by spaces.<br>
     *                   the format of courseInfo: [number] [type] [credit] <br>
     *                   For example <br>
     *                   If courseInfo is "A 1 2", a course whose number is "A", type is 1 and credit is 2 will be added
     */
    public void addOneCourse(String courseInfo);

    /**
     * Get all courses that have been added into the university.
     * @return a list of courses
     */
    public List<Course> getAllCourses();

    /**
     * Add a student into university.
     * @param studentInfo student number, student's college of the added student.
     *                    separated by spaces.<br>
     *                    The format of studentInfo for ArtsStudent: [number] [college] <br>
     *                    The format of studentInfo for ScienceStudent: [number] [college] [generalWeight] [artsWeight]<br>
     *                    For example<br>
     *                    If studentInfo is "4 1", he/she is a ArtsStudent with number is 4 and college is 1
     */
    public void addOneStudent(String studentInfo);

    /**
     * Get all students
     * @return a list of students
     */
    public List<Student> getAllStudents();

    /**
     * Count number of students in Science and Art<br>
     * @return a string in format representing the number of students in two majors.<br>
     * The return type is ARTS-[arts count]-SCIENCE-[science count]
     */
    public String showArtsANDScienceCount();

    /**
     * add some relations of courses (prerequisite)
     * @param relations a list of strings representing the relations
     *                  let "[CN]" stand for Course Number,<br>
     *                  If a course has no prerequisite courses, the format of the relation will be "[CN]", for example,
     *                      if course A has no prerequisite courses, the relation is "A"<br>
     *                  Otherwise, the format of strings in relations list is "[CN1] [CN2] ... [CNn] [Count] [CN]",<br>
     *                      indicating that in this relation, selecting course "[CN]" requires Count courses in the n courses"[CN1]", ...,  "[CNn]",<br>
     *                      for example, "C D 1 E" means that if one student wants to select "E", he/she has to learn any 1 course in "C" and "D".<br>
     */
    public void addCourseRelations(List<String> relations);

    /**
     * Student with student number studentNumber selects the course with course number courseNumber
     * @param studentNumber the student number of the student who selects course
     * @param courseNumber the course number of the course the student wants to select
     * @return true for successful selection, false for otherwise
     */
    public boolean selectCourse(int studentNumber, String courseNumber);

    /**
     * Get all courses of a student
     * @param studentNumber the student number of the student to be queried
     * @return a list of courses selected by the student ordered by course number
     */
    public List<Course> getCoursesOfOneStudentOrderByCourseNumber(int studentNumber);

    /**
     * Check whether a student can graduate or not
     * @param studentNumber the student number of the student need to check
     * @return true for yes, false for no
     */
    public boolean checkGraduateForOneStudent(int studentNumber);

    /**
     * Select some course for all students in one college
     * @param college college number of the college that wants to select course for students
     * @param courseNumber course number of the course that the college wants to select
     */
    public void selectCourseByCollege(int college, String courseNumber);
}
