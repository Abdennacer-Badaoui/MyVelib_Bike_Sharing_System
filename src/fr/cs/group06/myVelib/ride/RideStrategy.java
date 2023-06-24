package fr.cs.group06.myVelib.ride;

/**
 * This interface represents a strategy for calculating the cost of a ride.
 * Classes implementing this interface should provide an implementation of the
 * calculateCost method.
 */
public interface RideStrategy {
	
    /**
     * Calculates the cost of a ride based on the given parameters.
     *
     * @param rideDuration    The duration of the ride in minutes.
     * @param bikeType        The type of bike used in the ride.
     * @param cardType        The type of card used by the user.
     * @param returnedToDocking    A flag indicating whether the bike was returned to a docking station.
     * @return The cost of the ride.
     */
    double calculateCost(int rideDuration, String bikeType, String cardType, boolean returnedToDocking);
}

