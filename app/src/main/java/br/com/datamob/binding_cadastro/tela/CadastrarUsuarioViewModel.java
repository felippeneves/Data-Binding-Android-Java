package br.com.datamob.binding_cadastro.tela;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableField;

import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import br.com.datamob.binding_cadastro.Resposta;
import br.com.datamob.binding_cadastro.base.entidade.UsuarioEnt;
import br.com.datamob.binding_cadastro.controlador.UsuarioControlador;

public class CadastrarUsuarioViewModel
{
    private Context context;
    private Handler handler;

    //region Atributos de Interface

    private ICadastrarUsuario iCadastrarUsuario;

    //endregion

    //region Objetos da Tela

    public final ObservableField<String> nome =  new ObservableField<>();
    public final ObservableField<String> codigo =  new ObservableField<>();
    public final ObservableField<String> unidade =  new ObservableField<>();
    public final ObservableField<String> fazenda =  new ObservableField<>();
    public final ObservableField<String> itemCalculado =  new ObservableField<>();

    private UsuarioControlador usuarioControlador;

    //endregion


    //region Construtor

    public CadastrarUsuarioViewModel(Context context, Handler handler)
    {
        this.context = context;
        this.handler = handler;
        usuarioControlador = new UsuarioControlador(context);
    }

    //endregion

    public void chamaTelaConsultaLista()
    {
        ((AppCompatActivity)context).startActivity(new Intent(context, ListaUsuariosActivity.class));
    }

    //region Validações

    public void confirmaTela()
    {
        try
        {
            if(!validaNome())
                return;

            if(!validaCodigo())
                return;

            if(!validaUnidade())
                return;

            if(!validaFazenda())
                return;

            if(!validaItemCalculado())
                return;

//            Resposta resposta = usuarioControlador.insereUsuario(new UsuarioEnt(nome.get(), (codigo.get() != null ? Long.valueOf(codigo.get()) : null), unidade.get(), fazenda.get(), (itemCalculado.get() != null ? Double.valueOf(itemCalculado.get()) : null)));
            Resposta resposta = usuarioControlador.insereUsuario(new UsuarioEnt(nome.get(), Long.valueOf(codigo.get()), unidade.get(), fazenda.get(), Double.valueOf(itemCalculado.get())));


            if(resposta.isSucesso())
            {
                Toast.makeText(context, "Usuário salvo com sucesso", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, CadastrarUsuarioActivity.class));
                ((AppCompatActivity)context).finish();
            }
            else
                Toast.makeText(context, "Falha ao salvar o Usuário", Toast.LENGTH_SHORT).show();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            Toast.makeText(context, "Falha ao salvar usuário", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validaNome()
    {
        String nomeAtr = nome.get();
        if(nomeAtr != null && !nomeAtr.isEmpty())
        {
            return true;
        }
        else
        {
            Toast.makeText(context, "Informe o nome", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean validaCodigo()
    {
        String nomeAtr = codigo.get();
        if(nomeAtr != null && !nomeAtr.isEmpty())
        {
            return true;
        }
        else
        {
            Toast.makeText(context, "Informe o código", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean validaUnidade()
    {
        String nomeAtr = unidade.get();
        if(nomeAtr != null && !nomeAtr.isEmpty())
        {
            return true;
        }
        else
        {
            Toast.makeText(context, "Informe a unidade", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean validaFazenda()
    {
        String nomeAtr = fazenda.get();
        if(nomeAtr != null && !nomeAtr.isEmpty())
        {
            return true;
        }
        else
        {
            Toast.makeText(context, "Informe a fazenda", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean validaItemCalculado()
    {
        String nomeAtr = itemCalculado.get();
        if(nomeAtr != null && !nomeAtr.isEmpty())
        {
            Double nomeAtrDouble = Double.valueOf(itemCalculado.get());
            if(nomeAtrDouble > 0)
                return true;
            else
            {
                Toast.makeText(context, "Item calculado deve ser maior que 0", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        else
        {
            Toast.makeText(context, "Informe a fazenda", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    //endregion

    public interface ICadastrarUsuario
    {
        void onConfirmar(UsuarioEnt usuarioEnt);
    }
}
