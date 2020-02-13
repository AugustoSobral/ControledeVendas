package com.example.android.controledevendas.Data.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.android.controledevendas.Constantes;

@Entity(tableName = Constantes.CATEGORIA_TABLE_NAME)
public class CategoryEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = Constantes.CATEGORIA_COLUMN_NAME)
    private String categoria;

    @Ignore
    public CategoryEntity(String categoria) {
        this.categoria = categoria;
    }

    public CategoryEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
