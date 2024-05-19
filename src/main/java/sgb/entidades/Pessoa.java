package sgb.entidades;

public class Pessoa {
    private int id;
    private String nome;
    private String email;
    private String laudo;
    private String telefone;

    public Pessoa(int id, String nome, String email, String laudo, String telefone) {
        setId(id);
        setNome(nome);
        setEmail(email);
        setLaudo(laudo);
        setTelefone(telefone);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id inválido");
        }
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome inválido");
        }
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.email = email;
    }

    public String getLaudo() {
        return laudo;
    }

    public void setLaudo(String laudo) {
        this.laudo = laudo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void imprimirDados(){
        System.out.println("Id: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("E-mail: " + getEmail());
        System.out.println("Laudo: " + getLaudo());
        System.out.println("Telefone: " + getTelefone());
    }

}
