package sgb.negocio;

import org.springframework.stereotype.Service;
import sgb.entidades.Aluno;
import sgb.persistencia.AlunoRepositorio;
import java.util.List;
import java.util.UUID;

@Service
public class AlunoServico {
    private AlunoRepositorio repositorio;

    public AlunoServico(AlunoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Aluno listarAlunoPorId(UUID id){
        return repositorio.findAll().stream().filter(aluno -> aluno.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Aluno> listar() {
        return repositorio.findAll();
    }

    public void salvarTodos(List<Aluno> novosAlunos) {
        repositorio.salvarTodos(novosAlunos);
    }
}