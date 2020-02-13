package com.example.android.controledevendas.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.controledevendas.Constantes;
import com.example.android.controledevendas.Data.Entities.ProdutoEntity;
import com.example.android.controledevendas.EditTextFillActions.MoneyTextWatcher;
import com.example.android.controledevendas.R;
import com.example.android.controledevendas.ViewModels.ProdutoViewModel;

public class Add_Edit_Produto_Activity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextCodigo;
    private EditText editTextMarca;
    private EditText editTextPreco_Custo;
    private EditText editTextPreco_Venda;
    private EditText editTextCodigoBarras;
    private EditText editTextPeso;
    private EditText editTextTamanho;
    private EditText editTextEstoque;
    private EditText editTextObservacao;
    private ImageButton imageAddPicture;
    private ImageButton imageReset;
    private ImageView imageProduto;
    private Button gerenciarCategorias;
    private String imageUriParseString;                                                             //imageUriParseString pode ser passado pro Room DB como nulo, caso não houver imagem.
    private ProdutoViewModel mProdutoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__edit__produto_);

        editTextNome = findViewById(R.id.edit_text_nome_produto);
        editTextCodigo = findViewById(R.id.edit_text_codigo_produto);
        editTextMarca =  findViewById(R.id.edit_text_marca_produto);

        editTextPreco_Custo = findViewById(R.id.edit_text_preco_custo_produto);
        editTextPreco_Custo.addTextChangedListener(new MoneyTextWatcher(editTextPreco_Custo));

        editTextPreco_Venda = findViewById(R.id.edit_text_preco_venda_produto);
        editTextPreco_Venda.addTextChangedListener(new MoneyTextWatcher(editTextPreco_Venda));   //Cria a máscara do editText, com os caracteres R$ , .

        editTextCodigoBarras = findViewById(R.id.edit_text_codigo_barras_produto);
        editTextPeso = findViewById(R.id.edit_text_peso_produto);

        editTextTamanho = findViewById(R.id.edit_text_tamanho_produto);
        editTextEstoque = findViewById(R.id.edit_text_estoque_produto);
        editTextObservacao = findViewById(R.id.edit_text_observacao_produto);

        imageAddPicture = findViewById(R.id.btn_image_add_picture);
        imageReset = findViewById(R.id.image_delete);
        imageProduto = findViewById(R.id.image_produto_detail);

        gerenciarCategorias = findViewById(R.id.category_btn);

       getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_bar_close);

       mProdutoViewModel = ViewModelProviders.of(this).get(ProdutoViewModel.class);

        Intent intent = getIntent();
        if(intent.hasExtra("PRODUTO_ATUAL")){                          //Se o intent foi passado com o Extra "PRODUTO_ATUAL, o produto já existe, e executa-se as ações de update.
            setTitle("Editar Produto");
            ProdutoEntity produto = (ProdutoEntity) intent.getSerializableExtra("PRODUTO_ATUAL");

            editTextNome.setText(produto.getName());
            editTextCodigo.setText(produto.getCodigo());
            editTextMarca.setText(produto.getMarca());
            editTextPreco_Custo.setText(produto.getPreco_custo());
            editTextPreco_Venda.setText(produto.getPreco_venda());
            editTextCodigoBarras.setText(produto.getCodigo_barras());
            editTextPeso.setText(produto.getPeso());
            editTextObservacao.setText(produto.getObservacao());
            editTextTamanho.setText(produto.getTamanho());
            editTextEstoque.setText(produto.getEstoque());
            if(produto.getImageStringUri()!=null) {
                imageProduto.setImageURI(Uri.parse(produto.getImageStringUri()));
                //Atualizamos a variável global com o valor da imagem atual, assim caso não seja alterado nada relacionado a imagem, ela permanece igual.
                imageUriParseString = produto.getImageStringUri();
            }

        }else {
            setTitle(R.string.actionBarTitle_NovoProduto);
        }

        imageAddPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addImageIntent = new Intent(Add_Edit_Produto_Activity.this, Image_Cropper_Activity.class);
                startActivityForResult(addImageIntent, Constantes.REQUEST_CODE_ADD_PICTURE);
            }
        });

        imageReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageProduto.setImageResource(R.drawable.ic_image_sample_100x100_grey);
                imageUriParseString = null;
            }
        });

        gerenciarCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent categoryIntent = new Intent(Add_Edit_Produto_Activity.this, Gerenciar_Categorias_Activity.class);
                startActivity(categoryIntent);
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
                saveProduto();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void saveProduto(){

        String nome = editTextNome.getText().toString();
        String codigo = editTextCodigo.getText().toString();
        String marca = editTextMarca.getText().toString();
        String preco_custo = editTextPreco_Custo.getText().toString();
        String preco_venda = editTextPreco_Venda.getText().toString();
        String codigo_barras = editTextCodigoBarras.getText().toString();
        String peso = editTextPeso.getText().toString();
        String tamanho = editTextTamanho.getText().toString();
        String estoque = editTextEstoque.getText().toString();
        String observacao = editTextObservacao.getText().toString();

        if (nome.trim().isEmpty()){
            Toast.makeText(this, "Por favor, insira um nome para o produto.", Toast.LENGTH_SHORT).show();
            requestFocus(editTextNome);
            return;
        }

        Intent intent = getIntent();
        //Atualizar Produto
        if(intent.hasExtra("PRODUTO_ATUAL")){
            //Usa-se o mesmo id para o produto após a atualização
            ProdutoEntity produto_desatualizado = (ProdutoEntity) intent.getSerializableExtra("PRODUTO_ATUAL");
            int id = produto_desatualizado.getId();

            ProdutoEntity produto_atualizado = new ProdutoEntity(nome, codigo, marca, preco_custo, preco_venda, codigo_barras, peso,
                    tamanho, estoque, observacao, imageUriParseString);

            produto_atualizado.setId(id);
            mProdutoViewModel.update(produto_atualizado);

            //Para atualizar a activity detalhes, chamamos startActivityForResult para atualizar e passamos o cliente atualizado de volta pelo intent data.
            Intent data = new Intent();
            data.putExtra("PRODUTO_ATUAL", produto_atualizado);
            Toast.makeText(this, "Produto Atualizado!", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK, data);
            finish();
        }
        //Adicionar Produto
        else {
            ProdutoEntity produto = new ProdutoEntity(nome, codigo, marca, preco_custo, preco_venda, codigo_barras, peso,
                    tamanho, estoque, observacao, imageUriParseString);

            mProdutoViewModel.insert(produto);
            Toast.makeText(this, "Produto Adicionado!", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Constantes.REQUEST_CODE_ADD_PICTURE && resultCode == RESULT_OK && data!= null){

                Uri imageUri = data.getData();
                imageProduto.setImageURI(imageUri);
                imageUriParseString = imageUri.toString();
                Toast.makeText(this, "Imagem Adicionada!", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "Imagem Não Adicionada!", Toast.LENGTH_SHORT).show();
            imageUriParseString = null;
        }

    }

}
