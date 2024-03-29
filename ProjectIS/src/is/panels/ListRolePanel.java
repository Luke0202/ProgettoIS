package is.panels;

import is.item.DataTable;
import is.azienda.Azienda;
import is.azienda.Role;
import is.mediator.Mediator;
import is.item.ImageZoom;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Tale classe estende JPanel. Definisce un pannello
 * contenente l'elenco dei ruoli definiti in azienda.
 * @author lucab
 */
public class ListRolePanel extends JPanel {
    public ListRolePanel(Mediator mediator){
        //Verifica validità mediator
        if (mediator==null) throw new IllegalArgumentException("Mediator non valido");

        Azienda azienda = mediator.getAzienda();

        //Colors
        Color blue = new Color(3, 2, 179);
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
        Font f2 = new Font("TimesNewRoman",Font.ITALIC,15);
        JLabel head = new JLabel("Elenco ruoli");
        head.setFont(f);
        head.setForeground(Color.black);
        head.setBounds(10,7,380,50);

        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray); //Definizione sfondo
        fieldPanel.setBounds(0,60,1000,950); //Confini fieldPanel

        //Tipologia colonne della tabella contenente i ruoli
        String[] columnNames = {"Nome Ruolo","Nome Area","Numero Dipendenti"};
        Object[][] data = new Object[azienda.getNRoles()][columnNames.length];

        //Getting data
        int i = 0;

        for(Role r: azienda.getRoles()){
            data[i][0]=r.getName();
            data[i][1]=r.getArea();
            data[i][2]=azienda.getEmployee(r).size();
            i++;
        }

        //Table
        DataTable table = new DataTable(data, columnNames);
        //Dimension of columns
        TableColumn column = null;
        for (int j = 0;j<3;j++){
            column = table.getColumnModel().getColumn(j);
            if (j==0) column.setPreferredWidth(200);
            if (j==1) column.setPreferredWidth(220);
            if (j==2) column.setPreferredWidth(140);
        }

        //ScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15,50,955,200);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //Consente solo lo scorrimento verticale
        //Search role
        JLabel searchLab = new JLabel("Cerca ruolo: ");
        searchLab.setFont(f);
        searchLab.setForeground(blue);
        searchLab.setBounds(20,290,200,30);

        JLabel roleLab = new JLabel("Ruolo: ");
        roleLab.setFont(f2);
        roleLab.setBounds(20,330,50,30);

        JTextField nameField = new JTextField(20); //Campo in cui bisogna inserire il nome del ruolo che si vuole cercare
        nameField.setText("Digita nome ruolo");
        nameField.setBounds(80,330,280,30);

        JLabel areaLab = new JLabel("Area: ");
        areaLab.setFont(f2);
        areaLab.setBounds(20,375,50,30);

        JTextField areaField = new JTextField(20); //Campo in cui bisogna inserire il nome dell'area che si vuole cercare
        areaField.setText("Digita nome area di riferimento");
        areaField.setBounds(80,375,280,30);

        //Button
        JButton search = new JButton("Cerca"); //Button per la ricerca di un ruolo
        search.setForeground(Color.white);
        search.setBackground(blue2);
        search.setBounds(375,345,140,30);

        //Logo applicazione
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.25);
        ImageIcon image = icon.getImageIcon();
        JLabel lab = new JLabel(image);
        lab.setBounds(730,320,200,200);

        //Adding
        headPanel.add(head);
        fieldPanel.add(lab); fieldPanel.add(scrollPane);
        fieldPanel.add(searchLab); fieldPanel.add(search);
        fieldPanel.add(roleLab); fieldPanel.add(nameField);
        fieldPanel.add(areaLab); fieldPanel.add(areaField);
        add(headPanel); add(fieldPanel);
        //Mediator
        mediator.setNameListRole(nameField);
        mediator.setAreaListRole(areaField);
        mediator.setSearchListRole(search);
        //Listener

        //Attraverso un mouseListener viene effettuata la rimozione
        //della scritta "Digita nome ruolo", quando l'utente
        //clicca per la prima volta sul campo
        nameField.addMouseListener(new MouseInputAdapter() {
            private boolean rimuoviScritta = true;
            @Override
            public void mouseClicked(MouseEvent e) {
                if (rimuoviScritta && nameField.getText().equals("Digita nome ruolo")){
                    nameField.setText("");
                    rimuoviScritta = false;
                }
            }
        });

        //Attraverso un mouseListener viene effettuata la rimozione
        //della scritta "Digita nome area di riferimento", quando l'utente
        //clicca per la prima volta sul campo
        areaField.addMouseListener(new MouseInputAdapter() {
            private boolean rimuoviScritta = true;
            @Override
            public void mouseClicked(MouseEvent e) {
                if (rimuoviScritta && areaField.getText().equals("Digita nome area di riferimento")){
                    areaField.setText("");
                    rimuoviScritta = false;
                }
            }
        });

        search.addActionListener(e->mediator.widgetChanged(search));
    }
}//ListRolePanel
