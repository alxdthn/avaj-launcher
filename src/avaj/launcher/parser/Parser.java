package avaj.launcher.parser;

import avaj.launcher.AircraftFactory;
import avaj.launcher.Flyable;
import avaj.launcher.WeatherTower;
import avaj.launcher.exceptions.ArgumentException;
import avaj.launcher.exceptions.BadFileException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.PatternSyntaxException;

public class Parser {

    private int lineCounter = 1;

    private int simulations = 0;

    private String file;

    private WeatherTower weatherTower;

    public Parser(String file, WeatherTower weatherTower) {
        this.file = file;
        this.weatherTower = weatherTower;
    }

    public int parse() throws ArgumentException, BadFileException {
        lineCounter = 1;
        simulations = 0;
        collectFileData();
        return simulations;
    }

    private void collectFileData() throws BadFileException {
        BufferedReader reader;
        String line;

        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new BadFileException(file);
        }

        line = readLine(reader);
        simulations = parseInteger(line, line);

        while (line != null) {
            try {
                line = reader.readLine();
                if (line == null) break;
                Flyable flyable = parseLine(line);
                flyable.registerTower(weatherTower);
            } catch (IOException e) {
                throw new BadFileException(lineCounter, line);
            }
        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Flyable parseLine(String line) throws BadFileException {
        try {
            String[] splitResult = line.split("\\s+");
            if (splitResult.length != 5) {
                throw new BadFileException(lineCounter, line);
            }

            String type = splitResult[0];
            if (type.equals("8a7259026c561ed7ec52256a4666c354")) {
                type = "JetPlane";
            } else if (type.equals("9aca0d8a2dac0e6641a181fd9405da3e")) {
                type = "Helicopter";
            } else if (type.equals("10f93ad4617a2ca73cc42763cfa73cbf")) {
                type = "Baloon";
            } else if (!type.matches("(JetPlane|Helicopter|Baloon)")) {
                throw new BadFileException(lineCounter, line);
            }

            String name = splitResult[1];

            int longitude = parseInteger(splitResult[2], line);
            int latitude = parseInteger(splitResult[3], line);
            int height = parseInteger(splitResult[4], line);

            return AircraftFactory.newAircraft(
                    type,
                    name,
                    longitude,
                    latitude,
                    height
            );
        } catch (PatternSyntaxException e) {
            throw new BadFileException(lineCounter, line);
        }
    }

    private String readLine(BufferedReader reader) {
        try {
            String line = reader.readLine();
            lineCounter++;
            return line;
        } catch (IOException e) {
            throw new BadFileException(file);
        }
    }

    private int parseInteger(String src, String line) throws BadFileException {
        int result;
        try {
            result = Integer.parseInt(src);
            if (result < 0) {
                throw new BadFileException(lineCounter, line);
            }
            return result;
        } catch (NumberFormatException e) {
            throw new BadFileException(lineCounter, line);
        }
    }
}
