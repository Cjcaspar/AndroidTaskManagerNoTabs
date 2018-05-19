package com.connercaspar.androidtaskmanagernotabs;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(version = 2, entities = Task.class)
@TypeConverters(DateConverter.class)
abstract class TaskDatabase extends RoomDatabase {

        private static TaskDatabase INSTANCE;

        public abstract TaskDao taskDao();

        public static TaskDatabase getDatabase(Context context) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TaskDatabase.class, "task_database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
            }
            return INSTANCE;
        }
}

