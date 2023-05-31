package is;

import is.mediator.Mediator;
import is.organigramma.Azienda;
import is.panels.*;
import is.parser.TextParser;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Pagination extends JFrame implements PaginationIF{

    private interface StateCreationIF {
        default void entryAction(Mediator mediator, Pagination f) {}
        default void exitAction(Pagination f) {}
        default void goAhead(Pagination f,StateCreation state){f.transition(state);}
    }
    private enum StateCreation implements StateCreationIF{
        LOG_IN_PAGE{
            private LogPanel l;
            @Override
            public void entryAction(Mediator mediator, Pagination f) {
                //Getting data
                URL url = Mediator.class.getResource("data.txt");
                TextParser tx = new TextParser(url.getPath());
                Azienda azienda = tx.build();
                mediator.setAzienda(azienda);//Per conoscere principalmente le credenziali di accesso
                //Panel
                l = new LogPanel(mediator); f.add(l);
                //Frame settings
                f.setLocation(350,150);
                f.setSize(1000,650);
                f.setResizable(false);
                f.setVisible(true);
            }
            @Override
            public void exitAction(Pagination f) {
                f.remove(l);
            }
        },
        CREATE_AZIENDA_PAGE{
            private CreateAziendaPanel c;
            @Override
            public void entryAction(Mediator mediator, Pagination f) {
                c = new CreateAziendaPanel(mediator); f.add(c);
                //Frame settings
                f.setLocation(350,150);
                f.setSize(1000,650);
                f.setResizable(false);
                f.setVisible(true);
            }
            @Override
            public void exitAction(Pagination f) {
                f.remove(c);
            }
        },
        HOME_PAGE{
            private HomePanel h;
            @Override
            public void entryAction(Mediator mediator, Pagination f) {
                h = new HomePanel(mediator); f.add(h);
                System.out.println("www");
                addingMenu(mediator,f);
                //Frame settings
                f.setLocation(350,150);
                f.setSize(1000,650);
                f.setResizable(false);
                f.setVisible(true);
            }
            @Override
            public void exitAction(Pagination f) {
                f.remove(h);
            }
            private void addingMenu(Mediator mediator, Pagination f){
                //JMenubar
                JMenuBar menubar = new JMenuBar();
                //JMenu
                JMenu area = new JMenu("Area");
                JMenu role = new JMenu("Ruolo");
                JMenu association = new JMenu("Dipendente");
                JMenu az = new JMenu("Azienda");
                //JMenuItem
                JMenuItem createA = new JMenuItem("Inserisci nuova area");
                JMenuItem listA = new JMenuItem("Elenco aree");

                JMenuItem createR = new JMenuItem("Crea ruolo");
                JMenuItem listR = new JMenuItem("Elenco ruoli");

                JMenuItem createE = new JMenuItem("Inserisci nuovo dipendente");
                JMenuItem listE = new JMenuItem("Elenco dipendenti");

                JMenuItem detA = new JMenuItem("Dettagli azienda");
                //Adding
                menubar.add(area); menubar.add(role); menubar.add(association); menubar.add(az);
                area.add(createA); area.add(listA);
                role.add(createR); role.add(listR);
                association.add(createE); association.add(listE);
                az.add(detA);
                f.setJMenuBar(menubar);

                //Mediator
                mediator.setItem(createA,listA,createR,listR,createE,listE,detA);

                createA.addActionListener(e -> mediator.menuChanged(createA));
                listA.addActionListener(e -> mediator.menuChanged(listA));
                createR.addActionListener(e -> mediator.menuChanged(createR));
                listR.addActionListener(e -> mediator.menuChanged(listR));
                createE.addActionListener(e -> mediator.menuChanged(createE));
                listE.addActionListener(e -> mediator.menuChanged(listE));
                detA.addActionListener(e -> mediator.menuChanged(detA));
            }
        },
        DETT_AZIENDA_PAGE{
            private AziendaPanel a;
            @Override
            public void entryAction(Mediator mediator,Pagination f){
                a = new AziendaPanel(mediator); f.add(a);
                //Frame settings
                f.setLocation(350,150);
                f.setSize(1000,650);
                f.setResizable(false);
                f.setVisible(true);
            }
            @Override
            public void exitAction(Pagination f){
                f.remove(a);
            }
        },
        CREATE_AREA_PAGE{
            private CreateAreaPanel c;
            @Override
            public void entryAction(Mediator mediator,Pagination f){
                c = new CreateAreaPanel(mediator); f.add(c);
                //Frame settings
                f.setLocation(350,150);
                f.setSize(1000,650);
                f.setResizable(false);
                f.setVisible(true);
            }
            @Override
            public void exitAction(Pagination f){
                f.remove(c);
            }
        },
        LIST_AREA_PAGE{
            private ListAreaPanel l;
            @Override
            public void entryAction(Mediator mediator,Pagination f){
                l = new ListAreaPanel(mediator); f.add(l);
                //Frame settings
                f.setLocation(350,150);
                f.setSize(1000,650);
                f.setResizable(false);
                f.setVisible(true);
            }
            @Override
            public void exitAction(Pagination f){
                f.remove(l);
            }
        },
        CREATE_EMPLOYEE_PAGE{
            private CreateEmployeePanel c;
            @Override
            public void entryAction(Mediator mediator,Pagination f){
                c = new CreateEmployeePanel(mediator); f.add(c);
                //Frame settings
                f.setLocation(350,150);
                f.setSize(1000,650);
                f.setResizable(false);
                f.setVisible(true);
            }
            @Override
            public void exitAction(Pagination f){
                f.remove(c);
            }
        },
        LIST_EMPLOYEE_PAGE{
            private ListEmployeePanel l;
            @Override
            public void entryAction(Mediator mediator,Pagination f){
                l = new ListEmployeePanel(mediator); f.add(l);
                //Frame settings
                f.setLocation(350,150);
                f.setSize(1000,650);
                f.setResizable(false);
                f.setVisible(true);
            }
            @Override
            public void exitAction(Pagination f){
                f.remove(l);
            }
        },
        CREATE_ROLE_PAGE{
            private CreateRolePanel c;
            @Override
            public void entryAction(Mediator mediator,Pagination f){
                c = new CreateRolePanel(mediator); f.add(c);
                //Frame settings
                f.setLocation(350,150);
                f.setSize(1000,650);
                f.setResizable(false);
                f.setVisible(true);
            }
            @Override
            public void exitAction(Pagination f){
                f.remove(c);
            }
        },
        LIST_ROLE_PAGE{
            private ListRolePanel l;
            @Override
            public void entryAction(Mediator mediator,Pagination f){
                l = new ListRolePanel(mediator); f.add(l);
                //Frame settings
                f.setLocation(350,150);
                f.setSize(1000,650);
                f.setResizable(false);
                f.setVisible(true);
            }
            @Override
            public void exitAction(Pagination f){
                f.remove(l);
            }
        }
    }


    private StateCreationIF currentState;

    private Mediator mediator;

    public Pagination(Mediator mediator){
        this.mediator=mediator;

        this.setTitle("Gestione Azienda");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                int i = JOptionPane.showConfirmDialog(Pagination.this, "Vuoi uscire?");
                if (i == 0) System.exit(0);//SI
            }
        });

        mediator.setPag(this);
        //File contenente i dati dell'azienda
        URL url = Mediator.class.getResource("data.txt");
        System.out.println(url.getPath());
        try{
            BufferedReader br = new BufferedReader(new FileReader(url.getPath()));
            if (br.readLine()==null){
                //File vuoto -> Creazione Azienda
                transition(StateCreation.CREATE_AZIENDA_PAGE);
            }else{
                //Transizione verso l'area di log-in
                transition(StateCreation.LOG_IN_PAGE);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    private final void transition(StateCreation nextState) {
        if (currentState != null)
            currentState.exitAction(this);
        currentState = nextState;
        currentState.entryAction(mediator,this);
    }// transition

    @Override
    public void goAhead(int i) {
        switch(i){
            case 1: currentState.goAhead(this,StateCreation.CREATE_AZIENDA_PAGE); break;
            case 2: currentState.goAhead(this,StateCreation.HOME_PAGE); break;
            case 3: currentState.goAhead(this,StateCreation.DETT_AZIENDA_PAGE); break;
            case 4: currentState.goAhead(this,StateCreation.CREATE_AREA_PAGE); break;
            case 5: currentState.goAhead(this,StateCreation.LIST_AREA_PAGE); break;
            case 6: currentState.goAhead(this,StateCreation.CREATE_EMPLOYEE_PAGE); break;
            case 7: currentState.goAhead(this,StateCreation.LIST_EMPLOYEE_PAGE); break;
            case 8: currentState.goAhead(this,StateCreation.CREATE_ROLE_PAGE); break;
            case 9: currentState.goAhead(this,StateCreation.LIST_ROLE_PAGE); break;
            default: currentState.goAhead(this,StateCreation.LOG_IN_PAGE); break;
        }
    }
}
