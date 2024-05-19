package sgb.entidades;

public class Apoio extends Pessoa{

    private String senha;

    public Apoio(int id, String nome, String email, String laudo, String telefone, String senha) {
        super(id, nome, email, laudo, telefone);
        setSenha(senha);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
