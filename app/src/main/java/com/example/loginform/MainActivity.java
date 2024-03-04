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

public class MainActivity extends AppCompatActivity {
    EditText edtUser, edtPwd;
    Button btnLogin;
    TextView txtSignup;

//    private String root_name;
//    private String pwd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elements_findbyID();
        setonclick();
    }

    public void elements_findbyID(){
        edtUser = findViewById(R.id.edtUser);
        edtPwd = findViewById(R.id.edtPwd);
        btnLogin = findViewById(R.id.btnLogin);
        txtSignup = findViewById(R.id.txtSignup);
    }

    public void setonclick(){
        btnLogin.setOnClickListener(v->{
            String username = edtUser.getText().toString();
            String password = edtPwd.getText().toString();
//            root_name = "Duy";
//            pwd = "12345";

//            if (check_equals_root(username, password, root_name, pwd))
//                checkLogin(username, password);
            if (!check_equals_sign(username, password))
                checkLogin(username, password);
            else checkLogin(username, password);

        });

        txtSignup.setOnClickListener(v-> {
            Intent to_signup = new Intent(MainActivity.this, Register.class);
            startActivity(to_signup);
        });
    }

    public String get_name_signed(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("signed_info");

        if (bundle != null && bundle.containsKey("name")) {
            return bundle.getString("name");
        } else {
            return "";
        }
    }

    public String get_pwd_signed(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("signed_info");

        if (bundle != null && bundle.containsKey("pwd")) {
            return bundle.getString("pwd");
        } else {
            return "";
        }
    }

    public boolean check_equals_root(String username, String password, String root_name, String pwd){
        if (!username.equals(root_name) || !password.equals(pwd)){
            Toast.makeText(MainActivity.this, "Tên đăng nhập hoặc mật khẩu chưa chính xác!", Toast.LENGTH_SHORT).show();
            edtUser.selectAll();
            edtPwd.selectAll();
            edtUser.requestFocus();
            return false;
        }
        return true;
    }


    public boolean check_equals_sign(String username, String password){
        String signed_name = get_name_signed();
        String signed_pwd = get_pwd_signed();
        if (!username.equals(signed_name) || !password.equals(signed_pwd)){
            Toast.makeText(MainActivity.this, "Tên đăng nhập hoặc mật khẩu chưa chính xác!", Toast.LENGTH_SHORT).show();
            edtUser.selectAll();
            edtPwd.selectAll();
            edtUser.requestFocus();
            return false;
        }
        return true;
    }

    public void checkLogin(String username, String password){
        if (username.isEmpty() && password.isEmpty()) {
            Toast.makeText(MainActivity.this, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            edtUser.requestFocus();
        }
        else if (username.length()<3){
            Toast.makeText(MainActivity.this, "Tên đăng nhập phải lớn hơn 3 ký tự",Toast.LENGTH_SHORT).show();
            edtUser.selectAll();
            edtUser.requestFocus();
            return;
        }
        else if (password.length() <5){
            Toast.makeText(MainActivity.this, "Mật khẩu phải nhiều hơn 5 ký tự",Toast.LENGTH_SHORT).show();
            edtPwd.selectAll();
            edtPwd.requestFocus();
            return;
        }
        else {
            new AlertDialog.Builder(MainActivity.this)
            .setTitle("Thông báo")
            .setMessage("Bạn đã đăng nhập thành công!")
            .setPositiveButton("Đóng", (dialog, i) -> {
                Intent to_homepage = new Intent(MainActivity.this, HomePage.class);
                startActivity(to_homepage);
                finish();
            }).show();
            edtUser.setText("");
            edtPwd.setText("");
        }
    }
}