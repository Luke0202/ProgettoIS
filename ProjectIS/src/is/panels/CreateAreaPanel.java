package is.panels;

import is.dipendenti.Administrator;
import is.mediator.Mediator;
import is.organigramma.Organigramma;
import is.shapes.ImageZoom;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class CreateAreaPanel extends JPanel {
    private Mediator mediator;
    private Administrator admin;
    public CreateAreaPanel(Mediator mediator, Administrator admin) {
        if (mediator == null || admin == null) throw new IllegalArgumentException("Dati non validi");
        this.mediator = mediator;
        this.admin = admin;

        setLayout(null);
        Color blue = new Color(3,2,179);
        Color blue2 = new Color(0,51,200);
        Color gray = new Color(230,230,230);
        setBounds(0,0,1000,1000);
        //Header
        JPanel headPanel = new JPanel(null); headPanel.setBackground(Color.white);
        headPanel.setBounds(0,0,1000,60);

        Font f = new Font("TimesNewRoman",Font.BOLD,23);
        JLabel head = new JLabel("Creazione nuova area organizzativa");
        head.setFont(f); head.setForeground(Color.black); head.setBounds(10,7,380,50);
        headPanel.add(head);
        //Adding Fields
        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray);
        fieldPanel.setBounds(0,50,1000,950);
        f = new Font("TimesNewRoman",Font.ITALIC,20);
        //Name Field
        JLabel nameLab = new JLabel("Nome Area: ");
        nameLab.setFont(f); nameLab.setForeground(blue); nameLab.setBounds(20,15,200,30);
        JTextField nameField = new JTextField(20); nameField.setText("Digita nome area");
        nameField.setBounds(20,50,280,30);
        //DadLabel
        JLabel dadLab = new JLabel("Nome Area di Riferimento: ");
        dadLab.setFont(f); dadLab.setForeground(blue); dadLab.setBounds(350,15,280,30);

        String[] array = findAreas();
        JComboBox<String> dadComboBox = new JComboBox<>(array);
        dadComboBox.setBounds(350,50,280,30);
        //StateLabel
        JLabel stateLab = new JLabel("Stato: ");
        stateLab.setFont(f); stateLab.setForeground(blue); stateLab.setBounds(680,15,200,30);
        JLabel stateLab2 = new JLabel("BOZZA");
        stateLab2.setBounds(680,50,280,30);
        //DescriptionArea
        JLabel descrLab = new JLabel("Descrizione: ");
        descrLab.setFont(f); descrLab.setForeground(blue); descrLab.setBounds(20,100,200,30);
        JTextArea descrArea = new JTextArea(5,20); descrArea.setText("Digita descrizione");
        descrArea.setLineWrap(true);
        JScrollPane descrScroll = new JScrollPane(descrArea);
        descrScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        descrScroll.setBounds(20,135,940,150);
        //SaveButtons
        JButton saveB = new JButton("SALVA IN BOZZA");  saveB.setForeground(Color.white); saveB.setBackground(blue2);
        saveB.setBounds(20,350,200,30);
        JButton saveV = new JButton("SALVA E VALIDA");  saveV.setForeground(Color.white); saveV.setBackground(blue2);
        saveV.setBounds(350,350,200,30);
        //Image
        ImageZoom icon = new ImageZoom(new ImageIcon(HomePanel.class.getResource("myLogo.png")),0.25);
        ImageIcon image = icon.getImageIcon();
        JLabel lab = new JLabel(image);
        lab.setBounds(730,320,200,200);

        //Adding
        fieldPanel.add(nameLab); fieldPanel.add(nameField);
        fieldPanel.add(dadLab); fieldPanel.add(dadComboBox);
        fieldPanel.add(stateLab); fieldPanel.add(stateLab2);
        fieldPanel.add(descrLab); fieldPanel.add(descrScroll);
        fieldPanel.add(saveB); fieldPanel.add(saveV);
        fieldPanel.add(lab);
        add(fieldPanel); add(headPanel);

        //Using mediator
        mediator.setNameField(nameField);
        mediator.setDadComboBox(dadComboBox);
        mediator.setSaveBPanel(saveB);
        mediator.setSaveVPanel(saveV);
        nameField.addActionListener(e -> mediator.textChanged(nameField));
        dadComboBox.addActionListener(e -> mediator.boxComboChanged(dadComboBox));
        saveB.addActionListener(e -> mediator.buttonChanged(saveB));
        saveV.addActionListener(e -> mediator.buttonChanged(saveV));
    }
    private String[] findAreas(){
        HashSet<String> tot = admin.getAllAreas();
        Organigramma org = admin.getOrganigramma();
        tot.removeAll(org.getSubAreas());
        tot.remove(org.getName());

        String[] array;
        if (tot.size()==0){
            array = new String[1];
            array[0] = "Nessuna area di riferimento";
        } else{
            array = tot.toArray(new String[tot.size()]);
        }
        return array;
    }
}
