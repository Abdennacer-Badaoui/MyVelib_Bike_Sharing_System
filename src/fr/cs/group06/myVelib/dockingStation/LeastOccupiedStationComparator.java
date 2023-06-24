package fr.cs.group06.myVelib.dockingStation;

import java.util.*;

public class LeastOccupiedStationComparator implements Comparator<DockingStation> {
	
	/**
	 * Compare two docking stations based on their least occupied status.
	 * 
	 * @param ds1 The first docking station.
	 * @param ds2 The second docking station.
	 * @return Negative if ds1 is less occupied than ds2, positive if ds1 is more occupied than ds2, or zero if they are equally occupied.
	 */
	@Override
	public int compare(DockingStation ds1, DockingStation ds2) {
		// Calculate the least occupied status by subtracting the number of returns from the number of rents
		// The negative sign is used to sort in ascending order (least occupied first)
		return -(-ds1.getNumberOfRents() + ds1.getNumberOfReturns()) + (-ds2.getNumberOfRents() + ds2.getNumberOfReturns());
	}
}
