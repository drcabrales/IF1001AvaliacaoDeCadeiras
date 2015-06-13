package repositorioParse;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

import database.Database;
import objetoParse.ParseFaculdade;

/**
 * Created by ezequiel on 05/06/2015.
 */
public class RepositorioFaculdadeParse {


    private Database db;

    public RepositorioFaculdadeParse(Database db){
        this.db = db;
    }

    public void insert(ParseFaculdade parseFaculdade){
        db.insertFaculdadeObj(parseFaculdade);
    }

    public void delete(ParseFaculdade parseFaculdade){
        db.deleteFaculdadeObj(parseFaculdade);
    }

    public ArrayList<ParseObject> getAll(){
        return db.getAll("Faculdade");
    }

    public ParseFaculdade get(String sigla){
        return db.getParseFaculdadeObj(sigla);
    }



}
