package com.example.fragmentexample2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fragmentexample2.Database.GameDatabase;
import com.example.fragmentexample2.Models.CategoryModel;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateCategory extends AppCompatActivity {

    TextInputEditText tietCategoryName;
    Button btnSave;
    GameDatabase gdb=new GameDatabase(UpdateCategory.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_category);
        tietCategoryName=findViewById(R.id.tietCategoryName);
        btnSave=findViewById(R.id.btnSave);

        final CategoryModel cmodel=getIntent().getParcelableExtra("OldValue");
        tietCategoryName.setText(cmodel.getCategoryName());
        getSupportActionBar().setTitle("Update Category");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tietCategoryName.getText().toString().trim().length()<=0)
                {
                    tietCategoryName.setError("Fill Here!!!!!");
                    Toast.makeText(UpdateCategory.this, "Please Fill Category Name", Toast.LENGTH_LONG).show();
                }
                else {
                    gdb.UpdateCategory(cmodel.getCategoryID(), tietCategoryName.getText().toString());
                    Toast.makeText(UpdateCategory.this, "Updated Successfully", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
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
