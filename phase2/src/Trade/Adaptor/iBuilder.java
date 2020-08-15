package Trade.Adaptor;

import javax.swing.*;

public interface iBuilder {
    void addInput(String key, JTextArea input);
    void initializeList(JTextArea ta);
    void initializeCurr(JTextArea ta);
    void initializeMsg(JTextArea ta);
}
