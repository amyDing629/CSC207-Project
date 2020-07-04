public class InventoryController {
    Inventory iv = new Inventory();
    ClientUser currUser;
    Item item;

    public InventoryController(ClientUser currUser){
        this.currUser = currUser;
    }

    public boolean selectItem(String line){
        for (Item it: iv.getLendingList()){
            if (it.getName().equals(line)){
                item = it;
                return true;
            }
        }
        return false;
    }

    public void moveToWishList(){
        currUser.getWishBorrow().add(item.getName());
    }
}
