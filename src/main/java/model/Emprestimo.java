package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um empréstimo
 */
public class Emprestimo {

    private double valorEmprestimo;
    private int numeroParcelas;
    private double taxaJuros;
    private double valorParcela;
    private LocalDate dataContratacao;
    private List<Parcela> parcelas;

    /**
     * Construtor completo
     *
     * @param valorEmprestimo Valor solicitado para o empréstimo
     * @param numeroParcelas Número de parcelas para pagamento
     * @param taxaJuros Taxa de juros mensal (em decimal)
     * @param valorParcela Valor de cada parcela
     */
    public Emprestimo(double valorEmprestimo, int numeroParcelas, double taxaJuros, double valorParcela) {
        this.valorEmprestimo = valorEmprestimo;
        this.numeroParcelas = numeroParcelas;
        this.taxaJuros = taxaJuros;
        this.valorParcela = valorParcela;
        this.dataContratacao = LocalDate.now();
        this.parcelas = new ArrayList<>();
    }

    /**
     * Calcula o valor total do empréstimo (soma das parcelas)
     *
     * @return O valor total a ser pago
     */
    public double getValorTotal() {
        return valorParcela * numeroParcelas;
    }

    // Getters e Setters

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

    public double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    /**
     * Retorna a data de contratação formatada
     *
     * @return String formatada da data de contratação
     */
    public String getDataContratacaoFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataContratacao.format(formatter);
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "valorEmprestimo=" + valorEmprestimo +
                ", numeroParcelas=" + numeroParcelas +
                ", taxaJuros=" + taxaJuros +
                ", valorParcela=" + valorParcela +
                ", dataContratacao=" + dataContratacao +
                '}';
    }
}