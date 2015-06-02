package objetoParse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Diogo on 02/06/2015.
 */
@ParseClassName("Avaliacao")
public class ParseAvaliacao extends ParseObject {
    public ParseAvaliacao(){
        super();
    }
    public ParseAvaliacao(ParseAluno aluno, ParseCadeira cadeira){
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

    public ParseCadeira getCadeira(){
        return (ParseCadeira) getParseObject("cadeira");
    }

    public void setCadeira(ParseCadeira cadeira){
        put("cadeira", cadeira);
    }
}
