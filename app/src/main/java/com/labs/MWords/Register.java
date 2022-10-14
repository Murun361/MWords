package com.labs.MWords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Register extends AppCompatActivity {
    EditText username,password,confirmpass;
    Button register;
    TextView logintext;
    DB_Helper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=(EditText) findViewById(R.id.RUsername);
        password=(EditText) findViewById(R.id.RPassword);
        confirmpass=(EditText) findViewById(R.id.ConfirmPassword);
        register=(Button) findViewById(R.id.RegisterButton);
        logintext=(TextView) findViewById(R.id.loginText);
        db=new DB_Helper(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String confpass=confirmpass.getText().toString();
                if(user.equals("")||pass.equals("")||confpass.equals("")){
                    Toast.makeText(Register.this,"Та шаардлагатай мэдээллээ бүрэн бөглөнө үү!!",Toast.LENGTH_SHORT).show();
                }
                else
                    if(pass.equals(confpass)){
                        boolean checkUser=db.check_username(user);
                        if(checkUser==false){
                            boolean insert=db.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(Register.this,"Амжилттай бүртгэгдлээ.",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                                Toast.makeText(Register.this,"Бүртгэл амжилтгүй болллоо. Дахин оролдоно уу!",Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(Register.this,"Хэрэглэгч аль хэдийн бүртгэгдсэн байна. Бүртгэлтэй хэрэглэгчээр нэвтэрнэ үү!",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(Register.this,"Нүүц үг таарахгүй байна. Дахин оролдоно уу!",Toast.LENGTH_SHORT).show();
            }
        });
        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });
    }


}