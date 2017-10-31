package com.favor.icegxy.favor.utils;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by icegxy on 2017/10/28.
 * 网络请求工具类
 */

public class HttpUtil {

    private String mUrl;
    private Map<String, String> mParam;
    private HttpRes httpRes;
    private final OkHttpClient okHttpClient = new OkHttpClient();
    Handler handler = new Handler(Looper.getMainLooper());

    public HttpUtil(HttpRes httpRes) {
        this.httpRes = httpRes;
    }

    private void sendHttp(String url, Map<String, String> param, boolean isPost) {
        mUrl = url;
        mParam = param;

        final Request request;
        if (isPost) {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            Iterator<Map.Entry<String, String>> iterator = mParam.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            }
            request = new Request.Builder().url(mUrl).post(builder.build()).build();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator<Map.Entry<String, String>> iterator = mParam.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                stringBuilder.append(entry.getKey() + "=" + entry.getValue() + "&");
            }
            String urlStr = mUrl + "?" + stringBuilder.toString().substring(0, stringBuilder.length() - 1);
            request = new Request.Builder().url(urlStr).build();
        }
        //创建请求队列
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (httpRes != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            httpRes.onFail("请求失败");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (httpRes != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (!response.isSuccessful()) {
                                httpRes.onFail("请求失败：code " + response.code());
                            } else {
                                try {
                                    httpRes.onSuccess(response.body().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    httpRes.onFail("结果转换失败");
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    public void sendPostHttp(String url, Map<String, String> param) {
        sendHttp(url, param, true);
    }

    public void sendGetHttp(String url, Map<String, String> param) {
        sendHttp(url, param, false);
    }

    public interface HttpRes {

        void onSuccess(Object object);

        void onFail(String errMessage);
    }
}
