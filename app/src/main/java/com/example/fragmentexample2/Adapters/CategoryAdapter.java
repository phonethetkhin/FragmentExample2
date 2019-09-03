package com.example.fragmentexample2.Adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentexample2.Database.GameDatabase;
import com.example.fragmentexample2.Models.CategoryModel;
import com.example.fragmentexample2.R;
import com.example.fragmentexample2.UpdateCategory;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    List<CategoryModel> categoryModelList;


    public CategoryAdapter(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.categorylistitem,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
holder.tvCategoryName.setText(categoryModelList.get(position).getCategoryName());
holder.imgCategory.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(final View view) {
        PopupMenu popupMenu=new PopupMenu(view.getContext(),view);
        popupMenu.inflate(R.menu.editmenu);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.edit:
                        Intent i=new Intent(view.getContext(), UpdateCategory.class);
                        Bundle b=new Bundle();
                        b.putParcelable("OldValue",categoryModelList.get(position));
                        i.putExtras(b);
                        view.getContext().startActivity(i);
                        return true;

                    case R.id.remove:
                        AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                        builder.setMessage("ARE YOU SURE YOU WANT TO DELETE THIS !!!!!");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                GameDatabase gdb=new GameDatabase(view.getContext());
                                gdb.RemoveCategory(categoryModelList.get(position).getCategoryID());
                                Toast.makeText(view.getContext(), "Deleted Successfully!!!!", Toast.LENGTH_LONG).show();
                                categoryModelList.remove(position);
                                notifyDataSetChanged();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        AlertDialog dialog=builder.create();
                        dialog.show();
                        return true;

                        default:return false;
                }
            }
        });
    }
});
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
    TextView tvCategoryName;
    ImageView imgCategory;
        public ViewHolder(@NonNull View v) {
            super(v);
            tvCategoryName=v.findViewById(R.id.tvCategoryName);
            imgCategory=v.findViewById(R.id.imgCategory);

        }
    }
}
