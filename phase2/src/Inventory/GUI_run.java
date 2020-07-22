package Inventory;

import User.ClientUser;

import java.io.IOException;

public class GUI_run {
    public static void main(String[] args) throws IOException {
        ClientUser daniel = new ClientUser("daniel", "123", false);
        ClientUser amy = new ClientUser("amy", "123", false);
        Item apple = new Item("apple", "amy");
        Inventory iv = new Inventory();
        iv.getLendingList().add(apple);
        InvGUI invG = new InvGUI(daniel, iv);
        invG.run();
        System.out.println(daniel.getWishBorrow());
    }
}
