public class InventoryPresenter {
    ClientUser currUser;
    Inventory iv = new Inventory();

    public InventoryPresenter(ClientUser currUser){
        this.currUser = currUser;
    }

    public void printInventory(){
        System.out.println(iv.getLendingList());
    }

}
