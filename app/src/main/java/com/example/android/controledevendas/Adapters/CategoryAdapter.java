package com.example.android.controledevendas.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.controledevendas.Data.Entities.CategoryEntity;
import com.example.android.controledevendas.Data.Entities.ClienteEntity;
import com.example.android.controledevendas.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    private List<CategoryEntity> categoriasLista = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_categoria, viewGroup, false);

        return new CategoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder categoryHolder, int position) {
        CategoryEntity currentCategoria = categoriasLista.get(position);
        categoryHolder.textViewCategoriaNome.setText(currentCategoria.getCategoria());
    }

    public void setCategoriasLista(List<CategoryEntity> categoriasLista){
        this.categoriasLista = categoriasLista;
        notifyDataSetChanged();

    }
    @Override
    public int getItemCount() {
        return categoriasLista.size();
    }

    //Codificando a interface OnItemClickListener manualmente:
    public interface OnItemClickListener{
        void OnItemClick(CategoryEntity categoria);
        void OnDeleteClick(CategoryEntity categoria);
    }

    public void setOnItemClickListener (OnItemClickListener listener){
        this.listener = listener;
    }


    class CategoryHolder extends RecyclerView.ViewHolder{

        private TextView textViewCategoriaNome;
        public ImageView deleteCategoryBtn;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);

            textViewCategoriaNome = itemView.findViewById(R.id.textview_category_name);
            deleteCategoryBtn = itemView.findViewById(R.id.btn_delete_category);

            deleteCategoryBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener!=null && position != RecyclerView.NO_POSITION) {
                        listener.OnDeleteClick(categoriasLista.get(position));
                    }
                }
            });
        }
    }
}
