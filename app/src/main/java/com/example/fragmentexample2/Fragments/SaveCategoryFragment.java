package com.example.fragmentexample2.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.fragmentexample2.Adapters.CategoryAdapter;
import com.example.fragmentexample2.Database.GameDatabase;
import com.example.fragmentexample2.Models.CategoryModel;
import com.example.fragmentexample2.Models.GameModel;
import com.example.fragmentexample2.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaveCategoryFragment extends Fragment {
TextInputEditText tietCategoryName;
Button btnSave;
RecyclerView rvShowCategory;
List<CategoryModel> categoryModelList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
View v=inflater.inflate(R.layout.fragment_save_category, container, false);
tietCategoryName=v.findViewById(R.id.tietCategoryName);
btnSave=v.findViewById(R.id.btnSave);
rvShowCategory=v.findViewById(R.id.rvShowCategory);
 btnSave.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {

         GameDatabase gdb=new GameDatabase(getContext());
         if(tietCategoryName.getText().toString().trim().length()<=0)
         {
             Toast.makeText(getContext(), "Pleas Fill All Information", Toast.LENGTH_LONG).show();
             tietCategoryName.setError("Fill Here");
         }

         else
         {
             if(gdb.InsertCategory(tietCategoryName.getText().toString()))
             {
                 Toast.makeText(getContext(), "Save Successfully", Toast.LENGTH_LONG).show();
                 tietCategoryName.setText("");
                 tietCategoryName.requestFocus();
                 onResume();
             }
             else
             {
                 Toast.makeText(getContext(), "Save Fail", Toast.LENGTH_LONG).show();
             }
         }
     }
 });

onResume();

return v;

    }

    @Override
    public void onResume() {
        super.onResume();
        GameDatabase gdb=new GameDatabase(getContext());
        categoryModelList=gdb.GetCategory();
        rvShowCategory.setLayoutManager(new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false));
        rvShowCategory.setHasFixedSize(true);
        rvShowCategory.setAdapter(new CategoryAdapter(categoryModelList));

    }
}
