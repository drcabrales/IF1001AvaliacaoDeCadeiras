package ufpe.com.br.avaliacaodecadeiras;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseRole;

import org.json.JSONObject;

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

        //inicializando
        Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);

        //testando
        ParseFaculdade faculdade = new ParseFaculdade("Universidade Federal de Pernambuco", "UFPE");
        faculdade.saveInBackground();

        ParseCurso curso = new ParseCurso("Ciência da Computação", "Um curso bacana", faculdade);
        curso.saveInBackground();

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


    }
}
