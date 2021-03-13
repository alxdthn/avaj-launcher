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
                System.out.printf("%s: It's hot day!\n", formatSelfData());
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 2,
                        coordinates.getLatitude(),
                        coordinates.getHeight() + 4
                );
                break;
            case AvajLauncher.RAIN:
                System.out.printf("%s: It's rainy day!\n", formatSelfData());
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 5
                );
                break;
            case AvajLauncher.FOG:
                System.out.printf("%s: It's foggy day!\n", formatSelfData());
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 3
                );
                break;
            case AvajLauncher.SNOW:
                System.out.printf("%s: It's snowy day!\n", formatSelfData());
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
