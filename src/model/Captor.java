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

public abstract class Captor {

    private UUID id;
    private SimpleStringProperty name;

    private DoubleProperty temperature = new SimpleDoubleProperty();

    private Comportement comportement;

    public Captor(SimpleStringProperty name) {
        this.name=name;
    }

    public void setComportement(Comportement comportement) {

        this.comportement = comportement;
    }

    public double genererTemperature() throws FileNotFoundException {
        return this.comportement.genererTemperature();
    }

    public abstract void addChild(Captor captor, double poids) throws Exception;

    public abstract List<Captor> getChildren() throws Exception;

    public abstract TreeItem<Captor> accept(TreeItemVisitor visitor);

    @Override
    public String toString() {
        return name.get();
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }


}
