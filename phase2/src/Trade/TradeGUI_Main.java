package Trade;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class TradeGUI_Main {
    String currUser;
    JFrame cf;


    /**
     * constructor
     * @param currUser the user that is using the system
     */
    public TradeGUI_Main(String currUser, JFrame cf){
        this.currUser = currUser;
        this.cf = cf;
    }

    public void run(){
        JFrame frame = new JFrame("Select Trade Session");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 250);

        JPanel menu = new JPanel();
        JLabel lb = new JLabel("    Hello, "+ currUser);
        JButton rt = new JButton("request trade");
        JButton ct = new JButton("complete trade");
        JButton at = new JButton("agree/refuse requested trade");
        JButton th = new JButton("view trade history(also frequent traders)");
        JButton re = new JButton("return to login menu");

        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.add(lb);
        menu.add(rt);
        menu.add(at);
        menu.add(ct);
        menu.add(th);
        menu.add(re);

        frame.getContentPane().add(menu);
        frame.setVisible(true);

        rt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BorderGUIBuilder builder = new SelectItemToTradeBuilder(currUser, frame);
                TradeGUIEngineer engineer = new TradeGUIEngineer(builder);
                engineer.constructGUI();
                TradeGUIPlan tg = engineer.getGUI();
                tg.run();
            }
        });

        at.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BorderGUIBuilder builder = new AcceptTradeGUIBuilder(currUser, frame);
                TradeGUIEngineer engineer = new TradeGUIEngineer(builder);
                engineer.constructGUI();
                TradeGUIPlan tg = engineer.getGUI();
                tg.run();

            }
        });

        ct.addActionListener(new ActionListener() {
            @Override
            //to do
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BorderGUIBuilder builder = new CompleteTradeGUIBuilder(currUser, frame);
                TradeGUIEngineer engineer = new TradeGUIEngineer(builder);
                engineer.constructGUI();
                TradeGUIPlan tg = engineer.getGUI();
                tg.run();
            }
        });

        th.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BorderGUIBuilder builder = new TradeHistoryGUIBuilder(currUser, frame);
                TradeGUIEngineer engineer = new TradeGUIEngineer(builder);
                engineer.constructGUI();
                TradeGUIPlan tg = engineer.getGUI();
                tg.run();
            }
        });

        re.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                cf.setVisible(true);
            }
        });








        
    }








}
