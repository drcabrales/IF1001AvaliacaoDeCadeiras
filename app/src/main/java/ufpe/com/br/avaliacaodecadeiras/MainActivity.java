package ufpe.com.br.avaliacaodecadeiras;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import com.parse.ParseObject;

import database.Database;
import objeto.Aluno;
import objeto.Avaliacao;
import objeto.AvaliacaoCategoria;
import objeto.AvaliacaoMetodo;
import objeto.Cadeira;
import objeto.CategoriaAvaliacaoCadeira;
import objeto.Comentario;
import objeto.Curso;
import objeto.Faculdade;
import objeto.MetodoAvaliacaoCadeira;
import repositorio.RepositorioAluno;
import repositorio.RepositorioAvaliacao;
import repositorio.RepositorioAvaliacaoCategoria;
import repositorio.RepositorioAvaliacaoMetodo;
import repositorio.RepositorioCadeira;
import repositorio.RepositorioCategoriaAvaliacaoCadeira;
import repositorio.RepositorioComentario;
import repositorio.RepositorioCurso;
import repositorio.RepositorioFaculdade;
import repositorio.RepositorioMetodoAvaliacaoCadeira;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //parse
        /*
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "JyeIe8Y16g24m5XGGIbWqtx0YOesQbO4Z8ixXZZO", "8Q8H6Gckdv4QAeH2niPFXpGN4IaoOQhBr5gXW2N5");


        ParseObject gameScore = new ParseObject("GameScore");
        gameScore.put("pack", 1336);
        gameScore.put("playerName", "lucas");
        gameScore.put("cheatMode", true);
        gameScore.saveInBackground();
        */


        TextView txt1 = (TextView) findViewById(R.id.textView);
        TextView txt2 = (TextView) findViewById(R.id.textView2);
        TextView txt3 = (TextView) findViewById(R.id.textView3);
        TextView txt4 = (TextView) findViewById(R.id.textView4);
        TextView txt5 = (TextView) findViewById(R.id.textView5);
        TextView txt6 = (TextView) findViewById(R.id.textView6);
        TextView txt7 = (TextView) findViewById(R.id.textView7);
        TextView txt8 = (TextView) findViewById(R.id.textView8);
        TextView txt9 = (TextView) findViewById(R.id.textView9);
        TextView txt10 = (TextView) findViewById(R.id.textView10);

        Database db = new Database(this);

        RepositorioFaculdade repositorioFaculdade = new RepositorioFaculdade(db);
       Faculdade facul = new Faculdade();
       facul.setNome("Universidade Federal de Pernambuco");
       facul.setSigla("UFPE");
       facul.setId(1);
       //repositorioFaculdade.insert(facul);
       // repositorioFaculdade.delete(facul);
       //Faculdade retornoBanco = repositorioFaculdade.get(1);
        txt1.setText(String.valueOf(repositorioFaculdade.list().size()));


        ParseObject repositorioFaculdadetb = new ParseObject("faculdade");
        repositorioFaculdadetb.put("id", facul.getId());
        repositorioFaculdadetb.put("Nome", facul.getNome());
        repositorioFaculdadetb.put("sigla", facul.getSigla());
        repositorioFaculdadetb.saveInBackground();




        RepositorioCurso repositorioCurso = new RepositorioCurso(db);
        Curso curso = new Curso();
        curso.setIdFaculdade(1);
        curso.setNome("Ciência da Computação");
        curso.setDescricao("Curso pra doido");
       // repositorioCurso.insert(curso);
       // repositorioCurso.delete( curso);
        //Curso retornoBancoCurso = repositorioCurso.get(curso.getNome(), curso.getIdFaculdade());
        txt2.setText(String.valueOf(repositorioCurso.list().size()));




        RepositorioAluno repositorioAluno = new RepositorioAluno(db);
        Aluno aluno = new Aluno();
        aluno.setNomeCurso("Ciência da Computação");
        aluno.setNome("Diogo");
        aluno.setEmail("diogo@gmail.com");
       // repositorioAluno.insert(aluno);
        //repositorioAluno.delete(aluno);
        //Aluno retornoBancoAluno = repositorioAluno.get(aluno.getEmail());
        txt3.setText(String.valueOf(repositorioAluno.list().size()));

        RepositorioCategoriaAvaliacaoCadeira repositorioCategoriaAvaliacaoCadeira = new RepositorioCategoriaAvaliacaoCadeira(db);
        CategoriaAvaliacaoCadeira categoriaAvaliacaoCadeira = new CategoriaAvaliacaoCadeira();
        categoriaAvaliacaoCadeira.setNome("Ensino bom");
       // repositorioCategoriaAvaliacaoCadeira.insert(categoriaAvaliacaoCadeira);
       // repositorioCategoriaAvaliacaoCadeira.delete(categoriaAvaliacaoCadeira);
        //CategoriaAvaliacaoCadeira retornoCategoriaAvaliacaoCadeira = repositorioCategoriaAvaliacaoCadeira.get(1);
        txt4.setText(String.valueOf(repositorioCategoriaAvaliacaoCadeira.list().size()));

        RepositorioMetodoAvaliacaoCadeira repositorioMetodoAvaliacaoCadeira = new RepositorioMetodoAvaliacaoCadeira(db);
        MetodoAvaliacaoCadeira metodoAvaliacaoCadeira = new MetodoAvaliacaoCadeira();
        metodoAvaliacaoCadeira.setNome("Provas");
     //   repositorioMetodoAvaliacaoCadeira.insert(metodoAvaliacaoCadeira);
     //   repositorioMetodoAvaliacaoCadeira.delete(metodoAvaliacaoCadeira);
        //MetodoAvaliacaoCadeira retornoMetodoAvaliacaoCadeira = repositorioMetodoAvaliacaoCadeira.get(1);
        txt5.setText(String.valueOf(repositorioMetodoAvaliacaoCadeira.list().size()));

        RepositorioCadeira repositorioCadeira = new RepositorioCadeira(db);
        Cadeira cadeira = new Cadeira();
        cadeira.setIdFaculdade(1);
        cadeira.setNomeCurso("Ciência da Computação");
        cadeira.setNome("Programação 3");
        cadeira.setNomeProfessor("Leopoldo");
     //   repositorioCadeira.insert(cadeira);
      //  repositorioCadeira.delete(cadeira);
        //Cadeira retornoBancoCadeira = repositorioCadeira.get(cadeira.getNome(), cadeira.getNomeProfessor(), cadeira.getNomeCurso(), cadeira.getIdFaculdade());
        txt6.setText(String.valueOf(repositorioCadeira.list().size()));

        RepositorioAvaliacao repositorioAvaliacao = new RepositorioAvaliacao(db);
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setEmailAluno("diogo@gmail.com");
        avaliacao.setIdFaculdade(1);
        avaliacao.setNomeCurso("Ciência da Computação");
        avaliacao.setNomeCadeira("Programação 3");
        avaliacao.setNomeProfessor("Leopoldo");
    //    repositorioAvaliacao.insert(avaliacao);
       // repositorioAvaliacao.delete(avaliacao);
        Avaliacao retornoBancoAvaliacao = repositorioAvaliacao.get(1);
        txt7.setText(String.valueOf(repositorioAvaliacao.list().size()));

        RepositorioAvaliacaoMetodo repositorioAvaliacaoMetodo = new RepositorioAvaliacaoMetodo(db);
        AvaliacaoMetodo avaliacaoMetodo = new AvaliacaoMetodo();
        avaliacaoMetodo.setIdAvaliacao(1);
        avaliacaoMetodo.setIdMetodo(1);
     //   repositorioAvaliacaoMetodo.insert(avaliacaoMetodo);
        //repositorioAvaliacaoMetodo.delete(avaliacaoMetodo);
        //AvaliacaoMetodo retornoBancoAvaliacaoMetodo = repositorioAvaliacaoMetodo.get(1);
        txt8.setText(String.valueOf(repositorioAvaliacaoMetodo.list().size()));

        RepositorioAvaliacaoCategoria repositorioAvaliacaoCategoria= new RepositorioAvaliacaoCategoria(db);
        AvaliacaoCategoria avaliacaoCategoria = new AvaliacaoCategoria();
        avaliacaoCategoria.setIdAvaliacao(1);
        avaliacaoCategoria.setIdCategoria(1);
        avaliacaoCategoria.setNota(0);
     //   repositorioAvaliacaoCategoria.insert(avaliacaoCategoria);
       // repositorioAvaliacaoCategoria.delete(avaliacaoCategoria);
        AvaliacaoCategoria retornoAvaliacaoCategoria = repositorioAvaliacaoCategoria.get(1);
        txt9.setText(String.valueOf(repositorioAvaliacaoCategoria.list().size()));

        RepositorioComentario repositorioComentario = new RepositorioComentario(db);
        Comentario comentario = new Comentario();
        comentario.setTexto("Uma cadeira bacana");
        comentario.setIdAvaliacao(1);
        comentario.setAno("2015");
        comentario.setSemestre("2");

      //  repositorioComentario.insert(comentario);
       // repositorioComentario.delete(comentario);
       Comentario retornoComentario = repositorioComentario.get(1);
         txt10.setText(String.valueOf(repositorioComentario.list().size()));





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
