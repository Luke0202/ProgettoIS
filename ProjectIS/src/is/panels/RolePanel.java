package is.panels;

import is.organigramma.*;
import is.mediator.Mediator;
import is.decorator.DataTable;
import is.decorator.ImageZoom;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class RolePanel extends JPanel {
    public RolePanel(Role role,Mediator mediator) {
        if (role == null || mediator==null) throw new IllegalArgumentException("Dati non validi");

        Azienda azienda = mediator.getAzienda();

        setLayout(null);
        Color blue = new Color(3,2,179);
        Color blue2 = new Color(0,51,200);
        Color gray = new Color(230,230,230);
        setBounds(0,0,1000,1000);
        //Header
        JPanel headPanel = new JPanel(null); headPanel.setBackground(Color.white);
        headPanel.setBounds(0,0,1000,60); add(headPanel);

        Font f = new Font("TimesNewRoman",Font.BOLD,23);
        JLabel head = new JLabel("Dettaglio ruolo");
        head.setFont(f); head.setForeground(Color.black); head.setBounds(10,7,350,50);
        headPanel.add(head);
        //Adding Fields
        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray);
        fieldPanel.setBounds(0,50,1000,950);
        f = new Font("TimesNewRoman",Font.ITALIC,20);
        //Name Field
        JLabel nameLab = new JLabel("Nome Ruolo: ");
        nameLab.setFont(f); nameLab.setForeground(blue); nameLab.setBounds(20,15,200,30);
        JTextField nameField = new JTextField(20); nameField.setText(role.getName()); nameField.setEditable(false);
        nameField.setBounds(20,50,280,30);
        //DadLabel
        JLabel areaLab = new JLabel("Nome Area: ");
        areaLab.setFont(f); areaLab.setForeground(blue); areaLab.setBounds(350,15,200,30);

        JTextField areaField = new JTextField(20); areaField.setText(role.getArea()); areaField.setEditable(false);
        areaField.setBounds(350,50,200,30);
        //StateLabel
        JLabel stateLab = new JLabel("Stato: ");
        stateLab.setFont(f); stateLab.setForeground(blue); stateLab.setBounds(680,15,200,30);
        JLabel stateLab2 = new JLabel((!role.getStateRole()) ? "BOZZA":"VALIDATA");
        stateLab2.setBounds(680,50,280,30);
        //Button
        JButton modButton = new JButton("Modifica ruolo");  modButton.setForeground(Color.white);
        modButton.setBackground(blue2); modButton.setBounds(810,30,150,30);
        modButton.setEnabled(!role.getStateRole());
        JButton removeButton = new JButton("Elimina ruolo");  removeButton.setForeground(Color.white);
        removeButton.setEnabled(isRemovable(role,azienda));
        removeButton.setBackground(blue2); removeButton.setBounds(810,70,150,30);
        //DescriptionArea
        JLabel descrLab = new JLabel("Descrizione: ");
        descrLab.setFont(f); descrLab.setForeground(blue); descrLab.setBounds(20,100,200,30);
        JTextArea descrArea = new JTextArea(5,20); descrArea.setText(role.getDescription()); descrArea.setEditable(false);
        descrArea.setLineWrap(true);
        JScrollPane descrScroll = new JScrollPane(descrArea);
        descrScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        descrScroll.setBounds(20,135,940,150);
        //EmployeesArea
        JLabel empLab = new JLabel("Dipendendi: ");
        empLab.setFont(f); empLab.setForeground(blue); empLab.setBounds(20,315,200,30);
        //Table
        HashSet<Employee> employees = azienda.getEmployee(role);
        String[] columnNames = {"ID","Nome","Cognome","Email"};

        Object[][] data = new Object[employees.size()][columnNames.length];

        int i = 0;
        for (Employee e:employees){
            data[i][0] = e.getID();
            data[i][1] = e.getName();
            data[i][2] = e.getSurname();
            data[i][3] = e.getEmail();
            i++;
        }

        //Table
        DataTable table = new DataTable(data, columnNames);

        //ScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20,350,700,150);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //Image
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.25);
        ImageIcon image = icon.getImageIcon();
        //Label
        JLabel lab = new JLabel(image);
        lab.setBounds(730,320,200,200);

        //Adding
        add(fieldPanel);
        fieldPanel.add(nameLab); fieldPanel.add(nameField);
        fieldPanel.add(areaLab); fieldPanel.add(areaField);
        fieldPanel.add(stateLab); fieldPanel.add(stateLab2);
        fieldPanel.add(modButton); fieldPanel.add(removeButton);
        fieldPanel.add(descrLab); fieldPanel.add(descrScroll);
        fieldPanel.add(empLab); fieldPanel.add(scrollPane);
        fieldPanel.add(lab);

        //Mediator
        mediator.setNameRole(nameField);
        mediator.setAreaRole(areaField);
        mediator.setEditRole(modButton);
        mediator.setRemoveRole(removeButton);
        modButton.addActionListener(e->mediator.buttonChanged(modButton));
        removeButton.addActionListener(e->mediator.buttonChanged(removeButton));
    }
    private boolean isRemovable(Role role,Azienda azienda){
        //Un ruolo è rimuovibile se non ha alcun dipendente associato

        for (OrganigrammaIF o:azienda.getOrganigramma()){
            for (Couple c:((Organigramma)o).getCouples()){
                if (c.getRole().equals(role)) return false;
            }
        }
        return true;
    }
}