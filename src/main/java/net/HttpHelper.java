package net;

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
    public static IResponse get(String url) throws IOException {
        AtomicReference<IResponse> result = new AtomicReference<>(null);
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            ClassicHttpRequest httpGet = ClassicRequestBuilder.get(url)
                    .build();
            reqMethod(result, httpclient, httpGet);
        }
        return result.get();
    }
    // 自定义token
    public static IResponse get(String url, String token) throws IOException {
        AtomicReference<IResponse> result = new AtomicReference<>(null);
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            ClassicHttpRequest httpGet = ClassicRequestBuilder.get(url).build();
            //httpGet.setHeader("Authorization", "Bearer " + token);
            httpGet.setHeader("Authorization", token);
            reqMethod(result, httpclient, httpGet);
        }
        return result.get();
    }
    // 自定义请求头
    public static IResponse get(String url, Header header) throws IOException {
        AtomicReference<IResponse> result = new AtomicReference<>(null);
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            ClassicHttpRequest httpGet = ClassicRequestBuilder.get(url).build();
            httpGet.setHeader(header);
            reqMethod(result, httpclient, httpGet);
        }
        return result.get();
    }

    public static IResponse post(String url, String reqBody) throws IOException {
        if(reqBody == null) {
            System.out.println("ReqBody can not be null!");
            return null;
        }
        AtomicReference<IResponse> result = new AtomicReference<>(null);
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            ClassicHttpRequest httpPost = ClassicRequestBuilder.post(url)
                    .setEntity(new StringEntity(reqBody))
                    .build();
            reqMethod(result, httpclient, httpPost);
        }
        return result.get();
    }

    public static IResponse post(String url, String reqBody, String token) throws IOException {
        if(reqBody == null) {
            System.out.println("ReqBody can not be null!");
            return null;
        }
        AtomicReference<IResponse> result = new AtomicReference<>(null);
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            ClassicHttpRequest httpPost = ClassicRequestBuilder.post(url)
                    .setEntity(new StringEntity(reqBody))
                    .build();
            //httpGet.setHeader("Authorization", "Bearer " + token);
            httpPost.setHeader("Authorization", token);
            reqMethod(result, httpclient, httpPost);
        }
        return result.get();
    }

    public static IResponse post(String url, String reqBody, Header header) throws IOException {
        if(reqBody == null) {
            System.out.println("ReqBody can not be null!");
            return null;
        }
        AtomicReference<IResponse> result = new AtomicReference<>(null);
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            ClassicHttpRequest httpPost = ClassicRequestBuilder.post(url)
                    .setEntity(new StringEntity(reqBody))
                    .build();
            //httpGet.setHeader("Authorization", "Bearer " + token);
            httpPost.setHeader(header);
            reqMethod(result, httpclient, httpPost);
        }
        return result.get();
    }

    private static void reqMethod(AtomicReference<IResponse> result, CloseableHttpClient httpclient, ClassicHttpRequest httpRequest) throws IOException {
        httpclient.execute(httpRequest, response -> {
            var code = response.getCode();
            var reason = response.getReasonPhrase();
            var version = response.getVersion();
            var headers = response.getHeaders();
            var body = EntityUtils.toString(response.getEntity());
            result.set(new IResponse(code, reason, headers, body));
            return true;
        });
    }
}
