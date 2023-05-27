package is.Funzioni;

import is.organigramma.AreaOrganizationIF;
import is.organigramma.Azienda;

public interface AdministratorIF {
    boolean addEmployee(Azienda a, Employee d);
    boolean removeEmployee(Azienda a,int ID);
    void assignRole(Employee d, Role role);
    void assignArea(Employee d, AreaOrganizationIF area);
    int giveID();
}
