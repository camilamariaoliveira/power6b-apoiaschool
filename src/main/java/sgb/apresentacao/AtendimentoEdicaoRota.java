package sgb.apresentacao;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import sgb.entidades.Atendimento;
import sgb.negocio.AtendimentoServico;

@Route("/atendimento/edicao")
public class AtendimentoEdicaoRota extends VerticalLayout implements HasUrlParameter<Integer> {
    private AtendimentoServico servico;
    private AtendimentoFormulario formulario;
    private Atendimento atendimento;

    public AtendimentoEdicaoRota(AtendimentoServico servico) {
        this.servico = servico;

        formulario = new AtendimentoFormulario();
        add(formulario);

        var salvarBotao = new Button("Salvar");
        salvarBotao.addClickListener(this::salvar);

        var excluirBotao = new Button("Excluir");
        excluirBotao.addClickListener(this::excluir);

        var botoes = new HorizontalLayout();
        botoes.add(salvarBotao);
        botoes.add(excluirBotao);

        add(botoes);
    }

    @Override
    public void setParameter(BeforeEvent event, Integer parameter) {
        atendimento = servico.obter(parameter);
        formulario.preencherCampos(atendimento);
    }

    private void salvar(ClickEvent<Button> evento) {
        try {
            formulario.preencherAtendimento(atendimento);
            servico.salvar(atendimento);

            var atualTela = UI.getCurrent();
            atualTela.navigate(AtendimentoHistoricoRota.class);
        } catch (RuntimeException excecao) {
            tratar(excecao);
        }
    }

    private void excluir(ClickEvent<Button> evento) {
        try {
            var id = atendimento.getId();
            servico.excluir(id);

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
