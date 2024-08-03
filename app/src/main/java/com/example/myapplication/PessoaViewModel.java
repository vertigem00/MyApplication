package com.example.myapplication;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PessoaViewModel extends AndroidViewModel {
    private PessoaDao pessoaDao;
    private MutableLiveData<List<Pessoa>> allPessoas;

    public PessoaViewModel(Application application) {
        super(application);
        PessoaDatabase db = PessoaDatabase.getDatabase(application);
        pessoaDao = db.pessoaDao();
        allPessoas = new MutableLiveData<>();
        loadPessoas();
    }

    public LiveData<List<Pessoa>> getAllPessoas() {
        return allPessoas;
    }

    public void insert(Pessoa pessoa) {
        PessoaDatabase.databaseWriteExecutor.execute(() -> {
            pessoaDao.insert(pessoa);
            loadPessoas();
        });
    }

    public void delete(Pessoa pessoa) {
        PessoaDatabase.databaseWriteExecutor.execute(() -> {
            pessoaDao.delete(pessoa);
            loadPessoas();
        });
    }

    private void loadPessoas() {
        PessoaDatabase.databaseWriteExecutor.execute(() -> {
            List<Pessoa> pessoas = pessoaDao.getAllPessoa();
            allPessoas.postValue(pessoas);
        });
    }
}
