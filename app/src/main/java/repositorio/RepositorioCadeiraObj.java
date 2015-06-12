package repositorio;

import java.util.List;
import objetoParse.ParseCadeira;
import database.Database;

import database.Database;

/**
 * Created by ezequiel on 05/06/2015.
 */
public class RepositorioCadeiraObj {


    private Database db;

    public RepositorioCadeiraObj(Database db){
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

    public ParseCadeira get(String nome, String nomeProfessor, String nomeCurso, int idFaculdade){
        return db.getParseCadeiraObj(nome, nomeProfessor, nomeCurso, idFaculdade);
    }

*/
}
