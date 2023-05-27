package is.organigramma;

import is.Funzioni.Administrator;
import is.Funzioni.Employee;
import is.Funzioni.Role;

import java.util.LinkedList;

public class Azienda implements AziendaIF {
    private final int cod;
    private int maxEmployees;
    private String name;
    private OrganigrammaIF organigramma;
    private final Administrator admin;
    private LinkedList<Employee> employees = new LinkedList<>();

    public Azienda(int cod,int massimo, String name,OrganigrammaIF area,String nameA,String surnameA,String email){
        this.cod = cod;
        this.maxEmployees = massimo;
        this.name = name;
        this.organigramma = area;
        admin = new Administrator(nameA,surnameA,email,this);
    }
    //GETTERS
    public int getCod() {
        return cod;
    }
    public int getMaxEmployees(){
        return maxEmployees;
    }
    public String getName() {
        return name;
    }
    public OrganigrammaIF getOrganigramma(){
        return organigramma;
    }
    public LinkedList<Employee> getEmployees() {
        return employees;
    }
    //SETTERS
    public void setMaxEmployees(int maxEmployees) {
        this.maxEmployees = maxEmployees;
    }


    @Override
    public void addEmployee(Employee d){
        boolean b = admin.addEmployee(this,d);
        if (!b) throw new IllegalArgumentException("Impossibile aggiungere un dipendente");
    }

    @Override
    public void removeEmployee(int ID){
        boolean b = admin.removeEmployee(this,ID);
        if (!b) throw new IllegalArgumentException("Rimozione Impossibile");
    }

    @Override
    public void assignRole(Employee d, Role role){
        OrganigrammaIF area = role.getArea();
        if (!organigramma.contains(area))
            throw new IllegalArgumentException("Ruolo inserito non valido");
        admin.assignArea(d,area);
        admin.assignRole(d,role);
    }

    @Override
    public int getNEmployees() {
        return employees.size();
    }
}
