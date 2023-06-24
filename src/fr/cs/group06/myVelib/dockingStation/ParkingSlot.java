package fr.cs.group06.myVelib.dockingStation;

import fr.cs.group06.myVelib.bike.Bike;

public class ParkingSlot {
	private DockingStation station;
	private String slotStatus;
	private Bike bike = null;
	private static int generatorSlotsId = 0;
	private int SlotId;
	
	/**
	 * Constructor for the ParkingSlot class.
	 * 
	 * @param station     The docking station to which the parking slot belongs.
	 * @param slotStatus  The status of the parking slot (e.g., "Free", "Occupied").
	 * @param bike        The bike associated with the parking slot.
	 */
	public ParkingSlot(DockingStation station, String slotStatus, Bike bike) {
		super();
		this.station = station;
		this.slotStatus = slotStatus;
		this.bike = bike;
		this.SlotId = ++generatorSlotsId;
	}
    
	/**
	 * Get the docking station to which the parking slot belongs.
	 * 
	 * @return The docking station.
	 */
	public DockingStation getStation() {
		return station;
	}

	/**
	 * Get the status of the parking slot.
	 * 
	 * @return The slot status.
	 */
	public String getSlotStatus() {
		return slotStatus;
	}

	/**
	 * Get the bike associated with the parking slot.
	 * 
	 * @return The bike.
	 */
	public Bike getBike() {
		return bike;
	}

	/**
	 * Get the ID of the parking slot.
	 * 
	 * @return The slot ID.
	 */
	public int getSlotId() {
		return SlotId;
	}

	/**
	 * Set the status of the parking slot.
	 * 
	 * @param slotStatus The slot status to be set.
	 */
	public void setSlotStatus(String slotStatus) {
		this.slotStatus = slotStatus;
	}

	/**
	 * Set the bike associated with the parking slot.
	 * 
	 * @param bike The bike to be set.
	 */
	public void setBike(Bike bike) {
		this.bike = bike;
	}
}
