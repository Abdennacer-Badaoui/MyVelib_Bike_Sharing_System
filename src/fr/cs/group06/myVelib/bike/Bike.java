/**
 * The Bike interface represents a generic bike in the myVelib system.
 * It defines methods for renting and returning a bike, retrieving bike information, and managing bike location and docking station association.
 */
package fr.cs.group06.myVelib.bike;

import fr.cs.group06.myVelib.dockingStation.DockingStation;
import fr.cs.group06.myVelib.location.Location;
import fr.cs.group06.myVelib.user.User;

/**
 * The Bike interface represents a generic bike in the myVelib system.
 */
public interface Bike {
    
    /**
     * Rents the bike to the specified user.
     *
     * @param user the user who wants to rent the bike
     */
    void rent(User user);
    
    /**
     * Returns the bike from the user.
     *
     * @param user the user who wants to return the bike
     */
    void returnBike(User user);
    
    /**
     * Retrieves the type of the bike.
     *
     * @return the type of the bike as a String
     */
    String getBikeType();
    
    /**
     * Retrieves the ID of the bike.
     *
     * @return the ID of the bike as an integer
     */
    int getId();
    
    /**
     * Sets the ID of the bike.
     *
     * @param id the ID of the bike
     */
    void setId(int id);
    
    /**
     * Retrieves the location of the bike.
     *
     * @return the location of the bike as a Location object
     */
    Location getLocation();
    
    /**
     * Sets the location of the bike.
     *
     * @param location the location of the bike
     */
    void setLocation(Location location);
    
    /**
     * Retrieves the docking station where the bike is currently docked.
     *
     * @return the docking station where the bike is currently docked
     */
    DockingStation getDockingStation();
    
    /**
     * Sets the docking station where the bike is currently docked.
     *
     * @param dockingStation the docking station where the bike is currently docked
     */
    void setDockingStation(DockingStation dockingStation);
    
    /**
     * Displays the information of the bike.
     */
    void showBike();
}
