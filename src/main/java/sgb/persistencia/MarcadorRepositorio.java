package sgb.persistencia;

import org.springframework.stereotype.Repository;
import sgb.entidades.Marcador;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MarcadorRepositorio {
    private List<Marcador> marcadores;

    public MarcadorRepositorio() {marcadores = new ArrayList<>();
    }

    public void salvar(Marcador marcador) {
        if (marcador == null) {
            throw new IllegalArgumentException();
        }
        marcadores.add(marcador);
    }

    public List<Marcador> listar() {
        var copia = new ArrayList<Marcador>();
        copia.addAll(marcadores);
        return copia;
    }
}