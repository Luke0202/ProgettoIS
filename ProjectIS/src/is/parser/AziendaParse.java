package is.parser;

import is.dipendenti.Administrator;
import is.dipendenti.Employee;
import is.builder.TextBuilder;
import is.builder.TextBuilderIF;
import is.dipendenti.Role;
import is.organigramma.Azienda;
import is.organigramma.Couple;
import is.organigramma.Organigramma;
import java.io.PrintWriter;
import java.util.HashSet;
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
        Administrator admin = azienda.getAdmin();
        LinkedList<Employee> employees = admin.getEmployees();
        Organigramma org = (Organigramma) admin.getOrganigramma();
        HashSet<Role> roles = admin.getRoles();

        //Azienda
        builder.openAzienda();
        builder.openID(azienda.getCod());
        builder.closeID();
        builder.openName(azienda.getName());
        builder.closeName();
        builder.openHeadquarter(azienda.getHeadquarter());
        builder.closeHeadquarter();
        builder.openType(azienda.getType());
        builder.closeType();
        builder.openPassword(azienda.getPsw());
        builder.closePassword();
        //Employees
        builder.openEmployees();
        for (Employee emp:employees){
            builder.openEmployee();
            builder.openID(emp.getID());
            builder.closeID();
            builder.openName(emp.getName());
            builder.closeName();
            builder.openSurname(emp.getSurname());
            builder.closeSurname();
            builder.openEmail(emp.getEmail());
            builder.closeEmail();
            builder.closeEmployee();
        }
        builder.closeEmployees();

        //Roles
        builder.openRoles();
        for (Role role:roles){
            builder.openRole();
            builder.openName(role.getName());
            builder.closeName();
            builder.openNameArea(role.getArea());
            builder.closeNameArea();
            builder.openDescription(role.getDescription());
            builder.closeDescription();
            builder.openState(role.getStateRole());
            builder.closeState();
            builder.closeRole();
        }
        builder.closeRoles();

        //Organigramma
        builder.openOrganigramma();
        addAreas(org);
        builder.closeOrganigramma();

        builder.closeAzienda();
    }
    private void addAreas(Organigramma org){
        builder.openArea();
        builder.openName(org.getName());
        builder.closeName();
        builder.openDescription(org.getDescription());
        builder.closeDescription();
        builder.openState(org.getStateArea());
        builder.closeState();
        //Couples Id employee-role
        builder.openCouples();
        for(Couple c:org.getCouples()){
            builder.openCouple();
            builder.openID(c.getID());
            builder.closeID();
            Role r = c.getRole();
            builder.openRole();
            builder.openName(r.getName());
            builder.closeName();
            builder.openNameArea(r.getArea());
            builder.closeNameArea();
            builder.openDescription(r.getDescription());
            builder.closeDescription();
            builder.closeRole();
            builder.closeCouple();
        }
        builder.closeCouples();

        builder.openListAreas();
        for (int i = 0;i<org.getNChildren();i++){
            addAreas((Organigramma) org.getChild(i));
        }
        builder.closeListAreas();
        builder.closeArea();
    }
}
