package is.Funzioni;

import java.util.LinkedList;
import java.util.Objects;

public abstract class AbstractEmployee implements EmployeeIF {
    private final int ID;
    private String name, surname, email;
    private final AdministratorIF admin;
    protected LinkedList<Role> roles = new LinkedList<>();
    public AbstractEmployee(String name, String surname, String email, AdministratorIF admin){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.admin = admin;
        this.ID = admin.giveID();
    }

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
    public AdministratorIF getAdmin(){return admin;}
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
    public void setRoles(LinkedList<Role> roles) { this.roles = roles;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee that = (Employee) o;
        return ID == that.getID() && admin==that.getAdmin();
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
