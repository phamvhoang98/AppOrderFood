package com.example.phamv.apporderfood;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phamv.apporderfood.DAO.NhanVienDAO;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin, btnRegister;
    EditText edlogin_user, edlogin_password;
    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        btnLogin =(Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        edlogin_user = (EditText) findViewById(R.id.edlogin_user);
        edlogin_password = (EditText) findViewById(R.id.edlogin_password);

        nhanVienDAO = new NhanVienDAO(this);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);


        showButtonRegisterAndYes();
    }
    private void showButtonRegisterAndYes(){
        boolean check = nhanVienDAO.checkEmployee();
        if(check){
            btnLogin.setVisibility(View.VISIBLE);
            btnRegister.setVisibility(View.GONE);
        }
        else{
            btnLogin.setVisibility(View.GONE);
            btnRegister.setVisibility(View.VISIBLE);
        }
    }

    private void btnLogin(){
        String sUsername = edlogin_user.getText().toString();
        String sPassword = edlogin_password.getText().toString();
        boolean check =  nhanVienDAO.checkLogin(sUsername,sPassword);
        if(check){
            Toast.makeText(LoginActivity.this, "Đăng nhập thành công !", Toast.LENGTH_SHORT).show();
            Intent iHome= new Intent(LoginActivity.this,HomeActivity.class);
            iHome.putExtra("tendangnhap",edlogin_user.getText().toString());
            startActivity(iHome);
        }
        else{
            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại !", Toast.LENGTH_SHORT).show();

        }
    }

    private void btnRegister(){
        Intent iRegiter = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(iRegiter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showButtonRegisterAndYes();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnLogin:
                btnLogin();
                break;
            case R.id.btnRegister:
                btnRegister();
                break;
        }
    }
}
