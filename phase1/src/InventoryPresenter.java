public class InventoryPresenter {
    User currUser;
    Inventory iv = new Inventory();

    public InventoryPresenter(User currUser){
        this.currUser = currUser;
    }

    public void printInventory(){
        System.out.println(iv.getLendingList());
    }

}
