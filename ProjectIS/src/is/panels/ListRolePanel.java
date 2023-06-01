package is.panels;

import is.dipendenti.Administrator;
import is.dipendenti.Role;
import is.mediator.Mediator;
import is.organigramma.Organigramma;
import is.shapes.DataTable;
import is.shapes.ImageZoom;
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class ListRolePanel extends JPanel {
    private Mediator mediator;
    public ListRolePanel(Mediator mediator){
        if (mediator==null) throw new IllegalArgumentException("Mediator non valido");
        this.mediator=mediator;

        Administrator admin = mediator.getAzienda().getAdmin();

        setLayout(null);
        Color blue = new Color(3, 2, 179);
        Color blue2 = new Color(0,51,200);
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
            data[i][3]=(!r.getStateRole()) ? "BOZZA":"VALIDATA";
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
        //Search
        JLabel searchLab = new JLabel("Cerca ruolo: ");
        searchLab.setFont(f); searchLab.setForeground(blue); searchLab.setBounds(20,290,200,30);

        JTextField nameField = new JTextField(20); nameField.setText("Digita nome ruolo");
        nameField.setBounds(20,330,280,30);
        JTextField areaField = new JTextField(20); areaField.setText("Digita nome area di riferimento");
        areaField.setBounds(20,375,280,30);

        JButton search = new JButton("Cerca");  search.setForeground(Color.white); search.setBackground(blue2);
        search.setBounds(330,345,140,30);
        //Image
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.25);
        ImageIcon image = icon.getImageIcon();
        //Label
        JLabel lab = new JLabel(image);
        lab.setBounds(730,320,200,200);
        //ADDING
        fieldPanel.add(lab); fieldPanel.add(scrollPane);
        fieldPanel.add(searchLab); fieldPanel.add(nameField); fieldPanel.add(areaField); fieldPanel.add(search);
        add(headPanel); add(fieldPanel);
        //Mediator
        mediator.setNameListRole(nameField);
        mediator.setAreaListRole(areaField);
        mediator.setSearchListRole(search);
        nameField.addActionListener(e->mediator.textChanged(nameField));
        areaField.addActionListener(e->mediator.textChanged(areaField));
        search.addActionListener(e->mediator.buttonChanged(search));
    }
}
