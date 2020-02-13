package com.example.android.controledevendas.Data.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.controledevendas.Data.Entities.ClienteEntity;
import com.example.android.controledevendas.Data.Entities.ProdutoEntity;

import java.util.List;

@Dao
public interface ProdutoDao {

    @Insert
    void insert(ProdutoEntity produto);

    @Update
    void update(ProdutoEntity produto);

    @Delete
    void delete(ProdutoEntity produto);

    @Query("SELECT * FROM produto_table")
    LiveData<List<ProdutoEntity>> getAllProdutos();

}
