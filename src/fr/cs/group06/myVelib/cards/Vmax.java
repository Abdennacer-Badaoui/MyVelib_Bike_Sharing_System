/**
 * The Vmax class represents a specific type of registration card called "Vmax".
 * It implements the RegistrationCard interface.
 */
package fr.cs.group06.myVelib.cards;

/**
 * The Vmax class represents a specific type of registration card called "Vmax".
 * It implements the RegistrationCard interface.
 */
public class Vmax implements RegistrationCard {
    protected String type;
    protected static int id = 0;
    protected int id_unique;
    protected double timeCreditBalance;

    /**
     * Constructs a new Vmax registration card object.
     * Initializes the type, unique ID, and time credit balance.
     */
    public Vmax() {
        super();
        this.type = "Vmax";
        Vmax.id++;
        id_unique = Vmax.id;
        this.timeCreditBalance = 0;
    }

    @Override
    public void addTimeCreditBalance(double addition) {
        // TODO Auto-generated method stub
        setTimeCreditBalance(this.timeCreditBalance + addition);
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return this.type;
    }

    @Override
    public int getCardID() {
        // TODO Auto-generated method stub
        return this.id_unique;
    }

    /**
     * Retrieves the time credit balance of the Vmax registration card.
     *
     * @return the time credit balance
     */
    public double getTimeCreditBalance() {
        return timeCreditBalance;
    }

    /**
     * Sets the time credit balance of the Vmax registration card.
     *
     * @param timeCreditBalance the time credit balance to set
     */
    public void setTimeCreditBalance(double timeCreditBalance) {
        this.timeCreditBalance = timeCreditBalance;
    }
}
