package com.example.mydeliciousrecipe.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mydeliciousrecipe.Resep;

@Database(entities = Resep.class, version = 1)
public abstract class ResepDatabase extends RoomDatabase {
    private static final String DB_NAME = "db_resep";
    private static ResepDatabase instance;
    public abstract ResepDao resepDao();

    public static ResepDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ResepDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
