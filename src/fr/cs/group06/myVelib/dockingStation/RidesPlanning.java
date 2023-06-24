package fr.cs.group06.myVelib.dockingStation;

import java.util.*;

import fr.cs.group06.myVelib.location.Location;

/**
 * The RidesPlanning interface represents the rides planning functionality.
 * It provides methods for finding the nearest docking station based on location and bike type.
 */
public interface RidesPlanning {
    
    /**
     * Finds the nearest docking station based on the given location and bike type.
     * 
     * @param loc              The location to find the nearest docking station.
     * @param BikeType         The type of bike required.
     * @param dockingstations  The list of available docking stations.
     * @return The nearest docking station.
     * @throws Exception if no suitable docking station is found.
     */
    public DockingStation nearestStation(Location loc, String BikeType, List<DockingStation> dockingstations) throws Exception;
    
    /**
     * Finds the nearest docking station based on the given location.
     * 
     * @param loc              The location to find the nearest docking station.
     * @param dockingstations  The list of available docking stations.
     * @return The nearest docking station.
     * @throws Exception if no suitable docking station is found.
     */
    public DockingStation nearestStation(Location loc, List<DockingStation> dockingstations) throws Exception;
}
