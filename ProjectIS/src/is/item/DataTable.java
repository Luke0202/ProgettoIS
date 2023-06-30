package is.item;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Tale classe estende JTable. È possibile definire tabelle
 * non modificabili nel contenuto, che presentano colorazioni
 * differenti delle righe in base all'indice di riga e all'interazione
 * con l'utente.
 * @author lucab
 */
public class DataTable extends JTable {
    public DataTable(Object[][] d,String[] col){
        super(d,col); super.setFillsViewportHeight(true); super.setForeground(Color.black);
    }

    /**
     * La cella alla riga d e alla colonna r non è modificabile.
     * @param d indice di riga della tabella
     * @param r indice di colonna della tabella
     * @return false
     */
    @Override
    public boolean isCellEditable(int d,int r){
        return false;
    }

    /**
     * Tale metodo ha la funzione di cambiare la colorazione delle righe di una tabella,
     * in base all'indice di riga e all'interazione con l'utente.
     * @param r renderer
     * @param row indice di riga della tabella
     * @param columns indice di colonna della tabella
     * @return Component
     */
    @Override
    public Component prepareRenderer(TableCellRenderer r, int row, int columns){
        Component c = super.prepareRenderer(r,row,columns);

        //Conferire due tipologie di colorazione in base alla riga
        if (row%2==0) c.setBackground(Color.white);
        else c.setBackground(Color.lightGray);

        //Selezionare una riga comporta un cambiamento della colorazione
        if (isCellSelected(row,columns))
            c.setBackground(new Color(0,240,0));//Green
        return c;
    }
}//DataTable
