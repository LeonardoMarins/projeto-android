package com.example.primeiroprojeto;

import android.app.Application;

import com.example.primeiroprojeto.dao.AlunoDao;
import com.example.primeiroprojeto.model.Aluno;

public class AgendaAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AlunoDao dao = new AlunoDao();
        dao.salva(new Aluno("leonardo","23232","leo-lima-13@hotmail.com"));
        dao.salva(new Aluno("lucas","23232222","leo-lima"));
    }
}
