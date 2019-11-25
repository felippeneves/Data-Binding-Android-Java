package br.com.datamob.binding_cadastro.base.entidade;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import br.com.datamob.binding_cadastro.base.dao.UsuarioDao;

@Entity(tableName = UsuarioDao.DATABASE_TABLE)

public class UsuarioEnt
{
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo
    private String nome;

    @ColumnInfo
    private Long codigo;

    @ColumnInfo
    private String unidade;

    @ColumnInfo
    private String fazenda;

    @ColumnInfo
    private Double itemCalculado;

    public UsuarioEnt()
    {
    }

    public UsuarioEnt(String nome, Long codigo, String unidade, String fazenda, Double itemCalculado)
    {
        this.nome = nome;
        this.codigo = codigo;
        this.unidade = unidade;
        this.fazenda = fazenda;
        this.itemCalculado = itemCalculado;
    }

    public UsuarioEnt(@NonNull Long id, String nome, Long codigo, String unidade, String fazenda, Double itemCalculado)
    {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.unidade = unidade;
        this.fazenda = fazenda;
        this.itemCalculado = itemCalculado;
    }

    @NonNull
    public Long getId()
    {
        return id;
    }

    public void setId(@NonNull Long id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Long getCodigo()
    {
        return codigo;
    }

    public void setCodigo(Long codigo)
    {
        this.codigo = codigo;
    }

    public String getUnidade()
    {
        return unidade;
    }

    public void setUnidade(String unidade)
    {
        this.unidade = unidade;
    }

    public String getFazenda()
    {
        return fazenda;
    }

    public void setFazenda(String fazenda)
    {
        this.fazenda = fazenda;
    }

    public Double getItemCalculado()
    {
        return itemCalculado;
    }

    public void setItemCalculado(Double itemCalculado)
    {
        this.itemCalculado = itemCalculado;
    }
}
