package com.twsny.utils.http.dto;

import com.alibaba.fastjson.serializer.SerializerFeature;

import com.twsny.utils.http.constants.ContentType;
import com.twsny.utils.http.constants.RequestMethodType;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class PushRequest {
    private String url;
    private RequestMethodType methodType;
    private ContentType contentType;
    private Object requestBody;
    Map<String, String> paramMap;
    Map<String, String> headerMap;
    private SerializerFeature serializerFeature;

    private PushRequest(PushRequestBuilder pushRequestBuilder) {
        this.url = pushRequestBuilder.url;
        this.methodType = pushRequestBuilder.methodType;
        this.contentType = pushRequestBuilder.contentType;
        this.requestBody = pushRequestBuilder.requestBody;
        this.paramMap = pushRequestBuilder.paramMap;
        this.headerMap = pushRequestBuilder.headerMap;
        this.serializerFeature = pushRequestBuilder.serializerFeature;
    }

    public static class PushRequestBuilder {
        private String url;
        private RequestMethodType methodType;
        private ContentType contentType;
        private Object requestBody;
        Map<String, String> paramMap;
        Map<String, String> headerMap;
        private SerializerFeature serializerFeature = SerializerFeature.WriteMapNullValue;

        public PushRequestBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public PushRequestBuilder setMethodType(RequestMethodType methodType) {
            this.methodType = methodType;
            return this;
        }

        public PushRequestBuilder setContentType(ContentType contentType) {
            this.contentType = contentType;
            return this;
        }

        public PushRequestBuilder setRequestBody(Object requestBody) {
            this.requestBody = requestBody;
            return this;
        }

        public PushRequestBuilder setParamMap(Map<String, String> paramMap) {
            this.paramMap = paramMap;
            return this;
        }

        public PushRequestBuilder setHeaderMap(Map<String, String> headerMap) {
            this.headerMap = headerMap;
            return this;
        }

        public PushRequestBuilder setSerializerFeature(SerializerFeature serializerFeature) {
            this.serializerFeature = serializerFeature;
            return this;
        }

        public PushRequest build() {
            if (StringUtils.isBlank(url)) throw new IllegalArgumentException("url is empty");
            if (methodType == null) throw new IllegalArgumentException("methodType is null");
            return new PushRequest(this);
        }
    }

    public String getUrl() {
        return url;
    }

    public RequestMethodType getMethodType() {
        return methodType;
    }

    public Object getRequestBody() {
        return requestBody;
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public SerializerFeature getSerializerFeature() {
        return serializerFeature;
    }

    public ContentType getContentType() {
        return contentType;
    }
}
