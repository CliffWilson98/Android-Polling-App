package com.example.cliff.polling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                Intent i = new Intent(Login.this, MainActivity.class);

                EditText mEdit = (EditText) findViewById(R.id.enterName);
                String newName = mEdit.getText().toString();

                EditText mEdit2 = (EditText) findViewById(R.id.enterID);
                String newID = mEdit2.getText().toString();

                User.currentUser.setName(newName);
                User.currentUser.setId(newID);
                User.currentUser.fillList();
                startActivity(i);
            }

        });
    }

}

//        String name = ((EditText)(getView().findViewById(R.id.questionTitle))).getText().toString();
//        String id = ((EditText)(getView().findViewById(R.id.questionContent))).getText().toString();

