import configuration.ServerConfiguration;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Server {


    public static void main(String[] args) {
        try {
            System.out.println("=".repeat(17).concat("\nNATTHAPHONG CRUD\n").concat("=".repeat(17)));
            String jdbcUrl = "jdbc:mysql://localhost:3306/db";
            String dbUsername = "root";
            String dbPassword = "password";
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            System.out.println("=".repeat(50).concat("\nConnected DB : ").concat(jdbcUrl).concat("\n").concat("=".repeat(50)));
            ServerSocket ss = ServerSocketFactory.getDefault().createServerSocket(8080, 10);
            while (true) {
                Socket s = ss.accept();
                new Thread(new ServerProcess(s, connection)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
