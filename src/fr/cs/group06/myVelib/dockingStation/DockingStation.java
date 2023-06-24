/**
 * The DockingStation class represents a docking station for bikes in the MyVelib system.
 * It manages parking slots and bike rental/return operations.
 */
package fr.cs.group06.myVelib.dockingStation;

import java.util.Scanner;
import java.util.ArrayList;

import fr.cs.group06.myVelib.bike.Bike;
import fr.cs.group06.myVelib.bike.BikeFactory;
import fr.cs.group06.myVelib.location.Location;
import fr.cs.group06.myVelib.user.User;

/**
 * The DockingStation class represents a docking station for bikes in the MyVelib system.
 * It manages parking slots and bike rental/return operations.
 */
public class DockingStation {
    private int NumberOfParkingSlots = 0;
    private Scanner terminal;
    private Location StationLocation;
    private int StationId;
    private ArrayList<ParkingSlot> parkingSlots = new ArrayList<>();
    private int numberOfRents = 0;
    private int numberOfReturns = 0;
    private double ratioOccupied;
    private double ratiofree;
    private double ratioElectricalBikes;
    private String StationStatus;
    private String StationType;
    private static int generatorStationId = 0;

    /**
     * Constructs a new DockingStation object with the specified parameters.
     *
     * @param numberOfParkingSlots   the number of parking slots in the docking station
     * @param ratioOccupied          the ratio of occupied slots
     * @param ratiofree              the ratio of free slots
     * @param ratioElectricalBikes   the ratio of electrical bikes among occupied slots
     * @param stationLocation        the location of the docking station
     * @param stationStatus          the status of the docking station (e.g., "Online", "Offline")
     * @param stationType            the type of the docking station (e.g., "Standard", "Plus")
     * @throws Exception if the sum of ratios exceeds 1.0
     */
    public DockingStation(int numberOfParkingSlots, double ratioOccupied, double ratiofree, double ratioElectricalBikes,
            Location stationLocation, String stationStatus, String stationType) throws Exception {
        super();
        this.NumberOfParkingSlots = numberOfParkingSlots;
        this.StationLocation = stationLocation;
        this.StationStatus = stationStatus;
        this.StationType = stationType;
        this.ratioOccupied = ratioOccupied;
        this.ratiofree = ratiofree;
        this.ratioElectricalBikes = ratioElectricalBikes;
        StationId = ++generatorStationId;

        double sumOfRatios = ratioOccupied + ratiofree;
        if (sumOfRatios > 1.0) {
            throw new Exception("Error: The sum of ratios must be less than or equal to 1.");
        }

        // Calculate the number of slots of each type based on ratios
        int NumberOfOccupiedSlots = (int) (NumberOfParkingSlots * ratioOccupied);
        int NumberOfFreeSlots = (int) (NumberOfParkingSlots * ratiofree);
        int NumberOfOutOfOrderSlots = NumberOfParkingSlots - NumberOfOccupiedSlots - NumberOfFreeSlots;
        int NumberOfElectricalBikes = (int) (NumberOfOccupiedSlots * ratioElectricalBikes);
        int NumberOfMechanicalBikes = NumberOfOccupiedSlots - NumberOfElectricalBikes;
        BikeFactory bikeFactory = new BikeFactory();

        // Create parking slots and install bikes
        for (int i = 1; i <= NumberOfElectricalBikes; i++) {
            parkingSlots.add(new ParkingSlot(this, "Occupied",
                    bikeFactory.installingBike("Electrical", StationLocation, this)));
        }
        for (int i = 1; i <= NumberOfMechanicalBikes; i++) {
            parkingSlots.add(new ParkingSlot(this, "Occupied",
                    bikeFactory.installingBike("Mechanical", StationLocation, this)));
        }
        for (int i = 1; i <= NumberOfFreeSlots; i++) {
            parkingSlots.add(new ParkingSlot(this, "Free", null));
        }
        for (int i = 1; i <= NumberOfOutOfOrderSlots; i++) {
            parkingSlots.add(new ParkingSlot(this, "Out-of-order", null));
        }
    }

    /**
     * Displays the current state of the docking station, including information about slots and bikes.
     */
    public void showDockingStationState() {
        System.out.println("=======================================================================");
        System.out.println("The " + this.StationType + " Docking Station information (ID = " + this.getStationId()
                + " ; " + this.getStationStatus() + "):");
        System.out.println(this.getStationLocation().toString());
        for (ParkingSlot slot : parkingSlots) {
            System.out.println("Slot Id = " + slot.getSlotId() + ":");
            System.out.println("    State: " + slot.getSlotStatus());
            if (slot.getSlotStatus().equals("Occupied")) {
                System.out.print("    Bike: ");
                slot.getBike().showBike();
            }
        }

        // Initialize counters
        int electricalBikes = 0;
        int mechanicalBikes = 0;
        int occupiedSlots = 0;
        int freeSlots = 0;
        int outOfOrderSlots = 0;

        // Count bikes and slots
        for (ParkingSlot slot : parkingSlots) {
            if (slot.getSlotStatus().equals("Occupied")) {
                Bike bike = slot.getBike();
                if (bike.getBikeType().equals("Electrical")) {
                    electricalBikes++;
                } else {
                    mechanicalBikes++;
                }
                occupiedSlots++;
            } else if (slot.getSlotStatus().equals("Free")) {
                freeSlots++;
            } else if (slot.getSlotStatus().equals("Out-of-order")) {
                outOfOrderSlots++;
            }
        }

        // Print report
        System.out.println("Report:");
        System.out.println("Number of occupied slots: " + occupiedSlots);
        System.out.println("Number of electrical bikes: " + electricalBikes);
        System.out.println("Number of mechanical bikes: " + mechanicalBikes);
        System.out.println("Number of free slots: " + freeSlots);
        System.out.println("Number of out of order slots: " + outOfOrderSlots);
        System.out.println("=======================================================================");
    }

    /**
     * Rents a bike of the specified type from the docking station for the given user.
     *
     * @param user     the user renting the bike
     * @param bikeType the type of bike to rent (e.g., "Electrical", "Mechanical")
     * @return the rented bike
     * @throws Exception if the docking station is offline or no available bike of the specified type is found
     */
    public Bike rentFromStation(User user, String bikeType) throws Exception {
        if (this.getStationStatus().equalsIgnoreCase("Offline")) {
            throw new Exception("This Station is Offline!");
        }
        
        // Search for an available bike of the specified type in the parking slots
        for (ParkingSlot slot : parkingSlots) {
            if (slot.getSlotStatus().equalsIgnoreCase("Occupied") && slot.getBike().getBikeType().equalsIgnoreCase(bikeType)) {
                // Rent the bike
                Bike bike = slot.getBike();
                bike.rent(user);
                user.setStartStation(this);

                // Update the parking slots
                slot.setSlotStatus("Free");
                slot.setBike(null);
                this.setNumberOfRents(this.getNumberOfRents() + 1);

                return bike;
            }
        }

        // Bike of the specified type not found
        throw new Exception("Sorry, there is no available " + bikeType + " bike at this docking station.");
    }

    /**
     * Accepts the bike returned by the user and assigns it to an available slot in the docking station.
     *
     * @param user the user returning the bike
     * @throws Exception if the docking station is offline or there are no available free slots
     */
    public void returnedToStation(User user) throws Exception {
        if (this.getStationStatus().equalsIgnoreCase("Offline")) {
            throw new Exception("This Station is Offline!");
        }
        for (ParkingSlot slot : parkingSlots) {
            if (slot.getSlotStatus().equalsIgnoreCase("Free")) {
                slot.setSlotStatus("Occupied");
                user.getRentedBike().setDockingStation(this);
                user.getRentedBike().setLocation(this.getStationLocation());
                slot.setBike(user.getRentedBike());
                user.getRentedBike().returnBike(user);
                user.setStartStation(null);
                this.setNumberOfReturns(this.getNumberOfReturns() + 1);
                if (this.getStationType().equals("Plus") && user.getRegistrationCard() != null) {
                    user.getRegistrationCard().addTimeCreditBalance(5);
                }
                return;
            }

        }

        // No free slots
        System.out.println("=======================================================");
        System.out.println("Sorry, there is no available free slots in this station!");
        System.out.println("=======================================================");

    }

    /**
     * Checks if the docking station has a bike of the specified type.
     *
     * @param type the type of bike to check for (e.g., "Electrical", "Mechanical")
     * @return true if the docking station has a bike of the specified type, false otherwise
     */
    public boolean hasBike(String type) {
        for (ParkingSlot slot : parkingSlots) {
            if (slot.getBike() == null) {
                continue;
            }
            if (slot.getBike().getBikeType().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the docking station has a free slot.
     *
     * @return true if the docking station has a free slot, false otherwise
     */
    public boolean hasFreeSlot() {
        for (ParkingSlot slot : parkingSlots) {
            if (slot.getSlotStatus().equalsIgnoreCase("Free")) {
                return true;
            }
        }
        return false;

	}
	
	public Scanner getTerminal() {
		return terminal;
	}

	/**
	 * Get the location of the docking station.
	 *
	 * @return The location of the docking station.
	 */
	public Location getStationLocation() {
	    return StationLocation;
	}

	/**
	 * Get the status of the docking station.
	 *
	 * @return The status of the docking station.
	 */
	public String getStationStatus() {
	    return StationStatus;
	}

	/**
	 * Set the status of the docking station.
	 *
	 * @param status The status to set for the docking station.
	 */
	public void setStationStatus(String status) {
	    this.StationStatus = status;
	}

	/**
	 * Get the type of the docking station.
	 *
	 * @return The type of the docking station.
	 */
	public String getStationType() {
	    return StationType;
	}

	/**
	 * Get the generator station ID.
	 *
	 * @return The generator station ID.
	 */
	public static int getGeneratorStationId() {
	    return generatorStationId;
	}

	/**
	 * Get the ID of the docking station.
	 *
	 * @return The ID of the docking station.
	 */
	public int getStationId() {
	    return StationId;
	}

	/**
	 * Get the list of parking slots in the docking station.
	 *
	 * @return The list of parking slots in the docking station.
	 */
	public ArrayList<ParkingSlot> getParkingSlots() {
	    return parkingSlots;
	}

	/**
	 * Get the ratio of occupied parking slots in the docking station.
	 *
	 * @return The ratio of occupied parking slots.
	 */
	public double getRatioOccupied() {
	    return ratioOccupied;
	}

	/**
	 * Get the ratio of free parking slots in the docking station.
	 *
	 * @return The ratio of free parking slots.
	 */
	public double getRatiofree() {
	    return ratiofree;
	}

	/**
	 * Get the ratio of electrical bikes in the docking station.
	 *
	 * @return The ratio of electrical bikes.
	 */
	public double getRatioElectricalBikes() {
	    return ratioElectricalBikes;
	}

	/**
	 * Set the ratio of occupied parking slots in the docking station.
	 *
	 * @param ratioOccupied The ratio of occupied parking slots to set.
	 */
	public void setRatioOccupied(double ratioOccupied) {
	    this.ratioOccupied = ratioOccupied;
	}

	/**
	 * Set the ratio of free parking slots in the docking station.
	 *
	 * @param ratiofree The ratio of free parking slots to set.
	 */
	public void setRatiofree(double ratiofree) {
	    this.ratiofree = ratiofree;
	}

	/**
	 * Set the ratio of electrical bikes in the docking station.
	 *
	 * @param ratioElectricalBikes The ratio of electrical bikes to set.
	 */
	public void setRatioElectricalBikes(double ratioElectricalBikes) {
	    this.ratioElectricalBikes = ratioElectricalBikes;
	}

	/**
	 * Get the number of bike rents from the docking station.
	 *
	 * @return The number of bike rents.
	 */
	public int getNumberOfRents() {
	    return numberOfRents;
	}
	
	/**
	 * Get the number of parking slots in the docking station.
	 *
	 * @return The number of parking solts.
	 */
	
	public int getNumberOfParkingSlots() {
		return this.NumberOfParkingSlots;
	}

	/**
	 * Get the number of bike returns to the docking station.
	 *
	 * @return The number of bike returns.
	 */
	public int getNumberOfReturns() {
	    return numberOfReturns;
	}

	/**
	 * Set the number of bike rents from the docking station.
	 *
	 * @param numberOfRents The number of bike rents to set.
	 */
	public void setNumberOfRents(int numberOfRents) {
	    this.numberOfRents = numberOfRents;
	}

	/**
	 * Set the number of bike returns to the docking station.
	 *
	 * @param numberOfReturns The number of bike returns to set.
	 */
	public void setNumberOfReturns(int numberOfReturns) {
	    this.numberOfReturns = numberOfReturns;
	}
}