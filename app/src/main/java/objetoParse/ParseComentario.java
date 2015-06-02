package objetoParse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Diogo on 02/06/2015.
 */
@ParseClassName("Comentario")
public class ParseComentario extends ParseObject{
    public ParseComentario(){
        super();
    }
    public ParseComentario(ParseAvaliacao avaliacao, String ano, String semestre, String texto){
        super();
        setAvaliacao(avaliacao);
        setAno(ano);
        setSemestre(semestre);
        setTexto(texto);
    }

    public ParseAvaliacao getAvaliacao(){
        return (ParseAvaliacao) getParseObject("avaliacao");
    }

    public void setAvaliacao(ParseAvaliacao avaliacao){
        put("avaliacao", avaliacao);
    }

    public String getAno(){
        return getString("ano");
    }

    public void setAno(String ano){
        put("ano", ano);
    }

    public String getSemestre(){
        return getString("semestre");
    }

    public void setSemestre(String semestre){
        put("semestre", semestre);
    }

    public String getTexto(){
        return getString("texto");
    }

    public void setTexto(String texto){
        put("texto", texto);
    }

}
