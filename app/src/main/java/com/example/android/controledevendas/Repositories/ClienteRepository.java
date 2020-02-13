package com.example.android.controledevendas.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import com.example.android.controledevendas.Data.Daos.ClienteDao;
import com.example.android.controledevendas.Data.DataBase;
import com.example.android.controledevendas.Data.Entities.ClienteEntity;

import java.util.List;

public class ClienteRepository {

    private ClienteDao clienteDao;
    private LiveData<List<ClienteEntity>> allClientes;

    public ClienteRepository(Application application){

        DataBase db = DataBase.getInstance(application);
        clienteDao = db.getClienteDao();
        allClientes = clienteDao.getAllClientes();

    }

    public void insert(ClienteEntity cliente){

        new InsertClienteAsyncTask(clienteDao).execute(cliente);
    }

    public void update(ClienteEntity cliente){
        new UpdateClienteAsyncTask(clienteDao).execute(cliente);
    }

    public void delete(ClienteEntity cliente){

        new DeleteClienteAsyncTask(clienteDao).execute(cliente);
    }

    public LiveData<List<ClienteEntity>> getAllClientes() {
        return allClientes;
    }




    private static class InsertClienteAsyncTask extends AsyncTask<ClienteEntity, Void, Void>{

        private ClienteDao AsyncClienteDao;

        private InsertClienteAsyncTask(ClienteDao clienteDao){
            AsyncClienteDao = clienteDao;
        }


        @Override
        protected Void doInBackground(ClienteEntity... clientes) {
            AsyncClienteDao.insert_cliente(clientes[0]);
            return null;
        }
    }

    private static class UpdateClienteAsyncTask extends AsyncTask<ClienteEntity, Void, Void>{

        private ClienteDao AsyncClienteDao;

        private UpdateClienteAsyncTask(ClienteDao clienteDao){
            AsyncClienteDao = clienteDao;
        }


        @Override
        protected Void doInBackground(ClienteEntity... clientes) {
            AsyncClienteDao.update_cliente(clientes[0]);
            return null;
        }
    }

    private static class DeleteClienteAsyncTask extends AsyncTask<ClienteEntity, Void, Void>{

        private ClienteDao AsyncClienteDao;

        private DeleteClienteAsyncTask(ClienteDao clienteDao){
            AsyncClienteDao = clienteDao;
        }


        @Override
        protected Void doInBackground(ClienteEntity... clientes) {
            AsyncClienteDao.delete_cliente(clientes[0]);
            return null;
        }
    }

}
