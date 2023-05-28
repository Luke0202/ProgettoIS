package is.organigramma;

import is.dipendenti.Employee;
import is.dipendenti.Role;

import java.util.HashSet;

public interface AreaOrganizationIF{
    void addEmployee(Role r, Employee emp);
    void removeEmployee(Employee d);
    void accept();
    HashSet<String> getRoles();
    HashSet<Integer> getEmployees();
    HashSet<String> getAreasOfEmployee(Employee emp);
    boolean containsID(int empID);
}
