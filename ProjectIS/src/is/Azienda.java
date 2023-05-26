package is;

import is.Funzioni.Administrator;
import is.Funzioni.Dependent;

import java.util.HashSet;
import java.util.LinkedList;

public class Azienda implements AziendaIF{
    private int cod;
    private int maxDependents = 100;
    private String name;
    private Administrator admin;
    private LinkedList<Dependent> dependents = new LinkedList<>();
    private HashSet<AreaOrganization> areas = new HashSet<>();

    public Azienda(int cod,String name){
        this.cod = cod;
        this.name = name;
    }


    public int getCod() {
        return cod;
    }
    public String getName() {
        return name;
    }
    public LinkedList<Dependent> getDependents() {
        return dependents;
    }
    public int getMaxDependents(){
        return maxDependents;
    }
    public HashSet<AreaOrganization> getAreas(){
        return areas;
    }
    public void setMaxDependents(int maxDependents) {
        this.maxDependents = maxDependents;
    }

    @Override
    public LinkedList<Dependent> getDipendenti() {
        return null;
    }


    @Override
    public void addDependent(Dependent d){
        boolean b = admin.addDependent(this,d);
        if (!b) throw new IllegalArgumentException("Impossibile aggiungere un dipendente");
    }

    @Override
    public void removeDependent(int ID){
        boolean b = admin.removeDependent(this,ID);
        if (!b) throw new IllegalArgumentException("Rimozione Impossibile");
    }

    @Override
    public void assignRole(Dependent d, Role role){
        AreaOrganization area = role.getArea();
        if (!areas.contains(area))
            throw new IllegalArgumentException("Ruolo inserito non valido");
        admin.assignArea(d,area);
        admin.assignRole(d,role);
    }

    @Override
    public int getNDependents() {
        return 0;
    }
}
