package com.example.android.controledevendas.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.controledevendas.Constantes;
import com.example.android.controledevendas.Data.Entities.ClienteEntity;
import com.example.android.controledevendas.Data.Entities.FornecedorEntity;
import com.example.android.controledevendas.R;
import com.example.android.controledevendas.ViewModels.ClienteViewModel;
import com.example.android.controledevendas.ViewModels.FornecedorViewModel;

public class Details_FornecedorClick extends AppCompatActivity {

    private TextView textNome;
    private TextView textTelefone;
    private TextView textEmail;
    private TextView textEndereco;
    private TextView textCep;
    private TextView textCidade;
    private TextView textUf;
    private TextView textCpf;
    private TextView textObservacao;
    private TextView textCelular;
    private TextView textRamo;
    private TextView textPrazo;
    private TextView textPagamento;
    private FornecedorViewModel mFornecedorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details__fornecedor_click);

        setTitle(R.string.actionBarTitle_DetalheFornecedor);

        mFornecedorViewModel = ViewModelProviders.of(this).get(FornecedorViewModel.class);

        Intent i = getIntent();
        FornecedorEntity fornecedor = (FornecedorEntity) i.getSerializableExtra("FORNECEDOR_ATUAL");

        textNome = findViewById(R.id.text_input_fornecedor_name);
        textNome.setText(fornecedor.getName());
        textTelefone = findViewById(R.id.text_input_fornecedor_telefone);
        textTelefone.setText(fornecedor.getTelefone());
        textEmail = findViewById(R.id.text_input_fornecedor_email);
        textEmail.setText(fornecedor.getEmail());
        textEndereco = findViewById(R.id.text_input_fornecedor_endereco);
        textEndereco.setText(fornecedor.getEndereco());
        textCep = findViewById(R.id.text_input_fornecedor_cep);
        textCep.setText(fornecedor.getCep());
        textCpf = findViewById(R.id.text_input_fornecedor_CPF_CNPJ);
        textCpf.setText(fornecedor.getCpf_cnpj());
        textObservacao = findViewById(R.id.text_input_fornecedor_observacao);
        textObservacao.setText(fornecedor.getObservacao());
        textCelular = findViewById(R.id.text_input_fornecedor_celular);
        textCelular.setText(fornecedor.getCelular());
        textCidade = findViewById(R.id.text_input_fornecedor_cidade);
        textCidade.setText(fornecedor.getCidade());
        textUf = findViewById(R.id.text_input_fornecedor_uf);
        textUf.setText(fornecedor.getUf());
        textRamo = findViewById(R.id.text_input_fornecedor_ramo);
        textRamo.setText(fornecedor.getRamo());
        textPrazo = findViewById(R.id.text_input_fornecedor_prazo);
        textPrazo.setText(fornecedor.getPrazo());
        textPagamento = findViewById(R.id.text_input_fornecedor_pagamento);
        textPagamento.setText(fornecedor.getPagamento());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.details_cliente_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case (android.R.id.home):
                setResult(RESULT_OK);
                finish();
                return true;

            case (R.id.details_delete):
                deleteFornecedor();
                return true;

            case (R.id.details_edit):
                editFornecedor();
                return true;

            case (R.id.details_send):
                //saveCliente();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void deleteFornecedor(){
        //Dialog box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_delete_black).setTitle("Excluir Fornecedor").setMessage("Tem certeza que deseja excluir este Fornecedor?")
                .setPositiveButton("Sim", dialogClickListener)
                .setNegativeButton("Não", dialogClickListener).show();

    }

    //Configura um ClickListener pra uma dialog box de confirmação para exclusão de um cliente.
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked
                    Intent i = getIntent();
                    FornecedorEntity fornecedor = (FornecedorEntity) i.getSerializableExtra("FORNECEDOR_ATUAL");
                    mFornecedorViewModel.delete(fornecedor);
                    setResult(RESULT_OK);
                    Toast.makeText(getBaseContext(), "Fornecedor excluído.", Toast.LENGTH_SHORT).show();
                    finish();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };

    public void editFornecedor(){
        Intent i = getIntent();
        FornecedorEntity fornecedor = (FornecedorEntity) i.getSerializableExtra("FORNECEDOR_ATUAL");

        Intent intent = new Intent(this, Add_Edit_Fornecedor_Activity.class);
        intent.putExtra("FORNECEDOR_ATUAL", fornecedor);
        startActivityForResult(intent, Constantes.REQUEST_CODE_FORNECEDORES_EDIT);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constantes.REQUEST_CODE_FORNECEDORES_EDIT && resultCode == RESULT_OK && data!= null){
            FornecedorEntity fornecedor = (FornecedorEntity) data.getSerializableExtra("FORNECEDOR_ATUAL");

            //Atualizando as views
            textNome = findViewById(R.id.text_input_fornecedor_name);
            textNome.setText(fornecedor.getName());
            textTelefone = findViewById(R.id.text_input_fornecedor_telefone);
            textTelefone.setText(fornecedor.getTelefone());
            textEmail = findViewById(R.id.text_input_fornecedor_email);
            textEmail.setText(fornecedor.getEmail());
            textEndereco = findViewById(R.id.text_input_fornecedor_endereco);
            textEndereco.setText(fornecedor.getEndereco());
            textCep = findViewById(R.id.text_input_fornecedor_cep);
            textCep.setText(fornecedor.getCep());
            textCpf = findViewById(R.id.text_input_fornecedor_CPF_CNPJ);
            textCpf.setText(fornecedor.getCpf_cnpj());
            textObservacao = findViewById(R.id.text_input_fornecedor_observacao);
            textObservacao.setText(fornecedor.getObservacao());
            textCelular = findViewById(R.id.text_input_fornecedor_celular);
            textCelular.setText(fornecedor.getCelular());
            textCidade = findViewById(R.id.text_input_fornecedor_cidade);
            textCidade.setText(fornecedor.getCidade());
            textUf = findViewById(R.id.text_input_fornecedor_uf);
            textUf.setText(fornecedor.getUf());
            textRamo = findViewById(R.id.text_input_fornecedor_ramo);
            textRamo.setText(fornecedor.getRamo());
            textPrazo = findViewById(R.id.text_input_fornecedor_prazo);
            textPrazo.setText(fornecedor.getPrazo());
            textPagamento = findViewById(R.id.text_input_fornecedor_pagamento);
            textPagamento.setText(fornecedor.getPagamento());

        }
        else {
            Toast.makeText(this, "Fornecedor não salvo.", Toast.LENGTH_SHORT).show();
        }
    }
}
