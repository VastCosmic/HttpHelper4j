package net;

import org.apache.hc.core5.http.Header;

import java.util.Arrays;

// 响应类，用于封装响应的信息
public class IResponse {
    private int code; // 响应状态码
    private String reason; // 响应原因短语
    private Header[] headers; // 响应头数组
    private String body; // 响应体字符串

    public IResponse(int code, String reason, Header[] headers, String body) {
        this.code = code;
        this.reason = reason;
        this.headers = headers;
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Header[] getHeaders() {
        return headers;
    }

    public void setHeaders(Header[] headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String toString() {
        return "Code: " + code + ", Reason: " + reason + ",\nHeaders: " + Arrays.toString(headers) + ", \nRespBody: " + body;
    }
}