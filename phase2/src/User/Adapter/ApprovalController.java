package User.Adapter;

import User.UseCase.ApprovalManager;
import User.UseCase.UserManager;


public class ApprovalController {
    ApprovalManager am = new ApprovalManager();
    UserManager um = new UserManager();

    public String AllUserApprovals(){
        return am.AllUserApprovals();
    }

    public void removeUserApproval(String ua){

        am.removeUserApproval(ua);
    }

    public void addApprovals(String name, String des){am.addApprovals(um.getUser(name),des);}
}
