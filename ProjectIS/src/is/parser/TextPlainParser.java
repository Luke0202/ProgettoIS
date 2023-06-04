package is.parser;


import is.organigramma.Employee;
import is.builder.TextBuilder;
import is.builder.TextBuilderIF;
import is.organigramma.Role;
import is.organigramma.Azienda;
import is.organigramma.Organigramma;
import is.visitor.AreaVisitor;
import is.visitor.DirectorVisitor;
import java.io.PrintWriter;
import java.util.HashSet;

public class TextPlainParser {
    //Analizza un oggetto di tipo AziendaIF e lo converte in un oggetto di tipo txt
    private Azienda azienda;
    private TextBuilderIF builder;
    public TextPlainParser(Azienda azienda, PrintWriter pw){
        this.azienda=azienda;
        this.builder=new TextBuilder(pw);
    }
    public void doParse(){
        HashSet<Employee> employees = azienda.getEmployees();
        Organigramma org = azienda.getOrganigramma();
        HashSet<Role> roles = azienda.getRoles();

        //Azienda
        builder.openAzienda();
        builder.openID(azienda.getCod()); builder.closeID();
        builder.openName(azienda.getName()); builder.closeName();
        builder.openHeadquarter(azienda.getHeadquarter()); builder.closeHeadquarter();
        builder.openType(azienda.getType()); builder.closeType();
        builder.openPassword(azienda.getPsw()); builder.closePassword();
        //Employees
        builder.openEmployees();
        for (Employee emp:employees){
            builder.openEmployee();
            builder.openID(emp.getID()); builder.closeID();
            builder.openName(emp.getName()); builder.closeName();
            builder.openSurname(emp.getSurname()); builder.closeSurname();
            builder.openEmail(emp.getEmail()); builder.closeEmail();
            builder.closeEmployee();
        }
        builder.closeEmployees();

        //Roles
        builder.openRoles();
        for (Role role:roles){
            builder.openRole();
            builder.openName(role.getName()); builder.closeName();
            builder.openNameArea(role.getArea()); builder.closeNameArea();
            builder.openDescription(role.getDescription()); builder.closeDescription();
            builder.openState(role.getStateRole()); builder.closeState();
            builder.closeRole();
        }
        builder.closeRoles();

        //Organigramma
        builder.openOrganigramma();
        AreaVisitor visitor = new DirectorVisitor(builder);
        org.accept(visitor);
        builder.closeOrganigramma();

        builder.closeAzienda();
    }
}
