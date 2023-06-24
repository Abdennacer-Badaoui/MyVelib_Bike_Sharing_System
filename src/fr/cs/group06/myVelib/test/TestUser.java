package fr.cs.group06.myVelib.test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import fr.cs.group06.myVelib.bike.Bike;
import fr.cs.group06.myVelib.bike.BikeFactory;
import fr.cs.group06.myVelib.cards.CreditCard;
import fr.cs.group06.myVelib.cards.RegistrationCard;
import fr.cs.group06.myVelib.cards.RegistrationCardFactory;
import fr.cs.group06.myVelib.location.Location;
import fr.cs.group06.myVelib.dockingStation.DockingStation;
import fr.cs.group06.myVelib.user.User;


public class TestUser {

	    private User user;
	    private DockingStation dockingStation;
	    private Bike bike;

	    @Before
	    public void setUp() throws Exception {
	        Location location = new Location(0.0, 0.0);
	        CreditCard creditCard = null;

	        RegistrationCardFactory rgf = new RegistrationCardFactory();
	        RegistrationCard registrationCard = rgf.createRegistrationCard("Vlibre");
	        user = new User("Hatim Abdennacer", location, creditCard, registrationCard);

	        dockingStation = new DockingStation(10, 0.8, 0.2, 0.5, location, "Online", "Standard");
	        BikeFactory bf = new BikeFactory();
	    }

	    @Test
	    public void testHasRentedBike() {
	        assertFalse(user.hasRentedBike());

	        user.setRentedBike(bike);

	        assertTrue(user.hasRentedBike());
	    }

	    @Test
	    public void testGetRentedBike() {
	        assertNull(user.getRentedBike());

	        user.setRentedBike(bike);

	        assertEquals(bike, user.getRentedBike());
	    }

	    @Test
	    public void testGetUserName() {
	        assertEquals("Hatim Abdennacer", user.getUserName());
	    }

	    @Test
	    public void testSetUserName() {
	        user.setUserName("Abdennacer Hatim");

	        assertEquals("Abdennacer Hatim", user.getUserName());
	    }

	}


