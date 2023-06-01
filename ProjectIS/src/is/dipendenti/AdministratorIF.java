package is.dipendenti;

import is.organigramma.Organigramma;

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
    HashSet<String> getAllAreas();
    Organigramma getParent(Organigramma o);
    void addArea(Organigramma par,Organigramma org);
    void removeArea(Organigramma org);
    HashSet<Integer> getIDEmployees();
    HashSet<Employee> getEmployee(Role role);
    Organigramma getArea(String area);
    HashSet<Organigramma> getAreas(Employee emp);
}
