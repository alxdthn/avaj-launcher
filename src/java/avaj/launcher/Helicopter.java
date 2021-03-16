package avaj.launcher;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        switch (weatherTower.getWeather(coordinates)) {
            case AvajLauncher.SUN:
                AvajLauncher.trackOutput("%s: It's hot day!", formatSelfData());
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 10,
                        coordinates.getLatitude(),
                        coordinates.getHeight() + 2
                );
                break;
            case AvajLauncher.RAIN:
                AvajLauncher.trackOutput("%s: It's rainy day!", formatSelfData());
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 5,
                        coordinates.getLatitude(),
                        coordinates.getHeight()
                );
                break;
            case AvajLauncher.FOG:
                AvajLauncher.trackOutput("%s: It's foggy day!", formatSelfData());
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 1,
                        coordinates.getLatitude(),
                        coordinates.getHeight()
                );
                break;
            case AvajLauncher.SNOW:
                AvajLauncher.trackOutput("%s: It's snowy day!", formatSelfData());
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 12
                );
                break;
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
    }

    @Override
    public String formatSelfData() {
        return String.format("Helicopter#%s(%d)", name, id);
    }
}
