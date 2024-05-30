package sgb.negocio;

import org.springframework.stereotype.Service;
import sgb.entidades.Marcador;
import sgb.persistencia.MarcadorRepositorio;

import java.util.List;
import java.util.UUID;

@Service
public class MarcadorServico {
    private MarcadorRepositorio repositorio;

    public MarcadorServico(MarcadorRepositorio repositorio) {
        if (repositorio == null) {
            throw new IllegalArgumentException();
        }
        this.repositorio = repositorio;
    }

    public void salvar(Marcador marcador) {
        if (marcador == null) {
            throw new IllegalArgumentException();
        }repositorio.salvar(marcador);
    }


    public List<Marcador> listar() {
        return repositorio.listar();
    }

    public void criarMarcador(Marcador novoMarcador) {
        novoMarcador.setId(UUID.randomUUID());
        repositorio.salvar(novoMarcador);
    }
}