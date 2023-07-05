package is.textParser;


import is.azienda.Employee;
import is.azienda.Role;
import is.azienda.Azienda;
import is.azienda.Organigramma;

import java.io.PrintWriter;
import java.util.HashSet;

/**
 * Tale classe ha la funzione di analizzare un Azienda
 * e salvare il contenuto all'interno di un file.txt.
 * Per memorizzare il contenuto in memoria secondaria
 * si utilizza un TextBuilder, capace di salvare i
 * dati aziendali in formato XML.
 * @author lucab
 */
public class TextPlainParser {

    //Azienda da analizzare
    private Azienda azienda;

    private TextBuilderIF builder;
    public TextPlainParser(Azienda azienda, PrintWriter pw){
        this.azienda=azienda;
        this.builder=new TextBuilder(pw);
    }

    /**
     * Tale metodo effettua il parsing, salvando i dati all'interno
     * dell'oggetto di classe Azienda.
     */
    public void doParse(){
        //Dipendenti da memorizzare
        HashSet<Employee> employees = azienda.getEmployees();
        //Organigramma da memorizzare
        Organigramma org = azienda.getOrganigramma();
        //Ruoli da memorizzare
        HashSet<Role> roles = azienda.getRoles();

        //Memorizzazione azienda
        builder.openAzienda();
        builder.openCod(azienda.getCod()); builder.closeCod(); //Codice ATECO
        builder.openName(azienda.getName()); builder.closeName(); //Nome azienda
        builder.openHeadquarter(azienda.getHeadquarter()); builder.closeHeadquarter(); //Sede centrale
        builder.openType(azienda.getType()); builder.closeType(); //Settore
        builder.openPassword(azienda.getPsw()); builder.closePassword(); //Password
        //Memorizzazione dipendenti
        builder.openEmployees();
        for (Employee emp:employees){
            builder.openEmployee();
            builder.openID(emp.getID()); builder.closeID(); //Id dipendente
            builder.openName(emp.getName()); builder.closeName(); //Nome dipendente
            builder.openSurname(emp.getSurname()); builder.closeSurname(); //Cognome dipendente
            builder.openEmail(emp.getEmail()); builder.closeEmail(); //Email dipendente
            builder.closeEmployee();
        }
        builder.closeEmployees();

        //Memorizzazione ruoli
        builder.openRoles();
        for (Role role:roles){
            builder.openRole();
            builder.openName(role.getName()); builder.closeName(); //Nome ruolo
            builder.openNameArea(role.getArea()); builder.closeNameArea(); //Area di competenza del ruolo
            builder.openDescription(role.getDescription()); builder.closeDescription(); //Descrizione del ruolo
            builder.closeRole();
        }
        builder.closeRoles();

        //Memorizzazione organigramma
        builder.openOrganigramma();
        //Pattern visitor per effettuare una visita completa dell'organigramma, memorizzando il contenuto
        AreaVisitor visitor = new DirectorVisitor(builder);
        org.accept(visitor);
        builder.closeOrganigramma();

        builder.closeAzienda();
    }
}//TextPlainParser
