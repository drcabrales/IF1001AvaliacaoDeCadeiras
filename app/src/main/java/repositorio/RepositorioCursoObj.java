package repositorio;


import java.util.List;
import objetoParse.ParseCurso;
import database.Database;

/**
 * Created by ezequiel on 05/06/2015.
 */
public class RepositorioCursoObj {


    private Database db;

    public RepositorioCursoObj(Database db){
        this.db = db;
    }

    public void insert(ParseCurso parseCurso){
        db.insertCursoObj(parseCurso);
    }


    public void delete(ParseCurso parseCurso){
        db.deleteCursoObj(parseCurso);
    }
/*
    public List<ParseCurso> list(){
        return db.getAllParseCursoObj();
    }


    public ParseCurso get(String nome, int idFaculdade){
        return db.getParseCursoObj(nome, idFaculdade);
    }
*/

}
