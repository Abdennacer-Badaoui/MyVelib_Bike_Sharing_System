package fr.cs.group06.myVelib.ride;

import fr.cs.group06.myVelib.bike.Bike;
import fr.cs.group06.myVelib.dockingStation.*;
import fr.cs.group06.myVelib.location.Location;
import fr.cs.group06.myVelib.user.User;

/**
 * The Ride class represents a ride taken by a user with a bike.
 */
public class Ride {
    protected User user;
    protected Bike bike;
    protected DockingStation dsStart;
    protected DockingStation dsEnd;
    protected Location locStart;
    protected Location locEnd;
    protected double time;
    protected RideStrategy costStrategy;
    protected double cost;

    /**
     * Constructs a Ride object with the specified user, bike, starting and ending docking stations,
     * starting and ending locations, and ride duration.
     *
     * @param user     The user who took the ride.
     * @param bike     The bike used for the ride.
     * @param dsStart  The starting docking station (can be null if the bike is rented from a free position).
     * @param dsEnd    The ending docking station (can be null if the bike is left parked in the road).
     * @param locStart The starting location.
     * @param locEnd   The ending location.
     * @param time     The duration of the ride in minutes.
     */
    public Ride(User user, Bike bike, DockingStation dsStart, DockingStation dsEnd, Location locStart,
                Location locEnd, double time) {
        this.user = user;
        this.bike = bike;
        this.dsStart = dsStart;
        this.dsEnd = dsEnd;
        this.locStart = locStart;
        this.locEnd = locEnd;
        this.time = time;
        this.costStrategy = determineCostStrategy(dsStart, dsEnd);
        this.user.setTotalTimeSpentOnRides(this.getTime() + this.user.getTotalTimeSpentOnRides());
        user.setNumberOfRides(user.getNumberOfRides() + 1);
    }

    /**
     * Determines the cost calculation strategy based on the starting and ending docking stations.
     *
     * @param dsStart The starting docking station.
     * @param dsEnd   The ending docking station.
     * @return The appropriate RideStrategy based on the docking station conditions.
     */
    private RideStrategy determineCostStrategy(DockingStation dsStart, DockingStation dsEnd) {
        if (dsStart != null && dsEnd != null) {
            // Bike rented and returned to a docking station
            return new DockingStationCostStrategy();
        } else if (dsStart == null && dsEnd != null) {
            // Bike rented from a free position and returned to a docking station
            return new FreePositionCostStrategy(new DockingStationCostStrategy());
        } else {
            // Bike not returned to a docking station (left parked in the road)
            return new RoadParkingCostStrategy(new DockingStationCostStrategy());
        }
    }

    /**
     * Calculates the cost of the ride based on the cost strategy, user's card type, and other ride parameters.
     *
     * @return The calculated cost of the ride.
     */
    public double calculateCost() {
        String cardType = "none";
        if (user.getRegistrationCard() != null) {
            cardType = user.getRegistrationCard().getType();
        }
        boolean returnedToDocking = dsEnd != null; // Check if the bike is returned to a docking station

        if (user.getRegistrationCard() != null) {
            if (this.time > user.getRegistrationCard().getTimeCreditBalance()) {
                cost = costStrategy.calculateCost((int) (this.time - user.getRegistrationCard().getTimeCreditBalance()), bike.getBikeType(), cardType, returnedToDocking);
                user.getRegistrationCard().setTimeCreditBalance(0.0);
            } else {
                cost = costStrategy.calculateCost(0, bike.getBikeType(), cardType, returnedToDocking);
                user.getRegistrationCard().setTimeCreditBalance(user.getRegistrationCard().getTimeCreditBalance() - this.time);
            }
        } else {
            cost = costStrategy.calculateCost((int) this.time, bike.getBikeType(), cardType, returnedToDocking);
        }

        this.user.setBalanceOfTotalCharges(cost + this.user.getBalanceOfTotalCharges());
        return cost;
    }

    // Getters for various ride attributes

    /**
     * Returns the user associated with this ride.
     * 
     * @return The user object.
     */
    public User getUser() {
        return user;
    }

    /**
     * Returns the bike used in this ride.
     * 
     * @return The bike object.
     */
    public Bike getBike() {
        return bike;
    }

    /**
     * Returns the starting docking station of this ride.
     * 
     * @return The starting docking station object.
     */
    public DockingStation getDsStart() {
        return dsStart;
    }

    /**
     * Returns the ending docking station of this ride.
     * 
     * @return The ending docking station object.
     */
    public DockingStation getDsEnd() {
        return dsEnd;
    }

    /**
     * Returns the starting location of this ride.
     * 
     * @return The starting location object.
     */
    public Location getLocStart() {
        return locStart;
    }

    /**
     * Returns the ending location of this ride.
     * 
     * @return The ending location object.
     */
    public Location getLocEnd() {
        return locEnd;
    }

    /**
     * Returns the duration of this ride in minutes.
     * 
     * @return The ride duration in minutes.
     */
    public double getTime() {
        return time;
    }

    /**
     * Returns the cost of this ride.
     * 
     * @return The ride cost.
     */
    public double getCost() {
        return cost;
    }

}
