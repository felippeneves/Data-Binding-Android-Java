package br.com.datamob.binding_cadastro;

public class Resposta
{
    private boolean sucesso;

    private String  mensagem;

    private Object object;

    public Resposta()
    {
    }

    public Resposta(boolean sucesso, String mensagem, Object object)
    {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.object = object;
    }

    public boolean isSucesso()
    {
        return sucesso;
    }

    public void setSucesso(boolean sucesso)
    {
        this.sucesso = sucesso;
    }

    public String getMensagem()
    {
        return mensagem;
    }

    public void setMensagem(String mensagem)
    {
        this.mensagem = mensagem;
    }

    public Object getObject()
    {
        return object;
    }

    public void setObject(Object object)
    {
        this.object = object;
    }
}
