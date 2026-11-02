
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/university_db",
                "postgres",
                "12345"
        );
    }

    public static void main(String[] args) {

        try {
            // Connect to database
            Connection connection = getConnection();

            System.out.println("Connected successfully!");

            Statement statement = connection.createStatement();

            String students = """
                    CREATE TABLE IF NOT EXISTS students (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100),
                        email VARCHAR(100),
                        program VARCHAR(100),
                        gpa DOUBLE PRECISION
                    )
                    """;


            String courses = """
                    CREATE TABLE IF NOT EXISTS courses (
                        course_id SERIAL PRIMARY KEY,
                        course_name VARCHAR(100),
                        credits INT
                    )
                    """;


            String enrollments = """
                    CREATE TABLE IF NOT EXISTS enrollments (
                        id SERIAL PRIMARY KEY,
                        student_id INT REFERENCES students(id),
                        course_id INT REFERENCES courses(course_id),
                        grade VARCHAR(5),
                        UNIQUE(student_id, course_id)
                    )
                    """;


            statement.executeUpdate(students);
            statement.executeUpdate(courses);
            statement.executeUpdate(enrollments);

            System.out.println("All tables created successfully!");


            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }
}
