package br.com.datamob.binding_cadastro;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class Teclado
{
    public static void escondeTecladoAndroid(Context context)
    {
        try
        {
            ((Activity) context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            InputMethodManager imm = (InputMethodManager) ((Activity) context).getSystemService(Context.INPUT_METHOD_SERVICE);
            View view = ((Activity) context).getWindow().getDecorView().getRootView();
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        catch (Exception ex)
        {
        }
    }

    public static void escondeTecladoAndroid(final Context context, Handler handler)
    {
        handler.post(new Runnable()
        {
            @Override
            public void run()
            {
                escondeTecladoAndroid(context);
            }
        });
    }

    public static void escondeTecladoAndroid(final Context context, Handler handler, long delay)
    {
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                escondeTecladoAndroid(context);
            }
        }, delay);
    }

    public static void mostraTecladoAndroid(Context context, View view)
    {
        try
        {
            if (view == null)
                view = ((Activity) context).getWindow().getDecorView().getRootView();
            //
            InputMethodManager imm = (InputMethodManager) ((Activity) context).getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
        catch (Exception ex)
        {
        }
    }

    public static void mostraTecladoAndroid(Context context)
    {
        mostraTecladoAndroid(context, null);
    }
}
