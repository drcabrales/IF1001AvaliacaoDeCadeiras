package objetoParse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Diogo on 02/06/2015.
 */
@ParseClassName("Faculdade")
public class ParseFaculdade extends ParseObject{

    public ParseFaculdade(){
        super();
    }

    public ParseFaculdade(String nome, String sigla){
        super();
        setNome(nome);
        setSigla(sigla);
    }

    public String getNome(){
        return getString("nome");
    }
    public void setNome(String nome){
        put("nome", nome);
    }
    public String getSigla(){
        return getString("sigla");
    }
    public void setSigla(String sigla){
        put("sigla", sigla);
    }


}
