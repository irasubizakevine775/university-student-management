
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrollmentDAO {






    public void enrollStudent(int studentId, int courseId)
            throws SQLException {

        Connection con = DatabaseConnection.getConnection();

        String sql = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, studentId);
        pst.setInt(2, courseId);

        pst.executeUpdate();

        System.out.println("Student enrolled successfully!");

        pst.close();
        con.close();
    }


    public void loadEnrollments(UniversityManager manager)
            throws SQLException {

        Connection con = DatabaseConnection.getConnection();

        String sql = "SELECT student_id, course_id FROM enrollments";

        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            int studentId = rs.getInt("student_id");
            int courseId = rs.getInt("course_id");

            manager.addEnrollmentFromDatabase(studentId, courseId);
        }

        rs.close();
        pst.close();
        con.close();
    }

    // Display all students and courses using FULL JOIN
    public void showEnrollments() throws SQLException {

        Connection con = DatabaseConnection.getConnection();

        String sql = "SELECT s.name, c.course_name, e.grade "
                + "FROM students s "
                + "FULL JOIN enrollments e ON s.id = e.student_id "
                + "FULL JOIN courses c ON e.course_id = c.course_id";

        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            System.out.println(
                    "Student: " + rs.getString("name")
                            + " | Course: " + rs.getString("course_name")
                            + " | Grade: " + rs.getString("grade")
            );
        }

        rs.close();
        pst.close();
        con.close();
    }

    // Update grade
    public void updateGrade(int studentId, int courseId, String grade)
            throws SQLException {

        Connection con = DatabaseConnection.getConnection();

        String sql = "UPDATE enrollments SET grade = ? "
                + "WHERE student_id = ? AND course_id = ?";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, grade);
        pst.setInt(2, studentId);
        pst.setInt(3, courseId);

        int rows = pst.executeUpdate();

        if (rows > 0) {
            System.out.println("Grade updated successfully!");
        } else {
            System.out.println("Enrollment not found!");
        }

        pst.close();
        con.close();
    }

    // Remove enrollment
    public void removeEnrollment(int studentId, int courseId)
            throws SQLException {

        Connection con = DatabaseConnection.getConnection();

        String sql = "DELETE FROM enrollments "
                + "WHERE student_id = ? AND course_id = ?";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, studentId);
        pst.setInt(2, courseId);

        int rows = pst.executeUpdate();

        if (rows > 0) {
            System.out.println("Enrollment removed successfully!");
        } else {
            System.out.println("Enrollment not found!");
        }

        pst.close();
        con.close();
    }
}
