package is.dipendenti;

import java.util.HashSet;
import java.util.LinkedList;

public interface AdministratorIF {
    void addEmployee(Role role,Employee emp);
    void removeEmployee(Employee e);
    void removeEmployee(Role role,Employee emp);
    int giveID();
    HashSet<Role> getRoles(Employee emp);
    void addRole(Role r);
    void removeRole(Role r);
}
