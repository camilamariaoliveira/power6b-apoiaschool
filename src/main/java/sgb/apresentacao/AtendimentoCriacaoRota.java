package sgb.apresentacao;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import sgb.entidades.Atendimento;
import sgb.negocio.AlunoServico;
import sgb.negocio.AtendimentoServico;
import sgb.negocio.MarcadorServico;

@Route("/atendimento/criacao")
public class AtendimentoCriacaoRota extends VerticalLayout {
    @Autowired
    private AtendimentoServico servico;
    private AtendimentoFormulario formulario;
    private MarcadorServico marcadorServico;
    private AlunoServico alunoServico;

    public AtendimentoCriacaoRota(MarcadorServico marcadorServico, AlunoServico alunoServico) {

        formulario = new AtendimentoFormulario(marcadorServico, alunoServico);
        add(formulario);

        formulario.addSaveListener(event -> {
            Atendimento atendimento = formulario.criarAtendimento();
            servico.criarAtendimento(atendimento);
            Notification.show("Atendimento salvo com sucesso", 3000, Notification.Position.MIDDLE);
            UI.getCurrent().navigate("");
        });

    }
}