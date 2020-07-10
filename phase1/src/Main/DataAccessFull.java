package Main;

import Inventory.InvDataAccess;
import Trade.TradeDataAccess;
import User.FileEditor;

import java.io.IOException;

public class DataAccessFull {
    private final InvDataAccess ida = new InvDataAccess();
    private final TradeDataAccess tda = new TradeDataAccess();
    private final FileEditor fe = new FileEditor();

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
