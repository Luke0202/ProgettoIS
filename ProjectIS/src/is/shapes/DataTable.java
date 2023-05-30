package is.shapes;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class DataTable extends JTable {
    public DataTable(Object[][] d,String[] col){super(d,col); super.setFillsViewportHeight(true); super.setForeground(Color.black);}
    @Override
    public boolean isCellEditable(int d,int r){
        return false;
    }
    @Override
    public Component prepareRenderer(TableCellRenderer r, int row, int columns){
        Component c = super.prepareRenderer(r,row,columns);

        if (row%2==0) c.setBackground(Color.white);
        else c.setBackground(Color.lightGray);

        if (isCellSelected(row,columns))
            c.setBackground(new Color(0,240,0));
        return c;
    }
}