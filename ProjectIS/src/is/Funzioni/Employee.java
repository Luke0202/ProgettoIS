package is.Funzioni;

import is.organigramma.AreaOrganizationIF;

import java.util.HashSet;


public class Employee extends AbstractEmployee {
    public Employee(String name, String surname, String email, AdministratorIF admin){
        super(name,surname,email,admin);
    }
    //GETTERS

    @Override
    public void takeRole(Role role) {
        if (!roles.contains(role)){
            roles.add(role);
            role.getArea().addEmployee(this);
        }
    }

    @Override
    public HashSet<AreaOrganizationIF> getAreas(){
        HashSet<AreaOrganizationIF> ret = new HashSet<>();
        for (Role r:roles){
            AreaOrganizationIF area = r.getArea();
            if (!ret.contains(area)) ret.add(area);
        }
        return ret;
    }
}
