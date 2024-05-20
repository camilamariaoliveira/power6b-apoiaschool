package sgb.apresentacao;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import sgb.negocio.AtendimentoServico;

@Route("/atendimento/criacao")
public class AtendimentoCriacaoRota extends VerticalLayout {
    private AtendimentoServico servico;
    private AtendimentoFormulario formulario;

    public AtendimentoCriacaoRota(AtendimentoServico servico) {
        this.servico = servico;

        formulario = new AtendimentoFormulario();
        add(formulario);

        var salvarBotao = new Button("Salvar");
        salvarBotao.addClickListener(this::salvar);

        var botoes = new HorizontalLayout();
        botoes.add(salvarBotao);
        add(botoes);
    }

    private void salvar(ClickEvent<Button> buttonClickEvent) {
        try {
            var atendimento = formulario.criarAtendimento();
            servico.salvar(atendimento);

            var atualTela = UI.getCurrent();
            atualTela.navigate(AtendimentoHistoricoRota.class);
        } catch (RuntimeException excecao) {
            tratar(excecao);
        }
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
