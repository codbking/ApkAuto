package codbking.net;

import com.alibaba.fastjson.JSON;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class HttpHelper {

    private static final OkHttpClient client;
    public static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream; charset=utf-8");
    public static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");

    static {
        client = new OkHttpClient();
    }

    public static String post(String url, HashMap<String, String> hashMap, HashMap<String, String> header)
            throws IOException {

        RequestBody body = RequestBody.create(JSON_TYPE, JSON.toJSONString(hashMap));

        Request.Builder request = new Request.Builder().url(url).post(body);

        if (header != null && !header.isEmpty()) {
            for (String key : header.keySet()) {
                request.addHeader(key, header.get(key));
            }
        }

        String result = new String(client.newCall(request.build()).execute().body().bytes());

        return result;
    }

    public static String update(String url, HashMap<String, Object> params) throws IOException {

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        for (String key : params.keySet()) {
            Object object = params.get(key);
            if (object instanceof File) {
                File file = (File) object;
                builder.addFormDataPart(key, file.getName(), RequestBody.create(FILE_TYPE, file));
            } else {
                builder.addFormDataPart(key, object.toString());
            }
        }

        RequestBody body = builder.build();
        Request request = new Request.Builder().url(url).post(body).build();
        client.newBuilder().writeTimeout(5000, TimeUnit.SECONDS).build().newCall(request);

        String result = new String(client.newCall(request).execute().body().bytes());

        return result;
    }

    public static String get(String url, Object params) throws IOException {

        Request request = new Request.Builder().url(url + getParams(params)).build();

        Call call = client.newCall(request);
        ResponseBody body = call.execute().body();

        return body.toString();
    }

    public static String get(String url, HashMap<String, String> params) throws IOException {

        Request request = new Request.Builder().url(url + getParams(params)).build();

        Call call = client.newCall(request);
        ResponseBody body = call.execute().body();

        String result = new String(body.bytes());
        return result;
    }

    public static String getParams(HashMap<String, String> maps) {

        if (maps == null || maps.isEmpty()) {
            return "";
        }

        String params = "";
        for (String key : maps.keySet()) {
            params += "&" + key + "=" + maps.get(key);
        }

        if (!"".equals(params)) {
            params = params.substring(1);
            params = "?" + params;
        }

        return params;
    }

    public static String getParams(Object object) {
        String params = "";

        if (object == null) {
            return params;
        }

        try {
            Field[] fields = object.getClass().getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                params += "&" + field.getName() + "=" + field.get(object);
            }
        } catch (Exception e) {
        }

        if (!"".equals(params)) {
            params = params.substring(1);
            params = "?" + params;
        }

        return params;

    }




}
