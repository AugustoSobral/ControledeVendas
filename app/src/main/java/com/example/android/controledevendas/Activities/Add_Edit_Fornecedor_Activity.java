package com.example.android.controledevendas.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.android.controledevendas.Data.Entities.FornecedorEntity;
import com.example.android.controledevendas.EditTextFillActions.MaskCepTelData;
import com.example.android.controledevendas.EditTextFillActions.MaskCpfCnpj;
import com.example.android.controledevendas.R;
import com.example.android.controledevendas.ViewModels.FornecedorViewModel;

public class Add_Edit_Fornecedor_Activity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextCpfCnpj;
    private EditText editTextEmail;
    private EditText editTextTelefone;
    private EditText editTextCelular;
    private EditText editTextObservacao;
    private EditText editTextCep;
    private EditText editTextEndereco;
    private EditText editTextCidadde;
    private EditText editTextUF;
    private EditText editTextRamo;
    private EditText editTextPrazo;
    private EditText editTextPagamento;
    private Button btn_more_information;
    private LinearLayout hide_show_linearLayout;

    private FornecedorViewModel mFornecedorViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__fornecedor_);

        hide_show_linearLayout = findViewById(R.id.hide_show_linearLayout_fornecedor);
        hide_show_linearLayout.setVisibility(View.GONE);
        editTextNome = findViewById(R.id.edit_text_nome_fornecedor);

        editTextTelefone = findViewById(R.id.edit_text_telefone_fornecedor);
        editTextTelefone.addTextChangedListener(MaskCepTelData.mask(editTextTelefone, MaskCepTelData.FORMAT_TEL));

        editTextEmail = findViewById(R.id.edit_text_email_fornecedor);
        editTextEndereco = findViewById(R.id.edit_text_endereco_fornecedor);

        editTextCep = findViewById(R.id.edit_text_cep_fornecedor);
        editTextCep.addTextChangedListener(MaskCepTelData.mask(editTextCep, MaskCepTelData.FORMAT_CEP));

        editTextCpfCnpj = findViewById(R.id.edit_text_cpf_cnpj_fornecedor);
        editTextCpfCnpj.addTextChangedListener(MaskCpfCnpj.insert(editTextCpfCnpj));

        editTextObservacao = findViewById(R.id.edit_text_observacao_fornecedor);

        editTextCelular = findViewById(R.id.edit_text_celular_fornecedor);
        editTextCelular.addTextChangedListener(MaskCepTelData.mask(editTextCelular, MaskCepTelData.FORMAT_CELL));

        editTextCidadde = findViewById(R.id.edit_text_cidade_fornecedor);
        editTextUF = findViewById(R.id.edit_text_uf_fornecedor);
        editTextRamo = findViewById(R.id.edit_text_ramo_fornecedor);
        editTextPrazo = findViewById(R.id.edit_text_prazo_fornecedor);
        editTextPagamento = findViewById(R.id.edit_text_pagamento_fornecedor);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_bar_close);

        Intent intent = getIntent();
        if(intent.hasExtra("FORNECEDOR_ATUAL")){
            setTitle("Editar Fornecedor");
            FornecedorEntity fornecedor = (FornecedorEntity) intent.getSerializableExtra("FORNECEDOR_ATUAL");

            editTextNome.setText(fornecedor.getName());
            editTextTelefone.setText(fornecedor.getTelefone());
            editTextEmail.setText(fornecedor.getEmail());
            editTextEndereco.setText(fornecedor.getEndereco());
            editTextCep.setText(fornecedor.getCep());
            editTextCpfCnpj.setText(fornecedor.getCpf_cnpj());
            editTextObservacao.setText(fornecedor.getObservacao());
            editTextCelular.setText(fornecedor.getCelular());
            editTextCidadde.setText(fornecedor.getCidade());
            editTextUF.setText(fornecedor.getUf());
            editTextRamo.setText(fornecedor.getRamo());
            editTextPrazo.setText(fornecedor.getPrazo());
            editTextPagamento.setText(fornecedor.getPrazo());

        }
        else{
            setTitle(R.string.actionBarTitle_NovoFornecedor);
        }

        mFornecedorViewModel = ViewModelProviders.of(this).get(FornecedorViewModel.class);

        btn_more_information = findViewById(R.id.btn_more_information_fornecedor);
        btn_more_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide_show_linearLayout.setVisibility(View.VISIBLE);
                btn_more_information.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_cliente_fornecedor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case (android.R.id.home):
                setResult(RESULT_OK);
                finish();
                return true;

            case (R.id.save_client_fornecedor):
                saveFornecedor();
                return true;

            default:
                return super.onOptionsItemSelected(item);


        }

    }

    public void saveFornecedor() {

        String nome = editTextNome.getText().toString();
        String telefone = editTextTelefone.getText().toString();
        String email = editTextEmail.getText().toString();
        String endereco = editTextEndereco.getText().toString();
        String cep = editTextCep.getText().toString();
        String cpf_cnpj = editTextCpfCnpj.getText().toString();
        String ramo = editTextRamo.getText().toString();
        String observacao = editTextObservacao.getText().toString();
        String celular = editTextCelular.getText().toString();
        String cidade = editTextCidadde.getText().toString();
        String uf = editTextUF.getText().toString();
        String prazo = editTextPrazo.getText().toString();
        String pagamento = editTextPagamento.getText().toString();

        if (nome.trim().isEmpty()) {
            Toast.makeText(this, "Por favor insira um nome para o fornecedor.", Toast.LENGTH_SHORT).show();
            requestFocus(editTextNome);
            return;
        }

        Intent intent = getIntent();
        if (intent.hasExtra("FORNECEDOR_ATUAL")) {
            FornecedorEntity fornecedor_desatualizado = (FornecedorEntity) intent.getSerializableExtra("FORNECEDOR_ATUAL");
            int id = fornecedor_desatualizado.getId();
            FornecedorEntity fornecedor_atualizado = new FornecedorEntity(nome, cpf_cnpj, email, telefone, celular, cep,
                    endereco, cidade, uf, observacao, ramo, prazo, pagamento);
            fornecedor_atualizado.setId(id);
            mFornecedorViewModel.update(fornecedor_atualizado);

            //Para atualizar a activity detalhes, chamamos startActivityForResult para atualizar e passamos o cliente atualizado de volta pelo intent data.
            Intent data = new Intent();
            data.putExtra("FORNECEDOR_ATUAL", fornecedor_atualizado);
            Toast.makeText(this, "Fornecedor Atualizado!", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK, data);
            finish();
        } else {
            //Passar para o objeto Entity na ordem correta
            FornecedorEntity fornecedor = new FornecedorEntity(nome, cpf_cnpj, email, telefone, celular, cep,
                    endereco, cidade, uf, observacao, ramo, prazo, pagamento);

            mFornecedorViewModel.insert(fornecedor);
            setResult(RESULT_OK);
            Toast.makeText(this, "Fornecedor Salvo!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void requestFocus(View view)
    {
        if (view.requestFocus())
        {
            getWindow().setSoftInputMode
                    (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
