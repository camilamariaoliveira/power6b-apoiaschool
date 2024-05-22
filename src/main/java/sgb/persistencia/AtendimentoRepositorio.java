package sgb.persistencia;

import org.springframework.stereotype.Repository;
import sgb.entidades.Atendimento;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AtendimentoRepositorio {
    private List<Atendimento> atendimentos;

    public AtendimentoRepositorio() {atendimentos = new ArrayList<>();
    }

    public void salvar(Atendimento atendimento) {
        if (atendimento == null) {
            throw new IllegalArgumentException();
        }
        atendimentos.add(atendimento);
    }

    public void excluir(int id) {
        var i = atendimentos.iterator();
        while (i.hasNext()) {
            var atendimento = i.next();
            if (atendimento.getId() == id) {
                i.remove();
                break;
            }
        }
    }

    public List<Atendimento> pesquisar() {
        var copia = new ArrayList<Atendimento>();
        copia.addAll(atendimentos);
        //copia.sort();
        return copia;
    }

    public Atendimento obter(int id) {
        for (var atendimento : atendimentos) {
            if (atendimento.getId() == id) {
                return atendimento;
            }
        }
        return null;
    }
}
