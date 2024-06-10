package sgb.apresentacao;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;
import sgb.entidades.Marcador;

public class MarcadorFormulario extends FormLayout {
    private TextField nomeCampo;
    private Button salvarBotao;

    public MarcadorFormulario() {
        var container = new VerticalLayout();
        nomeCampo = new TextField("Nome do Marcador: ");

        salvarBotao = new Button("Salvar");
        salvarBotao.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        var linha1 = new HorizontalLayout(nomeCampo);
        var linha4 = new HorizontalLayout(salvarBotao);
        container.add(linha1,linha4);

        add(container);
    }

    public Registration addSaveListener(ComponentEventListener<ClickEvent<Button>> listener) {
        return salvarBotao.addClickListener(listener);
    }

    public Marcador criarMarcador() {
        var nome = nomeCampo.getValue();
        return new Marcador(nome);
    }
}