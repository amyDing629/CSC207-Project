package Trade;

import java.awt.*;

public class AcceptTradeGUIBuilder implements TradeGUIBuilder {
    private final TradeGUI tg;

    public AcceptTradeGUIBuilder(){
        tg = new TradeGUI();
    }

    public void buildSize(){

    }

    @Override
    public void buildPanelN() {

    }

    @Override
    public void buildPanelE() {

    }

    @Override
    public void buildPanelW() {

    }

    @Override
    public void buildPanelS() {

    }

    @Override
    public void buildPanelC() {

    }

    public TradeGUI getTradeGUI(){
        return tg;
    }
}
