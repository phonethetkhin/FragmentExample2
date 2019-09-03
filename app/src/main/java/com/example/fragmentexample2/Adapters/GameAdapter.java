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
import com.example.fragmentexample2.Models.GameModel;
import com.example.fragmentexample2.R;
import com.example.fragmentexample2.UpdateGame;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
    List<GameModel> gameModelList;

    public GameAdapter(List<GameModel> gameModelList) {
        this.gameModelList = gameModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.gamelistitem,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Picasso.get().load(gameModelList.get(position).getPhoto()).into(holder.imgPhoto);
holder.tvGameName.setText(gameModelList.get(position).getGameName());
holder.tvPrice.setText("$ "+gameModelList.get(position).getPrice()+" USD");
holder.imgEdit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(final View view) {
        PopupMenu popup=new PopupMenu(view.getContext(),view);
        popup.inflate(R.menu.editmenu);
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.edit:
                        Intent i=new Intent(view.getContext(), UpdateGame.class);
                        Bundle b=new Bundle();
                        b.putParcelable("OldValue",gameModelList.get(position));
                        i.putExtras(b);
                        view.getContext().startActivity(i);
                        return true;
                    case R.id.remove:
                        final GameDatabase gdb=new GameDatabase(view.getContext());
                        AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                        builder.setMessage("ARE YOUR SURE YOU WANT TO DELETE THIS !!!!!");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            gdb.RemoveGame(gameModelList.get(position).getGameID());
                                Toast.makeText(view.getContext(), "Deleted Successfully!!", Toast.LENGTH_LONG).show();
                                gameModelList.remove(position);
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
        return gameModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imgPhoto,imgEdit;
        TextView tvGameName,tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto=itemView.findViewById(R.id.imgPhoto);
            tvGameName=itemView.findViewById(R.id.tvGameName);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            imgEdit=itemView.findViewById(R.id.imgEdit);

        }
    }
}
