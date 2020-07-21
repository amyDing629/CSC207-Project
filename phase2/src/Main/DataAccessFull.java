package Main;

import Inventory.InvDataAccess;
import Inventory.Inventory;
import Main.UI.ApprovalDataAccess;
import Trade.TradeDataAccess;
import Trade.TradeManager;
import User.ItemApprovalManager;
import User.UserDataAccess;
import User.UserManager;

import java.io.IOException;

public class DataAccessFull {
    private final InvDataAccess ida;
    private final TradeDataAccess tda;
    private final UserDataAccess fe;
    private  final ApprovalDataAccess aa;

    /**
     * [constructor]
     */
    public DataAccessFull(UserManager um, TradeManager tm, Inventory iv, ItemApprovalManager iam){
        ida = new InvDataAccess(iv);
        tda = new TradeDataAccess(tm);
        fe = new UserDataAccess(um);
        aa= new ApprovalDataAccess(iam);
    }

    /**
     * update all the files
     */
    public void updateFile() throws IOException {
        ida.updateFile();
        tda.updateFile();
        fe.updateFile();
        aa.updateFile();
    }

    /**
     * read all the files
     */
    public void readFile(TradeManager tm, Inventory iv, UserManager um) {
        ida.readFile();
        tda.readFile(iv);
        fe.splitUser(fe.readFile(), tm, um);
        aa.readItem();
        aa.readUser();
    }


}