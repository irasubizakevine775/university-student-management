
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

        // Register students
        manager.addStudent(student1);
        manager.addStudent(student2);

        // Register courses
        manager.addCourse(course1);
        manager.addCourse(course2);

        // Assign instructors
        manager.assignInstructor(course1, instructor1);
        manager.assignInstructor(course2, instructor2);

        // Enroll students
        System.out.println(" ENROLLMENT ");

        manager.enrollStudent(student1, course1);
        manager.enrollStudent(student2, course1);
        manager.enrollStudent(student1, course2);

        // Test duplicate enrollment
        manager.enrollStudent(student1, course1);

        // Display course rosters
        System.out.println("COURSE ROSTERS ");

        manager.showCourseRoster(course1);

        System.out.println();

        manager.showCourseRoster(course2);
    }
}
