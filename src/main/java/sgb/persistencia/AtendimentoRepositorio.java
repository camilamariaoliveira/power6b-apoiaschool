package sgb.persistencia;

import org.springframework.stereotype.Repository;
import sgb.entidades.Atendimento;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class AtendimentoRepositorio {
    private List<Atendimento> atendimentos;

    public AtendimentoRepositorio() {atendimentos = new ArrayList<>();}

    public Atendimento salvar(Atendimento atendimento) {
        atendimentos.add(atendimento);
        return atendimento;
    }

    public List<Atendimento> findAll() {
        return new ArrayList<>(atendimentos);
    }

    public Atendimento findById(UUID id) {
        return atendimentos.stream()
                .filter(atendimento -> atendimento.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
