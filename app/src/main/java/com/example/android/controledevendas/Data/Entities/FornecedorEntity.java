package com.example.android.controledevendas.Data.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.android.controledevendas.Constantes;

import java.io.Serializable;

@Entity(tableName = Constantes.FORNECEDOR_TABLE_NAME)
public class FornecedorEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = Constantes.FORNECEDOR_COLUMN_NAME)
    private String name;

    @ColumnInfo(name = Constantes.FORNECEDOR_COLUMN_CPF_CNPJ)
    private String cpf_cnpj;

    @ColumnInfo(name = Constantes.FORNECEDOR_COLUMN_EMAIL)
    private String email;

    @ColumnInfo(name = Constantes.FORNECEDOR_COLUMN_TELEFONE)
    private String telefone;

    @ColumnInfo(name = Constantes.FORNECEDOR_COLUMN_CELULAR)
    private String celular;

    @ColumnInfo(name = Constantes.FORNECEDOR_COLUMN_CEP)
    private String cep;

    @ColumnInfo(name = Constantes.FORNECEDOR_COLUMN_ENDERECO)
    private String endereco;
    @ColumnInfo(name = Constantes.FORNECEDOR_COLUMN_CIDADE)
    private String cidade;
    @ColumnInfo(name = Constantes.FORNECEDOR_COLUMN_UF)
    private String uf;

    @ColumnInfo(name = Constantes.FORNECEDOR_COLUMN_OBSERVACAO)
    private String observacao;

    @ColumnInfo(name = Constantes.FORNECEDOR_COLUMN_RAMO)
    private String ramo;

    @ColumnInfo(name = Constantes.FORNECEDOR_COLUMN_PRAZO_ENTREGA)
    private String prazo;

    @ColumnInfo(name = Constantes.FORNECEDOR_COLUMN_CONDICOES_PAGAMENTO)
    private String pagamento;


    @Ignore
    public FornecedorEntity(String name, String cpf_cnpj, String email,
                            String telefone, String celular, String cep,
                            String endereco, String cidade,
                            String uf, String observacao, String ramo, String prazo,
                            String pagamento) {
        this.name = name;
        this.cpf_cnpj = cpf_cnpj;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.cep = cep;
        this.endereco = endereco;
        this.cidade = cidade;
        this.uf = uf;
        this.observacao = observacao;
        this.ramo = ramo;
        this.prazo = prazo;
        this.pagamento = pagamento;
    }

    public FornecedorEntity(){

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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }
}
