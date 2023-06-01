package is.panels;

import is.dipendenti.Administrator;
import is.dipendenti.Employee;
import is.dipendenti.Role;
import is.mediator.Mediator;
import is.organigramma.Azienda;
import is.organigramma.Organigramma;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PanelTest {
    private JFrame frame;

    public void primaDiTutto(){
        frame = new JFrame("Applicazione");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                int i = JOptionPane.showConfirmDialog(frame, "Vuoi uscire?");
                if (i == 0) System.exit(0);//SI
            }
        });

    }
    public static void main(String[] args){
        PanelTest p = new PanelTest();
        p.primaDiTutto();
        p.primoTest();
    }
    public void primoTest(){

        //Dati Azienda
        Organigramma o = new Organigramma("Consiglio di amministrazione","è il consiglio");
        Administrator admin = new Administrator(o);


        String psw ="ccc5";

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


        Employee emp1 = new Employee("Luca","Antonio","ddd",admin.giveID());
        admin.addEmployee(r1,emp1);
        Employee emp2 = new Employee("Mario","Antonio","ddd",admin.giveID());
        admin.addEmployee(r2,emp2);
        Employee emp3 = new Employee("Gianni","Antonio","sa",admin.giveID());
        admin.addEmployee(r3,emp3);
        Employee emp4 = new Employee("Armando","Antonio","dae",admin.giveID());
        admin.addEmployee(r4,emp4);
        Employee emp5 = new Employee("Roberto","Antonio","dafe",admin.giveID());
        admin.addEmployee(r5,emp5);
        Employee emp6 = new Employee("Fabio","Antonio","dafe",admin.giveID());
        admin.addEmployee(r6,emp6);
        Employee emp7 = new Employee("Marco","Antonio","dafe",admin.giveID());
        admin.addEmployee(r7,emp7);
        Employee emp8 = new Employee("Tommaso","Antonio","dafe",admin.giveID());
        admin.addEmployee(r7,emp8);
        Employee emp9 = new Employee("Martino","Antonio","dafe",admin.giveID());
        admin.addEmployee(r7,emp9);

        Azienda azienda = new Azienda(220293, "Spacex","Florida","Aerospaziale",psw,admin);

        //Mediator
        Mediator mediator = new Mediator(); mediator.setFrame(frame); mediator.setAzienda(azienda);


        int i = 9;
        switch(i){
            case 1:LogPanel log = new LogPanel(mediator); frame.add(log); break;
            case 2:CreateAreaPanel createArea = new CreateAreaPanel(mediator); frame.add(createArea); break;
            case 3:ModAreaPanel modArea = new ModAreaPanel(o,mediator); frame.add(modArea); break;
            case 4:ListAreaPanel listArea = new ListAreaPanel(mediator); frame.add(listArea); break;
            case 5:AreaPanel areaP = new AreaPanel(o,mediator); frame.add(areaP); break;
            case 6:CreateRolePanel createRole = new CreateRolePanel(mediator); frame.add(createRole); break;
            case 7:ModRolePanel modRole = new ModRolePanel(r1,mediator); frame.add(modRole); break;
            case 8:ListRolePanel listRole = new ListRolePanel(mediator); frame.add(listRole); break;
            case 9:RolePanel roleP = new RolePanel(r7,mediator); frame.add(roleP); break;
            case 10: CreateEmployeePanel assoPanel = new CreateEmployeePanel(mediator); frame.add(assoPanel); break;
            case 11: ListEmployeePanel listEmployeePanel = new ListEmployeePanel(mediator); frame.add(listEmployeePanel); break;
            case 12: EmployeePanel employeeP = new EmployeePanel(emp7,admin,mediator); frame.add(employeeP); break;
            case 13: AziendaPanel aziendaP = new AziendaPanel(mediator); frame.add(aziendaP); break;
            case 14: CreateAziendaPanel createAzienda = new CreateAziendaPanel(mediator); frame.add(createAzienda); break;
            case 15: HomePanel home = new HomePanel(mediator); frame.add(home);
            default:  break;
        }

        //Frame settings
        frame.setLocation(350,150);
        frame.setSize(1000,650);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
