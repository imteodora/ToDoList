package com.example.todolist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


    }
    public void createContButtonAction(View v){
       EditText edit1 = (EditText)findViewById(R.id.username);
       EditText edit2 = (EditText)findViewById(R.id.password);

        String username = edit1.getText().toString();
        String password = edit2.getText().toString();

        if(username.equals("") == false && password.equals("") == false){
            User u = new User(username,password);
            Intent intent = new Intent(this,LoginActivity.class);
            intent.putExtra("user",u);
            setResult(Activity.RESULT_OK, intent);
            startActivityForResult(intent,18);
            finish();

        }

    }
}
