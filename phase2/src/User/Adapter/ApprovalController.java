package User.Adapter;

import User.UseCase.ApprovalManager;

public class ApprovalController {
    ApprovalManager am = new ApprovalManager();

    public String AllUserApprovals(){
        return am.AllUserApprovals();
    }

    public void removeUserApproval(String ua){

        am.removeUserApproval(ua);
    }
}
