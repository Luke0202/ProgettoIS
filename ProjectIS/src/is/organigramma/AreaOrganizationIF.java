package is.organigramma;

import is.visitor.AreaVisitor;

import java.util.HashSet;

public interface AreaOrganizationIF{
    void addEmployee(Role role,int id);
    void removeEmployee(int id);
    void accept(AreaVisitor visitor);
    HashSet<Role> getRoles();
    HashSet<Integer> getEmployees();
    HashSet<String> getAreasOfEmployee(Employee emp);
    boolean containsID(int empID);
    boolean contains(Role role);
    HashSet<Role> getRoles(Employee emp);
    HashSet<String> getSubAreas();
    int getNEmployees();
    HashSet<Integer> getEmployees(Role r);
    void removeRole(Role r);
}
