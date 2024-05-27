package sgb.entidades;

import java.time.LocalDate;
import java.util.UUID;

public class Atendimento {
    private UUID id;
    private String nome;
    private String curso;
    private int periodo;
    private String psicologo;
    private LocalDate data;
    private String anotacoes;

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

    public String getPsicologo() {
        return psicologo;
    }

    public void setPsicologo(String psicologo) {
        if (psicologo == null) {
            throw new IllegalArgumentException("Psicológo inválido");
        }
        this.psicologo = psicologo;
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
}
