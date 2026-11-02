
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    public void addCourse(String name, int credits)
            throws SQLException {

        Connection con = DatabaseConnection.getConnection();

        String sql = "INSERT INTO courses (course_name, credits) VALUES (?, ?)";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, name);
        pst.setInt(2, credits);

        pst.executeUpdate();

        System.out.println("Course added successfully!");

        pst.close();
        con.close();
    }


    public void showCourses() throws SQLException {

        Connection con = DatabaseConnection.getConnection();

        String sql = "SELECT * FROM courses ORDER BY course_id";

        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            System.out.println(
                    rs.getInt("course_id") + " - "
                            + rs.getString("course_name")
                            + " - Credits: " + rs.getInt("credits")
            );
        }

        rs.close();
        pst.close();
        con.close();
    }


    public List<Course> getCourses() throws SQLException {

        List<Course> courses = new ArrayList<>();

        Connection con = DatabaseConnection.getConnection();

        String sql = "SELECT * FROM courses";

        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Course course = new Course(
                    rs.getInt("course_id"),
                    rs.getString("course_name"),
                    rs.getInt("credits")
            );

            courses.add(course);
        }

        rs.close();
        pst.close();
        con.close();

        return courses;
    }


    public void updateCourse(int id, String name, int credits)
            throws SQLException {

        Connection con = DatabaseConnection.getConnection();

        String sql = "UPDATE courses SET course_name = ?, credits = ? WHERE course_id = ?";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, name);
        pst.setInt(2, credits);
        pst.setInt(3, id);

        int rows = pst.executeUpdate();

        if (rows > 0) {
            System.out.println("Course updated successfully!");
        } else {
            System.out.println("Course not found!");
        }

        pst.close();
        con.close();
    }


    public void deleteCourse(int id) throws SQLException {

        Connection con = DatabaseConnection.getConnection();

        String sql = "DELETE FROM courses WHERE course_id = ?";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, id);

        int rows = pst.executeUpdate();

        if (rows > 0) {
            System.out.println("Course deleted successfully!");
        } else {
            System.out.println("Course not found!");
        }

        pst.close();
        con.close();
    }
}
