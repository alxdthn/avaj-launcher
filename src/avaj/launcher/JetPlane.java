package avaj.launcher;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        switch (weatherTower.getWeather(coordinates)) {
            case AvajLauncher.SUN:
                AvajLauncher.trackOutput("%s: It's hot day!", formatSelfData());
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 10,
                        coordinates.getHeight() + 2
                );
                break;
            case AvajLauncher.RAIN:
                AvajLauncher.trackOutput("%s: It's rainy day!", formatSelfData());
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 5,
                        coordinates.getHeight()
                );
                break;
            case AvajLauncher.FOG:
                AvajLauncher.trackOutput("%s: It's foggy day!", formatSelfData());
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 1,
                        coordinates.getHeight()
                );
                break;
            case AvajLauncher.SNOW:
                AvajLauncher.trackOutput("%s: It's snowy day!", formatSelfData());
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 7
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
        return String.format("JetPlane#%s(%d)", name, id);
    }
}
