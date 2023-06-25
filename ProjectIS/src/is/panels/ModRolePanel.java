package is.panels;

import is.organigramma.Role;
import is.mediator.Mediator;
import is.decorator.ImageZoom;
import javax.swing.*;
import java.awt.*;

public class ModRolePanel extends JPanel {
    public ModRolePanel(Role roleToMod, Mediator mediator) {
        if (mediator==null || roleToMod==null) throw new IllegalArgumentException("Dati non validi");

        setLayout(null);
        Color blue = new Color(3,2,179);
        Color blue2 = new Color(0,51,200);
        Color gray = new Color(230,230,230);
        setBounds(0,0,1000,1000);
        //Header
        JPanel headPanel = new JPanel(null); headPanel.setBackground(Color.white);
        headPanel.setBounds(0,0,1000,60);

        Font f = new Font("TimesNewRoman",Font.BOLD,23);
        JLabel head = new JLabel("Modifica Ruolo");
        head.setFont(f); head.setForeground(Color.black); head.setBounds(10,7,280,50);
        headPanel.add(head);
        //Adding Fields
        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray);
        fieldPanel.setBounds(0,50,1000,950);
        f = new Font("TimesNewRoman",Font.ITALIC,20);
        //Name Role
        JLabel nameLab = new JLabel("Nome Ruolo: ");
        nameLab.setFont(f); nameLab.setForeground(blue); nameLab.setBounds(20,15,200,30);
        JTextField nameField = new JTextField(20); nameField.setText(roleToMod.getName());
        nameField.setBounds(20,50,280,30);
        //DescriptionArea
        JLabel descrLab = new JLabel("Descrizione Ruolo: ");
        descrLab.setFont(f); descrLab.setForeground(blue); descrLab.setBounds(20,100,200,30);
        JTextArea descrArea = new JTextArea(5,20); descrArea.setText(roleToMod.getDescription());
        descrArea.setLineWrap(true);
        JScrollPane descrScroll = new JScrollPane(descrArea);//Controllo per vedere se è gia presente una descrizione per tale ruolo
        descrScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        descrScroll.setBounds(20,135,940,150);
        //SaveButtons
        JButton save = new JButton("SALVA");  save.setForeground(Color.white); save.setBackground(blue2);
        save.setBounds(20,350,200,30);
        //Image
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.25);
        ImageIcon image = icon.getImageIcon();
        //Label
        JLabel lab = new JLabel(image);
        lab.setBounds(730,320,200,200);

        //Adding
        fieldPanel.add(nameLab); fieldPanel.add(nameField);
        fieldPanel.add(descrLab); fieldPanel.add(descrScroll);
        fieldPanel.add(save);
        fieldPanel.add(lab);
        add(headPanel); add(fieldPanel);

        //Using mediator
        mediator.setOldRole(roleToMod);
        mediator.setNameModRole(nameField);
        mediator.setDescrModRole(descrArea);
        mediator.setSaveModRole(save);
        save.addActionListener(e -> mediator.widgetChanged(save));
    }
}
