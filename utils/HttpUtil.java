package utils;

import exception.ProjectException;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {

    public static Map<String, String> getQueryParameters(String url)  {
        Map<String, String> queryParams = new HashMap<>();
        try {
            URI uri = new URI(url);
            String query = uri.getQuery();
            if (query != null) {
                String[] params = query.split("&");
                for (String param : params) {
                    String[] keyValue = param.split("=");
                    if (keyValue.length == 2) {
                        String key = keyValue[0];
                        String value = keyValue[1];
                        queryParams.put(key, value);
                    }
                }
            }
        }catch (URISyntaxException ex){
            throw new ProjectException(ex.getMessage());
        }


        return queryParams;
    }


    public static String errorJsonMessage(String message){
        String timeStamp = LocalDateTime.now().format(Constant.Date.DATETIME_FORMATTER);
        return String.format("{\"timeStamp\": \"%s\", \"message\": \"%s\"}", timeStamp, message);
    }


}
