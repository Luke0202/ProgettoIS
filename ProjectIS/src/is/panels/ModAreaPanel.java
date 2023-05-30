package is.panels;

import is.dipendenti.Administrator;
import is.mediator.Mediator;
import is.organigramma.*;
import is.shapes.ImageZoom;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class ModAreaPanel extends JPanel{
    private Mediator mediator;
    private Administrator ad;
    private Organigramma org;
    public ModAreaPanel(Mediator mediator, Administrator ad,Organigramma orgToMod) {
        if (mediator == null || ad == null || orgToMod==null) throw new IllegalArgumentException("Dati non validi");
        this.mediator = mediator;
        this.org = orgToMod;
        this.ad = ad;

        setLayout(null);
        Color blue = new Color(3,2,179);
        Color blue2 = new Color(0,51,200);
        Color gray = new Color(230,230,230);
        setBounds(0,0,1000,1000);
        //Header
        JPanel headPanel = new JPanel(null); headPanel.setBackground(Color.white);
        headPanel.setBounds(0,0,1000,60);

        Font f = new Font("TimesNewRoman",Font.BOLD,23);
        JLabel head = new JLabel("Modifica Area organizzativa");
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
        JTextField nameField = new JTextField(20);

        String nameText=org.getName();
        if (nameText==null) nameText = "Digita nome area";

        nameField.setText(nameText);
        nameField.setBounds(20,50,280,30);
        //DadLabel
        JLabel dadLab = new JLabel("Nome Area di Riferimento: ");
        dadLab.setFont(f); dadLab.setForeground(blue); dadLab.setBounds(350,15,200,30);

        String[] array = findAreas();
        JComboBox<String> dadComboBox = new JComboBox<>(array);
        dadComboBox.setBounds(350,50,280,30);
        //StateLabel
        JLabel stateLab = new JLabel("Stato: ");
        stateLab.setFont(f); stateLab.setForeground(blue); stateLab.setBounds(680,15,200,30);


        JLabel stateLab2 = new JLabel((org.getStateArea()==false) ? "BOZZA":"VALIDATA");
        stateLab2.setBounds(680,50,280,30);
        //DescriptionArea
        JLabel descrLab = new JLabel("Descrizione: ");
        descrLab.setFont(f); descrLab.setForeground(blue); descrLab.setBounds(20,100,200,30);
        JTextArea descrArea = new JTextArea(5,20);

        String descrText=org.getDescription();
        if (descrText==null) descrText = "Digita descrizione";

        descrArea.setText(descrText);
        descrArea.setLineWrap(true);
        JScrollPane descrScroll = new JScrollPane(descrArea);
        descrScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        descrScroll.setBounds(20,135,940,150);
        //SaveButtons
        JButton saveB = new JButton("SALVA IN BOZZA");  saveB.setForeground(Color.white); saveB.setBackground(blue2);
        saveB.setBounds(20,350,200,30);    saveB.setEnabled(org.getStateArea()==false);
        JButton saveV = new JButton("SALVA E VALIDA");  saveV.setForeground(Color.white); saveV.setBackground(blue2);
        saveV.setBounds(350,350,200,30);   saveV.setEnabled(org.getStateArea()==false);
        //Image
        ImageZoom icon = new ImageZoom(new ImageIcon(HomePanel.class.getResource("myLogo.png")),0.25);
        ImageIcon image = icon.getImageIcon();
        //Label
        JLabel lab = new JLabel(image);
        lab.setBounds(730,320,200,200);

        //Adding
        fieldPanel.add(nameLab); fieldPanel.add(nameField);
        fieldPanel.add(dadLab); fieldPanel.add(dadComboBox);
        fieldPanel.add(stateLab); fieldPanel.add(stateLab2);
        fieldPanel.add(descrLab); fieldPanel.add(descrScroll);
        fieldPanel.add(saveB); fieldPanel.add(saveV);
        fieldPanel.add(lab);
        add(headPanel); add(fieldPanel);

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
        HashSet<String> tot = ad.getAllAreas();
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
