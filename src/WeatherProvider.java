public  WeatherProvider {

    private static WeatherProvider weatherProvider;

    private static String[] weather = { "RAIN", "FOG", "SUN", "SNOW" };

    private WeatherProvider() {
        weatherProvider = WeatherProvider();
    }

    public WeatherProvider getProvider() {

    }

    public String getCurrentWeather(Coordinates coordinates) {

    }
}
