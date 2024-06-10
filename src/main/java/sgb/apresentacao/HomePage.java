package sgb.apresentacao;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import sgb.entidades.Atendimento;
import sgb.entidades.Marcador;
import sgb.negocio.AtendimentoServico;

import java.util.List;
import java.util.stream.Collectors;

@Route("")
public class HomePage extends AppLayout {
    private final AtendimentoServico servico;
    private final TextField filtroNome;
    private final Grid<Atendimento> grid;

    public HomePage(AtendimentoServico servico) {
        this.servico = servico;
        this.filtroNome = new TextField();
        this.grid = new Grid<>(Atendimento.class, false);
        inicializarGrid();
        atualizarLista();

        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Apoio Psicopedagógico CESAR School");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        SideNav nav = barraNavLateral();

        Scroller scroller = new Scroller(nav);
        scroller.setClassName(LumoUtility.Padding.SMALL);

        addToDrawer(scroller);
        addToNavbar(toggle, title);
    }

    private SideNav barraNavLateral() {
        SideNav barraLateral = new SideNav();
        barraLateral.addItem(
                new SideNavItem("Início", "",
                        VaadinIcon.HOME.create()),
                new SideNavItem("Criar Atendimento", "/atendimento/criacao", VaadinIcon.CLIPBOARD_TEXT.create()),
                new SideNavItem("Marcadores", "/marcador/criacao",
                        VaadinIcon.TAGS.create()),
                new SideNavItem("Biblioteca Alunos ", "aluno/biblioteca",
                        VaadinIcon.USERS.create()),
                new SideNavItem("Analytics", "/analytics",
                        VaadinIcon.CHART.create()),
                new SideNavItem("Ajuda", "/suporte", VaadinIcon.EXCLAMATION_CIRCLE.create()));
        return barraLateral;
    }

    private void inicializarGrid(){

        var titulo = new H1("Histórico");

        filtroNome.setPlaceholder("Filtrar por nome...");
        filtroNome.setValueChangeMode(ValueChangeMode.EAGER);
        filtroNome.addValueChangeListener(e -> atualizarLista());
        grid.addColumn(atendimento -> atendimento.getAluno().getNome()).setHeader("Nome");
        grid.addColumn(Atendimento::getCurso).setHeader("Curso");
        grid.addColumn(Atendimento::getData).setHeader("Data");
        grid.addColumn(Atendimento::getPeriodo).setHeader("Período");
        grid.addColumn(atendimento -> atendimento.getMarcadores().stream()
                        .map((Marcador::getNome))
                        .collect(Collectors.joining(", ")))
                .setHeader("Marcadores");        grid.addItemClickListener(event -> {
            Atendimento atendimentoSelecionado = event.getItem();
            if (atendimentoSelecionado != null) {
                String idAtendimento = atendimentoSelecionado.getId().toString();
                getUI().ifPresent(ui -> ui.navigate("atendimento/edicao/" + idAtendimento));
            } else {
                Notification.show("Nenhum atendimento selecionado");
            }
        });
        grid.addItemClickListener(event -> {
            Atendimento atendimentoSelecionado = event.getItem();
            if (atendimentoSelecionado != null) {
                String idAtendimento = atendimentoSelecionado.getId().toString();
                getUI().ifPresent(ui -> ui.navigate("atendimento/edicao/" + idAtendimento));
            } else {
                Notification.show("Nenhum atendimento selecionado");
            }
        });
        VerticalLayout atendimentosLayout = new VerticalLayout(filtroNome, titulo, grid);
        setContent(atendimentosLayout);
    }

    private void atualizarLista() {
        List<Atendimento> atendimentos = servico.listarAtendimentos();
        String filtro = filtroNome.getValue().toLowerCase();
        if (!filtro.isEmpty()) {
            atendimentos = atendimentos.stream()
                    .filter(atendimento -> atendimento.getAluno().getNome().toLowerCase().contains(filtro))
                    .collect(Collectors.toList());
        }
        grid.setItems(atendimentos);
        grid.getDataProvider().refreshAll();
    }
}
