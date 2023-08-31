package net;

import Log.Log;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class HttpHelper {
    public static IResponse get(String url) {
        ClassicHttpRequest httpGet = ClassicRequestBuilder.get(url).build();
        return executeRequest(httpGet);
    }

    public static IResponse get(String url, String token) {
        ClassicHttpRequest httpGet = ClassicRequestBuilder.get(url)
                .setHeader("Authorization", token)
                .build();
        return executeRequest(httpGet);
    }

    public static IResponse get(String url, Header header) {
        ClassicHttpRequest httpGet = ClassicRequestBuilder.get(url)
                .setHeader(header)
                .build();
        return executeRequest(httpGet);
    }

    public static IResponse post(String url, String reqBody) {
        if (reqBody == null) {
            Log.error("ReqBody can not be null!");
            return null;
        }
        ClassicHttpRequest httpPost = ClassicRequestBuilder.post(url)
                .setEntity(new StringEntity(reqBody))
                .build();
        return executeRequest(httpPost);
    }

    public static IResponse post(String url, String reqBody, String token) {
        if (reqBody == null) {
            Log.error("ReqBody can not be null!");
            return null;
        }
        ClassicHttpRequest httpPost = ClassicRequestBuilder.post(url)
                .setEntity(new StringEntity(reqBody))
                .setHeader("Authorization", token)
                .build();
        return executeRequest(httpPost);
    }

    public static IResponse post(String url, String reqBody, Header header) {
        if (reqBody == null) {
            Log.error("ReqBody can not be null!");
            return null;
        }
        ClassicHttpRequest httpPost = ClassicRequestBuilder.post(url)
                .setEntity(new StringEntity(reqBody))
                .setHeader(header)
                .build();
        return executeRequest(httpPost);
    }

    public static IResponse put(String url, String reqBody) {
        if (reqBody == null) {
            Log.error("ReqBody can not be null!");
            return null;
        }
        ClassicHttpRequest httpPut = ClassicRequestBuilder.put(url)
                .setEntity(new StringEntity(reqBody))
                .build();
        return executeRequest(httpPut);
    }

    public static IResponse delete(String url) {
        ClassicHttpRequest httpDelete = ClassicRequestBuilder.delete(url).build();
        return executeRequest(httpDelete);
    }

    private static IResponse executeRequest(ClassicHttpRequest request) {
        AtomicReference<IResponse> result = new AtomicReference<>(null);
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            httpclient.execute(request, response -> {
                var code = response.getCode();
                var reason = response.getReasonPhrase();
                var headers = response.getHeaders();
                var body = EntityUtils.toString(response.getEntity());
                result.set(new IResponse(code, reason, headers, body));
                return true;
            });
        } catch (IOException e){
            Log.error(e.getMessage());
        }
        return result.get();
    }
}

