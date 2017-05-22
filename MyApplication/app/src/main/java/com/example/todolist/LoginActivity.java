package com.example.todolist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DBHandler(this);
    }
    public void signupButtonAction(View v){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.d("CALLED", "OnActivity Result");
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 18  && data !=null) {
            User u = (User) data.getSerializableExtra("user");
            db.addUser(u);

        }


    }
    public void loginButtonAction(View v){
        EditText edit1 = (EditText)findViewById(R.id.username);
        EditText edit2 = (EditText)findViewById(R.id.password);

        String username = edit1.getText().toString();
        String password = edit2.getText().toString();


            User u = db.findUser(username);
           if(u != null)
               if( u.getPassword().equals(password)){
                Intent intent = new Intent(this, ToDo.class);
                startActivity(intent);
            }


    }
}
