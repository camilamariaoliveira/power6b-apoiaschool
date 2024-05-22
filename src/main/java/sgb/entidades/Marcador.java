package sgb.entidades;

import java.util.UUID;

public class Marcador {
    private String nome;
    private UUID id;
    private boolean status;

    public Marcador(String nome) {
        this.nome = nome;
        this.id = UUID.randomUUID();
        this.status = false;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        if (!status){
            this.status = true;
        } else if (status) {
            this.status = false;
        }
    }

}
