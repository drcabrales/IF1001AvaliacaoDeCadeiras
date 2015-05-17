package repositorio;

import android.database.Cursor;

import database.Database;
import objeto.Comentario;

/**
 * Created by ezequiel on 17/05/2015.
 */
public class RepositorioComentario {

    private Database db;

    public RepositorioComentario(Database db){
        this.db = db;
    }

    public void insert(Comentario comentario){
        db.insertComentario(comentario);
    }

    public void delete(Comentario comentario){
        db.deleteComentario(comentario);
    }

    public Cursor list(){
        return db.getAllComentario();
    }


    public Comentario get(int id){
        return db.getComentario(id);
    }
}
