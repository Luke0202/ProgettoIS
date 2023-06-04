package is.panels;

import is.mediator.Mediator;
import is.decorator.ImageZoom;

import javax.swing.*;
import java.awt.*;

public class LogPanel extends JPanel{
    public LogPanel(Mediator mediator){
        if (mediator == null) throw new IllegalArgumentException("Mediator non valido");

        setLayout(null);
        Color blue = new Color(200,220,246);
        Color blue2 = new Color(0,51,200);
        setBackground(blue);
        setBounds(0,0,1000,1000);
        //Image
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.3);
        ImageIcon image = icon.getImageIcon();
        //Label
        JLabel lab = new JLabel(image);
        lab.setBounds(390,35,250,250);
        add(lab);
        //Header
        Font f = new Font("TimesNewRoman",Font.ITALIC,35);
        JLabel head = new JLabel("MyOrg",SwingConstants.CENTER);
        head.setFont(f); head.setForeground(Color.red); head.setBounds(410,280,200,100);
        add(head);
        //Access
        JTextField idField = new JTextField(20);  idField.setBounds(485,370,120,30);
        JTextField pswField = new JTextField(20);   pswField.setBounds(485,410,120,30);
        JButton confLog = new JButton("Accedi");  confLog.setForeground(Color.white); confLog.setBackground(blue2);
        confLog.setBounds(450,455,110,30);   confLog.setEnabled(false); confLog.setOpaque(true);
        JButton newAziendaButton = new JButton("Crea Nuova Azienda");  newAziendaButton.setForeground(Color.white);
        newAziendaButton.setBackground(blue2); newAziendaButton.setBounds(414,520,180,30); newAziendaButton.setOpaque(true);
        newAziendaButton.setEnabled(true);
        //Using mediator
        mediator.setIdLog(idField);
        mediator.setPswLog(pswField);
        mediator.setConfLog(confLog);
        mediator.setNewAziendaLog(newAziendaButton);
        idField.addActionListener(e -> mediator.textChanged(idField));
        pswField.addActionListener(e -> mediator.textChanged(pswField));
        confLog.addActionListener(e -> mediator.buttonChanged(confLog));
        newAziendaButton.addActionListener(e->mediator.buttonChanged(newAziendaButton));

        JLabel reqId = new JLabel("ID: "); reqId.setBounds(410,370,100,30); add(reqId);
        JLabel reqPsw = new JLabel("Password: "); reqPsw.setBounds(410,410,100,30); add(reqId);

        add(idField); add(pswField); add(reqId); add(reqPsw); add(confLog); add(newAziendaButton);
    }
}
