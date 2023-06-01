package is.panels;

import is.mediator.Mediator;
import is.shapes.ImageZoom;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private Mediator mediator;
    public HomePanel(Mediator mediator){
        if (mediator == null) throw new IllegalArgumentException("Dato non valido");
        this.mediator = mediator;

        setLayout(null);
        Color blue = new Color(200,220,246);
        Color blue2 = new Color(0,51,200);
        setBackground(blue);
        setBounds(0,0,1000,1000);
        //Image
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.45);
        ImageIcon image = icon.getImageIcon();
        //Label
        JLabel lab = new JLabel(image);
        lab.setBounds(315,35,370,370);
        add(lab);
        //Header
        Font f = new Font("TimesNewRoman",Font.BOLD,43);
        JLabel head = new JLabel("MyOrg",SwingConstants.CENTER);
        head.setFont(f); head.setForeground(Color.red); head.setBounds(395,400,200,100);
        add(head);
    }
}