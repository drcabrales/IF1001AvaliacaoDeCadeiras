package repositorioParse;


import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;
import objetoParse.ParseCurso;
import database.Database;
import objetoParse.ParseFaculdade;

/**
 * Created by ezequiel on 05/06/2015.
 */
public class RepositorioCursoParse {


    private Database db;

    public RepositorioCursoParse(Database db){
        this.db = db;
    }

    public void insert(ParseCurso parseCurso){
        db.insertCursoObj(parseCurso);
    }


    public void delete(ParseCurso parseCurso){
        db.deleteCursoObj(parseCurso);
    }

    public ArrayList<ParseObject> getAll(){
        return db.getAll("Curso");
    }


    public ParseCurso get(String nome, ParseFaculdade parseFaculdade){
        return db.getParseCursoObj(nome, parseFaculdade);
    }


}
