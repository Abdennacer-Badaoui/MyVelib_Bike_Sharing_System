package fr.cs.group06.myVelib.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.cs.group06.myVelib.cards.RegistrationCard;
import fr.cs.group06.myVelib.cards.RegistrationCardFactory;
import fr.cs.group06.myVelib.cards.Vlibre;
import fr.cs.group06.myVelib.cards.Vmax;

class RegistrationCardTest {

    @Test
    void testVlibreCard() {
        RegistrationCard vlibreCard = new Vlibre();
        
        assertEquals("Vlibre", vlibreCard.getType());
        assertEquals(1, vlibreCard.getCardID());
        assertEquals(0.0, vlibreCard.getTimeCreditBalance());
        
        vlibreCard.addTimeCreditBalance(5.0);
        assertEquals(5.0, vlibreCard.getTimeCreditBalance());
        
        vlibreCard.setTimeCreditBalance(10.0);
        assertEquals(10.0, vlibreCard.getTimeCreditBalance());
    }

    @Test
    void testVmaxCard() {
        RegistrationCard vmaxCard = new Vmax();
        
        assertEquals("Vmax", vmaxCard.getType());
        assertEquals(1, vmaxCard.getCardID());
        assertEquals(0.0, vmaxCard.getTimeCreditBalance());
        
        vmaxCard.addTimeCreditBalance(3.5);
        assertEquals(3.5, vmaxCard.getTimeCreditBalance());
        
        vmaxCard.setTimeCreditBalance(7.0);
        assertEquals(7.0, vmaxCard.getTimeCreditBalance());
    }

    @Test
    void testRegistrationCardFactory() {
        RegistrationCardFactory cardFactory = new RegistrationCardFactory();
        
        RegistrationCard vlibreCard = cardFactory.createRegistrationCard("Vlibre");
        assertNotNull(vlibreCard);
        assertEquals("Vlibre", vlibreCard.getType());
        
        RegistrationCard vmaxCard = cardFactory.createRegistrationCard("Vmax");
        assertNotNull(vmaxCard);
        assertEquals("Vmax", vmaxCard.getType());
        
        RegistrationCard invalidCard = cardFactory.createRegistrationCard("InvalidType");
        assertNull(invalidCard);
    }
}

