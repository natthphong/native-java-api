package utils;

import Httpenum.HttpContentType;
import Httpenum.HttpStatus;
import utils.Constant;
import utils.HttpUtil;

import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class HttpResponse {

    public static void response(PrintStream ps, String json, HttpStatus status, HttpContentType contentType) {
        LocalDateTime current = LocalDateTime.now();
        ps.println("HTTP/1.1 " + status.getCode() + " " + status.getMessage());
        ps.println("Date: ".concat(current.format(Constant.Date.DATETIME_FORMATTER)));
        ps.println("Server: Java");
        ps.println("Last-Modified: ".concat(current.atZone(ZoneId.of("GMT")).format(Constant.Date.DATETIME_FORMATTER_ENG)));
        ps.println("Content-Length: " + json.length());
        ps.println("Content-Type: ".concat(contentType.getValue()));
        ps.println("Connection: Closed");
        ps.println();
        ps.println(json);
        ps.close();
    }

    public static void response(PrintStream ps, byte[] content, HttpStatus status, HttpContentType contentType) {
        LocalDateTime current = LocalDateTime.now();
        ps.println("HTTP/1.1 " + status.getCode() + " " + status.getMessage());
        ps.println("Date: " + current.format(Constant.Date.DATETIME_FORMATTER));
        ps.println("Server: Java");
        ps.println("Last-Modified: " + current.atZone(ZoneId.of("GMT")).format(Constant.Date.DATETIME_FORMATTER_ENG));
        ps.println("Content-Length: " + content.length);
        ps.println("Content-Type: " + contentType.getValue());
        ps.println("Connection: Closed");
        ps.println();
        OutputStream os = ps;
        try {
            os.write(content);
            os.flush();
            os.close();
            ps.close();
        } catch (Exception e) {
            response(ps, HttpUtil.errorJsonMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR, HttpContentType.APPLICATION_JSON);
        }
    }
}
