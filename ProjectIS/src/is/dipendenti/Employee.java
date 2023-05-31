package is.dipendenti;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;


public class Employee {
    private final int ID;
    private String name, surname, email;

    public Employee(String name, String surname, String email,int ID){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.ID = ID;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee that = (Employee) o;
        return ID == that.getID();
    }//equals

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(400);
        sb.append("Dipendente: " + surname + " " + name + ". Id: " + ID + "\nRuoli: ");
        sb.setLength(sb.length() - 2);
        sb.append(".");
        return sb.toString();
    }
}