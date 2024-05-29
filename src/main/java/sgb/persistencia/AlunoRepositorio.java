package sgb.persistencia;

import org.springframework.stereotype.Repository;
import sgb.entidades.Aluno;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AlunoRepositorio {
    private List<Aluno> alunos;

    public AlunoRepositorio() {alunos =  new ArrayList<>();}
}
