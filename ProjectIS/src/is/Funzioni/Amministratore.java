package is.Funzioni;

import is.Azienda;

import java.util.Iterator;
import java.util.LinkedList;

public class Amministratore extends Dipendente implements AmministratoreIF{
    //L'amministratore dell'azienda è un dipendente
    //E' unico
    //Ha la possibilità
    public Amministratore(String nome,String cognome,String email){
        super(nome,cognome,email);
    }

    @Override
    public boolean aggiugniDipendente(Azienda a, Dipendente d) {
        int numeroDipendenti = a.numeroDipendenti();
        if (numeroDipendenti<a.getMaxDipendenti()) {
            a.getDipendenti().add(d);
            return true;
        }
        return false;
    }

    @Override
    public boolean rimuoviDipendente(Azienda a,int ID) {
        LinkedList<Dipendente> ll = a.getDipendenti();
        Iterator<Dipendente> it = ll.iterator();
        while(it.hasNext()){
            Dipendente dip = it.next();
            if (dip.getID()==ID){
                it.remove();
                return true;
            }
        }
        return false;
    }
}