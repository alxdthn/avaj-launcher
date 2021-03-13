package avaj.launcher;

public class Coordinates {

    private final int longitude;

    private final int latitude;

    private final int height;

    Coordinates(
            int longitude,
            int latitude,
            int height) {

        this.longitude = longitude;
        this.latitude = latitude;
        this.height = Math.min(height, AvajLauncher.MAX_HEIGHT);
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + longitude;
        result = 31 * result + latitude;
        result = 31 * result + height;
        return result;
    }
}
