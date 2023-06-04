package is.panels;

import is.organigramma.Employee;
import is.organigramma.Role;
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
        String psw ="ccc5";
        Organigramma org = new Organigramma("Consiglio di amministrazione","è il consiglio");
        Azienda azienda = new Azienda("220293", "Spacex","Florida","Aerospaziale",psw,org);


        Role r1 = new Role("segretario","Consiglio di amministrazione","è il segretario");
        Role r2 = new Role("bidello","Consiglio di amministrazione","è il segretario2");
        Role r3 = new Role("recluta","Consiglio di amministrazione","è il segretario3");
        Role r4 = new Role("architetto","Consiglio di amministrazione","è il segretario4");
        Role r5 = new Role("ingegnere","Consiglio di amministrazione","è il segretario5");
        Role r6 = new Role("Progettista","Consiglio di amministrazione","è il segretario5");
        Role r7 = new Role("Impiegato","Consiglio di amministrazione","è il segretario5");
        Role r8 = new Role("Piromane","Consiglio di amministrazione","è il segretario5");
        Role r9 = new Role("Ciclista","Consiglio di amministrazione","è il segretario5");

        azienda.addRole(r1);
        azienda.addRole(r2);
        azienda.addRole(r3);
        azienda.addRole(r4);
        azienda.addRole(r5);
        azienda.addRole(r6);
        azienda.addRole(r7);
        azienda.addRole(r8);
        azienda.addRole(r9);


        Employee emp1 = new Employee("Luca","Antonio","ddd",azienda.giveID());
        azienda.addEmployee(r1,emp1);
        Employee emp2 = new Employee("Mario","Antonio","ddd",azienda.giveID());
        azienda.addEmployee(r2,emp2);
        Employee emp3 = new Employee("Gianni","Antonio","sa",azienda.giveID());
        azienda.addEmployee(r3,emp3);
        Employee emp4 = new Employee("Armando","Antonio","dae",azienda.giveID());
        azienda.addEmployee(r4,emp4);
        Employee emp5 = new Employee("Roberto","Antonio","dafe",azienda.giveID());
        azienda.addEmployee(r5,emp5);
        Employee emp6 = new Employee("Fabio","Antonio","dafe",azienda.giveID());
        azienda.addEmployee(r6,emp6);
        Employee emp7 = new Employee("Marco","Antonio","dafe",azienda.giveID());
        azienda.addEmployee(r7,emp7);
        Employee emp8 = new Employee("Tommaso","Antonio","dafe",azienda.giveID());
        azienda.addEmployee(r8,emp8);
        Employee emp9 = new Employee("Martino","Antonio","dafe",azienda.giveID());
        azienda.addEmployee(r9,emp9);



        //Mediator
        Mediator mediator = new Mediator(); mediator.setFrame(frame); mediator.setAzienda(azienda);


        int i = 9;
        switch(i){
            case 1:LogPanel log = new LogPanel(mediator); frame.add(log); break;
            case 2:CreateAreaPanel createArea = new CreateAreaPanel(mediator); frame.add(createArea); break;
            case 3:ModAreaPanel modArea = new ModAreaPanel(org,mediator); frame.add(modArea); break;
            case 4:ListAreaPanel listArea = new ListAreaPanel(mediator); frame.add(listArea); break;
            case 5:AreaPanel areaP = new AreaPanel(org,mediator); frame.add(areaP); break;
            case 6:CreateRolePanel createRole = new CreateRolePanel(mediator); frame.add(createRole); break;
            case 7:ModRolePanel modRole = new ModRolePanel(r1,mediator); frame.add(modRole); break;
            case 8:ListRolePanel listRole = new ListRolePanel(mediator); frame.add(listRole); break;
            case 9:RolePanel roleP = new RolePanel(r7,mediator); frame.add(roleP); break;
            case 10: CreateEmployeePanel assoPanel = new CreateEmployeePanel(mediator); frame.add(assoPanel); break;
            case 11: ListEmployeePanel listEmployeePanel = new ListEmployeePanel(mediator); frame.add(listEmployeePanel); break;
            case 12: EmployeePanel employeeP = new EmployeePanel(emp7,mediator); frame.add(employeeP); break;
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
