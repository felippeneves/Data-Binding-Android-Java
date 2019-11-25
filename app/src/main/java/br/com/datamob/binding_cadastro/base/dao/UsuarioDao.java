package br.com.datamob.binding_cadastro.base.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import br.com.datamob.binding_cadastro.base.entidade.UsuarioEnt;

@Dao
public interface UsuarioDao extends BaseDao<UsuarioEnt>
{
    //region propriedades

    /* NOME DA TABELA */
    public static final String DATABASE_TABLE = "usuario";

    //endregion

    //region Métodos SQL

    public String selectAll =
    "   select *            " +
    "   from usuario         ";

    @Query(selectAll)
    public List<UsuarioEnt> selectAll();


    public String selectByChave =
    "   select *                " +
    "   from usuario            " +
    "   where id = :id          ";

    @Query(selectByChave)
    public UsuarioEnt selectByChave(Long id);


    public String deleteAll =
    "   delete from usuario     ";

    @Query(deleteAll)
    public int deleteAll();

    //endregion
}
