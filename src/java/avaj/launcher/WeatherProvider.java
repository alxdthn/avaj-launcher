package java.avaj.launcher;

public class WeatherProvider {

    private static WeatherProvider weatherProvider = null;

    private static final String[] weather = { "RAIN", "FOG", "SUN", "SNOW" };

    private WeatherProvider() {
    }

    static public WeatherProvider getProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        return "String";
    }
}
