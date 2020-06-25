public class Item {
    private String name;
    private String description;
    private int ownerId;

    /**
     * Constructor
     * @param name the name of the item
     * @param description the description of the item
     */
    public Item(String name, String description, String ownerId) {
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

    public int getOwner(){
        return ownerId;
    }

    public void setOwner(int user){
        ownerId = user;
    }
}

