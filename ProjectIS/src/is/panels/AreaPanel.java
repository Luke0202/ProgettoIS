package is.panels;

import is.item.DataTable;
import is.azienda.*;
import is.mediator.Mediator;
import is.item.ImageZoom;
import javax.swing.*;
import java.awt.*;

/**
 * Tale classe estende JPanel. Definisce il pannello
 * di gestione di una singola area organizzativa.
 * @author lucab
 */
public class AreaPanel extends JPanel {

    public AreaPanel(Organigramma org,Mediator mediator) {
        //Verifica validità dati
        if (org == null || mediator==null) throw new IllegalArgumentException("Dati non validi");

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
        JLabel head = new JLabel("Dettaglio area organizzativa");
        Font f = new Font("TimesNewRoman",Font.BOLD,23);
        head.setFont(f);
        head.setForeground(Color.black);
        head.setBounds(10,7,350,50);
        //Aggiunta i campi dell'area
        JPanel fieldPanel = new JPanel(null); //Pannello che contiene i campi dell'area
        fieldPanel.setBackground(gray); //Definizione sfondo
        fieldPanel.setBounds(0,50,1000,950); //Confini fieldPanel
        //NameArea Field
        JLabel nameLab = new JLabel("Nome area: ");
        f = new Font("TimesNewRoman",Font.ITALIC,20);
        nameLab.setFont(f);
        nameLab.setForeground(blue);
        nameLab.setBounds(20,15,200,30);

        JTextField nameField = new JTextField(20); //Campo contenente il nome dell'area
        nameField.setText(org.getName());
        nameField.setEditable(false);
        nameField.setBounds(20,50,280,30);
        //Parent Area Field
        JLabel dadLab = new JLabel("Nome area di riferimento: ");
        dadLab.setFont(f);
        dadLab.setForeground(blue);
        dadLab.setBounds(350,15,280,30);

        JTextField dadField = new JTextField(20); //Campo contenente il nome dell'area padre
        dadField.setText(findDadArea(azienda,org));
        dadField.setEditable(false);
        dadField.setBounds(350,50,200,30);
        //StateLabel
        JLabel stateLab = new JLabel("Stato: ");
        stateLab.setFont(f); //Definizione font
        stateLab.setForeground(blue);
        stateLab.setBounds(680,15,200,30);

        JLabel stateLab2 = new JLabel((!org.getStateArea()) ? "BOZZA":"VALIDATA"); //Label contenente lo stato dell'area
        stateLab2.setBounds(680,50,280,30);
        //Button
        JButton modButton = new JButton("Modifica area");  //Button per modificare un'area
        modButton.setForeground(Color.white);
        modButton.setBackground(blue2);
        modButton.setBounds(810,30,150,30);
        modButton.setEnabled(!org.getStateArea());

        JButton removeButton = new JButton("Elimina area");  //Button per eliminare un'area
        removeButton.setForeground(Color.white);
        removeButton.setEnabled(!dadField.getText().equals("Nessuna") && isRemovable(org));
        removeButton.setBackground(blue2);
        removeButton.setBounds(810,70,150,30);
        //DescriptionArea
        JLabel descrLab = new JLabel("Descrizione: ");
        descrLab.setFont(f); descrLab.setForeground(blue);
        descrLab.setBounds(20,100,200,30);

        JTextArea descrArea = new JTextArea(5,20); //JTextArea contenente la descrizione dell'area
        descrArea.setText(org.getDescription());
        descrArea.setEditable(false);
        descrArea.setLineWrap(true);

        JScrollPane descrScroll = new JScrollPane(descrArea);
        descrScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//Consente lo scorrimento verticale
        descrScroll.setBounds(20,135,940,150);
        //EmployeesArea
        JLabel empLab = new JLabel("Dipendenti: ");
        empLab.setFont(f);
        empLab.setForeground(blue);
        empLab.setBounds(20,315,200,30);
        //Tabella contenente le informazioni dei vari dipendenti

        //Tipologia informazioni per ogni colonna
        String[] columnNames = {"Cognome","Nome","Email","ID"};

        //Getting data
        Object[][] data = new Object[org.getNEmployees()][columnNames.length];

        int i = 0;
        for (Employee e:azienda.getEmployees()){
            if (org.contains(e.getID())){
                data[i][0] = e.getSurname();
                data[i][1] = e.getName();
                data[i][2] = e.getEmail();
                data[i][3] = e.getID();
                i++;
            }
        }

        //Table
        DataTable table = new DataTable(data, columnNames);
        //ScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //Consente lo scorrimento verticale
        scrollPane.setBounds(20,350,700,150);
        //Logo applicazione
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.25);
        ImageIcon image = icon.getImageIcon();
        JLabel lab = new JLabel(image);
        lab.setBounds(730,320,200,200);

        //Adding
        headPanel.add(head);
        fieldPanel.add(nameLab); fieldPanel.add(nameField);
        fieldPanel.add(dadLab); fieldPanel.add(dadField);
        fieldPanel.add(stateLab); fieldPanel.add(stateLab2);
        fieldPanel.add(modButton); fieldPanel.add(removeButton);
        fieldPanel.add(descrLab); fieldPanel.add(descrScroll);
        fieldPanel.add(empLab); fieldPanel.add(scrollPane);
        fieldPanel.add(lab);
        add(fieldPanel); add(headPanel);
        //Mediator
        mediator.setNameArea(nameField);
        mediator.setRemoveArea(removeButton);
        mediator.setEditArea(modButton);
        //Listeners
        modButton.addActionListener(e->mediator.widgetChanged(modButton));
        removeButton.addActionListener(e->mediator.widgetChanged(removeButton));
    }

    /**
     * Restituisce il nome dell'area genitore di un'area.
     * Se l'area, fornita in ingresso, coincide con
     * l'area radice, allora il metodo restituisce
     * la string "Nessuna".
     * @param azienda azienda che contiene l'organigramma
     * @param org area della quale si vuole identificare il padre
     * @return nome area padre
     */
    private static String findDadArea(Azienda azienda,Organigramma org){
        //Organigramma aziendale
        Organigramma organigramma = azienda.getOrganigramma();

        //Verifica presenza di un'area padre
        if (organigramma.getName().equals(org.getName()))
            return "Nessuna";

        return azienda.getParent(org).getName();
    }

    /**
     * Verifica se un'area è cancellabile o meno.
     * Se l'area e tutte le sotto-aree presentano
     * complessivamente almeno un dipendente, allora
     * l'area non può essere rimossa.
     * @param org organigramma da rimuovere
     * @return boolean
     */
    private static boolean isRemovable(Organigramma org){
        for (Area a:org){
            //Verifica presenza di dipendenti
            if (a.getNEmployees()>0) return false;
        }
        return true;
    }
}//AreaPanel
