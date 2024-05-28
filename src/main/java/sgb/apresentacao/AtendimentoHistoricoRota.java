package sgb.apresentacao;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import sgb.entidades.Atendimento;
import sgb.negocio.AtendimentoServico;
import java.util.List;
import java.util.stream.Collectors;

@Route("/atendimento/historico")
public class AtendimentoHistoricoRota extends VerticalLayout {
    private final AtendimentoServico servico;
    private final Grid<Atendimento> grid;
    private final TextField filtroNome;

    @Autowired
    public AtendimentoHistoricoRota(AtendimentoServico servico) {
        this.servico = servico;
        this.grid = new Grid<>(Atendimento.class);
        this.filtroNome = new TextField();
        var titulo = new H1("Histórico");

        add(titulo);
        filtroNome.setPlaceholder("Filtrar por nome...");
        filtroNome.addValueChangeListener(e -> atualizarLista());
        grid.setColumns("nome", "curso", "data", "periodo", "marcadores");
        grid.addItemClickListener(event -> {
            Atendimento atendimentoSelecionado = event.getItem();
            if (atendimentoSelecionado != null) {
                String idAtendimento = atendimentoSelecionado.getId().toString();
                getUI().ifPresent(ui -> ui.navigate("atendimento/edicao/" + idAtendimento));
            } else {
                Notification.show("Nenhum atendimento selecionado");
            }
        });
        add(filtroNome, grid);
        atualizarLista();
    }

    private void atualizarLista() {
        List<Atendimento> atendimentos = servico.listarAtendimentos();
        if (!filtroNome.getValue().isEmpty()) {
            atendimentos = atendimentos.stream()
                    .filter(atendimento -> atendimento.getNome().toLowerCase().contains(filtroNome.getValue().toLowerCase()))
                    .collect(Collectors.toList());
        }
        grid.setItems(atendimentos);
    }
}