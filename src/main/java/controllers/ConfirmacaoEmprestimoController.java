package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Controlador da tela de confirmação do empréstimo
 */
public class ConfirmacaoEmprestimoController {

    @FXML
    private Button btnVoltar;

    @FXML
    private Label lblValorAprovado;

    // Formatar valores monetários
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    /**
     * Inicializa o controlador.
     */
    public void initialize() {
        // Inicialização, se necessário
    }

    /**
     * Define o valor do empréstimo aprovado
     * @param valorEmprestimo O valor do empréstimo
     */
    public void setValorEmprestimo(double valorEmprestimo) {
        lblValorAprovado.setText(currencyFormat.format(valorEmprestimo));
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