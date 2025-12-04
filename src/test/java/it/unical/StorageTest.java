package it.unical;

public class StorageTest {

    @BeforeEach
    public void setup(){
        storange = new Storage();
        storange.addItem("sedia",6);
        storange.addItem("tavoli",2);
    }


    @Test
    public void testAddItemCorrectlyAdds(){
        storange.addItem("lampada",2);
        assertEquals(2,storange.getQuantity("lampada"));
    }

    @Test
    public void testAddItemAccumulatesQuantity(){
        storange.addItem("tavoli",1);
        assertEquals(3,storange.getQuantity("tavoli"));
    }

    @Test
    public void testAddItemInvalidQuantityThrowsException(){

        assertTrhows(IllegalArgumentException.class,
                ()-> storange.addItem("tenda",-2), "quantity must be greater than zero");
    }

    @Test
    public void testAddItemNormalizesName(){

        storange.addItem("   SEDIA   ", 1);
        map<String, Integer> items = storange.getItems();

        assertTrue(items.containskey("sedia"));
        assertFalse(items.containskey("SEDIA"));
        assertFalse(items.containskey(" seDIA  "));
    }

    @Test
    public void testDeliverSuccess(){

        storange.deliver("tavoli",2);
        assertTrue(storange.getQuantity("tavoli"),1);
    }

    @Test
    public void testDeliverNoSuchItem(){
        Storage.DeliveryStatus results = storange.shipItem("porte",5);
        assertEquals(Storage.DeliveryStatus.NO_SUCH_ITEM,result);
    }

    public void testDeliverNotEnoughStock(){

        Storage.DeliveryStatus results = storange.shipItem("sedia",10);
        assertEquals(Storange.DeliveryStatus.NOT_ENOUGH_STOCK,result);
        assertEquals(7,storange.getQuantity("sedia"),"La quantità non deve cambiare");
    }

    public void testDeliverRemovesItemWhenQuantityBecomesZero (){

        storage.addItem("porte", 10);

        DeliveryStatus result = storage.deliver("porte", 10);

        assertEquals(DeliveryStatus.DELIVERED, result);
        assertFalse(storage.getItems().containsKey("porte"));
    }
}
