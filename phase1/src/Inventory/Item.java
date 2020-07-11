package Inventory;


/**
 * [entity class]
 * Basic attributes of items.
 */
public class Item {
    /**
     * The name of the item. Each item has a unique name.
     */
    private final String name;
    /**
     * Description of the item. Can be null.
     */
    private String description;
    /**
     * The owner of the item. The person who put the item in the wishLend list.
     */
    private String ownerName;
    /**
     * Whether the item is in an incomplete/unconfirmed trade.
     */
    private boolean isInTrade;

    /**
     * Constructor. Set isInTrade to false. Description is null.
     * @param name: the name of the item
     * @param owner: the owner of the item
     */
    public Item(String name, String owner) {
        this.name = name;
        this.ownerName = owner;
        this.isInTrade = false;
    }


    /**
     * getter for isInTrade
     * @return isInTrade
     */
    public boolean getIsInTrade(){
        return isInTrade;
    }

    /**
     * setter for isInTrade. When a trade is created, isInTrade need to be set to true.
     * @param inTrade: new inTrade status
     */
    public void setIsInTrade(Boolean inTrade){
        isInTrade = inTrade;
    }

    /**
     * getter for name
     * @return name of the item
     */
    public String getName(){
        return name;
    }

    /**
     * getter for description
     * @return description of the item
     */
    public String getDescription(){
        return description;
    }

    /**
     * setter for description. Users can edit descriptions of the item.
     * @param description new description of the item
     */
    public void setDescription(String description){this.description = description;}

    /**
     * getter for owner name
     * @return owner name
     */
    public String getOwnerName(){
        return ownerName;
    }

    /**
     * setter for owner, if the permanent trade is made, the item's owner should be changed.
     * @param user new owner
     */
    public void setOwner(String user){
        ownerName = user;
    }

    /**
     * to String
     * @return the name of the item.
     */
    @Override
    public String toString() {
        return name;
    }
}
