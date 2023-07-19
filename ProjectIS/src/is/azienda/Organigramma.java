package is.azienda;

import is.textParser.AreaVisitor;
import java.util.*;

/**
 * Tale classe fornisce i metodi di gestione dei dipendenti
 * e dei ruoli interni a un'area. Include metodi di visita
 * dellorganigramma.
 * @author lucab
 */
public class Organigramma extends AbstractCompositeArea {

    //Coppie ruolo-dipendente
    //Le coppie di tale insieme comprendono solo i ruoli assegnati ai vari dipendenti
    //I ruoli, definiti per tale area, che non sono stati ancora assegnati, non si trovano nelle coppie
    private HashSet<Couple> couples = new HashSet<>();

    //Nome e descrizione di un'area
    private String name,description;

    public Organigramma(String name,String description){
        this.name = name; this.description=description;
    }

    //GETTERS
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public HashSet<Couple> getCouples(){return couples;}

    //SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Restituisce l'insieme degli id dei dipendenti che
     * lavorano nell'area.
     * @return insieme d'identificatori
     */
    @Override
    public HashSet<Integer> getEmployees(){
        //HashSet da restituire
        HashSet<Integer> ret = new HashSet<>();

        //Aggiunta dipendenti
        for(Couple c:couples){
            ret.add(c.getID());
        }

        return ret;
    }

    /**
     * Restituisce l'insieme degli id dei dipendenti che
     * presentano un determinato ruolo.
     * Il ruolo deve appartenere all'area, altrimenti viene
     * restituito un insieme vuoto.
     * @param role ruolo che devono possedere i dipendenti
     * @return insieme d'identificatori
     */
    @Override
    public HashSet<Integer> getEmployees(Role role) {
        //HashSet da restituire
        HashSet<Integer> ret = new HashSet<>();

        //Verifica ruolo valido
        if (role == null || !role.getArea().equals(name)) return ret;

        //Aggiunta dipendenti
        for(Couple c:couples){
            //Verifica ruolo
            if (c.getRole().equals(role)) ret.add(c.getID());
        }
        return ret;
    }

    /**
     * Permette di assegnare un ruolo ad un determinato dipendente.
     * Un dipendente è identificato dall'id.
     * Il ruolo deve appartenere all'area, altrimenti non viene
     * assegnato al dipendente.
     * @param role ruolo da assegnare
     * @param id identificatore dipendente
     */
    @Override
    public void addEmployee(Role role,int id){
        //Verifica ruolo valido
        if (role == null || !role.getArea().equals(name)) return;

        couples.add(new Couple(role,id));
    }

    /**
     * Permette di rimuovere un dipendente da una specifica area.
     * @param id identificatore dipendente
     */
    @Override
    public void removeEmployee(int id){
        Iterator<Couple> it = couples.iterator();

        while(it.hasNext()){
            Couple cur = it.next();
            //Verifica presenza dipendente
            if (cur.getID()==id){
                it.remove();
            }
        }
    }

    /**
     * Permettere di levare un ruolo da uno specifico dipendente.
     * @param role ruolo da levare
     * @param id identificatore dipendente
     */
    @Override
    public void removeEmployee(Role role,int id){
        //Verifica ruolo valido
        if (!role.getArea().equals(name)) return;

        Iterator<Couple> it = couples.iterator();
        while(it.hasNext()){
            Couple cur = it.next();
            //Verifica corrispondenza ruolo-dipendente
            if (cur.getRole().equals(role) && cur.getID()==id){
                it.remove();
                break;
            }
        }
    }

    /**
     * Permette di rimuovere un ruolo dall'area.
     * @deprecated è preferibile rimuovere un ruolo in maniera indiretta,
     * rimuovendo i dipendenti che presentano tale ruolo. Il rischio è
     * dato dalla possibilità di avere in azienda dipendenti senza ruolo.
     * @param role ruolo da rimuovere
     */
    @Override
    public void removeRole(Role role){
        //Verifica ruolo valido
        if (!role.getArea().equals(name)) return;

        Iterator<Couple> it = couples.iterator();
        while(it.hasNext()){
            Couple cur = it.next();
            //Verifica corrispondenza ruolo
            if (cur.getRole().equals(role)) it.remove();
        }
    }

    /**
     * Restituisce l'insieme dei ruoli di un'area
     * @return insieme di ruoli
     */
    @Override
    public HashSet<Role> getRoles(){
        //HashSet da restituire
        HashSet<Role> ret = new HashSet<>();

        //Aggiunta ruoli
        for(Couple c:couples){
            ret.add(c.getRole());
        }
        return ret;
    }

    /**
     * Restituisce l'insieme dei ruoli di un'area, assegnati ad
     * un determinato dipendente.
     * @param emp dipendente da ricercare
     * @return insieme dei ruoli
     */
    @Override
    public HashSet<Role> getRoles(Employee emp){
        //HashSet da restituire
        HashSet<Role> ret = new HashSet<>();

        //Aggiunta ruoli
        for(Couple c:couples){
            if (c.getID() == emp.getID()) ret.add(c.getRole());
        }
        return ret;
    }

    /**
     * Restituisce l'insieme dei nomi delle aree, in cui lavora
     * un determinato dipendente.
     * Si considera l'area corrente come area radice della gerarchia.
     * @param emp dipendente da ricercare
     * @return insieme dei nome delle aree
     */
    @Override
    public HashSet<String> getAreasOfEmployee(Employee emp){
        //HashSet da restituire
        HashSet<String> ret = new HashSet<>();

        //ID da ricercare
        int ID = emp.getID();

        Iterator<Area> it = iterator();
        while(it.hasNext()){
            //Area corrente
            Organigramma cur = (Organigramma)it.next();
            //Verifica presenza dipendente
            if (cur.contains(ID))
                //Aggiunta nome area
                ret.add(cur.getName());
        }
        return ret;
    }

    /**
     * Verifica la presenza di un dipendente all'interno dell'area.
     * @param empID id da ricercare
     * @return presenza o meno del dipendente
     */
    @Override
    public boolean contains(int empID){
        for(Couple c:couples){
            if (c.getID() == empID) return true;
        }
        return false;
    }

    /**
     * Verifica la presenza di un ruolo all'interno dell'area.
     * @param role ruolo da ricercare
     * @return presenza o meno del ruolo
     */
    @Override
    public boolean contains(Role role) {
        for(Couple c:couples){
            if (c.getRole().equals(role)) return true;
        }
        return false;
    }

    /**
     * Restituisce l'insieme dei nomi delle sotto-aree.
     * Si considera l'area corrente come area radice della
     * gerarchia.
     * @return insieme dei nomi delle aree
     */
    @Override
    public HashSet<String> getSubAreas(){
        //HashSet da restituire
        HashSet<String> ret = new HashSet<>();

        Iterator<Area> it = iterator();
        //Non bisogna aggiungere l'area corrente
        it.next();
        while(it.hasNext()){
            ret.add(((Organigramma)it.next()).getName());
        }
        return ret;
    }

    /**
     * Permette, attraverso un areaVisitor, di visitare
     * l'organigramma. In particolare la visita ha come
     * obiettivo la memorizzazione dell'organigramma in
     * memoria secondaria
     * @param visitor
     */
    @Override
    public void accept(AreaVisitor visitor) {
        visitor.visit(this);
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
            sb.append(getChild(i).toString()+"\n");
        }
        sb.append("</"+name+">");
        return sb.toString();
    }
}//Organigramma
