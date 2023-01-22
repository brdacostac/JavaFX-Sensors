package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaptorArea extends Captor{

    private Map<Captor, Double> captors = new HashMap<>();

    public CaptorArea(StringProperty name, Map<Captor, Double>  captors) {
        super((SimpleStringProperty) name);
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
