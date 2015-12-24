package com.example.diegotakei.async_task.util;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Diego Takei on 19/12/2015.
 */
public class HttpService {

    private static final String URL_CONTEXT = "http://192.168.1.178:8080/rest-servlet-service/";

    public static HttpURLConnection sendGetRequest()
            throws MalformedURLException, IOException {

        HttpURLConnection connection = null;

        try {

            URL url = new URL(URL_CONTEXT);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(15000);
            connection.connect();


        } finally {

            connection.disconnect();
        }

        return connection;
    }

    public static void sendJsonPostRequest(String service, JSONObject json) {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(URL_CONTEXT+service);

            String jsonS = json.toString();

            StringEntity se = new StringEntity(json);

            httpPost.setEntity(se);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            HttpResponse httpResponse = httpclient.execute(httpPost);

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
    }

    public static String getHttpContent(HttpURLConnection connection) {

        StringBuilder builder = new StringBuilder();

        try {

            InputStream content = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    content, "iso-8859-1"), 8);

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            content.close();

        } catch (IOException e) {

            Log.e("NotificationWearApp", "IOException: " + e);
        }

        return builder.toString();
    }
}
