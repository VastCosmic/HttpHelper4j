package Main;

import net.HttpHelper;

import java.io.IOException;

public class TestMain {
    public static void main(String[] args) throws IOException {
        var jsonBody = "{\"name\":\"java_test\",\"pwd\":\"123456\"}";
        var resp = HttpHelper.post("http://127.0.0.1:8080/users/login", jsonBody).toString();
        System.out.println("Resp: " + resp);

        var token = resp.split("\"token\":\"")[1].split("\"")[0];
        System.out.println("Token: " + token);

        resp = HttpHelper.get("http://127.0.0.1:8080/users/logout",token).toString();
        System.out.println("\nResp: " + resp);
    }
}
