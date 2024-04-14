package com.example.mtl_clothes.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.mtl_clothes.database.model.OrderModel;

@Database(entities = {OrderModel.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class APP_DATABASE extends RoomDatabase {

    public abstract DAO dao();

    private static APP_DATABASE appDatabase;

    public static synchronized APP_DATABASE requestDatabase(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, APP_DATABASE.class, "order_db_2")
                    .allowMainThreadQueries()
                    .setJournalMode(JournalMode.TRUNCATE)
                    .build();
        }
        return appDatabase;
    }

    public static void destroyDatabase() {
        appDatabase = null;
    }

}
