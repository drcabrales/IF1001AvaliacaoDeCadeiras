package objetoParse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Diogo on 02/06/2015.
 */
@ParseClassName("AvaliacaoMetodo")
public class ParseAvaliacaoMetodo extends ParseObject{
    public ParseAvaliacaoMetodo(){
        super();
    }
    public ParseAvaliacaoMetodo(ParseAvaliacao avaliacao, ParseMetodoAvaliacaoCadeira metodoAvaliacaoCadeira){
        super();
        setAvaliacao(avaliacao);
        setMetodoAvaliacaoCadeira(metodoAvaliacaoCadeira);
    }

    public ParseAvaliacao getAvaliacao(){
        return (ParseAvaliacao) getParseObject("avaliacao");
    }

    public void setAvaliacao(ParseAvaliacao avaliacao){
        put("avaliacao", avaliacao);
    }

    public ParseMetodoAvaliacaoCadeira getMetodoAvaliacaoCadeira(){
        return (ParseMetodoAvaliacaoCadeira) getParseObject("metodoAvaliacaoCadeira");
    }

    public void setMetodoAvaliacaoCadeira(ParseMetodoAvaliacaoCadeira metodoAvaliacaoCadeira){
        put("metodoAvaliacaoCadeira", metodoAvaliacaoCadeira);
    }
}
