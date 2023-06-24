package fr.cs.group06.myVelib.test;

import fr.cs.group06.myVelib.ride.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RideTest {
    private RideStrategy dockingStationStrategy;
    private RideStrategy freePositionStrategy;

    @BeforeEach
    public void setup() {
        dockingStationStrategy = new DockingStationCostStrategy();
        freePositionStrategy = new FreePositionCostStrategy(dockingStationStrategy);
    }

    @Test
    public void testCalculateCostWithoutCard() {
        int rideDuration = 90; // 1.5 hours
        String bikeType = "Mechanical";
        String cardType = "none";
        boolean returnedToDocking = true;

        double expectedCost = 1.5; // hourly rate for Mechanical bikes without a card

        double actualCost = dockingStationStrategy.calculateCost(rideDuration, bikeType, cardType, returnedToDocking);

        assertEquals(expectedCost, actualCost);
    }

    @Test
    public void testCalculateCostWithVmaxCard() {
        int rideDuration = 90; // 1.5 hours
        String bikeType = "Mechanical";
        String cardType = "Vmax";
        boolean returnedToDocking = true;

        double expectedCost = 0.5; // hourly rate for Mechanical bikes after 1 free hour with Vmax card

        double actualCost = dockingStationStrategy.calculateCost(rideDuration, bikeType, cardType, returnedToDocking);

        assertEquals(expectedCost, actualCost);
    }

    @Test
    public void testCalculateCostWithFreePositionAndDiscount() {
        int rideDuration = 60; // 1 hour
        String bikeType = "Electrical";
        String cardType = "Vlibre";
        boolean returnedToDocking = false;

        double expectedCost = 1.8; // 10% discount on the hourly rate for Electrical bikes

        double actualCost = freePositionStrategy.calculateCost(rideDuration, bikeType, cardType, returnedToDocking);

        assertEquals(expectedCost, actualCost);
    }
}

