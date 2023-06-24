package fr.cs.group06.myVelib.test;
import fr.cs.group06.myVelib.location.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class TestLocation {

    private Location location1;
    private Location location2;

    @Before
    public void setup() {
        // Initialize the location objects
        location1 = new Location(1.0, 3.0);
        location2 = new Location(3.0, 4.0);
    }

    @Test
    public void testGetX() {
        // Test getting the x-coordinate of the location
        double x = location1.getX();
        assertTrue(x == 1.0);
    }

    @Test
    public void testGetY() {
        // Test getting the y-coordinate of the location
        double y = location1.getY();
        assertTrue(y == 3.0);
    }

    @Test
    public void testCalculateDistance() {
        // Test calculating the distance between two locations
        double distance = location1.calculateDistance(location2);
        double mydistance = Math.sqrt((location1.getX()-location2.getX()) * (location1.getX()-location2.getX())+ (location1.getY()-location2.getY()) * (location1.getY()-location2.getY()));
        assertTrue(distance == mydistance);
    }

    @Test
    public void testEquals() {
        // Test checking equality between two locations
        Location sameLocation = new Location(1.0, 3.0);
        Location differentLocation = new Location(1.0, 2.0);

        boolean sameEquals = location1.equals(sameLocation);
        boolean differentEquals = location1.equals(differentLocation);

        assertTrue(sameEquals);
        assertFalse(differentEquals);
    }


}
