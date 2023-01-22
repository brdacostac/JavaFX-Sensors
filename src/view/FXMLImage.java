package view;

import Factory.ImageFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Captor;
import model.Observer;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;


public class FXMLImage implements Observer {
    private Captor captor;

    @FXML
    private Label captorTemperature;
    @FXML
    private ImageView imageTemp;

    public FXMLImage(Captor captor){
        this.captor=captor;
        captor.addObserver(this);
    }

    @FXML
    private void initialize() {
        update();
    }

    private void updateLabelValue(){
        Platform.runLater(() -> {
            captorTemperature.setText(String.format("%.2f", captor.getTemperature()));
        });
    }

    private static NavigableMap<Double, String> imageMap = new TreeMap<>(){{
        put(-Double.MAX_VALUE, "/images/coldday.jpg");
        put(0.0, "/images/normalday.jpeg");
        put(25.0, "/images/hotday.jpg");
    }};
    @Override
    public void update() {
        this.updateLabelValue();

        Map.Entry<Double, String> entry = imageMap.floorEntry(this.captor.getTemperature());
        imageTemp.setImage(new Image(entry.getValue()));
    }
}
