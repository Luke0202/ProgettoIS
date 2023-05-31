package is.dipendenti;


import is.organigramma.Organigramma;
import is.organigramma.OrganigrammaIF;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Administrator implements AdministratorIF {
    //L'amministratore dell'azienda è un dipendente
    //E' unico
    //Ha la possibilità
    private final int maxIdPossible = 10000;
    private final Organigramma organigramma;
    private LinkedList<Employee> employees = new LinkedList<>();
    private HashSet<Role> roles = new HashSet<>();

    public Administrator(Organigramma organigramma){
        this.organigramma=organigramma;
    }
    //GETTERS
    public Organigramma getOrganigramma(){ return organigramma;}
    public LinkedList<Employee> getEmployees() {
        return employees;
    }
    public HashSet<Role> getRoles() {return roles;}



    @Override
    public void addEmployee(Role role,Employee emp) {
        if (roles.contains(role)){
            Organigramma org = findArea(role);
            if (org == null) return;
            org.addEmployee(role,emp.getID());
            if (!employees.contains(emp)) employees.add(emp);
        }
    }

    @Override
    public void removeEmployee(Employee e) {
        employees.remove(e);
        organigramma.removeEmployee(e.getID());

        Iterator<OrganigrammaIF> it = organigramma.iterator();
        while(it.hasNext()){
            Organigramma cur = (Organigramma) it.next();
            cur.removeEmployee(e.getID());
        }
    }

    @Override
    public void removeEmployee(Role role,Employee emp) {
        if (roles.contains(role)){
            Organigramma org = findArea(role);
            org.removeEmployee(emp.getID());
        }
    }
    @Override
    public HashSet<Integer> getIDEmployees(){
        HashSet<Integer> ret = new HashSet<>();
        for (Employee emp:employees){
            ret.add(emp.getID());
        }
        return ret;
    }
    @Override
    public int giveID(){
        int N = employees.size();
        int id = 0;
        while (id<maxIdPossible){
            if (differentByOthers(id,N))
                return id;
            id++;
        }
        return -1;
    }
    private boolean differentByOthers(int id,int N){
        for (int i = 0;i<N;i++){
            if (id==employees.get(i).getID()){
                return false;
            }
        }
        return true;
    }

    private Organigramma findArea(Role r){
        if (organigramma.getName().equals(r.getArea())) return organigramma;
        Iterator<OrganigrammaIF> it = organigramma.iterator();
        while(it.hasNext()){
            Organigramma cur = (Organigramma) it.next();
            if (cur.getName().equals(r.getArea())) return cur;
        }
        return null;
    }
    @Override
    public HashSet<Role> getRoles(Employee emp){
        HashSet<Role> ret = new HashSet<>();

        ret.addAll(organigramma.getRoles(emp));

        Iterator<OrganigrammaIF> it = organigramma.iterator();
        while(it.hasNext()){
            Organigramma org = (Organigramma) it.next();
            ret.addAll(org.getRoles(emp));
        }
        return ret;
    }

    @Override
    public HashSet<Organigramma> getAreas(Employee emp){
        HashSet<Organigramma> ret = new HashSet<>();

        int empID = emp.getID();

        if (organigramma.containsID(empID)) ret.add(organigramma);

        Iterator<OrganigrammaIF> it = organigramma.iterator();

        while(it.hasNext()){
            Organigramma cur = (Organigramma)it.next();
            if (cur.containsID(empID)) ret.add(cur);
        }
        return ret;
    }
    @Override
    public HashSet<Employee> getEmployee(Role role){
        HashSet<Employee> ret = new HashSet<>();

        String nameArea = role.getArea();
        Organigramma org = getArea(nameArea);
        HashSet<Integer> listID = org.getEmployees(role);

        for (Employee emp:employees){
            if (listID.contains(emp.getID())) ret.add(emp);
        }
        return ret;
    }

    @Override
    public Organigramma getArea(String area){
        if (organigramma.getName().equals(area)) return organigramma;

        Iterator<OrganigrammaIF> it = organigramma.iterator();
        while(it.hasNext()){
            Organigramma cur = (Organigramma) it.next();
            if (cur.getName().equals(area)) return cur;
        }
        return null;
    }

    @Override
    public void addRole(Role r){
        roles.add(r);
    }
    @Override
    public void removeRole(Role role){
        //se non ha dipendenti associati allora è possibile rimuoverlo
        if (!containsInOrganigramma(role))  roles.remove(role);
    }
    private boolean containsInOrganigramma(Role role){
        if (organigramma.contains(role)) return true;

        Iterator<OrganigrammaIF> it = organigramma.iterator();
        while(it.hasNext()){
            if (it.next().contains(role)) return true;
        }
        return false;
    }
    @Override
    public HashSet<String> getAllAreas(){
        HashSet<String> ret = new HashSet<>();
        ret.add(organigramma.getName());

        Iterator<OrganigrammaIF> it = organigramma.iterator();
        while(it.hasNext()){
            ret.add(((Organigramma)it.next()).getName());
        }
        return ret;
    }
    @Override
    public Organigramma getParent(Organigramma o){
        if (organigramma.containsArea(o)) return organigramma;

        Iterator<OrganigrammaIF> it = organigramma.iterator();
        while(it.hasNext()){
            Organigramma cur = (Organigramma) it.next();
            if (cur.containsArea(o)) return cur;
        }
        return null;
    }

    @Override
    public void addArea(Organigramma par, Organigramma org) {
        par.addChild(org);
    }

    @Override
    public void removeArea(Organigramma par, Organigramma org) {
        par.removeChild(org);
    }
}