package sgb.apresentacao;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import sgb.entidades.Atendimento;

public class AtendimentoFormulario extends FormLayout {
    private TextField idCampo;
    private TextField nomeCampo;
    private Select<String> cursoCampo;
    private IntegerField periodoCampo;

    public AtendimentoFormulario() {
        var container = new VerticalLayout();

        idCampo = new TextField("Id: ");

        nomeCampo = new TextField("Nome: ");

        cursoCampo = new Select<>();
        cursoCampo.setLabel("Curso: ");
        cursoCampo.setItems("CC","Design","GTI","ADS","SI","Pós","Mestrado","Doutorado","Outro");
        cursoCampo.setPlaceholder("Curso...");

        periodoCampo = new IntegerField();
        periodoCampo.setLabel("Período: ");
        periodoCampo.setValue(1);
        periodoCampo.setStepButtonsVisible(true);
        periodoCampo.setMax(12);
        periodoCampo.setMin(1);

        var linha1 = new HorizontalLayout();
        var linha2 = new HorizontalLayout();
        linha1.add(idCampo);
        linha1.add(nomeCampo);
        linha2.add(cursoCampo);
        linha2.add(periodoCampo);
        linha2.setAlignItems(FlexComponent.Alignment.CENTER);
        container.add(linha1);
        container.add(linha2);
        add(container);
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
