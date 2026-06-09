package examenFinal.controller;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;
import examenFinal.service.VideoGameService;
import examenFinal.model.DigitalVideoGame;
import examenFinal.model.PhysicalVideoGame;
import javafx.event.ActionEvent;
import examenFinal.model.Sale;
import java.util.List;
import java.util.ArrayList;


public class MainController implements Initializable {

    private VideoGameService service = new VideoGameService();

    private List<Sale> sales = new ArrayList<>();

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtPlatform;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtStock;

    @FXML
    private TextField txtSizeGB;

    @FXML
    private TextField txtSearchPlatform;

    @FXML
    private TextArea txtAreaPlatformResult;

    @FXML
    private TextField txtSearchTitle;

    @FXML
    private TextArea txtAreaTitleResult;

    @FXML
    private RadioButton rbDigital;

    @FXML
    private RadioButton rbPhysical;

    @FXML
    private CheckBox chkUsed;

    @FXML
    private Button btnAdd;

    @FXML
    private TableView<examenFinal.model.VideoGame> tblGames;

    @FXML
    private TableColumn<examenFinal.model.VideoGame, String> colTitle;

    @FXML
    private TableColumn<examenFinal.model.VideoGame, String> colPlatform;

    @FXML
    private TableColumn<examenFinal.model.VideoGame, Double> colPrice;

    @FXML
    private TableColumn<examenFinal.model.VideoGame, Integer> colStock;

    @FXML
    private TableColumn<examenFinal.model.VideoGame, String> colType;

    @FXML
    private TextField txtSaleTitle;

    @FXML
    private TextField txtSaleQuantity;

    @FXML
    private TableView<Sale> tblSales;

    @FXML
    private TableColumn<Sale, String> colSaleTitle;

    @FXML
    private TableColumn<Sale, Integer> colSaleQuantity;

    @FXML
    private TableColumn<Sale, Double> colSaleTotal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colTitle.setCellValueFactory(
                new PropertyValueFactory<>("title"));

        colPlatform.setCellValueFactory(
                new PropertyValueFactory<>("platform"));

        colPrice.setCellValueFactory(
                new PropertyValueFactory<>("price"));

        colStock.setCellValueFactory(
                new PropertyValueFactory<>("stock"));

        colType.setCellValueFactory(
                new PropertyValueFactory<>("type"));

        colSaleTitle.setCellValueFactory(
                new PropertyValueFactory<>("gameTitle"));

        colSaleQuantity.setCellValueFactory(
                new PropertyValueFactory<>("quantity"));

        colSaleTotal.setCellValueFactory(
                new PropertyValueFactory<>("total"));

        tblSales.setItems(FXCollections.observableArrayList(sales));
    }

    private void loadTable() {

        tblGames.setItems(
                FXCollections.observableArrayList(
                        service.getAllGames()
                )
        );
    }

    @FXML
    public void addVideoGame(ActionEvent event) {

        try {

            String title = txtTitle.getText();
            String platform = txtPlatform.getText();

            double price =
                    Double.parseDouble(txtPrice.getText());

            int stock =
                    Integer.parseInt(txtStock.getText());

            if (rbDigital.isSelected()) {

                double sizeGB =
                        Double.parseDouble(txtSizeGB.getText());

                DigitalVideoGame game =
                        new DigitalVideoGame(
                                title,
                                platform,
                                price,
                                stock,
                                sizeGB);

                service.addVideoGame(game);
                loadTable();

            } else if (rbPhysical.isSelected()) {

                boolean used = chkUsed.isSelected();

                PhysicalVideoGame game =
                        new PhysicalVideoGame(
                                title,
                                platform,
                                price,
                                stock,
                                used);

                service.addVideoGame(game);
                loadTable();
            }

            Alert alert =
                    new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Videojuego agregado correctamente");

            alert.showAndWait();

        } catch (Exception e) {

            Alert alert =
                    new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
    }

    @FXML
    public void deleteVideoGame(ActionEvent event) {

        try {

            String title = txtTitle.getText();

            boolean deleted =
                    service.deleteGame(title);

            if (deleted) {

                loadTable();

                Alert alert =
                        new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText(
                        "Videojuego eliminado");

                alert.showAndWait();

            } else {

                throw new RuntimeException(
                        "No existe un videojuego con ese título");
            }

        } catch (Exception e) {

            Alert alert =
                    new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(
                    e.getMessage());

            alert.showAndWait();
        }
    }

    @FXML
    public void updateVideoGame(ActionEvent event) {

        try {

            String title = txtTitle.getText();
            String platform = txtPlatform.getText();

            double price =
                    Double.parseDouble(txtPrice.getText());

            int stock =
                    Integer.parseInt(txtStock.getText());

            boolean updated = false;

            if (rbDigital.isSelected()) {

                double sizeGB =
                        Double.parseDouble(txtSizeGB.getText());

                DigitalVideoGame game =
                        new DigitalVideoGame(
                                title,
                                platform,
                                price,
                                stock,
                                sizeGB);

                updated =
                        service.updateGame(title, game);

            } else if (rbPhysical.isSelected()) {

                boolean used =
                        chkUsed.isSelected();

                PhysicalVideoGame game =
                        new PhysicalVideoGame(
                                title,
                                platform,
                                price,
                                stock,
                                used);

                updated =
                        service.updateGame(title, game);
            }

            if (updated) {

                loadTable();

                Alert alert =
                        new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText(
                        "Videojuego actualizado");

                alert.showAndWait();

            } else {

                throw new RuntimeException(
                        "Videojuego no encontrado");
            }

        } catch (Exception e) {

            Alert alert =
                    new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(
                    e.getMessage());

            alert.showAndWait();
        }
    }

    @FXML
    public void searchByTitle(ActionEvent event) {

        String title = txtSearchTitle.getText();

        var game = service.findByTitle(title);

        if (game != null) {

            txtAreaTitleResult.setText(
                    game.toString()
            );

        } else {

            txtAreaTitleResult.setText(
                    "Videojuego no encontrado"
            );
        }
    }

    @FXML
    public void searchByPlatform(ActionEvent event) {

        String platform =
                txtSearchPlatform.getText();

        var games =
                service.findByPlatform(platform);

        if (games.isEmpty()) {

            txtAreaPlatformResult.setText(
                    "No se encontraron videojuegos"
            );

        } else {

            StringBuilder result =
                    new StringBuilder();

            for (var game : games) {

                result.append(game)
                        .append("\n");
            }

            txtAreaPlatformResult.setText(
                    result.toString()
            );
        }
    }
    @FXML
    public void processSale(ActionEvent event) {

        try {

            String title = txtSaleTitle.getText();
            int quantity = Integer.parseInt(txtSaleQuantity.getText());


            var game = service.findByTitle(title);

            if (game == null) {
                throw new RuntimeException("Videojuego no encontrado");
            }


            if (game.getStock() < quantity) {
                throw new RuntimeException("Stock insuficiente");
            }


            double total = game.getPrice() * quantity;


            game.setStock(game.getStock() - quantity);
            service.updateGame(title, game);


            Sale sale = new Sale(title, quantity, total);

            sales.add(sale);


            tblSales.setItems(FXCollections.observableArrayList(sales));


            loadTable();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("Venta realizada correctamente");
            alert.showAndWait();

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
  }



