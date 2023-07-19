package is.textParser;

import is.azienda.*;

/**
 * Tale interfaccia fornisce i metodi per la
 * memorizzazione in memoria secondaria dei dati di un'azienda.
 * @author lucab
 */
public interface TextBuilderIF {
    //Costruzione documento contenente i dati aziendali
    void openAzienda(Azienda azienda);
    void closeAzienda();

    void openEmployees();
    void closeEmployees();

    void addEmployee(Employee employee);

    void openRoles();
    void closeRoles();

    void addRole(Role role);

    void openArea(Organigramma organigramma);
    void closeArea();

    void openListAreas();
    void closeListAreas();

    void openOrganigramma();
    void closeOrganigramma();

    void openCouples();
    void closeCouples();

    void openCouple(Couple couple);
    void closeCouple();
}//TextBuilderIF
