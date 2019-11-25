package br.com.datamob.binding_cadastro.tela;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;


import br.com.datamob.binding_cadastro.R;
import br.com.datamob.binding_cadastro.databinding.ActivityListaUsuariosBinding;

public class ListaUsuariosActivity extends AppCompatActivity
{
    private Context context = ListaUsuariosActivity.this;
    private Handler handler = new Handler();

    private ActivityListaUsuariosBinding binding;

    private ListaUsuariosViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);
        inicializaComponentes();
        carregaInformacoes();
    }

    private void inicializaComponentes()
    {
        viewModel = new ListaUsuariosViewModel(context, handler);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lista_usuarios);
        binding.setViewModel(viewModel);

    }

    private void carregaInformacoes()
    {
        viewModel.carregaInformacoes();
    }
}
