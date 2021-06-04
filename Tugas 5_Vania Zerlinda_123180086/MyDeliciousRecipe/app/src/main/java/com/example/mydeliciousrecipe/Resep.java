package com.example.mydeliciousrecipe;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "reseps")
public class Resep implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private int id;
    @ColumnInfo(name = "Nama")
    private String nama;
    @ColumnInfo(name = "Lama")
    private String lama;
    @ColumnInfo(name = "Orang")
    private String orang;
    @ColumnInfo(name = "Bahan")
    private String bahan;
    @ColumnInfo(name = "Cara")
    private String cara;


    @Ignore
    public Resep(int id, String nama, String lama, String orang, String bahan, String cara) {
        this.id = id;
        this.nama = nama;
        this.lama = lama;
        this.orang = orang;
        this.bahan = bahan;
        this.cara = cara;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLama() {
        return lama;
    }

    public void setLama(String lama) {
        this.lama = lama;
    }

    public String getOrang() {
        return orang;
    }

    public void setOrang(String orang) {
        this.orang = orang;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public String getCara() {
        return cara;
    }

    public void setCara(String cara) {
        this.cara = cara;
    }


    public Resep(String nama, String lama, String orang, String bahan, String cara) {
        this.nama = nama;
        this.lama = lama;
        this.orang = orang;
        this.bahan = bahan;
        this.cara = cara;
    }

    protected Resep(Parcel in) {
        id = in.readInt();
        nama = in.readString();
        lama = in.readString();
        orang = in.readString();
        bahan = in.readString();
        cara = in.readString();
    }

    public static final Creator<Resep> CREATOR = new Creator<Resep>() {
        @Override
        public Resep createFromParcel(Parcel in) {
            return new Resep(in);
        }

        @Override
        public Resep[] newArray(int size) {
            return new Resep[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nama);
        parcel.writeString(lama);
        parcel.writeString(orang);
        parcel.writeString(bahan);
        parcel.writeString(cara);
    }
}
