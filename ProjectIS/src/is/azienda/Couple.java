package is.azienda;

import java.util.Objects;

/**
 * Tale classe associa un ruolo a un determinato dipendente,
 * caratterizzato da un id.
 * @author lucab
 */
public class Couple {
    private Role role;

    //identificatore dipendente
    private int id;

    public Couple(Role role, Employee employee){
        this.role = role;
        id = employee.getID();
    }
    public Couple(Role role, int id){
        this.role = role;
        this.id = id;
    }

    //GETTERS
    public Role getRole() {
        return role;
    }
    public int getID() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Couple couple = (Couple) o;
        return id == couple.id && Objects.equals(role, couple.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, id);
    }

    @Override
    public String toString() {
        return "Couple{" +
                "role=" + role +
                ", ID=" + id +
                '}';
    }
}//Couple
