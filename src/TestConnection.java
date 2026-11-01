
import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) {

        try {
            Connection connection =
                    DatabaseConnection.getConnection();

            System.out.println("Connected successfully!");

            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection failed!");
            System.out.println(e.getMessage());
        }
    }
}

