package java.avaj.launcher.flyable;

import java.avaj.launcher.Coordinates;
import java.avaj.launcher.WeatherTower;

public class jetPlane implements Flyable {

    private WeatherTower weatherTower;

    private Coordinates coordinates;

    private final String name;

    jetPlane(String name, Coordinates coordinates) {
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
