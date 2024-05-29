package sgb.negocio;

import org.springframework.stereotype.Service;
import sgb.persistencia.AlunoRepositorio;

@Service
public class AlunoServico {
    private AlunoRepositorio repositorio;

    public AlunoServico(AlunoRepositorio repositorio) {
        this.repositorio = repositorio;
    }
}