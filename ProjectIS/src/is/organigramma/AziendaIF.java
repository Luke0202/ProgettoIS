package is.organigramma;

import is.Funzioni.Employee;
import is.Funzioni.Role;

import java.util.LinkedList;

public interface AziendaIF {
    void addEmployee(Employee d);
    void removeEmployee(int ID);
    void assignRole(Employee d, Role r);
    int getNEmployees();
    LinkedList<String> getAreas();
}
