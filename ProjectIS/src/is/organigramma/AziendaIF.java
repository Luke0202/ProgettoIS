package is.organigramma;

import java.util.HashSet;

/**
 * Questa interfaccia dichiara i metodi che permettono
 * di manipolare i dipendenti, i ruoli e le aree di un'azienda.
 * @author lucab
 */
public interface AziendaIF {
    //About Employeers
    int getNEmployees();
    HashSet<Employee> getEmployee(Role role);
    HashSet<Integer> getIdEmployees();
    void addEmployee(Role role, Employee d);
    void removeEmployee(Employee emp);
    void removeEmployee(int id);
    void removeRoleFromEmployee(Role role, Employee emp);
    void removeRoleFromEmployee(Role role, int id);
    int giveID();

    //About Roles
    int getNRoles();
    HashSet<Role> getRoles(Employee emp);
    void addRole(Role role);
    void addRoleToEmployee(Role role, int id);
    void removeRole(Role role);
    HashSet<String> getRolesName();
    HashSet<String> getRolesName(Employee emp);

    //About Areas
    int getNAreas();
    Organigramma getArea(String area);
    HashSet<String> getAreasName(Employee emp);
    HashSet<String> getAreasName();
    Organigramma getParent(Organigramma o);
    void addArea(Organigramma par,Organigramma org);
    void removeArea(Organigramma org);
}//AziendaIF
