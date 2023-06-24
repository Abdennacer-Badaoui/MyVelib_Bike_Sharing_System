package fr.cs.group06.myVelib.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.cs.group06.myVelib.bike.Bike;
import fr.cs.group06.myVelib.bike.BikeFactory;
import fr.cs.group06.myVelib.dockingStation.DockingStation;
import fr.cs.group06.myVelib.location.Location;

class BikeFactoryTest {

	@Test
	void testInstallingBike() throws Exception {
		// Test case 1
		Location location = new Location(2.0, 1.0);
		DockingStation station = new DockingStation(10, 0.5, 0.2, 0.2, location, "online", "Plus");
		BikeFactory bikeFactory = new BikeFactory();
		Bike bike = bikeFactory.installingBike("Mechanical", location, station);
		assertEquals("Mechanical", bike.getBikeType());
		assertSame(location, bike.getLocation());
		assertSame(station, bike.getDockingStation());

		// Test case 2
		Location location2 = new Location(3.0, 2.0);
		DockingStation station2 = new DockingStation(5, 0.7, 0.1, 0.3, location2, "online", "Standard");
		Bike bike2 = bikeFactory.installingBike("Electrical", location2, station2);
		assertEquals("Electrical", bike2.getBikeType());
		assertSame(location2, bike2.getLocation());
		assertSame(station2, bike2.getDockingStation());

		// Test case 3: Invalid bike type
		Location location3 = new Location(4.0, 3.0);
		DockingStation station3 = new DockingStation(8, 0.4, 0.3, 0.1, location3, "online", "Plus");
		Bike bike3 = bikeFactory.installingBike("InvalidType", location3, station3);
		assertNull(bike3);

}
}