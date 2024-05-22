package sgb.negocio;

import org.springframework.stereotype.Service;
import sgb.entidades.Atendimento;
import sgb.persistencia.AtendimentoRepositorio;

import java.util.List;

@Service
public class AtendimentoServico {
    private AtendimentoRepositorio repositorio;

    public AtendimentoServico(AtendimentoRepositorio repositorio) {
        if (repositorio == null) {
            throw new IllegalArgumentException();
        }
        this.repositorio = repositorio;
    }

    public void salvar(Atendimento atendimento) {
        if (atendimento == null) {
            throw new IllegalArgumentException();
        }
        repositorio.excluir(atendimento.getId());
        repositorio.salvar(atendimento);
    }


    public List<Atendimento> pesquisar() {
        return repositorio.pesquisar();
    }

    public Atendimento obter(Integer id) {
        return repositorio.obter(id);
    }

    public Atendimento excluir(int id) {
        return repositorio.obter(id);
    }
}
