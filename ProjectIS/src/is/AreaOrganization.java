package is;

import is.Funzioni.Dependent;

import java.util.*;

public class AreaOrganization implements AreaOrganizationIF {
    private String name;
    private HashMap<Integer, HashSet<String>> dependents = new HashMap<>();

    public AreaOrganization(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void addDependent(Dependent d){
        HashSet<String> ret = new HashSet<>();
        LinkedList<Role> roles = d.getRoles();
        for (int i = 0;i<roles.size();i++){
            Role cur = roles.get(i);
            if (cur.getArea().equals(this)){
                ret.add(cur.getName());
            }
        }
        dependents.put(d.getID(),ret);
    }
    public void removeDependent(Dependent d){
        //Rimuovere i ruoli del dipendente d associati all'area
        LinkedList<Role> roles = d.getRoles();
        Iterator<Role> it = roles.iterator();
        while(it.hasNext()){
            Role cur = it.next();
            if (cur.getArea().equals(this)){
                it.remove();
            }
        }
        dependents.remove(d);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AreaOrganization that = (AreaOrganization) o;
        return name == that.name && Objects.equals(dependents, that.dependents);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, dependents);
    }
}
