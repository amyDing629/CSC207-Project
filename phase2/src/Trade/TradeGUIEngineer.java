package Trade;

public class TradeGUIEngineer {
    private TradeGUIBuilder tgBuilder;

    public TradeGUIEngineer(TradeGUIBuilder tgb){
        tgBuilder = tgb;
    }


    public TradeGUI getGUI()
    {
        return tgBuilder.getTradeGUI();
    }

    public void constructHouse()
    {
        tgBuilder.buildFrame();
        tgBuilder.buildPanelN();
        tgBuilder.buildPanelE();
        tgBuilder.buildPanelW();
        tgBuilder.buildPanelS();
        tgBuilder.buildPanelC();

    }
}
