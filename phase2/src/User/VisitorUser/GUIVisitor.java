package User.VisitorUser;

import java.awt.*;

public interface GUIVisitor {

    void visit(Container e);

    void visit(Panel e);

    void visit(Label e);

}
