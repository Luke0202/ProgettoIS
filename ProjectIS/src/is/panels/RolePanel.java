package is.panels;

import is.item.DataTable;
import is.azienda.*;
import is.mediator.Mediator;
import is.item.ImageZoom;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

/**
 * Tale classe estende JPanel. Definisce un pannello di
 * gestione di un singolo ruolo definito in azienda.
 * @author lucab
 */
public class RolePanel extends JPanel {
    public RolePanel(Role role,Mediator mediator) {
        //Verifica validità dati
        if (role == null || mediator==null) throw new IllegalArgumentException("Dati non validi");

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
        JLabel head = new JLabel("Dettaglio ruolo");
        head.setFont(f);
        head.setForeground(Color.black);
        head.setBounds(10,7,350,50);

        //Aggiunta campi per la visione di un ruolo
        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray); //Definizione sfondo
        fieldPanel.setBounds(0,50,1000,950); //Confini fieldPanel

        //Name Field
        f = new Font("TimesNewRoman",Font.ITALIC,20);
        JLabel nameLab = new JLabel("Nome Ruolo: ");
        nameLab.setFont(f);
        nameLab.setForeground(blue);
        nameLab.setBounds(20,15,200,30);

        JTextField nameField = new JTextField(20); //Campo contenente il nome del ruolo
        nameField.setText(role.getName());
        nameField.setEditable(false);
        nameField.setBounds(20,50,280,30);
        //Area Field
        JLabel areaLab = new JLabel("Nome Area: ");
        areaLab.setFont(f);
        areaLab.setForeground(blue);
        areaLab.setBounds(350,15,280,30);

        JTextField areaField = new JTextField(20); //Campo contenente il nome dell'area in cui è stato definito il ruolo
        areaField.setText(role.getArea());
        areaField.setEditable(false);
        areaField.setBounds(350,50,400,30);
        //Button
        JButton modButton = new JButton("Modifica ruolo"); //Button per la modifica di un ruolo
        modButton.setForeground(Color.white);
        modButton.setBackground(blue2);
        modButton.setBounds(810,30,150,30);

        JButton removeButton = new JButton("Elimina ruolo"); //Button per la cancellazione di un ruolo
        removeButton.setForeground(Color.white);
        removeButton.setEnabled(isRemovable(role,azienda));
        removeButton.setBackground(blue2);
        removeButton.setBounds(810,70,150,30);
        //DescriptionRole
        JLabel descrLab = new JLabel("Descrizione: ");
        descrLab.setFont(f);
        descrLab.setForeground(blue);
        descrLab.setBounds(20,100,200,30);

        JTextArea descrRole = new JTextArea(5,20); //Campo contenente la descrizione del ruolo
        descrRole.setText(role.getDescription());
        descrRole.setEditable(false);
        descrRole.setLineWrap(true);

        JScrollPane descrScroll = new JScrollPane(descrRole);
        descrScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //Consente solo lo scorrimento verticale
        descrScroll.setBounds(20,135,940,150);
        //Visione dei dipendenti che presentano tale ruolo
        JLabel empLab = new JLabel("Dipendenti: ");
        empLab.setFont(f);
        empLab.setForeground(blue);
        empLab.setBounds(20,315,200,30);
        //Table

        //Ricavo i dipendenti che presentano un determinato ruolo
        HashSet<Employee> employees = azienda.getEmployee(role);

        //Tipologia colonne della tabella contenente i dipendenti
        String[] columnNames = {"ID","Nome","Cognome","Email"};

        Object[][] data = new Object[employees.size()][columnNames.length];

        //Getting data
        int i = 0;
        for (Employee e:employees){
            data[i][0] = e.getID();
            data[i][1] = e.getName();
            data[i][2] = e.getSurname();
            data[i][3] = e.getEmail();
            i++;
        }

        //Definizione tabella
        DataTable table = new DataTable(data, columnNames);

        //ScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20,350,700,150);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //Consente solo lo scorrimento verticale

        //Logo applicazione
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.25);
        ImageIcon image = icon.getImageIcon();
        JLabel lab = new JLabel(image);
        lab.setBounds(730,320,200,200);

        //Adding
        add(headPanel);
        headPanel.add(head);
        add(fieldPanel);
        fieldPanel.add(nameLab); fieldPanel.add(nameField);
        fieldPanel.add(areaLab); fieldPanel.add(areaField);
        fieldPanel.add(modButton); fieldPanel.add(removeButton);
        fieldPanel.add(descrLab); fieldPanel.add(descrScroll);
        fieldPanel.add(empLab); fieldPanel.add(scrollPane);
        fieldPanel.add(lab);

        //Mediator
        mediator.setNameRole(nameField);
        mediator.setAreaRole(areaField);
        mediator.setEditRole(modButton);
        mediator.setRemoveRole(removeButton);
        //Listeners
        modButton.addActionListener(e->mediator.widgetChanged(modButton));
        removeButton.addActionListener(e->mediator.widgetChanged(removeButton));
    }

    /**
     * Verifica se un ruolo è cancellabile o meno.
     * Un ruolo si può rimuovere se non è associato a nessun dipendente.
     * @param role
     * @param azienda Azienda conen
     * @return
     */
    private boolean isRemovable(Role role,Azienda azienda){
        //Un ruolo è rimuovibile se non ha alcun dipendente associato
        //L'area di riferimento del ruolo non deve presentare alcuna associazione ruolo-idDipendente, in cui è presente tale ruolo
        Organigramma org = azienda.getArea(role.getArea());
        return !org.contains(role);
    }
}//RolePanel