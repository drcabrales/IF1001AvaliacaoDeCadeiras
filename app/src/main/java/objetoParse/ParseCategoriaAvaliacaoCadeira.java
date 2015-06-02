package objetoParse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Diogo on 02/06/2015.
 */
@ParseClassName("CategoriaAvaliacaoCadeira")
public class ParseCategoriaAvaliacaoCadeira extends ParseObject {

    public ParseCategoriaAvaliacaoCadeira(){
        super();
    }

    public ParseCategoriaAvaliacaoCadeira(String nome){
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
