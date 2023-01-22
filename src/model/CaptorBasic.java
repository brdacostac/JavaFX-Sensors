package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CaptorBasic extends Captor{
    public CaptorBasic(SimpleStringProperty name) {
        super(name);
    }

    @Override
    public void addChild(Captor captor, double poids) throws Exception {
        throw new Exception("Un capteur basic ne peut pas avoir des enfants");
    }

    @Override
    public List<Captor> getChildren() throws Exception {
        throw new Exception("Un capteur basic ne peut pas avoir des enfants");
    }

    @Override
    public TreeItem<Captor> accept(TreeItemVisitor visitorCaptor){
        return visitorCaptor.visit(this);
    }
}
