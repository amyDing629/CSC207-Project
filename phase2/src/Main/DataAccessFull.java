package Main;

import Inventory.InvDataAccess;
import Inventory.Inventory;
import Main.UI.ApprovalDataAccess;
import Trade.TradeDataAccess;
import Trade.TradeManager;
import User.Gateway.UserDataAccess;
import User.UseCase.ItemApprovalManager;
import User.UseCase.UserManager;

import java.io.IOException;

public class DataAccessFull {
    private final InvDataAccess ida;
    private final TradeDataAccess tda;
    private final UserDataAccess fe;
    private final ApprovalDataAccess aa;

    /**
     * [constructor]
     */
    public DataAccessFull(UserManager um, TradeManager tm, Inventory iv, ItemApprovalManager iam){
        ida = new InvDataAccess(iv);
        tda = new TradeDataAccess(tm);
        fe = new UserDataAccess(um);
        aa = new ApprovalDataAccess(iam);
    }

    /**
     * update all the files
     */
    public void updateFile() throws IOException {
        ida.updateFile();
        tda.updateFile();
        fe.updateSer();
        aa.updateFile();
    }

    /**
     * read all the files
     */
    public void readFile(TradeManager tm, Inventory iv, UserManager um) {
        ida.deSerialize();
        tda.deSerialize();
        fe.deSerialize();
        aa.readItem();
        aa.readUser();
    }


}
