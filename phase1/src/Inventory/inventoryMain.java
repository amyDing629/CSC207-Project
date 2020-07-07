package Inventory;

import java.io.IOException;

public class inventoryMain {
    public static void main(String[] args) throws IOException {
        Inventory inventory = new Inventory();
        User amy = new User("amy", "123", false);
        InventoryUI i = new InventoryUI(amy);
        i.run();
        System.out.println(amy.getWishBorrow());
    }
}
