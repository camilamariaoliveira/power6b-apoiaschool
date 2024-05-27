package sgb.apresentacao;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import sgb.entidades.Atendimento;
import sgb.negocio.AtendimentoServico;
import java.util.UUID;

@Route("/atendimento/edicao")
public class AtendimentoEdicaoRota extends VerticalLayout implements HasUrlParameter<String> {
    @Autowired
    private AtendimentoServico servico;
    private AtendimentoFormulario formulario;
    private Atendimento atendimento;

    public AtendimentoEdicaoRota() {

        formulario = new AtendimentoFormulario();

        add(formulario);

        formulario.addSaveListener(event -> {
            try {
                formulario.preencherAtendimento(atendimento);
                servico.atualizarAtendimento(atendimento);
                UI.getCurrent().navigate(AtendimentoHistoricoRota.class);

            } catch (Exception e){
                Notification.show("Erro ao atualizar atendimento: " + e.getMessage(), 3000, Notification.Position.MIDDLE);
            }
        });
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        UUID id = UUID.fromString(parameter);
        atendimento = servico.buscarAtendimentoPorId(id);
        if (atendimento != null) {
            formulario.preencherCampos(atendimento);
        } else {
            Notification.show("Atendimento não encontrado!", 3000, Notification.Position.MIDDLE);
        }
    }

}