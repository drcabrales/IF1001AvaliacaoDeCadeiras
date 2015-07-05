package objetoParse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Zeke on 04/07/2015.
 */
@ParseClassName("CadeiraFavorita")
public class ParseCadeiraFavorita extends ParseObject {
    public ParseCadeiraFavorita(){
        super();
    }

    public ParseCadeiraFavorita(ParseAluno aluno, ParseCadeira cadeira){
        super();
        setAluno(aluno);
        setCadeira(cadeira);
    }

    public ParseAluno getAluno(){
        return (ParseAluno) getParseObject("aluno");
    }

    public void setAluno(ParseAluno aluno){
        put("aluno", aluno);
    }

    public ParseObject getCadeira(){
        return getParseObject("cadeira");
    }

    public void setCadeira(ParseCadeira cadeira){
        put("cadeira", cadeira);
    }
}
