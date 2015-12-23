package com.example.diegotakei.async_task;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.diegotakei.async_task.asynctask.LoginAsyncTask;

import org.json.JSONObject;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enviar = (Button) findViewById(R.id.asyncTaskButton);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nomeEditText = (EditText) findViewById(R.id.nomeEditText);
                String nome = nomeEditText.getText().toString();

                EditText senhaEditText = (EditText) findViewById(R.id.nomeEditText);
                String senha = nomeEditText.getText().toString();

                LoginAsyncTask loginAsyncTask = new LoginAsyncTask(v.getContext());
                String[] valores = {nome,senha};

                loginAsyncTask.execute(valores);
            }
        });





    }



}


