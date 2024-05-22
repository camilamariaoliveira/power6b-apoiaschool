package sgb.apresentacao;

import com.vaadin.flow.component.datepicker.DatePicker;
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
    private DatePicker dataCampo;

    public AtendimentoFormulario() {
        var container = new VerticalLayout();

        idCampo = new TextField("Id: ");

        nomeCampo = new TextField("Nome: ");

        cursoCampo = new Select<>();
        cursoCampo.setLabel("Curso: ");
        cursoCampo.setItems("CC","Design","GTI","ADS","SI","Pós","Mestrado","Doutorado","Outro");
        cursoCampo.setPlaceholder("Curso...");

        dataCampo = new DatePicker();
        dataCampo.setLabel("Data do últ. atendimento: ");

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
        linha1.add(dataCampo);
        linha2.add(cursoCampo);
        linha2.add(periodoCampo);
        container.add(linha1);
        container.add(linha2);
        add(container);
    }

    public Atendimento criarAtendimento() {
        var idString = idCampo.getValue();
        var id = Integer.parseInt(idString);

        var nome = nomeCampo.getValue();
        var curso = cursoCampo.getValue();
        var periodo = periodoCampo.getValue();
        var data = dataCampo.getValue();

        return new Atendimento(id, nome, curso, periodo, data);
    }

    public void preencherAtendimento(Atendimento atendimento) {
        var nome = nomeCampo.getValue();
        var curso = cursoCampo.getValue();
        var periodo = periodoCampo.getValue();
        var data = dataCampo.getValue();
        atendimento.setNome(nome);
        atendimento.setCurso(curso);
        atendimento.setPeriodo(periodo);
        atendimento.setData(data);
    }

    public void preencherCampos(Atendimento atendimento) {
        var id = atendimento.getId();
        var idString = String.valueOf(id);
        idCampo.setValue(idString);

        var nome = atendimento.getNome();
        nomeCampo.setValue(nome);

        var curso = atendimento.getCurso();
        cursoCampo.setValue(curso);

        var periodo = atendimento.getPeriodo();
        periodoCampo.setValue(periodo);

        var data = atendimento.getData();
        dataCampo.setValue(data);
    }
}
