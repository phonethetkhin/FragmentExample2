package com.example.fragmentexample2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.fragmentexample2.Models.CategoryModel;
import com.example.fragmentexample2.Models.GameModel;

import java.util.ArrayList;
import java.util.List;

public class GameDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="GameDatabase";
    private static final int DATABASE_VERSION=1;

    private final String CATEGORY_TABLE="Category";
    private final String GAME_TABLE="Game";

    public GameDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+CATEGORY_TABLE+"(Category_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,Category_Name TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE "+GAME_TABLE+"(Game_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,Category_ID INTEGER,Game_Name TEXT,Photo TEXT,Detail TEXT,Price REAL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean InsertCategory(String CategoryName)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Category_Name",CategoryName);
        try {
            db.insert(CATEGORY_TABLE, null, cv);
            db.close();
            return true;
        }
        catch (Exception e)
        {
            db.close();
            return false;
        }
    }
    public List<CategoryModel> GetCategory()
    {
        List<CategoryModel> categoryModelList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cs=db.rawQuery("SELECT * FROM "+CATEGORY_TABLE,null);
        if(cs.moveToFirst())
        {
            while(!cs.isAfterLast())
            {
                categoryModelList.add(new CategoryModel(cs.getInt(0),cs.getString(cs.getColumnIndex("Category_Name"))));
            cs.moveToNext();
            }
        }
cs.close();
        db.close();
        return categoryModelList;
    }
    public void UpdateCategory(int CategoryID,String CategoryName)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("UPDATE "+CATEGORY_TABLE+" SET Category_Name='"+CategoryName+"' WHERE Category_ID="+CategoryID);
        db.close();
    }
    public void RemoveCategory(int CategoryID)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+CATEGORY_TABLE+" WHERE Category_ID="+CategoryID);
    }
    public boolean InsertGame(int CategoryID,String GameName,String Photo,String Detail,double Price)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Category_ID",CategoryID);
        cv.put("Game_Name",GameName);
        cv.put("Photo",Photo);
        cv.put("Detail",Detail);
        cv.put("Price",Price);
        try {
            db.insert(GAME_TABLE, null, cv);
            db.close();
            return true;
        }
        catch (Exception e)
        {
            db.close();
            return false;
        }
    }
    public List<GameModel> GetGame(int CategoryID)
    {
        List<GameModel> gameModelList=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cs=db.rawQuery("SELECT * FROM "+GAME_TABLE+" WHERE Category_ID="+CategoryID,null);
        if(cs.moveToFirst())
        {
            while (!cs.isAfterLast())
            {
                gameModelList.add(new GameModel(cs.getInt(0),cs.getInt(cs.getColumnIndex("Category_ID")),
                        cs.getString(cs.getColumnIndex("Game_Name")),cs.getString(cs.getColumnIndex("Photo")),cs.getString(cs.getColumnIndex("Detail")),
                        cs.getDouble(cs.getColumnIndex("Price"))));
                cs.moveToNext();
            }
        }
        cs.close();
        db.close();
        return gameModelList;
    }
    public void UpdateGame(int GameID,int CategoryID,String GameName,String Photo,String Detail,double Price)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("UPDATE "+GAME_TABLE+" SET Category_ID="+CategoryID+",Game_Name='"+GameName+"',Photo='"+Photo+"',Detail='"+Detail+"',Price="+Price+" WHERE Game_ID="+GameID);
        db.close();

    }
    public void RemoveGame(int GameID)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+GAME_TABLE+" WHERE Game_ID="+GameID);
    }
}
