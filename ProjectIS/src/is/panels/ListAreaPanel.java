package is.panels;

import is.dipendenti.Administrator;
import is.organigramma.Organigramma;
import is.organigramma.OrganigrammaIF;
import is.shapes.DataTable;
import is.shapes.ImageZoom;
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Iterator;

public class ListAreaPanel extends JPanel {
    private Administrator admin;
    public ListAreaPanel(Administrator admin){
        if (admin == null) throw new IllegalArgumentException("Dato non valido");
        this.admin=admin;

        setLayout(null);
        Color gray = new Color(230,230,230);
        setBounds(0,0,1000,1000);
        //Header
        JPanel headPanel = new JPanel(null); headPanel.setBackground(Color.white);
        headPanel.setBounds(0,0,1000,60);

        Font f = new Font("TimesNewRoman",Font.BOLD,23);
        JLabel head = new JLabel("Elenco aree organizzative");
        head.setFont(f); head.setForeground(Color.black); head.setBounds(10,7,380,50);
        headPanel.add(head);
        //Adding Fields
        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray);
        fieldPanel.setBounds(0,60,1000,950);

        Organigramma org = admin.getOrganigramma();
        String[] columnNames = {"Nome Area","Numero Dipendenti","Nome Area di Riferimento","Stato"};
        Object[][] data = new Object[admin.getAllAreas().size()][columnNames.length];

        data[0][0] = org.getName();
        data[0][1] = org.getNEmployees();
        data[0][2] = "Nessuna";
        data[0][3] = (org.getStateArea()==false) ? "BOZZA":"VALIDATA";

        Iterator<OrganigrammaIF> it = org.iterator();
        int i = 1;
        while(it.hasNext()){
            Organigramma cur = (Organigramma) it.next();
            data[i][0] = cur.getName();
            data[i][1] = cur.getNEmployees();
            Organigramma orgPar = admin.getParent(cur);
            data[i][2] = orgPar.getName();
            data[i][3] = (cur.getStateArea()==false) ? "BOZZA":"VALIDATA";
            i++;
        }


        //Table
        DataTable table = new DataTable(data, columnNames);
        //Dimension of column
        TableColumn column = null;
        for (int j = 0;j<4;j++){
            column = table.getColumnModel().getColumn(j);
            if (j==0) column.setPreferredWidth(220);
            if (j==1) column.setPreferredWidth(140);
            if (j==2) column.setPreferredWidth(220);
            if (j==3) column.setPreferredWidth(110);
        }

        //ScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15,50,955,200);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //Image
        ImageZoom icon = new ImageZoom(new ImageIcon(HomePanel.class.getResource("myLogo.png")),0.25);
        ImageIcon image = icon.getImageIcon();
        //Label
        JLabel lab = new JLabel(image);
        lab.setBounds(730,320,200,200);
        //ADDING
        fieldPanel.add(lab); fieldPanel.add(scrollPane);
        add(headPanel); add(fieldPanel);
    }
}
