package is.panels;

import is.item.DataTable;
import is.mediator.Mediator;
import is.azienda.Area;
import is.azienda.Azienda;
import is.azienda.Organigramma;
import is.item.ImageZoom;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Iterator;

/**
 * Tale classe estende JPanel. Definisce un pannello contenente
 * tutte le aree definite in azienda.
 * @author lucab
 */
public class ListAreaPanel extends JPanel {
    public ListAreaPanel(Mediator mediator){
        //Verifica validità mediator
        if (mediator==null) throw new IllegalArgumentException("Mediator non valido");

        Azienda azienda = mediator.getAzienda();

        //Colors
        Color gray = new Color(230,230,230);
        Color blue = new Color(3, 2, 179);
        Color blue2 = new Color(0,51,200);

        //Panel options
        setLayout(null);
        setBounds(0,0,1000,1000); //Confini JPanel

        //Header
        JPanel headPanel = new JPanel(null);
        headPanel.setBackground(Color.white); //Definizione sfondo
        headPanel.setBounds(0,0,1000,60); //Confini headPanel

        //Label of headPanel
        Font f = new Font("TimesNewRoman",Font.BOLD,23);
        Font f2 = new Font("TimesNewRoman",Font.ITALIC,15);

        JLabel head = new JLabel("Elenco aree organizzative");
        head.setFont(f);
        head.setForeground(Color.black);
        head.setBounds(10,7,380,50);

        //Aggiunta campi per visionare la lista delle aree
        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray); //Definizione sfondo
        fieldPanel.setBounds(0,60,1000,950); //Confini fieldPanel

        //Organigramma aziendale
        Organigramma org = azienda.getOrganigramma();

        //Tipologia colonne della tabella contenente le aree
        String[] columnNames = {"Nome Area","Numero Dipendenti","Nome Area di Riferimento","Stato"};
        Object[][] data = new Object[azienda.getNAreas()][columnNames.length];

        //Getting data
        Iterator<Area> it = org.iterator();
        int i = 0;
        while(it.hasNext()){
            Organigramma cur = (Organigramma) it.next();
            data[i][0] = cur.getName();
            data[i][1] = cur.getNEmployees();
            Organigramma orgPar = azienda.getParent(cur);
            data[i][2] = (orgPar==null) ? "Nessuna":orgPar.getName();
            data[i][3] = (!cur.getStateArea()) ? "BOZZA":"VALIDATA";
            i++;
        }

        //Table
        DataTable table = new DataTable(data, columnNames);
        //Dimension of columns
        TableColumn column = null;
        for (int j = 0;j<4;j++){
            column = table.getColumnModel().getColumn(j);
            if (j==0) column.setPreferredWidth(260);
            if (j==1) column.setPreferredWidth(85);
            if (j==2) column.setPreferredWidth(260);
            if (j==3) column.setPreferredWidth(85);
        }

        //ScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15,50,955,200);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //Permette lo scorrimento verticale

        //Search area
        JLabel searchLab = new JLabel("Cerca area: ");
        searchLab.setFont(f);
        searchLab.setForeground(blue);
        searchLab.setBounds(20,290,200,30);

        JLabel nameLab = new JLabel("Area: ");
        nameLab.setFont(f2);
        nameLab.setBounds(20,330,50,30);

        JTextField nameField = new JTextField(20); //Campo in cui bisogna inserire l'area che si vuole cercare
        nameField.setText("Digita nome area");
        nameField.setBounds(75,330,280,30);

        //Button
        JButton search = new JButton("Cerca");  //Button per la ricerca di una determinata area
        search.setForeground(Color.white);
        search.setBackground(blue2);
        search.setBounds(380,330,140,30);

        //Logo applicazione
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.25);
        ImageIcon image = icon.getImageIcon();
        JLabel lab = new JLabel(image);
        lab.setBounds(730,320,200,200);

        //Adding
        headPanel.add(head);
        fieldPanel.add(lab); fieldPanel.add(scrollPane);
        fieldPanel.add(searchLab); fieldPanel.add(search);
        fieldPanel.add(nameLab); fieldPanel.add(nameField);
        add(headPanel); add(fieldPanel);
        //Mediator
        mediator.setNameListArea(nameField);
        mediator.setSearchListArea(search);
        //Listener

        //Attraverso un mouseListener effettuo la rimozione
        //della scritta "Digita nome area", quando l'utente
        //clicca la prima volta sul campo
        nameField.addMouseListener(new MouseInputAdapter() {
            private boolean rimuoviScritta = true;
            @Override
            public void mouseClicked(MouseEvent e) {
                if (rimuoviScritta && nameField.getText().equals("Digita nome area")){
                    nameField.setText("");
                    rimuoviScritta = false;
                }
            }
        });
        search.addActionListener(e->mediator.widgetChanged(search));
    }
}//ListAreaPanel
