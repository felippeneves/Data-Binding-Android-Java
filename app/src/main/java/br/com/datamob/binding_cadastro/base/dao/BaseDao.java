package br.com.datamob.binding_cadastro.base.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;

public interface BaseDao<T>
{
    @Insert
    public long[] insert(T... rows);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insertOrReplace(T... rows);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public List<Long> insertOrReplace(List<T> rows);

    @Insert
    public List<Long> insert(List<T> rows);

    @Delete
    public int delete(T... rows);

    @Update
    public int update(T... rows);

    @RawQuery
    public List<T> selectRaw(SupportSQLiteQuery query);
}