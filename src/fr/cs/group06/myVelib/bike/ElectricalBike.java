/**
 * The ElectricalBike class represents an electrical bike in the MyVelib system.
 */
package fr.cs.group06.myVelib.bike;

import fr.cs.group06.myVelib.dockingStation.DockingStation;
import fr.cs.group06.myVelib.location.Location;
import fr.cs.group06.myVelib.user.User;

/**
 * The ElectricalBike class represents an electrical bike in the MyVelib system.
 */
public class ElectricalBike implements Bike {
    
    private int BikeId;
    private Location location;
    private DockingStation dockingStation;
    
    /**
     * Constructs a new ElectricalBike object with the specified parameters.
     *
     * @param id                the ID of the bike
     * @param location          the location of the bike
     * @param dockingStation    the docking station where the bike is located
     */
    public ElectricalBike(int id, Location location, DockingStation dockingStation) {
        this.BikeId = id;
        this.location = location;
        this.dockingStation = dockingStation;
    }

    /**
     * Allows a user to rent the electrical bike.
     *
     * @param user the user who wants to rent the bike
     */
    @Override
    public void rent(User user) {
        if (user.hasRentedBike()) {
            System.out.println("Sorry, " + user.getUserName() + "! You already have a bike rented. Please return the current bike before renting another one.");
        } else {
            System.out.println("++++++++++++++++++++++++\nHello, " + user.getUserName() + "!\nYou're renting an electrical bike:\nBike ID: " + BikeId + "\nDocking Station: " + dockingStation.getStationId() + "\nThank you for using MyVelib.\n++++++++++++++++++++++++");
            // Perform any additional operations or updates related to the renting process
            user.setRentedBike(this); // Set the rented bike for the user
            
            // Update the ratios of the docking station
            double totalOccupiedSlots = dockingStation.getNumberOfParkingSlots() * dockingStation.getRatioOccupied();
            double totalFreeSlots = dockingStation.getNumberOfParkingSlots() * dockingStation.getRatiofree();
            double totalElectricalBikes = totalOccupiedSlots * dockingStation.getRatioElectricalBikes();
            
            double updatedOccupiedSlots = totalOccupiedSlots - 1;
            double updatedFreeSlots = totalFreeSlots + 1;
            double updatedElectricalBikes = totalElectricalBikes - 1;
            
            double updatedRatioOccupied = updatedOccupiedSlots / dockingStation.getNumberOfParkingSlots();
            double updatedRatioFree = updatedFreeSlots / dockingStation.getNumberOfParkingSlots();
            double updatedRatioElectricalBikes = updatedElectricalBikes / updatedOccupiedSlots;
            
            dockingStation.setRatioOccupied(updatedRatioOccupied);
            dockingStation.setRatiofree(updatedRatioFree);
            dockingStation.setRatioElectricalBikes(updatedRatioElectricalBikes);
            
        }
    }
    
    /**
     * Allows a user to return the electrical bike.
     *
     * @param user the user who wants to return the bike
     */
    public void returnBike(User user) {
        // Perform any operations related to returning the bike
        user.setRentedBike(null);
        user.sethasRentedBike(false);
        System.out.println("==========================================================");
        System.out.println("    Bike Returned ! Thank you "+user.getUserName()+" for using MyVelib ;)");
        System.out.println("==========================================================");
        
        // Update the ratios of the docking station
        double totalOccupiedSlots = dockingStation.getNumberOfParkingSlots() * dockingStation.getRatioOccupied();
        double totalFreeSlots = dockingStation.getNumberOfParkingSlots() * dockingStation.getRatiofree();
        double totalElectricalBikes = totalOccupiedSlots * dockingStation.getRatioElectricalBikes();
        
        double updatedOccupiedSlots = totalOccupiedSlots + 1;
        double updatedFreeSlots = totalFreeSlots - 1;
        double updatedElectricalBikes = totalElectricalBikes - 1;
        
        double updatedRatioOccupied = updatedOccupiedSlots / dockingStation.getNumberOfParkingSlots();
        double updatedRatioFree = updatedFreeSlots / dockingStation.getNumberOfParkingSlots();
        double updatedRatioElectricalBikes = updatedElectricalBikes / updatedOccupiedSlots;
        
        dockingStation.setRatioOccupied(updatedRatioOccupied);
        dockingStation.setRatiofree(updatedRatioFree);
        dockingStation.setRatioElectricalBikes(updatedRatioElectricalBikes);
    }
    
    /**
     * Returns the type of the bike.
     *
     * @return the bike type as a string ("Electrical")
     */
    @Override
    public String getBikeType() {
        return "Electrical";
    }
    
    /**
     * Displays information about the electrical bike.
     */
    @Override
    public void showBike() {
        System.out.println("Electrical Bike of Id = "+this.getId());
    }

    /**
     * Gets the ID of the electrical bike.
     *
     * @return the bike ID
     */
    @Override
    public int getId() {
        return BikeId;
    }

    /**
     * Sets the ID of the electrical bike.
     *
     * @param id the bike ID to set
     */
    @Override
    public void setId(int id) {
        this.BikeId = id;
    }

    /**
     * Gets the location of the electrical bike.
     *
     * @return the bike location
     */
    @Override
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location of the electrical bike.
     *
     * @param location the bike location to set
     */
    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Gets the docking station where the electrical bike is located.
     *
     * @return the docking station
     */
    @Override
    public DockingStation getDockingStation() {
        return dockingStation;
    }

    /**
     * Sets the docking station where the electrical bike is located.
     *
     * @param dockingStation the docking station to set
     */
    @Override
    public void setDockingStation(DockingStation dockingStation) {
        this.dockingStation = dockingStation;
    }
}
