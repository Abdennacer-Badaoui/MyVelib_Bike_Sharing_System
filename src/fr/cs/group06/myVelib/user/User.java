package fr.cs.group06.myVelib.user;

import fr.cs.group06.myVelib.bike.Bike;
import fr.cs.group06.myVelib.cards.CreditCard;
import fr.cs.group06.myVelib.cards.RegistrationCard;
import fr.cs.group06.myVelib.location.Location;
import fr.cs.group06.myVelib.dockingStation.*;

/**
 * This class represents a user of the system.
 */
public class User {
    protected String userName;
    protected static int id = 0;
    protected int unique_id;
    protected Location userLocation;
    protected CreditCard creditCard;
    protected RegistrationCard registrationCard;
    protected double balanceOfTotalCharges = 0;
    protected boolean hasRentedBike;
    protected Bike rentedBike;
    protected int NumberOfRides = 0;
    protected double totalTimeSpentOnRides = 0.0;
    protected DockingStation startStation = null;

    /**
     * Constructs a User object with the specified parameters.
     *
     * @param name              The user's name.
     * @param userLocation      The user's location.
     * @param creditCard        The user's credit card.
     * @param registrationCard  The user's registration card.
     */
    public User(String name, Location userLocation, CreditCard creditCard, RegistrationCard registrationCard) {
        User.id++;
        this.unique_id = id;
        this.userName = name;
        this.userLocation = userLocation;
        this.registrationCard = registrationCard;
        this.creditCard = creditCard;
        this.hasRentedBike = false;
        this.rentedBike = null;
    }
    /**
     * Checks if the user has rented a bike.
     *
     * @return True if the user has rented a bike, false otherwise.
     */
    public boolean hasRentedBike() {
        return hasRentedBike;
    }

    /**
     * Sets the flag indicating whether the user has rented a bike.
     *
     * @param bo The flag value to set.
     */
    public void sethasRentedBike(boolean bo) {
        this.hasRentedBike = bo;
    }

    /**
     * Sets the bike that the user has rented.
     *
     * @param bike The bike object to set.
     */
    public void setRentedBike(Bike bike) {
        rentedBike = bike;
        hasRentedBike = true;
    }

    /**
     * Retrieves the bike that the user has rented.
     *
     * @return The rented bike object.
     */
    public Bike getRentedBike() {
        return rentedBike;
    }

    /**
     * Retrieves the unique ID of the user.
     *
     * @return The unique ID.
     */
    public int getID() {
        return unique_id;
    }

    /**
     * Retrieves the name of the user.
     *
     * @return The user's name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the name of the user.
     *
     * @param userName The user's name to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Retrieves the location of the user.
     *
     * @return The user's location.
     */
    public Location getUserLocation() {
        return userLocation;
    }

    /**
     * Sets the location of the user.
     *
     * @param userLocation The user's location to set.
     */
    public void setUserLocation(Location userLocation) {
        this.userLocation = userLocation;
    }

    /**
     * Retrieves the credit card associated with the user.
     *
     * @return The user's credit card.
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * Sets the credit card associated with the user.
     *
     * @param creditCard The credit card to set.
     */
    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    /**
     * Retrieves the registration card associated with the user.
     *
     * @return The user's registration card.
     */
    public RegistrationCard getRegistrationCard() {
        return registrationCard;
    }

    /**
     * Sets the registration card associated with the user.
     *
     * @param registrationCard The registration card to set.
     */
    public void setRegistrationCard(RegistrationCard registrationCard) {
        this.registrationCard = registrationCard;
    }

    /**
     * Retrieves the balance of total charges for the user.
     *
     * @return The balance of total charges.
     */
    public double getBalanceOfTotalCharges() {
        return balanceOfTotalCharges;
    }

    /**
     * Sets the balance of total charges for the user.
     *
     * @param balanceOfTotalCharges The balance of total charges to set.
     */
    public void setBalanceOfTotalCharges(double balanceOfTotalCharges) {
        this.balanceOfTotalCharges = balanceOfTotalCharges;
    }

    /**
     * Retrieves the number of rides taken by the user.
     *
     * @return The number of rides.
     */
    public int getNumberOfRides() {
        return NumberOfRides;
    }

    /**
     * Sets the number of rides taken by the user.
     *
     * @param numberOfRides The number of rides to set.
     */
    public void setNumberOfRides(int numberOfRides) {
        NumberOfRides = numberOfRides;
    }

    /**
     * Retrieves the total time spent on rides by the user.
     *
     * @return The total time spent on rides.
     */
    public double getTotalTimeSpentOnRides() {
        return totalTimeSpentOnRides;
    }

    /**
     * Sets the total time spent on rides by the user.
     *
     * @param totalTimeSpentOnRides The total time spent on rides to set.
     */
    public void setTotalTimeSpentOnRides(double totalTimeSpentOnRides) {
        this.totalTimeSpentOnRides = totalTimeSpentOnRides;
    }

    /**
     * Retrieves the starting docking station for the user's ride.
     *
     * @return The starting docking station.
     */
    public DockingStation getStartStation() {
        return this.startStation;
    }

    /**
     * Sets the starting docking station for the user's ride.
     *
     * @param station The starting docking station to set.
     */
    public void setStartStation(DockingStation station) {
        this.startStation = station;
    }
}

 
