package Inventory.Adaptor;

import Trade.Adaptor.BorderGUIWithThreeTextArea;

import javax.swing.*;

interface MarketInputPanelStrategy {
    void solve(BorderGUIWithThreeTextArea tg, MarketController ic);
}

class VisitorStrategy implements MarketInputPanelStrategy {

    public void solve(BorderGUIWithThreeTextArea tg, MarketController ic) {
        JPanel panelS = new JPanel();
        JTextArea ta = new JTextArea("Type item name here", 1, 10);
        JButton submit = new JButton("Submit");
        JButton back  = new JButton("Return");
        panelS.add(ta);
        panelS.add(submit);
        panelS.add(back);
        tg.setSouth(panelS);
        tg.addInput("input", ta);

        submit.addActionListener(e -> ic.submitButM());
        back.addActionListener(e -> ic.backBut());
    }

}

class RegularStrategy implements MarketInputPanelStrategy {

    public void solve(BorderGUIWithThreeTextArea tg, MarketController ic) {
        JPanel panelS = new JPanel();
        JTextArea ta = new JTextArea("Type item name here", 1, 10);
        JButton submit = new JButton("Submit");
        JButton back  = new JButton("Return");
        panelS.add(ta);
        panelS.add(submit);
        panelS.add(back);
        tg.setSouth(panelS);
        tg.addInput("input", ta);

        submit.addActionListener(e -> ic.submitButM());
        back.addActionListener(e -> ic.backBut());
    }

}
