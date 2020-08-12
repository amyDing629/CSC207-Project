package Trade.Adaptor.RequestTrade;

import Trade.Adaptor.BorderGUIBuilder;
import Trade.Adaptor.BorderGUINoTextArea;
import Trade.Adaptor.BorderGUI;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.UUID;

public class RTradeGUIBuilder implements BorderGUIBuilder {


    private final RTradeController trc;
    private final BorderGUI tg;
    private final JFrame fr;

    /**
     * [constructor]
     *
     * @param currUser current user
     * @param item     item selected by the current user
     */
    public RTradeGUIBuilder(UUID currUser, String item, JFrame fr) {


        tg = new BorderGUI();
        trc = new RTradeController(currUser, tg, item);
        trc.getTarUser(item);
        this.fr = fr;
    }

    @Override
    public void buildFrame() {
        tg.setFrame(800, 200, "Request Trade Session");
    }

    @Override
    public void buildPanelN() {
        JPanel msg = new JPanel();
        JLabel msgl = new JLabel("Message:", SwingConstants.LEFT);
        msg.add(msgl);
        JTextArea jtz = new JTextArea();
        msg.add(jtz);
        tg.setNorth(msg);
        tg.initializeMsg(jtz);
    }

    @Override
    public void buildPanelE() {
        JPanel panelRight = new JPanel();
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Input second item name here " +
                "for a two way trade", SwingConstants.LEFT);
        JTextArea ta = new JTextArea("Type item name here");
        panelRight.add(label);
        panelRight.add(ta);
        tg.setEast(panelRight);
        tg.addInput("Input", ta);
    }

    @Override
    public void buildPanelW() {
    }

    @Override
    public void buildPanelS() {
        JPanel panelS =  new JPanel();
        JButton onewayTemp = new JButton("One way-Temporary");
        JButton onewayPer = new JButton("One way-Permanent");
        JButton twowayTemp = new JButton("Two way-Temporary");
        JButton twowayPer = new JButton("Two way-Permanent");
        JButton back = new JButton("Back");
        panelS.add(onewayTemp);
        panelS.add(onewayPer);
        panelS.add(twowayTemp);
        panelS.add(twowayPer);
        panelS.add(back);
        tg.setSouth(panelS);
        onewayTemp.addActionListener(e -> {
            try {
                trc.onewayBut("temp");
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        onewayPer.addActionListener(e -> {
            try {
                trc.onewayBut("per");
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        twowayTemp.addActionListener(e -> {
            try {
                trc.twowayBut("temp");
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        twowayPer.addActionListener(e -> {
            try {
                trc.twowayBut("per");
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        back.addActionListener(e -> trc.backBut(fr));




    }

    @Override
    public void buildPanelC() {
        JTextArea tradeInfo = new JTextArea();
        JPanel panel = new JPanel();
        panel.add(tradeInfo);
        tradeInfo.setPreferredSize(new Dimension(400, 170));
        tg.setCenter(panel);
        tg.initializeCurr(tradeInfo);
        trc.presentTradeInfo();
    }

    @Override
    public BorderGUI getTradeGUI() {
        return tg;
    }
}
