package is.panels;

import is.mediator.Mediator;
import is.item.ImageZoom;
import javax.swing.*;
import java.awt.*;

/**
 * Tale classe estende JPanel. Rappresenta la homepage
 * dell'applicazione.
 */
public class HomePanel extends JPanel {
    public HomePanel(Mediator mediator){
        //Verifica validità mediator
        if (mediator == null) throw new IllegalArgumentException("Mediator non valido");

        //Color
        Color blue = new Color(200,220,246);
        //Panel options
        setLayout(null);
        setBackground(blue); //Definizione sfondo
        setBounds(0,0,1000,1000); //Confini JPanel
        //Logo applicazione
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.45);
        ImageIcon image = icon.getImageIcon();
        JLabel lab = new JLabel(image);
        lab.setBounds(315,35,370,370);

        //Title
        Font f = new Font("TimesNewRoman",Font.BOLD,43);
        JLabel title = new JLabel("MyOrg",SwingConstants.CENTER);
        title.setFont(f);
        title.setForeground(Color.black);
        title.setBounds(395,400,200,100);

        //Adding
        add(lab); add(title);
    }
}//HomePanel