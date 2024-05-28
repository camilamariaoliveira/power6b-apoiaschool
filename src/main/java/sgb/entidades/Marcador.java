package sgb.entidades;

import java.util.UUID;

public class Marcador {
    private String nome;
    private UUID id;

    public Marcador(String nome) {
        this.nome = nome;
        this.id = UUID.randomUUID();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UUID getId() {
        return id;
    }

    public UUID setId(UUID id) {
        this.id = id;
        return id;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}