package sgb.apresentacao;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import sgb.negocio.MarcadorServico;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import sgb.entidades.Marcador;

@Route("/marcador/criacao")
public class MarcadorCriacaoRota extends VerticalLayout {
    private MarcadorServico servico;
    private MarcadorFormulario formulario;
    private Grid<Marcador> grid = new Grid<Marcador>(Marcador.class);

    public MarcadorCriacaoRota(MarcadorServico servico) {
        this.servico = servico;

        formulario = new MarcadorFormulario();
        add(formulario);
        formulario.addSaveListener(event -> {
            Marcador novoMarcador = formulario.criarMarcador();
            servico.criarMarcador(novoMarcador);
            Notification.show("Marcador salvo com sucesso");
            updateGrid();
        });

        grid.setItems(servico.listar());
        add(grid);
    }

    private void updateGrid() {grid.setItems(servico.listar());}
}