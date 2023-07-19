package is.panels;


import is.item.DataTable;
import is.azienda.Azienda;
import is.azienda.Employee;
import is.mediator.Mediator;
import is.item.ImageZoom;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Tale classe estende JPanel. Definisce un pannello
 * contenente l'elenco dei dipendenti dell'azienda.
 */
public class ListEmployeePanel extends JPanel {
    public ListEmployeePanel(Mediator mediator){
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
        JLabel head = new JLabel("Elenco dipendenti");
        head.setFont(f);
        head.setForeground(Color.black);
        head.setBounds(10,7,380,50);

        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray); //Definizione sfondo
        fieldPanel.setBounds(0,60,1000,950); //Confini fieldPanel

        //Tipologia colonne della tabella contenente i dipendenti
        String[] columnNames = {"Cognome","Nome","Email","ID","Numero ruoli","Numero Aree"};
        Object[][] data = new Object[azienda.getNEmployees()][columnNames.length];

        //Getting data
        int i = 0;
        for (Employee emp: azienda.getEmployees()){
            data[i][0] = emp.getSurname();
            data[i][1] = emp.getName();
            data[i][2] = emp.getEmail();
            data[i][3] = emp.getID();
            data[i][4] = azienda.getRoles(emp).size();
            data[i][5] = azienda.getAreasName(emp).size();
            i++;
        }

        //Table
        DataTable table = new DataTable(data, columnNames);
        //Dimension of columns
        TableColumn column = null;
        for (int j = 0;j<6;j++){
            column = table.getColumnModel().getColumn(j);
            if (j==0) column.setPreferredWidth(180);
            if (j==1) column.setPreferredWidth(135);
            if (j==2) column.setPreferredWidth(270);
            if (j==3) column.setPreferredWidth(60);
            if (j==4) column.setPreferredWidth(90);
            if (j==5) column.setPreferredWidth(90);
        }

        //ScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15,50,955,200);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //Consente lo scorrimento verticale

        //Search employee
        JLabel searchLab = new JLabel("Cerca dipendente: ");
        searchLab.setFont(f);
        searchLab.setForeground(blue);
        searchLab.setBounds(20,290,280,30);

        JLabel idLab = new JLabel("ID dipendente: ");
        idLab.setFont(f2);
        idLab.setBounds(20,330,110,30);

        JTextField idField = new JTextField(20); //Campo in cui bisogna inserire l'id del dipendente che si vuole cercare
        idField.setText("Digita ID dipendente");
        idField.setBounds(135,330,280,30);

        //Button
        JButton search = new JButton("Cerca");  //Button per la ricerca di un dipendente
        search.setForeground(Color.white);
        search.setBackground(blue2);
        search.setBounds(440,330,140,30);

        //Logo applicazione
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.25);
        ImageIcon image = icon.getImageIcon();
        JLabel lab = new JLabel(image);
        lab.setBounds(730,320,200,200);

        //Adding
        headPanel.add(head);
        fieldPanel.add(scrollPane); fieldPanel.add(lab);
        fieldPanel.add(searchLab); fieldPanel.add(search);
        fieldPanel.add(idLab); fieldPanel.add(idField);
        add(fieldPanel); add(headPanel);
        //Mediator
        mediator.setIdListEmployee(idField);
        mediator.setSearchListEmployee(search);
        //Listener

        //Attraverso un mouseListener viene effettuata la rimozione
        //della scritta "Digita ID dipendente", quando l'utente
        //clicca per la prima volta sul campo
        idField.addMouseListener(new MouseInputAdapter() {
            private boolean rimuoviScritta = true;
            @Override
            public void mouseClicked(MouseEvent e) {
                if (rimuoviScritta && idField.getText().equals("Digita ID dipendente")){
                    idField.setText("");
                    rimuoviScritta = false;
                }
            }
        });

        search.addActionListener(e->mediator.widgetChanged(search));
    }
}//ListEmployeePanel
