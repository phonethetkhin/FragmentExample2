package com.example.fragmentexample2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fragmentexample2.Adapters.SpnAdapter;
import com.example.fragmentexample2.Database.GameDatabase;
import com.example.fragmentexample2.Models.CategoryModel;
import com.example.fragmentexample2.Models.GameModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class UpdateGame extends AppCompatActivity {
    Spinner spnCategoryID;
    TextInputEditText tietGameName,tietPhoto,tietDetail,tietPrice;
    Button btnGoogle,btnSave,btnCancel;
    List<CategoryModel> categoryModelList;
    List<GameModel> gameModelList;
    GameDatabase gdb=new GameDatabase(UpdateGame.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_game);
        //typecast
        spnCategoryID=findViewById(R.id.spnCategoryID);
        tietGameName=findViewById(R.id.tietGameName);
        tietPhoto=findViewById(R.id.tietPhoto);
        tietDetail=findViewById(R.id.tietDetail);
        tietPrice=findViewById(R.id.tietPrice);
        btnGoogle=findViewById(R.id.btnGoogle);
        btnSave=findViewById(R.id.btnSave);
        //spinner
categoryModelList=gdb.GetCategory();
        spnCategoryID.setAdapter(new SpnAdapter(UpdateGame.this,R.layout.spinnerlistitem,R.id.tvCategoryName,categoryModelList));
        //getolddata
        final GameModel gmodel=getIntent().getParcelableExtra("OldValue");
        tietGameName.setText(gmodel.getGameName());
        tietPrice.setText(gmodel.getPrice()+"");
        tietPhoto.setText(gmodel.getPhoto());
        tietDetail.setText(gmodel.getDetail());
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
        // save button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        if(tietGameName.getText().toString().trim().length()<=0||
        tietPhoto.getText().toString().trim().length()<=0||
        tietDetail.getText().toString().trim().length()<=0||
        tietPrice.getText().toString().trim().length()<=0)
        {
            Toast.makeText(UpdateGame.this, "Please Fill All Information", Toast.LENGTH_LONG).show();
        }
        else
        {
            gdb.UpdateGame(gmodel.getGameID(),categoryModelList.get(spnCategoryID.getSelectedItemPosition()).getCategoryID(),
                    tietGameName.getText().toString(),
                    tietPhoto.getText().toString(),
                    tietDetail.getText().toString(),
                    Double.parseDouble(tietPrice.getText().toString()));
            Toast.makeText(UpdateGame.this, "Updated Successfully", Toast.LENGTH_LONG).show();
            finish();
        }


            }
        });
        getSupportActionBar().setTitle("Update Game");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
