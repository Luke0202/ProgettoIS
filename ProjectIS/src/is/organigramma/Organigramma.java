package is.organigramma;

import is.dipendenti.Employee;
import is.dipendenti.Role;

import java.util.*;

public class Organigramma extends AbstractOrganigramma {
    private class Couple{
        String nameRole;
        int ID;

        public Couple(Role role,Employee employee){
            nameRole = role.getName();
            ID = employee.getID();
        }
        public Couple(String nameRole,int ID){
            this.nameRole=nameRole;
            this.ID = ID;
        }
    }
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
    public void addEmployee(Role role,Employee emp){
        if (role == null || emp == null) return;
        String nameRole = role.getName();
        int ID = emp.getID();
        Couple c = new Couple(nameRole,ID);
        couples.add(c);
    }

    @Override
    public void removeEmployee(Employee d){
        //Rimuovere i ruoli del dipendente d associati all'area
        if (d==null) return;

        int id = d.getID();

        Iterator<Couple> it = couples.iterator();
        while(it.hasNext()){
            Couple cur = it.next();
            if (cur.ID==id){
                it.remove();
            }
        }
    }

    @Override
    public HashSet<String> getRoles(){
        HashSet<String> roles = new HashSet<>();
        for(Couple c:couples){
            roles.add(c.nameRole);
        }
        return roles;
    }
    @Override
    public HashSet<Integer> getEmployees(){
        HashSet<Integer> employees = new HashSet<>();
        for(Couple c:couples){
            employees.add(c.ID);
        }
        return employees;
    }
    @Override
    public HashSet<String> getAreasOfEmployee(Employee emp){
        HashSet<String> listAreas = new HashSet<>();
        int ID = emp.getID();

        if (containsID(ID))
            listAreas.add(getName());

        Iterator<AreaOrganizationIF> it = iterator();
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
            if (c.ID == empID) return true;
        }
        return false;
    }
    @Override
    public void accept() {

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organigramma that = (Organigramma) o;
        return name == that.name;
    }

    @Override
    public int hashCode() {
        int M = 23;
        int ret = 0;
        for (int i:getEmployees()){
            ret+=i*M;
        }
        return ret;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(500);
        sb.append("<"+name+">\n");
        int N = getNChildren();
        for (int i = 0;i<N;i++){
            sb.append(getChild(i).toString());
        }
        sb.append("</"+name+">");
        return sb.toString();
    }
}
