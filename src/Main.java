
import java.util.Scanner;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        UniversityManager manager = new UniversityManager();

        int choice = -1;

        do {
            System.out.println("\n===== UNIVERSITY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Show Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Add Course");
            System.out.println("6. Show Courses");
            System.out.println("7. Update Course");
            System.out.println("8. Delete Course");
            System.out.println("9. Enroll Student");
            System.out.println("10. Show Enrollments (FULL JOIN)");
            System.out.println("11. Update Grade");
            System.out.println("12. Remove Enrollment");
            System.out.println("13. Show Students Using Collections");
            System.out.println("14. Show Courses Using Collections");
            System.out.println("15. Show Course Roster");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");

            if (!input.hasNextInt()) {
                System.out.println("Please enter a number.");
                input.nextLine();
                continue;
            }

            choice = input.nextInt();
            input.nextLine();

            try {

                switch (choice) {

                    case 1:
                        System.out.print("Student name: ");
                        String name = input.nextLine();

                        System.out.print("Email: ");
                        String email = input.nextLine();

                        System.out.print("Program: ");
                        String program = input.nextLine();

                        System.out.print("GPA: ");
                        double gpa = input.nextDouble();
                        input.nextLine();

                        studentDAO.addStudent(name, email, program, gpa);
                        break;

                    case 2:
                        System.out.println("\n===== STUDENTS =====");
                        studentDAO.showStudents();
                        break;

                    case 3:
                        System.out.print("Student ID: ");
                        int studentId = input.nextInt();
                        input.nextLine();

                        System.out.print("New program: ");
                        String newProgram = input.nextLine();

                        System.out.print("New GPA: ");
                        double newGpa = input.nextDouble();
                        input.nextLine();

                        studentDAO.updateStudent(studentId, newProgram, newGpa);
                        break;

                    case 4:
                        System.out.print("Student ID to delete: ");
                        int deleteStudentId = input.nextInt();
                        input.nextLine();

                        studentDAO.deleteStudent(deleteStudentId);
                        break;

                    case 5:
                        System.out.print("Course name: ");
                        String courseName = input.nextLine();

                        System.out.print("Credits: ");
                        int credits = input.nextInt();
                        input.nextLine();

                        courseDAO.addCourse(courseName, credits);
                        break;

                    case 6:
                        System.out.println("\n===== COURSES =====");
                        courseDAO.showCourses();
                        break;

                    case 7:
                        System.out.print("Course ID: ");
                        int updateCourseId = input.nextInt();
                        input.nextLine();

                        System.out.print("New course name: ");
                        String newCourseName = input.nextLine();

                        System.out.print("New credits: ");
                        int newCredits = input.nextInt();
                        input.nextLine();

                        courseDAO.updateCourse(
                                updateCourseId, newCourseName, newCredits);
                        break;

                    case 8:
                        System.out.print("Course ID to delete: ");
                        int deleteCourseId = input.nextInt();
                        input.nextLine();

                        courseDAO.deleteCourse(deleteCourseId);
                        break;

                    case 9:
                        System.out.println("\n===== STUDENTS =====");
                        studentDAO.showStudents();

                        System.out.println("\n===== COURSES =====");
                        courseDAO.showCourses();

                        System.out.print("Student ID: ");
                        int enrollStudentId = input.nextInt();

                        System.out.print("Course ID: ");
                        int enrollCourseId = input.nextInt();
                        input.nextLine();

                        manager.loadData();

                        Student selectedStudent = null;
                        Course selectedCourse = null;

                        for (Student s : studentDAO.getStudents()) {
                            if (s.getId() == enrollStudentId) {
                                selectedStudent = s;
                                break;
                            }
                        }

                        for (Course c : courseDAO.getCourses()) {
                            if (c.getCourseId() == enrollCourseId) {
                                selectedCourse = c;
                                break;
                            }
                        }

                        if (selectedStudent != null && selectedCourse != null) {
                            manager.enrollStudent(selectedStudent, selectedCourse);
                        } else {
                            System.out.println("Student or course not found.");
                        }
                        break;

                    case 10:
                        System.out.println("\n===== ENROLLMENTS =====");
                        enrollmentDAO.showEnrollments();
                        break;

                    case 11:
                        System.out.print("Student ID: ");
                        int gradeStudentId = input.nextInt();

                        System.out.print("Course ID: ");
                        int gradeCourseId = input.nextInt();
                        input.nextLine();

                        System.out.print("Grade: ");
                        String grade = input.nextLine();

                        enrollmentDAO.updateGrade(
                                gradeStudentId, gradeCourseId, grade);
                        break;

                    case 12:
                        System.out.print("Student ID: ");
                        int removeStudentId = input.nextInt();

                        System.out.print("Course ID: ");
                        int removeCourseId = input.nextInt();
                        input.nextLine();

                        enrollmentDAO.removeEnrollment(
                                removeStudentId, removeCourseId);
                        manager.loadData();
                        break;

                    case 13:
                        manager.loadData();

                        System.out.println("STUDENT COLLECTION ");
                        manager.showStudents();
                        break;

                    case 14:
                        manager.loadData();

                        System.out.println(" COURSE COLLECTION ");
                        manager.showCourses();
                        break;

                    case 15:
                        manager.loadData();

                        System.out.println(" COURSES ");
                        courseDAO.showCourses();

                        System.out.print("Enter Course ID: ");
                        int rosterCourseId = input.nextInt();
                        input.nextLine();

                        Course rosterCourse = null;

                        for (Course c : courseDAO.getCourses()) {
                            if (c.getCourseId() == rosterCourseId) {
                                rosterCourse = c;
                                break;
                            }
                        }

                        if (rosterCourse != null) {
                            manager.showCourseRoster(rosterCourse);
                        } else {
                            System.out.println("Course not found.");
                        }
                        break;

                    case 0:
                        System.out.println("Thank you for using the system!");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (SQLException e) {
                System.out.println("Database Error: " + e.getMessage());

            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter the correct value.");
                input.nextLine();
            }

        } while (choice != 0);

        input.close();
    }
}
