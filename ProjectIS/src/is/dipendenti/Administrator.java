package is.dipendenti;

import is.organigramma.AreaOrganizationIF;
import is.organigramma.Organigramma;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Administrator implements AdministratorIF {
    //L'amministratore dell'azienda è un dipendente
    //E' unico
    //Ha la possibilità
    private final int ID;
    private final int maxIdPossible = 10000;
    private int maxEmployees;
    private String name, surname, email;
    private final Organigramma organigramma;
    private LinkedList<Employee> employees = new LinkedList<>();
    private HashSet<Role> roles = new HashSet<>();

    public Administrator(String name, String surname, String email, int maxEmp,Organigramma organigramma){
        this.name = name;
        this.surname = surname;
        this.email = email;
        Random ran = new Random();
        this.maxEmployees = maxEmp;
        this.organigramma=organigramma;
        this.ID = ran.nextInt(maxEmp-1);
    }
    //GETTERS
    public int getID(){return ID;}
    public int getMaxEmployees() {return maxEmployees;}
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getEmail() {
        return email;
    }
    public Organigramma getOrganigramma(){ return organigramma;}
    public LinkedList<Employee> getEmployees() {
        return employees;
    }
    public HashSet<Role> getRoles() {return roles;}

    //SETTERS
    public void setMaxEmployees(int maxEmployees) {
        if (maxEmployees>0 && maxEmployees<maxIdPossible) this.maxEmployees = maxEmployees;
    }
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
    public void addEmployee(Role role,Employee emp) {
        Organigramma org = findArea(role);
        if (org == null) return;
        org.addEmployee(role,emp.getID());
        if (!employees.contains(emp)) employees.add(emp);
    }

    @Override
    public void removeEmployee(Employee e) {
        employees.remove(e);
        organigramma.removeEmployee(e.getID());

        Iterator<AreaOrganizationIF> it = organigramma.iterator();
        while(it.hasNext()){
            Organigramma cur = (Organigramma) it.next();
            cur.removeEmployee(e.getID());
        }
    }
    @Override
    public void removeEmployee(Role role,Employee emp) {
        Organigramma org = findArea(role);
        org.removeEmployee(emp.getID());
    }
    @Override
    public int giveID(){
        int N = employees.size();
        if (N<maxEmployees){
            int id = 0;
            while (id<maxIdPossible){
                if (differentByOthers(id,N))
                    return id;
            }
        }return -1;
    }
    private boolean differentByOthers(int id,int N){
        boolean b = true;
        for (int i = 0;i<N;i++){
            if (id==employees.get(i).getID()){
                b = false; break;
            }
        }
        return b;
    }

    private Organigramma findArea(Role r){
        if (organigramma.getName().equals(r.getArea())) return organigramma;
        Iterator<AreaOrganizationIF> it = organigramma.iterator();
        while(it.hasNext()){
            Organigramma cur = (Organigramma) it.next();
            if (cur.getName().equals(r.getArea())) return cur;
        }
        return null;
    }
    @Override
    public HashSet<Role> getRoles(Employee emp){
        HashSet<Role> ret = new HashSet<>();

        ret.addAll(organigramma.getRolesOfEmployee(emp));

        Iterator<AreaOrganizationIF> it = organigramma.iterator();
        while(it.hasNext()){
            Organigramma org = (Organigramma) it.next();
            ret.addAll(org.getRolesOfEmployee(emp));
        }
        return ret;
    }
    @Override
    public void addRole(Role r){
        roles.add(r);
    }
    @Override
    public void removeRole(Role r){
        roles.remove(r);
    }
}