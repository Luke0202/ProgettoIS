package is.organigramma;

import is.visitor.AreaVisitor;
import java.util.HashSet;

/**
 * Questa interfaccia dichiara i metodi per la gestione
 * di dipendenti e ruoli per una singola area.
 * Presenta in aggiunta metodi per visitare l'area corrente
 * e le sotto-aree.
 * @author lucab
 */
public interface Area {
    //About Employees
    void addEmployee(Role role,int id);
    void removeEmployee(int id);
    void removeEmployee(Role role,int id);
    HashSet<Integer> getEmployees();
    HashSet<Integer> getEmployees(Role r);
    default int getNEmployees(){return getEmployees().size();}
    boolean containsID(int empID);

    //About roles
    void removeRole(Role r);
    HashSet<Role> getRoles();
    HashSet<Role> getRoles(Employee emp);
    boolean contains(Role role);

    //About areas
    HashSet<String> getAreasOfEmployee(Employee emp);
    HashSet<String> getSubAreas();

    //About pattern visitor
    void accept(AreaVisitor visitor);
}//Area
