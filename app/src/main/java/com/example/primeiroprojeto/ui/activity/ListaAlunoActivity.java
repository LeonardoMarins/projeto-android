package com.example.primeiroprojeto.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.primeiroprojeto.R;
import com.example.primeiroprojeto.dao.AlunoDao;
import com.example.primeiroprojeto.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ListaAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";
    private static final String CHAVE_ALUNO = "";
    private final AlunoDao dao = new AlunoDao();
    private ListaAlunosAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configuraFabNovoAluno();
        configuraLista();


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        CharSequence tituloDoMenu = item.getTitle();
        if(tituloDoMenu.equals("remover")) {
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno alunoEscolhido = (Aluno) adapter.getItem(menuInfo.position);
            adapter.remove(alunoEscolhido);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioModoInsereAluno();
            }
        });
    }

    private void abreFormularioModoInsereAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.atualizar(dao.todos());
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.lista);
        configuraAdapter(listaDeAlunos);
        configuraListenerDeCliquePorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
        listaDeAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
                dao.remove(alunoEscolhido);
                adapter.remove(alunoEscolhido);
                return false;
            }
        });
    }

    private void configuraListenerDeCliquePorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
                abreFormularioModoEditaAluno(alunoEscolhido);
            }
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunoActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }

    private void configuraAdapter(ListView listaDeAlunos) {
        adapter = new ListaAlunosAdapter(this);
        listaDeAlunos.setAdapter(adapter);


    }
}
// * O cast, nada mais é, do que colocar entre parenteses o tipo de dado especifico
// * que sabemos que esta sendo referenciado pela variavel de tipo generico
// */
//variavelC1 = (C_1) variavelGenerica;    // Agora sim, estamos dizendo pro compilador que a variavel do tipo A tem uma referencia a um objeto do tipo C_1
//
//// finalmente podemos invocar o metodo especifico da classe C_1
//variavelC1.fazAlgo();
