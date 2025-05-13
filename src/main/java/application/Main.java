package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe principal da aplicação de empréstimo bancário
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Carrega o arquivo FXML da tela inicial
            Parent root = FXMLLoader.load(getClass().getResource("/views/TelaInicial.fxml"));

            // Configura a cena
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

            // Configura o stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("Banco Digital - Sistema de Empréstimos");
            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(600);
            primaryStage.setResizable(true);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método principal para iniciar a aplicação
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) {
        launch(args);
    }
}