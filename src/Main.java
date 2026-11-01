
public class Main {

    public static void main(String[] args) {

        UniversityManager manager = new UniversityManager();

        // Create students
        Student student1 = new Student(
                1, "Alice", "alice@gmail.com",
                "Computer Science", 3.8
        );

        Student student2 = new Student(
                2, "Kevin", "kevin@gmail.com",
                "Information Technology", 3.5
        );

        // Create courses
        Course course1 = new Course(
                201, "Java Programming", 3
        );

        Course course2 = new Course(
                202, "Database Management", 4
        );

        // Create instructors
        Instructor instructor1 = new Instructor(
                101, "John", "john@gmail.com",
                "Computer Science"
        );

        Instructor instructor2 = new Instructor(
                102, "Mary", "mary@gmail.com",
                "Information Technology"
        );

        // Add students
        manager.addStudent(student1);
        manager.addStudent(student2);

        // Add courses
        manager.addCourse(course1);
        manager.addCourse(course2);

        // Assign instructors
        manager.assignInstructor(course1, instructor1);
        manager.assignInstructor(course2, instructor2);

        // Display information
        System.out.println("===== STUDENTS =====");
        manager.showStudents();

        System.out.println("\n===== COURSES =====");
        manager.showCourses();

        System.out.println("\n===== INSTRUCTOR ASSIGNMENTS =====");
        manager.showAssignments();
    }
}
