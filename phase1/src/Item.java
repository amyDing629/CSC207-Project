public class Item {
    private String name;
    private String description;
    private String ownerName;

    /**
     * Constructor
     * @param name the name of the item
     * @param description the description of the item
     */
    public Item(String name, String description, String owner) {
        this.name = name;
        this.description = description;
        this.ownerName = owner;
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

