package fr.cs.group06.myVelib.dockingStation;

import java.util.*;

import fr.cs.group06.myVelib.location.Location;

public class EndDockingStation implements RidesPlanning {
	
	/**
	 * Find the nearest docking station with a free slot and online status.
	 *
	 * @param loc The location to find the nearest docking station from.
	 * @param dockingstations The list of available docking stations.
	 * @return The nearest docking station.
	 * @throws Exception If there are no free slots available in any station.
	 */
	public DockingStation nearestStation(Location loc , List<DockingStation> dockingstations) throws Exception {
		ArrayList<DockingStation> possibledockingstations = new ArrayList<>();
		
		for (DockingStation dockinstation : dockingstations) {
		     if (dockinstation.hasFreeSlot() && dockinstation.getStationStatus().equalsIgnoreCase("Online")) {
		           possibledockingstations.add(dockinstation);
		        }
		}

		if (possibledockingstations.isEmpty()) {
			throw new Exception("There is no free slot available in any station!");
            
        } else {
		
			DockingStation enddockingstation = possibledockingstations.get(0);
			double minDistance = enddockingstation.getStationLocation().calculateDistance(loc);
			
	
			for (DockingStation dockingstation : possibledockingstations) {
				if (dockingstation.getStationLocation().calculateDistance(loc) < minDistance) {
					enddockingstation = dockingstation;
					minDistance = dockingstation.getStationLocation().calculateDistance(loc);
				}
			}
			return enddockingstation;
		
        }
	}
	
    /**
     * Find the nearest docking station with a specific bike type, considering only location.
     *
     * @param loc The location to find the nearest docking station from.
     * @param BikeType The type of bike to consider.
     * @param dockingstations The list of available docking stations.
     * @return The nearest docking station.
     * @throws Exception If there are no docking stations with the specified bike type.
     */
    public DockingStation nearestStation(Location loc, String BikeType, List<DockingStation> dockingstations) throws Exception {
    	return null;
    }
}
