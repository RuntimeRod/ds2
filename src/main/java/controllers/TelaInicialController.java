package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controlador da tela inicial do aplicativo bancário
 */
public class TelaInicialController {

    @FXML
    private Button btnNovoEmprestimo;

    @FXML
    private Button btnHistoricoEmprestimos;

    /**
     * Inicializa o controlador.
     */
    public void initialize() {
        // Inicialização, se necessário
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
     * Abre a tela de histórico de empréstimos
     * @param event O evento que disparou esta ação
     */
    @FXML
    void abrirHistoricoEmprestimos(ActionEvent event) {
        try {
            // Carrega a tela de histórico de empréstimos
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/HistoricoEmprestimos.fxml"));
            Parent root = loader.load();

            // Obtém o stage atual
            Stage stage = (Stage) btnHistoricoEmprestimos.getScene().getWindow();

            // Configura a nova cena
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

            // Atualiza o stage
            stage.setScene(scene);
            stage.setTitle("Histórico de Empréstimos");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}