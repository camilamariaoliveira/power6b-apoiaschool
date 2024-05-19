package sgb.apresentacao;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import sgb.entidades.Atendimento;
import sgb.entidades.Autor;

public class AtendimentoFormulario extends FormLayout {
    private TextField idCampo;
    private TextField nomeCampo;

    public AtendimentoFormulario() {
        idCampo = new TextField("Id");
        add(idCampo);

        nomeCampo = new TextField("Nome");
        add(nomeCampo);
    }

    public Atendimento criarAtendimento() {
        var idString = idCampo.getValue();
        var id = Integer.parseInt(idString);

        var nome = nomeCampo.getValue();

        return new Atendimento(id, nome);
    }
}
