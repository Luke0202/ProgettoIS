package is.parser;

import is.dipendenti.Administrator;
import is.dipendenti.Employee;
import is.builder.TextBuilder;
import is.builder.TextBuilderIF;
import is.organigramma.Azienda;
import is.organigramma.Organigramma;
import java.io.PrintWriter;
import java.util.LinkedList;

public class AziendaParse {
    //Analizza un oggetto di tipo AziendaIF e lo converte in un oggetto di tipo txt
    private Azienda azienda;
    private TextBuilderIF builder;
    public AziendaParse(Azienda azienda, PrintWriter pw){
        this.azienda=azienda;
        this.builder=new TextBuilder(pw);
    }
    public void doParse(){
        builder.openAzienda(String.valueOf(azienda.getCod()));
        builder.openName(azienda.getName());
        builder.closeName();
        builder.openMaxEmployees(String.valueOf(azienda.getAdmin().getMaxEmployees()));
        builder.closeMaxEmployees();
        Administrator admin = azienda.getAdmin();
        builder.openAdmin(String.valueOf(admin.getID()));
        builder.openName(admin.getName());
        builder.closeName();
        builder.openSurname(admin.getSurname());
        builder.closeSurname();
        builder.openEmail(admin.getEmail());
        builder.closeEmail();
        builder.closeAdmin();
        builder.openEmployees();
        LinkedList<Employee> employees = azienda.getAdmin().getEmployees();
        for (Employee emp:employees){
            builder.openEmployee(String.valueOf(emp.getID()));
            builder.openName(emp.getName());
            builder.closeName();
            builder.openSurname(emp.getSurname());
            builder.closeSurname();
            builder.openEmail(emp.getEmail());
            builder.closeEmail();
            builder.closeEmployee();
        }
        builder.closeEmployees();
        Organigramma org = (Organigramma) azienda.getAdmin().getOrganigramma();
        builder.openOrganigramma();
        addAreas(org);


        builder.closeOrganigramma();
        builder.closeAzienda();
    }
    private void addAreas(Organigramma org){
        builder.openArea(org.getName());
        builder.closeName();
        builder.openDescription(org.getDescription());
        builder.closeDescription();
        //Aggiunta coppie ruolo e ID dipendente



        for (int i = 0;i<org.getNChildren();i++){
            addAreas((Organigramma) org.getChild(i));
        }
    }
}
