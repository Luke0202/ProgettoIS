package is.Funzioni;

import is.Azienda;

public interface AmministratoreIF {
    boolean aggiugniDipendente(Azienda a,Dipendente d);
    boolean rimuoviDipendente(Azienda a,int ID);

}
