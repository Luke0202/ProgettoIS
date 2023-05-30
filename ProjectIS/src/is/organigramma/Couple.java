package is.organigramma;

import is.dipendenti.Employee;
import is.dipendenti.Role;

public class Couple {
    private Role role;
    private int ID;

    public Couple(Role role, Employee employee){
        this.role = role;
        ID = employee.getID();
    }
    public Couple(Role role, int ID){
        this.role = role;
        this.ID = ID;
    }
    public Role getRole() {
        return role;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Couple{" +
                "role=" + role +
                ", ID=" + ID +
                '}';
    }
}
