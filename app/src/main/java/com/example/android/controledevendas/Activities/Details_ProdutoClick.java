package com.example.android.controledevendas.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.controledevendas.Constantes;
import com.example.android.controledevendas.Data.Entities.ClienteEntity;
import com.example.android.controledevendas.Data.Entities.ProdutoEntity;
import com.example.android.controledevendas.R;
import com.example.android.controledevendas.ViewModels.ProdutoViewModel;

//implements serializable permite a passagem de objetos entre classes ou activities via intent
public class Details_ProdutoClick extends AppCompatActivity {

    private TextView textNome;
    private TextView textCodigo;
    private TextView textMarca;
    private TextView textPreco_Custo;
    private TextView textPreco_Venda;
    private TextView textCodigoBarras;
    private TextView textPeso;
    private TextView textTamanho;
    private TextView textEstoque;
    private TextView textObservacao;
    private ImageView imageProduto;
    private ProdutoViewModel mProdutoViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details__produto_click);

        setTitle(R.string.actionBarTitle_DetalheProduto);

        mProdutoViewModel = ViewModelProviders.of(this).get(ProdutoViewModel.class);

        Intent intent = getIntent();
        ProdutoEntity produto = (ProdutoEntity) intent.getSerializableExtra("PRODUTO_ATUAL");

        textNome = findViewById(R.id.text_input_produto_nome);
        textCodigo = findViewById(R.id.text_input_produto_codigo);
        textMarca =  findViewById(R.id.text_input_produto_marca);
        textPreco_Custo = findViewById(R.id.text_input_produto_preco_custo);
        textPreco_Venda = findViewById(R.id.text_input_produto_preco_venda);
        textCodigoBarras = findViewById(R.id.text_input_produto_codigo_barras);
        textPeso = findViewById(R.id.text_input_produto_peso);
        textTamanho = findViewById(R.id.text_input_produto_tamanho);
        textEstoque = findViewById(R.id.text_input_produto_estoque);
        textObservacao = findViewById(R.id.text_input_produto_observacao);
        imageProduto = findViewById(R.id.details_produto_image);

        textNome.setText(produto.getName());
        textCodigo.setText(produto.getCodigo());
        textMarca.setText(produto.getMarca());
        textPreco_Custo.setText(produto.getPreco_custo());
        textPreco_Venda.setText(produto.getPreco_venda());
        textCodigoBarras.setText(produto.getCodigo_barras());
        textPeso.setText(produto.getPeso());
        textObservacao.setText(produto.getObservacao());
        textTamanho.setText(produto.getTamanho());
        textEstoque.setText(produto.getEstoque());
        if(produto.getImageStringUri()!=null) {
            imageProduto.setImageURI(Uri.parse(produto.getImageStringUri()));
        }
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
                deleteProduto();
                return true;

            case (R.id.details_edit):
                editProduto();
                return true;

            case (R.id.details_send):
                //sendProduto();
                return true;

            default:
                return super.onOptionsItemSelected(item);


        }

    }

    public void deleteProduto(){
        //Dialog box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_delete_black).setTitle("Excluir Produto").setMessage("Tem certeza que deseja excluir este produto?")
                .setPositiveButton("Sim", dialogClickListener)
                .setNegativeButton("Não", dialogClickListener).show();
    }

    //Configura um ClickListener pra uma dialog box de confirmação para exclusão de um Produto.
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked
                    Intent i = getIntent();
                    ProdutoEntity produto = (ProdutoEntity) i.getSerializableExtra("PRODUTO_ATUAL");
                    mProdutoViewModel.delete(produto);
                    setResult(RESULT_OK);
                    Toast.makeText(getBaseContext(), "Produto excluído.", Toast.LENGTH_SHORT).show();
                    finish();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };

    public void editProduto(){
        Intent i = getIntent();
        ProdutoEntity produto = (ProdutoEntity) i.getSerializableExtra("PRODUTO_ATUAL");

        Intent intent = new Intent(this, Add_Edit_Produto_Activity.class);
        intent.putExtra("PRODUTO_ATUAL", produto);
        startActivityForResult(intent, Constantes.REQUEST_CODE_ESTOQUE_EDIT);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Atualizando a activity details no retorno da activity edit
        if (requestCode == Constantes.REQUEST_CODE_ESTOQUE_EDIT && resultCode == RESULT_OK && data!= null){
            ProdutoEntity produto = (ProdutoEntity) data.getSerializableExtra("PRODUTO_ATUAL");

            textNome = findViewById(R.id.text_input_produto_nome);
            textCodigo = findViewById(R.id.text_input_produto_codigo);
            textMarca =  findViewById(R.id.text_input_produto_marca);
            textPreco_Custo = findViewById(R.id.text_input_produto_preco_custo);
            textPreco_Venda = findViewById(R.id.text_input_produto_preco_venda);
            textCodigoBarras = findViewById(R.id.text_input_produto_codigo_barras);
            textPeso = findViewById(R.id.text_input_produto_peso);
            textTamanho = findViewById(R.id.text_input_produto_tamanho);
            textEstoque = findViewById(R.id.text_input_produto_estoque);
            textObservacao = findViewById(R.id.text_input_produto_observacao);
            imageProduto = findViewById(R.id.details_produto_image);

            textNome.setText(produto.getName());
            textCodigo.setText(produto.getCodigo());
            textMarca.setText(produto.getMarca());
            textPreco_Custo.setText(produto.getPreco_custo());
            textPreco_Venda.setText(produto.getPreco_venda());
            textCodigoBarras.setText(produto.getCodigo_barras());
            textPeso.setText(produto.getPeso());
            textObservacao.setText(produto.getObservacao());
            textTamanho.setText(produto.getTamanho());
            textEstoque.setText(produto.getEstoque());
            if(produto.getImageStringUri()!=null) {
                imageProduto.setImageURI(Uri.parse(produto.getImageStringUri()));
            }
            if(produto.getImageStringUri()==null) {
                imageProduto.setImageResource(R.drawable.ic_image_sample_100x100_grey);
            }
        }
        else {
            Toast.makeText(this, "Produto não salvo.", Toast.LENGTH_SHORT).show();
        }
    }
}
