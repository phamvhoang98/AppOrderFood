package com.example.phamv.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.phamv.apporderfood.DTO.NhanVienDTO;
import com.example.phamv.apporderfood.Database.CreateDatabase;

import java.security.cert.CertificateRevokedException;

public class NhanVienDAO {
    SQLiteDatabase database;

    public NhanVienDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public long ThemNhanVien(NhanVienDTO nhanVienDTO){
        ContentValues contentValues = new ContentValues();

        contentValues.put(CreateDatabase.TB_NHANVIEN_TEDN, nhanVienDTO.getTENDN());
        contentValues.put(CreateDatabase.TB_NHANVIEN_CMND, nhanVienDTO.getCMND());
        contentValues.put(CreateDatabase.TB_NHANVIEN_GIOITINH, nhanVienDTO.getGIOITINH());
        contentValues.put(CreateDatabase.TB_NHANVIEN_MATKHAU, nhanVienDTO.getMATKHAU());
        contentValues.put(CreateDatabase.TB_NHANVIEN_NGAYSINH, nhanVienDTO.getNGAYSINH());

        long kiemtra = database.insert(CreateDatabase.TB_NHANVIEN, null, contentValues);
        return  kiemtra;
    }
    public boolean checkEmployee(){
        String query = "SELECT * FROM "+ CreateDatabase.TB_NHANVIEN;
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.getCount() != 0){
            return  true;
        }
        else return  false;
    }
    public boolean checkLogin(String username, String password){
        String query = "SELECT * FROM " + CreateDatabase.TB_NHANVIEN + " WHERE " + CreateDatabase.TB_NHANVIEN_TEDN + " = '" + username
                + "' AND " + CreateDatabase.TB_NHANVIEN_MATKHAU + " = '" + password + "'";
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.getCount() != 0){
            return  true;
        }
        else return  false;
    }
}
