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
    public DataAccessFull(ItemApprovalManager iam){
        ida = new InvDataAccess();
        tda = new TradeDataAccess();
        fe = new UserDataAccess();
        aa = new ApprovalDataAccess(iam);
    }

    /**
     * update all the files
     */
    public void updateFile() throws IOException {
        ida.updateSer();
        tda.updateSer();
        fe.updateSer();
        aa.updateFile();
    }

    /**
     * read all the files
     */
    public void readFile() {
        ida.deSerialize();
        tda.deSerialize();
        fe.deSerialize();
        aa.readItem();
        aa.readUser();
    }


}
