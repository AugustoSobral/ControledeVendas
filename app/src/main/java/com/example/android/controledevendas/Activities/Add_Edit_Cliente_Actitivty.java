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

import com.example.android.controledevendas.Data.Entities.ClienteEntity;
import com.example.android.controledevendas.EditTextFillActions.MaskCepTelData;
import com.example.android.controledevendas.EditTextFillActions.MaskCpfCnpj;
import com.example.android.controledevendas.R;
import com.example.android.controledevendas.ViewModels.ClienteViewModel;

public class Add_Edit_Cliente_Actitivty extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextTelefone;
    private EditText editTextEmail;
    private EditText editTextEndereco;
    private EditText editTextCep;
    private EditText editTextCpf;
    private EditText editTextNascimento;
    private EditText editTextObservacao;
    private EditText editTextCelular;
    private Button btn_more_information;
    private LinearLayout hide_show_linearLayout;

    private ClienteViewModel mClienteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__cliente__actitivty);

        hide_show_linearLayout = findViewById(R.id.hide_show_linear_layout_cliente);
        hide_show_linearLayout.setVisibility(View.GONE);
        editTextNome = findViewById(R.id.edit_text_nome_cliente);

        editTextTelefone = findViewById(R.id.edit_text_telefone_cliente);
        editTextTelefone.addTextChangedListener(MaskCepTelData.mask(editTextTelefone, MaskCepTelData.FORMAT_TEL));

        editTextEmail = findViewById(R.id.edit_text_email_cliente);
        editTextEndereco = findViewById(R.id.edit_text_endereço_cliente);

        editTextCep = findViewById(R.id.edit_text_cep_cliente);
        editTextCep.addTextChangedListener(MaskCepTelData.mask(editTextCep, MaskCepTelData.FORMAT_CEP));

        editTextCpf = findViewById(R.id.edit_text_cpf_cnpj_cliente);
        editTextCpf.addTextChangedListener(MaskCpfCnpj.insert(editTextCpf));

        editTextNascimento = findViewById(R.id.edit_text_nascimento_cliente);
        editTextNascimento.addTextChangedListener(MaskCepTelData.mask(editTextNascimento, MaskCepTelData.FORMAT_DATE));

        editTextObservacao = findViewById(R.id.edit_text_observacao_cliente);

        editTextCelular = findViewById(R.id.edit_text_celular_cliente);
        editTextCelular.addTextChangedListener(MaskCepTelData.mask(editTextCelular, MaskCepTelData.FORMAT_CELL));

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_bar_close);

        Intent intent = getIntent();
        if(intent.hasExtra("CLIENTE_ATUAL")){                          //Se o intent foi passado com o Extra "CLIENTE_ATUAL, o clinte já existe, e executa-se as ações de update.
            setTitle("Editar Cliente");
            ClienteEntity cliente = (ClienteEntity) intent.getSerializableExtra("CLIENTE_ATUAL");

            editTextNome.setText(cliente.getName());
            editTextTelefone.setText(cliente.getTelefone());
            editTextEmail.setText(cliente.getEmail());
            editTextEndereco.setText(cliente.getEndereco());
            editTextCep.setText(cliente.getCep());
            editTextCpf.setText(cliente.getCpf_cnpj());
            editTextNascimento.setText(cliente.getNascimento());
            editTextObservacao.setText(cliente.getObservacao());
            editTextCelular.setText(cliente.getCelular());

        }else {
            setTitle(R.string.actionBarTitle_NovoCliente);
        }

        mClienteViewModel = ViewModelProviders.of(this).get(ClienteViewModel.class);

        btn_more_information = findViewById(R.id.btn_more_information_cliente);
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
                saveCliente();
                return true;

            default:
                return super.onOptionsItemSelected(item);


        }

    }

    public void saveCliente(){

        String nome = editTextNome.getText().toString();
        String telefone = editTextTelefone.getText().toString();
        String email = editTextEmail.getText().toString();
        String endereco = editTextEndereco.getText().toString();
        String cep = editTextCep.getText().toString();
        String cpf = editTextCpf.getText().toString();
        String nascimento = editTextNascimento.getText().toString();
        String observacao = editTextObservacao.getText().toString();
        String celular = editTextCelular.getText().toString();

        if (nome.trim().isEmpty()){
            Toast.makeText(this, "Por favor insira um nome para o cliente.", Toast.LENGTH_SHORT).show();
            requestFocus(editTextNome);
            return;
        }

        Intent intent = getIntent();

        if(intent.hasExtra("CLIENTE_ATUAL")){
            //Usa-se o mesmo id para o cliente após a atualização
            ClienteEntity cliente_desatualizado = (ClienteEntity) intent.getSerializableExtra("CLIENTE_ATUAL");
            int id = cliente_desatualizado.getId();

            ClienteEntity cliente_atualizado = new ClienteEntity(nome, cpf, email, telefone, celular, nascimento, cep,
                    endereco, observacao);
            cliente_atualizado.setId(id);
            mClienteViewModel.update(cliente_atualizado);

            //Para atualizar a activity detalhes, chamamos startActivityForResult para atualizar e passamos o cliente atualizado de volta pelo intent data.
            Intent data = new Intent();
            data.putExtra("CLIENTE_ATUAL", cliente_atualizado);
            Toast.makeText(this, "Cliente Atualizado!", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK, data);
            finish();

        }
        else {
            ClienteEntity cliente = new ClienteEntity(nome, cpf, email, telefone, celular, nascimento, cep,
                    endereco, observacao);

            mClienteViewModel.insert(cliente);
            Toast.makeText(this, "Cliente Adicionado!", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
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
