package com.labs.MWords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends AppCompatActivity {
    EditText password,username;
    Button login;
    TextView signup;
    DB_Helper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        login=(Button) findViewById(R.id.loginButton);
        signup=(TextView) findViewById(R.id.signupText);
        db=new DB_Helper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                if(user.equals("")||pass.equals("")){
                    Toast.makeText(Login.this,"Хоосон талбарыг бөглөн нэвтэрнэ үү!",Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean checkuserpass = db.check_username_password(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(Login.this,"Амжилттай нэвтэрлээ.",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                        Toast.makeText(Login.this,"Нэвтрэх нэр эсвэл нууц үг буруу байна.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
                finish();
            }
        });
    }

}