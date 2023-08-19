import http_enum.HttpStatus;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class HttpResponse {

    public static void responseJson (PrintStream ps , String json, HttpStatus status){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String pattern = "EEE, dd MMM yyyy HH:mm:ss 'GMT'";
        DateTimeFormatter dateTimeFormatterEng = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
        LocalDateTime current  = LocalDateTime.now();
        ps.println("HTTP/1.1 " + status.getCode() + " " + status.getMessage());
        ps.println("Date: ".concat(current.format(dateTimeFormatter)));
        ps.println("Server: Java");
        ps.println("Last-Modified: ".concat(current.atZone(ZoneId.of("GMT")).format(dateTimeFormatterEng)));
        ps.println("Content-Length: " + json.length());
        ps.println("Content-Type: application/json");
        ps.println("Connection: Closed");
        ps.println();
        ps.println(json);
    }
}
