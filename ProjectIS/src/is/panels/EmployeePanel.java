package is.panels;

import is.DataTable;
import is.organigramma.Azienda;
import is.organigramma.Employee;
import is.organigramma.Role;
import is.mediator.Mediator;
import is.decorator.ImageZoom;
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.HashSet;

/**
 * Tale classe estende JPanel. Definisce un pannello di
 * gestione di un singolo dipendente.
 * @author lucab
 */
public class EmployeePanel extends JPanel {

    public EmployeePanel(Employee emp,Mediator mediator) {
        //Verifica validità dati
        if (emp == null || mediator==null) throw new IllegalArgumentException("Dati non validi");

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
        JLabel head = new JLabel("Dettaglio dipendente");
        head.setFont(f); head.setForeground(Color.black);
        head.setBounds(10,7,350,50);

        //Aggiunta campi per la gestione di un dipendente
        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray); //Definizione sfondo
        fieldPanel.setBounds(0,50,1000,950); //Confini fieldPanel
        f = new Font("TimesNewRoman",Font.ITALIC,20);
        //Name Field
        JLabel nameLab = new JLabel("Nome: ");
        nameLab.setFont(f);
        nameLab.setForeground(blue);
        nameLab.setBounds(20,15,200,30);

        JTextField nameField = new JTextField(20); //Contiene il nome del dipendente
        nameField.setText(emp.getName());
        nameField.setEditable(false);
        nameField.setBounds(20,50,280,30);
        //Surname Field
        JLabel surnameLab = new JLabel("Cognome: ");
        surnameLab.setFont(f);
        surnameLab.setForeground(blue);
        surnameLab.setBounds(350,15,200,30);

        JTextField surnameField = new JTextField(20); //Contiene il cognome del dipendente
        surnameField.setText(emp.getSurname());
        surnameField.setEditable(false);
        surnameField.setBounds(350,50,280,30);
        //Email Label
        JLabel emailLab = new JLabel("Email: ");
        emailLab.setFont(f);
        emailLab.setForeground(blue);
        emailLab.setBounds(680,15,200,30);

        JTextField emailField = new JTextField(20); //Contiene l'email del dipendente
        emailField.setText(emp.getEmail());
        emailField.setEditable(false);
        emailField.setBounds(680,50,280,30);
        //ID Label
        JLabel IDLab = new JLabel("ID: ");
        IDLab.setFont(f);
        IDLab.setForeground(blue);
        IDLab.setBounds(20,100,200,30);

        JTextField IDField = new JTextField(20); //Contiene l'ID del dipendente
        IDField.setText(String.valueOf(emp.getID()));
        IDField.setEditable(false);
        IDField.setBounds(20,140,280,30);
        //Button
        JButton removeButton = new JButton("Elimina dipendente"); //Button per licenziare un dipendente
        removeButton.setForeground(Color.white);
        removeButton.setBackground(blue2);
        removeButton.setBounds(400,140,150,30);
        //Roles of employee
        JLabel rolesLab = new JLabel("Ruoli dipendente: ");
        rolesLab.setFont(f);
        rolesLab.setForeground(blue);
        rolesLab.setBounds(20,180,200,30);

        //Getting roles
        HashSet<Role> roles = mediator.getAzienda().getRoles(emp);

        //Tipologia colonne della tabella contenente i ruoli
        String[] columnNames = {"Nome","Area"};
        Object[][] data = new Object[roles.size()][columnNames.length];

        //Getting data
        int i = 0;
        for(Role r:roles){
            data[i][0]=r.getName();
            data[i][1]=r.getArea();
            i++;
        }

        //Table
        DataTable table = new DataTable(data, columnNames);

        //Dimension of columns
        TableColumn column = null;
        for (int j = 0;j<2;j++){
            column = table.getColumnModel().getColumn(j);
            if (j==0) column.setPreferredWidth(180);
            if (j==1) column.setPreferredWidth(240);
        }

        //ScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20,220,420,130);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //Consente solo lo scorrimento verticale

        //Ruoli assegnabili ad un dipendente
        JLabel rolesTotLab = new JLabel("Ruoli disponibili: ");
        rolesTotLab.setFont(f);
        rolesTotLab.setForeground(blue);
        rolesTotLab.setBounds(20,360,200,30);

        String[] array = findRoles(emp,mediator.getAzienda());
        JComboBox<String> roleComboBox = new JComboBox<>(array); //ComboBox contenente i ruoli assegnabili ad un dipendente
        roleComboBox.setBounds(20,400,350,30);

        //Button
        JButton newRoleButton = new JButton("Aggiungi ruolo"); //Button per aggiungere un ruolo ad un dipendente
        newRoleButton.setForeground(Color.white);
        newRoleButton.setBackground(blue2);
        newRoleButton.setBounds(400,400,150,30);
        newRoleButton.setEnabled(!roleComboBox.getItemAt(0).equals("Nessun ruolo presente"));
        //Logo applicazione
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.25);
        ImageIcon image = icon.getImageIcon();
        JLabel lab = new JLabel(image);
        lab.setBounds(730,320,200,200);

        //Adding
        headPanel.add(head);
        fieldPanel.add(nameLab); fieldPanel.add(nameField);
        fieldPanel.add(surnameLab); fieldPanel.add(surnameField);
        fieldPanel.add(emailLab); fieldPanel.add(emailField);
        fieldPanel.add(IDLab); fieldPanel.add(IDField); fieldPanel.add(removeButton);
        fieldPanel.add(rolesLab); fieldPanel.add(scrollPane);
        fieldPanel.add(lab); fieldPanel.add(rolesTotLab);
        fieldPanel.add(roleComboBox); fieldPanel.add(newRoleButton);
        add(headPanel); add(fieldPanel);

        //Mediator
        mediator.setIdEmployee(IDField);
        mediator.setNewRoleEmployee(newRoleButton);
        mediator.setRemoveEmployee(removeButton);
        mediator.setRoleEmployee(roleComboBox);
        //Listeners
        newRoleButton.addActionListener(e->mediator.widgetChanged(newRoleButton));
        removeButton.addActionListener(e->mediator.widgetChanged(removeButton));
    }

    /**
     * Restituisce la lista dei ruoli che si possono assegnare
     * ad un determinato dipendente. La lista non comprende i ruoli
     * che già possiede.
     * @param emp
     * @param azienda
     * @return
     */
    private static String[] findRoles(Employee emp,Azienda azienda){
        //Ricavo ruoli che si possono assegnare al dipendente emp
        HashSet<String> ruoliValidi = new HashSet<>();

        //Insieme dei ruoli che il dipendente già possiede
        HashSet<Role> rolesOfEmp = azienda.getRoles(emp);

        //Aggiunta ruoli validi
        for (Role r:azienda.getRoles()){
            if (!rolesOfEmp.contains(r))
                ruoliValidi.add(r.getName()+" - "+r.getArea());
        }

        //Conversione in String[]
        String[] array;
        if (ruoliValidi.size()==0){
            array = new String[1];
            array[0] = "Nessun ruolo presente";
        } else{
            array = ruoliValidi.toArray(new String[ruoliValidi.size()]);
        }
        return array;
    }
}//EmployeePanel
