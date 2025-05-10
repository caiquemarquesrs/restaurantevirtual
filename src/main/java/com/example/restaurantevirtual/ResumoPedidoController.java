package com.example.restaurantevirtual;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResumoPedidoController implements Initializable {

    @FXML
    private ListView<String> listViewPedido;

    @FXML
    private Label labelTotal;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnConcluir;

    private ObservableList<ItemPedido> itensPedido;

    private double total = 0.0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setItensPedido(ObservableList<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
        exibirItensPedido();
    }

    private void exibirItensPedido() {
        listViewPedido.getItems().clear();
        total = 0.0;
        for (ItemPedido item : itensPedido) {
            double subtotal = item.getQuantidade() * item.getItem().getPreco();
            total += subtotal;
            NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            String linha = String.format("%s - %d x %s = %s", item.getItem().getNome(), item.getQuantidade(), formatoMoeda.format(item.getItem().getPreco()), formatoMoeda.format(subtotal));
            listViewPedido.getItems().add(linha);
        }
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        labelTotal.setText("Total: " + formatoMoeda.format(total));
    }

    @FXML
    private void handleVoltar(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Erro ao voltar para o menu principal: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleConcluir(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Pedido Concluído");
        alert.setHeaderText("Pedido Realizado com Sucesso!");
        alert.showAndWait();
        handleVoltar(event);
    }
}
