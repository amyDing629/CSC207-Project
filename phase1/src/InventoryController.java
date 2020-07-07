public class InventoryController {
    Inventory iv = new Inventory();
    User currUser;
    Item item;

    public InventoryController(User currUser){
        this.currUser = currUser;
    }

    public boolean selectItem(String line){
        for (Item it: iv.getLendingList()){
            if (it.getName().equals(line)){
                item = it;
                System.out.println();
                return true;
            }
        }
        return false;
    }

    public boolean isOwnItem(Item it){
        if (it.getOwnerName().equals(currUser.getUsername())){
            return true;
        }
        return false;
    }

    public void moveToWishList(Item it){
        currUser.addWishBorrow(it.getName());
    }
}
