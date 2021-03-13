package avaj.launcher;

import avaj.launcher.exceptions.BadFileException;
import avaj.launcher.parser.Parser;

public class AvajLauncher {

    public static int MAX_HEIGHT = 100;

    public final static String RAIN = "RAIN";
    public final static String FOG = "FOG";
    public final static String SUN = "SUN";
    public final static String SNOW = "SNOW";

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Only one file argument expected");
        } else {
            start(args[0]);
        }
    }

    private static void start(String file) {
        WeatherTower weatherTower = new WeatherTower();

        Parser parser = new Parser(file, weatherTower);
        try {
            int simulationNumber = parser.parse();
            for (int i = 0; i < simulationNumber; i++) {
                weatherTower.changeWeather();
            }
        } catch (BadFileException e) {
            e.printStackTrace();
        }
    }
}
