package view;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FXMLAddCaptor {

    @FXML
    private TextField name;

    @FXML
    private Button buttonChangeStage;

    Comportement comportementSelected;

    private List<Captor> captors;

    private List<Comportement> comportements = Arrays.asList(new ComportementBornee(), new ComportementCPU(), new ComportementFloatingBornee());

    @FXML
    private FlowPane checkBoxPane;

    public FXMLAddCaptor(List<Captor> captors) throws IOException {
        this.captors=captors;
        FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/fxml/FXMLAddCaptor.fxml"));
        leLoader.setController(this);
        leLoader.setRoot(this);
        leLoader.load();
    }

    public List<Captor> getCaptors() {
        return captors;
    }


    @FXML
    private void initialize() {

        for (Comportement comportement : comportements) {
            CheckBox checkBox = new CheckBox(comportement.getName());
            checkBoxPane.getChildren().add(checkBox);
            if (checkBox.isSelected()){
                comportementSelected = comportement;
            }
        }

    }

    @FXML
    private void home() {
        try {
            Stage stage = (Stage) buttonChangeStage.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLWindow.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
