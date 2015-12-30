package com.example.diegotakei.async_task;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.diegotakei.async_task.asynctask.LoginAsyncTask;

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

                EditText senhaEditText = (EditText) findViewById(R.id.senhaEditText);
                String senha = senhaEditText.getText().toString();

                LoginAsyncTask loginAsyncTask = new LoginAsyncTask(v.getContext());
                String[] valores = {nome,senha};

                loginAsyncTask.execute(valores);
            }
        });





    }



}


