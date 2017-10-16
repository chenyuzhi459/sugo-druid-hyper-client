package io.druid.hyper.client.util;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HttpClientUtil {

    private static final long DEFAULT_TIME_OUT = 30 * 60; // 30 minutes

    private static final MediaType DEFAULT_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    public static String get(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        String result = response.body().string();

        return result;
    }

    public static String post(String url, String jsonData) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .build();

        RequestBody body = RequestBody.create(DEFAULT_MEDIA_TYPE, jsonData);
        Request request = new Request.Builder().url(url).post(body).build();

        Response response = client.newCall(request).execute();
        String result = response.body().string();

        return result;
    }

}

