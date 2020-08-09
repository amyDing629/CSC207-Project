package Inventory;

import User.Entity.ClientUser;
import User.UseCase.ItemApprovalManager;
import User.UseCase.UserManager;

import javax.swing.*;
import java.io.IOException;

public class GUI_run {
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        ClientUser daniel = new ClientUser("daniel", "123", false);
        ClientUser amy = new ClientUser("amy", "123", false);
        ClientUser admin = new ClientUser("admin", "123", true);
        Item apple = new Item("apple", amy.getId());
        Item pear = new Item("pear", daniel.getId());
        amy.addWishes("apple");
        daniel.addWishes("pear");
        Inventory iv = new Inventory();
        iv.addItem(apple);
        iv.addItem(pear);
        UserManager um = new UserManager();
        um.addUser(daniel);
        um.addUser(amy);
        um.addUser(admin);
        InventoryGUI igAmy = new InventoryGUI(amy.getId(), frame);
        igAmy.run();
        InventoryGUI ar = new InventoryGUI(admin.getId(), frame);
        ar.run();
    }
}
