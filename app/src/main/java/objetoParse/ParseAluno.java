package objetoParse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Diogo on 02/06/2015.
 */
@ParseClassName("Aluno")
public class ParseAluno extends ParseObject{
    public ParseAluno(){
        super();
    }
    public ParseAluno(String nome, String email, String senha, byte[] foto,ParseCurso curso){
        super();
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        setFoto(foto);
        setCurso(curso);
    }

    public String getNome(){
        return getString("nome");
    }

    public void setNome(String nome){
        put("nome", nome);
    }

    public String getEmail(){
        return getString("email");
    }

    public void setEmail(String email){
        put("email", email);
    }

    public String getSenha(){
        return getString("senha");
    }

    public void setSenha(String senha){
        put("senha", senha);
    }

    public byte[] getFoto(){
        return getBytes("foto");
    }

    public void setFoto(byte[] foto){
        put("foto", foto);
    }

    public ParseCurso getCurso(){
        return (ParseCurso) getParseObject("curso");
    }

    public void setCurso(ParseCurso curso){
        put("curso", curso);
    }
}
