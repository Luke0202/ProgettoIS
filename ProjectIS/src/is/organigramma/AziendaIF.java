package is.organigramma;

import java.util.HashSet;
import java.util.LinkedList;

public interface AziendaIF {
    int getNEmployees();
    HashSet<Employee> getEmployee(Role role);
    HashSet<Integer> getIdEmployees();
    void addEmployee(Role role, Employee d);
    void removeEmployee(Employee emp);
    void removeEmployee(Role role,Employee emp);

    int getNRoles();
    HashSet<Role> getRoles(Employee emp);
    void addRole(Role role);
    void removeRole(Role role);
    HashSet<String> getRolesName();
    HashSet<String> getRolesName(Employee emp);

    int getNAreas();
    HashSet<String> getAreasName(Employee emp);
    HashSet<String> getAreasName();
    Organigramma getParent(Organigramma o);
    void addArea(Organigramma par,Organigramma org);
    void removeArea(Organigramma org);
    Organigramma getArea(String area);

    int giveID();
}
