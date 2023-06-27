package is.panels;

import is.organigramma.Employee;
import is.organigramma.Role;
import is.mediator.Mediator;
import is.organigramma.Azienda;
import is.organigramma.Organigramma;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Tale classe effettua un testing sui pannelli definiti
 * nel package is.panels. In base a un indice è possibile
 * selezionare il pannello che si vuole mostrare.
 * Lo scopo unico della classe è quello di visionare un singolo
 * pannello, pertanto la pressione di buttons potrebbe generare
 * errori.
 * @author lucab
 */
public class PanelTest {
    private JFrame frame;

    public PanelTest(int j){
        frame = new JFrame("Applicazione");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                int i = JOptionPane.showConfirmDialog(frame, "Vuoi uscire?");
                if (i == 0) System.exit(0);//SI
            }
        });

        //Definizione Aree
        Organigramma o = new Organigramma("Azienda","");
        Organigramma o1 = new Organigramma("Consiglio di amministrazione","");
        Organigramma o2 = new Organigramma("Area vendite","");
        Organigramma o3 = new Organigramma("Custom care","");
        Organigramma o4 = new Organigramma("Marketing","");
        Organigramma o5 = new Organigramma("Acquisti","");
        Organigramma o6 = new Organigramma("Produzione","");

        //Tree Structure
        o.addChild(o1);
        o1.addChild(o2);
        o1.addChild(o5);
        o1.addChild(o6);
        o2.addChild(o3);
        o2.addChild(o4);

        //Definizione Azienda
        String psw ="1234";//Password aziendale
        Azienda azienda = new Azienda("010101", "Spacex","Florida","Aerospaziale",psw,o);

        //Definizione ruoli
        Role r1 = new Role("direttore","Consiglio di amministrazione","");
        Role r2 = new Role("vice-direttore","Consiglio di amministrazione","");
        Role r3 = new Role("venditore","Area vendite","");
        Role r4 = new Role("supervisore","Custom care","");
        Role r5 = new Role("rappresentante","Custom care","");
        Role r6 = new Role("responsabile assistenza clienti","Custom care","");
        Role r7 = new Role("E-Commerce manager","Marketing","");
        Role r8 = new Role("venditore","Acquisti","");

        //Aggiunta ruoli
        azienda.addRole(r1);
        azienda.addRole(r2);
        azienda.addRole(r3);
        azienda.addRole(r4);
        azienda.addRole(r5);
        azienda.addRole(r6);
        azienda.addRole(r7);
        azienda.addRole(r8);


        //Definizione dipendenti
        Employee emp1 = new Employee("Luca","Granata","",azienda.giveID());
        azienda.addEmployee(r1,emp1);
        Employee emp2 = new Employee("Mario","Tommasini","",azienda.giveID());
        azienda.addEmployee(r2,emp2);
        Employee emp3 = new Employee("Gianni","Filice","",azienda.giveID());
        azienda.addEmployee(r3,emp3); azienda.addEmployee(r8,emp3);
        Employee emp4 = new Employee("Armando","Basta","",azienda.giveID());
        azienda.addEmployee(r4,emp4);
        Employee emp5 = new Employee("Roberto","Cerchiara","",azienda.giveID());
        azienda.addEmployee(r5,emp5);
        Employee emp6 = new Employee("Fabio","Graceffa","",azienda.giveID());
        azienda.addEmployee(r6,emp6);
        Employee emp7 = new Employee("Marco","Quaglio","",azienda.giveID());
        azienda.addEmployee(r7,emp7);
        Employee emp8 = new Employee("Tommaso","Dodaro","",azienda.giveID());
        azienda.addEmployee(r8,emp8);


        //Mediator
        Mediator mediator = new Mediator(); mediator.setFrame(frame); mediator.setAzienda(azienda);

        //Scelta pannello da mostrare
        switch(j){
            case 0: LogPanel log = new LogPanel(mediator); frame.add(log); break;
            case 1: CreateAziendaPanel createAzienda = new CreateAziendaPanel(mediator); frame.add(createAzienda); break;
            case 2: HomePanel home = new HomePanel(mediator); frame.add(home); break;
            case 3: AziendaPanel aziendaP = new AziendaPanel(mediator); frame.add(aziendaP); break;
            case 4: CreateAreaPanel createArea = new CreateAreaPanel(mediator); frame.add(createArea); break;
            case 5: ListAreaPanel listArea = new ListAreaPanel(mediator); frame.add(listArea); break;
            case 6: CreateEmployeePanel assoPanel = new CreateEmployeePanel(mediator); frame.add(assoPanel); break;
            case 7: ListEmployeePanel listEmployeePanel = new ListEmployeePanel(mediator); frame.add(listEmployeePanel); break;
            case 8: CreateRolePanel createRole = new CreateRolePanel(mediator); frame.add(createRole); break;
            case 9: ListRolePanel listRole = new ListRolePanel(mediator); frame.add(listRole); break;
            case 10: AreaPanel areaP = new AreaPanel(o1,mediator); frame.add(areaP); break;
            case 11: ModAreaPanel modArea = new ModAreaPanel((Organigramma) o.getChild(0),mediator); frame.add(modArea); break;
            case 12: RolePanel roleP = new RolePanel(r3,mediator); frame.add(roleP); break;
            case 13: ModRolePanel modRole = new ModRolePanel(r7,mediator); frame.add(modRole); break;
            case 14: EmployeePanel employeeP = new EmployeePanel(emp5,mediator); frame.add(employeeP); break;
            case 15: AccessPanel accessP = new AccessPanel(mediator); frame.add(accessP); break;
            default:  break;
        }

        //Frame settings
        frame.setLocation(350,150);
        frame.setSize(1000,650);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public static void main(String[] args){

        PanelTest panel = new PanelTest(5);
    }
}
