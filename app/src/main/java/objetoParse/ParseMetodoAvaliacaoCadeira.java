package objetoParse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Diogo on 02/06/2015.
 */
@ParseClassName("MetodoAvaliacaoCadeira")
public class ParseMetodoAvaliacaoCadeira extends ParseObject{
    public ParseMetodoAvaliacaoCadeira(){
        super();
    }

    public ParseMetodoAvaliacaoCadeira(String nome){
        super();
        setNome(nome);
    }

    public String getNome(){
        return getString("nome");
    }
    public void setNome(String nome){
        put("nome", nome);
    }
}
