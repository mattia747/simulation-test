package it.unical;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    private Storage storage;

    @BeforeEach
    public void setup() {
        storage = new Storage();
        storage.addItem("sedia", 6);
        storage.addItem("tavoli", 2);
    }

    @Test
    public void aggiuntaArticoloFunzionaCorrettamente() {
        storage.addItem("lampada", 2);
        assertEquals(2, storage.getQuantity("lampada"));
    }
    
    @Test
    public void aggiuntaArticoloSommaQuantitaCorretta() {
        storage.addItem("tavoli", 1);
        assertEquals(3, storage.getQuantity("tavoli")); // 2 da setup + 1
    }

    @Test
    public void aggiuntaArticoloQuantitaNonValidaLanciaEccezione() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> storage.addItem("tenda", -2)
        );
        assertEquals("quantity must be greater than zero", ex.getMessage());
    }

    @Test
    public void aggiuntaArticoloNormalizzaIlNome() {
        storage.addItem("   SEDIA   ", 1);

        Map<String, Integer> items = storage.getItems();

        assertTrue(items.containsKey("sedia"));
        assertFalse(items.containsKey("SEDIA"));
        assertFalse(items.containsKey(" seDIA  "));
    }

    @Test
    public void consegnaArticoloRiesce() {
        Storage.DeliveryStatus result = storage.deliver("tavoli", 2);

        assertEquals(Storage.DeliveryStatus.DELIVERED, result);
        assertEquals(0, storage.getQuantity("tavoli"));
        assertFalse(storage.getItems().containsKey("tavoli"));
    }

    @Test
    public void consegnaArticoloNonEsistenteRestituisceErrore() {
        Storage.DeliveryStatus result = storage.deliver("porte", 5);
        assertEquals(Storage.DeliveryStatus.NO_SUCH_ITEM, result);
    }

    @Test
    public void consegnaArticoloSenzaStockSufficienteRestituisceErrore() {
        Storage.DeliveryStatus result = storage.deliver("sedia", 10);

        assertEquals(Storage.DeliveryStatus.NOT_ENOUGH_STOCK, result);
        assertEquals(6, storage.getQuantity("sedia"),
                "La quantità non deve cambiare se non ci sono abbastanza pezzi");
    }

    @Test
    public void consegnaRimuoveArticoloQuandoQuantitaDiventaZero() {
        storage.addItem("porte", 10);

        Storage.DeliveryStatus result = storage.deliver("porte", 10);

        assertEquals(Storage.DeliveryStatus.DELIVERED, result);
        assertFalse(storage.getItems().containsKey("porte"));
    }

    @Test
    public void consegnaQuantitaNonValidaLanciaEccezione() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> storage.deliver("sedia", 0)
        );
        assertEquals("quantity must be greater than zero", ex.getMessage());
    }
}
