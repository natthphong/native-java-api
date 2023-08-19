package utils;

import java.net.URI;
import java.net.URISyntaxException;
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
            throw new RuntimeException(ex.getMessage());
        }


        return queryParams;
    }


}
