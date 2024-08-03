package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder> {
    List<Pessoa> pessoaList;

    @NonNull
    @Override
    public PessoaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new PessoaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PessoaViewHolder holder, int position) {
        if (pessoaList != null) {
            Pessoa current = pessoaList.get(position);
            holder.nameView.setText(current.getName());
            holder.ageView.setText(String.valueOf(current.getAge()));
        }
    }

    void setPessoas(List<Pessoa> pessoas) {
        pessoaList = pessoas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (pessoaList != null)
            return pessoaList.size();
        else return 0;
    }

    class PessoaViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;
        private final TextView ageView;

        private PessoaViewHolder(View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.textViewName);
            ageView = itemView.findViewById(R.id.textViewAge);
        }
    }
}
