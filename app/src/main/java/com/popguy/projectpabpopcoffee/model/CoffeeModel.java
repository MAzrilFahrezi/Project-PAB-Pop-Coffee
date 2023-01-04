package com.popguy.projectpabpopcoffee.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CoffeeModel implements Parcelable {

    public List<Data> data;

    protected CoffeeModel(Parcel in) {
    }

    public static final Creator<CoffeeModel> CREATOR = new Creator<CoffeeModel>() {
        @Override
        public CoffeeModel createFromParcel(Parcel in) {
            return new CoffeeModel(in);
        }

        @Override
        public CoffeeModel[] newArray(int size) {
            return new CoffeeModel[size];
        }
    };

    public List<Data> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "CoffeeModel{" +
                "data=" + data +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public class Data {
        Integer id;
        String nama_kopi, asal_kopi, deskripsi_kopi, gambarKopi;

        public Integer getId() {
            return id;
        }

        public String getNama_kopi() {
            return nama_kopi;
        }

        public String getAsal_kopi() {
            return asal_kopi;
        }

        public String getDeskripsi_kopi() {
            return deskripsi_kopi;
        }

        public String getGambarKopi() {
            return gambarKopi;
        }
    }

}
