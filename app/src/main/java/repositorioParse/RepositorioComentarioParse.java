package repositorioParse;


import java.util.List;
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
/*
    public List<ParseComentario> list(){
        return db.getAllParseComentarioObj();
    }


    public ParseComentario get(int id){
        return db.getParseComentarioObj(id);
    }
*/

}


