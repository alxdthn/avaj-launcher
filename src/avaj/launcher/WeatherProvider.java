package avaj.launcher;

public class WeatherProvider {

    private static WeatherProvider weatherProvider = null;

    private static final String[] weather = {
            AvajLauncher.RAIN,
            AvajLauncher.FOG,
            AvajLauncher.SUN,
            AvajLauncher.SNOW
    };

    private WeatherProvider() {
    }

    static public WeatherProvider getProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        return weather[coordinates.hashCode() % weather.length];
    }
}
