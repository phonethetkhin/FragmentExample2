package com.example.fragmentexample2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TableLayout;

import com.example.fragmentexample2.Fragments.GameListFragment;
import com.example.fragmentexample2.Fragments.SaveCategoryFragment;
import com.example.fragmentexample2.Fragments.SaveGameFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tlMain;
    FrameLayout flMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tlMain=findViewById(R.id.tlMain);
        flMain=findViewById(R.id.flMain);
        tlMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                Fragment fragment=null;
                String title="";
                if(tab.getPosition()==0)
                {
                    fragment=new SaveCategoryFragment();
                    title="Save Category";
                }
                if(tab.getPosition()==1)
                {
                    fragment=new SaveGameFragment();
                    title="Save Game";
                }
                if(tab.getPosition()==2)
                {
                    fragment=new GameListFragment();
                    title="Game List";
                }
                transaction.replace(R.id.flMain,fragment).commit();
                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flMain,new SaveCategoryFragment()).commit();
        getSupportActionBar().setTitle("Save Category");
    }
}
