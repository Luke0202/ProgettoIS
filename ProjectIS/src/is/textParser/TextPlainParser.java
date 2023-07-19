package is.textParser;

import is.azienda.Employee;
import is.azienda.Role;
import is.azienda.Azienda;
import is.azienda.Organigramma;
import java.io.PrintWriter;
import java.util.HashSet;

/**
 * Tale classe ha la funzione di salvare il contenuto
 * di un'azienda all'interno di un file.txt.
 * Per memorizzare il contenuto in memoria secondaria
 * si utilizza un TextBuilder, in grado di salvare i
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
        builder.openAzienda(azienda);

        //Memorizzazione dipendenti
        builder.openEmployees();
        for (Employee employee:employees){
            builder.addEmployee(employee);
        }
        builder.closeEmployees();

        //Memorizzazione ruoli
        builder.openRoles();
        for (Role role:roles){
            builder.addRole(role);
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
