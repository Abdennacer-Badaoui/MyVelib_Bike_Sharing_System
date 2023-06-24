package fr.cs.group06.myVelib.myvelib;

import java.util.*;
import fr.cs.group06.myVelib.bike.*;
import fr.cs.group06.myVelib.dockingStation.*;
import fr.cs.group06.myVelib.location.Location;
import fr.cs.group06.myVelib.ride.Ride;
import fr.cs.group06.myVelib.user.User;

/**
 * The MyVelib class represents the MyVelib network.
 */
public class MyVelib {
    private List<DockingStation> dockingStations = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private int NumberOfStations;
    private int TotalParkingSlots;
    private double ratioOccupied;
    private double ratioElectrical;
    private double side;
    private String name;

    /**
     * Constructs a MyVelib object with the specified parameters.
     *
     * @param name              The name of the MyVelib network.
     * @param numberOfStations  The total number of docking stations in the network.
     * @param totalParkingSlots The total number of parking slots in the network.
     * @param ratioOccupied     The ratio of occupied parking slots in each station.
     * @param ratioElectrical   The ratio of electrical bikes in each station.
     * @param side              The side length of the area where stations are randomly distributed.
     * @throws Exception if an error occurs during MyVelib initialization.
     */
    public MyVelib(String name, int numberOfStations, int totalParkingSlots, double ratioOccupied,
                   double ratioElectrical, double side) throws Exception {
        super();
        this.NumberOfStations = numberOfStations;
        this.TotalParkingSlots = totalParkingSlots;
        this.ratioOccupied = ratioOccupied;
        this.ratioElectrical = ratioElectrical;
        this.side = side;
        this.name = name;

        // Stations Locations
        Location[] locations = new Location[NumberOfStations];
        Random random = new Random();
        for (int i = 0; i < NumberOfStations; i++) {
            double randomx = random.nextDouble() * side;
            double randomy = random.nextDouble() * side;
            locations[i] = new Location(randomx, randomy);
        }

        int parkingsByStation = TotalParkingSlots / NumberOfStations;
        for (int i = 0; i < NumberOfStations; i++) {
            double ratioFree = 0.9 * (1 - ratioOccupied);
            DockingStation station = new DockingStation(parkingsByStation, ratioOccupied, ratioFree,
                    ratioElectrical, locations[i], "online", "Standard");
            dockingStations.add(station);
        }
    }

    /**
     * Displays the state of the MyVelib system.
     */
    public void State() {
        System.out.println("================ MyVelib Stations ================");
        System.out.println(" Total number of Stations   : " + NumberOfStations);
        System.out.println("     Type   :    Standard   : " + this.getStandardStationsNumber());
        System.out.println("                 Plus       : " + this.getPlusStationsNumber());
        System.out.println("     Status :    On service : " + this.getOnServiceStationsNumber());
        System.out.println("                 Offline    : " + this.getOffLineStationsNumber());
        System.out.println("==================================================");

        for (DockingStation station : dockingStations) {
            station.showDockingStationState();
            System.out.println("==================================================");
        }
    }

    /**
     * Creates a new ride for the specified user.
     *
     * @param user      The user who wants to create a new ride.
     * @param biketype  The type of bike the user wants to rent.
     * @param LocEnd    The location of the destination docking station.
     * @param time      The estimated duration of the ride.
     * @return The newly created ride.
     * @throws Exception if an error occurs during ride creation.
     */
    public Ride newRide(User user, String biketype, Location LocEnd, double time) throws Exception {
        if (user.hasRentedBike()) {
            throw new Exception("You already have a rented bike. You can't create a new Ride!");
        }
        StartDockingStation findstartstation = new StartDockingStation();
        DockingStation startStation = findstartstation.nearestStation(user.getUserLocation(), biketype,
                this.getDockingStations());
        EndDockingStation findendstation = new EndDockingStation();
        DockingStation endStation = findendstation.nearestStation(LocEnd, this.getDockingStations());
        Bike mybike = startStation.rentFromStation(user, biketype);
        Ride myNewRide = new Ride(user, mybike, startStation, endStation, startStation.getStationLocation(),
                endStation.getStationLocation(), time);
        @SuppressWarnings("unused")
		double cost = myNewRide.calculateCost();
        return myNewRide;
    }

    /**
     * Drops off the bike at the end of the ride.
     *
     * @param ride The ride to end and drop off the bike.
     * @throws Exception if an error occurs during bike drop-off.
     */
    public void dropbike(Ride ride) throws Exception {
        ride.getDsEnd().returnedToStation(ride.getUser());
    }

    /**
     * Adds a docking station to the MyVelib network.
     *
     * @param dockingStation The docking station to add.
     */
    public void addDockingStation(DockingStation dockingStation) {
        dockingStations.add(dockingStation);
    }

    /**
     * Adds a user to the MyVelib network.
     *
     * @param user The user to add.
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Displays the balance information for a specific user.
     *
     * @param user The user to display the balance for.
     */
    public void UserBalance(User user) {
        System.out.println("========= User Name : " + user.getUserName() + "  Id : " + user.getID() + " ========");
        System.out.println(" Number of rides             :  " + user.getNumberOfRides());
        System.out.println(" Total amount of charges     :  " + user.getBalanceOfTotalCharges() + " Euros.");
        if (user.getRegistrationCard() != null) {
            System.out.println(" Time-credit                 :  " + user.getRegistrationCard().getTimeCreditBalance()
                    + " min");
        } else {
            System.out.println(" User has no time credit");
        }
        System.out.println(" Total time spent on rides   :  " + user.getTotalTimeSpentOnRides() + " min");
    }

    /**
     * Displays the balance information for a specific docking station.
     *
     * @param station The docking station to display the balance for.
     */
    public void StationBalance(DockingStation station) {
        System.out.println("============= Station " + station.getStationId() + " Balance ============");
        System.out.println(" Number of Rents               : " + station.getNumberOfRents());
        System.out.println(" Number of Returns Operations  : " + station.getNumberOfReturns());
    }
    
    /**
     * Returns the docking station with the specified ID.
     *
     * @param id The ID of the docking station.
     * @return The docking station with the specified ID.
     * @throws Exception if the docking station with the specified ID is not found.
     */
    public DockingStation getDockingStation(int id) throws Exception {
        for (DockingStation station : dockingStations) {
            if (station.getStationId() == id) {
                return station;
            }
        }
        throw new Exception("There is no Station with this Id in this MyVelib Network !");
    }

    /**
     * Returns the user with the specified ID.
     *
     * @param id The ID of the user.
     * @return The user with the specified ID.
     * @throws Exception if the user with the specified ID is not found.
     */
    public User getUser(int id) throws Exception {
        for (User user : users) {
            if (user.getID() == id) {
                return user;
            }
        }
        throw new Exception("There is no User with this Id in this MyVelib Network !");
    }

    /**
     * Sorts the docking stations based on the specified method.
     *
     * @param method The sorting method ("MostUsed" or "LeastOccupied").
     */
    public void sort(String method) {
        if (method.equalsIgnoreCase("MostUsed")) {
            MostUsedComparator mucomp = new MostUsedComparator();
            Collections.sort(dockingStations, mucomp);
            int i = 1;
            System.out.println("List of stations of the " + this.getName() + " MyVelib Policy: Most Used");
            for (DockingStation station : this.dockingStations) {
                System.out.println(i + ". Station Id=" + station.getStationId());
                i++;
            }
        }
        if (method.equalsIgnoreCase("LeastOccupied")) {
            LeastOccupiedStationComparator locomp = new LeastOccupiedStationComparator();
            Collections.sort(dockingStations, locomp);
            int i = 1;
            System.out.println("List of stations of the " + this.getName() + " MyVelib Policy: Least Occupied");
            for (DockingStation station : this.dockingStations) {
                System.out.println(i + ". Station Id=" + station.getStationId());
                i++;
            }
        }
        
    }


    // Getters and Setters

    /**
     * Returns the list of docking stations in the MyVelib network.
     *
     * @return The list of docking stations.
     */
    
    /**
     * Returns the number of standard docking stations in the MyVelib network.
     *
     * @return The number of standard docking stations.
     */
    public int getStandardStationsNumber() {
        int count = 0;
        for (DockingStation station : dockingStations) {
            if (station.getStationType().equalsIgnoreCase("Standard")) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * Returns the number of plus docking stations in the MyVelib network.
     *
     * @return The number of plus docking stations.
     */
    public int getPlusStationsNumber() {
        return this.NumberOfStations - this.getStandardStationsNumber();
    }

    /**
     * Returns the number of docking stations in service in the MyVelib network.
     *
     * @return The number of docking stations in service.
     */
    public int getOnServiceStationsNumber() {
        int count = 0;
        for (DockingStation station : dockingStations) {
            if (station.getStationStatus().equalsIgnoreCase("On service")) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * Returns the number of offline docking stations in the MyVelib network.
     *
     * @return The number of offline docking stations.
     */
    public int getOffLineStationsNumber() {
        return this.NumberOfStations - this.getOnServiceStationsNumber();
    }

    
    public List<DockingStation> getDockingStations() {
        return dockingStations;
    }

    /**
     * Sets the list of docking stations in the MyVelib network.
     *
     * @param dockingStations The list of docking stations to set.
     */
    public void setDockingStations(List<DockingStation> dockingStations) {
        this.dockingStations = dockingStations;
    }

    /**
     * Returns the list of users in the MyVelib network.
     *
     * @return The list of users.
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Sets the list of users in the MyVelib network.
     *
     * @param users The list of users to set.
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * Returns the number of docking stations in the MyVelib network.
     *
     * @return The number of docking stations.
     */
    public int getNumberOfStations() {
        return NumberOfStations;
    }

    /**
     * Sets the number of docking stations in the MyVelib network.
     *
     * @param numberOfStations The number of docking stations to set.
     */
    public void setNumberOfStations(int numberOfStations) {
        NumberOfStations = numberOfStations;
    }

    /**
     * Returns the total number of parking slots in the MyVelib network.
     *
     * @return The total number of parking slots.
     */
    public int getTotalParkingSlots() {
        return TotalParkingSlots;
    }

    /**
     * Sets the total number of parking slots in the MyVelib network.
     *
     * @param totalParkingSlots The total number of parking slots to set.
     */
    public void setTotalParkingSlots(int totalParkingSlots) {
        TotalParkingSlots = totalParkingSlots;
    }

    /**
     * Returns the ratio of occupied parking slots in each docking station.
     *
     * @return The ratio of occupied parking slots.
     */
    public double getRatioOccupied() {
        return ratioOccupied;
    }

    /**
     * Sets the ratio of occupied parking slots in each docking station.
     *
     * @param ratioOccupied The ratio of occupied parking slots to set.
     */
    public void setRatioOccupied(double ratioOccupied) {
        this.ratioOccupied = ratioOccupied;
    }

    /**
     * Returns the ratio of electrical bikes in each docking station.
     *
     * @return The ratio of electrical bikes.
     */
    public double getRatioElectrical() {
        return ratioElectrical;
    }

    /**
     * Sets the ratio of electrical bikes in each docking station.
     *
     * @param ratioElectrical The ratio of electrical bikes to set.
     */
    public void setRatioElectrical(double ratioElectrical) {
        this.ratioElectrical = ratioElectrical;
    }

    /**
     * Returns the side length of the area where stations are randomly distributed.
     *
     * @return The side length.
     */
    public double getSide() {
        return side;
    }

    /**
     * Sets the side length of the area where stations are randomly distributed.
     *
     * @param side The side length to set.
     */
    public void setSide(double side) {
        this.side = side;
    }

    /**
     * Returns the name of the MyVelib network.
     *
     * @return The name of the network.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the MyVelib network.
     *
     * @param name The name of the network to set.
     */
    public void setName(String name) {
        this.name = name;
    }
}
