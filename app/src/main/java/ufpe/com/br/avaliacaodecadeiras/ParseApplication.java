package ufpe.com.br.avaliacaodecadeiras;

import android.app.Application;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import database.Database;
import objetoParse.ParseAluno;
import objetoParse.ParseAvaliacao;
import objetoParse.ParseAvaliacaoCategoria;
import objetoParse.ParseAvaliacaoMetodo;
import objetoParse.ParseCadeira;
import objetoParse.ParseCadeiraFavorita;
import objetoParse.ParseCategoriaAvaliacaoCadeira;
import objetoParse.ParseComentario;
import objetoParse.ParseCurso;
import objetoParse.ParseFaculdade;
import objetoParse.ParseMetodoAvaliacaoCadeira;
import repositorio.RepositorioAluno;
import repositorio.RepositorioCategoriaAvaliacaoCadeira;
import repositorio.RepositorioFaculdade;
import repositorio.RepositorioMetodoAvaliacaoCadeira;
import repositorioParse.RepositorioAlunoParse;
import repositorioParse.RepositorioAvaliacaoCategoriaParse;
import repositorioParse.RepositorioAvaliacaoMetodoParse;
import repositorioParse.RepositorioAvaliacaoParse;
import repositorioParse.RepositorioCadeiraParse;
import repositorioParse.RepositorioCategoriaAvaliacaoCadeiraParse;
import repositorioParse.RepositorioComentarioParse;
import repositorioParse.RepositorioCursoParse;
import repositorioParse.RepositorioFaculdadeParse;
import repositorioParse.RepositorioMetodoAvalicaoCadeiraParse;

/**
 * Created by Diogo on 02/06/2015.
 */
public class ParseApplication extends Application {
    public static final String YOUR_APPLICATION_ID = "D5DIBSP1WX3NbxrPKQeNJSfKPuvthK3L0IDZOxfV";
    public static final String YOUR_CLIENT_KEY = "mXrAjeU91I9wT2key3aVh7Py4JpVMw0eXTTFXYqP";

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);

        //registrando as classes
        ParseObject.registerSubclass(ParseFaculdade.class);
        ParseObject.registerSubclass(ParseCurso.class);
        ParseObject.registerSubclass(ParseCadeira.class);
        ParseObject.registerSubclass(ParseAluno.class);
        ParseObject.registerSubclass(ParseCategoriaAvaliacaoCadeira.class);
        ParseObject.registerSubclass(ParseMetodoAvaliacaoCadeira.class);
        ParseObject.registerSubclass(ParseAvaliacao.class);
        ParseObject.registerSubclass(ParseAvaliacaoCategoria.class);
        ParseObject.registerSubclass(ParseAvaliacaoMetodo.class);
        ParseObject.registerSubclass(ParseComentario.class);
        ParseObject.registerSubclass(ParseCadeiraFavorita.class);

        Database db = new Database(this);

        //inicializando
        Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);

/*
        RepositorioFaculdadeParse repositorioFaculdadeObj = new RepositorioFaculdadeParse(db) ;
        ParseFaculdade facull = new ParseFaculdade();
        facull.setNome("Universidade Federal do Rio de Janeiro");
        facull.setSigla("UFRJ");
        //repositorioFaculdadeObj.insert(facull);

        RepositorioCursoParse repositorioCursoObj = new RepositorioCursoParse(db) ;
        ParseCurso curso = new ParseCurso();
        curso.setNome("Mat");
        curso.setDescricao("not bad");
        curso.setFaculdade(facull);
        repositorioCursoObj.insert(curso);
*/

        /*
        RepositorioFaculdadeParse repositorioFaculdade = new RepositorioFaculdadeParse(db);
        ParseFaculdade faculdade = new ParseFaculdade();
        faculdade = repositorioFaculdade.get("UFPE");
       // ParseCurso curso = new ParseCurso("Ciência da Computação3", "Um curso bacana2", faculdade );
      //  curso.saveInBackground();

        RepositorioCategoriaAvaliacaoCadeiraParse repositorioCategoriaAvaliacaoCadeira = new RepositorioCategoriaAvaliacaoCadeiraParse(db);
        ParseCategoriaAvaliacaoCadeira categoriaAvaliacaoCadeira = new ParseCategoriaAvaliacaoCadeira();
        categoriaAvaliacaoCadeira = repositorioCategoriaAvaliacaoCadeira.get("Dificuldade");

        RepositorioAlunoParse repositorioAlunoParse = new RepositorioAlunoParse(db);
        ParseAluno aluno = new ParseAluno();
        aluno = repositorioAlunoParse.get("drc2@cin.ufpe.br");

        RepositorioCursoParse repositorioCursoParse = new RepositorioCursoParse(db);
        ParseCurso curso = new ParseCurso();
        curso = repositorioCursoParse.get("Ciência da Computação",faculdade);

        RepositorioMetodoAvalicaoCadeiraParse repositorioMetodoAvalicaoCadeiraParse = new RepositorioMetodoAvalicaoCadeiraParse(db);
        ParseMetodoAvaliacaoCadeira metodoAvaliacao = new ParseMetodoAvaliacaoCadeira();
        metodoAvaliacao = repositorioMetodoAvalicaoCadeiraParse.get("Prova");

        RepositorioCadeiraParse repositorioCadeiraParse = new RepositorioCadeiraParse(db);
        ParseCadeira cadeira = new ParseCadeira();
        cadeira = repositorioCadeiraParse.get(curso, "Programação 3","Leopoldo");

        RepositorioAvaliacaoParse repositorioAvaliacaoParse = new RepositorioAvaliacaoParse(db);
        ParseAvaliacao avaliacao = new ParseAvaliacao();
        avaliacao = repositorioAvaliacaoParse.get(aluno ,cadeira);

        RepositorioAvaliacaoCategoriaParse repositorioAvaliacaoCategoriaParse = new RepositorioAvaliacaoCategoriaParse(db);
        ParseAvaliacaoCategoria avaliacaoCategoria = new ParseAvaliacaoCategoria();
        avaliacaoCategoria = repositorioAvaliacaoCategoriaParse.get(avaliacao, categoriaAvaliacaoCadeira);

        RepositorioAvaliacaoMetodoParse repositorioAvaliacaoMetodoParse = new RepositorioAvaliacaoMetodoParse(db);
        ParseAvaliacaoMetodo avaliacaoMetodo = new ParseAvaliacaoMetodo();
        avaliacaoMetodo = repositorioAvaliacaoMetodoParse.get(avaliacao, metodoAvaliacao);

        RepositorioComentarioParse repositorioComentarioParse = new RepositorioComentarioParse(db);
        ParseComentario comentario = new ParseComentario();
        comentario = repositorioComentarioParse.get(avaliacao);
*/

        /*
        RepositorioFaculdadeParse repositorioCategoriaAvaliacaoCadeira = new RepositorioFaculdadeParse(db);
        ArrayList<ParseObject> x = repositorioCategoriaAvaliacaoCadeira.getAll();

        ParseComentario comentario = new ParseComentario();
*/


 //pega apenas 1

/*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Faculdade");
        query.whereEqualTo("sigla", "UFPE");
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object == null) {

                    Log.d("nome", "The getFirst request failed.");


                } else {

                    Log.d("nome", "The getFirst request failed.");
                    //String x = String.valueOf(object.get("sigla"));
                    //String x = String.valueOf(object.get("ObjectId"));
                    ParseFaculdade y = (ParseFaculdade) object;

                    ParseCurso curso = new ParseCurso("Ciência da Computação3", "Um curso bacana2", y );
                    curso.saveInBackground();

                }
            }
        });

*/
        //delete

        /*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Faculdade");
        query.whereEqualTo("sigla", "UFPE");
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object == null) {

                    Log.d("nome", "The getFirst request failed.");


                } else {

                    Log.d("nome", "The getFirst request failed.");
                    object.deleteInBackground();
                }
            }
        });

*/
//pega lista
/*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Curso");
        ArrayList<ParseObject> lista = new ArrayList<ParseObject>();
        try {
            lista = (ArrayList<ParseObject>) query.find();
        } catch (com.parse.ParseException e) {
            e.printStackTrace();
        }


*/


        // testando
        ParseFaculdade faculdade = new ParseFaculdade("Universidade Federal de Pernambuco", "UFPE");
        faculdade.saveInBackground();
        //faculdade.deleteInBackground();

        //ParseCurso curso = new ParseCurso("Ciência da Computação2", "Um curso bacana", faculdade );
        //curso.saveInBackground();



        ParseCurso curso = new ParseCurso("Ciência da Computação4", "Um curso bacana", faculdade );
       curso.saveInBackground();

        ParseAluno aluno = new ParseAluno("Diogo", "drc2@cin.ufpe.br", "senhaAleatoria", curso); //ver como colocar null na foto
        aluno.saveInBackground();

        ParseCadeira cadeira = new ParseCadeira("Programação 3", "Leopoldo", curso);
        cadeira.saveInBackground();

        ParseCadeiraFavorita cadeiraFavorita = new ParseCadeiraFavorita(aluno, cadeira);
        cadeiraFavorita.saveInBackground();

        int a = 2;
        /*



        ParseCategoriaAvaliacaoCadeira categoriaAvaliacaoCadeira = new ParseCategoriaAvaliacaoCadeira("Dificuldade");
        categoriaAvaliacaoCadeira.saveInBackground();

        ParseMetodoAvaliacaoCadeira metodoAvaliacaoCadeira = new ParseMetodoAvaliacaoCadeira("Prova");
        metodoAvaliacaoCadeira.saveInBackground();

        ParseAvaliacao avaliacao = new ParseAvaliacao(aluno,cadeira);
        avaliacao.saveInBackground();

        ParseAvaliacaoCategoria avaliacaoCategoria = new ParseAvaliacaoCategoria(avaliacao, categoriaAvaliacaoCadeira, 10);
        avaliacaoCategoria.saveInBackground();

        ParseAvaliacaoMetodo avaliacaoMetodo = new ParseAvaliacaoMetodo(avaliacao, metodoAvaliacaoCadeira);
        avaliacaoMetodo.saveInBackground();

        ParseComentario comentario = new ParseComentario(avaliacao, "2015", "2", "Vale muito a pena pagar!");
        comentario.saveInBackground();

*/
    }


}
