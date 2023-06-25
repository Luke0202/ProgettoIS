package is.panels;

import is.DataTable;
import is.organigramma.Employee;
import is.organigramma.Role;
import is.mediator.Mediator;
import is.decorator.ImageZoom;
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.HashSet;

public class EmployeePanel extends JPanel {

    public EmployeePanel(Employee emp,Mediator mediator) {
        if (emp == null || mediator==null) throw new IllegalArgumentException("Dati non validi");

        setLayout(null);
        Color blue = new Color(3,2,179);
        Color blue2 = new Color(0,51,200);
        Color gray = new Color(230,230,230);
        setBounds(0,0,1000,1000);
        //Header
        JPanel headPanel = new JPanel(null); headPanel.setBackground(Color.white);
        headPanel.setBounds(0,0,1000,60);

        Font f = new Font("TimesNewRoman",Font.BOLD,23);
        JLabel head = new JLabel("Dettaglio dipendente");
        head.setFont(f); head.setForeground(Color.black); head.setBounds(10,7,350,50);
        headPanel.add(head);
        //Adding Fields
        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray);
        fieldPanel.setBounds(0,50,1000,950);
        f = new Font("TimesNewRoman",Font.ITALIC,20);
        //Name Field
        JLabel nameLab = new JLabel("Nome: ");
        nameLab.setFont(f); nameLab.setForeground(blue); nameLab.setBounds(20,15,200,30);
        JTextField nameField = new JTextField(20); nameField.setText(emp.getName()); nameField.setEditable(false);
        nameField.setBounds(20,50,280,30);
        //Surname Field
        JLabel surnameLab = new JLabel("Cognome: ");
        surnameLab.setFont(f); surnameLab.setForeground(blue); surnameLab.setBounds(350,15,200,30);

        JTextField surnameField = new JTextField(20); surnameField.setText(emp.getSurname()); surnameField.setEditable(false);
        surnameField.setBounds(350,50,280,30);
        //Email Label
        JLabel emailLab = new JLabel("Email: ");
        emailLab.setFont(f); emailLab.setForeground(blue); emailLab.setBounds(680,15,200,30);
        JTextField emailField = new JTextField(20); emailField.setText(emp.getEmail()); emailField.setEditable(false);
        emailField.setBounds(680,50,280,30);
        //ID Label
        JLabel IDLab = new JLabel("ID: ");
        IDLab.setFont(f); IDLab.setForeground(blue); IDLab.setBounds(20,100,200,30);
        JTextField IDField = new JTextField(20); IDField.setText(String.valueOf(emp.getID())); IDField.setEditable(false);
        IDField.setBounds(20,140,280,30);
        //Button
        JButton removeButton = new JButton("Elimina Dipendente");  removeButton.setForeground(Color.white);
        removeButton.setBackground(blue2); removeButton.setBounds(400,140,150,30);
        //Roles
        JLabel rolesLab = new JLabel("Ruoli: ");
        rolesLab.setFont(f); rolesLab.setForeground(blue); rolesLab.setBounds(20,180,200,30);

        HashSet<Role> roles = mediator.getAzienda().getRoles(emp);
        String[] columnNames = {"Nome","Area"};
        Object[][] data = new Object[roles.size()][columnNames.length];


        int i = 0;
        for(Role r:roles){
            data[i][0]=r.getName();
            data[i][1]=r.getArea();
            i++;
        }

        //Table
        DataTable table = new DataTable(data, columnNames);
        //Dimension of column

        TableColumn column = null;
        for (int j = 0;j<2;j++){
            column = table.getColumnModel().getColumn(j);
            if (j==0) column.setPreferredWidth(180);
            if (j==1) column.setPreferredWidth(240);
        }

        //ScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20,220,420,130);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //Image
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.25);
        ImageIcon image = icon.getImageIcon();
        //Label
        JLabel lab = new JLabel(image);
        lab.setBounds(730,320,200,200);

        //Adding
        fieldPanel.add(nameLab); fieldPanel.add(nameField);
        fieldPanel.add(surnameLab); fieldPanel.add(surnameField);
        fieldPanel.add(emailLab); fieldPanel.add(emailField);
        fieldPanel.add(IDLab); fieldPanel.add(IDField); fieldPanel.add(removeButton);
        fieldPanel.add(rolesLab); fieldPanel.add(scrollPane);
        fieldPanel.add(lab);
        add(headPanel); add(fieldPanel);

        //Mediator
        mediator.setIdEmployee(IDField);
        mediator.setRemoveEmployee(removeButton);
        removeButton.addActionListener(e->mediator.widgetChanged(removeButton));
    }
}
