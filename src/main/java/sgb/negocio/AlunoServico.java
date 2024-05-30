package sgb.negocio;

import org.springframework.stereotype.Service;
import sgb.entidades.Aluno;
import sgb.persistencia.AlunoRepositorio;

import java.util.List;

@Service
public class AlunoServico {
    private AlunoRepositorio repositorio;

    public AlunoServico(AlunoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Aluno> listar() {
        return repositorio.findAll();
    }

    public void salvarTodos(List<Aluno> novosAlunos) {
        repositorio.salvarTodos(novosAlunos);
    }
}