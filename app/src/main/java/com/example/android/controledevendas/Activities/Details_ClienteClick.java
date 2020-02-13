package com.example.android.controledevendas.Activities;

import android.app.Activity;
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
import com.example.android.controledevendas.R;
import com.example.android.controledevendas.ViewModels.ClienteViewModel;

//implements serializable permite a passagem de objetos entre classes ou activities via intent
public class Details_ClienteClick extends AppCompatActivity {

    private TextView textNome;
    private TextView textTelefone;
    private TextView textEmail;
    private TextView textEndereco;
    private TextView textCep;
    private TextView textCpf;
    private TextView textNascimento;
    private TextView textObservacao;
    private TextView textCelular;
    private ClienteViewModel mClienteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details__cliente_click);

        setTitle(R.string.actionBarTitle_DetalheCliente);

        mClienteViewModel = ViewModelProviders.of(this).get(ClienteViewModel.class);

        Intent i = getIntent();
        ClienteEntity cliente = (ClienteEntity) i.getSerializableExtra("CLIENTE_ATUAL");

        textNome = findViewById(R.id.text_input_cliente_name);
        textNome.setText(cliente.getName());
        textTelefone = findViewById(R.id.text_input_cliente_telefone);
        textTelefone.setText(cliente.getTelefone());
        textEmail = findViewById(R.id.text_input_cliente_email);
        textEmail.setText(cliente.getEmail());
        textEndereco = findViewById(R.id.text_input_cliente_endereco);
        textEndereco.setText(cliente.getEndereco());
        textCep = findViewById(R.id.text_input_cliente_cep);
        textCep.setText(cliente.getCep());
        textCpf = findViewById(R.id.text_input_cliente_CPF_CNPJ);
        textCpf.setText(cliente.getCpf_cnpj());
        textNascimento = findViewById(R.id.text_input_cliente_aniversario);
        textNascimento.setText(cliente.getNascimento());
        textObservacao = findViewById(R.id.text_input_cliente_observacao);
        textObservacao.setText(cliente.getObservacao());
        textCelular = findViewById(R.id.text_input_cliente_celular);
        textCelular.setText(cliente.getCelular());


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
                deleteCliente();
                return true;

            case (R.id.details_edit):
                editCliente();
                return true;

            case (R.id.details_send):
                //saveCliente();
                return true;

            default:
                return super.onOptionsItemSelected(item);


        }

    }

    public void deleteCliente(){
        //Dialog box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_delete_black).setTitle("Excluir Cliente").setMessage("Tem certeza que deseja excluir este cliente " +
                "e todas as suas compras?").setPositiveButton("Sim", dialogClickListener)
                .setNegativeButton("Não", dialogClickListener).show();

    }

    public void editCliente(){
        Intent i = getIntent();
        ClienteEntity cliente = (ClienteEntity) i.getSerializableExtra("CLIENTE_ATUAL");

        Intent intent = new Intent(this, Add_Edit_Cliente_Actitivty.class);
        intent.putExtra("CLIENTE_ATUAL", cliente);
        startActivityForResult(intent, Constantes.REQUEST_CODE_CLIENTE_EDIT);
    }

    //Configura um ClickListener pra uma dialog box de confirmação para exclusão de um cliente.
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked
                    Intent i = getIntent();
                    ClienteEntity cliente = (ClienteEntity) i.getSerializableExtra("CLIENTE_ATUAL");
                    mClienteViewModel.delete(cliente);
                    setResult(RESULT_OK);
                    Toast.makeText(getBaseContext(), "Cliente excluído.", Toast.LENGTH_SHORT).show();
                    finish();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };

    //Executa as ações sobre o result da edição do cliente
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constantes.REQUEST_CODE_CLIENTE_EDIT && resultCode == RESULT_OK && data!= null){
            ClienteEntity cliente = (ClienteEntity) data.getSerializableExtra("CLIENTE_ATUAL");

            textNome = findViewById(R.id.text_input_cliente_name);
            textNome.setText(cliente.getName());
            textTelefone = findViewById(R.id.text_input_cliente_telefone);
            textTelefone.setText(cliente.getTelefone());
            textEmail = findViewById(R.id.text_input_cliente_email);
            textEmail.setText(cliente.getEmail());
            textEndereco = findViewById(R.id.text_input_cliente_endereco);
            textEndereco.setText(cliente.getEndereco());
            textCep = findViewById(R.id.text_input_cliente_cep);
            textCep.setText(cliente.getCep());
            textCpf = findViewById(R.id.text_input_cliente_CPF_CNPJ);
            textCpf.setText(cliente.getCpf_cnpj());
            textNascimento = findViewById(R.id.text_input_cliente_aniversario);
            textNascimento.setText(cliente.getNascimento());
            textObservacao = findViewById(R.id.text_input_cliente_observacao);
            textObservacao.setText(cliente.getObservacao());
            textCelular = findViewById(R.id.text_input_cliente_celular);
            textCelular.setText(cliente.getCelular());
        }
        else {
            Toast.makeText(this, "Cliente não salvo.", Toast.LENGTH_SHORT).show();
        }
    }
}
