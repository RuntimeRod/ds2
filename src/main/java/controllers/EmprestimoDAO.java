package controllers;

import model.Emprestimo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO (Data Access Object) para gerenciar os empréstimos
 * Esta classe implementa o padrão Singleton para garantir uma única instância
 * Em uma aplicação real, esta classe se conectaria a um banco de dados
 */
public class EmprestimoDAO {

    // Instância única do DAO (Singleton)
    private static EmprestimoDAO instancia;

    // Lista que simula o armazenamento de empréstimos (substitui o banco de dados)
    private List<Emprestimo> emprestimos;

    /**
     * Construtor privado (Singleton)
     */
    private EmprestimoDAO() {
        emprestimos = new ArrayList<>();

        // Adiciona alguns empréstimos de exemplo para o histórico
        adicionarEmprestimosExemplo();
    }

    /**
     * Obtém a instância única do DAO
     *
     * @return A instância do DAO
     */
    public static EmprestimoDAO getInstancia() {
        if (instancia == null) {
            instancia = new EmprestimoDAO();
        }
        return instancia;
    }

    /**
     * Obtém a lista de empréstimos
     *
     * @return Lista de empréstimos
     */
    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    /**
     * Adiciona um empréstimo à lista
     *
     * @param emprestimo O empréstimo a ser adicionado
     */
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    /**
     * Adiciona empréstimos de exemplo para demonstração
     * Em uma aplicação real, isto seria removido
     */
    private void adicionarEmprestimosExemplo() {
        // Exemplo 1: Empréstimo de R$ 5.000,00 em 12 parcelas
        Emprestimo exemplo1 = new Emprestimo(5000.00, 12, 0.015, 448.48);
        adicionarEmprestimo(exemplo1);

        // Exemplo 2: Empréstimo de R$ 10.000,00 em 24 parcelas
        Emprestimo exemplo2 = new Emprestimo(10000.00, 24, 0.015, 482.43);
        adicionarEmprestimo(exemplo2);

        // Exemplo 3: Empréstimo de R$ 2.500,00 em 6 parcelas
        Emprestimo exemplo3 = new Emprestimo(2500.00, 6, 0.015, 430.16);
        adicionarEmprestimo(exemplo3);
    }
}