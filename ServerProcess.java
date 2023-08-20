import Httpenum.HttpContentType;
import Httpenum.HttpMethod;
import Httpenum.HttpStatus;
import configuration.ServerConfiguration;
import controller.CustomerController;
import exception.ProjectException;
import repository.Impl.CustomerRepositoryImpl;
import service.CustomerService;
import utils.BcryptUtil;
import utils.HttpResponse;
import utils.HttpUtil;
import utils.JsonConverter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.util.Map;

class ServerProcess implements Runnable {
    private final Socket s;
    private final Connection connection;

    ServerProcess(Socket s, Connection connection) {
        this.s = s;
        this.connection = connection;
    }

    public void run() {
        PrintStream ps = null;
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            ps = new PrintStream(s.getOutputStream());

            String reqLine = br.readLine();
            if (reqLine != null) {
                String[] requestParts = reqLine.split(" ");
                String method = requestParts[0];
                String path = requestParts[1];
                if (path.equalsIgnoreCase("/favicon.ico")) {
                    return;
                }
                if (path.equalsIgnoreCase("/paths")){
                    HttpResponse.response(ps, JsonConverter.toJsonString(ServerConfiguration.getAllPath()), HttpStatus.OK, HttpContentType.APPLICATION_JSON);
                    return;
                }
                if (method.equalsIgnoreCase(HttpMethod.OPTIONS.getValue()) || path.equalsIgnoreCase("/")) {
                    HttpResponse.response(ps, "{}", HttpStatus.OK, HttpContentType.APPLICATION_JSON);
                    return;
                }if (!ServerConfiguration.validatePathServer(path)) {
                    throw new ProjectException("Invalid Path", HttpStatus.FORBIDDEN);
                }
                String line;
                int contentLength = 0;
                while ((line = br.readLine()) != null && !line.isEmpty()) {
//                    System.out.println(line);
                    if (line.startsWith("Content-Length:")) {
                        contentLength = Integer.parseInt(line.substring("Content-Length:".length()).trim());
                    }
                }
                char[] requestBody = new char[contentLength];
                String requestBodyString = "";
                if (contentLength > 0) {
                    br.read(requestBody, 0, contentLength);
                    requestBodyString = new String(requestBody);
//                    System.out.println("Request Body: " + requestBodyString);
                }
                //ROUTE
                Map<String, String> param = HttpUtil.getQueryParameters(path);
                if (path.startsWith("/v1/customer")) {
                    path = !param.isEmpty()?path.substring("/v1/customer".length(),path.indexOf("?")).trim():path.substring("/v1/customer".length()).trim();
                    BcryptUtil bcryptUtil = new BcryptUtil();
                    CustomerRepositoryImpl customerRepositoryImpl = new CustomerRepositoryImpl(connection, bcryptUtil);
                    CustomerService customerService = new CustomerService(customerRepositoryImpl, s, ps, bcryptUtil);
                    CustomerController controller = new CustomerController(customerService);
                    controller.run(requestBodyString, param,path ,method);
                }
            }
            br.close();
        } catch (ProjectException e) {
            assert ps != null;
            HttpResponse.response(ps, e.getBodyJson(), e.getHttpStatus(), HttpContentType.APPLICATION_JSON);
        } catch (Exception e) {
            assert ps != null;
            HttpResponse.response(ps, HttpUtil.errorJsonMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR, HttpContentType.APPLICATION_JSON);
        }
    }


}