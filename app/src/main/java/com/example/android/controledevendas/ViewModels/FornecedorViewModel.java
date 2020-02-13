package com.example.android.controledevendas.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.controledevendas.Data.Entities.ClienteEntity;
import com.example.android.controledevendas.Data.Entities.FornecedorEntity;
import com.example.android.controledevendas.Repositories.FornecedorRepository;

import java.util.List;

public class FornecedorViewModel extends AndroidViewModel {

    private FornecedorRepository repository;
    private LiveData<List<FornecedorEntity>> allFornecedores;

    public FornecedorViewModel(@NonNull Application application) {
        super(application);

        repository = new FornecedorRepository(application);
        allFornecedores = repository.getAllFornecedores();
    }

    public void insert(FornecedorEntity fornecedor){
        repository.insert(fornecedor);
    }
    public void update(FornecedorEntity fornecedor){
        repository.update(fornecedor);
    }
    public void delete(FornecedorEntity fornecedor) {
        repository.delete(fornecedor);
    }
    public LiveData<List<FornecedorEntity>> getAllFornecedor(){
        return allFornecedores;
    }
}
