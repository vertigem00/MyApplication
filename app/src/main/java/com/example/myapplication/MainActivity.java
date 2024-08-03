package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PessoaViewModel pessoaViewModel;
    private EditText editTextName;
    private EditText editTextAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final PessoaAdapter adapter = new PessoaAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pessoaViewModel = new ViewModelProvider(this).get(PessoaViewModel.class);
        pessoaViewModel.getAllPessoas().observe(this, adapter::setPessoas);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getBindingAdapterPosition();
                List<Pessoa> pessoas = adapter.pessoaList;
                pessoaViewModel.delete(pessoas.get(position));
                Toast.makeText(MainActivity.this, "Pessoa deletada", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        Button addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String ageStr = editTextAge.getText().toString().trim();

            if (name.isEmpty() || ageStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int age = Integer.parseInt(ageStr);
            Pessoa pessoa = new Pessoa(name, age);
            pessoaViewModel.insert(pessoa);

            editTextName.setText("");
            editTextAge.setText("");
        });
    }
}
