package com.example.android.controledevendas.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.android.controledevendas.Data.Daos.CategoryDao;
import com.example.android.controledevendas.Data.Daos.ClienteDao;
import com.example.android.controledevendas.Data.DataBase;
import com.example.android.controledevendas.Data.Entities.CategoryEntity;
import com.example.android.controledevendas.Data.Entities.ClienteEntity;

import java.util.List;

public class CategoryRepository {

    private CategoryDao mCategoryDao;
    private LiveData<List<CategoryEntity>> allCategorias;

    public CategoryRepository(Application application){
        DataBase db = DataBase.getInstance(application);
        mCategoryDao = db.getCategoryDao();
        allCategorias = mCategoryDao.getAllCategorias();
    }

    public void insert(CategoryEntity categoria){

        new CategoryRepository.InsertCategoriaAsyncTask(mCategoryDao).execute(categoria);
    }

    public void update(CategoryEntity categoria){
        new CategoryRepository.UpdateCategoriaAsyncTask(mCategoryDao).execute(categoria);
    }

    public void delete(CategoryEntity categoria){

        new CategoryRepository.DeleteCategoriaAsyncTask(mCategoryDao).execute(categoria);
    }

    public LiveData<List<CategoryEntity>> getAllCategorias() {
        return allCategorias;
    }

    private static class InsertCategoriaAsyncTask extends AsyncTask<CategoryEntity, Void, Void>{

        private CategoryDao AsyncCategoryDao;

        private InsertCategoriaAsyncTask(CategoryDao categoryDao){
            AsyncCategoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(CategoryEntity... categorias) {
            AsyncCategoryDao.insert_categoria(categorias[0]);
            return null;
        }
    }

    private static class UpdateCategoriaAsyncTask extends AsyncTask<CategoryEntity, Void, Void> {

        private CategoryDao AsyncCategoryDao;

        private UpdateCategoriaAsyncTask(CategoryDao categoryDao){
            AsyncCategoryDao = categoryDao;
        }


        @Override
        protected Void doInBackground(CategoryEntity... categorias) {
            AsyncCategoryDao.update_categoria(categorias[0]);
            return null;
        }
    }

    private static class DeleteCategoriaAsyncTask extends AsyncTask<CategoryEntity, Void, Void>{

        private CategoryDao AsyncCategoryDao;

        private DeleteCategoriaAsyncTask(CategoryDao categoryDao){
            AsyncCategoryDao = categoryDao;
        }


        @Override
        protected Void doInBackground(CategoryEntity... categorias) {
            AsyncCategoryDao.delete_categoria(categorias[0]);
            return null;
        }
    }
}
