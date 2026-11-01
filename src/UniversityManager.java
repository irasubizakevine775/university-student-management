
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

    private Map<Course, Set<Student>> enrollments = new HashMap<>();

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

    // Enroll a student in a course
    public void enrollStudent(Student student, Course course) {

        if (!students.contains(student) || !courses.contains(course)) {
            System.out.println("Student or course not registered.");
            return;
        }

        enrollments.putIfAbsent(course, new HashSet<>());

        boolean added = enrollments.get(course).add(student);

        if (added) {
            System.out.println(student.getName()
                    + " enrolled in "
                    + course.getCourseName());
        } else {
            System.out.println("Student is already enrolled.");
        }
    }


    public void showStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }


    public void showCourses() {
        for (Course course : courses) {
            System.out.println(course);
        }
    }


    public void showAssignments() {
        for (Course course : assignments.keySet()) {
            Instructor instructor = assignments.get(course);

            System.out.println(course.getCourseName()
                    + " - " + instructor.getName());
        }
    }


    public void showCourseRoster(Course course) {

        System.out.println("Course: " + course.getCourseName());

        Set<Student> roster = enrollments.get(course);

        if (roster == null || roster.isEmpty()) {
            System.out.println("No students enrolled.");
            return;
        }

        for (Student student : roster) {
            System.out.println(student.getName());
        }
    }
}
