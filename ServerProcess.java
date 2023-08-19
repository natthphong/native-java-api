import http_enum.HttpStatus;
import model.CustomerModel;
import utils.HttpUtil;
import utils.JsonConverter;
import utils.SystemOutUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

class ServerProcess implements Runnable {
    private static final Logger log = Logger.getLogger(ServerProcess.class.getName());

    private final Socket s;
    private final Connection connection;
    ServerProcess(Socket s, Connection connection) {
        this.s = s;
        this.connection = connection;
    }

    public void run() {
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintStream ps = new PrintStream(s.getOutputStream());


            String reqLine = br.readLine();
            if (reqLine != null) {
                String[] requestParts = reqLine.split(" ");
                String method = requestParts[0];
                String path = requestParts[1];
                if (path.equalsIgnoreCase("/favicon.ico")){
                    return;
                }
                var param = HttpUtil.getQueryParameters(path);
                Arrays.stream(requestParts).toList().forEach(System.out::println);
                List<CustomerModel> list = new ArrayList<>();
                CustomerModel customerModel = new CustomerModel();
                customerModel.setAge(10);
                customerModel.setEmail("taza3aza@hotmail.com");
                customerModel.setPassword("sfsfsd");
                customerModel.setUsername("tar");
                list.add(customerModel);
                list.add(customerModel);
                String customerTest = JsonConverter.toJsonString(customerModel);
                System.out.println(customerModel.test.size());
                String bodyTest = JsonConverter.toJsonString(list);
                CustomerModel Test  = JsonConverter.fromJsonString(customerTest,CustomerModel.class);
                List<CustomerModel> BodyTest  = JsonConverter.fromJsonStringToList(bodyTest,CustomerModel.class);
                SystemOutUtil.printObjects(BodyTest);
                HttpResponse.responseJson(ps,JsonConverter.toJsonString(BodyTest), HttpStatus.OK);
                String line;
                int contentLength = 0;
                while ((line = br.readLine()) != null && !line.isEmpty()) {
                    System.out.println(line);
                    if (line.startsWith("Content-Length:")) {
                        contentLength = Integer.parseInt(line.substring("Content-Length:".length()).trim());
                    }
                }
                if (contentLength > 0) {
                    char[] requestBody = new char[contentLength];
                    br.read(requestBody, 0, contentLength);
                    String requestBodyString = new String(requestBody);
                    System.out.println("Request Body: " + requestBodyString);
                }

            }
            ps.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}