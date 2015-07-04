package objetoParse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.io.Serializable;

/**
 * Created by Diogo on 02/06/2015.
 */
@ParseClassName("Curso")
public class ParseCurso extends ParseObject implements Serializable{
    public ParseCurso(){
        super();
    }
    public ParseCurso(String nome, String descricao, ParseFaculdade faculdade){
        super();
        setNome(nome);
        setDescricao(descricao);
        setFaculdade(faculdade);
    }

    public String getNome(){
        return getString("nome");
    }

    public void setNome(String nome){
        put("nome", nome);
    }

    public String getDescricao(){
        return getString("descricao");
    }

    public void setDescricao(String descricao){
        put("descricao", descricao);
    }

    public ParseFaculdade getFaculdade(){
        return (ParseFaculdade) getParseObject("faculdade");
    }

    public void setFaculdade(ParseFaculdade faculdade){
        put("faculdade", faculdade);
    }
}
