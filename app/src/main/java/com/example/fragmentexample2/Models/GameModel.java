package com.example.fragmentexample2.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class GameModel implements Parcelable {
    private int GameID,CategoryID;
    private String GameName,Photo,Detail;
    private double Price;

    public GameModel(int gameID, int categoryID, String gameName, String photo, String detail, double price) {
        GameID = gameID;
        CategoryID = categoryID;
        GameName = gameName;
        Photo = photo;
        Detail = detail;
        Price = price;
    }

    public int getGameID() {
        return GameID;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public String getGameName() {
        return GameName;
    }

    public String getPhoto() {
        return Photo;
    }

    public String getDetail() {
        return Detail;
    }

    public double getPrice() {
        return Price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.GameID);
        dest.writeInt(this.CategoryID);
        dest.writeString(this.GameName);
        dest.writeString(this.Photo);
        dest.writeString(this.Detail);
        dest.writeDouble(this.Price);
    }

    protected GameModel(Parcel in) {
        this.GameID = in.readInt();
        this.CategoryID = in.readInt();
        this.GameName = in.readString();
        this.Photo = in.readString();
        this.Detail = in.readString();
        this.Price = in.readDouble();
    }

    public static final Parcelable.Creator<GameModel> CREATOR = new Parcelable.Creator<GameModel>() {
        @Override
        public GameModel createFromParcel(Parcel source) {
            return new GameModel(source);
        }

        @Override
        public GameModel[] newArray(int size) {
            return new GameModel[size];
        }
    };
}
