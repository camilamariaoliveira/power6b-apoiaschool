package sgb.entidades;

public class Atendimento {
    private int id;
    String nome;
    Pessoa pessoa;
    String relacionado;
    Apoio atendente;
    boolean status;
    String data;
    String anotacoes;
    String duracao;

    public Atendimento(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Atendimento(int id, Pessoa pessoa, String relacionado, Apoio atendente, String data, String anotacoes, String duracao) {
        this.status = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0){
            throw new IllegalArgumentException("Id inválido.");
        }
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        if (pessoa == null) {
            throw new IllegalArgumentException();
        }
        this.pessoa = pessoa;
    }

    public String getRelacionado() {
        return relacionado;
    }

    public void setRelacionado(String relacionado) {
        if (relacionado == null) {
            throw new IllegalArgumentException();
        }
        this.relacionado = relacionado;
    }

    public Apoio getAtendente() {
        return atendente;
    }

    public void setAtendente(Apoio atendente) {
        if (atendente == null) {
            throw new IllegalArgumentException();
        }
        this.atendente = atendente;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus() {
        if (!isStatus()){
            this.status = true;
        } else if (isStatus()) {
            this.status = false;
        }
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
}
