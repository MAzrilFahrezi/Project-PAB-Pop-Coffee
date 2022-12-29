package com.popguy.projectpabpopcoffee.model;

import java.util.List;

public class CoffeeModel {

    public List<Data> data;

    public List<Data> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "CoffeeModel{" +
                "data=" + data +
                '}';
    }

    public class Data {
        Integer id;
        String nama_kopi, asal_kopi, deskripsi_kopi;

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

    }

}
