package Main.UI;


import User.Entity.ClientUser;

public class ItemApprovals extends UserApprovals {
    private String secString;
    public ItemApprovals(ClientUser user, String fstString, String secString) {
        super(user, fstString);
        this.secString=secString;
        this.secString=secString;
    }

    public String getSecString() {
        return secString;
    }

    @Override
    public String toString() {
        return "User: "+getCurUserName()+"\nItem name: "+getFstString()+"\nDescription: "+getSecString()+"\n";
    }
}
