package sgb.apresentacao;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.theme.lumo.LumoIcon;
import sgb.entidades.Marcador;



public class MarcadorFormulario extends FormLayout {
    private TextField nomeCampo;
    private Button salvarBotao;
    // Os trechos comentados são uma melhoria na personalização dos marcadores que não vai entrar em teste
    private Span opcaoAzul;
    private Span opcaoVerde;
    private Span opcaoVermelho;
    private Span opcaoBranco;
    private Icon checkIcon;
    private Icon crossIcon;
    private Icon clockIcon;
    private Icon exclamationIcon;
    private ListBox<String> opcaoCores = new ListBox<>();
    private ListBox<String> opcaoIcon = new ListBox<>();
    private VerticalLayout corContainer;
    private VerticalLayout iconContainer;
    private VerticalLayout demoCores;
    private VerticalLayout demoIcons;

    public MarcadorFormulario() {
        var container = new VerticalLayout();
        nomeCampo = new TextField("Nome do Marcador: ");

        opcaoAzul = createColorBadge("Azul");
        opcaoVerde = createColorBadge("Verde");
        opcaoVermelho = createColorBadge("Vermelho");
        opcaoBranco = createColorBadge("Branco");

        demoCores = new VerticalLayout(opcaoAzul, opcaoVerde, opcaoVermelho, opcaoBranco);
        opcaoCores = new ListBox<>();
        corContainer = new VerticalLayout();
        opcaoCores.setItems("Azul", "Verde","Vermelho", "Branco");
        opcaoCores.addValueChangeListener(e-> {
            String cor = e.getValue();
            Span corSelecionada = createColorBadge(cor);
            corContainer.add(corSelecionada);
        });

        checkIcon = LumoIcon.CHECKMARK.create();
        crossIcon = LumoIcon.CROSS.create();
        clockIcon = LumoIcon.CLOCK.create();
        exclamationIcon = LumoIcon.ERROR.create();

        demoIcons = new VerticalLayout(checkIcon, crossIcon, clockIcon, exclamationIcon);
        opcaoIcon = new ListBox<>();
        iconContainer = new VerticalLayout();
        opcaoIcon.setItems("checkIcon", "crossIcon","clockIcon", "exclamationIcon");
        opcaoIcon.addValueChangeListener(e-> {
            String icon = e.getValue();
            Icon iconSelecionado = createIcon(icon);
            iconContainer.add(iconSelecionado);
        });

        salvarBotao = new Button("Salvar");
        salvarBotao.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        var linha1 = new HorizontalLayout(nomeCampo);
        var linha2 = new HorizontalLayout(demoCores, opcaoCores, corContainer);
        var linha3 = new HorizontalLayout(demoIcons, opcaoIcon, iconContainer);
        var linha4 = new HorizontalLayout(salvarBotao);
        //container.add(linha1,linha4);
        container.add(linha1, linha2, linha3,linha4);

        add(container);
    }
    private Span createColorBadge(String color) {
        String theme = switch (color) {
            case "Azul" -> "badge primary";
            case "Verde" -> "badge success primary";
            case "Vermelho" -> "badge error primary";
            default -> "badge contrast primary";
        };
        Span badge = new Span(color);
        badge.getElement().getThemeList().add(theme);
        return badge;
    }

    private Icon createIcon(String nomeIcon) {
        Icon icon = switch (nomeIcon) {
            case "checkIcon" -> LumoIcon.CHECKMARK.create();
            case "crossIcon" -> LumoIcon.CROSS.create();
            case "clockIcon" -> LumoIcon.CLOCK.create();
            case "exclamationIcon" -> LumoIcon.ERROR.create();
            default -> null;
        };
        return icon;
    }

    public Registration addSaveListener(ComponentEventListener<ClickEvent<Button>> listener) {
        return salvarBotao.addClickListener(listener);
    }

    public Marcador criarMarcador() {
        var nome = nomeCampo.getValue();
        return new Marcador(nome);
    }
}