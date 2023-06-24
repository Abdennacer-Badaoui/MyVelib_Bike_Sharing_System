/**
 * The RegistrationCard interface represents a registration card in the MyVelib system.
 * It defines the methods that should be implemented by classes representing different types of registration cards.
 */
package fr.cs.group06.myVelib.cards;

/**
 * The RegistrationCard interface represents a registration card in the MyVelib system.
 * It defines the methods that should be implemented by classes representing different types of registration cards.
 */
public interface RegistrationCard {

    /**
     * Returns the type of the registration card.
     *
     * @return the type of the card
     */
    public String getType();

    /**
     * Returns the ID of the registration card.
     *
     * @return the card ID
     */
    public int getCardID();

    /**
     * Adds the specified amount of time credit balance to the card.
     *
     * @param addition the amount of time credit balance to add
     */
    public void addTimeCreditBalance(double addition);

    /**
     * Returns the time credit balance of the card.
     *
     * @return the time credit balance
     */
    public double getTimeCreditBalance();

    /**
     * Sets the time credit balance of the card to the specified value.
     *
     * @param time the time credit balance to set
     */
    public void setTimeCreditBalance(double time);

}
