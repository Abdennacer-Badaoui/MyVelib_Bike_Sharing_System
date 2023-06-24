package fr.cs.group06.myVelib.ride;

/**
 * The FreePositionCostStrategy class implements the RideStrategy interface
 * and represents the cost calculation strategy for rides starting at a free position.
 */
public class FreePositionCostStrategy implements RideStrategy {
    private final RideStrategy dockingStationStrategy;

    /**
     * Constructs a FreePositionCostStrategy object with the specified docking station strategy.
     *
     * @param dockingStationStrategy The strategy used for calculating the cost of rides starting and ending at a docking station.
     */
    public FreePositionCostStrategy(RideStrategy dockingStationStrategy) {
        this.dockingStationStrategy = dockingStationStrategy;
    }

    /**
     * Calculates the cost of a ride starting at a free position.
     * The cost is calculated based on the docking station strategy and a 10% discount is applied.
     *
     * @param rideDuration    The duration of the ride in minutes.
     * @param bikeType        The type of the bike ("Mechanical" or "Electrical").
     * @param cardType        The type of the card ("none", "Vlibre", "Vmax", etc.).
     * @param returnedToDocking A boolean indicating whether the bike is returned to a docking station.
     * @return The calculated cost of the ride.
     */
    @Override
    public double calculateCost(int rideDuration, String bikeType, String cardType, boolean returnedToDocking) {
        double cost = dockingStationStrategy.calculateCost(rideDuration, bikeType, cardType, true);
        return cost * 0.9; // Apply 10% discount
    }
}
