package avaj.launcher.parser;

import avaj.launcher.AircraftFactory;
import avaj.launcher.Flyable;
import avaj.launcher.WeatherTower;
import avaj.launcher.exceptions.BadFileException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.PatternSyntaxException;

public class Parser {

    private int lineCounter = 1;

    private int simulations = 0;

    private final String file;

    private final WeatherTower weatherTower;

    private MD5Decryptor decryptor;

    public Parser(String file, WeatherTower weatherTower) {
        this.file = file;
        this.weatherTower = weatherTower;
    }

    public int parse() throws NoSuchAlgorithmException {
        lineCounter = 1;
        simulations = 0;
        decryptor = MD5Decryptor.getInstance();
        collectFileData();
        return simulations;
    }

    private void collectFileData() throws BadFileException {
        BufferedReader reader;
        String line = null;

        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new BadFileException(file);
        }

        try {
            line = readLineInternal(reader);
            simulations = parseInteger(line, line);
            while (line != null) {
                line = readLineInternal(reader);
                if (line == null) break;
                Flyable flyable = parseLine(line);
                flyable.registerTower(weatherTower);
            }
        } catch (IOException | PatternSyntaxException e) {
            throw new BadFileException(lineCounter, line);
        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Flyable parseLine(String line) {
        String[] splitResult = line.split("\\s+");
        if (splitResult.length != 5) {
            throw new BadFileException(lineCounter, line);
        }

        String type = splitResult[0];
        if (!type.matches("(JetPlane|Helicopter|Baloon)")) {
            if (type.length() != 32) throw new BadFileException(lineCounter, line);
            type = decryptor.decryptType(type);
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
    }

    private String readLineInternal(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        lineCounter++;
        return line;
    }

    private int parseInteger(String src, String line) {
        int result;
        try {
            result = Integer.parseInt(src);
            if (result < 0) throw new BadFileException(lineCounter, line);
            return result;
        } catch (NumberFormatException e) {
            if (src.length() != 32) throw new BadFileException(lineCounter, line);
            return decryptor.decryptInteger(src);
        }
    }
}
