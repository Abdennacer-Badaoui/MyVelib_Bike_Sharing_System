package fr.cs.group06.myVelib.ride;

/**
 * This class represents a cost strategy for rides where the bike is parked on the road
 * instead of being returned to a docking station. It applies a 10% malus to the cost
 * calculated by the underlying docking station cost strategy.
 */
class RoadParkingCostStrategy implements RideStrategy {
    private final RideStrategy dockingStationStrategy;

    /**
     * Constructs a RoadParkingCostStrategy with the specified docking station strategy.
     *
     * @param dockingStationStrategy The underlying docking station cost strategy to use.
     */
    public RoadParkingCostStrategy(RideStrategy dockingStationStrategy) {
        this.dockingStationStrategy = dockingStationStrategy;
    }

    @Override
    public double calculateCost(int rideDuration, String bikeType, String cardType, boolean returnedToDocking) {
        // Calculate the cost using the docking station strategy
        double cost = dockingStationStrategy.calculateCost(rideDuration, bikeType, cardType, true);
        // Apply a 10% malus to the cost
        return cost * 0.9;
    }
}
