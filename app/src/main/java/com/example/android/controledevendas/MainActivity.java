package com.example.android.controledevendas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.example.android.controledevendas.Fragments.BackupFragment;
import com.example.android.controledevendas.Fragments.ClienteFragment;
import com.example.android.controledevendas.Fragments.ContasPagarFragment;
import com.example.android.controledevendas.Fragments.ContasReceberFragment;
import com.example.android.controledevendas.Fragments.EstatisticaFragment;
import com.example.android.controledevendas.Fragments.EstoqueFragment;
import com.example.android.controledevendas.Fragments.FornecedorFragment;
import com.example.android.controledevendas.Fragments.VendasFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    boolean doubleBackToExitPressedOnce = false;
    private Toast toastExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //Quando a main activity for criada este código abrirá o fragment inicial, usa-se a verificação savedInstance para evitar criação de mais de um fragment em mudanças de config.
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,
                    new VendasFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_vendas); //Muda o cursor no NavView para a opção inicial.
            getSupportActionBar().setTitle(R.string.menu_vendas);
        }
    }

    @Override
    public void onBackPressed() {
        drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        //Duplo click em voltar para fechar o aplicativo, qnd o drawer não está a mostra, senão ele só fecha o drawer.
        else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            toastExit = Toast.makeText(this, "Pressione novamente para sair", Toast.LENGTH_LONG);
            toastExit.show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }
    }

    //Fechar a toastExit mais rápido depois que o app fechar.
    @Override
    protected void onDestroy(){
        if(toastExit != null)
        toastExit.cancel();
        super.onDestroy();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()){

            case (R.id.nav_vendas):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,
                        new VendasFragment()).commit();
                getSupportActionBar().setTitle(R.string.menu_vendas);
                break;

            case (R.id.nav_clientes):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,
                        new ClienteFragment()).commit();
                getSupportActionBar().setTitle(R.string.menu_clientes);
                break;

            case (R.id.nav_estoque):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,
                        new EstoqueFragment()).commit();
                getSupportActionBar().setTitle(R.string.menu_estoque);
                break;

            case (R.id.nav_estatistica):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,
                        new EstatisticaFragment()).commit();
                getSupportActionBar().setTitle(R.string.menu_estatistica);
                break;

            case (R.id.nav_fornecedores):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,
                        new FornecedorFragment()).commit();
                getSupportActionBar().setTitle(R.string.menu_fornecedores);
                break;

            case (R.id.nav_backup):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,
                        new BackupFragment()).commit();
                getSupportActionBar().setTitle(R.string.menu_backup);
                break;

            case (R.id.nav_config):
                break;

            case (R.id.nav_conta_receber):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,
                        new ContasReceberFragment()).commit();
                getSupportActionBar().setTitle(R.string.menu_contas_receber);
                break;

            case (R.id.nav_contas_pagar):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,
                        new ContasPagarFragment()).commit();
                getSupportActionBar().setTitle(R.string.menu_contas_pagar);
                break;
        }

        drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Os intents para adicionar novos clientes, fornecedores, entre outros, enviam um request_code para identificar de onde vem a chamada do intent, assim o onResult usa o request code para iniciar o fragmento que fez a chamada, atualizando sua lista.

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){

        switch (requestCode) {

            case (Constantes.REQUEST_CODE_VENDAS_ADD):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,
                        new VendasFragment()).commit();
                getSupportActionBar().setTitle(R.string.menu_vendas);
                break;

            case (Constantes.REQUEST_CODE_CLIENTE_ADD):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,
                        new ClienteFragment()).commit();
                getSupportActionBar().setTitle(R.string.menu_clientes);
                break;

            case (Constantes.REQUEST_CODE_ESTOQUE_ADD):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,
                        new EstoqueFragment()).commit();
                getSupportActionBar().setTitle(R.string.menu_estoque);
                break;

            case (Constantes.REQUEST_CODE_ESTATISTICAS_ADD):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,
                        new EstatisticaFragment()).commit();
                getSupportActionBar().setTitle(R.string.menu_estatistica);
                break;

            case (Constantes.REQUEST_CODE_FORNECEDORES_ADD):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,
                        new FornecedorFragment()).commit();
                getSupportActionBar().setTitle(R.string.menu_fornecedores);

                break;

            case (Constantes.REQUEST_CODE_BACKUP_ADD):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,
                        new BackupFragment()).commit();
                getSupportActionBar().setTitle(R.string.menu_backup);
                break;

            case (Constantes.REQUEST_CODE_CONTAS_RECEBER_ADD):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,
                        new ContasReceberFragment()).commit();
                getSupportActionBar().setTitle(R.string.menu_contas_receber);
                break;

            case (Constantes.REQUEST_CODE_CONTAS_PAGAR_ADD):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,
                        new ContasPagarFragment()).commit();
                getSupportActionBar().setTitle(R.string.menu_contas_pagar);
                break;
        }

        }

    }
}
