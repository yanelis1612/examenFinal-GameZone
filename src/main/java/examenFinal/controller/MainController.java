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

public class MainController implements Initializable {

    private VideoGameService service = new VideoGameService();
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
    }


