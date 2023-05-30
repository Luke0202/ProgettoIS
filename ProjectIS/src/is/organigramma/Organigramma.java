package is.organigramma;

import is.dipendenti.Employee;
import is.dipendenti.Role;

import java.util.*;

public class Organigramma extends AbstractOrganigramma {
    private HashSet<Couple> couples = new HashSet<>();//Coppie nomeRuolo e IDDipendenti
    public Organigramma(String name,String description){
        super(name,description);
    }

    //GETTERS
    public String getName() {
        return name;
    }
    public HashSet<Couple> getCouples(){return couples;}

    //SETTERS
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void addEmployee(Role role,int id){
        if (role == null) return;
        couples.add(new Couple(role,id));
    }

    @Override
    public void removeEmployee(int id){
        //Rimuovere i ruoli del dipendente d associati all'area

        Iterator<Couple> it = couples.iterator();
        while(it.hasNext()){
            Couple cur = it.next();
            if (cur.getID()==id){
                it.remove();
            }
        }
    }

    @Override
    public void removeRole(Role r){
        Iterator<Couple> it = couples.iterator();
        while(it.hasNext()){
            Couple cur = it.next();
            if (cur.getRole().equals(r)) it.remove();
        }
    }

    @Override
    public HashSet<Role> getRoles(){
        HashSet<Role> roles = new HashSet<>();
        for(Couple c:couples){
            roles.add(c.getRole());
        }
        return roles;
    }
    @Override
    public HashSet<Role> getRoles(Employee emp){
        HashSet<Role> roles = new HashSet<>();
        for(Couple c:couples){
            if (c.getID() == emp.getID()) roles.add(c.getRole());
        }
        return roles;
    }

    @Override
    public HashSet<String> getAreasOfEmployee(Employee emp){
        HashSet<String> listAreas = new HashSet<>();
        int ID = emp.getID();

        if (containsID(ID))
            listAreas.add(getName());

        Iterator<OrganigrammaIF> it = iterator();
        while(it.hasNext()){
            Organigramma cur = (Organigramma)it.next();
            if (cur.containsID(ID))
                listAreas.add(cur.getName());
        }
        return listAreas;
    }
    @Override
    public boolean containsID(int empID){
        for(Couple c:couples){
            if (c.getID() == empID) return true;
        }
        return false;
    }

    @Override
    public boolean contains(Role role) {
        for(Couple c:couples){
            if (c.getRole().equals(role)) return true;
        }
        return false;
    }

    @Override
    public HashSet<String> getSubAreas(){
        HashSet<String> ret = new HashSet<>();
        Iterator<OrganigrammaIF> it = iterator();
        while(it.hasNext()){
            ret.add(((Organigramma)it.next()).getName());
        }
        return ret;
    }
    @Override
    public HashSet<Integer> getEmployees(){
        HashSet<Integer> employees = new HashSet<>();

        for(Couple c:couples){
            employees.add(c.getID());
        }
        return employees;
    }
    @Override
    public int getNEmployees(){ return getEmployees().size();}

    @Override
    public HashSet<Integer> getEmployees(Role r) {
        HashSet<Integer> employees = new HashSet<>();
        for(Couple c:couples){
            if (c.getRole().equals(r)) employees.add(c.getID());
        }
        return employees;
    }

    @Override
    public void accept() {

    }

}
