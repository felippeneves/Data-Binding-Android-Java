package br.com.datamob.binding_cadastro.tela;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.datamob.binding_cadastro.R;
import br.com.datamob.binding_cadastro.databinding.ActivityCadastrarUsuarioBinding;

public class CadastrarUsuarioActivity extends ActivityControlador
{
    private Context context = CadastrarUsuarioActivity.this;
    private Handler handler = new Handler();

    //region Campos

    //endregion

    public CadastrarUsuarioViewModel viewModel;
    private ActivityCadastrarUsuarioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        inicializaComponentes();
    }

    private void inicializaComponentes()
    {
        viewModel = new CadastrarUsuarioViewModel(context, handler);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cadastrar_usuario);
        binding.setUsuarioViewModel(viewModel);
        setListFocus(new ArrayList<Object>(Arrays.asList(findViewById(R.id.etNome), findViewById(R.id.etCodigo), findViewById(R.id.etUnidade), findViewById(R.id.etFazenda), findViewById(R.id.etItemCalculado))));
    }
}
