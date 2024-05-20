package sgb.apresentacao;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import sgb.entidades.Atendimento;

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

    public void preencherAtendimento(Atendimento atendimento) {
        var nome = nomeCampo.getValue();
        atendimento.setNome(nome);
    }

    public void preencherCampos(Atendimento atendimento) {
        var id = atendimento.getId();
        var idString = String.valueOf(id);
        idCampo.setValue(idString);

        var nome = atendimento.getNome();
        nomeCampo.setValue(nome);
    }
}
