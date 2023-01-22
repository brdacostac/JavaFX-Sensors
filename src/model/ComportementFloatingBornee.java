package model;
import java.util.Random;

public class ComportementFloatingBornee implements Comportement {
    private static final double MIN_TEMP = -273.0;
    private static final double MAX_TEMP = 100.0;

    @Override
    public double genererTemperature() {
        Random rand = new Random();
        return MIN_TEMP + (MAX_TEMP - MIN_TEMP) * rand.nextDouble();
    }

    @Override
    public String getName(){
        return "Comportement Floating Bornee";
    }
}
