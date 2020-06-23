public class Item {
    private String name;
    private String description;
    private ClientUser owner;

    /**
     * Constructor
     * @param name the name of the item
     * @param description the description of the item
     */
    public Item(String name, String description, String owner) {
        this.name = name;
        this.description = description;
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

    public ClientUser getOwner(){
        return owner;
    }

    public void setOwner(ClientUser user){
        owner = user;
    }
}

