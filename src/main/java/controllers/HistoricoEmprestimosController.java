package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Emprestimo;
import model.EmprestimoHistorico;

import java.io.IOException;
import java.util.List;

/**
 * Controlador da tela de histórico de empréstimos
 */
public class HistoricoEmprestimosController {

    @FXML
    private Button btnNovoEmprestimo;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<EmprestimoHistorico, String> colDataContratacao;

    @FXML
    private TableColumn<EmprestimoHistorico, Integer> colNumeroParcelas;

    @FXML
    private TableColumn<EmprestimoHistorico, String> colStatus;

    @FXML
    private TableColumn<EmprestimoHistorico, String> colValorEmprestimo;

    @FXML
    private TableColumn<EmprestimoHistorico, String> colValorParcela;

    @FXML
    private Label lblTotalEmprestimos;

    @FXML
    private TableView<EmprestimoHistorico> tblHistoricoEmprestimos;

    /**
     * Inicializa o controlador.
     */
    public void initialize() {
        // Configura as colunas da tabela
        colDataContratacao.setCellValueFactory(new PropertyValueFactory<>("dataContratacao"));
        colValorEmprestimo.setCellValueFactory(new PropertyValueFactory<>("valorEmprestimoFormatado"));
        colNumeroParcelas.setCellValueFactory(new PropertyValueFactory<>("numeroParcelas"));
        colValorParcela.setCellValueFactory(new PropertyValueFactory<>("valorParcelaFormatado"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Carrega os dados do histórico
        carregarHistoricoEmprestimos();
    }

    /**
     * Carrega os dados históricos de empréstimos
     */
    private void carregarHistoricoEmprestimos() {
        // Obtém a lista de empréstimos
        List<Emprestimo> emprestimos = EmprestimoDAO.getInstancia().getEmprestimos();

        // Cria uma lista observável para os empréstimos históricos
        ObservableList<EmprestimoHistorico> historico = FXCollections.observableArrayList();

        // Converte os empréstimos para o formato histórico
        for (Emprestimo emp : emprestimos) {
            EmprestimoHistorico item = new EmprestimoHistorico(emp);
            historico.add(item);
        }

        // Atualiza a tabela
        tblHistoricoEmprestimos.setItems(historico);

        // Atualiza o contador de empréstimos
        lblTotalEmprestimos.setText(String.valueOf(historico.size()));
    }

    /**
     * Abre a tela de solicitação de empréstimo
     * @param event O evento que disparou esta ação
     */
    @FXML
    void abrirTelaEmprestimo(ActionEvent event) {
        try {
            // Carrega a tela de solicitação de empréstimo
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SolicitacaoEmprestimo.fxml"));
            Parent root = loader.load();

            // Obtém o stage atual
            Stage stage = (Stage) btnNovoEmprestimo.getScene().getWindow();

            // Configura a nova cena
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

            // Atualiza o stage
            stage.setScene(scene);
            stage.setTitle("Solicitar Empréstimo");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Volta para a tela inicial
     * @param event O evento que disparou esta ação
     */
    @FXML
    void voltarTelaInicial(ActionEvent event) {
        try {
            // Carrega a tela inicial
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/TelaInicial.fxml"));
            Parent root = loader.load();

            // Obtém o stage atual
            Stage stage = (Stage) btnVoltar.getScene().getWindow();

            // Configura a nova cena
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

            // Atualiza o stage
            stage.setScene(scene);
            stage.setTitle("Banco Digital");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}