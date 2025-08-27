package dev.java10x.CadastroDeNinjas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class MainApp extends Application {

    private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        // Inicializa o contexto do Spring Boot antes de abrir o JavaFX
        springContext = new SpringApplicationBuilder(dev.java10x.CadastroDeNinjas.CadastroDeNinjasApplication.class)
                .run();
    }

    @Override
    public void start(Stage primaryStage) {
        // Exemplo mínimo de tela JavaFX
        Label label = new Label("Sistema Cadastro de Ninjas!");
        Scene scene = new Scene(label, 400, 200);

        primaryStage.setTitle("Cadastro de Ninjas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        // Fecha o contexto Spring ao fechar o JavaFX
        springContext.close();
    }

    public static void main(String[] args) {
        launch(args); // Inicia o JavaFX
    }
}
