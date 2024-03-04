package com.example.loginform;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class Register extends AppCompatActivity {

    TextView txtlogin;
    EditText edtuser, edtpwd, edtcfpwd;
    Button btnregis;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        linkedbyID();
        setonclick();

    }

    public void linkedbyID(){
        txtlogin = findViewById(R.id.txtlogin);
        edtuser = findViewById(R.id.edtuser);
        edtpwd = findViewById(R.id.edtpwd);
        edtcfpwd = findViewById(R.id.edtcfpwd);
        btnregis = findViewById(R.id.btnregis);
    }

    public void setonclick(){
        btnregis.setOnClickListener(v->{
            String regis_user = edtuser.getText().toString();
            String regis_pwd = edtpwd.getText().toString();
            String regis_cfpwd = edtcfpwd.getText().toString();
            check_regis(regis_pwd, regis_user, regis_cfpwd);

        });

        txtlogin.setOnClickListener(v->{
            finish();
        });
    }

    public void check_regis(String regis_pwd, String regis_user, String regis_cfpwd){
        if (regis_user.length() == 0 && regis_pwd.length() == 0) {
            Toast.makeText(Register.this, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
            edtuser.requestFocus();
        }
        else if (edtuser.length()<3){
            Toast.makeText(Register.this, "Tên đăng nhập phải lớn hơn 3 ký tự",Toast.LENGTH_LONG).show();
            edtuser.selectAll();
            edtuser.requestFocus();
            return;
        }
        else if (regis_pwd.length() <5 || regis_cfpwd.length() < 5){
            Toast.makeText(Register.this, "Mật khẩu phải nhiều hơn 5 ký tự",Toast.LENGTH_LONG).show();
            edtpwd.selectAll();
            edtpwd.requestFocus();
            return;
        }
        else if (!regis_cfpwd.equals(regis_pwd)){
            Toast.makeText(Register.this, "Mật khẩu không khớp",Toast.LENGTH_LONG).show();
            edtcfpwd.selectAll();
            edtcfpwd.requestFocus();
            return;
        }
        else {
            new AlertDialog.Builder(Register.this)
            .setTitle("Thông báo")
            .setMessage("Bạn đã đăng kí thành công!")
            .setPositiveButton("Đóng", (dialog, i) ->{
                Intent intent = new Intent(Register.this, MainActivity.class);
                String signed_name = edtuser.getText().toString();
                String signed_pwd = edtpwd.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("name", signed_name);
                bundle.putString("pwd", signed_pwd);

                intent.putExtra("signed_info", bundle);
                startActivity(intent);
                finish();
            }).show();
        }
    }
}