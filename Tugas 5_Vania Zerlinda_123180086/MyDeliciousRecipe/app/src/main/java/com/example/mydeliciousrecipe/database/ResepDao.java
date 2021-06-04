package com.example.mydeliciousrecipe.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mydeliciousrecipe.Resep;

import java.util.List;

@Dao
public interface ResepDao {
    @Query("SELECT*FROM reseps")
    List<Resep> getAllData();

    @Insert
    void insertData(Resep resep);

    @Update
    void updateData(Resep resep);

    @Delete
    void deleteData(Resep resep);
}
