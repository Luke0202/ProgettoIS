package is.panels;

import is.mediator.Mediator;
import is.item.ImageZoom;
import is.azienda.Azienda;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

/**
 * Tale classe estende JPanel. Definisce un pannello
 * di creazione di un nuovo ruolo.
 * @author lucab
 */
public class CreateRolePanel extends JPanel {
    public CreateRolePanel(Mediator mediator) {
        //Verifica validità mediator
        if (mediator==null) throw new IllegalArgumentException("Mediator non valido");

        Azienda azienda = mediator.getAzienda();
        //Colors
        Color blue = new Color(3,2,179);
        Color blue2 = new Color(0,51,200);
        Color gray = new Color(230,230,230);
        //Panel options
        setLayout(null);
        setBounds(0,0,1000,1000);
        //Header
        JPanel headPanel = new JPanel(null);
        headPanel.setBackground(Color.white); //Definizione sfondo
        headPanel.setBounds(0,0,1000,60); //Confini headPanel
        //Label of headPanel
        Font f = new Font("TimesNewRoman",Font.BOLD,23);
        JLabel head = new JLabel("Inserimento nuovo Ruolo");
        head.setFont(f);
        head.setForeground(Color.black);
        head.setBounds(10,7,350,50);

        //Aggiunta campi di creazione ruolo
        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray); //Definizione sfondo
        fieldPanel.setBounds(0,50,1000,950); //Confini fieldPanel
        f = new Font("TimesNewRoman",Font.ITALIC,20);
        //Name Role
        JLabel nameLab = new JLabel("Nome Ruolo: ");
        nameLab.setFont(f);
        nameLab.setForeground(blue);
        nameLab.setBounds(20,15,200,30);

        JTextField nameField = new JTextField(20); //Campo contenente il nome del ruolo
        nameField.setBounds(20,50,280,30);
        //Name Area
        JLabel areaLab = new JLabel("Scegli Area: ");
        areaLab.setFont(f);
        areaLab.setForeground(blue);
        areaLab.setBounds(350,15,200,30);

        String[] array = findAreas(azienda);
        JComboBox<String> areaComboBox = new JComboBox<>(array);//ComboBox contenente le aree in azienda
        areaComboBox.setBounds(350,50,280,30);
        //DescriptionArea
        JLabel descrLab = new JLabel("Descrizione Ruolo: ");
        descrLab.setFont(f);
        descrLab.setForeground(blue);
        descrLab.setBounds(20,100,200,30);

        JTextArea descrArea = new JTextArea(5,20); //Campo contenente la descrizione del ruolo
        descrArea.setLineWrap(true);
        JScrollPane descrScroll = new JScrollPane(descrArea);
        descrScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //Consente solo lo scorrimento verticale
        descrScroll.setBounds(20,135,940,150);
        //SaveButton
        JButton save = new JButton("SALVA"); //Button per il salvataggio
        save.setForeground(Color.white);
        save.setBackground(blue2);
        save.setBounds(20,350,200,30);
        //Logo applicazione
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.25);
        ImageIcon image = icon.getImageIcon();
        JLabel lab = new JLabel(image);
        lab.setBounds(730,320,200,200);

        //Adding
        add(headPanel); add(fieldPanel);
        headPanel.add(head);
        fieldPanel.add(nameLab); fieldPanel.add(nameField);
        fieldPanel.add(areaLab); fieldPanel.add(areaComboBox);
        fieldPanel.add(descrLab); fieldPanel.add(descrScroll);
        fieldPanel.add(save);
        fieldPanel.add(lab);

        //Using mediator
        mediator.setNameCreateRole(nameField);
        mediator.setAreaComboCreateRole(areaComboBox);
        mediator.setDescrCreateRole(descrArea);
        mediator.setSaveCreateRole(save);
        //Listener
        save.addActionListener(e -> mediator.widgetChanged(save));
    }

    /**
     * Restituisce la lista dei nomi delle aree in azienda.
     * @param azienda Azienda da visionare
     * @return lista dei nomi
     */
    private static String[] findAreas(Azienda azienda){
        //Ricavo i nomi delle aree
        HashSet<String> areas = azienda.getAreasName();

        //Conversione in String[]
        String[] array;
        if (areas.size()==0){
            array = new String[1];
            array[0] = "Nessuna Area";
        } else{
            array = areas.toArray(new String[areas.size()]);
        }
        return array;
    }
}//CreateRolePanel
