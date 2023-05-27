package is.Funzioni;

import is.organigramma.AreaOrganizationIF;

import java.util.HashSet;

public interface EmployeeIF {
    void takeRole(Role role);
    HashSet<AreaOrganizationIF> getAreas();
}
