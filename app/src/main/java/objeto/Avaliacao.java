package objeto;

/**
 * Created by ezequiel on 15/05/2015.
 */
public class Avaliacao {

    int id;
    String emailAluno;
    private String nomeCurso;
    private int idFaculdade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailAluno() {
        return emailAluno;
    }

    public void setEmailAluno(String emailAluno) {
        this.emailAluno = emailAluno;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public int getIdFaculdade() {
        return idFaculdade;
    }

    public void setIdFaculdade(int idFaculdade) {
        this.idFaculdade = idFaculdade;
    }
}
