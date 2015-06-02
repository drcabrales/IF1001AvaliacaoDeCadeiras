package objetoParse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Diogo on 02/06/2015.
 */
@ParseClassName("AvaliacaoCategoria")
public class ParseAvaliacaoCategoria extends ParseObject {
    public ParseAvaliacaoCategoria(){
        super();
    }
    public ParseAvaliacaoCategoria(ParseAvaliacao avaliacao, ParseCategoriaAvaliacaoCadeira categoriaAvaliacaoCadeira, int nota){
        super();
        setAvaliacao(avaliacao);
        setCategoriaAvaliacaoCadeira(categoriaAvaliacaoCadeira);
        setNota(nota);
    }

    public ParseAvaliacao getAvaliacao(){
        return (ParseAvaliacao) getParseObject("avaliacao");
    }

    public void setAvaliacao(ParseAvaliacao avaliacao){
        put("avaliacao", avaliacao);
    }

    public ParseCategoriaAvaliacaoCadeira getCategoriaAvaliacaoCadeira(){
        return (ParseCategoriaAvaliacaoCadeira) getParseObject("categoriaAvaliacaoCadeira");
    }

    public void setCategoriaAvaliacaoCadeira(ParseCategoriaAvaliacaoCadeira categoriaAvaliacaoCadeira){
        put("categoriaAvaliacaoCadeira", categoriaAvaliacaoCadeira);
    }

    public int getNota(){
        return getInt("nota");
    }

    public void setNota(int nota){
        put("nota", nota);
    }

}
