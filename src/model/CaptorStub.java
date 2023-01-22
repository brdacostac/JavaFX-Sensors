package model;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CaptorStub {
    private List<Captor> captors;
    private ObservableList<Captor> captorsItems;


    public CaptorStub() {
        captors = new ArrayList<>();

        Captor captor1 = new CaptorArea(new SimpleStringProperty("CaptorBornnée"),new ComportementBornee(),new HashMap<>());
        Captor captor2 = new CaptorBasic(new SimpleStringProperty("CaptorCPU"), new ComportementCPU());
        try {
            captor1.addChild(captor2,10.0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        captors.add(captor1);
        captors.add(new CaptorBasic(new SimpleStringProperty("CaptorFloatingBornée"), new ComportementFloatingBornee()));
        captors.add(new CaptorBasic(new SimpleStringProperty("CaptorCPU2"), new ComportementCPU()));

        captorsItems = FXCollections.observableArrayList();
        for (Captor captor : captors) {
            captorsItems.add(captor);
        }
    }

    public ObservableList<Captor> getCaptorsItems() {
        return captorsItems;
    }
}