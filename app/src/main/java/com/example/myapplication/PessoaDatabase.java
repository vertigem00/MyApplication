package com.example.myapplication;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Pessoa.class}, version = 1)
public abstract class PessoaDatabase extends RoomDatabase {
    public abstract PessoaDao pessoaDao();

    private static volatile PessoaDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PessoaDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PessoaDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PessoaDatabase.class, "pessoa_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
