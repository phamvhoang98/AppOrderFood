package com.example.phamv.apporderfood.FragmentApp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.phamv.apporderfood.AddTablesActivity;
import com.example.phamv.apporderfood.R;

public class ShowTablesFragment extends Fragment {

    public static int REQUEST_CODE_ADD = 111;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_show_tables,container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itAddTables = menu.add(1,R.id.itaddtables,1,R.string.addtables);
        itAddTables.setIcon(R.drawable.thembanan);
        itAddTables.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itaddtables:
                Intent itAddTables = new Intent(getActivity(),AddTablesActivity.class);
                startActivityForResult(itAddTables,REQUEST_CODE_ADD);

                break;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD ){
            if(resultCode == Activity.RESULT_OK){
                Intent intent = data;
                boolean check = intent.getBooleanExtra("result",false);
                if(check){
                    Toast.makeText(getActivity(),getResources().getString(R.string.addSuccess),Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(),getResources().getString(R.string.addFailed),Toast.LENGTH_SHORT).show();

                }
            }
        }
    }
}
