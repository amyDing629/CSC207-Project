package Inventory;

public class MarketController {
    Inventory iv;
    public MarketController(Inventory iv){
        this.iv = iv;
    }


    String printAvailable(){
        String result = "";
        for (Item it: iv.getAvailableList()){
            result = result + iv.getName(it) + "\n";
        }
        if (result.equals("")){
            return "no available item";
        }
        return result;

    }
    boolean selectItem(String line){
        for (Item it: iv.getLendingList()){
            if (iv.getName(it).equals(line)){
                return true;
            }
        }
        return false;
    }
    Item getItem(String line){
        return iv.getItem(line);
    }

    String wrongInput(){
        return "wrong input, please type again";
    }

    String printItemInfo(Item item) {
        return "Item Info:\nitem name: " + iv.getName(item) + "\n" +
                "item description: " + iv.getDescription(item)
                + "\n" + "item owner: " + iv.getOwnerName(item) ;
    }

}
