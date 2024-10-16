/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Models.Animal;
import Models.Client;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author Azertyy
 */
public class AnimauxController implements Initializable {

    @FXML
    private Button dash;
    @FXML
    private Button btnpers;
    @FXML
    private Button logout;
    @FXML
    private TableView<Animal> table;
    @FXML
    private TableColumn<Animal, String> nom;
    @FXML
    private TableColumn<Animal, Date> dateNaissance;
    @FXML
    private TableColumn<Animal, String> type;
    @FXML
    private TableColumn<Animal, Client> proprietaire;
    private ObservableList<Animal> animalList = FXCollections.observableArrayList(
            new Animal(new Client(123, "John Doe", "Address", "123456789", "john@example.com"), "Chien", new Date(), "Mammifère")
            // Ajoutez d'autres animaux si nécessaire
    );


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // Associer les colonnes aux propriétés de la classe Animal
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        dateNaissance.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        proprietaire.setCellValueFactory(new PropertyValueFactory<>("proprietaire"));

        // Remplir le TableView avec la liste initiale
        table.setItems(animalList);
    }    

    @FXML
    private void ClickDash(ActionEvent event) throws IOException {
        Stage logp = new Stage();
            dash.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/views/FXMLDocument_1.fxml"));
            Scene scene = new Scene(root);
            logp.setScene(scene);
            logp.show();
            logp.setResizable(false);
    }

    @FXML
    private void Clickpers(ActionEvent event) throws IOException {
        Stage logp = new Stage();
            btnpers.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/views/Personnel_1.fxml"));
            Scene scene = new Scene(root);
            logp.setScene(scene);
            logp.show();
            logp.setResizable(false);
    }


    @FXML
    private void Clicklogout(ActionEvent event) throws IOException {
        Stage logp = new Stage();
            logout.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
            Scene scene = new Scene(root);
            logp.setScene(scene);
            logp.show();
            logp.setResizable(false);
    }
    @FXML
    private void ajouterAnimal(ActionEvent event) {
        Dialog<Pair<Boolean, Animal>> dialog = new Dialog<>();
        dialog.setTitle("Ajouter Animal");
        dialog.setHeaderText("Ajouter un nouvel Animal");

        ButtonType buttonTypeAjouter = new ButtonType("Ajouter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypeAjouter, ButtonType.CANCEL);

        TextField nomField = new TextField();
        DatePicker dateNaissancePicker = new DatePicker();
        TextField typeField = new TextField();

        dialog.getDialogPane().setContent(createGridPane(nomField, dateNaissancePicker, typeField));

        Node ajouterButton = dialog.getDialogPane().lookupButton(buttonTypeAjouter);
        ajouterButton.setDisable(true);

        nomField.textProperty().addListener((observable, oldValue, newValue) -> {
            ajouterButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == buttonTypeAjouter) {
                return new Pair<>(true, new Animal(null, nomField.getText(), java.sql.Date.valueOf(dateNaissancePicker.getValue()), typeField.getText()));
            }
            return new Pair<>(false, null);
        });

        Optional<Pair<Boolean, Animal>> result = dialog.showAndWait();

        result.ifPresent(pair -> {
            if (pair.getKey()) {
                Animal newAnimal = pair.getValue();
                animalList.add(newAnimal);
            }
        });
    }

    @FXML
    private void supprimerAnimal(ActionEvent event) {
        Animal selectedAnimal = table.getSelectionModel().getSelectedItem();

        if (selectedAnimal != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Voulez-vous vraiment supprimer cet animal ?");
            alert.setContentText(selectedAnimal.getNom());

            ButtonType buttonTypeOui = new ButtonType("Oui", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNon = new ButtonType("Non", ButtonBar.ButtonData.NO);

            alert.getButtonTypes().setAll(buttonTypeOui, buttonTypeNon);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == buttonTypeOui) {
                animalList.remove(selectedAnimal);
            }
        } else {
            showAlert("Aucun Animal sélectionné", "Veuillez sélectionner un Animal à supprimer.");
        }
    }
    @FXML
    private void updateAnimal(ActionEvent event) {
        Animal selectedAnimal = table.getSelectionModel().getSelectedItem();

        if (selectedAnimal != null) {
            Dialog<Pair<Boolean, Animal>> dialog = new Dialog<>();
            dialog.setTitle("Modifier Animal");
            dialog.setHeaderText("Modifier les détails de l'Animal");

            ButtonType buttonTypeModifier = new ButtonType("Modifier", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(buttonTypeModifier, ButtonType.CANCEL);

            TextField nomField = new TextField(selectedAnimal.getNom());
            DatePicker dateNaissancePicker = new DatePicker(selectedAnimal.getDateNaissance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            TextField typeField = new TextField(selectedAnimal.getType());

            dialog.getDialogPane().setContent(createGridPane(nomField, dateNaissancePicker, typeField));

            Node modifierButton = dialog.getDialogPane().lookupButton(buttonTypeModifier);
            modifierButton.setDisable(true);

            nomField.textProperty().addListener((observable, oldValue, newValue) -> {
                modifierButton.setDisable(newValue.trim().isEmpty());
            });

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == buttonTypeModifier) {
                    return new Pair<>(true, new Animal(
                            selectedAnimal.getProprietaire(),
                            nomField.getText(),
                            java.sql.Date.valueOf(dateNaissancePicker.getValue()),
                            typeField.getText()));
                }
                return new Pair<>(false, null);
            });

            Optional<Pair<Boolean, Animal>> result = dialog.showAndWait();

            result.ifPresent(pair -> {
                if (pair.getKey()) {
                    Animal updatedAnimal = pair.getValue();

                    // Update selectedAnimal with the new values
                    selectedAnimal.setNom(updatedAnimal.getNom());
                    selectedAnimal.setDateNaissance(updatedAnimal.getDateNaissance());
                    selectedAnimal.setType(updatedAnimal.getType());

                    // Update your table view accordingly
                    table.refresh();  // This refreshes the TableView to reflect the changes
                }
            });
        } else {
            showAlert("Aucun Animal sélectionné", "Veuillez sélectionner un Animal à mettre à jour.");
        }
    }
    private GridPane createGridPane(TextField nomField, DatePicker dateNaissancePicker, TextField typeField) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Nom:"), 0, 0);
        grid.add(nomField, 1, 0);
        grid.add(new Label("Date de Naissance:"), 0, 1);
        grid.add(dateNaissancePicker, 1, 1);
        grid.add(new Label("Type:"), 0, 2);
        grid.add(typeField, 1, 2);

        return grid;
    }
    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    
}
