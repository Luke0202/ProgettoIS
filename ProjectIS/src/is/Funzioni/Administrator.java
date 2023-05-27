package is.Funzioni;

import is.organigramma.AreaOrganizationIF;
import is.organigramma.Azienda;

import java.util.Iterator;
import java.util.LinkedList;

public class Administrator implements AdministratorIF {
    //L'amministratore dell'azienda è un dipendente
    //E' unico
    //Ha la possibilità

    private Azienda azienda;
    private String name, surname, email;
    public Administrator(String name, String surname, String email, Azienda azienda){
        this.azienda = azienda;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean addEmployee(Azienda azienda, Employee d) {
        int N = azienda.getNEmployees();
        if (N<azienda.getMaxEmployees()) {
            azienda.getEmployees().add(d);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEmployee(Azienda azienda,int ID) {
        LinkedList<Employee> ll = azienda.getEmployees();
        Iterator<Employee> it = ll.iterator();
        while(it.hasNext()){
            Employee emp = it.next();
            if (emp.getID()==ID){
                it.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public void assignRole(Employee d, Role role){
        //Ogni unità organizzativa presenta un set di ruoli
        d.takeRole(role);
    }

    @Override
    public int giveID(){
        LinkedList<Employee> dep = azienda.getEmployees();
        int N = azienda.getEmployees().size();
        if (N<azienda.getMaxEmployees()){
            for (int i = 0;i<N;i++){
                if (i!=dep.get(i).getID()){
                    return i;
                }
            }
        }return -1;
    }

    @Override
    public void assignArea(Employee d, AreaOrganizationIF area){
        area.addEmployee(d);
    }
}