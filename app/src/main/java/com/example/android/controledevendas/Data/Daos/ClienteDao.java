package com.example.android.controledevendas.Data.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.controledevendas.Data.Entities.ClienteEntity;

import java.util.List;

@Dao
public interface ClienteDao {

    @Insert
    void insert_cliente(ClienteEntity cliente);

    @Update
    void update_cliente(ClienteEntity cliente);

    @Delete
    void delete_cliente(ClienteEntity cliente);

    @Query("SELECT * FROM cliente_table")
    LiveData<List<ClienteEntity>> getAllClientes();



}
