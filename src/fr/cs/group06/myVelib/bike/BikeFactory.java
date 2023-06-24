/**
 * The BikeFactory class is responsible for creating instances of Bike objects.
 */
package fr.cs.group06.myVelib.bike;

import fr.cs.group06.myVelib.dockingStation.DockingStation;
import fr.cs.group06.myVelib.location.Location;

/**
 * The BikeFactory class is responsible for creating instances of Bike objects.
 */
public class BikeFactory {
    
    private int bikeIdCounter = 0;
    
    /**
     * Creates and installs a new bike based on the specified parameters.
     *
     * @param bikeType         the type of the bike ("MECHANICAL" or "ELECTRICAL")
     * @param location         the location of the bike
     * @param dockingStation   the docking station where the bike will be installed
     * @return the created Bike object, or null if the bikeType is invalid
     */
    public Bike installingBike(String bikeType, Location location, DockingStation dockingStation) {
        if (bikeType == null) {
            return null;
        }
        
        Bike bike;
        
        if (bikeType.equalsIgnoreCase("MECHANICAL")) {
            bike = new MechanicalBike(++bikeIdCounter, location, dockingStation);
        } else if (bikeType.equalsIgnoreCase("ELECTRICAL")) {
            bike = new ElectricalBike(++bikeIdCounter, location, dockingStation);
        } else {
            return null;
        }
        
        return bike;
    }
}
