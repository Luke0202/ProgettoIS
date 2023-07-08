package is.panels;


import is.azienda.Azienda;
import is.azienda.Role;
import is.mediator.Mediator;
import is.item.ImageZoom;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

/**
 * Tale classe estende JPanel. Definisce un pannello per
 * l'aggiunta di un dipendente in azienda. Al dipendente
 * bisogna assegnare un ruolo tra quelli disponibili.
 * @author lucab
 */
public class CreateEmployeePanel extends JPanel {

    public CreateEmployeePanel(Mediator mediator) {
        //Verifica validità mediator
        if (mediator == null) throw new IllegalArgumentException("Mediator non valido");

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
        JLabel head = new JLabel("Inserimento nuovo dipendente");
        head.setFont(f);
        head.setForeground(Color.black);
        head.setBounds(10,7,380,50);

        //Aggiunta campi di creazione dipendente
        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray); //Definizione sfondo
        fieldPanel.setBounds(0,50,1000,950); //Confini fieldPanel
        f = new Font("TimesNewRoman",Font.ITALIC,20);
        //Name Field
        JLabel nameLab = new JLabel("Nome: ");
        nameLab.setFont(f);
        nameLab.setForeground(blue);
        nameLab.setBounds(20,15,200,30);

        JTextField nameField = new JTextField(20); //Campo contenente il nome del dipendente
        nameField.setBounds(20,50,280,30);
        //Surname Field
        JLabel surnameLab = new JLabel("Cognome: ");
        surnameLab.setFont(f);
        surnameLab.setForeground(blue);
        surnameLab.setBounds(350,15,280,30);

        JTextField surnameField = new JTextField(20); //Campo contenente il cognome del dipendente
        surnameField.setBounds(350,50,280,30);
        //Email Field
        JLabel emailLab = new JLabel("Email: ");
        emailLab.setFont(f);
        emailLab.setForeground(blue);
        emailLab.setBounds(680,15,200,30);

        JTextField emailField = new JTextField(20); //Campo contenente l'email del dipendente
        emailField.setBounds(680,50,290,30);
        //Role Field
        JLabel roleLab = new JLabel("Ruolo: ");
        roleLab.setFont(f);
        roleLab.setForeground(blue);
        roleLab.setBounds(20,150,200,30);

        String[] array = findRoles(mediator.getAzienda());
        JComboBox<String> roleComboBox = new JComboBox<>(array); //ComboBox contenente i ruoli dell'azienda
        roleComboBox.setBounds(20,185,400,30);
        //SaveButtons
        JButton save = new JButton("SALVA");  //Button di salvataggio
        save.setForeground(Color.white);
        save.setBackground(blue2);
        save.setBounds(20,350,200,30);
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
        fieldPanel.add(roleLab); fieldPanel.add(roleComboBox);
        fieldPanel.add(save);
        fieldPanel.add(lab);
        add(headPanel);
        add(fieldPanel);
        //Using mediator
        mediator.setNameCreateEmployee(nameField);
        mediator.setSurnameCreateEmployee(surnameField);
        mediator.setEmailCreateEmployee(emailField);
        mediator.setRoleComboCreateEmployee(roleComboBox);
        mediator.setSaveCreateEmployee(save);
        //Listener
        save.addActionListener(e -> mediator.widgetChanged(save));
    }

    /**
     * Restituisce la lista dei ruoli definiti in azienda
     * @param azienda Azienda da visionare
     * @return lista di stringhe
     */
    private static String[] findRoles(Azienda azienda){
        //Ricavo ruoli dell'azienda
        HashSet<Role> roles = azienda.getRoles();

        HashSet<String> rol = new HashSet<>();
        for (Role r:roles){
            rol.add(r.getName()+" - "+r.getArea());
        }

        //Conversione in String[]
        String[] array;
        if (rol.size()==0){
            array = new String[1];
            array[0] = "Nessun ruolo presente";
        } else{
            array = rol.toArray(new String[rol.size()]);
        }
        return array;
    }
}//CreateEmployeePanel