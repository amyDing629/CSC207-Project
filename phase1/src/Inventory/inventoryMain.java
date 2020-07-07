package Inventory;
import User.User;
import java.io.IOException;

public class inventoryMain {
    public static void main(String[] args) throws IOException {
        User amy = new User("amy", "123", false);
        InventoryUI i = new InventoryUI(amy);
        i.run();
        System.out.println(amy.getWishBorrow());
    }
}
