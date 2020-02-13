package com.example.android.controledevendas.Data.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.android.controledevendas.Constantes;

import java.io.Serializable;

//implements serializable permite a passagem de objetos entre classes ou activities via intent
@Entity(tableName = Constantes.CLIENTE_TABLE_NAME)
public class ClienteEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = Constantes.CLIENTE_COLUMN_NAME)
    private String name;

    @ColumnInfo(name = Constantes.CLIENTE_COLUMN_CPF_CNPJ)
    private String cpf_cnpj;

    @ColumnInfo(name = Constantes.CLIENTE_COLUMN_EMAIL)
    private String email;

    @ColumnInfo(name = Constantes.CLIENTE_COLUMN_TELEFONE)
    private String telefone;

    @ColumnInfo(name = Constantes.CLIENTE_COLUMN_CELULAR)
    private String celular;

    @ColumnInfo(name = Constantes.CLIENTE_COLUMN_NASCIMENTO)
    private String nascimento;

    @ColumnInfo(name = Constantes.CLIENTE_COLUMN_CEP)
    private String cep;

    @ColumnInfo(name = Constantes.CLIENTE_COLUMN_ENDERECO)
    private String endereco;

    @ColumnInfo(name = Constantes.CLIENTE_COLUMN_OBSERVACAO)
    private String observacao;

    @Ignore
    public ClienteEntity(String name, String cpf_cnpj, String email, String telefone,
                         String celular, String nascimento, String cep, String endereco,
                         String observacao) {

        this.name = name;
        this.cpf_cnpj = cpf_cnpj;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.nascimento = nascimento;
        this.cep = cep;
        this.endereco = endereco;
        this.observacao = observacao;
    }

    public ClienteEntity(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
