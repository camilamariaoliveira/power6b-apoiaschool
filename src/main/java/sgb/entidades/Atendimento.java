package sgb.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Atendimento {
    private UUID id;
    private Aluno aluno;
    private String curso;
    private int periodo;
    private int contatoAluno;
    private int contatoRelacionado;
    private String laudo;
    private String psicologo;
    private LocalDate data;
    private String anotacoes;
    private List<Marcador> marcadores;

    public Atendimento(Aluno aluno, LocalDate data, String psicologo, String anotacoes) {
        this.id = UUID.randomUUID();
        this.aluno = aluno;
        this.curso= aluno.getCurso();
        this.periodo = aluno.getPeriodo();
        this.data = data;
        this.psicologo = psicologo;
        this.anotacoes = anotacoes;
        this.marcadores = new ArrayList<>();
    }

    public UUID getId() { return id; }

    public UUID setId(UUID id) {
        this.id = id;
        return id;
    }

    public Aluno getAluno() { return aluno; }

    public void setAluno(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno inválido");
        }
        this.aluno = aluno;
        this.curso = aluno.getCurso();  // Atualizando o curso com base no aluno
        this.periodo = aluno.getPeriodo();  // Atualizando o período com base no aluno
    }

    public String getCurso() { return curso; }

    public void setCurso(String curso) {
        if (curso == null) {
            throw new IllegalArgumentException("Curso inválido");
        }
        this.curso = curso;
    }

    public int getPeriodo() { return periodo; }

    public void setPeriodo(int periodo) {
        if (periodo < 0 || periodo > 12) {
            throw new IllegalArgumentException("Período inválido");
        }
        this.periodo = periodo;
    }

    public String getPsicologo() { return psicologo; }

    public void setPsicologo(String psicologo) {
        if (psicologo == null) {
            throw new IllegalArgumentException("Psicológo inválido");
        }
        this.psicologo = psicologo;
    }

    public int getContatoAluno() { return contatoAluno; }

    public void setContatoAluno(int contatoAluno) { this.contatoAluno = contatoAluno; }

    public int getContatoRelacionado() { return contatoRelacionado; }

    public void setContatoRelacionado(int contatoRelacionado) { this.contatoRelacionado = contatoRelacionado; }

    public String getLaudo() { return laudo; }

    public void setLaudo(String laudo) { this.laudo = laudo; }

    public LocalDate getData() { return data; }

    public void setData(LocalDate data) { this.data = data; }

    public String getAnotacoes() { return anotacoes; }

    public void setAnotacoes(String anotacoes) { this.anotacoes = anotacoes; }

    public List<Marcador> getMarcadores() { return marcadores; }

    public void setMarcadores(List<Marcador> marcadores) { this.marcadores = marcadores; }

    public void adicionarMarcador(Marcador marcador) { marcadores.add(marcador); }

    public void removerMarcador(Marcador marcador) { marcadores.remove(marcador); }

    public String toString() {
        return "Atendimento [ id="+ id + ", curso=" + curso + ", periodo= "+ periodo + ", psicologo= "+ psicologo + ", data= "+ data + ", anotacoes= "+ anotacoes + ", marcadores= "+ marcadores.stream().map(Marcador::getNome).toList() +"]";
    }
}