import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InventoryUI {
    Inventory inventory;
    User currUser;
    InventoryPresenter ip;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    InventoryController ic = new InventoryController(currUser);
    Item currItem;

    public InventoryUI(User currUser){
        inventory = new Inventory();
        this.currUser = currUser;
        ip = new InventoryPresenter(currUser);

    }

    public void run() {
        System.out.println("type 'see inventory' to see all the items");
        try {
            String line = br.readLine();
            if (line.equals("see inventory")) {
                ip.printInventory();
            }

        } catch (IOException e) {
            System.out.println("your input is not correct, please try again");
        }

        while (true) {
            System.out.println("Please select an Item");
            try {
                String line2 = br.readLine();
                if (!ic.selectItem(line2)) {
                    System.out.println("your input is not found, please type again");
                } else {
                    currItem = inventory.getItem(line2);
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        while (true){
            System.out.println("menu:\n type '1' to add to wish borrow list");
            try{
                String line3 = br.readLine();
                if (line3.equals("1")){
                    ic.moveToWishList();
                    System.out.println("the item has been moved to the wish list");
                    break;//move back to see inventory
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



