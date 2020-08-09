package Trade;

import User.Entity.ClientUser;
import User.UseCase.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AcceptTradeGUIBuilder implements BorderGUIBuilder {
    private final BorderGUIWithThreeTextArea tg;
    AcceptTradeController atc;
    JFrame tf;

    public AcceptTradeGUIBuilder(ClientUser currUser, TradeManager tm, UserManager um, JFrame tf){
        tg = new BorderGUIWithThreeTextArea();
        atc = new AcceptTradeController(currUser, tm, um, tg);
        this.tf = tf;
    }

    @Override
    public void buildFrame(){
        tg.setFrame(800, 400, "Accept Trade Session");

    }

    @Override
    public void buildPanelN() {
        JPanel panelN = new JPanel();
        JLabel msg = new JLabel("message:", SwingConstants.LEFT);
        JTextArea msgArea = new JTextArea();
        panelN.add(msg);
        panelN.add(msgArea);
        tg.initializeMsg(msgArea);
        tg.setNorth(panelN);

    }

    @Override
    public void buildPanelE() {
        JPanel panelE = new JPanel();
        JButton agree = new JButton("agree");
        JButton refuse = new JButton("refuse");
        panelE.setLayout(new BoxLayout(panelE, BoxLayout.Y_AXIS));
        panelE.add(agree);
        panelE.add(refuse);
        tg.setEast(panelE);
        agree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atc.agreeBut(true);
            }
        });
        refuse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atc.agreeBut(false);
            }
        });
    }

    @Override
    public void buildPanelW() {
        JPanel panelW =  new JPanel();
        JLabel tradeList = new JLabel("Available Trades");
        JTextArea tradeArea = new JTextArea();
        tg.initializeList(tradeArea);
        atc.updateBut();
        JScrollPane jsp= new JScrollPane(tradeArea);
        panelW.setLayout(new BoxLayout(panelW, BoxLayout.Y_AXIS));
        panelW.add(tradeList);
        panelW.add(jsp);
        panelW.setPreferredSize(new Dimension(380,370));
        tg.setWest(panelW);
    }

    @Override
    public void buildPanelS() {
        JPanel panelS =  new JPanel();
        JLabel input = new JLabel("input trade number");
        JTextArea inputArea = new JTextArea("trade number");
        JButton submit = new JButton("submit");
        JButton back = new JButton("return to trade menu");
        JButton update = new JButton("update");
        panelS.add(input);
        panelS.add(inputArea);
        panelS.add(submit);
        panelS.add(back);
        panelS.add(update);
        tg.setInputStr("trade number");
        tg.initializeInput(inputArea);
        tg.setSouth(panelS);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tradeNum = tg.getInput();
                System.out.println(tradeNum);
                atc.submitBut(tradeNum);
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atc.backBut(tf);
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atc.updateBut();
            }
        });
    }

    @Override
    public void buildPanelC() {
        JPanel panelC =  new JPanel();
        JLabel currTradeL = new JLabel("Trade selected");
        JTextArea currArea = new JTextArea();
        panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));
        panelC.add(currTradeL);
        panelC.add(currArea);
        tg.setCenter(panelC);
        tg.initializeCurr(currArea);
        atc.noTradeSelected();

    }

    public BorderGUIWithThreeTextArea getTradeGUI(){
        return tg;
    }
}
