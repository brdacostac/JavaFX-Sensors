package view;

import Factory.ImageFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import model.Captor;
import model.CaptorBasic;
import model.CaptorStub;
import model.TreeItemVisitor;


public class FXMLWindow extends BorderPane {
    @FXML
    private TreeView<Captor> captorTreeView;

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
    private void afficher() {
        System.out.println(captorStub.getCaptorsItems());
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
}
