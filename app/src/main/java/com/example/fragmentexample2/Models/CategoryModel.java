package com.example.fragmentexample2.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoryModel implements Parcelable {
    private int CategoryID;
    private String CategoryName;

    public CategoryModel(int categoryID, String categoryName) {
        CategoryID = categoryID;
        CategoryName = categoryName;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.CategoryID);
        dest.writeString(this.CategoryName);
    }

    protected CategoryModel(Parcel in) {
        this.CategoryID = in.readInt();
        this.CategoryName = in.readString();
    }

    public static final Parcelable.Creator<CategoryModel> CREATOR = new Parcelable.Creator<CategoryModel>() {
        @Override
        public CategoryModel createFromParcel(Parcel source) {
            return new CategoryModel(source);
        }

        @Override
        public CategoryModel[] newArray(int size) {
            return new CategoryModel[size];
        }
    };
}
