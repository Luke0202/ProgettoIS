package is;

import is.Funzioni.Amministratore;
import is.Funzioni.Dipendente;

import java.util.LinkedList;

public interface AziendaIF {
    LinkedList<Dipendente> getDipendenti();
    int numeroDipendenti();
}
