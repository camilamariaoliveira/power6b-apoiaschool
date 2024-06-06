package sgb.apresentacao;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import sgb.entidades.Aluno;
import sgb.negocio.AlunoServico;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Route("aluno/biblioteca")
public class AlunoBibliotecaRota  extends VerticalLayout {

    private AlunoServico servico;
    private List<Aluno> alunos ;
    private Grid<Aluno> grid = new Grid<>(Aluno.class);

    @Autowired
    public AlunoBibliotecaRota(AlunoServico servico) {
        this.servico = servico;
        this.alunos = new ArrayList<>();

        MemoryBuffer memoryBuffer = new MemoryBuffer();

        Upload upload = new Upload(memoryBuffer);
        upload.setAcceptedFileTypes(".xlsx", ".xls");

        upload.addSucceededListener(event -> {
            String fileName = event.getFileName();
            InputStream inputStream = memoryBuffer.getInputStream();
            try {
                processExcelFile(inputStream, fileName);
                Notification.show("Arquivo " + fileName + " carregado com sucesso!");
                servico.salvarTodos(alunos);
                updateGrid(); // Atualiza o Grid após carregar os dados
            } catch (IOException e) {
                Notification.show("Erro ao processar o arquivo: " + e.getMessage(), 5000, Notification.Position.MIDDLE);
            }
        });
        grid.setColumns("nome", "curso", "periodo", "matricula");
        add(upload, grid);
    }

    private void processExcelFile(InputStream inputStream, String fileName) throws IOException {
        Workbook workbook = fileName.endsWith(".xls") ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.iterator().next();
        alunos.clear(); // Limpa a lista de alunos antes de adicionar novos dados
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue; // Pula o cabeçalho
            }
            String nome = row.getCell(0).getStringCellValue();
            String curso = row.getCell(1).getStringCellValue();
            int periodo = (int) row.getCell(2).getNumericCellValue();
            boolean matricula = row.getCell(3).getBooleanCellValue();

            Aluno aluno = new Aluno(nome, curso, periodo, matricula);
            alunos.add(aluno);
        }
        workbook.close();
    }

    private void updateGrid() {grid.setItems(servico.listar());}
}