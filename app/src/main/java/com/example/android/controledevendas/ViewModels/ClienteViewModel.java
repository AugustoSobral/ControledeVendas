package com.example.android.controledevendas.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.controledevendas.Data.Entities.ClienteEntity;
import com.example.android.controledevendas.Repositories.ClienteRepository;

import java.util.List;

public class ClienteViewModel extends AndroidViewModel {

    private ClienteRepository repository;
    private LiveData<List<ClienteEntity>> allClientes;

    public ClienteViewModel(@NonNull Application application) {
        super(application);

        repository = new ClienteRepository(application);
        allClientes = repository.getAllClientes();

    }

    public void insert(ClienteEntity cliente){
        repository.insert(cliente);
    }
    public void update(ClienteEntity cliente){
        repository.update(cliente);
    }
    public void delete(ClienteEntity cliente) {
        repository.delete(cliente);
    }
    public LiveData<List<ClienteEntity>> getAllClientes(){
        return allClientes;
    }
}
