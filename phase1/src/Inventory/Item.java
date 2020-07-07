package Inventory;

public class Item {
    private String name;
    private String description;
    private String ownerName;
    private boolean isInTrade;

    /**
     * Constructor
     * @param name the name of the item
     */
    public Item(String name, String owner) {
        this.name = name;
        this.ownerName = owner;
        this.isInTrade = false;
    }


    public boolean getIsInTrade(){
        return isInTrade;
    }

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

    public void setDescription(String description){this.description = description;}

    public String getOwnerName(){
        return ownerName;
    }

    public void setOwner(String user){
        ownerName = user;
    }

    @Override
    public String toString() {
        return name;
    }
}

