package is.Funzioni;

import is.AreaOrganization;
import is.Role;

import java.util.LinkedList;
import java.util.Objects;

public class Dependent implements DependentIF {
    private int ID;
    private String name, surname, email;
    private final AdministratorIF admin;
    private LinkedList<Role> roles = new LinkedList<>();
    private LinkedList<AreaOrganization> areas = new LinkedList<>();

    public Dependent(String name, String surname, String email, AdministratorIF admin){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.admin = admin;
        this.ID = admin.giveID();
    }
    //GETTERS
    public LinkedList<Role> getRoles() {
        return roles;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getEmail() {
        return email;
    }
    public int getID(){ return ID;  }
    //SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setRoles(LinkedList<Role> roles) { this.roles = roles;
    }

    @Override
    public void takeRole(Role role) {
        if (!roles.contains(role)){
            roles.add(role);
            role.getArea().addDependent(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dependent that = (Dependent) o;
        return ID == that.ID && admin==that.admin;
    }//equals

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }//hashCode

    public String toString() {
        StringBuilder sb = new StringBuilder(400);
        sb.append("Dipendente: " + surname + " " + name + ". Id: " + ID + "\nRuoli: ");
        int N = roles.size();
        for (int i = 0; i < N; i++) {
            sb.append(roles.get(i).getName() + ", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append(".");
        return sb.toString();
    }//toString
}
