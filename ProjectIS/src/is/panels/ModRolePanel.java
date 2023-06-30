package is.panels;

import is.azienda.Role;
import is.mediator.Mediator;
import is.item.ImageZoom;
import javax.swing.*;
import java.awt.*;

/**
 * Tale classe estende JPanel. Definisce un pannello che
 * consente la modifica di un ruolo.
 */
public class ModRolePanel extends JPanel {
    public ModRolePanel(Role roleToMod, Mediator mediator) {
        //Verifica validità dati
        if (mediator==null || roleToMod==null) throw new IllegalArgumentException("Dati non validi");

        //Colors
        Color blue = new Color(3,2,179);
        Color blue2 = new Color(0,51,200);
        Color gray = new Color(230,230,230);
        //Panel options
        setLayout(null);
        setBounds(0,0,1000,1000); //Confini JPanel
        //Header
        JPanel headPanel = new JPanel(null);
        headPanel.setBackground(Color.white); //Definizione sfondo
        headPanel.setBounds(0,0,1000,60); //Confini headPanel
        //Label of headPanel
        Font f = new Font("TimesNewRoman",Font.BOLD,23);
        JLabel head = new JLabel("Modifica Ruolo");
        head.setFont(f);
        head.setForeground(Color.black);
        head.setBounds(10,7,280,50);

        //Aggiunta campi per la modifica di un ruolo
        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray); //Definizione sfondo
        fieldPanel.setBounds(0,50,1000,950); //Confini fieldPanel
        //Name Role
        f = new Font("TimesNewRoman",Font.ITALIC,20);
        JLabel nameLab = new JLabel("Nome Ruolo: ");
        nameLab.setFont(f);
        nameLab.setForeground(blue);
        nameLab.setBounds(20,15,200,30);

        JTextField nameField = new JTextField(20); //Campo contenente il nome del ruolo da modificare
        nameField.setText(roleToMod.getName()); //Nome del ruolo da modificare
        nameField.setBounds(20,50,280,30);
        //DescriptionArea
        JLabel descrLab = new JLabel("Descrizione Ruolo: ");
        descrLab.setFont(f);
        descrLab.setForeground(blue);
        descrLab.setBounds(20,100,200,30);

        JTextArea descrArea = new JTextArea(5,20); //Campo contenente la descrizione del ruolo
        descrArea.setText(roleToMod.getDescription());
        descrArea.setLineWrap(true);

        JScrollPane descrScroll = new JScrollPane(descrArea);
        descrScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //Consente solo lo scorrimento verticale
        descrScroll.setBounds(20,135,940,150);
        //SaveButtons
        JButton save = new JButton("SALVA");  //Button per il salvataggio
        save.setForeground(Color.white);
        save.setBackground(blue2);
        save.setBounds(20,350,200,30);
        //Logo applicazione
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.25);
        ImageIcon image = icon.getImageIcon();
        JLabel lab = new JLabel(image);
        lab.setBounds(730,320,200,200);

        //Adding
        headPanel.add(head);
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
        //Listener
        save.addActionListener(e -> mediator.widgetChanged(save));
    }
}//ModRolePanel
