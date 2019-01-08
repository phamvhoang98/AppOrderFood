package com.example.phamv.apporderfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.phamv.apporderfood.DAO.BanAnDAO;

public class AddTablesActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edAddtables;
    Button btnAddTables;
    BanAnDAO banAnDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_tables);

        edAddtables = findViewById(R.id.edAddTables);
        btnAddTables = findViewById(R.id.btnAddTables);

        banAnDAO = new BanAnDAO(this);

        btnAddTables.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String sTableName = edAddtables.getText().toString();
        if(sTableName != null || sTableName.equals("")){
            boolean check = banAnDAO.addTables(sTableName);
            Intent intent =  new Intent();
            intent.putExtra("result",check);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
