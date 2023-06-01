package is.panels;

import is.dipendenti.Administrator;
import is.mediator.Mediator;
import is.shapes.ImageZoom;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class CreateRolePanel extends JPanel {
    public CreateRolePanel(Mediator mediator) {
        if (mediator==null) throw new IllegalArgumentException("Mediator non valido");

        setLayout(null);
        Color blue = new Color(3,2,179);
        Color blue2 = new Color(0,51,200);
        Color gray = new Color(230,230,230);
        setBounds(0,0,1000,1000);
        //Header
        JPanel headPanel = new JPanel(null); headPanel.setBackground(Color.white);
        headPanel.setBounds(0,0,1000,60); add(headPanel);

        Font f = new Font("TimesNewRoman",Font.BOLD,23);
        JLabel head = new JLabel("Inserimento nuovo Ruolo");
        head.setFont(f); head.setForeground(Color.black); head.setBounds(10,7,350,50);
        headPanel.add(head);
        //Adding Fields
        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray);
        fieldPanel.setBounds(0,50,1000,950);
        f = new Font("TimesNewRoman",Font.ITALIC,20);
        //Name Role
        JLabel nameLab = new JLabel("Nome Ruolo: ");
        nameLab.setFont(f); nameLab.setForeground(blue); nameLab.setBounds(20,15,200,30);
        JTextField nameField = new JTextField(20); nameField.setText("Digita nome ruolo");
        nameField.setBounds(20,50,280,30);
        //Name Area
        JLabel areaLab = new JLabel("Scegli Area: ");
        areaLab.setFont(f); areaLab.setForeground(blue); areaLab.setBounds(350,15,200,30);

        String[] array = findAreas(mediator.getAzienda().getAdmin());
        JComboBox<String> areaComboBox = new JComboBox<>(array);
        areaComboBox.setBounds(350,50,280,30);
        //StateLabel
        JLabel stateLab = new JLabel("Stato: ");
        stateLab.setFont(f); stateLab.setForeground(blue); stateLab.setBounds(680,15,200,30);
        JLabel stateLab2 = new JLabel("BOZZA");
        stateLab2.setBounds(680,50,280,30);
        //DescriptionArea
        JLabel descrLab = new JLabel("Descrizione Ruolo: ");
        descrLab.setFont(f); descrLab.setForeground(blue); descrLab.setBounds(20,100,200,30);
        JTextArea descrArea = new JTextArea(5,20); descrArea.setText("Digita descrizione");
        descrArea.setLineWrap(true);
        JScrollPane descrScroll = new JScrollPane(descrArea);//Controllo per vedere se è gia presente una descrizione per tale ruolo
        descrScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        descrScroll.setBounds(20,135,940,150);
        //SaveButtons
        JButton saveB = new JButton("SALVA IN BOZZA");  saveB.setForeground(Color.white); saveB.setBackground(blue2);
        saveB.setBounds(20,350,200,30);
        JButton saveV = new JButton("SALVA E VALIDA");  saveV.setForeground(Color.white); saveV.setBackground(blue2);
        saveV.setBounds(350,350,200,30);
        //Image
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.25);
        ImageIcon image = icon.getImageIcon();
        //Label
        JLabel lab = new JLabel(image);
        lab.setBounds(730,320,200,200);

        //Adding
        add(fieldPanel);
        fieldPanel.add(nameLab); fieldPanel.add(nameField);
        fieldPanel.add(areaLab); fieldPanel.add(areaComboBox);
        fieldPanel.add(stateLab); fieldPanel.add(stateLab2);
        fieldPanel.add(descrLab); fieldPanel.add(descrScroll);
        fieldPanel.add(saveB); fieldPanel.add(saveV);
        fieldPanel.add(lab);

        //Using mediator
        mediator.setNameCreateRole(nameField);
        mediator.setAreaCreateRole(areaComboBox);
        mediator.setDescrCreateRole(descrArea);
        mediator.setSaveBCreateRole(saveB);
        mediator.setSaveVCreateRole(saveV);
        saveB.addActionListener(e -> mediator.buttonChanged(saveB));
        saveV.addActionListener(e -> mediator.buttonChanged(saveV));
    }
    private String[] findAreas(Administrator admin){

        HashSet<String> areas = admin.getAllAreas();

        String[] array;
        if (areas.size()==0){
            array = new String[1];
            array[0] = "Nessuna Area";
        } else{
            array = areas.toArray(new String[areas.size()]);
        }
        return array;
    }
}
