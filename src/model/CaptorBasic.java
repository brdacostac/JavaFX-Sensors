package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TreeItem;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CaptorBasic extends Captor{

    private Thread thread;

    public CaptorBasic(SimpleStringProperty name, Comportement comportement) {
        super(name, comportement);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(1500);
                        CaptorBasic.super.genererTemperature();
                    } catch (InterruptedException | FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread.start();
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
