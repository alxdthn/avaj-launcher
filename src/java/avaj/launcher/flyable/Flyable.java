package java.avaj.launcher.flyable;

import java.avaj.launcher.WeatherTower;

public interface Flyable {

    void updateConditions();

    void registerTower(WeatherTower weatherTower);
}
