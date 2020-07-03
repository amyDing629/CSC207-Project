import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InventoryUI {
    Inventory inventory;
    ClientUser currUser;
    InventoryPresenter ip;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public InventoryUI(ClientUser currUser){
        inventory = new Inventory();
        this.currUser = currUser;
        ip = new InventoryPresenter(currUser);

    }

    public void run(){
        System.out.println("type 'see inventory' to see all the items");
        try {
            String line = br.readLine();
            if (line.equals("see inventory")){
                ip.printInventory();
            }

        } catch (IOException e) {
            System.out.println("your input is not correct, please try again");
        }
    }

    public void Selection(){
        System.out.println("");
        try{
            String line = br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
