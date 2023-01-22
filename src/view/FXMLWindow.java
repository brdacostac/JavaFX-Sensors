package view;

import Factory.ImageFactory;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;


public class FXMLWindow extends BorderPane{
    @FXML
    private TreeView<Captor> captorTreeView;

    @FXML
    private Label id;

    @FXML
    private TextField name;

    @FXML
    private Button addCaptorButton;

    private CaptorStub captorStub;


    @FXML
    private void addCaptor() {
        addCaptorButton.setOnAction(event -> {
            TreeItem<Captor> newCaptor = new TreeItem<Captor>(new CaptorBasic(new SimpleStringProperty("New Captor"), new ComportementCPU()), new ImageFactory().imagePath("/images/captor.png") );
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
