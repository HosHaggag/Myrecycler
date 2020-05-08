package com.example.myrecycler.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myrecycler.Items;

@Database(entities ={Items.class} ,version =3)
public abstract class AppDataBase extends RoomDatabase {

    private static volatile AppDataBase INSTANCE;

    public abstract ItemsDao itemsDao();

    // i removed the instance block cause it makes a bugs until just fix a bug

    public static AppDataBase getInstance(Context context) {
        if (INSTANCE == null){
            synchronized (AppDataBase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext() , AppDataBase.class,"ContactDatabase.db")
                            .addMigrations()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
