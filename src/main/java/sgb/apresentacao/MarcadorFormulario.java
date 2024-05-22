package sgb.apresentacao;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import sgb.entidades.Marcador;

public class MarcadorFormulario extends FormLayout {
    private TextField nomeCampo;

    public MarcadorFormulario() {
        var container = new VerticalLayout();
        nomeCampo = new TextField("Nome do Marcador: ");

        var linha1 = new HorizontalLayout();
        linha1.add(nomeCampo);
        container.add(linha1);

        add(container);
    }


    public Marcador criarFormulario() {
        var nome = nomeCampo.getValue();

        return new Marcador(nome);
    }
}
