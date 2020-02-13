package com.example.android.controledevendas.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.android.controledevendas.Adapters.CategoryAdapter;
import com.example.android.controledevendas.Constantes;
import com.example.android.controledevendas.Data.Entities.CategoryEntity;
import com.example.android.controledevendas.Data.Entities.ClienteEntity;
import com.example.android.controledevendas.R;
import com.example.android.controledevendas.ViewModels.CategoryViewModel;
import com.example.android.controledevendas.ViewModels.ClienteViewModel;

import java.text.Normalizer;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Gerenciar_Categorias_Activity extends AppCompatActivity {

    private ImageButton btnSalvarCategoria;
    private EditText editTetCategoria;
    private CategoryViewModel categoryViewModel;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerenciar_categorias_activity);

        setTitle(R.string.actionBarTitle_Categorias);

        RecyclerView mRecyclerView = findViewById(R.id.recycler_view_categorias);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        categoryAdapter = new CategoryAdapter();
        mRecyclerView.setAdapter(categoryAdapter);

        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);

        categoryViewModel.getAllCategorias().observe(this, new Observer<List<CategoryEntity>>() {
            @Override
            public void onChanged(@Nullable List<CategoryEntity> categoriasLista) {                                         //Quando a lista de todas as notas mudar, isso será observado e uma ação executada.
                //Coloca a lista em ordem alfabética
                sortListAlphabetically(categoriasLista);
                //update RecyclerView.
                categoryAdapter.setCategoriasLista(categoriasLista);

            }
        });

        editTetCategoria = findViewById(R.id.edit_text_categoria);

        btnSalvarCategoria = findViewById(R.id.btn_save_categoria);
        btnSalvarCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoriaName = editTetCategoria.getText().toString();
                if(categoriaName.trim().isEmpty()){
                    Toast.makeText(Gerenciar_Categorias_Activity.this, "Por favor insira um nome para a categoria.", Toast.LENGTH_SHORT).show();
                    return;
                }
                CategoryEntity categoria = new CategoryEntity(categoriaName);
                categoryViewModel.insert(categoria);
                editTetCategoria.getText().clear();
                Toast.makeText(Gerenciar_Categorias_Activity.this, "Categoria Adicionada!", Toast.LENGTH_SHORT).show();
            }
        });

        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(CategoryEntity categoria) {

            }

            @Override
            public void OnDeleteClick(CategoryEntity categoria) {
                categoryViewModel.delete(categoria);
                Toast.makeText(Gerenciar_Categorias_Activity.this, "Categoria Apagada!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sortListAlphabetically(List<CategoryEntity> categoryNoAlphabetically){
        Collections.sort(categoryNoAlphabetically, new Comparator<CategoryEntity>() {
            @Override
            public int compare(CategoryEntity o1, CategoryEntity o2) {
                String nome1, nome2;
                nome1 = Normalizer.normalize(o1.getCategoria(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
                nome2 = Normalizer.normalize(o2.getCategoria(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
                return nome1.compareTo(nome2);
            }
        });
        categoryAdapter.notifyDataSetChanged();
    }
}
