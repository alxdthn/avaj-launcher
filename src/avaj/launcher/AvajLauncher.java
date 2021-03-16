package avaj.launcher;

import avaj.launcher.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AvajLauncher {

    @SuppressWarnings("FieldCanBeLocal")
    private static boolean DEBUG = true;

    public static int MAX_HEIGHT = 100;

    public final static String RAIN = "RAIN";
    public final static String FOG = "FOG";
    public final static String SUN = "SUN";
    public final static String SNOW = "SNOW";

    private final static String OUTPUT_FILE = "out.txt";

    private final static List<String> outputResult = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Only one file argument expected");
            return;
        }
        try {
            start(args[0]);
            printOutput();
        } catch (Exception e) {
            if (DEBUG) {
                e.printStackTrace();
            } else {
                System.out.printf("ERROR: %s\n", e.getMessage());
            }
        }
    }

    private static void start(String file) {
        WeatherTower weatherTower = new WeatherTower();
        Parser parser = new Parser(file, weatherTower);
        int simulationNumber = parser.parse();
        for (int i = 0; i < simulationNumber; i++) {
            weatherTower.changeWeather();
        }
    }

    static void trackOutput(String format, Object... args) {
        outputResult.add(String.format(format, args));
    }

    private static void printOutput() throws FileNotFoundException {
        File outputFile = new File(OUTPUT_FILE);
        PrintWriter writer = new PrintWriter(outputFile);
        writer.write(String.join("\n", outputResult));
        writer.close();
    }
}
