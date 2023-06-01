package is.panels;

import is.dipendenti.Administrator;
import is.dipendenti.Employee;
import is.mediator.Mediator;
import is.organigramma.Organigramma;
import is.shapes.DataTable;
import is.shapes.ImageZoom;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class ListEmployeePanel extends JPanel {
    private Mediator mediator;
    public ListEmployeePanel(Mediator mediator){
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
        JLabel head = new JLabel("Elenco dipendenti");
        head.setFont(f); head.setForeground(Color.black); head.setBounds(10,7,380,50);
        headPanel.add(head);
        //Adding Fields
        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray);
        fieldPanel.setBounds(0,60,1000,950);

        Organigramma org = admin.getOrganigramma();
        String[] columnNames = {"Cognome","Nome","Email","ID","Numero ruoli","Numero Aree"};
        Object[][] data = new Object[admin.getEmployees().size()][columnNames.length];

        int i = 0;
        for (Employee emp: admin.getEmployees()){
            data[i][0] = emp.getSurname();
            data[i][1] = emp.getName();
            data[i][2] = emp.getEmail();
            data[i][3] = emp.getID();
            data[i][4] = admin.getRoles(emp).size();
            data[i][5] = admin.getAreas(emp).size();
            i++;
        }

        //Table
        DataTable table = new DataTable(data, columnNames);
        //Dimension of column
        TableColumn column = null;
        for (int j = 0;j<6;j++){
            column = table.getColumnModel().getColumn(j);
            if (j==0) column.setPreferredWidth(180);
            if (j==1) column.setPreferredWidth(150);
            if (j==2) column.setPreferredWidth(215);
            if (j==3) column.setPreferredWidth(100);
            if (j==4) column.setPreferredWidth(90);
            if (j==5) column.setPreferredWidth(90);
        }

        //ScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15,50,955,200);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //Search
        JLabel searchLab = new JLabel("Cerca dipendente: ");
        searchLab.setFont(f); searchLab.setForeground(blue); searchLab.setBounds(20,290,280,30);

        JTextField idField = new JTextField(20); idField.setText("Digita ID dipendente");
        idField.setBounds(20,330,280,30);

        JButton search = new JButton("Cerca");  search.setForeground(Color.white); search.setBackground(blue2);
        search.setBounds(340,330,140,30);
        //Image
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.25);
        ImageIcon image = icon.getImageIcon();
        //Label
        JLabel lab = new JLabel(image);
        lab.setBounds(730,320,200,200);
        //ADDING
        fieldPanel.add(scrollPane); fieldPanel.add(lab);
        fieldPanel.add(searchLab); fieldPanel.add(idField); fieldPanel.add(search);
        add(fieldPanel); add(headPanel);
        //Mediator
        mediator.setIdListEmployee(idField);
        mediator.setSearchListEmployee(search);
        search.addActionListener(e->mediator.buttonChanged(search));
    }
}
