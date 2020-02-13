package com.example.android.controledevendas.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.controledevendas.Data.Entities.ClienteEntity;
import com.example.android.controledevendas.Data.Entities.ProdutoEntity;
import com.example.android.controledevendas.Repositories.ClienteRepository;
import com.example.android.controledevendas.Repositories.ProdutoRepository;

import java.util.List;

public class ProdutoViewModel extends AndroidViewModel {

    private ProdutoRepository repository;
    private LiveData<List<ProdutoEntity>> allProdutos;

    public ProdutoViewModel(@NonNull Application application) {
        super(application);

        repository = new ProdutoRepository(application);
        allProdutos = repository.getAllProdutos();
    }

    public void insert(ProdutoEntity produto){
        repository.insert(produto);
    }
    public void update(ProdutoEntity produto){
        repository.update(produto);
    }
    public void delete(ProdutoEntity produto) {
        repository.delete(produto);
    }
    public LiveData<List<ProdutoEntity>> getAllProdutos(){
        return allProdutos;
    }
}
