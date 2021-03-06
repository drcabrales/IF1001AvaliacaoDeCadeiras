package repositorioParse;


import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

import objetoParse.ParseAvaliacao;
import objetoParse.ParseComentario;
import database.Database;

/**
 * Created by ezequiel on 05/06/2015.
 */
public class RepositorioComentarioParse {



    private Database db;

    public RepositorioComentarioParse(Database db){
        this.db = db;
    }

    public void insert(ParseComentario parseComentario){
        db.insertComentarioObj(parseComentario);
    }

    public void delete(ParseComentario parseComentario){
        db.deleteComentarioObj(parseComentario);
    }

    public ArrayList<ParseObject> getAll(){
        return db.getAll("Comentario");
    }

    public ParseComentario get(ParseAvaliacao parseAvaliacao){
        return db.getParseComentarioObj(parseAvaliacao);
    }


}


