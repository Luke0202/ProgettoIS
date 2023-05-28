package is.dipendenti;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;


public class Employee implements EmployeeIF {
    private final int ID;
    private String name, surname, email;
    private final AdministratorIF admin;
    protected LinkedList<Role> roles = new LinkedList<>();
    public Employee(String name, String surname, String email, AdministratorIF admin){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.admin = admin;
        this.ID = admin.giveID();
    }

    public int getID(){ return ID;  }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getEmail() {
        return email;
    }
    public AdministratorIF getAdmin(){return admin;}
    public LinkedList<Role> getRoles() {
        return roles;
    }

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


    @Override
    public HashSet<String> getMyAreas(){
        HashSet<String> ret = new HashSet<>();
        for (Role r:roles){
            String area = r.getArea();
            if (!ret.contains(area)) ret.add(area);
        }
        return ret;
    }
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
    }

    @Override
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
    }
}
