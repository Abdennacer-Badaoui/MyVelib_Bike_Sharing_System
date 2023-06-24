package fr.cs.group06.myVelib.test;
import fr.cs.group06.myVelib.dockingStation.*;
import org.junit.Before;
import org.junit.Test;
import fr.cs.group06.myVelib.bike.Bike;
import fr.cs.group06.myVelib.bike.BikeFactory;
import fr.cs.group06.myVelib.location.Location;

import static org.junit.Assert.assertTrue;

public class TestParkingSlot {

	

	    private ParkingSlot parkingSlot;
	    private DockingStation dockingStation;

	    @Before
	    public void setup() {
	        // Initialize the parking slot, docking station, and bike objects
	        int numberOfParkingSlots = 10;
	        double ratioOccupied = 0.7;
	        double ratioFree = 0.2;
	        double ratioElectricalBikes = 0.5;

	        Location stationLocation = new Location(0, 0); // Replace with actual coordinates
	        String stationStatus = "Online";
	        String stationType = "Standard";
	        try {
				dockingStation = new DockingStation(numberOfParkingSlots, ratioOccupied, ratioFree, ratioElectricalBikes,
				        stationLocation, stationStatus, stationType);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        BikeFactory bf = new BikeFactory();
			Location bikeLocation = new Location(15,20);
			Bike bike = bf.installingBike("Electrical", bikeLocation, dockingStation);

	        parkingSlot = new ParkingSlot(dockingStation, "Free", bike);
	    }

	    @Test
	    public void testGetStation() {
	        // Test getting the docking station associated with the parking slot
	        DockingStation station = parkingSlot.getStation();
	        assertTrue(dockingStation.getStationId() == station.getStationId());
	    }

	    @Test
	    public void testGetSlotStatus() {
	        // Test getting the slot status of the parking slot
	        String slotStatus = parkingSlot.getSlotStatus();
	        assertTrue(slotStatus.equalsIgnoreCase("Free"));
	    }



	    @Test
	    public void testSetSlotStatus() {
	        // Test setting the slot status of the parking slot
	        parkingSlot.setSlotStatus("Occupied");
	        String slotStatus = parkingSlot.getSlotStatus();
	        assertTrue(slotStatus.equalsIgnoreCase("Occupied"));
	    }

	    @Test
	    public void testSetBike() {
	        // Test setting the bike associated with the parking slot
	    	BikeFactory bf = new BikeFactory();
			Location bikeLocation = new Location(15,20);
			Bike newBike = bf.installingBike("Electrical", bikeLocation, dockingStation);
	        parkingSlot.setBike(newBike);
	        Bike associatedBike = parkingSlot.getBike();
	        assertTrue(newBike.getId() == associatedBike.getId());
	    }
	}

	


