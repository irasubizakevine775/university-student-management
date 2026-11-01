
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class UniversityManager {

    private List<Student> students = new ArrayList<>();
    private Set<Course> courses = new HashSet<>();
    private Map<Course, Instructor> assignments = new HashMap<>();

    // Add a student
    public void addStudent(Student student) {
        students.add(student);
    }

    // Add a course
    public void addCourse(Course course) {
        courses.add(course);
    }

    // Assign an instructor to a course
    public void assignInstructor(Course course, Instructor instructor) {
        assignments.put(course, instructor);
    }

    // Display students
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
}
