package com.example.fragmentexample2.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.fragmentexample2.Adapters.GameAdapter;
import com.example.fragmentexample2.Adapters.SpnAdapter;
import com.example.fragmentexample2.Database.GameDatabase;
import com.example.fragmentexample2.Models.CategoryModel;
import com.example.fragmentexample2.Models.GameModel;
import com.example.fragmentexample2.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameListFragment extends Fragment {
    Spinner spnCategoryID;
    RecyclerView rvShowGameList;
    List<CategoryModel> categoryModelList;
    List<GameModel> gameModelList;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_game_list, container, false);
        spnCategoryID=v.findViewById(R.id.spnCategoryID);
        rvShowGameList=v.findViewById(R.id.rvShowGameList);
        //spinner
        GameDatabase gdb=new GameDatabase(getContext());
        categoryModelList=gdb.GetCategory();
        spnCategoryID.setAdapter(new SpnAdapter(getContext(),R.layout.spinnerlistitem,R.id.tvCategoryName,categoryModelList));
        //RecyclerView
        spnCategoryID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                onResume();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        GameDatabase gdb=new GameDatabase(getContext());

        gameModelList=gdb.GetGame(categoryModelList.get(spnCategoryID.getSelectedItemPosition()).getCategoryID());
        rvShowGameList.setLayoutManager(new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false));
        rvShowGameList.setHasFixedSize(true);
        rvShowGameList.setAdapter(new GameAdapter(gameModelList));
    }
}

