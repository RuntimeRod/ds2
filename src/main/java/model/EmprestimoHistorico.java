package model;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Classe que representa um item do histórico de empréstimos
 */
public class EmprestimoHistorico {

    private String dataContratacao;
    private double valorEmprestimo;
    private int numeroParcelas;
    private double valorParcela;
    private String status;

    // Formatador para valores monetários em reais
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Construtor que cria um item histórico a partir de um empréstimo
     *
     * @param emprestimo O empréstimo a ser convertido para histórico
     */
    public EmprestimoHistorico(Emprestimo emprestimo) {
        this.dataContratacao = emprestimo.getDataContratacao().format(dateFormatter);
        this.valorEmprestimo = emprestimo.getValorEmprestimo();
        this.numeroParcelas = emprestimo.getNumeroParcelas();
        this.valorParcela = emprestimo.getValorParcela();
        this.status = "Ativo"; // Por padrão, todos os empréstimos são iniciados como ativos
    }

    /**
     * Construtor completo
     *
     * @param dataContratacao Data de contratação
     * @param valorEmprestimo Valor do empréstimo
     * @param numeroParcelas Número de parcelas
     * @param valorParcela Valor da parcela
     * @param status Status do empréstimo (Ativo, Quitado, etc.)
     */
    public EmprestimoHistorico(String dataContratacao, double valorEmprestimo, int numeroParcelas,
                               double valorParcela, String status) {
        this.dataContratacao = dataContratacao;
        this.valorEmprestimo = valorEmprestimo;
        this.numeroParcelas = numeroParcelas;
        this.valorParcela = valorParcela;
        this.status = status;
    }

    /**
     * Retorna o valor do empréstimo formatado como moeda (R$)
     *
     * @return Valor do empréstimo formatado
     */
    public String getValorEmprestimoFormatado() {
        return currencyFormat.format(valorEmprestimo);
    }

    /**
     * Retorna o valor da parcela formatado como moeda (R$)
     *
     * @return Valor da parcela formatado
     */
    public String getValorParcelaFormatado() {
        return currencyFormat.format(valorParcela);
    }

    // Getters e Setters

    public String getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(String dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public double getValorEmprestimo() {
        return valorEmprestimo;
    }

    public void setValorEmprestimo(double valorEmprestimo) {
        this.valorEmprestimo = valorEmprestimo;
    }

    public int getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EmprestimoHistorico{" +
                "dataContratacao='" + dataContratacao + '\'' +
                ", valorEmprestimo=" + valorEmprestimo +
                ", numeroParcelas=" + numeroParcelas +
                ", valorParcela=" + valorParcela +
                ", status='" + status + '\'' +
                '}';
    }
}