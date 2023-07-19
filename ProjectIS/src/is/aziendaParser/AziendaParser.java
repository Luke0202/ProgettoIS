package is.aziendaParser;

import is.azienda.Employee;
import is.azienda.Role;
import is.azienda.Azienda;
import is.azienda.Couple;
import is.azienda.Organigramma;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Tale classe analizza un file.txt contenente i dati di un'azienda,
 * memorizzati in XML, con l'obiettivo di costruire un oggetto della
 * classe Azienda.
 * @author lucab
 */
public class AziendaParser {
    //Percorso file
    private final String path;

    //Oggetto che consente la lettura del file
    private BufferedReader br;

    //Token per riconoscere i tag usati nel file.txt
    private String token;

    //StringTokenizer necessario per separare i token dai dati aziendali
    private StringTokenizer st;

    //Coppie ruolo-idDipendente
    private HashSet<Couple> couples = new HashSet<>();

    public AziendaParser(String path){
        this.path=path;
    }

    /**
     * Tale metodo ha la funzione di costruire l'Azienda.
     * Vengono catturate eventuali eccezioni che possono
     * essere lanciate dalla funzione doParse.
     * @return Azienda
     */
    public Azienda build() {
        Azienda az=null;
        try {
            az=doParse();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return az;
    }

    /**
     * Tale metodo definisce l'oggetto di lettura file.
     * Avvia inoltre il metodo readAzienda inizializzando
     * la procedura di lettura dati.
     * @return Azienda
     */
    private Azienda doParse() {
        try {
            br = new BufferedReader(new FileReader(path));
        } catch (IOException e) {
            throw new IllegalArgumentException("Errore di lettura");
        }
        return readAzienda();
    }

    /**
     * Tale metodo restituisce, mediante l'utilizzo
     * di StringTokenizer, un token, il quale può indicare
     * il contenuto informativo di un campo oppure un tag.
     * @return token
     */
    private String nextToken() {

        while (st == null || !st.hasMoreTokens()) {
            //Casi in cui lo stringTokenizer non è stato ancora definito o non presenta più token
            String line = null;

            try {
                while(line == null || line.charAt(line.length()-1)!='>'){
                    //Gestione caso in cui un'informazione è definita su più righe del file
                    line = (line == null )?  br.readLine(): line+"\n"+br.readLine();

                    //Caso in cui sono state analizzate tutte le righe del file
                    if (line == null)
                        return null;//Fine file
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            //Definizione nuovo stringTokenizer
            st = new StringTokenizer(line,"[><]");
        }

        //Un token contiene il contenuto di un tag o un dato dell'azienda
        return st.nextToken();
    }

    /**
     * Tale metodo verifica che il token ottenuto analizzando il file
     * sia coincidente con il tag atteso.
     * Se restituisce false, è possibile che qualcuno abbia manomesso il
     * file contenente i dati aziendali.
     * @param expected esprime il tag che noi ci aspettiamo analizzando la
     *                 composizione del file in formato XML
     * @return boolean
     */
    private boolean isCorrect(String expected){
        return token != null && token.equals(expected);
    }

    /**
     * Tale metodo ha la funzione di leggere i dati relativi all'azienda, in merito
     * a informazioni generali sull'azienda, ruoli definiti in azienda, dipendenti assunti
     * e organigramma aziendale.
     * @return Azienda
     */
    private Azienda readAzienda(){
        //Token d'ingresso per la lettura dell'azienda
        token = nextToken();//<Azienda>

        if (!isCorrect("Azienda")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        token = nextToken();//<Cod>

        if (!isCorrect("Cod")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        String codAzienda = readText();

        token = nextToken();//<Name>

        if (!isCorrect("Name")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        String nameAzienda = readText();

        token = nextToken();//<Headquarter>

        if (!isCorrect("Headquarter")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        String hqAzienda = readText();

        token = nextToken();//<Type>

        if (!isCorrect("Type")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        String typeAzienda = readText();

        token = nextToken();//<Password>

        if (!isCorrect("Password")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        String psw = readText();

        //Lettura dipendenti
        LinkedList<Employee> employees = readEmployees();

        //Lettura ruoli
        HashSet<Role> roles = readRoles();

        //Lettura organigramma
        Organigramma org = readOrganigramma();

        //Creazione azienda
        Azienda azienda = new Azienda(codAzienda,nameAzienda,hqAzienda,typeAzienda,psw,org);

        //Aggiunta ruoli
        for (Role r:roles){
            azienda.addRole(r);
        }

        //Aggiunta coppie ID emp-Role
        for (Employee emp:employees){
            int id = emp.getID();
            for (Couple c:couples){
                if (c.getID()==id){
                    //Aggiunta coppia
                    azienda.addEmployee(c.getRole(),emp);
                }
            }
        }
        return azienda;
    }

    /**
     * Tale metodo ha la funzione di leggere i ruoli definiti in azienda.
     * @return insieme di ruoli
     */
    private HashSet<Role> readRoles(){
        //HashSet da restituire
        HashSet<Role> roles = new HashSet<>();

        //Token d'ingresso per la lettura dei ruoli
        token = nextToken();//<Roles>

        if (!isCorrect("Roles")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        //Variabili per la definizione di ogni singolo ruolo
        String name=null;
        String area=null;
        String descr = null;
        //Variabile booleana per indicare la fine della lettura dei ruoli
        boolean go = true;
        while(go){
            token = nextToken(); //token successivo
            switch(token){
                case "Role": break; //Nuovo ruolo da leggere
                case "Name": name = readText(); break; //Lettura nome ruolo
                case "NameArea": area = readText(); break; //Lettura nome area
                case "Description": descr = readText(); break; //Lettura descrizione
                case "/Role": Role r = new Role(name,area,descr); roles.add(r); break; //Salvataggio ruolo
                case "/Roles": go = false; //Non ci sono più ruoli da leggere
            }
        }
        return roles;
    }

    /**
     * Tale metodo ha la funzione di leggere l'organigramma aziendale.
     * @return Organigramma
     */
    private Organigramma readOrganigramma(){
        //Token d'ingresso per la lettura dell'organigramma
        token = nextToken();//<Organigramma>

        token = nextToken();
        if (token.equals("/Organigramma")) return null; //Non sono presenti aree
        else if (token.equals("Area")) return readArea(); //È presente almeno un'area da leggere

        //Token non riconosciuto
        throw new IllegalArgumentException("Wrong token. Token found: " + token);
    }

    /**
     * Tale metodo ha la funzione di leggere un'area, includendo
     * eventuali sotto-aree da cui è composta.
     * @return Organigramma
     */
    private Organigramma readArea(){

        token = nextToken();//<Name>

        if (!isCorrect("Name")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        String nameArea = readText();

        token = nextToken();//<Description>

        if (!isCorrect("Description")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        String descrArea = readText();

        token = nextToken();//<State>

        if (!isCorrect("State")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        //Individuazione stato area
        String s = readText();
        boolean state = s.equals("true");

        token = nextToken();//<Couples>

        if (!isCorrect("Couples")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        //Creazione organigramma
        Organigramma org = new Organigramma(nameArea,descrArea);
        //Modifica stato
        org.setStateArea(state);

        //Variabili necessarie per la lettura delle coppie ruolo-idDipendente
        Couple cou = null;
        Role r = null;

        int coupleID = 0;
        String nameCouple = null;
        String areaCouple = null;
        String descrCouple = null;

        //Variabile booleana utile a sancire la fine della lettura delle coppie
        boolean go = true;
        while(go){
            token = nextToken();//Token successivo
            switch(token){
                case "ID": coupleID = Integer.parseInt(readText()); break; //Lettura idDipendente
                case "Name": nameCouple = readText(); break; //Lettura nome ruolo
                case "NameArea": areaCouple = readText(); break; //Lettura area
                case "Description": descrCouple = readText(); break; //Lettura descrizione
                case "/Role": r = new Role(nameCouple,areaCouple,descrCouple); break; //Creazione ruolo
                case "/Couple": cou = new Couple(r,coupleID); couples.add(cou); break; //Creazione coppia
                case "/Couples": go = false; //Non ci sono più coppie da leggere
            }
        }

        token = nextToken();//<listAreas>

        if (!isCorrect("ListAreas")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        //Lettura sotto-aree
        LinkedList<Organigramma> listAreas = new LinkedList<>();

        Organigramma area = null;
        //Variabile booleana per indicare la fine della lettura dell'area
        go = true;
        while (go){
            token = nextToken();//Token successivo
            switch(token){
                case "Area": area = readArea(); listAreas.add(area); break; //Lettura nuova sotto-area
                case "/ListAreas": go = false; //Non ci sono più sotto-aree da leggere
            }
        }

        nextToken();//</Area>

        //Aggiunta sotto-aree
        for (int i=0;i<listAreas.size();i++){
            org.addChild(listAreas.get(i));
        }
        return org;
    }

    /**
     * Tale metodo ha la funzione di leggere i dipendenti dell'azienda.
     * @return lista dei dipendenti
     */
    private LinkedList<Employee> readEmployees(){
        //LinkedList da restituire
        LinkedList<Employee> listEmp = new LinkedList<>();

        token = nextToken();//<Employees>

        if (!isCorrect("Employees")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        //Variabili per la definizione di ogni singolo dipendente
        int id=0;
        String name=null;
        String surname=null;
        String email=null;
        Employee emp=null;
        //Variabile booleana per indicare la fine della lettura dei dipendenti
        boolean go = true;

        while(go){
            token = nextToken(); //Token successivo

            switch(token){
                case "Employee": break; //Inizio lettura dipendente
                case "ID":id = Integer.parseInt(readText()); break; //Lettura id dipendente
                case "Name": name = readText(); break; //Lettura nome dipendente
                case "Surname": surname = readText(); break; //Lettura cognome dipendente
                case "Email": email = readText(); break; //Lettura email dipendente
                case "/Employee":
                    emp = new Employee(name,surname,email,id); listEmp.add(emp); break; //Creazione nuovo dipendente
                case "/Employees": go = false; //Non ci sono più dipendenti da leggere
            }
        }

        return listEmp;
    }

    /**
     * Tale metodo ha la funzione di leggere una porzione di file,
     * restituendone il contenuto.
     * @return String
     */
    private String readText(){
        token = nextToken();//content

        nextToken();//<...>
        if (token == null) throw new IllegalArgumentException("Atteso token");
        else return token;
    }
}//AziendaParser
