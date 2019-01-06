package com.example.phamv.apporderfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phamv.apporderfood.DAO.NhanVienDAO;
import com.example.phamv.apporderfood.DTO.NhanVienDTO;
import com.example.phamv.apporderfood.Database.CreateDatabase;
import com.example.phamv.apporderfood.FragmentApp.DatePickerFragment;

import java.text.SimpleDateFormat;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener , View.OnFocusChangeListener {

    EditText edUsernameRegister, edPasswordRegister, edDateOfBirthRegister, edIdCardRegister;
    Button btnYesRegister, btnExitRegister;
    RadioGroup rgSex;
    String sSex;
    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        CreateDatabase createDatabase = new CreateDatabase(this);
        createDatabase.open();

        edUsernameRegister = (EditText) findViewById(R.id.edUsernameRegister);
        edPasswordRegister = (EditText) findViewById(R.id.edPasswordRegister);
        edDateOfBirthRegister = (EditText) findViewById(R.id.edDateOfBirthRegister);
        edIdCardRegister  = (EditText) findViewById(R.id.edIdCardRegister);
        btnYesRegister = (Button) findViewById(R.id.btnYesRegister);
        btnExitRegister = (Button) findViewById(R.id.btnExitRegister);
        rgSex = (RadioGroup) findViewById(R.id.rgSexRegister);

        btnYesRegister.setOnClickListener(this);
        btnExitRegister.setOnClickListener(this);
        edDateOfBirthRegister.setOnFocusChangeListener(this);

        nhanVienDAO = new NhanVienDAO(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnYesRegister:
                String sDateOfBirth = edDateOfBirthRegister.getText().toString();
                int  sIdCard = Integer.parseInt(edIdCardRegister.getText().toString());
                String sUsername = edUsernameRegister.getText().toString();
                String sPassword = edPasswordRegister.getText().toString();

                switch(rgSex.getCheckedRadioButtonId()){
                    case R.id.maleRegister:
                        sSex = "Nam";
                        break;
                    case R.id.femaleRegister:
                        sSex = "Nữ";
                        break;
                }

                if(sUsername == null || sUsername.equals("")){
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.fillUserName), Toast.LENGTH_SHORT).show();
                }
                else if(sPassword == null || sPassword.equals("")){
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.fillPassword), Toast.LENGTH_SHORT).show();
                }
                else {
                    NhanVienDTO nhanVienDTO = new NhanVienDTO();
                    nhanVienDTO.setTENDN(sUsername);
                    nhanVienDTO.setCMND(sIdCard);
                    nhanVienDTO.setGIOITINH(sSex);
                    nhanVienDTO.setNGAYSINH(sDateOfBirth);
                    nhanVienDTO.setMATKHAU(sPassword);

                    long kiemtra = nhanVienDAO.ThemNhanVien(nhanVienDTO);
                    if(kiemtra != 0){
                        Toast.makeText(RegisterActivity.this, getResources().getString(R.string.addSuccess), Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(RegisterActivity.this, getResources().getString(R.string.addFailed), Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnExitRegister:
                finish();
                break;
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();
        switch (id){
            case R.id.edDateOfBirthRegister:
                if(b){
                    DatePickerFragment datePickerFragment = new DatePickerFragment();
                    datePickerFragment.show(getFragmentManager(),"Ngày sinh");
                }
                break;
        }
    }

}
