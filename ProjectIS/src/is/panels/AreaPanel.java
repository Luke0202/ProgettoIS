package is.panels;

import is.organigramma.Azienda;
import is.organigramma.Employee;
import is.mediator.Mediator;
import is.organigramma.Organigramma;
import is.organigramma.OrganigrammaIF;
import is.decorator.DataTable;
import is.decorator.ImageZoom;
import javax.swing.*;
import java.awt.*;

public class AreaPanel extends JPanel {

    public AreaPanel(Organigramma org,Mediator mediator) {
        if (org == null || mediator==null) throw new IllegalArgumentException("Dati non validi");

        Azienda azienda = mediator.getAzienda();

        setLayout(null);
        Color blue = new Color(3,2,179);
        Color blue2 = new Color(0,51,200);
        Color gray = new Color(230,230,230);
        setBounds(0,0,1000,1000);
        //Header
        JPanel headPanel = new JPanel(null); headPanel.setBackground(Color.white);
        headPanel.setBounds(0,0,1000,60);

        Font f = new Font("TimesNewRoman",Font.BOLD,23);
        JLabel head = new JLabel("Dettaglio area organizzativa");
        head.setFont(f); head.setForeground(Color.black); head.setBounds(10,7,350,50);
        headPanel.add(head);
        //Adding Fields
        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray);
        fieldPanel.setBounds(0,50,1000,950);
        f = new Font("TimesNewRoman",Font.ITALIC,20);
        //Name Field
        JLabel nameLab = new JLabel("Nome area: ");
        nameLab.setFont(f); nameLab.setForeground(blue); nameLab.setBounds(20,15,200,30);
        JTextField nameField = new JTextField(20); nameField.setText(org.getName()); nameField.setEditable(false);
        nameField.setBounds(20,50,280,30);
        //DadLabel
        JLabel dadLab = new JLabel("Nome area di riferimento: ");
        dadLab.setFont(f); dadLab.setForeground(blue); dadLab.setBounds(350,15,280,30);

        JTextField dadField = new JTextField(20); dadField.setText(findDadArea(azienda,org)); dadField.setEditable(false);
        dadField.setBounds(350,50,200,30);
        //StateLabel
        JLabel stateLab = new JLabel("Stato: ");
        stateLab.setFont(f); stateLab.setForeground(blue); stateLab.setBounds(680,15,200,30);
        JLabel stateLab2 = new JLabel((!org.getStateArea()) ? "BOZZA":"VALIDATA");
        stateLab2.setBounds(680,50,280,30);
        //Button
        JButton modButton = new JButton("Modifica area");  modButton.setForeground(Color.white);
        modButton.setBackground(blue2); modButton.setBounds(810,30,150,30);
        modButton.setEnabled(!org.getStateArea());
        JButton removeButton = new JButton("Elimina area");  removeButton.setForeground(Color.white);
        removeButton.setEnabled(!org.getStateArea() && !dadField.getText().equals("Nessuna") && isRemovable(org));
        removeButton.setBackground(blue2); removeButton.setBounds(810,70,150,30);
        //DescriptionArea
        JLabel descrLab = new JLabel("Descrizione: ");
        descrLab.setFont(f); descrLab.setForeground(blue); descrLab.setBounds(20,100,200,30);
        JTextArea descrArea = new JTextArea(5,20); descrArea.setText(org.getDescription()); descrArea.setEditable(false);
        descrArea.setLineWrap(true);
        JScrollPane descrScroll = new JScrollPane(descrArea);
        descrScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        descrScroll.setBounds(20,135,940,150);
        //EmployeesArea
        JLabel empLab = new JLabel("Dipendenti: ");
        empLab.setFont(f); empLab.setForeground(blue); empLab.setBounds(20,315,200,30);
        //Table
        String[] columnNames = {"Cognome","Nome","Email","ID"};

        Object[][] data = new Object[org.getNEmployees()][columnNames.length];

        int i = 0;
        for (Employee e:azienda.getEmployees()){
            if (org.containsID(e.getID())){
                data[i][0] = e.getSurname();
                data[i][1] = e.getName();
                data[i][2] = e.getEmail();
                data[i][3] = e.getID();
                i++;
            }
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
        fieldPanel.add(nameLab); fieldPanel.add(nameField);
        fieldPanel.add(dadLab); fieldPanel.add(dadField);
        fieldPanel.add(stateLab); fieldPanel.add(stateLab2);
        fieldPanel.add(modButton); fieldPanel.add(removeButton);
        fieldPanel.add(descrLab); fieldPanel.add(descrScroll);
        fieldPanel.add(empLab); fieldPanel.add(scrollPane);
        fieldPanel.add(lab);
        add(fieldPanel); add(headPanel);
        //Mediator
        mediator.setNameArea(nameField);
        mediator.setRemoveArea(removeButton);
        mediator.setEditArea(modButton);
        modButton.addActionListener(e->mediator.buttonChanged(modButton));
        removeButton.addActionListener(e->mediator.buttonChanged(removeButton));
    }
    private String findDadArea(Azienda azienda,Organigramma org){
        Organigramma organigramma = azienda.getOrganigramma();

        if (organigramma.getName().equals(org.getName()))
            return "Nessuna";

        return azienda.getParent(org).getName();
    }
    private boolean isRemovable(Organigramma org){
        for (OrganigrammaIF o:org){
            if (o.getNEmployees()>0) return false;
        }
        return true;
    }
}
