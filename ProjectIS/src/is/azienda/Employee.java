package is.azienda;

import java.util.Objects;

/**
 * Tale classe definisce le caratteristiche che contraddistinguono un dipendente.
 * Un dipendente è identificato da un id ed è caratterizzato da
 * un nome, un cognome e una email.
 * @author lucab
 */
public class Employee {

    //Id dipendente
    private final int ID;

    //Nome, cognome ed email di un dipendente.
    private String name, surname, email;

    public Employee(String name, String surname, String email,int ID){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.ID = ID;
    }

    //GETTERS
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
        return "Dipendente: " + surname + " " + name + ", Id: " + ID + ".";
    }
}//Employee
