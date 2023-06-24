/**
 * The CreditCard class represents a credit card in the MyVelib system.
 */
package fr.cs.group06.myVelib.cards;

/**
 * The CreditCard class represents a credit card in the MyVelib system.
 */
public class CreditCard {
    protected String ownerName;
    protected int cardNumber;
    protected String expirationDate;
    protected int secretCode;

    /**
     * Constructs a new CreditCard object with the specified parameters.
     *
     * @param ownerName       the name of the card owner
     * @param cardNumber      the card number
     * @param expirationDate  the expiration date of the card
     * @param secretCode      the secret code of the card
     */
    public CreditCard(String ownerName, int cardNumber, String expirationDate, int secretCode) {
        this.ownerName = ownerName;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.secretCode = secretCode;
    }

    /**
     * Returns the name of the card owner.
     *
     * @return the owner name
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Sets the name of the card owner.
     *
     * @param ownerName the owner name to set
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * Returns the card number.
     *
     * @return the card number
     */
    public int getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the card number.
     *
     * @param cardNumber the card number to set
     */
    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Returns the expiration date of the card.
     *
     * @return the expiration date
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date of the card.
     *
     * @param expirationDate the expiration date to set
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Returns the secret code of the card.
     *
     * @return the secret code
     */
    public int getSecretCode() {
        return secretCode;
    }

    /**
     * Sets the secret code of the card.
     *
     * @param secretCode the secret code to set
     */
    public void setSecretCode(int secretCode) {
        this.secretCode = secretCode;
    }

    /**
     * Returns a string representation of the CreditCard object.
     *
     * @return a string representation of the CreditCard
     */
    @Override
    public String toString() {
        return "CreditCard of the user has the following identifiers : {ownerName=" + ownerName + ", cardNumber=" + cardNumber + ", expirationDate="
                + expirationDate + ", secretCode=" + secretCode + "}";
    }
}
