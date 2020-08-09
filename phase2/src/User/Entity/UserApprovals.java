package User.Entity;

public class UserApprovals {
    ClientUser user;
    String fstString;
    public UserApprovals(ClientUser user, String fstString){
        this.user=user;
        this.fstString=fstString;
    }
    public ClientUser getCurUser(){
        return user;
    }
    public String getCurUserName(){
        return user.getUsername();
    }
    public String getFstString(){
        return fstString;
    }
    @Override
    public String toString() {
        return "User: "+getCurUserName()+"\nReasons: "+getFstString()+"\n";
    }
}
