package is.panels;

import is.mediator.Mediator;
import is.decorator.ImageZoom;
import javax.swing.*;
import java.awt.*;

/**
 * Tale classe estende JPanel. Definisce un pannello di creazione
 * di una nuova azienda.
 * @author lucab
 */
public class CreateAziendaPanel extends JPanel {

    public CreateAziendaPanel(Mediator mediator) {
        //Verifica validità mediator
        if (mediator == null) throw new IllegalArgumentException("Mediator non valido");

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
        JLabel head = new JLabel("Creazione azienda");
        head.setFont(f);
        head.setForeground(Color.black);
        head.setBounds(10,7,380,50);
        //Aggiunta campi di creazione azienda
        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray); //Definizione sfondo
        fieldPanel.setBounds(0,50,1000,950); //Confini fieldPanel
        //Name Field
        f = new Font("TimesNewRoman",Font.ITALIC,20);
        JLabel nameLab = new JLabel("Nome Azienda: ");
        nameLab.setFont(f);
        nameLab.setForeground(blue);
        nameLab.setBounds(20,15,200,30);

        JTextField nameField = new JTextField(20); //Campo contenente il nome dell'azienda
        nameField.setBounds(20,50,280,30);
        //Codice ATECO
        JLabel codLab = new JLabel("Codice ATECO: ");
        codLab.setFont(f); codLab.setForeground(blue); codLab.setBounds(350,15,280,30);

        JTextField codField = new JTextField(20); //Campo contenente il codice ATECO dell'azienda
        codField.setBounds(350,50,280,30);
        //Headquarter Field
        JLabel headquarterLab = new JLabel("Sede centrale: ");
        headquarterLab.setFont(f);
        headquarterLab.setForeground(blue);
        headquarterLab.setBounds(680,15,200,30);

        JTextField headquarterField = new JTextField(20); //Campo contenente la sede centrale dell'azienda
        headquarterField.setBounds(680,50,280,30);
        //Type Field
        JLabel typeLab = new JLabel("Settore azienda: ");
        typeLab.setFont(f);
        typeLab.setForeground(blue);
        typeLab.setBounds(20,120,200,30);

        JTextField typeField = new JTextField(20); //Campo contenente il settore dell'azienda
        typeField.setBounds(20,155,280,30);
        //Psw Field
        JLabel pswLab = new JLabel("Password: ");
        pswLab.setFont(f);
        pswLab.setForeground(blue);
        pswLab.setBounds(350,120,200,30);

        JTextField pswField = new JTextField(20); //Campo contenente la password aziendale
        pswField.setBounds(350,155,280,30);
        //SaveButtons
        JButton save = new JButton("SALVA");  //Button di salvataggio
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
        fieldPanel.add(codLab); fieldPanel.add(codField);
        fieldPanel.add(headquarterLab); fieldPanel.add(headquarterField);
        fieldPanel.add(typeLab); fieldPanel.add(typeField);
        fieldPanel.add(pswLab); fieldPanel.add(pswField);
        fieldPanel.add(save); fieldPanel.add(lab);
        add(fieldPanel); add(headPanel);
        //Using mediator
        mediator.setNameCreateAzienda(nameField);
        mediator.setCodCreateAzienda(codField);
        mediator.setHeadquarterCreateAzienda(headquarterField);
        mediator.setTypeCreateAzienda(typeField);
        mediator.setPswCreateAzienda(pswField);
        mediator.setSaveCreateAzienda(save);
        //Listener
        save.addActionListener(e -> mediator.widgetChanged(save));
    }
}//CreateAziendaPanel