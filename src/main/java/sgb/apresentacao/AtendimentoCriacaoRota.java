package sgb.apresentacao;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
        System.out.println(formulario);
        System.out.println(this);
        add(formulario);
        formulario.addSaveListener(event -> {
            System.out.println("Passou");
            System.out.println(servico);
            Atendimento atendimento = formulario.criarAtendimento();
            servico.criarAtendimento(atendimento);
        });

//        var salvarBotao = new Button("Salvar");
//        salvarBotao.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        salvarBotao.addClickListener(this::salvar);
//
//        var cancelarBotao = new Button("Cancelar");
//        cancelarBotao.addClickListener(this::cancelar);
//
//        var botoes = new HorizontalLayout();
//        botoes.add(salvarBotao);
//        botoes.add(cancelarBotao);
//
//        add(botoes);
    }

    private void salvar(ClickEvent<Button> evento) {
        try {
            var atendimento = formulario.criarAtendimento();
            servico.salvar(atendimento);

            var atualTela = UI.getCurrent();
            atualTela.navigate(AtendimentoHistoricoRota.class);
        } catch (RuntimeException excecao) {
            tratar(excecao);
        }
    }

    private void cancelar(ClickEvent<Button> evento) {
    }

    private void tratar(RuntimeException excecao) {
        var mensagem = excecao.getMessage();

        var conteudo = "<ul><li>" + mensagem + "</li></ul>";
        var html = new Html(conteudo);

        var dialogo = new ConfirmDialog();
        dialogo.setHeader("Erro");
        dialogo.add(html);
        dialogo.setConfirmText("Fechar");
        dialogo.open();
    }
}
