public class inventoryMain {
    public static void main(String[] args){
        Inventory inventory = new Inventory();
        ClientUser amy = new ClientUser("amy", "123");
        Item apple = new Item("apple","amy");
        Item pear = new Item("pear", "amy");
        inventory.addItemLending(apple);
        inventory.addItemLending(pear);
        inventory.addItemFrozen(pear);
        InventoryPresentor ip = new InventoryPresentor(inventory);
        ip.run();


    }
}
