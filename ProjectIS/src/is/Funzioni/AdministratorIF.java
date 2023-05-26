package is.Funzioni;

import is.Azienda;
import is.Role;

public interface AdministratorIF {
    int getNumberDependents();
    boolean addDependent(Azienda a, Dependent d);
    boolean removeDependent(Azienda a,int ID);
    void assignRole(Dependent d,Role role);
    int giveID();
}
