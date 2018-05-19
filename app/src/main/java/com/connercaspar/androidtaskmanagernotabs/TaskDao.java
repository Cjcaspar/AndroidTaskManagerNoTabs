package com.connercaspar.androidtaskmanagernotabs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    //Allow us to get all video games
    @Query("SELECT * FROM task")
    List<Task> getTasks();

    //Allows us to add a single game to the list
    @Insert
    void addTask(Task task);

    //Allows us to update the values of an existing game
    @Update
    void updateTasks(Task task);

    //Allows us to delete a game from the library
    @Delete
    void deleteTask(Task task);
}

