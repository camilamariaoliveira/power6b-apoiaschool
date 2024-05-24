package sgb.entidades;

import java.time.LocalDate;
import java.util.UUID;

public class Atendimento {
    private UUID id;
    private String nome;
    private String curso;
    private int periodo;
    private Pessoa pessoa;
    private String relacionado;
    private String psicologo;
    private boolean status;
    private LocalDate data;
    private String anotacoes;
    private String duracao;

    public Atendimento(String nome, String curso, int periodo, LocalDate data, String psicologo, String anotacoes) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.curso= curso;
        this.periodo = periodo;
        this.data = data;
        this.psicologo = psicologo;
        this.anotacoes = anotacoes;
    }

    public UUID getId() {
        return id;
    }

    public UUID setId(UUID id) {
        this.id = id;
        return id;
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
