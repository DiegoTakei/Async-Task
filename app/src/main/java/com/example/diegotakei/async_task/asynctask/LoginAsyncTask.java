package com.example.diegotakei.async_task.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.diegotakei.async_task.util.HttpService;
import com.example.diegotakei.async_task.util.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

/**
 * Created by Diego Takei on 19/12/2015.
 */
public class LoginAsyncTask extends AsyncTask<String, Void, Response> {

    Context context;

    public LoginAsyncTask(Context activity) {

        this.context = activity;
    }

    @Override
    protected void onPreExecute() {

        Log.i("NotificationWearApp", "OnPreExecute");
    }

    @Override
    protected Response doInBackground(String... valores) {

        Log.i("NotificationWearApp", "doInBackground: " + valores[0]);

        Response response = null;

        JSONObject jo = new JSONObject();
        try {

            jo.put("nome", valores[0]);
            jo.put("senha", valores[1]);


        } catch (Exception e) {
            Log.e("", "" + e.getMessage());
        }

        try {
            response = HttpService.sendJsonPostRequest("servicoservlet",jo);

        } catch (MalformedURLException ex) {

            Log.e("NotificationWearApp","MalformedURLException");

        } catch (IOException ex) {

            Log.e("NotificationWearApp","MalformedURLException");
        }

        return response;
    }

    @Override
    protected void onPostExecute(Response response) {

        try {

            int status = response.getStatusCodeHttp();
            JSONObject json = new JSONObject(response.getContentValue());

            if (status == HttpURLConnection.HTTP_OK) {
                String key = json.getString("key");
                Toast.makeText(context, "Key: "+key, Toast.LENGTH_LONG).show();

            }else{
                String erro = json.getString("mensagem");
                Toast.makeText(context, "Mensagem: "+erro, Toast.LENGTH_LONG).show();
            }


        } catch (JSONException e) {

            Log.e("NotificationWearApp", "JSONException");
        }
    }


}
