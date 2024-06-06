package sgb.negocio;

import org.springframework.stereotype.Service;
import sgb.entidades.Atendimento;
import sgb.persistencia.AtendimentoRepositorio;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AtendimentoServico {
    private final AtendimentoRepositorio repositorio;

    public AtendimentoServico(AtendimentoRepositorio repositorio) {
        if (repositorio == null) {
            throw new IllegalArgumentException();
        }
        this.repositorio = repositorio;
    }

    public Atendimento criarAtendimento(Atendimento atendimento) {
        atendimento.setId(UUID.randomUUID());
        return repositorio.salvar(atendimento);
    }

    public List<Atendimento> listarAtendimentos() {
        return repositorio.findAll();
    }

    public Atendimento buscarAtendimentoPorId(UUID id) {
        return repositorio.findById(id);
    }

    public void atualizarAtendimento(Atendimento atendimentoAtualizado) {
        Optional<Atendimento> optionalAtendimentoExistente = Optional.ofNullable(repositorio.findById(atendimentoAtualizado.getId()));

        if (optionalAtendimentoExistente.isPresent()) {
            Atendimento atendimentoExistente = optionalAtendimentoExistente.get();
            atendimentoExistente.setAluno(atendimentoAtualizado.getAluno());
            atendimentoExistente.setCurso(atendimentoAtualizado.getCurso());
            atendimentoExistente.setPeriodo(atendimentoAtualizado.getPeriodo());
            atendimentoExistente.setData(atendimentoAtualizado.getData());
            atendimentoExistente.setPsicologo(atendimentoAtualizado.getPsicologo());
            atendimentoExistente.setAnotacoes(atendimentoAtualizado.getAnotacoes());

        } else {
            throw new RuntimeException("Atendimento não encontrado");
        }
    }
}
