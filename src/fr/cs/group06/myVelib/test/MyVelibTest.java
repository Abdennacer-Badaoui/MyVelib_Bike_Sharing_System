package fr.cs.group06.myVelib.test;
import  fr.cs.group06.myVelib.myvelib.*;
import  fr.cs.group06.myVelib.dockingStation.*;
import  fr.cs.group06.myVelib.user.*;
import  fr.cs.group06.myVelib.location.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyVelibTest {

    private MyVelib myVelib;
    private User user1;


    @Before
    public void setUp() throws Exception {
        myVelib = new MyVelib("MyVelib Network", 5, 20, 0.7, 0.5, 10.0);
        myVelib.addUser(user1);
    }

    @Test
    public void testGetStandardStationsNumber() {
        int expected = 5;
        int actual = myVelib.getStandardStationsNumber();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPlusStationsNumber() {
        int expected = 0;
        int actual = myVelib.getPlusStationsNumber();
        assertEquals(expected, actual);
    }


    @Test
    public void testAddDockingStation() throws Exception {
        DockingStation dockingStation = new DockingStation(10, 0.5, 0.1, 0.3, new Location(5.0, 5.0), "online", "Standard");
        myVelib.addDockingStation(dockingStation);
        int expected = 6;
        int actual = myVelib.getDockingStations().size();
        assertEquals(expected, actual);
    }

    @Test
    public void testAddUser() {
        User user = new User("John Doe",new Location(2.0, 2.0),null, null);
        myVelib.addUser(user);
        int expected = 2;
        int actual = myVelib.getUsers().size();
        assertEquals(expected, actual);
    }


    @Test(expected = Exception.class)
    public void testGetDockingStation_ThrowsException() throws Exception {
        int id = 10;
        myVelib.getDockingStation(id);
    }


    @Test(expected = Exception.class)
    public void testGetUser_ThrowsException() throws Exception {
        int id = 10;
        myVelib.getUser(id);
    }
}
