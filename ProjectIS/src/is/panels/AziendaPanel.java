package is.panels;

import is.mediator.Mediator;
import is.organigramma.Azienda;
import is.decorator.ImageZoom;
import javax.swing.*;
import java.awt.*;

/**
 * Tale classe estende JPanel. Definisce il pannello contenente
 * i dettagli aziendali, quali nome, codice ATECO, settore, sede,
 * numero dipendenti, numero aree e numero ruoli.
 * @author lucab
 */
public class AziendaPanel extends JPanel {

    public AziendaPanel(Mediator mediator) {
        //Verifica validità mediator
        if (mediator==null) throw new IllegalArgumentException("Mediator non valido");

        Azienda azienda = mediator.getAzienda();

        //Colors
        Color blue = new Color(3, 2, 179);
        Color gray = new Color(230, 230, 230);
        //Panel options
        setLayout(null);
        setBounds(0, 0, 1000, 1000);//Confini JPanel
        //Header
        JPanel headPanel = new JPanel(null);
        headPanel.setBackground(Color.white); //Definizione sfondo
        headPanel.setBounds(0, 0, 1000, 60); //Confini headPanel
        //Label of headPanel
        Font f = new Font("TimesNewRoman", Font.BOLD, 23);
        JLabel head = new JLabel("Dettagli azienda");
        head.setFont(f);
        head.setForeground(Color.black);
        head.setBounds(10, 7, 350, 50);
        //Aggiungendo i campi dell'azienda
        JPanel fieldPanel = new JPanel(null); //Pannello che contiene i campi dell'azienda
        fieldPanel.setBackground(gray); //Definizione sfondo
        fieldPanel.setBounds(0, 50, 1000, 950); //Confini fieldPanel
        //Name Field
        f = new Font("TimesNewRoman", Font.ITALIC, 20);
        Font f2 = new Font("TimesNewRoman", Font.ITALIC, 18);
        JLabel nameLab = new JLabel("Nome azienda: ");
        nameLab.setFont(f);
        nameLab.setForeground(blue);
        nameLab.setBounds(20, 15, 150, 30);

        JLabel nameField = new JLabel(azienda.getName()); //Campo contenente il nome dell'azienda
        nameField.setFont(f2);
        nameField.setBounds(190, 15, 280, 30);
        //Codice ATECO
        JLabel codLab = new JLabel("Codice ATECO: ");
        codLab.setFont(f);
        codLab.setForeground(blue);
        codLab.setBounds(455, 15, 200, 30);

        JLabel codField = new JLabel(String.valueOf(azienda.getCod())); //Campo contenente il codice ATECO dell'azienda
        codField.setFont(f2);
        codField.setBounds(630, 15, 280, 30);
        //Settore
        JLabel presLab = new JLabel("Settore azienda: ");
        presLab.setFont(f);
        presLab.setForeground(blue);
        presLab.setBounds(20,85,280,30);

        JLabel presField = new JLabel(azienda.getType()); //Campo contenente il settore dell'azienda
        presField.setFont(f2);
        presField.setBounds(200, 85, 280, 30);
        //HeadQuarter
        JLabel headquarterLab = new JLabel("Sede centrale: ");
        headquarterLab.setFont(f);
        headquarterLab.setForeground(blue);
        headquarterLab.setBounds(455,85,280,30);

        JLabel headquarterField = new JLabel(azienda.getHeadquarter()); //Campo contenente la sede centrale dell'azienda
        headquarterField.setFont(f2);
        headquarterField.setBounds(620, 85, 280, 30);
        //Number of Employees
        int N = azienda.getNEmployees(); //Numero dipendenti in azienda
        JLabel nEmployeesLab = new JLabel("Numero dipendenti: ");
        nEmployeesLab.setFont(f);
        nEmployeesLab.setForeground(blue);
        nEmployeesLab.setBounds(20,155,200,30);

        JLabel nEmployeesField = new JLabel(String.valueOf(N)); //Campo contenente il numero dei dipendenti in azienda
        nEmployeesField.setFont(f2);
        nEmployeesField.setBounds(235, 155, 280, 30);
        //Number of Areas
        int M = azienda.getNAreas(); //Numero aree in azienda
        JLabel nAreasLab = new JLabel("Numero aree: ");
        nAreasLab.setFont(f);
        nAreasLab.setForeground(blue);
        nAreasLab.setBounds(455,155,200,30);

        JLabel nAreasField = new JLabel(String.valueOf(M)); //Campo contenente il numero delle aree in azienda
        nAreasField.setFont(f2);
        nAreasField.setBounds(615, 155, 280, 30);
        //Number of Roles
        int X = azienda.getRoles().size(); //Numero ruoli in azienda
        JLabel nRolesLab = new JLabel("Numero ruoli: ");
        nRolesLab.setFont(f);
        nRolesLab.setForeground(blue);
        nRolesLab.setBounds(20,225,200,30);

        JLabel nRolesField = new JLabel(String.valueOf(X)); //Campo contenente il numero di ruoli in azienda
        nRolesField.setFont(f2);
        nRolesField.setBounds(235, 225, 280, 30);
        //Logo applicazione
        ImageZoom icon = new ImageZoom(new ImageIcon(LogPanel.class.getResource("myLogo.png")), 0.25);
        ImageIcon image = icon.getImageIcon();
        JLabel lab = new JLabel(image);
        lab.setBounds(730, 320, 200, 200);
        //Adding
        headPanel.add(head);
        fieldPanel.add(nameLab); fieldPanel.add(nameField);
        fieldPanel.add(codLab); fieldPanel.add(codField);
        fieldPanel.add(presLab); fieldPanel.add(presField);
        fieldPanel.add(headquarterLab); fieldPanel.add(headquarterField);
        fieldPanel.add(nEmployeesLab); fieldPanel.add(nEmployeesField);
        fieldPanel.add(nAreasLab); fieldPanel.add(nAreasField);
        fieldPanel.add(nRolesLab); fieldPanel.add(nRolesField);
        fieldPanel.add(lab);
        add(fieldPanel); add(headPanel);
    }
}//AziendaPanel