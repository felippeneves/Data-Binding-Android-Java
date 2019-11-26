package br.com.datamob.binding_cadastro.tela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import br.com.datamob.binding_cadastro.R;
import br.com.datamob.binding_cadastro.Teclado;

public class ActivityControlador extends AppCompatActivity
{
    protected Context context = ActivityControlador.this;
    protected Handler handler = new Handler();

    private ArrayList<Object> listFocus;

    private OnConfirmaTelaListener onConfirmaTelaListener;


    public void setListFocus(ArrayList<Object> listFocus)
    {
        setListFocus(listFocus, null);
    }

    public void setListFocus(ArrayList<Object> listFocus, OnConfirmaTelaListener onConfirmaTelaListener)
    {
        this.listFocus = listFocus;
        this.onConfirmaTelaListener = onConfirmaTelaListener;
    }

    public void selectNextFocus(Object campo)
    {
        try
        {
            if (listFocus != null && !listFocus.isEmpty())
            {
                //ultimo campo da tela
                if (listFocus.indexOf(campo) == listFocus.size() - 1)
                {
                    if (onConfirmaTelaListener != null)
                        onConfirmaTelaListener.onConfirmaTela();
                }
                else
                {
                    int indiceProximoCampo = campo != null ? listFocus.indexOf(campo) + 1 : 0;
                    for (int i = indiceProximoCampo; i < listFocus.size(); i++)
                    {
                        View view = (View) listFocus.get(i);
                        if (view.isEnabled() && view.isFocusable() && view.isShown())
                        {
                            view.requestFocus();
                            if (view instanceof TextInputEditText)
                            {
                                ((TextInputEditText) view).setSelection(0);
                                if (view.hasFocus())
                                    return;
                            }
                            else if (view.hasFocus())
                            {
                                return;
                            }
                            else
                            {
                                Teclado.escondeTecladoAndroid(this);
                                view.requestFocusFromTouch();
                                if (view.hasFocus())
                                    return;
                            }
                        }
                        else
                        {
                            if (!(view instanceof TextInputEditText) && view.isEnabled())
                            {
                                Teclado.escondeTecladoAndroid(this);
                                view.requestFocusFromTouch();
                                if (view.hasFocus())
                                    return;
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    public interface OnConfirmaTelaListener
    {
        public void onConfirmaTela();
    }
}
