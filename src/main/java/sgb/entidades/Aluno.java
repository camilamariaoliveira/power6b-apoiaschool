package sgb.entidades;

public class Aluno {
    private String nome;
    private String curso;
    private int periodo;
    private boolean matricula;

    public Aluno(String nome, String curso, int periodo, boolean matricula) {
        this.nome = nome;
        this.curso = curso;
        this.periodo = periodo;
        this.matricula = matricula;
    }

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getCurso() {return curso;}

    public void setCurso(String curso) {this.curso = curso;}

    public int getPeriodo() {return periodo;}

    public void setPeriodo(int periodo) {this.periodo = periodo;}

    public boolean isMatricula() {return matricula;}

    public void setMatricula(boolean matricula) {this.matricula = matricula;}
}
