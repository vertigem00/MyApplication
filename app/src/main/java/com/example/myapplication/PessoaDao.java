package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PessoaDao {
    @Insert
    void insert(Pessoa pessoa);

    @Query("SELECT * FROM pessoa_tabela")
    List<Pessoa> getAllPessoa();

    @Delete
    void delete(Pessoa pessoa);
}
