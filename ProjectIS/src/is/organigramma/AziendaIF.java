package is.organigramma;

import is.Funzioni.Employee;
import is.Funzioni.Role;

public interface AziendaIF {
    void addEmployee(Employee d);
    void removeEmployee(int ID);
    void assignRole(Employee d, Role r);
    int getNEmployees();
}
