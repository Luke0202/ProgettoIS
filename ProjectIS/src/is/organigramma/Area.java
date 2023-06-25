package is.organigramma;

import is.visitor.AreaVisitor;

import java.util.HashSet;

public interface Area {
    //Metodi di gestione di: dipendenti, ruoli
    void addEmployee(Role role,int id);
    void removeEmployee(int id);
    HashSet<Integer> getEmployees();
    HashSet<Integer> getEmployees(Role r);
    default int getNEmployees(){return getEmployees().size();}
    boolean containsID(int empID);

    void removeRole(Role r);
    HashSet<Role> getRoles();
    HashSet<Role> getRoles(Employee emp);
    boolean contains(Role role);

    HashSet<String> getAreasOfEmployee(Employee emp);
    HashSet<String> getSubAreas();


    void accept(AreaVisitor visitor);
}
