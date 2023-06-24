package fr.cs.group06.myVelib.test;
import fr.cs.group06.myVelib.dockingStation.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import fr.cs.group06.myVelib.bike.Bike;
import fr.cs.group06.myVelib.bike.BikeFactory;
import fr.cs.group06.myVelib.cards.RegistrationCard;
import fr.cs.group06.myVelib.cards.RegistrationCardFactory;
import fr.cs.group06.myVelib.location.Location;
import fr.cs.group06.myVelib.user.User;
import static org.junit.jupiter.api.Assertions.*;

public class TestDockingStation {





    private DockingStation dockingStation;
    private DockingStation dockingStation1;

    private User user;

    @Before
    public void setup() throws Exception {
        // Initialize the docking station and user objects
        int numberOfParkingSlots = 10;
        double ratioOccupied = 0.7;
        double ratioFree = 0.2;
        double ratioElectricalBikes = 0.5;
        int numberOfParkingSlots1 = 10;
        double ratioOccupied1 = 0.7;
        double ratioFree1 = 0.2;
        double ratioElectricalBikes1 = 1;
        
        RegistrationCardFactory rgf = new RegistrationCardFactory();
        RegistrationCard registrationCard = rgf.createRegistrationCard("Vlibre");
        Location stationLocation = new Location(0, 0); // Replace with actual coordinates
        Location userLocation = new Location(15,20);
        String stationStatus = "Online";
        String stationType = "Standard";
        dockingStation = new DockingStation(numberOfParkingSlots, ratioOccupied, ratioFree, ratioElectricalBikes,
                stationLocation, stationStatus, stationType);
        dockingStation1 = new DockingStation(numberOfParkingSlots1, ratioOccupied1, ratioFree1, ratioElectricalBikes1,
                stationLocation, stationStatus, stationType);
        
        
        user = new User("Hatim",userLocation, null, registrationCard);
    }

    @Test
    public void testRentFromStation() throws Exception {
        // Test renting a bike from the docking station
        Bike bike = dockingStation.rentFromStation(user, "Electrical");
        assertNotNull(bike);
        assertTrue(bike.getBikeType().equalsIgnoreCase("Electrical"));

    }
    
    
    @Test(expected = Exception.class)
    public void testRentFromStationNoAvailableBike() throws Exception {
        // Test attempting to rent a bike of a specific type when no bike is available
        dockingStation1.rentFromStation(user, "Mechanical");
    }


	@Test
	public void testReturnedToStation() throws Exception {
	    // Test returning a bike to the docking station
	
		BikeFactory bf = new BikeFactory();
		Location bikeLocation = new Location(15,20);
		Bike bike = bf.installingBike("Electrical", bikeLocation, dockingStation);
	    user.setRentedBike(bike);
	    dockingStation.returnedToStation(user);
	    assertTrue(dockingStation.getParkingSlots().get(0).getSlotStatus().equalsIgnoreCase("Occupied"));
	    assertTrue(bike.getId() == dockingStation.getParkingSlots().get(0).getBike().getId());
	    assertTrue(dockingStation.getStationId() == bike.getDockingStation().getStationId());
	    assertTrue(dockingStation.getStationLocation().equals(bike.getLocation()));
	}
	
	@Test(expected = Exception.class)
	public void testReturnedToStationNoFreeSlots() throws Exception {
	    // Test attempting to return a bike when no free slot is available
		BikeFactory bf = new BikeFactory();
		Location bikeLocation = new Location(15,20);
		Bike bike = bf.installingBike("Electrical", bikeLocation, dockingStation);
	    user.setRentedBike(bike);
	
	    // Set docking station offline
	    dockingStation.setStationStatus("offline");
	
	    dockingStation.returnedToStation(user);
	}
	
	@Test
	public void testHasBike() {
	    // Test checking if the docking station has a bike of a specific type
	    boolean hasBike = dockingStation.hasBike("Electrical");
	    Assert.assertTrue(hasBike);
	}
	
	@Test
	public void testHasFreeSlot() {
	    // Test checking if the docking station has a free slot
	    boolean hasFreeSlot = dockingStation.hasFreeSlot();
	    assertTrue(hasFreeSlot);
	}
}