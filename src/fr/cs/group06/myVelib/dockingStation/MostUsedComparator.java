package fr.cs.group06.myVelib.dockingStation;

import java.util.*;

public class MostUsedComparator implements Comparator<DockingStation> {

	/**
	 * Compare two docking stations based on their most used status.
	 * 
	 * @param ds1 The first docking station.
	 * @param ds2 The second docking station.
	 * @return Negative if ds1 is less used than ds2, positive if ds1 is more used than ds2, or zero if they are equally used.
	 */
	@Override
	public int compare(DockingStation ds1, DockingStation ds2) {
		// Calculate the most used status by adding the number of rents and returns
		// The negative sign is used to sort in descending order (most used first)
		return -(ds1.getNumberOfRents() + ds1.getNumberOfReturns()) + (ds2.getNumberOfRents() + ds2.getNumberOfReturns());
	}
}
