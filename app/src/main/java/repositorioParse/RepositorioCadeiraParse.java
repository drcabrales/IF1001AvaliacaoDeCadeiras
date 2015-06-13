package repositorioParse;

import java.util.List;
import objetoParse.ParseCadeira;
import database.Database;

import database.Database;
import objetoParse.ParseCurso;

/**
 * Created by ezequiel on 05/06/2015.
 */
public class RepositorioCadeiraParse {


    private Database db;

    public RepositorioCadeiraParse(Database db){
        this.db = db;
    }

    public void insert(ParseCadeira parseCadeira){
        db.insertCadeiraObj(parseCadeira);
    }

    public void delete(ParseCadeira parseCadeira){
        db.deleteCadeiraObj(parseCadeira);
    }
/*
    public List<ParseCadeira> list(){
        return db.getAllParseCadeiraObj();
    }
*/
    public ParseCadeira get(ParseCurso parseCurso, String nome, String nomeProfessor){
        return db.getParseCadeiraObj(parseCurso, nome, nomeProfessor);
    }


}
