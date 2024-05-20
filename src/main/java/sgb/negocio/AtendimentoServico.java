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

        var paciente1 = new Atendimento(232, "Camila Maria de Oliveira");

        var paciente2 = new Atendimento(323, "Luiz Roberto Lobato Lobato");
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
