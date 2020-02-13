package com.example.android.controledevendas.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.controledevendas.Data.Entities.CategoryEntity;
import com.example.android.controledevendas.Data.Entities.ClienteEntity;
import com.example.android.controledevendas.Repositories.CategoryRepository;
import com.example.android.controledevendas.Repositories.ClienteRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private CategoryRepository repository;
    private LiveData<List<CategoryEntity>> allCategorias;

    public CategoryViewModel(@NonNull Application application) {
        super(application);

        repository = new CategoryRepository(application);
        allCategorias = repository.getAllCategorias();
    }
    public void insert(CategoryEntity categoria){
        repository.insert(categoria);
    }
    public void update(CategoryEntity categoria){
        repository.update(categoria);
    }
    public void delete(CategoryEntity categoria) {
        repository.delete(categoria);
    }
    public LiveData<List<CategoryEntity>> getAllCategorias(){
        return allCategorias;
    }
}
