package is.panels;

import is.mediator.Mediator;
import is.item.ImageZoom;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Tale classe estende JPanel. Definisce il pannello di
 * log in.
 * @author lucab
 */
public class LogPanel extends JPanel{

    private JButton confLog;
    private JTextField nameField;
    private JPasswordField pswField;

    public LogPanel(Mediator mediator){
        //Verifica validità mediator
        if (mediator == null) throw new IllegalArgumentException("Mediator non valido");

        //Colors
        Color blue = new Color(200,220,246);
        Color blue2 = new Color(0,51,200);
        //Panel options
        setLayout(null);
        setBackground(blue); //Definizione sfondo
        setBounds(0,0,1000,1000); //Confini JPanel
        //Logo applicazione
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.3);
        ImageIcon image = icon.getImageIcon();
        JLabel lab = new JLabel(image);
        lab.setBounds(390,35,250,250);

        //Title
        Font f = new Font("TimesNewRoman",Font.ITALIC,35);
        JLabel title = new JLabel("MyOrg",SwingConstants.CENTER);
        title.setFont(f);
        title.setForeground(Color.red);
        title.setBounds(410,280,200,100);

        //Name Azienda
        JLabel reqId = new JLabel("Nome azienda: ");
        reqId.setBounds(385,370,100,30);

        nameField = new JTextField(20);  //Campo in cui bisogna inserire il nome
        nameField.setBounds(485,370,150,30);

        //Password
        JLabel reqPsw = new JLabel("Password: ");
        reqPsw.setBounds(410,410,100,30);

        pswField = new JPasswordField(20);  //Campo in cui bisogna inserire la password
        pswField.setBounds(485,410,150,30);

        //Confirmation button
        confLog = new JButton("Accedi"); //Button per accedere al sistema
        confLog.setForeground(Color.white);
        confLog.setBackground(blue2);
        confLog.setEnabled(false);
        confLog.setOpaque(true);
        confLog.setBounds(450,465,110,30);

        //Button to create a new azienda
        JButton newAziendaButton = new JButton("Crea nuova azienda"); //Button per la creazione di una nuova azienda
        newAziendaButton.setForeground(Color.white);
        newAziendaButton.setBackground(blue2);
        newAziendaButton.setOpaque(true);
        newAziendaButton.setEnabled(true);
        newAziendaButton.setBounds(414,520,180,30);

        //Using mediator
        mediator.setNameLog(nameField);
        mediator.setPswLog(pswField);
        mediator.setConfLog(confLog);
        mediator.setNewAziendaLog(newAziendaButton);
        //Listeners
        nameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {gestisciButton();}

            @Override
            public void removeUpdate(DocumentEvent e) {gestisciButton();}

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        pswField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {gestisciButton();}

            @Override
            public void removeUpdate(DocumentEvent e) {gestisciButton();}

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

        confLog.addActionListener(e -> mediator.widgetChanged(confLog));
        newAziendaButton.addActionListener(e->mediator.widgetChanged(newAziendaButton));

        //Adding
        add(lab); add(title); add(nameField); add(pswField); add(reqId); add(reqPsw); add(confLog); add(newAziendaButton);
    }

    /**
     * Tale metodo abilita o disabilita il pulsante per
     * accedere al sistema, in base al contenuto di nameField
     * e pswField. In presenza di campi vuoti il pulsante viene
     * disabilitato.
     */
    private void gestisciButton(){
        if (nameField.getText().isEmpty() || pswField.getText().isEmpty()){
            confLog.setEnabled(false);
        }else confLog.setEnabled(true);
    }
}//LogPanel
