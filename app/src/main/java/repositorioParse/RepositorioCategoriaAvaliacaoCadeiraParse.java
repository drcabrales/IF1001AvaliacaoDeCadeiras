package repositorioParse;

import java.util.List;
import objetoParse.ParseCategoriaAvaliacaoCadeira;
import database.Database;

/**
 * Created by ezequiel on 05/06/2015.
 */
public class RepositorioCategoriaAvaliacaoCadeiraParse {


    private Database db;

    public RepositorioCategoriaAvaliacaoCadeiraParse(Database db){
        this.db = db;
    }

    public void insert(ParseCategoriaAvaliacaoCadeira parseCategoriaAvaliacaoCadeira){
        db.insertCategoriaAvaliacaoCadeiraObj(parseCategoriaAvaliacaoCadeira);
    }

    public void delete(ParseCategoriaAvaliacaoCadeira parseCategoriaAvaliacaoCadeira){
        db.deleteCategoriaAvaliacaoCadeiraObj(parseCategoriaAvaliacaoCadeira);
    }
/*
    public List<ParseCategoriaAvaliacaoCadeira> list(){
        return db.getAllParseCategoriaAvaliacaoCadeiraObj();
    }

*/
    public ParseCategoriaAvaliacaoCadeira get(String nome){
        return db.getParseCategoriaAvaliacaoCadeiraObj(nome);
    }


}
