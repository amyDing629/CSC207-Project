package Inventory.Adaptor;

import Trade.Adaptor.BorderGUIWithThreeTextArea;

public interface iMarketPresenter {

    void resetInputArea();

    void updateListM(String availableList);

    void closeFrame();

    void resetCurr();

    void wrongInput();

    void updateCurr(String itemInfo);
}
