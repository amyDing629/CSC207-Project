package Main;

import Inventory.InvDataAccess;
import Trade.TradeDataAccess;
import User.FileEditor;

import java.io.IOException;

public class DataAccessFull {
    private final InvDataAccess ida;
    private final TradeDataAccess tda;
    private final FileEditor fe;

    public DataAccessFull(GateWay gw){
        ida = new InvDataAccess(gw);
        tda = new TradeDataAccess(gw);
        fe = new FileEditor(gw);


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
    }


}

