package com.example.android.controledevendas.Data.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.controledevendas.Data.Entities.CategoryEntity;
import com.example.android.controledevendas.Data.Entities.ClienteEntity;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insert_categoria(CategoryEntity categoria);

    @Update
    void update_categoria(CategoryEntity categoria);

    @Delete
    void delete_categoria(CategoryEntity categoria);

    @Query("SELECT * FROM categoria_table")
    LiveData<List<CategoryEntity>> getAllCategorias();

}
