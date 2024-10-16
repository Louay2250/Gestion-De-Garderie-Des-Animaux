/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Models.Animal;
import Models.Client;
import Models.Personnel;
import Models.data;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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
import javafx.collections.transformation.FilteredList;

/**
 * FXML Controller class
 *
 * @author Azertyy
 */
public class ClientsController implements Initializable {

    @FXML
    private Button dash;
    @FXML
    private Button btnpers;
    @FXML
    private Button logout;
    @FXML
    private Button ajouterclient;
    @FXML
    private TableView<Client> tab;
     private ObservableList<Client> originalList = FXCollections.observableArrayList();
    private ObservableList<Client> list = FXCollections.observableArrayList(
            new Client(132812,"Louay","Ariana","56948166","Dhkarrr@gmail.Com")
            );
    private FilteredList<Client> filteredList;
    @FXML
    private TableColumn<Client, String> Nom;
    @FXML
    private TableColumn<Client, Integer> CIN;
    @FXML
    private TableColumn<Client, String> Adresse;
    @FXML
    private TableColumn<Client, String> Telephone;
    @FXML
    private TableColumn<Client, String> Email;
    @FXML
    private TableColumn<Client, ArrayList> animals;
    @FXML
    private Button Res;
    @FXML
    private TextField txnom;
    @FXML
    private Button res;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        CIN.setCellValueFactory(new PropertyValueFactory<>("cin"));
        Adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        Telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        animals.setCellValueFactory(new PropertyValueFactory<>("animaux"));
        tab.setItems(list);
        list = FXCollections.observableArrayList();
        originalList.addAll(new Client(13281209,"Louay","ariana","56948166","Dhka@gmail.com"));
        // Wrap the original list in a FilteredList
        filteredList = new FilteredList<>(originalList);

        // Set the TableView items to the filtered list
        tab.setItems(filteredList);
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
    private void GoToRes(ActionEvent event) throws IOException {
        Stage logp = new Stage();
            Res.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/views/Reservation.fxml"));
            Scene scene = new Scene(root);
            logp.setScene(scene);
            logp.show();
            logp.setResizable(false);
    }
     @FXML
    private void supprimerClient(ActionEvent event) {
        Client selectedClient = tab.getSelectionModel().getSelectedItem();

    if (selectedClient != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce Client ?");
        alert.setContentText(selectedClient.getNom());

        ButtonType buttonTypeOui = new ButtonType("Oui", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNon = new ButtonType("Non", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(buttonTypeOui, buttonTypeNon);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeOui) {
            // Remove from the originalList
            originalList.remove(selectedClient);

            // Recreate the filteredList with the updated originalList
            filteredList = new FilteredList<>(originalList);

            // Apply the existing filter predicate to the refreshed filteredList
            String searchNom = txnom.getText().trim();
            Predicate<Client> filterPredicate = client ->
                    client.getNom().toLowerCase().contains(searchNom.toLowerCase());
            filteredList.setPredicate(filterPredicate);

            // Set the TableView items to the refreshed filteredList
            tab.setItems(filteredList);

            // Update your table view accordingly
            tab.refresh();  // This refreshes the TableView to reflect the changes
        }
    } else {
        showAlert("Aucun Personnel sélectionné", "Veuillez sélectionner un Personnel à supprimer.");
    }
    }
    @FXML
    private void handleAjouterButton() {
    // Create a dialog with text input for Nom
    TextInputDialog nomDialog = new TextInputDialog();
    nomDialog.setTitle("Ajouter Client");
    nomDialog.setHeaderText("Ajouter un nouveau Client");
    nomDialog.setContentText("Nom:");

    // Show the dialog and wait for user input
    Optional<String> nomResult = nomDialog.showAndWait();
    if (!nomResult.isPresent()) {
        return; // User canceled the input
    }
    String newNom = nomResult.get();

    // Create a dialog with text input for cin
    TextInputDialog cinDialog = new TextInputDialog();
    cinDialog.setTitle("Ajouter Client");
    cinDialog.setHeaderText("Ajouter un nouveau Client");
    cinDialog.setContentText("CIN:");

    // Show the dialog and wait for user input
    Optional<String> cinResult = cinDialog.showAndWait();
    if (!cinResult.isPresent()) {
        return; // User canceled the input
    }
    int nCIN = Integer.parseInt(cinResult.get());

    // Create a dialog with text input for Groupe Sanguin
    TextInputDialog adresseDialog = new TextInputDialog();
    adresseDialog.setTitle("Ajouter Client");
    adresseDialog.setHeaderText("Ajouter un nouveau Client");
    adresseDialog.setContentText(" Adresse:");

    // Show the dialog and wait for user input
    Optional<String> AdresseResult = adresseDialog.showAndWait();
    if (!AdresseResult.isPresent()) {
        return; // User canceled the input
    }
    String newAdresse = AdresseResult.get();

    // Create a dialog with text input for Email
    TextInputDialog telDialog = new TextInputDialog();
    telDialog.setTitle("Ajouter Client");
    telDialog.setHeaderText("Ajouter un nouveau Client");
    telDialog.setContentText("Telephone:");

    // Show the dialog and wait for user input
    Optional<String> telResult = telDialog.showAndWait();
    if (!telResult.isPresent()) {
        return; // User canceled the input
    }
    String newTel = telResult.get();
    
       // Create a dialog with text input for Groupe Sanguin
    TextInputDialog emailDialog = new TextInputDialog();
    emailDialog.setTitle("Ajouter Client");
    emailDialog.setHeaderText("Ajouter un nouveau Client");
    emailDialog.setContentText(" email:");

    // Show the dialog and wait for user input
    Optional<String> emailResult = emailDialog.showAndWait();
    if (!emailResult.isPresent()) {
        return; // User canceled the input
    }
    String nemail = emailResult.get();
         // Create a dialog with text input for Groupe Sanguin
    TextInputDialog animalDialog = new TextInputDialog();
    emailDialog.setTitle("Ajouter Client");
    emailDialog.setHeaderText("Ajouter un nouveau Client");
    emailDialog.setContentText(" Animal:");

    // Show the dialog and wait for user input
    Optional<String> animalResult = animalDialog.showAndWait();
    if (!animalResult.isPresent()) {
        return; // User canceled the input
    }
    String nanimal = animalResult.get();

    // Create a new Donneur with the provided details
    Client newclt = new Client(nCIN,newNom, newAdresse, newTel,nemail);
// Add the new Client to the originalList
    originalList.add(newclt);
    // Refresh the filteredList to include the new client
    filteredList = new FilteredList<>(originalList);
    String searchNom = txnom.getText().trim();
    Predicate<Client> filterPredicate = client ->
            client.getNom().toLowerCase().contains(searchNom.toLowerCase());
    filteredList.setPredicate(filterPredicate);
    tab.setItems(filteredList);

    // Add the new Donneur to the list
    list.add(newclt);

    // Update your table view accordingly
    tab.refresh();  // This refreshes the TableView to reflect the changes
     txnom.clear();
}
  @FXML
private void handleModifierButton(ActionEvent event) {
    Client selectedClient = tab.getSelectionModel().getSelectedItem();

    if (selectedClient != null) {
        // Create a GridPane to hold the input fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Create TextFields for each attribute
        TextField nomField = new TextField(selectedClient.getNom());
        TextField cinField = new TextField(Integer.toString(selectedClient.getCin()));
        TextField adresseField = new TextField(selectedClient.getAdresse());
        TextField telField = new TextField(selectedClient.getTelephone());
        TextField emailField = new TextField(selectedClient.getEmail());

        // Create a ListView for managing the list of animals
        ListView<Animal> animalListView = new ListView<>();
        animalListView.getItems().addAll(selectedClient.getAnimaux());

        // Add labels and fields to the grid
        grid.add(new Label("Nom:"), 0, 0);
        grid.add(nomField, 1, 0);
        grid.add(new Label("CIN:"), 0, 1);
        grid.add(cinField, 1, 1);
        grid.add(new Label("Adresse:"), 0, 2);
        grid.add(adresseField, 1, 2);
        grid.add(new Label("Telephone:"), 0, 3);
        grid.add(telField, 1, 3);
        grid.add(new Label("Email:"), 0, 4);
        grid.add(emailField, 1, 4);
        grid.add(new Label("Animaux:"), 0, 5);
        grid.add(animalListView, 1, 5);

        // Create a custom dialog
        Dialog<Pair<Boolean, Client>> dialog = new Dialog<>();
        dialog.setTitle("Modifier Profil");
        dialog.setHeaderText("Modifier les détails de Profil");

        // Add OK and Cancel buttons to the dialog
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        // Enable the OK button only if all fields are non-empty
        Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
        okButton.setDisable(true);

        // Use a GridPane layout for the dialog
        dialog.getDialogPane().setContent(grid);

        // Request focus on the nomField by default
        Platform.runLater(() -> nomField.requestFocus());

        // Update the OK button's disable property based on whether the fields are empty
        BooleanBinding fieldsValid = nomField.textProperty().isEmpty()
                .or(cinField.textProperty().isEmpty())
                .or(adresseField.textProperty().isEmpty())
                .or(telField.textProperty().isEmpty())
                .or(emailField.textProperty().isEmpty());

        okButton.disableProperty().bind(fieldsValid);

        // Convert the result to a Pair<Boolean, Client> when the OK button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return new Pair<>(true, new Client(
                        Integer.parseInt(cinField.getText()),
                        nomField.getText(),
                        adresseField.getText(),
                        telField.getText(),
                        emailField.getText()));
            }
            return new Pair<>(false, null);
        });

        // Show the dialog and wait for the user's input
        Optional<Pair<Boolean, Client>> result = dialog.showAndWait();

        // If the user clicked OK, update the Client's details
        result.ifPresent(pair -> {
            if (pair.getKey()) {
                Client updatedClient = pair.getValue();

                // Update the selected Client with the new values
                selectedClient.setCIN(updatedClient.getCin());
                selectedClient.setNom(updatedClient.getNom());
                selectedClient.setAdresse(updatedClient.getAdresse());
                selectedClient.setTelephone(updatedClient.getTelephone());
                selectedClient.setEmail(updatedClient.getEmail());
                selectedClient.getAnimaux().setAll(updatedClient.getAnimaux());

                // Update your table view accordingly
                tab.refresh();  // This refreshes the TableView to reflect the changes
            }
        });
    }
    else {
        showAlert("Aucun Profil sélectionné", "Veuillez sélectionner un Profil pour le modifier.");
    }
}
public void switchToOtherInterface() {
    data clientData = new data();
    clientData.setClients(list); // This sets the list of clients in the ClientData object

    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("clients.dat"))) {
        oos.writeObject(clientData);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Read the list from a file when switching back to the interface
public void switchBackToInterface() {
    data clientData = null;
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("clients.dat"))) {
        clientData = (data) ois.readObject();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    list.setAll(clientData.getClients()); // This sets the list of clients in the ObservableList
}


    private void showAlert(String aucun_Profil_sélectionné, String veuillez_sélectionner_un_Profil_pour_le_m) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @FXML
    private void filtrer(ActionEvent event) {
        String searchNom = txnom.getText().trim();

        // Define a predicate for filtering based on the search criteria
        Predicate<Client> filterPredicate = client ->
                client.getNom().toLowerCase().contains(searchNom.toLowerCase());

        // Apply the filter predicate to the filtered list
        filteredList.setPredicate(filterPredicate);
    }

    @FXML
    private void reset(ActionEvent event) {
        // Clear the filter predicate to show the original unfiltered list
        filteredList.setPredicate(null);

        // Clear the search text in the TextField
        txnom.clear();
    }

}
