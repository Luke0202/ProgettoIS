package is.panels;

import is.dipendenti.Administrator;
import is.dipendenti.Employee;
import is.mediator.Mediator;
import is.organigramma.Azienda;
import is.organigramma.Organigramma;
import is.organigramma.OrganigrammaIF;
import is.shapes.DataTable;
import is.shapes.ImageZoom;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class AziendaPanel extends JPanel {
    private Azienda azienda;

    public AziendaPanel(Azienda azienda) {
        if (azienda == null) throw new IllegalArgumentException("Dato non valido");
        this.azienda = azienda;

        setLayout(null);
        Color blue = new Color(3, 2, 179);

        Color gray = new Color(230, 230, 230);
        setBounds(0, 0, 1000, 1000);
        //Header
        JPanel headPanel = new JPanel(null);
        headPanel.setBackground(Color.white);
        headPanel.setBounds(0, 0, 1000, 60);

        Font f = new Font("TimesNewRoman", Font.BOLD, 23);
        JLabel head = new JLabel("Dettagli azienda");
        head.setFont(f);
        head.setForeground(Color.black);
        head.setBounds(10, 7, 350, 50);
        headPanel.add(head);
        //Adding Fields
        JPanel fieldPanel = new JPanel(null);
        fieldPanel.setBackground(gray);
        fieldPanel.setBounds(0, 50, 1000, 950);
        f = new Font("TimesNewRoman", Font.ITALIC, 18);
        //Name Field
        JLabel nameLab = new JLabel("Nome Azienda: ");
        nameLab.setFont(f);
        nameLab.setForeground(blue);
        nameLab.setBounds(20, 15, 200, 30);
        JLabel nameField = new JLabel(azienda.getName());
        nameField.setBounds(20, 50, 280, 30);
        //Cod ATECO
        JLabel codLab = new JLabel("Codice ATECO: ");
        codLab.setFont(f);
        codLab.setForeground(blue);
        codLab.setBounds(20, 15, 200, 30);
        JLabel codField = new JLabel(String.valueOf(azienda.getCod()));
        codField.setBounds(20, 50, 280, 30);
        //President
        JLabel presLab = new JLabel("Presidente: ");
        presLab.setFont(f);
        presLab.setForeground(blue);
        JLabel presField = new JLabel(azienda.getPresidentSurname()+" "+azienda.getPresidentName());
        presField.setBounds(20, 50, 280, 30);
        //HeadQuarter
        JLabel headquarterLab = new JLabel("Sede centrale: ");
        headquarterLab.setFont(f);
        headquarterLab.setForeground(blue);
        JLabel headquarterField = new JLabel(azienda.getHeadquarter());
        headquarterField.setBounds(20, 50, 280, 30);
        //Number of Employees
        int N = azienda.getIDEmployees().size();
        JLabel nEmployeesLab = new JLabel("Numero dipendenti: ");
        nEmployeesLab.setFont(f);
        nEmployeesLab.setForeground(blue);
        JLabel nEmployeesField = new JLabel(String.valueOf(N));
        nEmployeesField.setBounds(20, 50, 280, 30);
        //Image
        ImageZoom icon = new ImageZoom(new ImageIcon(HomePanel.class.getResource("myLogo.png")), 0.25);
        ImageIcon image = icon.getImageIcon();
        //Label
        JLabel lab = new JLabel(image);
        lab.setBounds(730, 320, 200, 200);
        //Adding
        fieldPanel.add(nameLab); fieldPanel.add(nameField);
        fieldPanel.add(codLab); fieldPanel.add(codField);
        fieldPanel.add(presLab); fieldPanel.add(presField);
        fieldPanel.add(headquarterLab); fieldPanel.add(headquarterField);
        fieldPanel.add(nEmployeesLab); fieldPanel.add(nEmployeesField);
        fieldPanel.add(lab);
        add(fieldPanel); add(headPanel);
    }
}