package Trade.Adaptor;

public class BorderGUIEngineer {
    private final BorderGUIBuilder tgBuilder;

    public BorderGUIEngineer(BorderGUIBuilder tgb){
        tgBuilder = tgb;
    }


    public GUIPlan getGUI()
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
