package objeto;

/**
 * Created by Diogo on 15/05/2015.
 */
public class Aluno {
    private String nome;
    private String email;
    private byte[] foto;
    private String nomeCurso;
    private int idFaculdade;

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

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
