package dev.samar.ultimatepokedex;

import android.app.Application;

import net.zetetic.database.sqlcipher.SQLiteDatabase;

import dev.samar.ultimatepokedex.prefs.AppPreferences;
//import dev.samar.ultimatepokedex.sqlite.helper.AppDbHelper;

public class MyApp extends Application {

//    static {
//        System.loadLibrary("sqlcipher");
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        AppDbHelper.initialize(this);
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize AppPreferences here
        AppPreferences.initialize(this);
    }
}


