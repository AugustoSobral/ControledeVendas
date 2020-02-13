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
import com.example.android.controledevendas.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteHolder> implements Filterable {

    private List<ClienteEntity> clientesListaFull = new ArrayList<>();
    private List<ClienteEntity> clientesBusca = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ClienteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_cliente_fornecedor, viewGroup, false);
        return new ClienteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteHolder clienteHolder, int position) {
        ClienteEntity currentClientes = clientesBusca.get(position);
        clienteHolder.textViewNome.setText(currentClientes.getName());
        clienteHolder.textViewEmail.setText(currentClientes.getEmail());
        if(!currentClientes.getTelefone().trim().isEmpty() && !currentClientes.getCelular().trim().isEmpty())
            clienteHolder.textViewBarra.setVisibility(View.VISIBLE);

        clienteHolder.textViewTelefone.setText(currentClientes.getTelefone());
        clienteHolder.textViewCelular.setText(currentClientes.getCelular());
    }

    public void setClientesListaFull(List<ClienteEntity> clientesListaFull) {
        this.clientesListaFull = clientesListaFull;
        clientesBusca.clear();
        clientesBusca.addAll(clientesListaFull);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return clientesBusca.size();
    }

    public List<ClienteEntity> getFilteredClienteList(){
        return clientesBusca;
    }

    class ClienteHolder extends RecyclerView.ViewHolder{
        private TextView textViewNome;
        private TextView textViewTelefone;
        private TextView textViewEmail;
        private TextView textViewCelular;
        private TextView textViewBarra;

        public ClienteHolder (View itemView){
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
                        listener.OnItemClick(clientesBusca.get(position));
                    }
                }
            });
        }
    }

    //Codificando a interface OnItemClickListener manualmente:
    public interface OnItemClickListener{
        void OnItemClick(ClienteEntity cliente);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    //Métodos para implementar a pesquisa em clientesListaFull pelo nome.
    @Override
    public Filter getFilter() {
        return namesFilter;
    }



    private Filter namesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ClienteEntity> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(clientesListaFull);                                             //Quando não há nada digitado na pesquisa, mostramos a lista completa (padrão).
            }else{
                //Trasforma a CharSequence em uma String, coloca tudo minúsculo e remove espaços.
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(ClienteEntity cliente : clientesListaFull){                                     //Percorre cliente por cliente da lista de clientes.
                    String nomeNoASCII;
                    nomeNoASCII = Normalizer.normalize(cliente.getName(), Normalizer.Form.NFD)
                            .replaceAll("[^\\p{ASCII}]", "");
                    if(nomeNoASCII.toLowerCase().contains(filterPattern)){                          //Se a CharSequence digitada estiver contida no cliente.getName, adicionamos esse cliente ao resultado da busca.
                        filteredList.add(cliente);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clientesBusca.clear();
            clientesBusca.addAll((List)results.values);                                             //A variável clienteBusca só contém os itens que condizem com a busca agora.
            notifyDataSetChanged();
        }
    };

}
