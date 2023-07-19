package is.state;

import is.azienda.Employee;
import is.azienda.Role;
import is.mediator.Mediator;
import is.azienda.Azienda;
import is.azienda.Organigramma;
import is.panels.*;
import is.aziendaParser.AziendaParser;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;


/**
 * Tale classe estende JFrame e implementa PaginationIF.
 * Rappresenta il frame nel quale vengono aggiunti i pannelli
 * che l'utente chiede di visionare. Ogni pannello viene
 * rappresentato da uno stato che può variare a seconda
 * di come l'utente interagisce con il sistema.
 * La classe presenta la variabile d'istanza currentState che
 * memorizza lo stato corrente.
 * @author lucab
 */
public class Pagination extends JFrame implements PaginationIF {

    /**
     * L'interfaccia StateIF fornisce i metodi eseguibili su uno
     * stato. Uno stato dispone quindi di tre metodi:
     * -entryAction: metodo d'ingresso di uno stato, invocato quando uno stato diventa lo stato corrente.
     * -exitAction: metodo di uscita di uno stato, invocato quando uno stato smette di essere lo stato corrente.
     * -goAhead: metodo che effettua la transizione di stato.
     * @author lucab
     */
    private interface StateIF {
        default void entryAction(Mediator mediator, Pagination frame,Object obj) {}
        default void exitAction(Pagination frame) {}
        default void goAhead(Pagination frame, State state, Object obj){
            frame.transition(state,obj);
        }
    }//StateIF

    /**
     * State implementa StateIF. Definisce i metodi eseguibili su un qualsiasi stato.
     * Uno stato rappresenta un pannello che viene mostrato all'utente.
     * @author lucab
     */
    private enum State implements StateIF {
        //Pannello di accesso
        ACCESS_PAGE{
            private JPanel a;
            @Override
            public void entryAction(Mediator mediator, Pagination frame,Object obj) {
                a = new AccessPanel(mediator); frame.add(a);
                //Frame settings
                frame.setLocation(350,150);
                frame.setSize(1000,650);
                frame.setResizable(false);
                frame.setVisible(true);
            }
            @Override
            public void exitAction(Pagination frame) {
                frame.remove(a);
            }
        },
        //Pannello per il log in
        LOG_IN_PAGE{
            private JPanel l;
            @Override
            public void entryAction(Mediator mediator, Pagination frame,Object obj) {
                //Getting data
                File file = new File(path);
                //Costruzione azienda a partire dal file data.txt
                AziendaParser tx = new AziendaParser(file.getPath());
                Azienda azienda = tx.build();

                //Settaggio azienda
                //Il mediator apprende così le credenziali di accesso al sistema
                mediator.setAzienda(azienda);
                //Panel
                l = new LogPanel(mediator); frame.add(l);
                //Frame settings
                frame.setLocation(350,150);
                frame.setSize(1000,650);
                frame.setResizable(false);
                frame.setVisible(true);
            }
            @Override
            public void exitAction(Pagination frame) {
                frame.remove(l);
            }
        },
        //Pannello per la creazione di una nuova azienda
        CREATE_AZIENDA_PAGE{
            private JPanel c;
            @Override
            public void entryAction(Mediator mediator, Pagination frame,Object obj) {
                c = new CreateAziendaPanel(mediator); frame.add(c);
                //Frame settings
                frame.setLocation(350,150);
                frame.setSize(1000,650);
                frame.setResizable(false);
                frame.setVisible(true);
            }
            @Override
            public void exitAction(Pagination frame) {
                frame.remove(c);
            }
        },
        //Homepage dell'applicazione
        HOME_PAGE{
            private JPanel h;
            @Override
            public void entryAction(Mediator mediator, Pagination frame,Object obj) {
                h = new HomePanel(mediator); frame.add(h);
                addingMenu(mediator,frame);
                //Frame settings
                frame.setLocation(350,150);
                frame.setSize(1000,650);
                frame.setResizable(false);
                frame.setVisible(true);
            }
            @Override
            public void exitAction(Pagination frame) {
                frame.remove(h);
            }

            /**
             * Tale metodo ha la funzione di aggiungere al frame la barra del menu,
             * consentendo una migliore interazione con l'utente.
             * @param mediator Mediator in modo da settare i JMenuItem e gli ascoltatori
             * @param frame frame al quale va aggiunta la barra del menu.
             */
            private void addingMenu(Mediator mediator, Pagination frame){
                //JMenubar
                JMenuBar menubar = new JMenuBar();
                //JMenu
                JMenu area = new JMenu("Area");
                JMenu role = new JMenu("Ruolo");
                JMenu association = new JMenu("Dipendente");
                JMenu azienda = new JMenu("Azienda");
                //JMenuItem
                JMenuItem createA = new JMenuItem("Inserisci nuova area");
                JMenuItem listA = new JMenuItem("Elenco aree");

                JMenuItem createR = new JMenuItem("Crea ruolo");
                JMenuItem listR = new JMenuItem("Elenco ruoli");

                JMenuItem createE = new JMenuItem("Inserisci nuovo dipendente");
                JMenuItem listE = new JMenuItem("Elenco dipendenti");

                JMenuItem detA = new JMenuItem("Dettagli azienda");
                //Adding
                menubar.add(area); menubar.add(role); menubar.add(association); menubar.add(azienda);
                area.add(createA); area.add(listA);
                role.add(createR); role.add(listR);
                association.add(createE); association.add(listE);
                azienda.add(detA);
                frame.setJMenuBar(menubar);

                //Mediator
                mediator.setItem(createA,listA,createR,listR,createE,listE,detA);
                //Listeners
                createA.addActionListener(e -> mediator.widgetChanged(createA));
                listA.addActionListener(e -> mediator.widgetChanged(listA));
                createR.addActionListener(e -> mediator.widgetChanged(createR));
                listR.addActionListener(e -> mediator.widgetChanged(listR));
                createE.addActionListener(e -> mediator.widgetChanged(createE));
                listE.addActionListener(e -> mediator.widgetChanged(listE));
                detA.addActionListener(e -> mediator.widgetChanged(detA));
            }
        },
        //Pannello contenente i dettagli aziendali
        DETT_AZIENDA_PAGE{
            private JPanel a;
            @Override
            public void entryAction(Mediator mediator,Pagination frame,Object obj){
                a = new AziendaPanel(mediator); frame.add(a);
                //Frame settings
                frame.setLocation(350,150);
                frame.setSize(1000,650);
                frame.setResizable(false);
                frame.setVisible(true);
            }
            @Override
            public void exitAction(Pagination frame){
                frame.remove(a);
            }
        },
        //Pannello per la creazione di un'area
        CREATE_AREA_PAGE{
            private JPanel c;
            @Override
            public void entryAction(Mediator mediator,Pagination frame,Object obj){
                c = new CreateAreaPanel(mediator); frame.add(c);
                //Frame settings
                frame.setLocation(350,150);
                frame.setSize(1000,650);
                frame.setResizable(false);
                frame.setVisible(true);
            }
            @Override
            public void exitAction(Pagination frame){
                frame.remove(c);
            }
        },
        //Pannello per visionare le aree dell'azienda
        LIST_AREA_PAGE{
            private JPanel l;
            @Override
            public void entryAction(Mediator mediator,Pagination frame,Object obj){
                l = new ListAreaPanel(mediator); frame.add(l);
                //Frame settings
                frame.setLocation(350,150);
                frame.setSize(1000,650);
                frame.setResizable(false);
                frame.setVisible(true);
            }
            @Override
            public void exitAction(Pagination frame){
                frame.remove(l);
            }
        },
        //Pannello per l'assunzione di un dipendente
        CREATE_EMPLOYEE_PAGE{
            private JPanel c;
            @Override
            public void entryAction(Mediator mediator,Pagination frame,Object obj){
                c = new CreateEmployeePanel(mediator); frame.add(c);
                //Frame settings
                frame.setLocation(350,150);
                frame.setSize(1000,650);
                frame.setResizable(false);
                frame.setVisible(true);
            }
            @Override
            public void exitAction(Pagination frame){
                frame.remove(c);
            }
        },
        //Pannello per visionare i dipendenti dell'azienda
        LIST_EMPLOYEE_PAGE{
            private JPanel l;
            @Override
            public void entryAction(Mediator mediator,Pagination frame,Object obj){
                l = new ListEmployeePanel(mediator); frame.add(l);
                //Frame settings
                frame.setLocation(350,150);
                frame.setSize(1000,650);
                frame.setResizable(false);
                frame.setVisible(true);
            }
            @Override
            public void exitAction(Pagination frame){
                frame.remove(l);
            }
        },
        //Pannello per la creazione di un ruolo
        CREATE_ROLE_PAGE{
            private JPanel c;
            @Override
            public void entryAction(Mediator mediator,Pagination frame,Object obj){
                c = new CreateRolePanel(mediator); frame.add(c);
                //Frame settings
                frame.setLocation(350,150);
                frame.setSize(1000,650);
                frame.setResizable(false);
                frame.setVisible(true);
            }
            @Override
            public void exitAction(Pagination frame){
                frame.remove(c);
            }
        },
        //Pannello per visionare i ruoli definiti in azienda
        LIST_ROLE_PAGE{
            private JPanel l;
            @Override
            public void entryAction(Mediator mediator,Pagination frame,Object obj){
                l = new ListRolePanel(mediator); frame.add(l);
                //Frame settings
                frame.setLocation(350,150);
                frame.setSize(1000,650);
                frame.setResizable(false);
                frame.setVisible(true);
            }
            @Override
            public void exitAction(Pagination frame){
                frame.remove(l);
            }
        },
        //Pannello per la gestione di una singola area
        AREA_PAGE{
            private JPanel a;
            @Override
            public void entryAction(Mediator mediator,Pagination frame,Object obj){
                a = new AreaPanel((Organigramma) obj,mediator); frame.add(a);
                //Frame settings
                frame.setLocation(350,150);
                frame.setSize(1000,650);
                frame.setResizable(false);
                frame.setVisible(true);
            }
            @Override
            public void exitAction(Pagination frame){
                frame.remove(a);
            }
        },
        //Pannello per la modifica di un'area
        EDIT_AREA_PAGE{
            private JPanel m;
            @Override
            public void entryAction(Mediator mediator,Pagination frame,Object obj){
                m = new ModAreaPanel((Organigramma) obj,mediator); frame.add(m);
                //Frame settings
                frame.setLocation(350,150);
                frame.setSize(1000,650);
                frame.setResizable(false);
                frame.setVisible(true);
            }
            @Override
            public void exitAction(Pagination frame){
                frame.remove(m);
            }
        },
        //Pannello per la gestione di un singolo ruolo
        ROLE_PAGE{
            private JPanel r;
            @Override
            public void entryAction(Mediator mediator,Pagination frame,Object obj){
                r = new RolePanel((Role) obj,mediator); frame.add(r);
                //Frame settings
                frame.setLocation(350,150);
                frame.setSize(1000,650);
                frame.setResizable(false);
                frame.setVisible(true);
            }
            @Override
            public void exitAction(Pagination frame){
                frame.remove(r);
            }
        },
        //Pannello per la modifica di un ruolo
        EDIT_ROLE_PAGE{
            private JPanel m;
            @Override
            public void entryAction(Mediator mediator,Pagination frame,Object obj){
                m = new ModRolePanel((Role)obj,mediator); frame.add(m);
                //Frame settings
                frame.setLocation(350,150);
                frame.setSize(1000,650);
                frame.setResizable(false);
                frame.setVisible(true);
            }
            @Override
            public void exitAction(Pagination frame){
                frame.remove(m);
            }
        },
        //Pannello per la gestione di un singolo dipendente
        EMPLOYEE_PAGE{
            private JPanel e;
            @Override
            public void entryAction(Mediator mediator,Pagination frame,Object obj){
                e = new EmployeePanel((Employee) obj,mediator); frame.add(e);
                //Frame settings
                frame.setLocation(350,150);
                frame.setSize(1000,650);
                frame.setResizable(false);
                frame.setVisible(true);
            }
            @Override
            public void exitAction(Pagination frame){
                frame.remove(e);
            }
        }
    }//State

    //Assegnazione di un intero per ogni Stato
    public static final int LOG_IN = 0;
    public static final int CREATE_AZIENDA = 1;
    public static final int HOME = 2;
    public static final int DETT_AZIENDA = 3;
    public static final int CREATE_AREA = 4;
    public static final int LIST_AREA = 5;
    public static final int CREATE_EMPLOYEE = 6;
    public static final int LIST_EMPLOYEE = 7;
    public static final int CREATE_ROLE = 8;
    public static final int LIST_ROLE = 9;
    public static final int AREA = 10;
    public static final int EDIT_AREA = 11;
    public static final int ROLE = 12;
    public static final int EDIT_ROLE = 13;
    public static final int EMPLOYEE = 14;

    //Percorso file
    private final static String path = "data.txt";

    private final Mediator mediator;

    //Stato corrente
    private State currentState;
    
    public Pagination(){
        //Definizione mediator
        mediator = new Mediator();

        //Frame options
        this.setTitle("MyOrg");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //Listener per gestire la chiusura del frame
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                //Richiedi conferma
                int i = JOptionPane.showConfirmDialog(Pagination.this, "Vuoi uscire?");
                if (i != 0) return; //L'utente non ha selezionato 'Si'

                //Option: yes
                System.exit(0);
            }
        });

        //Il mediator deve gestire il passaggio da una pannello a un altro
        mediator.setPagination(this);

        //Lettura Azienda

        //Se il file è inesistente, allora deve essere creato
        File f = new File(path);

        if (!f.exists()){
            try{
                f.createNewFile();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

        //Se l'azienda esiste già, allora l'utente viene reindirizzato alla schermata di log in
        //Altrimenti viene reindirizzato al pannello di accesso al sistema
        try{
            BufferedReader br = new BufferedReader(new FileReader(f.getPath()));
            
            if (br.readLine()==null){
                //File vuoto -> Transizione verso la schermata di creazione azienda
                transition(State.ACCESS_PAGE,null);
            }else{
                //File non vuoto -> Transizione verso la schermata di log-in
                transition(State.LOG_IN_PAGE,null);
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Tale metodo effettua la transizione di stato.
     * @param nextState nuovo stato
     * @param obj oggetto utile per la definizione di un pannello
     */
    private final void transition(State nextState, Object obj) {
        if (currentState != null)
            currentState.exitAction(this);
        //Definizione nuovo stato
        currentState = nextState;
        //Invocazione del metodo d'ingresso di tale stato
        currentState.entryAction(mediator,this,obj);
    }

    /**
     * Tale metodo viene invocato quando l'utente, interagendo con
     * il pannello corrente, chiede di collegarsi a un altro pannello.
     * @param i intero che indica il pannello al quale connettersi
     * @param obj oggetto utile per la definizione di un pannello
     */
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
}//Pagination
