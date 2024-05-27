package sgb.apresentacao;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import sgb.entidades.Atendimento;
import sgb.negocio.AtendimentoServico;

@Route("/atendimento/criacao")
public class AtendimentoCriacaoRota extends VerticalLayout {
    @Autowired
    private AtendimentoServico servico;
    private AtendimentoFormulario formulario;

    public AtendimentoCriacaoRota() {

        formulario = new AtendimentoFormulario();
        add(formulario);

        formulario.addSaveListener(event -> {
            Atendimento atendimento = formulario.criarAtendimento();
            servico.criarAtendimento(atendimento);
            Notification.show("Atendimento salvo com sucesso");
            UI.getCurrent().navigate(AtendimentoHistoricoRota.class);
        });

    }
}