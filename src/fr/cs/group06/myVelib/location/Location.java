package fr.cs.group06.myVelib.location;

/**
 * The Location class represents a location with coordinates (x, y).
 */
public class Location {
    private double x;
    private double y;

    /**
     * Constructs a Location object with the specified x and y coordinates.
     *
     * @param x The x-coordinate of the location.
     * @param y The y-coordinate of the location.
     */
    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of the location.
     *
     * @return The x-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the location.
     *
     * @return The y-coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Calculates the Euclidean distance between this location and another location.
     *
     * @param otherLocation The other location to calculate the distance to.
     * @return The distance between the two locations.
     */
    public double calculateDistance(Location otherLocation) {
        double dx = otherLocation.getX() - x;
        double dy = otherLocation.getY() - y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Checks if this location is equal to another location.
     *
     * @param loc The other location to compare with.
     * @return true if the locations are equal, false otherwise.
     */
    public boolean equals(Location loc) {
        if (this.getX() == loc.getX() && this.getY() == loc.getY()) {
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of the location.
     *
     * @return A string representation of the location in the format "Location: (x, y)".
     */
    @Override
    public String toString() {
        return "Location: (" + x + ", " + y + ")";
    }
}
