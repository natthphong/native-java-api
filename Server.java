import model.CustomerModel;
import model.TestHardModel;
import utils.JsonConverter;
import utils.SystemOutUtil;

import javax.net.ServerSocketFactory;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Server {
    public static String USER_DIR = System.getProperty("user.dir");
    public static Map<String, Object> appConfig = new HashMap<>(Map.of(
            "url", "jdbc:mysql://localhost:3306/db?createDatabaseIfNotExist=true",
            "username", "root",
            "password", "password",
            "enable-flyway", true
    ));

    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("=".repeat(17).concat("\nNATTHAPHONG CRUD\n").concat("=".repeat(17)));
            loadConfig();
            Connection connection = connectDb();
            System.out.println("=".repeat(75).concat("\nConnected DB : ").concat((String) appConfig.get("url")).concat("\n").concat("=".repeat(75)));
            ServerSocket ss = ServerSocketFactory.getDefault().createServerSocket(8080, 10);
            if (Boolean.parseBoolean((String) appConfig.get("enable-flyway"))) {
                InitSql(connection);
            }
            long endTime = System.currentTimeMillis();
            double executionTimeSeconds = (endTime - startTime) / 1000.0;
            System.out.println("Total execution time: " + executionTimeSeconds + " seconds");
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

    private static Connection connectDb() throws SQLException {
        String jdbcUrl = (String) appConfig.get("url");
        String dbUsername = (String) appConfig.get("username");
        String dbPassword = (String) appConfig.get("password");
        return DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
    }

    private static void loadConfig() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("application.yml"));
        String line;
        String currentKey = null;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                continue;
            }
            int colonIndex = line.indexOf(":");
            if (colonIndex != -1) {
                String key = line.substring(0, colonIndex).trim();
                String value = line.substring(colonIndex + 1).trim();
                appConfig.put(key, value);
                currentKey = null;
            } else if (currentKey != null) {
                String value = line.trim();
                String existingValue = appConfig.get(currentKey).toString();
                appConfig.put(currentKey, existingValue + " " + value);
                currentKey = null;
            } else {
                currentKey = line.trim();
            }
        }
    }

    public static void InitSql(Connection connection) {
        String sqlDirectoryPath = USER_DIR + "/tarWay";
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
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

}
