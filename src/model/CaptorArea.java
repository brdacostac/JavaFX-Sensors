package model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TreeItem;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaptorArea extends Captor{

    private Map<Captor, Double> captors = new HashMap<>();

    public CaptorArea(SimpleStringProperty name,Comportement comportement, Map<Captor, Double>  captors) {
        super(name, comportement);
        this.captors=captors;
    }

    @Override
    public void addChild(Captor captor, double poids) throws Exception{
        captors.put(captor,poids);
    }

    @Override
    public List<Captor> getChildren() throws Exception {
        List<Captor> captor = new ArrayList<>();
        for (Map.Entry<Captor, Double> entry : captors.entrySet()) {
            captor.add(entry.getKey());
        }
        return captor;
    }

    @Override
    public TreeItem<Captor> accept(TreeItemVisitor visitorCaptor){
        return visitorCaptor.visit(this);
    }


}
