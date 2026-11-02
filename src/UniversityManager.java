
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.sql.SQLException;

public class UniversityManager {

    private List<Student> students = new ArrayList<>();
    private Set<Course> courses = new HashSet<>();

    private Map<Course, Instructor> assignments = new HashMap<>();
    private Map<Course, Set<Student>> enrollments = new HashMap<>();

    // Load data from PostgreSQL
    public void loadData() throws SQLException {

        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

        students.clear();
        courses.clear();
        enrollments.clear();

        for (Student student : studentDAO.getStudents()) {
            students.add(student);
        }

        for (Course course : courseDAO.getCourses()) {
            courses.add(course);
        }

        enrollmentDAO.loadEnrollments(this);

        System.out.println("Data loaded successfully!");
    }


    public void addStudent(Student student) {
        students.add(student);
    }


    public void addCourse(Course course) {
        courses.add(course);
    }


    public void assignInstructor(Course course, Instructor instructor) {
        assignments.put(course, instructor);
    }


    public void addEnrollmentFromDatabase(int studentId, int courseId) {

        Student foundStudent = null;
        Course foundCourse = null;

        for (Student student : students) {
            if (student.getId() == studentId) {
                foundStudent = student;
                break;
            }
        }

        for (Course course : courses) {
            if (course.getCourseId() == courseId) {
                foundCourse = course;
                break;
            }
        }

        if (foundStudent != null && foundCourse != null) {

            if (!enrollments.containsKey(foundCourse)) {
                enrollments.put(foundCourse, new HashSet<>());
            }

            enrollments.get(foundCourse).add(foundStudent);
        }
    }


    public void enrollStudent(Student student, Course course)
            throws SQLException {

        Student foundStudent = null;
        Course foundCourse = null;

        for (Student s : students) {
            if (s.getId() == student.getId()) {
                foundStudent = s;
                break;
            }
        }

        for (Course c : courses) {
            if (c.getCourseId() == course.getCourseId()) {
                foundCourse = c;
                break;
            }
        }

        if (foundStudent == null || foundCourse == null) {
            System.out.println("Student or course not registered.");
            return;
        }

        if (enrollments.containsKey(foundCourse)) {
            if (enrollments.get(foundCourse).contains(foundStudent)) {
                System.out.println("Student already enrolled.");
                return;
            }
        }

        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

        enrollmentDAO.enrollStudent(
                foundStudent.getId(),
                foundCourse.getCourseId()
        );

        addEnrollmentFromDatabase(
                foundStudent.getId(),
                foundCourse.getCourseId()
        );
    }


    public void showStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // Display courses
    public void showCourses() {
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    // Display instructor assignments
    public void showAssignments() {

        for (Course course : assignments.keySet()) {

            Instructor instructor = assignments.get(course);

            System.out.println(
                    course.getCourseName() + " - "
                            + instructor.getName()
            );
        }
    }























    public void showCourseRoster(Course course) {

        Course foundCourse = null;

        for (Course c : courses) {
            if (c.getCourseId() == course.getCourseId()) {
                foundCourse = c;
                break;
            }
        }

        if (foundCourse == null) {
            System.out.println("Course not registered.");
            return;
        }

        System.out.println("Course: " + foundCourse.getCourseName());

        Set<Student> roster = enrollments.get(foundCourse);

        if (roster == null || roster.isEmpty()) {
            System.out.println("No students enrolled.");
            return;
        }

        for (Student student : roster) {
            System.out.println(student.getName());
        }
    }
}
