package Main;

import Inventory.InvDataAccess;
import Main.UI.ApprovalDataAccess;
import Trade.TradeDataAccess;
import User.FileEditor;

import java.io.IOException;

public class DataAccessFull {
    private final InvDataAccess ida;
    private final TradeDataAccess tda;
    private final FileEditor fe;
    private  final ApprovalDataAccess aa;
    public DataAccessFull(GateWay gw){
        ida = new InvDataAccess(gw);
        tda = new TradeDataAccess(gw);
        fe = new FileEditor(gw);
        aa= new ApprovalDataAccess(gw);
    }

    public void updateFile() throws IOException {
        ida.updateFile();
        tda.updateFile();
        fe.updateFile();
    }

    public void readFile() throws IOException {
        ida.readFile();
        tda.readFile();
        fe.splitUser(fe.readFile());
        aa.readItem();
        aa.readUser();
    }


}

