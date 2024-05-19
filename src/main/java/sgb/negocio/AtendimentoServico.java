package sgb.negocio;

import org.springframework.stereotype.Service;
import sgb.entidades.Atendimento;
import sgb.persistencia.AtendimentoRespositorio;

import java.util.List;

@Service
public class AtendimentoServico {
    private AtendimentoRespositorio repositorio;

    public AtendimentoServico(AtendimentoRespositorio repositorio) {
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
}
