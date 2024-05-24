package sgb.apresentacao;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
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
        grid.setColumns("id", "nome", "curso", "data", "periodo", "anotacoes");
        //grid.getColumnByKey("marcadores").setHeader("Marcadores");
        add(filtroNome, grid);
        atualizarLista();
//        var atendimentos = servico.pesquisar();
//        var grade = new Grid<Atendimento>(Atendimento.class, false);
//        grade.addColumn("id").setHeader("Id");
//        grade.addComponentColumn(this::edicaoAtendimento1).setHeader("Nome");
//        grade.addComponentColumn(this::edicaoAtendimento2).setHeader("Curso");
//        grade.addComponentColumn(this::edicaoAtendimento3).setHeader("Período");
//
//        grade.setItems(atendimentos);
//        add(grade);
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

    private RouterLink edicaoAtendimento1(Atendimento atendimento) {
        var id = atendimento.getId();
        var link = new RouterLink(AtendimentoEdicaoRota.class, id);
        link.setText(atendimento.getNome());
        return link;
    }

    private RouterLink edicaoAtendimento2(Atendimento atendimento) {
        var id = atendimento.getId();
        var link = new RouterLink(AtendimentoEdicaoRota.class, id);
        link.setText(atendimento.getCurso());
        return link;
    }

    private RouterLink edicaoAtendimento3(Atendimento atendimento) {
        var id = atendimento.getId();
        var link = new RouterLink(AtendimentoEdicaoRota.class, id);
        link.setText(String.valueOf(atendimento.getPeriodo()));
        return link;
    }
}

