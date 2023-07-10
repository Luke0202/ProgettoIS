package is.azienda;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Tale classe astratta implementa l'interfaccia AziendaIF.
 * Definisce la struttura interna di un'azienda, composta da
 * un organigramma, un insieme di dipendenti e un insieme di ruoli.
 * @author lucab
 */
public class AbstractAzienda implements AziendaIF{

    //Massimo Id utilizzabile per identificare un dipendente
    private final int maxIdPossible = 50000;

    //Definizione organigramma
    private Organigramma organigramma;

    //Definizione lista dipendenti
    private HashSet<Employee> employees = new HashSet<>();

    //Definizione lista ruoli
    private HashSet<Role> roles = new HashSet<>();

    public AbstractAzienda(Organigramma organigramma){
        this.organigramma=organigramma;
    }

    //GETTERS
    public Organigramma getOrganigramma(){
        return organigramma;
    }
    public HashSet<Employee> getEmployees() {
        return employees;
    }
    public HashSet<Role> getRoles() {
        return roles;
    }

    //SETTERS
    public void setOrganigramma(Organigramma organigramma){
        this.organigramma=organigramma;
    }

    /**
     * Restituisce il numero di dipendenti che
     * lavorano nell'azienda.
     * @return numero dipendenti
     */
    @Override
    public int getNEmployees() {
        return getEmployees().size();
    }

    /**
     * Restituisce l'insieme dei dipendenti che presentano
     * un determinato ruolo.
     * @param role parametro usato per filtrare la ricerca dei dipendenti
     * @return insieme dei dipendenti con uno specifico ruolo
     */
    @Override
    public HashSet<Employee> getEmployee(Role role) {
        //HashSet da restituire
        HashSet<Employee> ret = new HashSet<>();

        //Ricavo l'area a partire dal ruolo
        Organigramma org = findArea(role);
        if (org == null) return ret;

        //Ottengo l'insieme degli id dei dipendenti che lavorano nell'area
        HashSet<Integer> listId = org.getEmployees(role);

        //A partire dagli id, ottengo i dipendenti
        for (Employee emp:employees){
            if (listId.contains(emp.getID())) ret.add(emp);
        }
        return ret;
    }

    /**
     * Restituisce l'insieme degli identificatori
     * dei dipendenti interni all'azienda.
     * @return insieme di identificatori
     */
    @Override
    public HashSet<Integer> getIdEmployees() {
        //HashSet da restituire
        HashSet<Integer> ret = new HashSet<>();

        for (Employee emp:employees){
            ret.add(emp.getID());
        }
        return ret;
    }

    /**
     * Permette di assegnare un ruolo ad un determinato
     * dipendente. Il ruolo deve già esistere all'interno
     * dell'azienda, altrimenti non è possibile assegnare tale
     * ruolo ad un dipendente.
     * Se il dipendente è nuovo, egli viene aggiunto
     * in azienda.
     * @param role ruolo da assegnare ad un dipendente
     * @param emp dipendente al quale assegnare un ruolo
     */
    @Override
    public void addEmployee(Role role, Employee emp) {

        //Verifica presenza ruolo
        if (roles.contains(role) && emp.getID()>=0){

            //Ricavo l'area a partire dal ruolo
            Organigramma org = findArea(role);
            if (org == null) return;

            //Aggiunta dipendente all'area
            org.addEmployee(role,emp.getID());

            //Aggiunta dipendente in azienda
            employees.add(emp);
        }
    }

    /**
     * Permette di assegnare un ruolo ad un determinato
     * dipendente. Il ruolo, così come il dipendente, deve
     * già esistere all'interno dell'azienda, altrimenti
     * non è possibile assegnare tale ruolo ad un dipendente.
     * @param role ruolo da assegnare ad un dipendente
     * @param id dipendente al quale assegnare un ruolo
     */
    @Override
    public void addRoleToEmployee(Role role, int id) {
        //Verifica presenza ruolo e validità id
        if (roles.contains(role) && id >=0){
            //Verifica presenza dipendente nell'azienda
            boolean trovato = false;

            for(Employee emp:employees){
                if (emp.getID()==id){
                    trovato = true; break;
                }
            }

            //Dipendente non trovato
            if (!trovato) return;

            //Ricavo l'area a partire dal ruolo
            Organigramma org = findArea(role);
            if (org == null) return;

            //Aggiunta dipendente all'area
            org.addEmployee(role,id);
        }
    }

    /**
     * Consente di licenziare un dipendente dall'azienda.
     * Tale dipendente viene così rimosso da ogni area in cui svolge
     * almeno un ruolo.
     * @param emp dipendente da licenziare
     */
    @Override
    public void removeEmployee(Employee emp) {
        //Verifica validità id
        if (emp.getID()<0) return;

        //Rimozione del dipendente dall'insieme dei dipendenti dell'azienda
        employees.remove(emp);

        //Rimozione del dipendente da ogni area
        Iterator<Area> it = organigramma.iterator();
        while(it.hasNext()){
            Organigramma cur = (Organigramma) it.next();
            cur.removeEmployee(emp.getID());
        }
    }

    /**
     * Consente di licenziare un dipendente dall'azienda.
     * Tale dipendente viene così rimosso da ogni area in cui svolge
     * almeno un ruolo.
     * @param id identificatore dipendente
     */
    @Override
    public void removeEmployee(int id) {
        //Verifica validità id
        if (id<0) return;

        boolean trovato = false;
        //Rimozione dall'insieme dei dipendenti dell'azienda
        Iterator<Employee> it = employees.iterator();
        while(it.hasNext()){
            if (it.next().getID()==id){
                it.remove();
                trovato = true;
                break;
            }
        }

        //Id non presente nell'azienda
        if (!trovato) return;

        //Rimozione del dipendente da ogni area
        Iterator<Area> it2 = organigramma.iterator();
        while(it2.hasNext()){
            it2.next().removeEmployee(id);
        }
    }

    /**
     * Permette di levare un ruolo ad un dipendente.
     * Se tale dipendente, a seguito della rimozione,
     * non presenta più alcun ruolo, egli risulta licenziato.
     * @param role ruolo da levare a un dipendente
     * @param emp dipendente al quale rimuovere il ruolo
     */
    @Override
    public void removeRoleFromEmployee(Role role, Employee emp) {
        //Verifica presenza ruolo e validità id
        if (roles.contains(role) && emp.getID()>=0){

            //Ricavo l'area a partire dal ruolo
            Organigramma org = findArea(role);
            if (org == null) return;

            //Rimozione coppia ruolo-dipendente dall'area
            org.removeEmployee(role,emp.getID());

            //Verifica condizione di licenziamento di un dipendente
            if (getRoles(emp).isEmpty())
                employees.remove(emp);
        }
    }

    /**
     * Permette di dissociare un ruolo da un dipendente.
     * Se tale dipendente, a seguito della rimozione,
     * non presenta più alcun ruolo, egli risulta licenziato.
     * @param role ruolo da levare a un dipendente
     * @param id id dipendente
     */
    @Override
    public void removeRoleFromEmployee(Role role, int id) {
        //Verifica presenza ruolo e validità id
        if (roles.contains(role) && id>=0){

            //Ricavo l'area a partire dal ruolo
            Organigramma org = findArea(role);
            if (org == null) return;

            //Rimozione coppia ruolo-dipendente dall'area
            org.removeEmployee(role,id);

            //Verifica condizione di licenziamento di un dipendente
            for (Employee emp:employees){
                if (emp.getID()==id){
                    if (getRoles(emp).isEmpty())
                        employees.remove(emp);
                }
            }
        }
    }

    /**
     * Restituisce il numero di ruoli definiti in azienda.
     * @return numero ruoli
     */
    @Override
    public int getNRoles(){
        return getRoles().size();
    }

    /**
     * Restituisce l'insieme dei nomi dei
     * ruoli dell'azienda.
     * @return insieme dei nomi dei ruoli
     */
    @Override
    public HashSet<String> getRolesName() {
        //HashSet da restituire
        HashSet<String> ret = new HashSet<>();

        //Aggiunta nomi ruoli
        for (Role r:roles){
            ret.add(r.getName());
        }
        return ret;
    }

    /**
     * Restituisce l'insieme dei nomi dei ruoli
     * assegnati ad un determinato dipendente.
     * @param emp dipendente del quale si vogliono
     *            ottenere i nomi dei ruoli
     * @return insieme nomi ruoli
     */
    @Override
    public HashSet<String> getRolesName(Employee emp) {
        //HashSet da restituire
        HashSet<String> ret = new HashSet<>();

        //Lista dei ruoli assegnati al dipendente
        HashSet<Role> roles = getRoles(emp);

        //Aggiunta nomi ruoli
        for (Role r:roles){
            ret.add(r.getName());
        }
        return ret;
    }

    /**
     * Restituisce i ruoli assegnati ad un determinato
     * dipendente. Per ogni area, una volta verificata
     * la presenza del dipendente, vengono restituiti
     * i ruoli a lui assegnati.
     * @param emp dipendente del quale si vogliono ottenere
     *            i ruoli
     * @return insieme dei ruoli di un determinato dipendente
     */
    @Override
    public HashSet<Role> getRoles(Employee emp) {
        //HashSet da restituire
        HashSet<Role> ret = new HashSet<>();

        //Per ogni area verifico la presenza del dipendente
        Iterator<Area> it = organigramma.iterator();
        while(it.hasNext()){
            Organigramma org = (Organigramma) it.next();
            //Aggiunta ruoli
            ret.addAll(org.getRoles(emp));
        }
        return ret;
    }

    /**
     * Permette l'aggiunta di un ruolo nella lista
     * dei ruoli dell'azienda.
     * @param role ruolo da aggiungere
     */
    @Override
    public void addRole(Role role) {
        roles.add(role);
    }

    /**
     * Permette di cancellare un ruolo dalla lista
     * dei ruoli dell'azienda.
     * Il ruolo viene rimosso se esso non è stato
     * assegnato ad alcun dipendente.
     * @param role ruolo da rimuovere
     */
    @Override
    public void removeRole(Role role) {
        //Verifica assenza dipendenti con tale ruolo
        for (Area a:organigramma){
            if (a.contains(role)) return;
        }

        roles.remove(role);
    }

    /**
     * Restituisce il numero di aree dell'organigramma.
     * @return numero aree
     */
    @Override
    public int getNAreas(){
        return getAreasName().size();
    }

    /**
     * Restituisce l'insieme dei nomi delle aree.
     * @return
     */
    @Override
    public HashSet<String> getAreasName() {
        //HashSet da restituire
        HashSet<String> ret = new HashSet<>();

        //Aggiunta nomi aree
        for (Area a:organigramma){
            ret.add(((Organigramma)a).getName());
        }

        return ret;
    }

    /**
     * Restituisce l'insieme dei nomi delle aree nelle
     * quali lavora un certo dipendente.
     * @param emp dipendente del quale si vogliono conoscere le aree
     * @return insieme nomi aree
     */
    @Override
    public HashSet<String> getAreasName(Employee emp) {
        //HashSet da restituire
        HashSet<String> ret = new HashSet<>();

        //Insieme dei ruoli del dipendente
        HashSet<Role> rolesOfEmp = getRoles(emp);

        //Per ogni ruolo, ottengo il nome dell'area in cui è definito
        for (Role role:rolesOfEmp){
            ret.add(role.getArea());
        }
        return ret;
    }

    /**
     * Restituisce l'area a partire dal suo nome.
     * @param area nome area
     * @return area
     */
    @Override
    public Organigramma getArea(String area) {
        //Analisi aree
        Iterator<Area> it = organigramma.iterator();
        while(it.hasNext()){
            Organigramma cur = (Organigramma) it.next();
            //Verifica nome
            if (cur.getName().equals(area)) return cur;
        }
        return null;
    }

    /**
     * Restituisce l'area padre di una determinata area.
     * Se l'area non presenta alcun area padre, allora viene
     * restituito null.
     * @param o area della quale si vuole conoscere il genitore
     * @return area padre
     */
    @Override
    public Organigramma getParent(Organigramma o) {
        //Analisi aree
        Iterator<Area> it = organigramma.iterator();
        while(it.hasNext()){
            Organigramma cur = (Organigramma) it.next();
            //Verifica paternità
            if (cur.isChild(o)) return cur;
        }
        return null;
    }

    /**
     * Permette di aggiungere un'area, data la conoscenza dell'area padre.
     * @param par area padre
     * @param org area figlia
     */
    @Override
    public void addArea(Organigramma par, Organigramma org) {
        par.addChild(org);
    }

    /**
     * Permette di rimuovere un'area dell'azienda.
     * Rimuovendo un'area, si eliminano di conseguenza tutte
     * le sotto-aree. Per consentire la rimozione di un'area
     * si necessita che tale area e tutte le sotto-aree non
     * presentino dipendenti.
     * @param org area da rimuovere
     */
    @Override
    public void removeArea(Organigramma org) {
        //Verifica condizione sufficiente di rimozione area
        for (Area a:org){
            if (a.getNEmployees()>0) return;
        }

        //Rimozione ruoli dalla lista dei ruoli
        for (Role role:roles){
            if (role.getArea().equals(org.getName())) removeRole(role);
        }

        //Ricavo area padre per effettuare la rimozione dell'area figlia
        Organigramma par = getParent(org);
        if (par==null) org = null;
        else par.removeChild(org);
    }

    /**
     * Restituisce l'identificatore da assegnare ad un dipendente.
     * L'id massimo assegnabile è pari a maxIdPossible, il quale può
     * esprimere un limite al numero di dipendenti assumibili da un'azienda.
     * @return id
     */
    @Override
    public int giveID() {
        int id = 0;

        while (id<=maxIdPossible){
            //Verifico che tale id non sia già stato assegnato
            if (differentByOthers(id))
                return id;
            id++;
        }
        //È stato superato il limite dei dipendenti che si possono assumere
        return -1;
    }

    /**
     * Verifica l'assenza di un dipendente interno all'azienda
     * con un determinato id.
     * Restituisce false se è presente un dipendente con tale id,
     * altrimenti restituisce true.
     * @param id identificatore dipendente
     * @return assenza/presenza di un dipendente con tale id
     */
    private boolean differentByOthers(int id){
        for (Employee emp:employees){
            if (id==emp.getID()){
                return false;
            }
        }
        return true;
    }

    /**
     * Restituisce, a partire da un ruolo, l'area associata.
     * Restituisce null se non è presente un'area in cui è stato
     * definito tale ruolo.
     * @param role ruolo da ricercare nelle aree
     * @return area contenente il ruolo
     */
    private Organigramma findArea(Role role){
        Iterator<Area> it = organigramma.iterator();

        while(it.hasNext()){
            Organigramma cur = (Organigramma) it.next();
            //Verifica appartenenza ruolo all'area
            if (cur.getName().equals(role.getArea())) return cur;
        }
        return null;
    }
}//AbstractAzienda
