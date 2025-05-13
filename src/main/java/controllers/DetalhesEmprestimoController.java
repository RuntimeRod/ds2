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
import model.Parcela;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Controlador da tela de detalhes do empréstimo
 */
public class DetalhesEmprestimoController {

    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<Parcela, String> colDataVencimento;

    @FXML
    private TableColumn<Parcela, Integer> colNumeroParcela;

    @FXML
    private TableColumn<Parcela, String> colSaldoDevedor;

    @FXML
    private TableColumn<Parcela, String> colValorParcela;

    @FXML
    private Label lblNumeroParcelas;

    @FXML
    private Label lblTaxaJuros;

    @FXML
    private Label lblValorSolicitado;

    @FXML
    private Label lblValorTotal;

    @FXML
    private TableView<Parcela> tblParcelas;

    // Objeto de empréstimo
    private Emprestimo emprestimo;

    // Formatar valores monetários
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Inicializa o controlador.
     */
    public void initialize() {
        // Configura as colunas da tabela
        colNumeroParcela.setCellValueFactory(new PropertyValueFactory<>("numeroParcela"));
        colDataVencimento.setCellValueFactory(new PropertyValueFactory<>("dataVencimento"));
        colValorParcela.setCellValueFactory(new PropertyValueFactory<>("valorParcelaFormatado"));
        colSaldoDevedor.setCellValueFactory(new PropertyValueFactory<>("saldoDevedorFormatado"));
    }

    /**
     * Define o objeto de empréstimo e atualiza a interface
     * @param emprestimo O objeto de empréstimo
     */
    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;

        // Atualiza os labels com os valores do empréstimo
        lblValorSolicitado.setText(currencyFormat.format(emprestimo.getValorEmprestimo()));
        lblNumeroParcelas.setText(String.valueOf(emprestimo.getNumeroParcelas()));
        lblTaxaJuros.setText(String.format("%.2f%% ao mês", emprestimo.getTaxaJuros() * 100));
        lblValorTotal.setText(currencyFormat.format(emprestimo.getValorParcela() * emprestimo.getNumeroParcelas()));

        // Carrega a tabela de parcelas
        carregarTabelaParcelas();
    }

    /**
     * Carrega a tabela de parcelas com os detalhes do empréstimo
     */
    private void carregarTabelaParcelas() {
        ObservableList<Parcela> parcelas = FXCollections.observableArrayList();

        double valorParcela = emprestimo.getValorParcela();
        double saldoDevedor = emprestimo.getValorEmprestimo();
        double taxaJuros = emprestimo.getTaxaJuros();
        LocalDate dataAtual = LocalDate.now();

        for (int i = 1; i <= emprestimo.getNumeroParcelas(); i++) {
            // Calcula a data de vencimento (30 dias a partir da data atual)
            LocalDate dataVencimento = dataAtual.plusMonths(i);

            // Calcula juros do período
            double juros = saldoDevedor * taxaJuros;

            // Calcula amortização (valor pago do principal)
            double amortizacao = valorParcela - juros;

            // Atualiza o saldo devedor
            double novoSaldo = saldoDevedor - amortizacao;

            // Garante que o saldo não fique negativo na última parcela
            if (i == emprestimo.getNumeroParcelas()) {
                novoSaldo = 0;
            }

            // Cria o objeto de parcela
            Parcela parcela = new Parcela(
                    i,
                    dataVencimento.format(dateFormatter),
                    valorParcela,
                    saldoDevedor,
                    novoSaldo
            );

            parcelas.add(parcela);

            // Atualiza o saldo para a próxima iteração
            saldoDevedor = novoSaldo;
        }

        // Adiciona as parcelas à tabela
        tblParcelas.setItems(parcelas);
    }

    /**
     * Volta para a tela de solicitação de empréstimo
     * @param event O evento que disparou esta ação
     */
    @FXML
    void voltarTelaEmprestimo(ActionEvent event) {
        try {
            // Carrega a tela de solicitação de empréstimo
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SolicitacaoEmprestimo.fxml"));
            Parent root = loader.load();

            // Obtém o stage atual
            Stage stage = (Stage) btnVoltar.getScene().getWindow();

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
     * Confirma o empréstimo e abre a tela de confirmação
     * @param event O evento que disparou esta ação
     */
    @FXML
    void confirmarEmprestimo(ActionEvent event) {
        try {
            // Carrega a tela de confirmação
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ConfirmacaoEmprestimo.fxml"));
            Parent root = loader.load();

            // Obtém o controlador da tela de confirmação
            ConfirmacaoEmprestimoController controller = loader.getController();

            // Passa o valor do empréstimo para o controlador da tela de confirmação
            controller.setValorEmprestimo(emprestimo.getValorEmprestimo());

            // Salva o empréstimo (isto deveria ir para um banco de dados em uma aplicação real)
            salvarEmprestimo();

            // Obtém o stage atual
            Stage stage = (Stage) btnConfirmar.getScene().getWindow();

            // Configura a nova cena
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

            // Atualiza o stage
            stage.setScene(scene);
            stage.setTitle("Empréstimo Aprovado");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Salva o empréstimo (simulação)
     * Em uma aplicação real, isto iria para um banco de dados
     */
    private void salvarEmprestimo() {
        // Obtém a lista atual de empréstimos
        List<Emprestimo> emprestimos = EmprestimoDAO.getInstancia().getEmprestimos();

        // Adiciona o empréstimo atual à lista
        emprestimos.add(emprestimo);

        // Em uma aplicação real, aqui seria feita a persistência no banco de dados
        System.out.println("Empréstimo salvo com sucesso: " + emprestimo);
    }
}