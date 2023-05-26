package is;

import is.Funzioni.Dependent;

import java.util.LinkedList;

public interface AziendaIF {
    void addDependent(Dependent d);
    void removeDependent(int ID);
    void assignRole(Dependent d,Role r);
    int getNDependents();
}
