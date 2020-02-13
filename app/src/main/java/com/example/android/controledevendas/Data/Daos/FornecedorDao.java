package com.example.android.controledevendas.Data.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.controledevendas.Data.Entities.FornecedorEntity;

import java.util.List;

@Dao
public interface FornecedorDao {

    @Insert
    void insert(FornecedorEntity fornecedor);

    @Update
    void update(FornecedorEntity fornecedor);

    @Delete
    void delete_fornecedor(FornecedorEntity fornecedor);


    @Query("SELECT * FROM fornecedor_table")
    LiveData<List<FornecedorEntity>> getAllFornecedores();

}
