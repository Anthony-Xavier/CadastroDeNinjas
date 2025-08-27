package dev.java10x.CadastroDeNinjas;

import dev.java10x.CadastroDeNinjas.model.MissaoModel;
import dev.java10x.CadastroDeNinjas.model.NinjaModel;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpClient;

public class NinjaController {

    @FXML
    private TextField nameField, emailField, ageField;

    @FXML
    private ComboBox<MissaoModel> missaoComboBox;

    private final ObjectMapper mapper = new ObjectMapper();

    public void initialize() {
        // Carrega missões do backend
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/missoes"))
                .GET()
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(json -> {
                    try {
                        List<MissaoModel> missoes = List.of(mapper.readValue(json, MissaoModel[].class));
                        missaoComboBox.getItems().addAll(missoes);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    @FXML
    private void handleCadastrar() {
        try {
            String name = nameField.getText();
            String email = emailField.getText();
            int age = Integer.parseInt(ageField.getText());
            MissaoModel missao = missaoComboBox.getValue();

            NinjaModel ninja = new NinjaModel();
            ninja.setName(name);
            ninja.setEmail(email);
            ninja.setAge(age);
            ninja.setMissoes(missao);

            String json = mapper.writeValueAsString(ninja);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/ninjas"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenAccept(response -> System.out.println("Resposta: " + response.body()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}