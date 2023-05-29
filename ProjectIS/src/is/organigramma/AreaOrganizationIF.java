package is.organigramma;

import is.dipendenti.Employee;
import is.dipendenti.Role;

import java.util.HashSet;

public interface AreaOrganizationIF{
    void addEmployee(Role role,int id);
    void removeEmployee(int id);
    void accept();
    HashSet<Role> getRoles();
    HashSet<Integer> getEmployees();
    HashSet<String> getAreasOfEmployee(Employee emp);
    boolean containsID(int empID);
    HashSet<Role> getRolesOfEmployee(Employee emp);
}
