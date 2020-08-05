package Trade;

interface TradeGUIBuilder {
    void buildFrame();
    void buildPanelN();
    void buildPanelE();
    void buildPanelW();
    void buildPanelS();
    void buildPanelC();
    TradeGUI getTradeGUI();
}
