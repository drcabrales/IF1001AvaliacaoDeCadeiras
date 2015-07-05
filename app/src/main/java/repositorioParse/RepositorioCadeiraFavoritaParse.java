package repositorioParse;

import com.parse.ParseObject;

import java.util.ArrayList;

import database.Database;
import objetoParse.ParseAluno;
import objetoParse.ParseCadeira;
import objetoParse.ParseCadeiraFavorita;

/**
 * Created by Zeke on 04/07/2015.
 */
public class RepositorioCadeiraFavoritaParse {


    private Database db;

    public RepositorioCadeiraFavoritaParse(Database db){
        this.db = db;
    }

    public void insert(ParseCadeiraFavorita parseCadeiraFavorita){
        db.insertCadeiraFavoritaObj(parseCadeiraFavorita);
    }


    public void delete(ParseCadeiraFavorita parseCadeiraFavorita){
        db.deleteCadeiraFavoritaObj(parseCadeiraFavorita);
    }
    public ArrayList<ParseObject> getAll(){
        return db.getAll("CadeiraFavorita");
    }
    public ParseCadeiraFavorita get(ParseAluno parseAluno, ParseCadeira parseCadeira){
        return db.getParseCadeiraFavoritaObj(parseAluno, parseCadeira);
    }

    public ArrayList<ParseObject> getCadeiraByAluno(ParseAluno parseAluno){
        return db.getParseCadeiraObjByCurso(parseAluno);
    }

}
