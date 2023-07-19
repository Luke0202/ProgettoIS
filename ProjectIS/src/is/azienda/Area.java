package is.azienda;

import is.textParser.AreaVisitor;
import java.util.HashSet;

/**
 * Questa interfaccia dichiara i metodi per la gestione
 * di dipendenti e di ruoli per una singola area. Include metodi
 * di visita delle proprie sotto-aree.
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
    boolean contains(int empID);

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
