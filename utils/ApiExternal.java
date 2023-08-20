package utils;

import Httpenum.HttpMethod;
import Httpenum.HttpStatus;
import exception.ProjectException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ApiExternal {
    public String callApi(String apiUrl) {
        return callApi(apiUrl, HttpMethod.GET.getValue(), null, null);
    }

    public String callApi(String apiUrl, HttpMethod method) {
        return callApi(apiUrl, method.getValue(), null, null);
    }

    public String callApi(String apiUrl, HttpMethod method, String requestBody) {
        return callApi(apiUrl, method.getValue(), requestBody, null);
    }

    public String callApi(String apiUrl, String method, String requestBody, Map<String, String> headers) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            if (requestBody != null) {
                connection.setDoOutput(true);
                connection.getOutputStream().write(requestBody.getBytes());
            }
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpStatus.OK.getCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            } else {
                System.out.println("API request failed. Response Code: " + responseCode);
            }
        } catch (IOException e) {
            throw new ProjectException(e.getMessage());
        }
        return response.toString();
    }
}
