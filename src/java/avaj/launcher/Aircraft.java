package avaj.launcher;

public abstract class Aircraft {

    protected long id;

    protected String name;

    protected Coordinates coordinates;

    private static long idCounter;

    protected Aircraft(String name, Coordinates coordinates) {
        this.id = nextId();
        this.name = name;
        this.coordinates = coordinates;
    }

    private long nextId() {
        return ++idCounter;
    }

    protected void land(WeatherTower weatherTower, Flyable flyable) {
        AvajLauncher.trackOutput(
                "%s landing in lat %d; lng %d",
                formatSelfData(),
                coordinates.getLatitude(),
                coordinates.getLongitude()
        );
        weatherTower.unregister(flyable);
    }

    public abstract String formatSelfData();
}
