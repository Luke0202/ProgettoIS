package is.organigramma;

import is.dipendenti.Employee;
import is.dipendenti.Role;

import java.util.HashSet;
import java.util.LinkedList;

public interface AziendaIF {
    int getNEmployees();
    void addEmployee(Role role, Employee d);
    void removeEmployee(Employee emp);
    void removeEmployee(Role role,Employee emp);
    void addRole(Role role);
    void removeRole(Role role);
    HashSet<Role> getRoles();
    HashSet<String> getAreas();
    HashSet<String> getNameRole();
    HashSet<Role> getRoles(Employee emp);
    HashSet<String> getAreas(Employee emp);
    HashSet<String> getNameRoles(Employee emp);
    HashSet<Integer> getIDEmployees();
    LinkedList<Employee> getEmployees();
}
