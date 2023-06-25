package is.state;

import is.organigramma.Employee;
import is.organigramma.Role;
import is.mediator.Mediator;
import is.organigramma.Azienda;
import is.organigramma.Organigramma;
import is.panels.*;
import is.parser.TextPlainParser;
import is.parser.AziendaParser;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;


public class Pagination extends JFrame implements PaginationIF {

    private interface StateIF {
        default void entryAction(Mediator mediator, Pagination f,Object obj) {}
        default void exitAction(Pagination f) {}
        default void goAhead(Pagination f, State state, Object obj){f.transition(state,obj);}
    }
    private enum State implements StateIF {
        LOG_IN_PAGE{
            private LogPanel l;
            @Override
            public void entryAction(Mediator mediator, Pagination f,Object obj) {
                //Getting data
                File file = new File("data.txt");
                AziendaParser tx = new AziendaParser(file.getPath());
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
            public void entryAction(Mediator mediator, Pagination f,Object obj) {
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
            public void entryAction(Mediator mediator, Pagination f,Object obj) {
                h = new HomePanel(mediator); f.add(h);
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

                createA.addActionListener(e -> mediator.widgetChanged(createA));
                listA.addActionListener(e -> mediator.widgetChanged(listA));
                createR.addActionListener(e -> mediator.widgetChanged(createR));
                listR.addActionListener(e -> mediator.widgetChanged(listR));
                createE.addActionListener(e -> mediator.widgetChanged(createE));
                listE.addActionListener(e -> mediator.widgetChanged(listE));
                detA.addActionListener(e -> mediator.widgetChanged(detA));
            }
        },
        DETT_AZIENDA_PAGE{
            private AziendaPanel a;
            @Override
            public void entryAction(Mediator mediator,Pagination f,Object obj){
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
            public void entryAction(Mediator mediator,Pagination f,Object obj){
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
            public void entryAction(Mediator mediator,Pagination f,Object obj){
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
            public void entryAction(Mediator mediator,Pagination f,Object obj){
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
            public void entryAction(Mediator mediator,Pagination f,Object obj){
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
            public void entryAction(Mediator mediator,Pagination f,Object obj){
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
            public void entryAction(Mediator mediator,Pagination f,Object obj){
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
        },
        AREA_PAGE{
            private AreaPanel a;
            @Override
            public void entryAction(Mediator mediator,Pagination f,Object obj){
                a = new AreaPanel((Organigramma) obj,mediator); f.add(a);
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
        EDIT_AREA_PAGE{
            private ModAreaPanel m;
            @Override
            public void entryAction(Mediator mediator,Pagination f,Object obj){
                m = new ModAreaPanel((Organigramma) obj,mediator); f.add(m);
                //Frame settings
                f.setLocation(350,150);
                f.setSize(1000,650);
                f.setResizable(false);
                f.setVisible(true);
            }
            @Override
            public void exitAction(Pagination f){
                f.remove(m);
            }
        },
        ROLE_PAGE{
            private RolePanel r;
            @Override
            public void entryAction(Mediator mediator,Pagination f,Object obj){
                r = new RolePanel((Role) obj,mediator); f.add(r);
                //Frame settings
                f.setLocation(350,150);
                f.setSize(1000,650);
                f.setResizable(false);
                f.setVisible(true);
            }
            @Override
            public void exitAction(Pagination f){
                f.remove(r);
            }
        },
        EDIT_ROLE_PAGE{
            private ModRolePanel m;
            @Override
            public void entryAction(Mediator mediator,Pagination f,Object obj){
                m = new ModRolePanel((Role)obj,mediator); f.add(m);
                //Frame settings
                f.setLocation(350,150);
                f.setSize(1000,650);
                f.setResizable(false);
                f.setVisible(true);
            }
            @Override
            public void exitAction(Pagination f){
                f.remove(m);
            }
        },
        EMPLOYEE_PAGE{
            private EmployeePanel e;
            @Override
            public void entryAction(Mediator mediator,Pagination f,Object obj){
                e = new EmployeePanel((Employee) obj,mediator); f.add(e);
                //Frame settings
                f.setLocation(350,150);
                f.setSize(1000,650);
                f.setResizable(false);
                f.setVisible(true);
            }
            @Override
            public void exitAction(Pagination f){
                f.remove(e);
            }
        }
    }


    private State currentState;

    private Mediator mediator;

    public Pagination(Mediator mediator){
        this.mediator=mediator;

        this.setTitle("Gestione Azienda");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                int i = JOptionPane.showConfirmDialog(Pagination.this, "Vuoi uscire?");
                if (i == 0){
                    //Option: yes
                    if (mediator.getAzienda()!=null){

                        File f = new File("data.txt");
                        if (!f.exists()){
                            try{
                                f.createNewFile();
                            }
                            catch(IOException e){
                                e.printStackTrace();
                            }
                        }

                        try{
                            PrintWriter pw = new PrintWriter(new FileWriter(f.getPath()));

                            TextPlainParser tx = new TextPlainParser(mediator.getAzienda(),pw); tx.doParse();
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                    System.exit(0);
                }
            }
        });

        mediator.setPagination(this);
        //File contenente i dati dell'azienda
        File f = new File("data.txt");
        if (!f.exists()){
            try{
                f.createNewFile();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

        try{
            BufferedReader br = new BufferedReader(new FileReader(f.getPath()));
            if (br.readLine()==null){
                //File vuoto -> Creazione Azienda
                transition(State.CREATE_AZIENDA_PAGE,null);
            }else{
                //Transizione verso l'area di log-in
                transition(State.LOG_IN_PAGE,null);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    private final void transition(State nextState, Object obj) {
        if (currentState != null)
            currentState.exitAction(this);
        currentState = nextState;
        currentState.entryAction(mediator,this,obj);
    }// transition

    @Override
    public void avanza(int i,Object obj) {
        switch(i){
            case 1: currentState.goAhead(this, State.CREATE_AZIENDA_PAGE,null); break;
            case 2: currentState.goAhead(this, State.HOME_PAGE,null); break;
            case 3: currentState.goAhead(this, State.DETT_AZIENDA_PAGE,null); break;
            case 4: currentState.goAhead(this, State.CREATE_AREA_PAGE,null); break;
            case 5: currentState.goAhead(this, State.LIST_AREA_PAGE,null); break;
            case 6: currentState.goAhead(this, State.CREATE_EMPLOYEE_PAGE,null); break;
            case 7: currentState.goAhead(this, State.LIST_EMPLOYEE_PAGE,null); break;
            case 8: currentState.goAhead(this, State.CREATE_ROLE_PAGE,null); break;
            case 9: currentState.goAhead(this, State.LIST_ROLE_PAGE,null); break;
            case 10: currentState.goAhead(this, State.AREA_PAGE,obj); break;
            case 11: currentState.goAhead(this, State.EDIT_AREA_PAGE,obj); break;
            case 12: currentState.goAhead(this, State.ROLE_PAGE,obj); break;
            case 13: currentState.goAhead(this, State.EDIT_ROLE_PAGE,obj); break;
            case 14: currentState.goAhead(this, State.EMPLOYEE_PAGE,obj); break;
            default: currentState.goAhead(this, State.LOG_IN_PAGE,null); break;
        }
    }
}
