package is.decorator;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public abstract class TableDecorator extends JTable {
    protected JTable table;

    @Override
    public abstract boolean isCellEditable(int d,int r);
    @Override
    public abstract Component prepareRenderer(TableCellRenderer r, int row, int columns);
}
