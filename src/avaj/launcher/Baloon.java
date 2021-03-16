package avaj.launcher;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        switch (weatherTower.getWeather(coordinates)) {
            case AvajLauncher.SUN:
                AvajLauncher.trackOutput("%s: It's hot day!", formatSelfData());
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 2,
                        coordinates.getLatitude(),
                        coordinates.getHeight() + 4
                );
                break;
            case AvajLauncher.RAIN:
                AvajLauncher.trackOutput("%s: It's rainy day!", formatSelfData());
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 5
                );
                break;
            case AvajLauncher.FOG:
                AvajLauncher.trackOutput("%s: It's foggy day!", formatSelfData());
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 3
                );
                break;
            case AvajLauncher.SNOW:
                AvajLauncher.trackOutput("%s: It's snowy day!", formatSelfData());
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 15
                );
                break;
        }
        if (coordinates.getHeight() <= 0) {
            land(weatherTower, this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
    }

    @Override
    public String formatSelfData() {
        return String.format("Baloon#%s(%d)", name, id);
    }
}
