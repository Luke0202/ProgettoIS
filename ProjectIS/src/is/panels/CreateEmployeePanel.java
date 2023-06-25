package is.panels;


import is.organigramma.Azienda;
import is.organigramma.Role;
import is.mediator.Mediator;
import is.decorator.ImageZoom;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class CreateEmployeePanel extends JPanel {
    private Mediator mediator;
    public CreateEmployeePanel(Mediator mediator) {
        if (mediator == null) throw new IllegalArgumentException("Mediator non valido");

        setLayout(null);
        Color blue = new Color(3,2,179);
        Color blue2 = new Color(0,51,200);
        Color gray = new Color(230,230,230);
        setBounds(0,0,1000,1000);
        //Header
        JPanel headPanel = new JPanel(null); headPanel.setBackground(Color.white);
        headPanel.setBounds(0,0,1000,60);

        Font f = new Font("TimesNewRoman",Font.BOLD,23);
        JLabel head = new JLabel("Inserimento nuovo dipendente");
        head.setFont(f); head.setForeground(Color.black); head.setBounds(10,7,380,50);
        headPanel.add(head);
        //Adding Fields
        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray);
        fieldPanel.setBounds(0,50,1000,950);
        f = new Font("TimesNewRoman",Font.ITALIC,20);
        //Name Field
        JLabel nameLab = new JLabel("Nome: ");
        nameLab.setFont(f); nameLab.setForeground(blue); nameLab.setBounds(20,15,200,30);
        JTextField nameField = new JTextField(20);
        nameField.setBounds(20,50,280,30);
        //Surname Field
        JLabel surnameLab = new JLabel("Cognome: ");
        surnameLab.setFont(f); surnameLab.setForeground(blue); surnameLab.setBounds(350,15,280,30);
        JTextField surnameField = new JTextField(20);
        surnameField.setBounds(350,50,280,30);
        //Email Field
        JLabel emailLab = new JLabel("Email: ");
        emailLab.setFont(f); emailLab.setForeground(blue); emailLab.setBounds(680,15,200,30);
        JTextField emailField = new JTextField(20);
        emailField.setBounds(680,50,280,30);
        //Role Field
        JLabel roleLab = new JLabel("Ruolo: ");
        roleLab.setFont(f); roleLab.setForeground(blue); roleLab.setBounds(20,150,200,30);
        String[] array = findRoles(mediator.getAzienda());
        JComboBox<String> roleComboBox = new JComboBox<>(array);
        roleComboBox.setBounds(20,185,350,30);
        //SaveButtons
        JButton save = new JButton("SALVA");  save.setForeground(Color.white); save.setBackground(blue2);
        save.setBounds(20,350,200,30);
        //Image
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")),0.25);
        ImageIcon image = icon.getImageIcon();
        JLabel lab = new JLabel(image);
        lab.setBounds(730,320,200,200);

        //Adding
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
        mediator.setRoleCreateEmployee(roleComboBox);
        mediator.setSaveCreateEmployee(save);
        save.addActionListener(e -> mediator.widgetChanged(save));
    }
    private String[] findRoles(Azienda azienda){
        HashSet<Role> roles = azienda.getRoles();

        HashSet<String> rol = new HashSet<>();
        for (Role r:roles){
            rol.add(r.getName()+" - "+r.getArea());
        }
        String[] array;
        if (rol.size()==0){
            array = new String[1];
            array[0] = "Nessun ruolo presente";
        } else{
            array = rol.toArray(new String[rol.size()]);
        }
        return array;
    }
}