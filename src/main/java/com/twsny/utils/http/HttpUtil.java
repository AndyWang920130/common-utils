package com.twsny.utils.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.twsny.utils.http.constants.ContentType;
import com.twsny.utils.http.constants.RequestMethodType;
import com.twsny.utils.http.dto.PushRequest;
import com.twsny.utils.token.JwtUtil;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public class HttpUtil {
    public static Response executeRequest(Request request) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        return client.newCall(request).execute();
    }

    public static Request initRequest(PushRequest pushRequest) {
        RequestMethodType methodType = pushRequest.getMethodType();
        String url = pushRequest.getUrl();
        ContentType contentType = pushRequest.getContentType();
        Object requestBody = pushRequest.getRequestBody();
        Map<String, String> paramMap = pushRequest.getParamMap();
        Map<String, String> headerMap = pushRequest.getHeaderMap();
        switch (methodType) {
            case GET:
                return initGetRequest(url, paramMap, headerMap);
            case POST:
            default:
                return initPostRequest(url, contentType, requestBody, paramMap, headerMap);
        }
    }

    public static String getToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(JwtUtil.AUTHORIZATION_HEADER);
        if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else {
            String token = request.getParameter("token");
            return token;
        }
    }

    private static Request initPostRequest(String url, ContentType contentType, Object requestBody, Map<String, String> paramMap, Map<String, String> headerMap) {
        Request.Builder requestBuilder;
        switch (contentType) {
            case JSON:
            default:
                requestBuilder = initApplicationJsonPostRequestBuilder(url, requestBody);
                break;
            case MULTIPART:
                requestBuilder = initFormDataPostRequestBuilder(url, paramMap);
                break;
        }

        return addHeader(requestBuilder, headerMap).build();
    }

    private static Request initGetRequest(String url, Map<String, String> paramMap, Map<String, String> headerMap) {
        Request.Builder requestBuilder = initGetRequestBuilder(url, paramMap);
        return HttpUtil
                .addHeader(requestBuilder, headerMap)
                .build();
    }

    private static Request.Builder initApplicationJsonPostRequestBuilder(String url, Object requestBodyVM) {
        return initApplicationJsonPostRequestBuilder(url, requestBodyVM, SerializerFeature.WriteMapNullValue);
    }

    private static Request.Builder initApplicationJsonPostRequestBuilder(String url, Object requestBodyVM, SerializerFeature... features) {
        String requestBodyString = JSON.toJSONString(requestBodyVM, features);
        RequestBody requestBody = RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), requestBodyString);
        Request.Builder requestBuilder = new Request.Builder()
            .url(url)
            .method("POST", requestBody)
            .addHeader("Content-Type", ContentType.JSON.getValue());
        return requestBuilder;
    }

    private static Request.Builder initFormDataPostRequestBuilder(String url, Map<String, String> formDataMap) {
        MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder()
            .setType(MultipartBody.FORM);  // 设置请求类型为 multipart/form-data
        if (formDataMap != null) {
            for (Map.Entry<String, String> entry : formDataMap.entrySet()) {
                multipartBodyBuilder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
        MultipartBody multipartBody = multipartBodyBuilder.build();


        Request.Builder requestBuilder = new Request.Builder()
            .url(url)
            .method("POST", multipartBody)
            .addHeader("Content-Type", ContentType.MULTIPART.getValue());
        return requestBuilder;
    }

    private static Request.Builder initGetRequestBuilder(String url, Map<String, String> paramsMap) {
        HttpUrl httpUrl;
        HttpUrl.Builder  urlBuilder = HttpUrl.parse(url)
            .newBuilder();
        if (paramsMap != null)  {
            for (String paramName : paramsMap.keySet()) {
                String paramValue = paramsMap.get(paramName);
                urlBuilder.addQueryParameter(paramName, paramValue);
            }
        }

        httpUrl = urlBuilder.build();

        Request.Builder requestBuilder = new Request.Builder()
            .url(httpUrl)
            .get();
        return requestBuilder;
    }

    private static Request.Builder addHeader(Request.Builder builder, Map<String, String> headerMap) {
        if (headerMap != null) {
            for (Map.Entry<String, String> header : headerMap.entrySet()) {
                builder.addHeader(header.getKey(), header.getValue());
            }
        }
        return builder;
    }
}
