package com.example.primeiroprojeto.ui.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.primeiroprojeto.R;
import com.example.primeiroprojeto.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter{
    private final List<Aluno> alunos = new ArrayList<>();
    private Context context;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int posicao) {
        return alunos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return alunos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = LayoutInflater
                .from(context)
                .inflate(R.layout.activity_lista_alunos_nome, viewGroup, false);
        Aluno alunoDevolvido = alunos.get(posicao);
        TextView nome = viewCriada.findViewById(R.id.item_aluno_nome);
        nome.setText(alunoDevolvido.getNome());
        TextView telefone = viewCriada.findViewById(R.id.item_aluno_telefone);
        telefone.setText(alunoDevolvido.getTelefone());
        return viewCriada;
    }


    private void clear() {
        alunos.clear();
    }

    private void addAll(List<Aluno>alunos) {
        this.alunos.addAll(alunos);
    }

    public void atualizar(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
