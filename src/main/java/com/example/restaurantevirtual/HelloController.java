package com.example.restaurantevirtual;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Spinner<Integer> spinnerPrato1;

    @FXML
    private Spinner<Integer> spinnerPrato2;

    @FXML
    private Spinner<Integer> spinnerPrato3;

    @FXML
    private Spinner<Integer> spinnerSobremesa1;

    @FXML
    private Spinner<Integer> spinnerSobremesa2;

    @FXML
    private Spinner<Integer> spinnerSobremesa3;

    @FXML
    private Spinner<Integer> spinnerBebida1;

    @FXML
    private Spinner<Integer> spinnerBebida2;

    @FXML
    private Spinner<Integer> spinnerBebida3;

    @FXML
    private Button btnVerPedido;

    @FXML
    private ImageView imgPrato1;

    @FXML
    private ImageView imgPrato2;

    @FXML
    private ImageView imgPrato3;

    @FXML
    private ImageView imgSobremesa1;

    @FXML
    private ImageView imgSobremesa2;

    @FXML
    private ImageView imgSobremesa3;

    @FXML
    private ImageView imgBebida1;

    @FXML
    private ImageView imgBebida2;

    @FXML
    private ImageView imgBebida3;

    private ObservableList<ItemPedido> itensPedido = FXCollections.observableArrayList();

    private MenuItem[] menuItems = {
            new MenuItem(1, "Feijoada Completa", 45.90, TipoItem.PRATO_PRINCIPAL, "feijoada.jpg"),
            new MenuItem(2, "Picanha na Brasa", 59.90, TipoItem.PRATO_PRINCIPAL, "picanha.jpg"),
            new MenuItem(3, "Moqueca de Peixe", 52.90, TipoItem.PRATO_PRINCIPAL, "moqueca.jpg"),
            new MenuItem(4, "Pudim de Leite", 12.90, TipoItem.SOBREMESA, "pudim.jpg"),
            new MenuItem(5, "Mousse de Maracujá", 10.90, TipoItem.SOBREMESA, "mousse.jpg"),
            new MenuItem(6, "Açaí com Frutas", 15.90, TipoItem.SOBREMESA, "acai.jpg"),
            new MenuItem(7, "Caipirinha", 14.90, TipoItem.BEBIDA, "caipirinha.jpg"),
            new MenuItem(8, "Suco Natural", 9.90, TipoItem.BEBIDA, "suco.jpg"),
            new MenuItem(9, "Água Mineral", 5.90, TipoItem.BEBIDA, "agua.jpg")
    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarSpinner(spinnerPrato1);
        inicializarSpinner(spinnerPrato2);
        inicializarSpinner(spinnerPrato3);
        inicializarSpinner(spinnerSobremesa1);
        inicializarSpinner(spinnerSobremesa2);
        inicializarSpinner(spinnerSobremesa3);
        inicializarSpinner(spinnerBebida1);
        inicializarSpinner(spinnerBebida2);
        inicializarSpinner(spinnerBebida3);
        carregarImagens();
    }

    private void carregarImagens() {
        try {
            carregarImagem(imgPrato1, menuItems[0].getImagePath());
            carregarImagem(imgPrato2, menuItems[1].getImagePath());
            carregarImagem(imgPrato3, menuItems[2].getImagePath());
            carregarImagem(imgSobremesa1, menuItems[3].getImagePath());
            carregarImagem(imgSobremesa2, menuItems[4].getImagePath());
            carregarImagem(imgSobremesa3, menuItems[5].getImagePath());
            carregarImagem(imgBebida1, menuItems[6].getImagePath());
            carregarImagem(imgBebida2, menuItems[7].getImagePath());
            carregarImagem(imgBebida3, menuItems[8].getImagePath());
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagens: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void carregarImagem(ImageView imageView, String nomeArquivo) {
        try {
            String caminho = "/images/" + nomeArquivo;
            Image image = new Image(getClass().getResourceAsStream(caminho));
            imageView.setImage(image);
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            imageView.setPreserveRatio(true);
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem " + nomeArquivo + ": " + e.getMessage());
        }
    }

    private void inicializarSpinner(Spinner<Integer> spinner) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        spinner.setValueFactory(valueFactory);
    }

    @FXML
    private void handleVerPedido(ActionEvent event) {
        try {
            itensPedido.clear();
            adicionarItemAoPedido(spinnerPrato1, 0);
            adicionarItemAoPedido(spinnerPrato2, 1);
            adicionarItemAoPedido(spinnerPrato3, 2);
            adicionarItemAoPedido(spinnerSobremesa1, 3);
            adicionarItemAoPedido(spinnerSobremesa2, 4);
            adicionarItemAoPedido(spinnerSobremesa3, 5);
            adicionarItemAoPedido(spinnerBebida1, 6);
            adicionarItemAoPedido(spinnerBebida2, 7);
            adicionarItemAoPedido(spinnerBebida3, 8);
            if (itensPedido.isEmpty()) {
                return;
            }
            Stage stage = (Stage) btnVerPedido.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("resumo-pedido.fxml"));
            Parent resumoPedidoParent = loader.load();
            ResumoPedidoController resumoController = loader.getController();
            resumoController.setItensPedido(itensPedido);
            Scene resumoPedidoScene = new Scene(resumoPedidoParent, 800, 600);
            stage.setScene(resumoPedidoScene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Erro ao carregar a tela de resumo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void adicionarItemAoPedido(Spinner<Integer> spinner, int menuIndex) {
        int quantidade = spinner.getValue();
        if (quantidade > 0) {
            MenuItem item = menuItems[menuIndex];
            itensPedido.add(new ItemPedido(item, quantidade));
        }
    }
}