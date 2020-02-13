package com.example.android.controledevendas.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.android.controledevendas.Data.Daos.ClienteDao;
import com.example.android.controledevendas.Data.Daos.FornecedorDao;
import com.example.android.controledevendas.Data.DataBase;
import com.example.android.controledevendas.Data.Entities.ClienteEntity;
import com.example.android.controledevendas.Data.Entities.FornecedorEntity;

import java.util.List;

public class FornecedorRepository {

    private FornecedorDao mFornecedorDao;
    private LiveData<List<FornecedorEntity>> allFornecedores;

    public FornecedorRepository(Application application){

        DataBase db = DataBase.getInstance(application);
        mFornecedorDao = db.getFornecedorDao();
        allFornecedores = mFornecedorDao.getAllFornecedores();

    }

    public void insert(FornecedorEntity fornecedor){

        new FornecedorRepository.InsertFornecedorAsyncTask(mFornecedorDao).execute(fornecedor);
    }

    public void update(FornecedorEntity fornecedor){
        new FornecedorRepository.UpdateFornecedorAsyncTask(mFornecedorDao).execute(fornecedor);
    }

    public void delete(FornecedorEntity fornecedor){

        new FornecedorRepository.DeleteFornecedorAsyncTask(mFornecedorDao).execute(fornecedor);
    }

    public LiveData<List<FornecedorEntity>> getAllFornecedores() {
        return allFornecedores;
    }


    private static class InsertFornecedorAsyncTask extends AsyncTask<FornecedorEntity, Void, Void>{

        private FornecedorDao AsyncFornecedorDao;

        private InsertFornecedorAsyncTask(FornecedorDao fornecedorDao){
            AsyncFornecedorDao = fornecedorDao;
        }


        @Override
        protected Void doInBackground(FornecedorEntity... fornecedores) {
            AsyncFornecedorDao.insert(fornecedores[0]);
            return null;
        }
    }

    private static class UpdateFornecedorAsyncTask extends AsyncTask<FornecedorEntity, Void, Void> {

        private FornecedorDao AsyncFornecedorDao;

        private UpdateFornecedorAsyncTask(FornecedorDao fornecedorDao){
            AsyncFornecedorDao = fornecedorDao;
        }


        @Override
        protected Void doInBackground(FornecedorEntity... fornecedores) {
            AsyncFornecedorDao.update(fornecedores[0]);
            return null;
        }
    }

    private static class DeleteFornecedorAsyncTask extends AsyncTask<FornecedorEntity, Void, Void>{

        private FornecedorDao AsyncFornecedorDao;

        private DeleteFornecedorAsyncTask(FornecedorDao fornecedorDao){
            AsyncFornecedorDao = fornecedorDao;
        }


        @Override
        protected Void doInBackground(FornecedorEntity... fornecedores) {
            AsyncFornecedorDao.delete_fornecedor(fornecedores[0]);
            return null;
        }
    }

}
