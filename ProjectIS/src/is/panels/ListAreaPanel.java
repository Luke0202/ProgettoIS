package is.panels;

import is.DataTable;
import is.mediator.Mediator;
import is.organigramma.Area;
import is.organigramma.Azienda;
import is.organigramma.Organigramma;
import is.decorator.ImageZoom;
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Iterator;

public class ListAreaPanel extends JPanel {
    public ListAreaPanel(Mediator mediator){
        if (mediator==null) throw new IllegalArgumentException("Mediator non valido");

        Azienda azienda = mediator.getAzienda();

        setLayout(null);
        Color gray = new Color(230,230,230);
        Color blue = new Color(3, 2, 179);
        Color blue2 = new Color(0,51,200);
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

        Organigramma org = azienda.getOrganigramma();
        String[] columnNames = {"Nome Area","Numero Dipendenti","Nome Area di Riferimento","Stato"};
        Object[][] data = new Object[azienda.getNAreas()][columnNames.length];

        Iterator<Area> it = org.iterator();
        int i = 0;
        while(it.hasNext()){
            Organigramma cur = (Organigramma) it.next();
            data[i][0] = cur.getName();
            data[i][1] = cur.getNEmployees();
            Organigramma orgPar = azienda.getParent(cur);
            data[i][2] = (orgPar==null) ? "Nessuna":orgPar.getName();
            data[i][3] = (!cur.getStateArea()) ? "BOZZA":"VALIDATA";
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

        //Search
        JLabel searchLab = new JLabel("Cerca area: ");
        searchLab.setFont(f); searchLab.setForeground(blue); searchLab.setBounds(20,290,200,30);

        JTextField nameField = new JTextField(20); nameField.setText("Digita nome area");
        nameField.setBounds(20,330,280,30);

        JButton search = new JButton("Cerca");  search.setForeground(Color.white); search.setBackground(blue2);
        search.setBounds(340,330,140,30);
        //Image
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.25);
        ImageIcon image = icon.getImageIcon();
        //Label
        JLabel lab = new JLabel(image);
        lab.setBounds(730,320,200,200);
        //ADDING
        fieldPanel.add(lab); fieldPanel.add(scrollPane);
        fieldPanel.add(searchLab); fieldPanel.add(nameField); fieldPanel.add(search);
        add(headPanel); add(fieldPanel);
        //Mediator
        mediator.setNameListArea(nameField);
        mediator.setSearchListArea(search);
        search.addActionListener(e->mediator.widgetChanged(search));
    }
}
