package sgb.apresentacao;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import sgb.entidades.Marcador;
import sgb.negocio.MarcadorServico;

@Route("/marcador/lista")
public class MarcadorListaRota extends VerticalLayout {

    public MarcadorListaRota(MarcadorServico servico) {
        var titulo = new H1("Lista de Marcadores");
        add(titulo);

        var marcadores = servico.listar();
        var grade = new Grid<Marcador>(Marcador.class, true);
        grade.setItems(marcadores);
        add(grade);
    }
}