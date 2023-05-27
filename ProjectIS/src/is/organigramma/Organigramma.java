package is.organigramma;

import is.Funzioni.Employee;
import is.Funzioni.Role;

import java.util.*;

public class Organigramma extends AbstractOrganigramma {
    private HashMap<Integer, HashSet<String>> employees = new HashMap<>();

    public Organigramma(String name,String description){
        super(name,description);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void addEmployee(Employee d){
        HashSet<String> ret = new HashSet<>();
        LinkedList<Role> roles = d.getRoles();
        for (int i = 0;i<roles.size();i++){
            Role cur = roles.get(i);
            if (cur.getArea().equals(this)){
                ret.add(cur.getName());
            }
        }
        employees.put(d.getID(),ret);
    }

    @Override
    public void removeEmployee(Employee d){
        //Rimuovere i ruoli del dipendente d associati all'area
        LinkedList<Role> roles = d.getRoles();
        Iterator<Role> it = roles.iterator();
        while(it.hasNext()){
            Role cur = it.next();
            if (cur.getArea().equals(this)){
                it.remove();
            }
        }
        employees.remove(d);
    }

    @Override
    public void accept() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organigramma that = (Organigramma) o;
        return name == that.name && Objects.equals(employees,that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, employees);
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
