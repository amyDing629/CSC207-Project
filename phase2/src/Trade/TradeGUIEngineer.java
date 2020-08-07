package Trade;

public class TradeGUIEngineer {
    private final BorderGUIBuilder tgBuilder;

    public TradeGUIEngineer(BorderGUIBuilder tgb){
        tgBuilder = tgb;
    }


    public TradeGUIPlan getGUI()
    {
        return tgBuilder.getTradeGUI();
    }

    public void constructGUI()
    {
        tgBuilder.buildFrame();
        tgBuilder.buildPanelN();
        tgBuilder.buildPanelE();
        tgBuilder.buildPanelW();
        tgBuilder.buildPanelS();
        tgBuilder.buildPanelC();

    }
}
