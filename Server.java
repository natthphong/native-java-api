import javax.net.ServerSocketFactory;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Comparator;

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
            if (false){
                InitSql(connection);
            }
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

    public static  void  InitSql(Connection connection){
        String sqlDirectoryPath = "/Users/natthaohong/Desktop/Java/tarWay";
        System.out.println("=".repeat(50).concat("\nInit Sql Script : ").concat(sqlDirectoryPath).concat("\n").concat("=".repeat(50)));
        File sqlDirectory = new File(sqlDirectoryPath);
        if (!sqlDirectory.isDirectory()) {
            System.out.println("Invalid directory path.");
        }
        File[] sqlFiles = sqlDirectory.listFiles((dir, name) -> name.endsWith(".sql"));
        if (sqlFiles == null || sqlFiles.length == 0) {
            System.out.println("No SQL files found in the directory.");
            return;
        }
        try {
            Arrays.sort(sqlFiles, Comparator.comparing(File::getName));
            Statement statement = connection.createStatement();
            for (File sqlFile : sqlFiles) {
                String sqlContent = new String(Files.readAllBytes(sqlFile.toPath()), StandardCharsets.UTF_8);
                String[] sqlCommands = sqlContent.split(";");
                for (String sqlCommand : sqlCommands) {
                    sqlCommand = sqlCommand.trim();
                    if (!sqlCommand.isEmpty()) {
                        statement.executeUpdate(sqlCommand);
                        System.out.println("Executed: " + sqlCommand);
                    }
                }
            }
        }catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

}
