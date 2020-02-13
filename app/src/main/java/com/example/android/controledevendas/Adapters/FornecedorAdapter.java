package com.example.android.controledevendas.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.example.android.controledevendas.Data.Entities.ClienteEntity;
import com.example.android.controledevendas.Data.Entities.FornecedorEntity;
import com.example.android.controledevendas.R;
import java.util.ArrayList;
import java.util.List;

public class FornecedorAdapter extends RecyclerView.Adapter<FornecedorAdapter.FornecedorHolder> implements Filterable {

    private List<FornecedorEntity> fornecedoresLista = new ArrayList<>();
    private List<FornecedorEntity> fornecedoresBusca = new ArrayList<>();
    private FornecedorAdapter.OnItemClickListener listener;

    @NonNull
    @Override
    public FornecedorAdapter.FornecedorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_cliente_fornecedor, viewGroup, false);
        return new FornecedorAdapter.FornecedorHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FornecedorAdapter.FornecedorHolder fornecedorHolder, int position) {
        FornecedorEntity currentFornecedores = fornecedoresBusca.get(position);
        fornecedorHolder.textViewNome.setText(currentFornecedores.getName());
        fornecedorHolder.textViewEmail.setText(currentFornecedores.getEmail());
        //Quando há telefone e celular setamos a visibilidade da barra para visível.
        if(!currentFornecedores.getTelefone().trim().isEmpty() && !currentFornecedores.getCelular().trim().isEmpty())
            fornecedorHolder.textViewBarra.setVisibility(View.VISIBLE);

        fornecedorHolder.textViewTelefone.setText(currentFornecedores.getTelefone());
        fornecedorHolder.textViewCelular.setText(currentFornecedores.getCelular());
    }

    @Override
    public int getItemCount() {
        return fornecedoresBusca.size();
    }

    public void setFornecedoresLista(List<FornecedorEntity> fornecedoresLista) {
        this.fornecedoresLista = fornecedoresLista;
        fornecedoresBusca.clear();
        fornecedoresBusca.addAll(fornecedoresLista);
        notifyDataSetChanged();
    }

    class FornecedorHolder extends RecyclerView.ViewHolder{
        private TextView textViewNome;
        private TextView textViewTelefone;
        private TextView textViewEmail;
        private TextView textViewCelular;
        private TextView textViewBarra;

        public FornecedorHolder (View itemView){
            super(itemView);
            textViewNome = itemView.findViewById(R.id.text_view_list_name);
            textViewTelefone = itemView.findViewById(R.id.text_view_list_telefone);
            textViewEmail = itemView.findViewById(R.id.text_view_list_email);
            textViewCelular = itemView.findViewById(R.id.text_view_list_celular);
            textViewBarra = itemView.findViewById(R.id.textviewBarra);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener!=null && position != RecyclerView.NO_POSITION) {
                        listener.OnItemClick(fornecedoresBusca.get(position));
                    }
                }
            });
        }
    }

    //Codificando a interface OnItemClickListener manualmente:
    public interface OnItemClickListener{
        void OnItemClick(FornecedorEntity fornecedor);
    }

    public void setOnItemClickListener(FornecedorAdapter.OnItemClickListener listener){
        this.listener = listener;
    }

    //Métodos para implementar a pesquisa em fornecedoresLista pelo nome.
    @Override
    public Filter getFilter() {
        return namesFilter;
    }

    private Filter namesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<FornecedorEntity> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(fornecedoresLista);                      //Quando não há nada digitado na pesquisa, mostramos a lista completa (padrão).
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(FornecedorEntity fornecedor : fornecedoresLista){                                         //Percorre fornecedor por fornecedor da lista de fornecedores.
                    if(fornecedor.getName().toLowerCase().contains(filterPattern)){                           //Se a CharSequence digitada estiver contida no fornecedor.getName, adicionamos esse fornecedor ao resultado da busca.
                        filteredList.add(fornecedor);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            fornecedoresBusca.clear();
            fornecedoresBusca.addAll((List)results.values);                                             //A variável fornecedoresBusca só contém os itens que condizem com a busca agora.
            notifyDataSetChanged();
        }
    };

}
