package repositorio;

import java.util.List;

import database.Database;
import objetoParse.ParseFaculdade;

/**
 * Created by ezequiel on 05/06/2015.
 */
public class RepositorioFaculdadeObj {


    private Database db;

    public RepositorioFaculdadeObj(Database db){
        this.db = db;
    }

    public void insert(ParseFaculdade parseFaculdade){
        db.insertFaculdadeObj(parseFaculdade);
    }

    public void delete(ParseFaculdade parseFaculdade){
        db.deleteFaculdadeObj(parseFaculdade);
    }
/*
    public List<ParseFaculdade> list(){
        return db.getAllParseFaculdadeObj();
    }

*/
    public ParseFaculdade get(String id){
        return db.getParseFaculdadeObj(id);
    }



}
