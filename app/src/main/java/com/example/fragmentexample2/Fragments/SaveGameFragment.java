package com.example.fragmentexample2.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fragmentexample2.Adapters.SpnAdapter;
import com.example.fragmentexample2.Database.GameDatabase;
import com.example.fragmentexample2.Models.CategoryModel;
import com.example.fragmentexample2.Models.GameModel;
import com.example.fragmentexample2.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaveGameFragment extends Fragment {
    Spinner spnCategoryID;
    Button btnGoogle,btnSave,btnCancel;
    TextInputEditText tietGameName,tietPhoto,tietDetail,tietPrice;
    List<CategoryModel> categoryModelList;
    List<GameModel> gameModelList;


    public SaveGameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_save_game, container, false);
        //typecast
         spnCategoryID=v.findViewById(R.id.spnCategoryID);
         btnGoogle=v.findViewById(R.id.btnGoogle);
         btnSave=v.findViewById(R.id.btnSave);
         btnCancel=v.findViewById(R.id.btnCancel);
         tietGameName=v.findViewById(R.id.tietGameName);
         tietPhoto=v.findViewById(R.id.tietPhoto);
         tietDetail=v.findViewById(R.id.tietDetail);
         tietPrice=v.findViewById(R.id.tietPrice);
         //spinner
        final GameDatabase gdb=new GameDatabase(getContext());
        categoryModelList=gdb.GetCategory();
        spnCategoryID.setAdapter(new SpnAdapter(getContext(),R.layout.spinnerlistitem,R.id.tvCategoryName,categoryModelList));
        //google button
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW);
                String url="https://www.google.com";
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        //save button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(tietGameName.getText().toString().trim().length()<=0 ||
            tietPhoto.getText().toString().trim().length()<=0 ||
            tietDetail.getText().toString().trim().length()<=0 ||
            tietPrice.getText().toString().trim().length()<=0)
            {
                Toast.makeText(getContext(), "Please Fill All Information", Toast.LENGTH_LONG).show();
            }
            else
            {
            if(gdb.InsertGame(categoryModelList.get(spnCategoryID.getSelectedItemPosition()).getCategoryID(),
                    tietGameName.getText().toString(),
                    tietPhoto.getText().toString(),
                    tietDetail.getText().toString(),
                    Double.parseDouble(tietPrice.getText().toString())))
                {
                    Toast.makeText(getContext(), "Save Successfully", Toast.LENGTH_LONG).show();
                    tietGameName.setText("");
                    tietPhoto.setText("");
                    tietDetail.setText("");
                    tietPrice.setText("");
                    tietGameName.requestFocus();
                }
            else
                {
                    Toast.makeText(getContext(), "Save Fail", Toast.LENGTH_LONG).show();
                }
            }
            }
        });










        return v;
    }

}
