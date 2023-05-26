package is.Funzioni;

import is.AreaOrganization;
import is.AreaOrganizationIF;
import is.Azienda;
import is.Role;

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
    public int getNumberDependents() {
        return azienda.numeroDipendenti();
    }

    @Override
    public boolean addDependent(Azienda a, Dependent d) {
        int N = a.numeroDipendenti();
        if (N<a.getMaxDependents()) {
            a.getDipendenti().add(d);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeDependent(Azienda a,int ID) {
        LinkedList<Dependent> ll = a.getDipendenti();
        Iterator<Dependent> it = ll.iterator();
        while(it.hasNext()){
            Dependent dip = it.next();
            if (dip.getID()==ID){
                it.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public void assignRole(Dependent d, Role role){
        //Ogni unità organizzativa presenta un set di ruoli
        d.takeRole(role);
    }

    @Override
    public int giveID(){
        LinkedList<Dependent> dep = azienda.getDipendenti();
        int N = azienda.getDipendenti().size();
        if (N<azienda.getMaxDependents()){
            for (int i = 0;i<N;i++){
                if (i!=dep.get(i).getID()){
                    return i;
                }
            }
        }return -1;
    }

    public void assignArea(Dependent d, AreaOrganizationIF area){
        area.addDependent(d);
    }
}