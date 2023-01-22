package view;

import Factory.ImageFactory;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class FXMLWindow extends BorderPane{
    @FXML
    private TreeView<Captor> captorTreeView;

    @FXML
    private Label id;

    @FXML
    private TextField name;

    @FXML
    private Button addCaptorButton;

    @FXML
    private Button deleteCaptorButton;

    private CaptorStub captorStub;


    @FXML
    private void addCaptor() {
        addCaptorButton.setOnAction(event -> {
            TreeItem<Captor> newCaptor = new TreeItem<Captor>(new CaptorBasic(new SimpleStringProperty("New Captor"), new ComportementFloatingBornee()), new ImageFactory().imagePath("/images/captor.png") );
            try {
                newCaptor.getValue().genererTemperature();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            captorTreeView.getRoot().getChildren().add(newCaptor);

            captorTreeView.refresh();
        });
    }

    @FXML
    private void deleteCaptor() {
        deleteCaptorButton.setOnAction(event -> {
            TreeItem<Captor> selectCaptor = captorTreeView.getSelectionModel().getSelectedItem();
            captorTreeView.getRoot().getChildren().remove(selectCaptor);
            captorTreeView.refresh();
        });
    }

    @FXML
    private void initialize() {
        captorStub = new CaptorStub();
        captorTreeView.setShowRoot(false);
        TreeItemVisitor visitor = new TreeItemVisitor();
        captorTreeView.setRoot(new TreeItem<Captor>());

        for (Captor captor : captorStub.getCaptorsItems()) {
            captorTreeView.getRoot().getChildren().add(captor.accept(visitor));
        }

        captorTreeView.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> {
            if(oldValue !=null ){
                name.textProperty().unbindBidirectional(oldValue.getValue().nameProperty());
            }
            if(newValue!=null){
                name.textProperty().bindBidirectional(newValue.getValue().nameProperty());
            }
        });

    }


    @FXML
    public void windowImage(){
        if(captorTreeView.getSelectionModel().getSelectedItem()!=null) {
            Captor selectedCaptor = captorTreeView.getSelectionModel().getSelectedItem().getValue();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FXMLImage.fxml"));
            loader.setController(new FXMLImage(selectedCaptor));

            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
    }

}
