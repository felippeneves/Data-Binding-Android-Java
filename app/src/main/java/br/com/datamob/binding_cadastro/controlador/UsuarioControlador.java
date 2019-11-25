package br.com.datamob.binding_cadastro.controlador;

import android.content.Context;

import br.com.datamob.binding_cadastro.Resposta;
import br.com.datamob.binding_cadastro.base.Database;
import br.com.datamob.binding_cadastro.base.dao.UsuarioDao;
import br.com.datamob.binding_cadastro.base.entidade.UsuarioEnt;

public class UsuarioControlador
{
    private Context context;
    private UsuarioDao usuarioDao;

    public UsuarioControlador(Context context)
    {
        this.context = context;
        inicializaDao();
    }

    private void inicializaDao()
    {
        usuarioDao = Database.getInstance(context).usuarioDao();
    }

    public Resposta insereUsuario(UsuarioEnt usuarioEnt)
    {
        try
        {
            if(usuarioEnt != null)
            {
                usuarioDao.insert(usuarioEnt);
                return new Resposta(true, "", null);
            }
            else
            {
                return new Resposta(false, "Falha ao adicionar Usuário", null);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return new Resposta(false, "Falha ao adicionar Usuário", null);
        }
    }
}
