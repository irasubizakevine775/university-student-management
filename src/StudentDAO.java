
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {


    public void addStudent(String name, String email,
                           String program, double gpa)
            throws SQLException {

        Connection con = DatabaseConnection.getConnection();

        String sql = "INSERT INTO students (name, email, program, gpa) VALUES (?, ?, ?, ?)";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, name);
        pst.setString(2, email);
        pst.setString(3, program);
        pst.setDouble(4, gpa);

        pst.executeUpdate();

        System.out.println("Student added successfully!");

        pst.close();
        con.close();
    }


    public void showStudents() throws SQLException {

        Connection con = DatabaseConnection.getConnection();

        String sql = "SELECT * FROM students ORDER BY id";

        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            System.out.println(
                    rs.getInt("id") + " - "
                            + rs.getString("name") + " - "
                            + rs.getString("program") + " - GPA: "
                            + rs.getDouble("gpa")
            );
        }

        rs.close();
        pst.close();
        con.close();
    }

    public List<Student> getStudents() throws SQLException {

        List<Student> students = new ArrayList<>();

        Connection con = DatabaseConnection.getConnection();

        String sql = "SELECT * FROM students";

        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Student student = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("program"),
                    rs.getDouble("gpa")
            );

            students.add(student);
        }

        rs.close();
        pst.close();
        con.close();

        return students;
    }

    public void updateStudent(int id, String program, double gpa)
            throws SQLException {

        Connection con = DatabaseConnection.getConnection();

        String sql = "UPDATE students SET program = ?, gpa = ? WHERE id = ?";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, program);
        pst.setDouble(2, gpa);
        pst.setInt(3, id);

        int rows = pst.executeUpdate();

        if (rows > 0) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found!");
        }

        pst.close();
        con.close();
    }
    public void deleteStudent(int id) throws SQLException {

        Connection con = DatabaseConnection.getConnection();

        String sql = "DELETE FROM students WHERE id = ?";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, id);

        int rows = pst.executeUpdate();

        if (rows > 0) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found!");
        }

        pst.close();
        con.close();
    }
}

