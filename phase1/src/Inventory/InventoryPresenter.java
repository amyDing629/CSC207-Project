package Inventory;

public class InventoryPresenter {
    User currUser;
    Inventory iv = new Inventory();

    public InventoryPresenter(User currUser){
        this.currUser = currUser;
    }

    public void printAvailable(){
        System.out.println(iv.getAvailableList());
    }

    public void printItemInfo(Item item) {
        System.out.println("item name: " + item.getName());
        System.out.println("item description: " + item.getDescription());
        System.out.println("item owner: " + item.getOwnerName());
    }

}
