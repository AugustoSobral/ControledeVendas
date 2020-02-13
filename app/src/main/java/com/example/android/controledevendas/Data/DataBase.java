package com.example.android.controledevendas.Data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.android.controledevendas.Constantes;
import com.example.android.controledevendas.Data.Daos.CategoryDao;
import com.example.android.controledevendas.Data.Daos.ClienteDao;
import com.example.android.controledevendas.Data.Daos.FornecedorDao;
import com.example.android.controledevendas.Data.Daos.ProdutoDao;
import com.example.android.controledevendas.Data.Entities.CategoryEntity;
import com.example.android.controledevendas.Data.Entities.ClienteEntity;
import com.example.android.controledevendas.Data.Entities.FornecedorEntity;
import com.example.android.controledevendas.Data.Entities.ProdutoEntity;

@Database(entities = {ClienteEntity.class, FornecedorEntity.class, ProdutoEntity.class, CategoryEntity.class}, version = 2)
public abstract class DataBase extends RoomDatabase {

    private static DataBase instance;

    public abstract FornecedorDao getFornecedorDao();
    public abstract ClienteDao getClienteDao();
    public abstract ProdutoDao getProdutoDao();
    public abstract CategoryDao getCategoryDao();

    public static synchronized DataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DataBase.class, Constantes.DB_NAME)
                    .fallbackToDestructiveMigration().addCallback(roomCallBack).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };





}
