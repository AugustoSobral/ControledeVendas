package com.example.android.controledevendas.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.controledevendas.Data.Entities.ClienteEntity;
import com.example.android.controledevendas.Data.Entities.ProdutoEntity;
import com.example.android.controledevendas.R;
import com.squareup.picasso.Picasso;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoHolder> implements Filterable {

    private List<ProdutoEntity> produtosLista = new ArrayList<>();
    private List<ProdutoEntity> produtosBusca = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ProdutoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_produto, viewGroup, false);
        return new ProdutoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoAdapter.ProdutoHolder produtoHolder, int position) {
        ProdutoEntity currentProdutos = produtosBusca.get(position);
        produtoHolder.textViewNome.setText(currentProdutos.getName());
        produtoHolder.textViewCodigo.setText(currentProdutos.getCodigo());
        produtoHolder.textViewPreco.setText(currentProdutos.getPreco_venda());
        if(currentProdutos.getImageStringUri() != null) {
            Picasso.get().load(Uri.parse(currentProdutos.getImageStringUri()))
                    .placeholder(R.drawable.ic_image_sample_50x50_grey)
                    .error(R.drawable.ic_image_sample_50x50_grey)
                    .fit().centerCrop().into(produtoHolder.imageProduto);
        }else {
            //Se a uri for nula, setamos a imagem default.
            Picasso.get().load(R.drawable.ic_image_sample_50x50_grey).placeholder(R.drawable.ic_image_sample_50x50_grey)
                    .error(R.drawable.ic_image_sample_50x50_grey).into(produtoHolder.imageProduto);
        }
    }

    @Override
    public int getItemCount() {
        return produtosBusca.size();
    }

    public void setProdutosLista(List<ProdutoEntity> produtosLista) {
        this.produtosLista = produtosLista;
        produtosBusca.clear();
        produtosBusca.addAll(produtosLista);
        notifyDataSetChanged();
    }

    class ProdutoHolder extends RecyclerView.ViewHolder {
        private ImageView imageProduto;
        private TextView textViewNome;
        private TextView textViewCodigo;
        private TextView textViewPreco;

        public ProdutoHolder(View itemView) {
            super(itemView);
            imageProduto = itemView.findViewById(R.id.list_produto_imagem);
            textViewNome = itemView.findViewById(R.id.list_produto_nome);
            textViewCodigo = itemView.findViewById(R.id.list_produto_codigo);
            textViewPreco = itemView.findViewById(R.id.list_produto_preco);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.OnItemClick(produtosBusca.get(position));
                    }
                }
            });
        }
    }

        //Codificando a interface OnItemClickListener manualmente:
        public interface OnItemClickListener{
            void OnItemClick(ProdutoEntity produto);
        }

        public void setOnItemClickListener(ProdutoAdapter.OnItemClickListener listener){
            this.listener = listener;
        }

    @Override
    public Filter getFilter() {
        return namesFilter;
    }

    private Filter namesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ProdutoEntity> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(produtosLista);                                             //Quando não há nada digitado na pesquisa, mostramos a lista completa (padrão).
            }else{
                //Trasforma a CharSequence em uma String, coloca tudo minúsculo e remove espaços.
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(ProdutoEntity produto : produtosLista){                                     //Percorre cliente por cliente da lista de clientes.
                    String nomeNoASCII;
                    nomeNoASCII = Normalizer.normalize(produto.getName(), Normalizer.Form.NFD)
                            .replaceAll("[^\\p{ASCII}]", "");
                    if(nomeNoASCII.toLowerCase().contains(filterPattern)){                          //Se a CharSequence digitada estiver contida no cliente.getName, adicionamos esse cliente ao resultado da busca.
                        filteredList.add(produto);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            produtosBusca.clear();
            produtosBusca.addAll((List)results.values);                                             //A variável clienteBusca só contém os itens que condizem com a busca agora.
            notifyDataSetChanged();
        }
    };


}

