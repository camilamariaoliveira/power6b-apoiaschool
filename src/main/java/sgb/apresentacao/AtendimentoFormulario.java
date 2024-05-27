package sgb.apresentacao;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;
import org.springframework.stereotype.Component;
import sgb.entidades.Atendimento;

@Component
public class AtendimentoFormulario extends FormLayout {
    private TextField idCampo;
    private TextField nomeCampo;
    private Select<String> cursoCampo;
    private IntegerField periodoCampo;
    private DatePicker dataCampo;
    private Select<String> apoioCampo;
    private TextArea anotacoesCampo;
    private Button saveButton;
    private VerticalLayout container;

    public AtendimentoFormulario() {
        container = new VerticalLayout();

        idCampo = new TextField("Id: ");
        idCampo.setEnabled(false);

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

        apoioCampo = new Select<>();
        apoioCampo.setLabel("Último Atendente: ");
        apoioCampo.setItems("Ana Catharina Carvalho","Kleyton Silva","Giovana Barros");

        anotacoesCampo = new TextArea("Anotações: ");
        anotacoesCampo.setWidth("610px");

        saveButton = new Button("Salvar");

        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        var linha1 = new HorizontalLayout();
        var linha2 = new HorizontalLayout();
        var linha3 = new HorizontalLayout();
        var linha4 = new HorizontalLayout();
        linha1.add(idCampo);
        linha1.add(nomeCampo);
        linha1.add(dataCampo);
        linha2.add(cursoCampo);
        linha2.add(periodoCampo);
        linha2.add(apoioCampo);
        linha3.add(anotacoesCampo);
        linha4.add(saveButton);
        container.add(linha1);
        container.add(linha2);
        container.add(linha3);
        container.add(linha4);
        add(container);
    }

    public Atendimento criarAtendimento() {
        var nome = nomeCampo.getValue();
        var curso = cursoCampo.getValue();
        var periodo = periodoCampo.getValue();
        var data = dataCampo.getValue();
        var apoio = apoioCampo.getValue();
        var anotacoes = anotacoesCampo.getValue();

        return new Atendimento(nome, curso, periodo, data, apoio, anotacoes);
    }

    public void preencherAtendimento(Atendimento atendimento) {
        atendimento.setNome(nomeCampo.getValue());
        atendimento.setCurso(cursoCampo.getValue());
        atendimento.setPeriodo(periodoCampo.getValue());
        atendimento.setData(dataCampo.getValue());
        atendimento.setPsicologo(apoioCampo.getValue());
        atendimento.setAnotacoes(anotacoesCampo.getValue());
    }

    public void preencherCampos(Atendimento atendimento) {
        idCampo.setValue(String.valueOf(atendimento.getId()));
        nomeCampo.setValue(atendimento.getNome());
        cursoCampo.setValue(atendimento.getCurso());
        periodoCampo.setValue(atendimento.getPeriodo());
        dataCampo.setValue(atendimento.getData());
        apoioCampo.setValue(atendimento.getPsicologo());
        anotacoesCampo.setValue(atendimento.getAnotacoes());
    }

    public Registration addSaveListener(ComponentEventListener<ClickEvent<Button>> listener) {
        return saveButton.addClickListener(listener);
    }
}