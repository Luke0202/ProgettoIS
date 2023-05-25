package is;

import is.Funzioni.Amministratore;
import is.Funzioni.Dipendente;

import java.util.LinkedList;

public class Azienda {
    private int codice;
    private String nome;
    private LinkedList<Dipendente> listaDipendenti = new LinkedList<>();
    private Amministratore admin;
    private int maxDipendenti = 100;
    public Azienda(int codice,String nome){
        this.codice = codice;
        this.nome = nome;
    }
    public int getCodice() {
        return codice;
    }
    public String getNome() {
        return nome;
    }
    public LinkedList<Dipendente> getDipendenti() {
        return listaDipendenti;
    }
    public int getMaxDipendenti(){
        return maxDipendenti;
    }
    public void setMaxDipendenti(int N){
        maxDipendenti=N;
    }
    public int numeroDipendenti(){
        return listaDipendenti.size();
    }
    public void aggiungiDipendente(Dipendente d){
        boolean b = admin.aggiugniDipendente(this,d);
        if (!b) throw new IllegalArgumentException("Impossibile aggiungere un dipendente");
    }
    public void rimuoviDipendente(int ID){
        boolean b = admin.rimuoviDipendente(this,ID);
        if (!b) throw new IllegalArgumentException("Rimozione Impossibile");
    }

}
