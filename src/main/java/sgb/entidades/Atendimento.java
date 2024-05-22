package sgb.entidades;

import java.time.LocalDate;

public class Atendimento {
    private int id;
    String nome;
    String curso;
    int periodo;
    Pessoa pessoa;
    String relacionado;
    Apoio atendente;
    String psicologo;
    boolean status;
    LocalDate data;
    String anotacoes;
    String duracao;

    public Atendimento(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Atendimento(int id, String nome, String curso, int periodo, LocalDate data, String psicologo, String anotacoes) {
        this.id = id;
        this.nome = nome;
        this.curso= curso;
        this.periodo = periodo;
        this.data = data;
        this.psicologo = psicologo;
        this.anotacoes = anotacoes;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome inválido");
        }
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        if (curso == null) {
            throw new IllegalArgumentException("Curso inválido");
        }
        this.curso = curso;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        if (periodo < 0 || periodo > 12) {
            throw new IllegalArgumentException("Período inválido");
        }
        this.periodo = periodo;
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

    public String getPsicologo() {
        return psicologo;
    }

    public void setPsicologo(String psicologo) {
        if (psicologo == null) {
            throw new IllegalArgumentException("Psicológo inválido");
        }
        this.psicologo = psicologo;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
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
