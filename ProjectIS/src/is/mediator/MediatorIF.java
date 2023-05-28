package is.mediator;

import javax.swing.*;

public interface MediatorIF {
    void menuChanged(JMenuItem widget);
    void textChanged(JTextField widget);
    void buttonChanged(JButton widget);
    void boxComboChanged(JComboBox widget);
}
