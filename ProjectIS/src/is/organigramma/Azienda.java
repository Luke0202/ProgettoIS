package is.organigramma;

import is.dipendenti.Administrator;
import is.dipendenti.Employee;
import is.dipendenti.Role;

import java.util.HashSet;
import java.util.Objects;

public class Azienda implements AziendaIF{
    private final int cod;
    private String name, headquarter, presidentName, presidentSurname;
    private final Administrator admin;
    public Azienda(int cod,String name,String headquarter,String presidentName,String presidentSurname,Administrator admin){
        this.cod = cod;
        this.name = name;
        this.admin = admin;
    }
    //GETTERS

    public String getHeadquarter() { return headquarter;}
    public String getPresidentName() { return presidentName;}
    public String getPresidentSurname() { return presidentSurname;}
    public int getCod() {return cod;}
    public String getName() {
        return name;
    }
    public Administrator getAdmin() { return admin;}

    //SETTERS

    public void setHeadquarter(String headquarter) {this.headquarter = headquarter;}
    public void setPresidentName(String presidentName) {this.presidentName = presidentName;}
    public void setPresidentSurname(String presidentSurname) {this.presidentSurname = presidentSurname;}
    public void setName(String name){this.name = name;}


    @Override
    public int getNEmployees(){return admin.getEmployees().size();}
    @Override
    public HashSet<Role> getRoles(){return admin.getRoles();}
    @Override
    public HashSet<String> getAreas(){
        HashSet<String> ret = new HashSet<>();
        HashSet<Role> roles = admin.getRoles();
        for (Role r:roles){
            ret.add(r.getArea());
        }
        return ret;
    }
    @Override
    public HashSet<String> getNameRole(){
        HashSet<String> ret = new HashSet<>();
        HashSet<Role> roles = admin.getRoles();
        for (Role r:roles){
            ret.add(r.getName());
        }
        return ret;
    }

    @Override
    public HashSet<Role> getRoles(Employee emp) {return admin.getRoles(emp);}

    @Override
    public HashSet<String> getAreas(Employee emp) {
        HashSet<String> ret = new HashSet<>();
        HashSet<Role> roles = admin.getRoles(emp);
        for (Role r:roles){
            ret.add(r.getArea());
        }
        return ret;
    }

    @Override
    public HashSet<String> getNameRoles(Employee emp) {
        HashSet<String> ret = new HashSet<>();
        HashSet<Role> roles = admin.getRoles(emp);
        for (Role r:roles){
            ret.add(r.getName());
        }
        return ret;
    }

    @Override
    public HashSet<Integer> getIDEmployees(){
        HashSet<Integer> ret = new HashSet<>();

        for (Employee emp:admin.getEmployees()){
            ret.add(emp.getID());
        }
        return ret;
    }

    @Override
    public void addEmployee(Role role, Employee emp){
        if (getNEmployees()<admin.getMaxEmployees()) admin.addEmployee(role,emp);
    }

    @Override
    public void removeEmployee(Employee emp){ admin.removeEmployee(emp);}

    @Override
    public void removeEmployee(Role role,Employee emp){ admin.removeEmployee(role,emp);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Azienda azienda = (Azienda) o;
        return cod == azienda.cod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod);
    }

    @Override
    public String toString() {
        return "Azienda{" +
                "cod=" + cod +
                ", name='" + name + '\'' +
                ", headquarter='" + headquarter + '\'' +
                ", presidentName='" + presidentName + '\'' +
                ", presidentSurname='" + presidentSurname + '\'' +
                '}';
    }
}
