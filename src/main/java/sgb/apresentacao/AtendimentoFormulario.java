package sgb.apresentacao;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.shared.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sgb.entidades.Aluno;
import sgb.entidades.Atendimento;
import sgb.entidades.Marcador;
import sgb.negocio.AlunoServico;
import sgb.negocio.MarcadorServico;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class AtendimentoFormulario extends FormLayout {

    private TextField idCampo;
    private final ComboBox<Aluno> alunoComboBox = new ComboBox<>("Nome do Aluno");
    private Select<String> cursoCampo;
    private IntegerField periodoCampo;
    private DatePicker dataCampo;
    private Select<String> apoioCampo;
    private TextArea anotacoesCampo;
    private List<String> marcadoresSelecionados;
    private CustomField contatoCampo;
    private CustomField contatoRelacionadoCampo;
    private VerticalLayout container;
    private ComboBox<String> marcadoresSelecao;
    private VerticalLayout marcadoresContainer;
    private Button saveButton;
    private final MarcadorServico marcadorServico;
    private final AlunoServico alunoServico;

    @Autowired
    public AtendimentoFormulario(MarcadorServico marcadorServico, AlunoServico alunoServico) {
        this.marcadorServico = marcadorServico;
        this.alunoServico = alunoServico;
        this.marcadoresSelecionados = new ArrayList<>(); // Para não iniciar como null
        inicializarForm();
        addMarcadores();
        addAlunos();
    }

    private void inicializarForm() {
        container = new VerticalLayout();
        idCampo = new TextField("Id: ");
        idCampo.setEnabled(false);

        alunoComboBox.setItems(alunoServico.listar());
        alunoComboBox.setItemLabelGenerator(Aluno::getNome);

        cursoCampo = new Select<>();
        cursoCampo.setLabel("Curso: ");
        cursoCampo.setItems("CC","Design","GTI","ADS","SI","Pós","Mestrado","Doutorado","Outro");
        cursoCampo.setPlaceholder("Curso...");
        cursoCampo.setEnabled(false);

        dataCampo = new DatePicker();
        dataCampo.setLabel("Data do últ. atendimento: ");

        periodoCampo = new IntegerField();
        periodoCampo.setLabel("Período: ");
        periodoCampo.setValue(1);
        periodoCampo.setStepButtonsVisible(true);
        periodoCampo.setMax(12);
        periodoCampo.setMin(1);
        periodoCampo.setEnabled(false);

        apoioCampo = new Select<>();
        apoioCampo.setLabel("Último Atendente: ");
        apoioCampo.setItems("Ana Catharina Carvalho","Kleyton Silva","Giovana Barros");

        anotacoesCampo = new TextArea("Anotações: ");
        anotacoesCampo.setWidth("610px");

        marcadoresSelecao = new ComboBox<>("Marcadores");
        marcadoresContainer = new VerticalLayout();

        MemoryBuffer memoryBuffer = new MemoryBuffer();
        Upload upload = new Upload(memoryBuffer);
        Checkbox checkbox = new Checkbox();
        checkbox.setLabel("Anexar Laudo");

        saveButton = new Button("Salvar");
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout linha1 = new HorizontalLayout(idCampo,apoioCampo,dataCampo);
        HorizontalLayout linha2 = new HorizontalLayout(alunoComboBox, cursoCampo,periodoCampo);
        HorizontalLayout linha3 = new HorizontalLayout(anotacoesCampo);
        HorizontalLayout linha4 = new HorizontalLayout(marcadoresSelecao, checkbox);
        HorizontalLayout linha5 = new HorizontalLayout(marcadoresContainer);
        HorizontalLayout linha6 = new HorizontalLayout(saveButton);

        alunoComboBox.addValueChangeListener(event -> {
            Aluno aluno = event.getValue();
            if (aluno != null) {
                cursoCampo.setValue(aluno.getCurso());
                periodoCampo.setValue(aluno.getPeriodo());
                if (!aluno.isMatricula()) {
                    Notification.show("Atenção: Aluno com matrícula não ativa", 3000, Notification.Position.MIDDLE);
                }
            } else {
                cursoCampo.clear();
                periodoCampo.clear();
            }
        });

        checkbox.addValueChangeListener(e->{
            boolean laudo = checkbox.getValue();
            if (laudo) {
                linha4.add(upload);
            } else {
                upload.setVisible(false);
            }
        });
        marcadoresSelecao.addValueChangeListener(e -> {
            String marcador = e.getValue();
            if (marcador != null && !marcadoresSelecionados.contains(marcador)) {
                marcadoresSelecionados.add(marcador);
                Span filterBadge = filtroBadge(marcador);
                marcadoresContainer.add(filterBadge);
            }
            marcadoresSelecao.clear();
        });

        container.add(linha1, linha2, linha3, linha4, linha5, linha6);

        add(container);
    }

    private Span filtroBadge(String marcador) {
        Button clearButton = new Button(VaadinIcon.CLOSE_SMALL.create());
        clearButton.addThemeVariants(ButtonVariant.LUMO_CONTRAST, ButtonVariant.LUMO_TERTIARY_INLINE);
        clearButton.getStyle().set("margin-inline-start", "var(--lumo-space-xs)");
        clearButton.getElement().setAttribute("aria-label", "Clear filter: " + marcador);
        clearButton.getElement().setAttribute("title", "Clear filter: " + marcador);

        Span badge = new Span(new Span(marcador), clearButton);
        badge.getElement().getThemeList().add("badge contrast pill");

        clearButton.addClickListener(event -> {
            badge.getElement().removeFromParent();
            marcadoresSelecionados.remove(marcador);
        });

        return badge;
    }

    private void addMarcadores() {
        if (marcadorServico != null) {
            List<Marcador> marcadores = marcadorServico.listar();
            if (!marcadores.isEmpty()) {
                List<String> nomesMarcadores = marcadores.stream()
                        .map(Marcador::getNome)
                        .collect(Collectors.toList());
                marcadoresSelecao.setItems(nomesMarcadores);
            } else {
                marcadoresSelecao.setEnabled(false);
                // Criar caixa de dialogo
                Label labelSemMarcadores = new Label("Nenhum marcador disponível");
                container.add(labelSemMarcadores);
            }
        }
    }

    private void addAlunos() {
        if (alunoServico != null) {
            List<Aluno> alunos = alunoServico.listar();
            if (!alunos.isEmpty()) {
                alunoComboBox.setItems(alunos);
            } else {
                alunoComboBox.setEnabled(false);
                // Criar caixa de dialogo
                Label labelSemBiblioteca = new Label("Sem biblioteca de alunos disponível");
                container.add(labelSemBiblioteca);
            }
        }
    }

    public Atendimento criarAtendimento() {
        Atendimento atendimento = new Atendimento(
                alunoComboBox.getValue(),
                dataCampo.getValue(),
                apoioCampo.getValue(),
                anotacoesCampo.getValue()
        );
        if (marcadorServico != null) {
            List<Marcador> marcadores = marcadorServico.listar();
            for (String nomeMarcador : marcadoresSelecionados) {
                Optional<Marcador> marcadorOptional = marcadores.stream()
                        .filter(marcador -> marcador.getNome().equals(nomeMarcador))
                        .findFirst();
                marcadorOptional.ifPresent(atendimento::adicionarMarcador);
            }
        }
        return atendimento;
    }

    public void preencherAtendimento(Atendimento atendimento) {
        atendimento.setAluno(alunoComboBox.getValue());
        atendimento.setCurso(cursoCampo.getValue());
        atendimento.setPeriodo(periodoCampo.getValue());
        atendimento.setData(dataCampo.getValue());
        atendimento.setPsicologo(apoioCampo.getValue());
        atendimento.setAnotacoes(anotacoesCampo.getValue());
        atendimento.getMarcadores().clear();

        if (marcadorServico != null) {
            List<Marcador> marcadores = marcadorServico.listar();
            for (String nomeMarcador : marcadoresSelecionados) {
                Optional<Marcador> marcadorOptional = marcadores.stream()
                        .filter(marcador -> marcador.getNome().equals(nomeMarcador))
                        .findFirst();
                marcadorOptional.ifPresent(atendimento::adicionarMarcador);
            }
        }
    }

    public void preencherCampos(Atendimento atendimento) {
        idCampo.setValue(String.valueOf(atendimento.getId()));
        alunoComboBox.setValue(alunoServico.listarAlunoPorId(atendimento.getAlunoId()));
        cursoCampo.setValue(atendimento.getCurso());
        periodoCampo.setValue(atendimento.getPeriodo());
        dataCampo.setValue(atendimento.getData());
        apoioCampo.setValue(atendimento.getPsicologo());
        anotacoesCampo.setValue(atendimento.getAnotacoes());
        if (marcadorServico != null) {
            Set<String> nomesMarcadores = atendimento.getMarcadores().stream()
                    .map(Marcador::getNome)
                    .collect(Collectors.toSet());
            marcadoresSelecionados.clear();
            marcadoresSelecionados.addAll(nomesMarcadores);
            marcadoresContainer.removeAll();
            nomesMarcadores.forEach(nomeMarcador -> {
                Span filterBadge = filtroBadge(nomeMarcador);
                marcadoresContainer.add(filterBadge);
            });
        }
    }

    public Registration addSaveListener(ComponentEventListener<ClickEvent<Button>> listener) {
        return saveButton.addClickListener(listener);
    }
}