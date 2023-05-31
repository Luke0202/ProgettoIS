package is;

import is.mediator.Mediator;
import is.organigramma.Azienda;
import is.panels.CreateAreaPanel;
import is.panels.CreateAziendaPanel;
import is.panels.HomePanel;
import is.panels.LogPanel;
import is.parser.TextParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Pagination extends JFrame implements PaginationIF{

    private interface StateCreationIF {
        default void entryAction(Mediator mediator, Pagination f) {
        }

        default void exitAction(Pagination f) {
        }
        default void start(Pagination p){
        }
        default void create(Pagination f){

        }
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
                mediator.setAzienda(azienda);
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
            @Override
            public void start(Pagination f){
                f.transition(StateCreation.HOME_PAGE);
            }
            @Override
            public void create(Pagination f) {
                f.transition(StateCreation.CREATE_AZIENDA_PAGE);
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
            @Override
            public void start(Pagination f){
                f.transition(StateCreation.HOME_PAGE);
            }
        },
        HOME_PAGE{
            private HomePanel h;
            @Override
            public void entryAction(Mediator mediator, Pagination f) {
                h = new HomePanel(mediator); f.add(h);
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
                System.out.print('r');
                //File vuoto -> Creazione Azienda
                transition(StateCreation.CREATE_AZIENDA_PAGE);
            }else{
                System.out.print('s');
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
    public void start() {
        currentState.start(this);
    }

    @Override
    public void create() {
        currentState.create(this);
    }
}
