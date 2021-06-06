package com.example.tema3;

import android.app.Application;
import androidx.room.Room;
import com.example.tema3.data.LibraryDatabase;

public class ApplicationController extends Application {
    private static ApplicationController instance;
    private static LibraryDatabase libraryDatabase;
    private static final String libraryDatabaseName = "LibraryDB";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        setupDataBase();
    }

    private void setupDataBase() {
        libraryDatabase = Room.databaseBuilder(
                getApplicationContext(),
                LibraryDatabase.class,
                libraryDatabaseName)
                .build();
    }

    public static LibraryDatabase getLibraryDataBase() {
        return libraryDatabase;
    }
}
