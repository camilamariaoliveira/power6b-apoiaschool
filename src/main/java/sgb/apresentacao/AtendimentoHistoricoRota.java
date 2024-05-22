package sgb.apresentacao;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import sgb.entidades.Atendimento;
import sgb.negocio.AtendimentoServico;

@Route("/atendimento/historico")
public class AtendimentoHistoricoRota extends VerticalLayout {

    public AtendimentoHistoricoRota(AtendimentoServico servico) {
        var titulo = new H1("Histórico");

        add(titulo);
        var atendimentos = servico.pesquisar();
        var grade = new Grid<Atendimento>(Atendimento.class, false);
        grade.addColumn("id").setHeader("Id");
        grade.addComponentColumn(this::edicaoAtendimento).setHeader("Nome");
        grade.addComponentColumn(this::edicaoAtendimento).setHeader("Curso");
        grade.addComponentColumn(this::edicaoAtendimento).setHeader("Período");

        grade.setItems(atendimentos);
        add(grade);
    }

    private RouterLink edicaoAtendimento(Atendimento atendimento) {
        var id = atendimento.getId();
        var link = new RouterLink(AtendimentoEdicaoRota.class, id);
        link.setText(atendimento.getNome());
        return link;
    }
}

