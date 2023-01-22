package model;

import javafx.beans.property.SimpleDoubleProperty;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ComportementCPU implements Comportement{
    @Override
    public double genererTemperature() throws FileNotFoundException {
        return 15.5;
    }
    @Override
    public String getName(){
        return "Comportement CPU";
    }
}
