package is;

import is.dipendenti.Administrator;
import is.dipendenti.Employee;
import is.dipendenti.Role;
import is.mediator.Mediator;
import is.organigramma.Azienda;
import is.organigramma.Organigramma;
import is.panels.*;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class Applicazione {
    public static void main(String[] args){

        /*
        //Building
        PrintWriter pw = new PrintWriter(System.out);
        TextBuilderIF builder = new TextBuilder(pw);
        String urlstring = null;
        TextParser tp = new TextParser(builder, urlstring);
        Azienda azienda = tp.build();
        */


        Mediator mediator = new Mediator(null);
        JFrame frame = new JFrame("Applicazione");
        mediator.setFrame(frame);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                int i = JOptionPane.showConfirmDialog(frame, "Vuoi uscire?");
                if (i == 0) System.exit(0);//SI
            }
        });



        JMenuBar menubar = new JMenuBar();
        JMenu area = new JMenu("Area"); menubar.add(area);
        JMenu role = new JMenu("Role"); menubar.add(role);
        JMenu association = new JMenu("Association"); menubar.add(association);
        frame.setJMenuBar(menubar);

        JMenuItem searchA = new JMenuItem("Search Area"); area.add(searchA);
        JMenuItem createA = new JMenuItem("Create Area"); area.add(createA);
        JMenuItem editA = new JMenuItem("Edit Area"); area.add(editA);
        JMenuItem remA = new JMenuItem("Remove Area"); area.add(remA);
        JMenuItem openA = new JMenuItem("Open Organization Chart"); area.add(openA);
        JMenuItem showA = new JMenuItem("Show Organization Chart"); area.add(showA);

        JMenuItem searchR = new JMenuItem("Search Role"); role.add(searchR);
        JMenuItem createR = new JMenuItem("Create Role"); role.add(createR);
        JMenuItem editR = new JMenuItem("Edit Role"); role.add(editR);
        JMenuItem remR = new JMenuItem("Remove Role"); role.add(remR);

        JMenuItem searchU = new JMenuItem("Search Association"); association.add(searchU);
        JMenuItem createU = new JMenuItem("Create Association"); association.add(createU);
        JMenuItem editU = new JMenuItem("Edit Association"); association.add(editU);
        JMenuItem remU = new JMenuItem("Remove Association"); association.add(remU);


        //Azienda azienda = new Azienda();

        mediator.setItem(searchA,searchR,searchU,createA,createR,createU,editA,editR,editU,remA,remR,remU,openA,showA);
        searchA.addActionListener(e -> mediator.menuChanged(searchA));
        searchR.addActionListener(e -> mediator.menuChanged(searchR));
        searchU.addActionListener(e -> mediator.menuChanged(searchU));
        createA.addActionListener(e -> mediator.menuChanged(createA));
        createR.addActionListener(e -> mediator.menuChanged(createR));
        createU.addActionListener(e -> mediator.menuChanged(createU));
        editA.addActionListener(e -> mediator.menuChanged(editA));
        editR.addActionListener(e -> mediator.menuChanged(editR));
        editU.addActionListener(e -> mediator.menuChanged(editU));
        remA.addActionListener(e -> mediator.menuChanged(remA));
        remR.addActionListener(e -> mediator.menuChanged(remR));
        remU.addActionListener(e -> mediator.menuChanged(remU));
        openA.addActionListener(e -> mediator.menuChanged(openA));
        showA.addActionListener(e -> mediator.menuChanged(showA));


        Organigramma o = new Organigramma("Consiglio di amministrazione","è il consiglio");
        Administrator admin = new Administrator(22,o);

        Role r1 = new Role("segretario","Consiglio di amministrazione","è il segretario");
        Role r2 = new Role("bidello","Consiglio di amministrazione","è il segretario2");
        Role r3 = new Role("recluta","Consiglio di amministrazione","è il segretario3");
        Role r4 = new Role("architetto","Consiglio di amministrazione","è il segretario4");
        Role r5 = new Role("ingegnere","Consiglio di amministrazione","è il segretario5");
        Role r6 = new Role("Progettista","Consiglio di amministrazione","è il segretario5");
        Role r7 = new Role("Impiegato","Consiglio di amministrazione","è il segretario5");
        Role r8 = new Role("Piromane","Consiglio di amministrazione","è il segretario5");
        Role r9 = new Role("Ciclista","Consiglio di amministrazione","è il segretario5");

        admin.addRole(r1);
        admin.addRole(r2);
        admin.addRole(r3);
        admin.addRole(r4);
        admin.addRole(r5);
        admin.addRole(r6);
        admin.addRole(r7);
        admin.addRole(r8);
        admin.addRole(r9);


        Employee emp1 = new Employee("Luca","Antonio","ddd",admin.giveID(), 333);
        admin.addEmployee(r1,emp1);
        Employee emp2 = new Employee("Mario","Antonio","ddd",admin.giveID(),331);
        admin.addEmployee(r2,emp2);
        Employee emp3 = new Employee("Gianni","Antonio","sa",admin.giveID(),332);
        admin.addEmployee(r3,emp3);
        Employee emp4 = new Employee("Armando","Antonio","dae",admin.giveID(),213);
        admin.addEmployee(r4,emp4);
        Employee emp5 = new Employee("Roberto","Antonio","dafe",admin.giveID(),233);
        admin.addEmployee(r5,emp5);
        Employee emp6 = new Employee("Fabio","Antonio","dafe",admin.giveID(),273);
        admin.addEmployee(r6,emp6);
        Employee emp7 = new Employee("Marco","Antonio","dafe",admin.giveID(),2366);
        admin.addEmployee(r7,emp7);
        Employee emp8 = new Employee("Tommaso","Antonio","dafe",admin.giveID(),2366);
        admin.addEmployee(r7,emp8);
        Employee emp9 = new Employee("Martino","Antonio","dafe",admin.giveID(),2366);
        admin.addEmployee(r7,emp9);

        Azienda azienda = new Azienda(220293, "Spacex","Florida","Elon","Musk",admin);
        //Panel1
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        switch(i){
            case 1:HomePanel home = new HomePanel(mediator); frame.add(home); break;
            case 2:CreateAreaPanel createArea = new CreateAreaPanel(mediator,admin); frame.add(createArea); break;
            case 3:ModAreaPanel modArea = new ModAreaPanel(mediator,admin,o); frame.add(modArea); break;
            case 4:ListAreaPanel listArea = new ListAreaPanel(admin); frame.add(listArea); break;
            case 5:AreaPanel areaP = new AreaPanel(o,admin,mediator); frame.add(areaP); break;
            case 6:CreateRolePanel createRole = new CreateRolePanel(mediator,admin); frame.add(createRole); break;
            case 7:ModRolePanel modRole = new ModRolePanel(mediator,admin,r1); frame.add(modRole); break;
            case 8:ListRolePanel listRole = new ListRolePanel(admin); frame.add(listRole); break;
            case 9:RolePanel roleP = new RolePanel(r7,admin,mediator); frame.add(roleP); break;
            case 10: CreateEmployeePanel assoPanel = new CreateEmployeePanel(mediator,admin); frame.add(assoPanel); break;
            case 11: ListEmployeePanel listEmployeePanel = new ListEmployeePanel(admin); frame.add(listEmployeePanel); break;
            case 12: EmployeePanel employeeP = new EmployeePanel(emp7,admin,mediator); frame.add(employeeP); break;
            case 13: AziendaPanel aziendaP = new AziendaPanel(azienda);
            default:  break;
        }
        //
        //
        //
        //
        //

        //
        //


        //Frame settings
        frame.setLocation(350,150);
        frame.setSize(1000,650);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
