package is.panels;

import is.mediator.Mediator;
import is.organigramma.Azienda;
import is.shapes.ImageZoom;
import javax.swing.*;
import java.awt.*;

public class AziendaPanel extends JPanel {
    private Mediator mediator;

    public AziendaPanel(Mediator mediator) {
        if (mediator==null) throw new IllegalArgumentException("Mediator non valido");
        this.mediator=mediator;
        Azienda azienda = mediator.getAzienda();

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
        f = new Font("TimesNewRoman", Font.ITALIC, 20);
        Font f2 = new Font("TimesNewRoman", Font.ITALIC, 18);
        //Name Field
        JLabel nameLab = new JLabel("Nome azienda: ");
        nameLab.setFont(f);
        nameLab.setForeground(blue);
        nameLab.setBounds(20, 15, 150, 30);
        JLabel nameField = new JLabel(azienda.getName()); nameField.setFont(f2);
        nameField.setBounds(190, 15, 280, 30);
        //Cod ATECO
        JLabel codLab = new JLabel("Codice ATECO: ");
        codLab.setFont(f);
        codLab.setForeground(blue);
        codLab.setBounds(455, 15, 200, 30);
        JLabel codField = new JLabel(String.valueOf(azienda.getCod())); codField.setFont(f2);
        codField.setBounds(630, 15, 280, 30);
        //President
        JLabel presLab = new JLabel("Settore azienda: ");
        presLab.setFont(f);
        presLab.setForeground(blue);
        presLab.setBounds(20,85,280,30);
        JLabel presField = new JLabel(azienda.getType());
        presField.setFont(f2);
        presField.setBounds(200, 85, 280, 30);
        //HeadQuarter
        JLabel headquarterLab = new JLabel("Sede centrale: ");
        headquarterLab.setFont(f);
        headquarterLab.setForeground(blue); headquarterLab.setBounds(455,85,280,30);
        JLabel headquarterField = new JLabel(azienda.getHeadquarter());
        headquarterField.setFont(f2);
        headquarterField.setBounds(620, 85, 280, 30);
        //Number of Employees
        int N = azienda.getIDEmployees().size();
        JLabel nEmployeesLab = new JLabel("Numero dipendenti: ");
        nEmployeesLab.setFont(f);
        nEmployeesLab.setForeground(blue);
        nEmployeesLab.setBounds(20,155,200,30);
        JLabel nEmployeesField = new JLabel(String.valueOf(N)); nEmployeesField.setFont(f2);
        nEmployeesField.setBounds(235, 155, 280, 30);
        //Image
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")), 0.25);
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