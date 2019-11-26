package br.com.datamob.binding_cadastro.tela;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.datamob.binding_cadastro.OnSingleclickListener;
import br.com.datamob.binding_cadastro.R;
import br.com.datamob.binding_cadastro.base.Database;
import br.com.datamob.binding_cadastro.base.entidade.UsuarioEnt;
import br.com.datamob.binding_cadastro.databinding.ItemListaBinding;

public class ListaUsuariosViewModel extends BaseObservable
{
    private Context context;
    private Handler handler;

    public List<AdapterViewObject> listUsuarios;

    public AdapterUsuario adapterUsuario;

    //region Construtor

    public ListaUsuariosViewModel(Context context, Handler handler)
    {
        this.context = context;
        this.handler = handler;
        this.adapterUsuario = new AdapterUsuario();
        this.listUsuarios = new ArrayList<>();
    }

    //endregion

    public void carregaInformacoes()
    {
        try
        {
            listUsuarios.clear();
            List<UsuarioEnt> list = Database.getInstance(context).usuarioDao().selectAll();
            if(list != null)
            {
                int i = 0;
                for(UsuarioEnt usuarioEnt : list)
                {
                    AdapterViewObject adapterViewObject = new AdapterViewObject();
                    adapterViewObject.nome = usuarioEnt.getNome();
                    adapterViewObject.codigo = usuarioEnt.getCodigo().toString();
                    adapterViewObject.unidade = usuarioEnt.getUnidade();
                    adapterViewObject.fazenda = usuarioEnt.getFazenda();
                    adapterViewObject.itemCalculado = usuarioEnt.getItemCalculado();
                    if(i % 2 == 0)
                        adapterViewObject.teste = true;
                    else
                        adapterViewObject.teste = false;

                    listUsuarios.add(adapterViewObject);
                    i++;
                }

                AdapterViewObject adapterViewObject = new AdapterViewObject();
                adapterViewObject.fazenda = "ITEM DETALHES";
                listUsuarios.add(adapterViewObject);
//                notifyPropertyChanged(BR.listUsuarios);
            }
            else
            {
                Toast.makeText(context, "Não foi encontrado nenhum usuário", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            Toast.makeText(context, "Falha ao carregar os usuários", Toast.LENGTH_SHORT).show();
        }
    }

    //region getter's and setter's

    @Bindable
    public AdapterUsuario getAdapterUsuario()
    {
        return this.adapterUsuario;
    }

    @Bindable
    public List<AdapterViewObject> getListUsuarios()
    {
        return listUsuarios;
    }

    //endregion


    //region adapters

    public class AdapterUsuario extends RecyclerView.Adapter<AdapterViewHolderUsuario>
{
        @NonNull
        @Override
        public AdapterViewHolderUsuario onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            ViewDataBinding binding = null;

            switch(viewType)
            {
                case 0:
                    binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_lista, parent, false);
                    break;

                case 1:
                    binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_lista2, parent, false);
                    break;
            }

            return new AdapterViewHolderUsuario(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull final AdapterViewHolderUsuario viewHolder, int position)
        {
            final AdapterViewObject data = getItem(position);
            viewHolder.bind(data);
        }

        @Override
        public int getItemCount()
        {
            return listUsuarios != null ? listUsuarios.size() : 0;
        }

        public AdapterViewObject getItem(int position)
        {
            return listUsuarios != null ? listUsuarios.get(position) : null;
        }

        @Override
        public int getItemViewType(int position)
        {
            AdapterViewObject adapterViewObject = getItem(position);
            int type = 0;
            if(adapterViewObject != null)
                type = adapterViewObject.nome != null ? 0 : 1;

            return type;
        }
    }

    public class AdapterViewHolderUsuario extends RecyclerView.ViewHolder
    {
        private ViewDataBinding viewDataBinding;


        public AdapterViewHolderUsuario(ViewDataBinding viewDataBinding)
        {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
        }

        public void bind(Object obj)
        {
            viewDataBinding.setVariable(BR.adapterViewObject, obj);
            viewDataBinding.executePendingBindings();
        }
    }

    public class AdapterViewObject extends BaseObservable
    {
        public String nome;
        public String codigo;
        public String unidade;
        public String fazenda;
        public boolean teste;
        public Double itemCalculado;


        @Bindable
        public String getNome()
        {
            return nome;
        }

        @Bindable
        public String getCodigo()
        {
            return codigo;
        }

        @Bindable
        public String getUnidade()
        {
            return unidade;
        }

        @Bindable
        public String getFazenda()
        {
            return fazenda;
        }
    }

    //endregion

    //region Adapter Databindings

    @BindingAdapter({"app:adapter"})
    public static void setAdapter(RecyclerView recyclerView, AdapterUsuario adapter)
    {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("srcCompatIvFazenda")
    public static void setImageResource(AppCompatImageView imageView, ListaUsuariosViewModel.AdapterViewObject adapterViewObject)
    {
        Context context = imageView.getContext();

        if(adapterViewObject.teste)
        {
            imageView.setImageResource(R.drawable.ic_android);
            imageView.setColorFilter(context.getResources().getColor(R.color.Red));
        }
        else
        {
            imageView.setImageResource(R.drawable.ic_wifi);
            imageView.setColorFilter(context.getResources().getColor(R.color.Blue300));
        }
    }

    @BindingAdapter("textItemCalculado")
    public static void setText(AppCompatTextView textView, ListaUsuariosViewModel.AdapterViewObject adapterViewObject)
    {
        Context context = textView.getContext();
        double itemCalculadoResultado = 0D;
        if(adapterViewObject.teste)
        {
            itemCalculadoResultado = adapterViewObject.itemCalculado * 2;
            textView.setTextColor(context.getResources().getColor(R.color.Red));
        }
        else
        {
            itemCalculadoResultado = adapterViewObject.itemCalculado * 10;
            textView.setTextColor(context.getResources().getColor(R.color.Blue300));
        }

        textView.setText(String.valueOf(itemCalculadoResultado));
    }


    @BindingAdapter("cliqueItem")
    public static void onClick(LinearLayoutCompat linearLayout, final ListaUsuariosViewModel.AdapterViewObject adapterViewObject)
    {
        final Context context = linearLayout.getContext();

        linearLayout.setOnClickListener(new OnSingleclickListener(){
            @Override
            public void onSingleClick(View v)
            {
                Toast.makeText(context, "Nome: " + adapterViewObject.nome + "\nCódigo: " + adapterViewObject.codigo, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //endregion


    //region Interfaces



    //endregion
}
