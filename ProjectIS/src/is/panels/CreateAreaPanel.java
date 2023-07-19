package is.panels;

import is.mediator.Mediator;
import is.item.ImageZoom;
import is.azienda.Azienda;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

/**
 * Tale classe estende JPanel. Definisce un pannello
 * di creazione area. L'area può essere salvata in BOZZA
 * o può essere salvata e VALIDATA. Una volta validata l'area
 * non risulta più modificabile.
 * @author lucab
 */
public class CreateAreaPanel extends JPanel {
    public CreateAreaPanel(Mediator mediator) {
        //Verifica validità mediator
        if (mediator == null) throw new IllegalArgumentException("Mediator non valido");

        Azienda azienda = mediator.getAzienda();
        //Colors
        Color blue = new Color(3,2,179);
        Color blue2 = new Color(0,51,200);
        Color gray = new Color(230,230,230);
        //Panel options
        setLayout(null);
        setBounds(0,0,1000,1000); //Confini JPanel
        //Header
        JPanel headPanel = new JPanel(null);
        headPanel.setBackground(Color.white); //Sfondo headPanel
        headPanel.setBounds(0,0,1000,60); //Confini headPanel
        //Label of headPanel
        Font f = new Font("TimesNewRoman",Font.BOLD,23);
        JLabel head = new JLabel("Creazione nuova area organizzativa");
        head.setFont(f);
        head.setForeground(Color.black);
        head.setBounds(10,7,480,50);

        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray); //Sfondo fieldPanel
        fieldPanel.setBounds(0,50,1000,950); //Confini fieldPanel
        //Name Field
        f = new Font("TimesNewRoman",Font.ITALIC,20);
        JLabel nameLab = new JLabel("Nome area: ");
        nameLab.setFont(f); nameLab.setForeground(blue);
        nameLab.setBounds(20,15,200,30);

        JTextField nameField = new JTextField(20); //Campo contenente il nome dell'area
        nameField.setBounds(20,50,280,30);
        //Area di riferimento
        JLabel dadLab = new JLabel("Nome area di riferimento: ");
        dadLab.setFont(f);
        dadLab.setForeground(blue);
        dadLab.setBounds(350,15,280,30);

        String[] array = findAreas(azienda);
        JComboBox<String> dadComboBox = new JComboBox<>(array); //ComboBox contenente le aree aziendali
        dadComboBox.setBounds(350,50,280,30);
        //StateLabel
        JLabel stateLab = new JLabel("Stato: ");
        stateLab.setFont(f);
        stateLab.setForeground(blue);
        stateLab.setBounds(680,15,200,30);

        JLabel stateLab2 = new JLabel("BOZZA"); //Campo contenente lo stato dell'area. Inizialmente si trova in BOZZA
        stateLab2.setBounds(680,50,280,30);
        //DescriptionArea
        JLabel descrLab = new JLabel("Descrizione: ");
        descrLab.setFont(f);
        descrLab.setForeground(blue);
        descrLab.setBounds(20,100,200,30);

        JTextArea descrArea = new JTextArea(5,20); //Campo contenente la descrizione dell'area
        descrArea.setLineWrap(true);
        JScrollPane descrScroll = new JScrollPane(descrArea);
        descrScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        descrScroll.setBounds(20,135,940,150);
        //SaveButtons
        JButton saveB = new JButton("SALVA IN BOZZA"); //Button per salvare in BOZZA
        saveB.setForeground(Color.white);
        saveB.setBackground(blue2);
        saveB.setBounds(20,350,200,30);

        JButton saveV = new JButton("SALVA E VALIDA");  //Button per salvare e VALIDARE
        saveV.setForeground(Color.white);
        saveV.setBackground(blue2);
        saveV.setBounds(350,350,200,30);
        //Logo applicazione
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.25);
        ImageIcon image = icon.getImageIcon();
        JLabel lab = new JLabel(image);
        lab.setBounds(730,320,200,200);
        //Adding
        headPanel.add(head);
        fieldPanel.add(nameLab); fieldPanel.add(nameField);
        fieldPanel.add(dadLab); fieldPanel.add(dadComboBox);
        fieldPanel.add(stateLab); fieldPanel.add(stateLab2);
        fieldPanel.add(descrLab); fieldPanel.add(descrScroll);
        fieldPanel.add(saveB); fieldPanel.add(saveV);
        fieldPanel.add(lab);
        add(fieldPanel); add(headPanel);
        //Using mediator
        mediator.setNameCreateArea(nameField);
        mediator.setDadComboCreateArea(dadComboBox);
        mediator.setDescrCreateArea(descrArea);
        mediator.setSaveBCreateArea(saveB);
        mediator.setSaveVCreateArea(saveV);
        //Listeners
        saveB.addActionListener(e -> mediator.widgetChanged(saveB));
        saveV.addActionListener(e -> mediator.widgetChanged(saveV));
    }

    /**
     * Restituisce i nomi delle aree definite in azienda.
     * @param azienda Azienda da visionare
     * @return array dei nomi delle aree
     */
    private static String[] findAreas(Azienda azienda){
        //Ricavo i nomi delle aree dell'azienda
        HashSet<String> tot = azienda.getAreasName();

        //Conversione in String[]
        String[] array;
        if (tot.size()==0){
            array = new String[1];
            array[0] = "Nessuna area di riferimento";
        } else{
            array = tot.toArray(new String[tot.size()]);
            //Ordinamento array
            java.util.Arrays.sort(array);
        }
        return array;
    }
}//CreateAreaPanel
