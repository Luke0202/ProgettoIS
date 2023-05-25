package is.Funzioni;

import is.Azienda;

import java.util.LinkedList;
import java.util.Objects;

public class Dipendente {
    private int ID;
    private Azienda azienda;
    private LinkedList<String> listaRuoli = new LinkedList<>();
    private String nome,cognome,email;
    public Dipendente(String nome,String cognome,String email){
        this.nome = nome;
        this.cognome = cognome;
        this.email=email;
        this.ID = azienda.numeroDipendenti()+1;
        azienda.aggiungiDipendente(this);
    }

    public int getID() {
        return ID;
    }

    public LinkedList<String> getListaRuoli() {
        return listaRuoli;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    public void setListaRuoli(LinkedList<String> listaRuoli) {
        this.listaRuoli = listaRuoli;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dipendente that = (Dipendente) o;
        return ID == that.ID && azienda.getCodice()==that.azienda.getCodice();
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(400);
        sb.append("Dipendente: " + cognome + " " + nome + ". Id: " + ID + "\nRuoli: ");
        int N = listaRuoli.size();
        for (int i = 0; i < N; i++) {
            sb.append(listaRuoli.get(i).toString() + ", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append(".");
        return sb.toString();
    }
}
