package is.organigramma;

import is.dipendenti.Administrator;
import is.dipendenti.Employee;
import is.dipendenti.Role;

import java.util.Iterator;
import java.util.LinkedList;

public class Azienda implements AziendaIF{
    private final int cod;
    private String name;
    private final Administrator admin;
    public Azienda(int cod,int massimo, String name,Administrator admin){
        this.cod = cod;
        this.name = name;
        this.admin = admin;
    }
    //GETTERS
    public int getCod() {return cod;}
    public String getName() {
        return name;
    }
    public Administrator getAdmin() { return admin;}

    //SETTERS
    public void setName(String name){this.name = name;}

    @Override
    public LinkedList<String> getAreas(){
        LinkedList<String> ret = new LinkedList<>();

        Iterator<AreaOrganizationIF> it = admin.getOrganigramma().iterator();
        while(it.hasNext()){
            ret.add(((Organigramma)it.next()).getName());
        }
        return ret;
    }
    @Override
    public LinkedList<Integer> getIDEmployees(){
        LinkedList<Integer> ret = new LinkedList<>();

        for (Employee emp:admin.getEmployees()){
            ret.add(emp.getID());
        }
        return ret;
    }
    @Override
    public int getNEmployees(){
        return admin.getEmployees().size();
    }
    @Override
    public void addEmployee(Role role, Employee emp){
        if (getNEmployees()<admin.getMaxEmployees()) {
            boolean b = admin.addEmployee(role,emp);
            if (!b) throw new IllegalArgumentException("Impossibile aggiungere il dipendente "+emp.getSurname()+" "+emp.getName()+".");
        }
    }

    @Override
    public void removeEmployee(int ID){
        boolean b = admin.removeEmployee(ID);
        if (!b) throw new IllegalArgumentException("Impossibile effettuare la rimozione");
    }
}
