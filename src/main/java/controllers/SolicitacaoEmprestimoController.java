package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Emprestimo;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Controlador da tela de solicitação de empréstimo
 */
public class SolicitacaoEmprestimoController {

    @FXML
    private Button btnContinuar;

    @FXML
    private Button btnVoltar;

    @FXML
    private Label lblParcelas;

    @FXML
    private Label lblTaxaJuros;

    @FXML
    private Label lblTotalPagar;

    @FXML
    private Label lblValorParcela;

    @FXML
    private Slider sliderParcelas;

    @FXML
    private TextField txtValorEmprestimo;

    // Taxa de juros fixa (1.5% ao mês)
    private final double TAXA_JUROS = 0.015;

    // Formatar valores monetários
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    private final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    /**
     * Inicializa o controlador.
     */
    public void initialize() {
        // Configura a taxa de juros
        lblTaxaJuros.setText(decimalFormat.format(TAXA_JUROS * 100) + "% ao mês");

        // Listener para o slider de parcelas
        sliderParcelas.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int numeroParcelas = newValue.intValue();
                lblParcelas.setText(String.valueOf(numeroParcelas));
                calcularEmprestimo();
            }
        });

        // Listener para o campo de valor do empréstimo
        txtValorEmprestimo.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                try {
                    // Verifica se é um número válido
                    double valor = Double.parseDouble(newValue.replaceAll("[^\\d,.]", "").replace(",", "."));
                    calcularEmprestimo();
                } catch (NumberFormatException e) {
                    // Se não for um número válido, limpa os cálculos
                    lblValorParcela.setText("R$ 0,00");
                    lblTotalPagar.setText("R$ 0,00");
                }
            } else {
                // Se o campo estiver vazio, limpa os cálculos
                lblValorParcela.setText("R$ 0,00");
                lblTotalPagar.setText("R$ 0,00");
            }
        });
    }

    /**
     * Calcula os valores do empréstimo com base nos dados fornecidos
     */
    private void calcularEmprestimo() {
        try {
            String valorTexto = txtValorEmprestimo.getText().replaceAll("[^\\d,.]", "").replace(",", ".");
            if (valorTexto.isEmpty()) {
                return;
            }

            double valorEmprestimo = Double.parseDouble(valorTexto);
            int numeroParcelas = (int) sliderParcelas.getValue();

            // Calcula o valor da parcela usando a fórmula de amortização de empréstimos
            double valorParcela = valorEmprestimo * (TAXA_JUROS * Math.pow(1 + TAXA_JUROS, numeroParcelas))
                    / (Math.pow(1 + TAXA_JUROS, numeroParcelas) - 1);

            double valorTotal = valorParcela * numeroParcelas;

            // Atualiza os labels com os valores calculados
            lblValorParcela.setText(currencyFormat.format(valorParcela));
            lblTotalPagar.setText(currencyFormat.format(valorTotal));

        } catch (NumberFormatException e) {
            // Em caso de erro, não atualiza os valores
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

    /**
     * Abre a tela de detalhes do empréstimo
     * @param event O evento que disparou esta ação
     */
    @FXML
    void abrirTelaDetalhes(ActionEvent event) {
        try {
            // Verifica se os dados estão preenchidos corretamente
            if (txtValorEmprestimo.getText().isEmpty()) {
                // Poderia adicionar uma mensagem de erro aqui
                return;
            }

            // Cria um objeto de empréstimo com os dados informados
            double valorEmprestimo = Double.parseDouble(txtValorEmprestimo.getText().replaceAll("[^\\d,.]", "").replace(",", "."));
            int numeroParcelas = (int) sliderParcelas.getValue();

            // Calcula o valor da parcela
            double valorParcela = valorEmprestimo * (TAXA_JUROS * Math.pow(1 + TAXA_JUROS, numeroParcelas))
                    / (Math.pow(1 + TAXA_JUROS, numeroParcelas) - 1);

            Emprestimo emprestimo = new Emprestimo(valorEmprestimo, numeroParcelas, TAXA_JUROS, valorParcela);

            // Carrega a tela de detalhes do empréstimo
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DetalhesEmprestimo.fxml"));
            Parent root = loader.load();

            // Obtém o controlador da tela de detalhes
            DetalhesEmprestimoController controller = loader.getController();

            // Passa o objeto de empréstimo para o controlador da tela de detalhes
            controller.setEmprestimo(emprestimo);

            // Obtém o stage atual
            Stage stage = (Stage) btnContinuar.getScene().getWindow();

            // Configura a nova cena
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

            // Atualiza o stage
            stage.setScene(scene);
            stage.setTitle("Detalhes do Empréstimo");
            stage.show();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}