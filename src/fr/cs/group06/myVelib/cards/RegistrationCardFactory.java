/**
 * The RegistrationCardFactory class is responsible for creating registration cards based on the card type.
 * It provides a method to create a registration card object based on the specified card type.
 */
package fr.cs.group06.myVelib.cards;

/**
 * The RegistrationCardFactory class is responsible for creating registration cards based on the card type.
 * It provides a method to create a registration card object based on the specified card type.
 */
public class RegistrationCardFactory {
	
    /**
     * Creates a registration card object based on the specified card type.
     *
     * @param cardType the type of the registration card
     * @return the created registration card object, or null if the card type is invalid or not supported
     */
    public RegistrationCard createRegistrationCard(String cardType) {
        if (cardType == null) {
            return null;
        }
        if (cardType.equalsIgnoreCase("Vlibre")) {
            return new Vlibre();
        } else if (cardType.equalsIgnoreCase("Vmax")) {
            return new Vmax();
        }
        return null;
    }
}






