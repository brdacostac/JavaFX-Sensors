package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

public abstract class Captor extends Observable {

    private UUID id;
    private SimpleStringProperty name;

    private double temperature;



    protected Comportement comportement;

    public Captor(SimpleStringProperty name, Comportement comportement) {
        this.name=name;
        this.comportement=comportement;
        try {
            this.genererTemperature();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void genererTemperature() throws FileNotFoundException {
        temperature = this.comportement.genererTemperature();
        notifyObservers();
    }

    public abstract void addChild(Captor captor, double poids) throws Exception;

    public abstract List<Captor> getChildren() throws Exception;

    public abstract TreeItem<Captor> accept(TreeItemVisitor visitor);



    @Override
    public String toString() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public double getTemperature() {
        return temperature;
    }
}
