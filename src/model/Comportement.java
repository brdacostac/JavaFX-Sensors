package model;

import javafx.beans.property.SimpleDoubleProperty;

import java.io.FileNotFoundException;

public interface Comportement {
    public double genererTemperature() throws FileNotFoundException;

    public String getName();
}
