package sgb.entidades;

import java.util.UUID;

public class Aluno {
    private UUID id;
    private String nome;
    private String curso;
    private int periodo;
    private boolean matricula;
    private Atendimento atendimento;

    public Atendimento getAtendimento() { return atendimento; }

    public void setAtendimento(Atendimento atendimento) { this.atendimento = atendimento;}

    public Aluno(String nome, String curso, int periodo, boolean matricula) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.curso = curso;
        this.periodo = periodo;
        this.matricula = matricula;
    }

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getCurso() { return curso; }

    public void setCurso(String curso) { this.curso = curso; }

    public int getPeriodo() { return periodo; }

    public void setPeriodo(int periodo) { this.periodo = periodo; }

    public boolean isMatricula() { return matricula; }

    public void setMatricula(boolean matricula) { this.matricula = matricula; }
}
