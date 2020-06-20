public class AdministrativeUser {
    public String password;
    public String username;
    private int id;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    pubic void changePassword(String password){
        this.password = password;
    }

    pubic void changeUsername(String username){
        this.username = username;
    }

    public List<String> getNotification() {
        return notification;
    }

    public void freeze();


    public void unfreeze();


    public void confirmItem();


    public void changeValue();


    public addNewAduser();

}
