package avaj.launcher;

public class AircraftFactory {

    public static Flyable newAircraft(
            String type,
            String name,
            int longitude,
            int latitude,
            int height
    ) {
        Flyable flyable = null;
        Coordinates coordinates = new Coordinates(
                longitude,
                latitude,
                height
        );
        switch (type) {
            case "JetPlane":
                flyable = new JetPlane(name, coordinates);
                break;
            case "Helicopter":
                flyable =  new Helicopter(name, coordinates);
                break;
            case "Baloon":
                flyable =  new Baloon(name, coordinates);
                break;
        }
        return flyable;
    }
}
