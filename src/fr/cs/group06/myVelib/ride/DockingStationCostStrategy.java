package fr.cs.group06.myVelib.ride;

/**
 * The DockingStationCostStrategy class implements the RideStrategy interface
 * and represents the cost calculation strategy for rides starting and ending at a docking station.
 */
public class DockingStationCostStrategy implements RideStrategy {
    /**
     * Calculates the cost of a ride based on the ride duration, bike type, card type, and whether the bike is returned to a docking station.
     *
     * @param rideDuration    The duration of the ride in minutes.
     * @param bikeType        The type of the bike ("Mechanical" or "Electrical").
     * @param cardType        The type of the card ("none", "Vlibre", "Vmax", etc.).
     * @param returnedToDocking A boolean indicating whether the bike is returned to a docking station.
     * @return The calculated cost of the ride.
     */
    @Override
    public double calculateCost(int rideDuration, String bikeType, String cardType, boolean returnedToDocking) {
        if (cardType.equals("none")) {
            return calculateCostWithoutCard(rideDuration, bikeType);
        } else if (cardType.equals("Vlibre")) {
            return calculateCostWithVlibreCard(rideDuration, bikeType);
        } else if (cardType.equals("Vmax")) {
            return calculateCostWithVmaxCard(rideDuration);
        }
        // Handle other card types if needed

        return 0.0;
    }

    /**
     * Calculates the cost of a ride without a card.
     *
     * @param rideDuration The duration of the ride in minutes.
     * @param bikeType     The type of the bike ("Mechanical" or "Electrical").
     * @return The calculated cost of the ride.
     */
    private double calculateCostWithoutCard(int rideDuration, String bikeType) {
        double hourlyRate = 0.0;

        if (bikeType.equalsIgnoreCase("Mechanical")) {
            hourlyRate = 1.0;
        } else if (bikeType.equalsIgnoreCase("electrical")) {
            hourlyRate = 2.0;
        }

        return rideDuration / 60.0 * hourlyRate;
    }

    /**
     * Calculates the cost of a ride with a Vlibre card.
     *
     * @param rideDuration The duration of the ride in minutes.
     * @param bikeType     The type of the bike ("Mechanical" or "Electrical").
     * @return The calculated cost of the ride.
     */
    private double calculateCostWithVlibreCard(int rideDuration, String bikeType) {
        double hourlyRate = 0.0;

        if (bikeType.equalsIgnoreCase("mechanical")) {
            hourlyRate = 1.0;
        } else if (bikeType.equalsIgnoreCase("electrical")) {
            hourlyRate = 2.0;
        }

        // Check if the ride duration exceeds the free hour
        if (rideDuration > 60) {
            int timeCredit = 20;
            int excessDuration = rideDuration - 60;

            if (excessDuration <= timeCredit) {
                return hourlyRate + (excessDuration / 60.0 * hourlyRate);
            } else {
                return (excessDuration - timeCredit) / 60.0 * hourlyRate;
            }
        }

        return hourlyRate;
    }

    /**
     * Calculates the cost of a ride with a Vmax card.
     *
     * @param rideDuration The duration of the ride in minutes.
     * @return The calculated cost of the ride.
     */
    private double calculateCostWithVmaxCard(int rideDuration) {
        double hourlyRate = 1.0; // Fixed rate for Vmax card

        // Check if the ride duration exceeds the free hour
        if (rideDuration > 60) {
            return (rideDuration - 60) / 60.0 * hourlyRate;
        }

        return 0.0;
    }
}
