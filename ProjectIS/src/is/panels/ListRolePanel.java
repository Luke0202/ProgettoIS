package is.panels;

import is.dipendenti.Administrator;
import is.dipendenti.Role;
import is.organigramma.Organigramma;
import is.shapes.DataTable;
import is.shapes.ImageZoom;
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class ListRolePanel extends JPanel {
    private Administrator admin;
    public ListRolePanel(Administrator admin){
        if (admin == null) throw new IllegalArgumentException("Dato non valido");
        this.admin=admin;

        setLayout(null);
        Color gray = new Color(230,230,230);
        setBounds(0,0,1000,1000);
        //Header
        JPanel headPanel = new JPanel(null); headPanel.setBackground(Color.white);
        headPanel.setBounds(0,0,1000,60);

        Font f = new Font("TimesNewRoman",Font.BOLD,23);
        JLabel head = new JLabel("Elenco ruoli");
        head.setFont(f); head.setForeground(Color.black); head.setBounds(10,7,380,50);
        headPanel.add(head);
        //Adding Fields
        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray);
        fieldPanel.setBounds(0,60,1000,950);

        Organigramma org = admin.getOrganigramma();
        String[] columnNames = {"Nome Ruolo","Nome Area","Numero Dipendenti","Stato"};
        Object[][] data = new Object[admin.getRoles().size()][columnNames.length];

        int i = 0;
        for(Role r: admin.getRoles()){
            data[i][0]=r.getName();
            data[i][1]=r.getArea();
            data[i][2]=admin.getOrganigramma().getEmployees(r).size();
            data[i][3]=(r.getStateRole()==false) ? "BOZZA":"VALIDATA";
            i++;
        }


        //Table
        DataTable table = new DataTable(data, columnNames);
        //Dimension of column
        TableColumn column = null;
        for (int j = 0;j<4;j++){
            column = table.getColumnModel().getColumn(j);
            if (j==0) column.setPreferredWidth(200);
            if (j==1) column.setPreferredWidth(220);
            if (j==2) column.setPreferredWidth(140);
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
