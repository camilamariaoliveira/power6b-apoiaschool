package sgb.persistencia;

import org.springframework.stereotype.Repository;
import sgb.entidades.Atendimento;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class AtendimentoRepositorio {
    private List<Atendimento> atendimentos;

    public AtendimentoRepositorio() {atendimentos = new ArrayList<>();
    }

    public Atendimento salvar(Atendimento atendimento) {
        atendimentos.add(atendimento);
        return atendimento;
    }

    public void excluir(int id) {
        var i = atendimentos.iterator();
//        while (i.hasNext()) {
//            var atendimento = i.next();
//            if (atendimento.getId() == id) {
//                i.remove();
//                break;
//            }
//        }
    }

    public List<Atendimento> pesquisar() {
        return new ArrayList<>(atendimentos);
    }

    public Atendimento obter(UUID id) {
        for (var atendimento : atendimentos) {
            if (atendimento.getId() == id) {
                return atendimento;
            }
        }
        return null;
    }

    public List<Atendimento> findAll() {
        return new ArrayList<>(atendimentos);
    }
}
