package is.organigramma;

import is.dipendenti.Employee;
import is.dipendenti.Role;

import java.util.LinkedList;

public interface AziendaIF {
    int getNEmployees();
    void addEmployee(Role role, Employee d);
    void removeEmployee(int ID);
    LinkedList<String> getAreas();
    LinkedList<Integer> getIDEmployees();
}
