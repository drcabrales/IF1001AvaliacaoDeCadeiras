package objetoParse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.io.Serializable;

/**
 * Created by Diogo on 02/06/2015.
 */
@ParseClassName("Cadeira")
public class ParseCadeira extends ParseObject implements Serializable{
    public ParseCadeira(){
        super();
    }
    public ParseCadeira(String nome, String nomeProfessor, ParseCurso curso){
        super();
        setNome(nome);
        setNomeProfessor(nomeProfessor);
        setCurso(curso);
    }

    public String getNome(){
        return getString("nome");
    }

    public void setNome(String nome){
        put("nome", nome);
    }

    public String getNomeProfessor(){
        return getString("nomeProfessor");
    }

    public void setNomeProfessor(String nomeProfessor){
        put("nomeProfessor", nomeProfessor);
    }

    public ParseCurso getCurso(){
        return (ParseCurso) getParseObject("curso");
    }

    public void setCurso(ParseCurso curso){
        put("curso", curso);
    }
}
