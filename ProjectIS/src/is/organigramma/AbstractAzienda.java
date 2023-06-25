package is.organigramma;

import java.util.HashSet;
import java.util.Iterator;

public class AbstractAzienda implements AziendaIF{
    private final int maxIdPossible = 10000;
    private Organigramma organigramma;
    private HashSet<Employee> employees = new HashSet<>();
    private HashSet<Role> roles = new HashSet<>();

    public AbstractAzienda(Organigramma organigramma){
        this.organigramma=organigramma;
    }
    //GETTERS
    public Organigramma getOrganigramma(){ return organigramma;}
    public HashSet<Employee> getEmployees() {
        return employees;
    }
    public HashSet<Role> getRoles() {return roles;}

    //SETTERS
    public void setOrganigramma(Organigramma organigramma){this.organigramma=organigramma;}


    @Override
    public int getNEmployees() {
        return getEmployees().size();
    }

    @Override
    public HashSet<Employee> getEmployee(Role role) {
        HashSet<Employee> ret = new HashSet<>();

        String nameArea = role.getArea();
        Organigramma org = getArea(nameArea);
        HashSet<Integer> listId = org.getEmployees(role);

        for (Employee emp:employees){
            if (listId.contains(emp.getID())) ret.add(emp);
        }
        return ret;
    }

    @Override
    public HashSet<Integer> getIdEmployees() {
        HashSet<Integer> ret = new HashSet<>();

        for (Employee emp:employees){
            ret.add(emp.getID());
        }
        return ret;
    }

    @Override
    public void addEmployee(Role role, Employee emp) {
        if (roles.contains(role)){
            Organigramma org = findArea(role);
            if (org == null) return;
            org.addEmployee(role,emp.getID());
            if (!employees.contains(emp)) employees.add(emp);
        }
    }

    @Override
    public void removeEmployee(Employee emp) {
        employees.remove(emp);

        Iterator<Area> it = organigramma.iterator();
        while(it.hasNext()){
            Organigramma cur = (Organigramma) it.next();
            cur.removeEmployee(emp.getID());
        }
    }

    @Override
    public void removeEmployee(Role role, Employee emp) {
        if (roles.contains(role)){
            Organigramma org = findArea(role);
            org.removeEmployee(emp.getID());
        }
    }

    @Override
    public int getNRoles(){
        return getRoles().size();
    }

    @Override
    public HashSet<Role> getRoles(Employee emp) {
        HashSet<Role> ret = new HashSet<>();

        Iterator<Area> it = organigramma.iterator();
        while(it.hasNext()){
            Organigramma org = (Organigramma) it.next();
            ret.addAll(org.getRoles(emp));
        }
        return ret;
    }

    @Override
    public void addRole(Role role) {
        roles.add(role);
    }

    @Override
    public void removeRole(Role role) {
        //se non ha dipendenti associati allora è possibile rimuoverlo
        if (!isInOrganigramma(role))  roles.remove(role);
    }

    @Override
    public HashSet<String> getRolesName() {
        HashSet<String> ret = new HashSet<>();

        for (Role r:roles){
            ret.add(r.getName());
        }
        return ret;
    }

    @Override
    public HashSet<String> getRolesName(Employee emp) {
        HashSet<String> ret = new HashSet<>();
        HashSet<Role> roles = getRoles(emp);
        for (Role r:roles){
            ret.add(r.getName());
        }
        return ret;
    }

    @Override
    public int getNAreas(){
        return getAreasName().size();
    }

    @Override
    public HashSet<String> getAreasName() {
        HashSet<String> ret = new HashSet<>();

        for (Area a:organigramma){
            ret.add(((Organigramma)a).getName());
        }

        return ret;
    }

    @Override
    public HashSet<String> getAreasName(Employee emp) {
        HashSet<String> ret = new HashSet<>();
        HashSet<Role> rolesOfEmp = getRoles(emp);
        for (Role role:rolesOfEmp){
            ret.add(role.getArea());
        }
        return ret;
    }

    @Override
    public Organigramma getArea(String area) {
        Iterator<Area> it = organigramma.iterator();
        while(it.hasNext()){
            Organigramma cur = (Organigramma) it.next();
            if (cur.getName().equals(area)) return cur;
        }
        return null;
    }

    @Override
    public Organigramma getParent(Organigramma o) {
        Iterator<Area> it = organigramma.iterator();
        while(it.hasNext()){
            Organigramma cur = (Organigramma) it.next();
            if (cur.isChild(o)) return cur;
        }
        return null;
    }

    @Override
    public void addArea(Organigramma par, Organigramma org) {
        par.addChild(org);
    }

    @Override
    public void removeArea(Organigramma org) {
        for (Role role:roles){
            if (role.getArea().equals(org.getName())) removeRole(role);
        }
        Organigramma par = getParent(org);
        if (par==null) org = null;
        else par.removeChild(org);
    }

    @Override
    public int giveID() {

        int id = 0;
        while (id<maxIdPossible){
            if (differentByOthers(id))
                return id;
            id++;
        }
        return -1;
    }

    private boolean differentByOthers(int id){
        for (Employee emp:employees){
            if (id==emp.getID()){
                return false;
            }
        }
        return true;
    }
    private Organigramma findArea(Role role){

        Iterator<Area> it = organigramma.iterator();
        while(it.hasNext()){
            Organigramma cur = (Organigramma) it.next();
            if (cur.getName().equals(role.getArea())) return cur;
        }
        return null;
    }
    private boolean isInOrganigramma(Role role){

        Iterator<Area> it = organigramma.iterator();
        while(it.hasNext()){
            if (it.next().contains(role)) return true;
        }
        return false;
    }
}
