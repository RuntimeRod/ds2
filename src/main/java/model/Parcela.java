package model;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Classe que representa uma parcela de um empréstimo
 */
public class Parcela {

    private int numeroParcela;
    private String dataVencimento;
    private double valorParcela;
    private double saldoDevedorAnterior;
    private double saldoDevedorAtual;

    // Formatador para valores monetários em reais
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    /**
     * Construtor completo
     *
     * @param numeroParcela Número da parcela
     * @param dataVencimento Data de vencimento da parcela
     * @param valorParcela Valor da parcela
     * @param saldoDevedorAnterior Saldo devedor antes do pagamento da parcela
     * @param saldoDevedorAtual Saldo devedor após o pagamento da parcela
     */
    public Parcela(int numeroParcela, String dataVencimento, double valorParcela,
                   double saldoDevedorAnterior, double saldoDevedorAtual) {
        this.numeroParcela = numeroParcela;
        this.dataVencimento = dataVencimento;
        this.valorParcela = valorParcela;
        this.saldoDevedorAnterior = saldoDevedorAnterior;
        this.saldoDevedorAtual = saldoDevedorAtual;
    }

    /**
     * Retorna o valor da parcela formatado como moeda (R$)
     *
     * @return Valor da parcela formatado
     */
    public String getValorParcelaFormatado() {
        return currencyFormat.format(valorParcela);
    }

    /**
     * Retorna o saldo devedor formatado como moeda (R$)
     *
     * @return Saldo devedor formatado
     */
    public String getSaldoDevedorFormatado() {
        return currencyFormat.format(saldoDevedorAtual);
    }

    // Getters e Setters

    public int getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(int numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public double getSaldoDevedorAnterior() {
        return saldoDevedorAnterior;
    }

    public void setSaldoDevedorAnterior(double saldoDevedorAnterior) {
        this.saldoDevedorAnterior = saldoDevedorAnterior;
    }

    public double getSaldoDevedorAtual() {
        return saldoDevedorAtual;
    }

    public void setSaldoDevedorAtual(double saldoDevedorAtual) {
        this.saldoDevedorAtual = saldoDevedorAtual;
    }

    @Override
    public String toString() {
        return "Parcela{" +
                "numeroParcela=" + numeroParcela +
                ", dataVencimento='" + dataVencimento + '\'' +
                ", valorParcela=" + valorParcela +
                ", saldoDevedorAnterior=" + saldoDevedorAnterior +
                ", saldoDevedorAtual=" + saldoDevedorAtual +
                '}';
    }
}