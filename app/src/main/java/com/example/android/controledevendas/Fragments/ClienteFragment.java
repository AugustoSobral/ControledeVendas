package com.example.android.controledevendas.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import com.example.android.controledevendas.Activities.Add_Edit_Cliente_Actitivty;
import com.example.android.controledevendas.Activities.Details_ClienteClick;
import com.example.android.controledevendas.Adapters.ClienteAdapter;
import com.example.android.controledevendas.Constantes;
import com.example.android.controledevendas.Data.Entities.ClienteEntity;
import com.example.android.controledevendas.R;
import com.example.android.controledevendas.ViewModels.ClienteViewModel;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClienteFragment extends Fragment {

    private ClienteViewModel clienteViewModel;
    private ClienteAdapter clienteAdapter;

    public ClienteFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view_fab_only,
                container, false);

        setHasOptionsMenu(true);   //Quando se trabalha com fragment, chamar esse método é necessário para criar o menu.

        FloatingActionButton fab = rootView.findViewById(R.id.fab_generico);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Add_Edit_Cliente_Actitivty.class);
                getActivity().startActivityForResult(intent, Constantes.REQUEST_CODE_CLIENTE_ADD);
            }
        });

        RecyclerView mRecyclerView = rootView.findViewById(R.id.recycler_view_generico);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRecyclerView.setHasFixedSize(true);                                           //Informa ao recyclerView que seu tamanho (altura e largura) independe do conteúdo do adapter, otimizando suas operações com layout.

        clienteAdapter = new ClienteAdapter();
        mRecyclerView.setAdapter(clienteAdapter);

        clienteViewModel = ViewModelProviders.of(this).get(ClienteViewModel.class);         //o ViewModel será destruído quando esta activity for finalizada

        clienteViewModel.getAllClientes().observe(this, new Observer<List<ClienteEntity>>() {
            @Override
            public void onChanged(@Nullable List<ClienteEntity> clientes) {                                         //Quando a lista de todas as notas mudar, isso será observado e uma ação executada.
                //Coloca a lista em ordem alfabética
                sortListAlphabetically(clientes);
                //update RecyclerView.
                clienteAdapter.setClientesListaFull(clientes);

            }
        });

        clienteAdapter.setOnItemClickListener(new ClienteAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(ClienteEntity cliente) {
                Intent intent = new Intent(getActivity(), Details_ClienteClick.class);
                intent.putExtra("CLIENTE_ATUAL",cliente);
                startActivity(intent);
            }
        });


        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_only_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.pesquisa_button);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);                                       //Troca o botão de buscar para confirmar, já que a busca é em tempo real então não faz muito sentido ter o botão "pesquisar"

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Convertendo o texto de entrada para caracteres equivalentes sem acentuação, ç etc
                String texto;
                texto = Normalizer.normalize(newText, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
                //Chamando o filter
                clienteAdapter.getFilter().filter(texto);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);

    }

    private void sortListAlphabetically(List<ClienteEntity> clientesNoAlphabetically){
        Collections.sort(clientesNoAlphabetically, new Comparator<ClienteEntity>() {
            @Override
            public int compare(ClienteEntity o1, ClienteEntity o2) {
                String nome1, nome2;
                nome1 = Normalizer.normalize(o1.getName(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
                nome2 = Normalizer.normalize(o2.getName(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
                return nome1.compareTo(nome2);
            }
        });
        clienteAdapter.notifyDataSetChanged();
    }
}
