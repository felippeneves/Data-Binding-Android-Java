package br.com.datamob.binding_cadastro.base;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.datamob.binding_cadastro.base.dao.UsuarioDao;
import br.com.datamob.binding_cadastro.base.entidade.UsuarioEnt;

@androidx.room.Database(
        entities =
                {
                        UsuarioEnt.class,
                }
        , version = Database.DATABASE_VERSION, exportSchema = true)
public abstract class Database extends RoomDatabase
{
    /* VERSAO DA BASE */
    public static final int DATABASE_VERSION = 1;

    /* NOME DA BASE DE DADOS */
    public static final String DATABASE_NAME = "base.db3";

    public abstract UsuarioDao usuarioDao();

    private static Database instance;

    public static synchronized Database getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, Database.class, DATABASE_NAME).allowMainThreadQueries().addMigrations
                    (
                    ).build();
        }

        return instance;
    }
}
