package configuration;

import java.util.List;

public class ServerConfiguration {

    private static List<String> allPath =
            List.of("/v1/customer/all", "/v1/customer/**",
                    "/v1/customer/update", "/v1/customer/delete",
                    "/v1/customer/save");


    public static void addPathServer(List<String> paths) {
        allPath.addAll(paths);
    }

    public static void addPathServer(String paths) {
        allPath.add(paths);
    }

    public static List<String> getAllPath() {
        return allPath;
    }

    public static boolean validatePathServer(String path) {
        List<String> allowAll = allPath.stream().filter(e -> e.contains("**")).map(e -> e.substring(0, e.indexOf("**"))).toList();
        for (var e : allowAll) {
            if (path.startsWith(e)) {
                return true;
            }
        }
        return allPath.contains(path);
    }


}
