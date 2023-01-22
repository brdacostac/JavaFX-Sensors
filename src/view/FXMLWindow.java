package view;

import Factory.ImageFactory;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import model.Captor;
import model.CaptorBasic;
import model.CaptorStub;
import model.TreeItemVisitor;


public class FXMLWindow extends BorderPane {
    @FXML
    private TreeView<Captor> captorTreeView;

    @FXML
    private Label temperature;

    @FXML
    private TextField name;

    @FXML
    private Button addCaptorButton;

    private CaptorStub captorStub;


    @FXML
    private void addCaptor() {
        addCaptorButton.setOnAction(event -> {
            TreeItem<Captor> newCaptor = new TreeItem<Captor>(new CaptorBasic(new SimpleStringProperty("New Captor")), new ImageFactory().imagePath("/images/captor.png") );
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
                temperature.textProperty().unbindBidirectional(oldValue.getValue().temperatureProperty());
            }
            if(newValue!=null){
                name.textProperty().bindBidirectional(newValue.getValue().nameProperty());
                temperature.textProperty().bind(newValue.getValue().temperatureProperty().asString());
            }
        });

    }
}
