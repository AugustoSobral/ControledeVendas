package com.example.android.controledevendas.Data.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.android.controledevendas.Constantes;

import java.io.Serializable;

@Entity(tableName = Constantes.PRODUTO_TABLE_NAME)
public class ProdutoEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = Constantes.PRODUTO_COLUMN_NAME)
    private String name;

    @ColumnInfo(name = Constantes.PRODUTO_COLUMN_CODIGO)
    private String codigo;

    @ColumnInfo(name = Constantes.PRODUTO_COLUMN_MARCA)
    private String marca;

    @ColumnInfo(name = Constantes.PRODUTO_COLUMN_PRECO_CUSTO)
    private String preco_custo;

    @ColumnInfo(name = Constantes.PRODUTO_COLUMN_PRECO_VENDA)
    private String preco_venda;

    @ColumnInfo(name = Constantes.PRODUTO_COLUMN_CODIGO_BARRAS)
    private String codigo_barras;

    @ColumnInfo(name = Constantes.PRODUTO_COLUMN_PESO)
    private String peso;

    @ColumnInfo(name = Constantes.PRODUTO_COLUMN_TAMANHO)
    private String tamanho;

    @ColumnInfo(name = Constantes.PRODUTO_COLUMN_ESTOQUE)
    private String estoque;

    @ColumnInfo(name = Constantes.PRODUTO_COLUMN_OBSERVACAO)
    private String observacao;

    @ColumnInfo(name = Constantes.PRODUTO_COLUMN_IMAGE_PATH)
    private String imageStringUri;

    /*//Contrutor sem imagem
    @Ignore
    public ProdutoEntity(String name, String codigo, String marca, String preco_custo, String preco_venda,
                         String codigo_barras, String peso, String tamanho, String estoque, String observacao) {
        this.name = name;
        this.codigo = codigo;
        this.marca = marca;
        this.preco_custo = preco_custo;
        this.preco_venda = preco_venda;
        this.codigo_barras = codigo_barras;
        this.peso = peso;
        this.tamanho = tamanho;
        this.estoque = estoque;
        this.observacao = observacao;
    }*/

    //Construtor com imagem
    @Ignore
    public ProdutoEntity(String name, String codigo, String marca, String preco_custo, String preco_venda,
                         String codigo_barras, String peso, String tamanho, String estoque, String observacao,
                         String imageStringUri) {
        this.name = name;
        this.codigo = codigo;
        this.marca = marca;
        this.preco_custo = preco_custo;
        this.preco_venda = preco_venda;
        this.codigo_barras = codigo_barras;
        this.peso = peso;
        this.tamanho = tamanho;
        this.estoque = estoque;
        this.observacao = observacao;
        this.imageStringUri = imageStringUri;
    }

    public ProdutoEntity() {

    }

    public String getImageStringUri() {
        return imageStringUri;
    }

    public void setImageStringUri(String imageStringUri) {
        this.imageStringUri = imageStringUri;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPreco_custo() {
        return preco_custo;
    }

    public void setPreco_custo(String preco_custo) {
        this.preco_custo = preco_custo;
    }

    public String getPreco_venda() {
        return preco_venda;
    }

    public void setPreco_venda(String preco_venda) {
        this.preco_venda = preco_venda;
    }

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getEstoque() {
        return estoque;
    }

    public void setEstoque(String estoque) {
        this.estoque = estoque;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
