package is.panels;

import is.item.ImageZoom;
import is.mediator.Mediator;
import javax.swing.*;
import java.awt.*;

/**
 * Tale classe estende JPanel. Definisce un pannello di
 * accesso al sistema, attraverso il quale è possibile
 * accedere al pannello di creazione di una nuova azienda.
 * @author lucab
 */
public class AccessPanel extends JPanel {
    public AccessPanel(Mediator mediator){
        //Verifica validità mediator
        if (mediator == null) throw new IllegalArgumentException("Mediator non valido");
        //Colors
        Color blue = new Color(200,220,246);
        Color blue2 = new Color(0,51,200);
        //Panel options
        setLayout(null);
        setBackground(blue);
        setBounds(0,0,1000,1000);
        //Logo applicazione
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.45);
        ImageIcon image = icon.getImageIcon();
        JLabel lab = new JLabel(image);
        lab.setBounds(315,35,370,370);
        //Label
        Font f = new Font("TimesNewRoman",Font.BOLD,43);
        JLabel head = new JLabel("MyOrg",SwingConstants.CENTER);
        head.setFont(f);
        head.setForeground(Color.black);
        head.setBounds(395,400,200,100);
        //Button
        JButton newAziendaButton = new JButton("Crea Nuova Azienda"); //Button di creazione di una nuova azienda
        newAziendaButton.setForeground(Color.white);
        newAziendaButton.setBackground(blue2);
        newAziendaButton.setOpaque(true);
        newAziendaButton.setBounds(397,520,198,35);
        //Adding
        add(lab); add(head); add(newAziendaButton);
        //Mediator
        mediator.setNewAziendaAccess(newAziendaButton);
        //Listener
        newAziendaButton.addActionListener(e->mediator.widgetChanged(newAziendaButton));
    }
}//AccessPanel