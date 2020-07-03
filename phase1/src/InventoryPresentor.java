import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InventoryPresentor {
    Inventory inventory;
    ClientUser currUser;
    public InventoryPresentor(ClientUser currUser){
        inventory = new Inventory();
        this.currUser = currUser;

    }

    public void run(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("type 'see inventory' to see the items that you an trade");
        try {
            String line = br.readLine();
            if (line.equals("see inventory")){
                System.out.println(inventory.getLendingList());
            }

        } catch (IOException e) {
            System.out.println("your input is not correct, please try again");
        }
    }


}
