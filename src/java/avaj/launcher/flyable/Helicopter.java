package java.avaj.launcher.flyable;

import java.avaj.launcher.Coordinates;
import java.avaj.launcher.WeatherTower;

public class Helicopter implements Flyable {

    private WeatherTower weatherTower;

    private Coordinates coordinates;

    private final String name;

    Helicopter(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    @Override
    public void updateConditions() {
        //  TODO
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        //  TODO
    }
}
