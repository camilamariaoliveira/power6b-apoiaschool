package sgb.persistencia;

import org.springframework.stereotype.Repository;
import sgb.entidades.Aluno;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AlunoRepositorio {
    private List<Aluno> alunos;

    public AlunoRepositorio() {alunos =  new ArrayList<>();}

//    public List<Aluno> findAll() {
//        var copia = new ArrayList<Aluno>();
//        copia.addAll(alunos);
//        return copia;
//    }
    public List<Aluno> findAll() {
        return new ArrayList<>(alunos); // Retorna uma cópia para evitar modificações diretas
    }

    public void salvarTodos(List<Aluno> novosAlunos) {
        alunos.clear();
        alunos.addAll(novosAlunos);
    }
}