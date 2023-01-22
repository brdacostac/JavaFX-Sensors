package model;

import java.util.Random;

public class ComportementBornee implements Comportement{
    private static final double MIN_TEMP = -5;
    private static final double MAX_TEMP = 5;

    @Override
    public double genererTemperature() {
        Random rand = new Random();
        return MIN_TEMP + (MAX_TEMP - MIN_TEMP) * rand.nextDouble();
    }

    @Override
    public String getName(){
        return "Comportement Bornee";
    }
}
