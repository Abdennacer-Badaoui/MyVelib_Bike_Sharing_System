package fr.cs.group06.myVelib.dockingStation;

import java.util.*;

import fr.cs.group06.myVelib.location.Location;

/**
 * The StartDockingStation class implements the RidesPlanning interface and represents the starting docking station for rides planning.
 */
public class StartDockingStation implements RidesPlanning {

    /**
     * Finds the nearest docking station based on the given location and bike type.
     * 
     * @param loc              The location to find the nearest docking station.
     * @param biketype         The type of bike required.
     * @param dockingstations  The list of available docking stations.
     * @return The nearest docking station.
     * @throws Exception if no suitable docking station is found.
     */
    public DockingStation nearestStation(Location loc, String biketype, List<DockingStation> dockingstations) throws Exception {
        ArrayList<DockingStation> possibledockingstations = new ArrayList<>();
        
        for (DockingStation dockinstation : dockingstations) {
             if (dockinstation.hasBike(biketype) && dockinstation.getStationStatus().equalsIgnoreCase("Online")) {
                   possibledockingstations.add(dockinstation);
                }
        }
        
        if (possibledockingstations.isEmpty()) {
            throw new Exception("There is no " + biketype + " bike left!");
        } else {
            DockingStation startdockingstation = possibledockingstations.get(0);
            double minDistance = startdockingstation.getStationLocation().calculateDistance(loc);
            
            for (DockingStation dockingstation : possibledockingstations) {
                if (dockingstation.getStationLocation().calculateDistance(loc) < minDistance) {
                    startdockingstation = dockingstation;
                    minDistance = dockingstation.getStationLocation().calculateDistance(loc);
                }
            }
            return startdockingstation;
        }
    }

    /**
     * Finds the nearest docking station based on the given location.
     * 
     * @param loc              The location to find the nearest docking station.
     * @param dockingstations  The list of available docking stations.
     * @return The nearest docking station.
     * @throws Exception if no suitable docking station is found.
     */
    public DockingStation nearestStation(Location loc, List<DockingStation> dockingstations) throws Exception {
        return null;
    }
}
