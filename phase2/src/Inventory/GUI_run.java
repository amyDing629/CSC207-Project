package Inventory;

import User.ClientUser;
import User.ItemApprovalManager;
import User.UserManager;

import javax.swing.*;
import java.io.IOException;

public class GUI_run {
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        ClientUser daniel = new ClientUser("daniel", "123", false);
        ClientUser amy = new ClientUser("amy", "123", false);
        ClientUser admin = new ClientUser("admin", "123", true);
        Item apple = new Item("apple", "amy");
        Item pear = new Item("pear", "daniel");
        Inventory iv = new Inventory();
        iv.addItem(apple);
        iv.addItem(pear);
        UserManager um = new UserManager();
        ItemApprovalManager iam = new ItemApprovalManager();
        um.addUser(daniel);
        um.addUser(amy);
        um.addUser(admin);
        InventoryGUI igAmy = new InventoryGUI(amy, iv, um, iam, frame);
        igAmy.run();
        AgreeRequestsGUI ar = new AgreeRequestsGUI(admin, iv, um, iam, frame);
        ar.run();
    }
}