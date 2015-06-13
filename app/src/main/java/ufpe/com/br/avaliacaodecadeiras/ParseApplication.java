package ufpe.com.br.avaliacaodecadeiras;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import database.Database;
import objetoParse.ParseAluno;
import objetoParse.ParseAvaliacao;
import objetoParse.ParseAvaliacaoCategoria;
import objetoParse.ParseAvaliacaoMetodo;
import objetoParse.ParseCadeira;
import objetoParse.ParseCategoriaAvaliacaoCadeira;
import objetoParse.ParseComentario;
import objetoParse.ParseCurso;
import objetoParse.ParseFaculdade;
import objetoParse.ParseMetodoAvaliacaoCadeira;
import repositorioParse.RepositorioFaculdadeParse;

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

        RepositorioFaculdadeParse repositorioFaculdadeObj = new RepositorioFaculdadeParse(db);
        ParseFaculdade faculdade = new ParseFaculdade();
        faculdade = repositorioFaculdadeObj.get("UFPE");
        ParseCurso curso = new ParseCurso("Ciência da Computação3", "Um curso bacana2", faculdade );
        curso.saveInBackground();

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
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Faculdade");
        query.whereEqualTo("sigla", "UFPE");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("nome", "Retrieved " + scoreList.size() + " nomes");

                    Context contexto = getApplicationContext();
                    String boy = String.valueOf(scoreList.get(1));
                    int duracao = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(contexto, boy,duracao); toast.show();

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
*/


        // testando
      //  ParseFaculdade faculdade = new ParseFaculdade("Universidade Federal de Pernambuco", "UFPE");
      //  faculdade.saveInBackground();
        //faculdade.deleteInBackground();

        //ParseCurso curso = new ParseCurso("Ciência da Computação2", "Um curso bacana", faculdade );
        //curso.saveInBackground();



       // ParseCurso curso = new ParseCurso("Ciência da Computação2", "Um curso bacana", faculdade.getObjectId() );
     //   curso.saveInBackground();


/*
        ParseCadeira cadeira = new ParseCadeira("Programação 3", "Leopoldo", curso);
        cadeira.saveInBackground();

        ParseAluno aluno = new ParseAluno("Diogo", "drc2@cin.ufpe.br", "senhaAleatoria", new byte[12], curso); //ver como colocar null na foto
        aluno.saveInBackground();

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
