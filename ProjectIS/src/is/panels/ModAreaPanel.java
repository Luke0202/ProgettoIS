package is.panels;

import is.mediator.Mediator;
import is.azienda.*;
import is.item.ImageZoom;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

/**
 * Tale classe estende JPanel. Definisce un pannello che
 * consente la modifica di un'area.
 */
public class ModAreaPanel extends JPanel{
    public ModAreaPanel(Organigramma orgToMod,Mediator mediator) {
        //Verifica validità dati
        if (mediator == null || orgToMod==null) throw new IllegalArgumentException("Dati non validi");

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
        headPanel.setBackground(Color.white); //Definizione sfondo
        headPanel.setBounds(0,0,1000,60); //Confini headPanel
        //Label of headPanel
        Font f = new Font("TimesNewRoman",Font.BOLD,23);
        JLabel head = new JLabel("Modifica area organizzativa");
        head.setFont(f);
        head.setForeground(Color.black);
        head.setBounds(10,7,380,50);

        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray); //Definizione sfondo
        fieldPanel.setBounds(0,50,1000,950); //Confini fieldPanel

        //Name Field
        f = new Font("TimesNewRoman",Font.ITALIC,20);
        JLabel nameLab = new JLabel("Nome Area: ");
        nameLab.setFont(f);
        nameLab.setForeground(blue);
        nameLab.setBounds(20,15,200,30);

        JTextField nameField = new JTextField(20); //Campo contenente il nome dell'area
        String nameText=orgToMod.getName(); //Nome dell'area da modificare
        nameField.setText(nameText);
        nameField.setBounds(20,50,280,30);
        //Area di riferimento
        JLabel dadLab = new JLabel("Nome Area di Riferimento: ");
        dadLab.setFont(f);
        dadLab.setForeground(blue);
        dadLab.setBounds(350,15,400,30);

        String[] array = findAreas(azienda,orgToMod);
        JComboBox<String> dadComboBox = new JComboBox<>(array); //ComboBox contenente i nomi delle possibili aree padre di 'orgToMod'
        dadComboBox.setBounds(350,50,280,30);
        //StateLabel
        JLabel stateLab = new JLabel("Stato: ");
        stateLab.setFont(f);
        stateLab.setForeground(blue);
        stateLab.setBounds(680,15,200,30);

        JLabel stateLab2 = new JLabel((!orgToMod.getStateArea()) ? "BOZZA":"VALIDATA"); //Campo contenente lo stato dell'area
        stateLab2.setBounds(680,50,280,30);
        //DescriptionArea
        JLabel descrLab = new JLabel("Descrizione: ");
        descrLab.setFont(f);
        descrLab.setForeground(blue);
        descrLab.setBounds(20,100,200,30);

        JTextArea descrArea = new JTextArea(5,20); //Campo contenente la descrizione dell'area
        String descrText=orgToMod.getDescription();
        descrArea.setText(descrText);
        descrArea.setLineWrap(true);
        //ScrollPane
        JScrollPane descrScroll = new JScrollPane(descrArea);
        descrScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //Consente solo lo scorrimento verticale
        descrScroll.setBounds(20,135,940,150);
        //SaveButtons
        JButton saveB = new JButton("SALVA IN BOZZA");  //Button per il salvataggio in BOZZA
        saveB.setForeground(Color.white); saveB.setBackground(blue2);
        saveB.setBounds(20,350,200,30);
        saveB.setEnabled(!orgToMod.getStateArea());

        JButton saveV = new JButton("SALVA E VALIDA");  //Button per salvare e validare un'area
        saveV.setForeground(Color.white); saveV.setBackground(blue2);
        saveV.setBounds(350,350,200,30);
        saveV.setEnabled(!orgToMod.getStateArea());
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
        add(headPanel); add(fieldPanel);

        //Using mediator
        mediator.setOldArea(orgToMod);
        mediator.setNameModArea(nameField);
        mediator.setDescrModArea(descrArea);
        mediator.setDadComboModArea(dadComboBox);
        mediator.setSaveBModArea(saveB);
        mediator.setSaveVModArea(saveV);
        //Listeners
        saveB.addActionListener(e -> mediator.widgetChanged(saveB));
        saveV.addActionListener(e -> mediator.widgetChanged(saveV));
    }

    /**
     * Restituisce i nomi delle aree di riferimento
     * che si possono attribuire a una specifica area.
     * @param azienda Azienda contenente tutte le aree
     * @param orgChild Area della quale si vogliono conoscere tutte le
     *                 possibili aree di riferimento che si possono
     *                 attribuire a tale area.
     * @return array contenente i nomi delle aree di riferimento
     */
    private static String[] findAreas(Azienda azienda,Organigramma orgChild){
        //Ricavo i nomi delle aree dell'azienda
        HashSet<String> tot = azienda.getAreasName();

        //Non bisogna aggiungere aree che appartengano alla
        //gerarchia che ha 'org' come area radice
        tot.removeAll(orgChild.getSubAreas());
        tot.remove(orgChild.getName());

        //Conversione in String[]
        String[] array;
        if (tot.size()==0){
            array = new String[1];
            array[0] = "Nessuna area di riferimento";
        } else{
            array = tot.toArray(new String[tot.size()]);
            //Ordinamento
            java.util.Arrays.sort(array);

            //Bisogna posizionare il padre dell'area "orgChild" in prima posizione
            String nomePadre = azienda.getParent(orgChild).getName();

            for (int i = 0;i<array.length;i++){
                if (array[i].equals(nomePadre)){
                    if (i!=0){
                        array[i] = array[0];
                        array[0] = nomePadre;
                        break;
                    }
                }
            }
        }
        return array;
    }
}//ModAreaPanel
