package com.example.android.controledevendas.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.android.controledevendas.Data.Daos.ClienteDao;
import com.example.android.controledevendas.Data.Daos.ProdutoDao;
import com.example.android.controledevendas.Data.DataBase;
import com.example.android.controledevendas.Data.Entities.ClienteEntity;
import com.example.android.controledevendas.Data.Entities.ProdutoEntity;

import java.util.List;

public class ProdutoRepository {

    private ProdutoDao produtoDao;
    private LiveData<List<ProdutoEntity>> allProdutos;

    public ProdutoRepository(Application application) {

        DataBase db = DataBase.getInstance(application);
        produtoDao = db.getProdutoDao();
        allProdutos = produtoDao.getAllProdutos();

    }

    public void insert(ProdutoEntity produto){

        new ProdutoRepository.InsertProdutoAsyncTask(produtoDao).execute(produto);
    }

    public void update(ProdutoEntity produto){
        new ProdutoRepository.UpdateProdutoAsyncTask(produtoDao).execute(produto);
    }

    public void delete(ProdutoEntity produto){

        new ProdutoRepository.DeleteProdutoAsyncTask(produtoDao).execute(produto);
    }

    public LiveData<List<ProdutoEntity>> getAllProdutos() {
        return allProdutos;
    }



    private static class InsertProdutoAsyncTask extends AsyncTask<ProdutoEntity, Void, Void>{

        private ProdutoDao AsyncProdutoDao;

        private InsertProdutoAsyncTask(ProdutoDao produtoDao){
            AsyncProdutoDao = produtoDao;
        }


        @Override
        protected Void doInBackground(ProdutoEntity... produtos) {
            AsyncProdutoDao.insert(produtos[0]);
            return null;
        }
    }

    private static class UpdateProdutoAsyncTask extends AsyncTask<ProdutoEntity, Void, Void> {

        private ProdutoDao AsyncProdutoDao;

        private UpdateProdutoAsyncTask(ProdutoDao produtoDao){
            AsyncProdutoDao = produtoDao;
        }


        @Override
        protected Void doInBackground(ProdutoEntity... produtos) {
            AsyncProdutoDao.update(produtos[0]);
            return null;
        }
    }

    private static class DeleteProdutoAsyncTask extends AsyncTask<ProdutoEntity, Void, Void>{

        private ProdutoDao AsyncProdutoDao;

        private DeleteProdutoAsyncTask(ProdutoDao produtoDao){
            AsyncProdutoDao = produtoDao;
        }


        @Override
        protected Void doInBackground(ProdutoEntity... produtos) {
            AsyncProdutoDao.delete(produtos[0]);
            return null;
        }
    }

}
