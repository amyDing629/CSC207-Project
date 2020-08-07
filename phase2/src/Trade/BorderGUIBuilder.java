package Trade;

interface BorderGUIBuilder {
    void buildFrame();
    void buildPanelN();
    void buildPanelE();
    void buildPanelW();
    void buildPanelS();
    void buildPanelC();
    BorderGUINoTextArea getTradeGUI();
}
