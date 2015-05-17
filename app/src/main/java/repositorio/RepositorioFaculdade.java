package repositorio;

import android.database.Cursor;

import database.Database;

import objeto.Faculdade;
/**
 * Created by ezequiel on 15/05/2015.
 */
public class RepositorioFaculdade {

    private Database db;

    public RepositorioFaculdade(Database db){
        this.db = db;
    }

    public void insert(Faculdade faculdade){
        db.insertFaculdade(faculdade);
    }

    public void delete(Faculdade faculdade){
        db.deleteFaculdade(faculdade);
    }

    public Cursor list(){
        return db.getAllFaculdades();
    }


    public Faculdade get(int id){
        return db.getFaculdade(id);
    }

}
