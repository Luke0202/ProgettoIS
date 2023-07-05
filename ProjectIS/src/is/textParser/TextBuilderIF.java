package is.textParser;

/**
 * Tale interfaccia fornisce i metodi per la
 * memorizzazione in memoria secondaria dei dati di un'azienda.
 * @author lucab
 */
public interface TextBuilderIF {
    //Costruzione documento contenente i dati aziendali
    void openAzienda();
    void closeAzienda();

    void openCod(String cod);
    void closeCod();

    void openHeadquarter(String h);
    void closeHeadquarter();

    void openType(String type);
    void closeType();

    void openName(String name);
    void closeName();

    void openSurname(String surname);
    void closeSurname();

    void openEmail(String email);
    void closeEmail();

    void openPassword(String psw);
    void closePassword();

    void openEmployee();
    void closeEmployee();

    void openEmployees();
    void closeEmployees();

    void openRoles();
    void closeRoles();

    void openRole();
    void closeRole();

    void openState(boolean state);
    void closeState();

    void openArea();
    void closeArea();

    void openListAreas();
    void closeListAreas();

    void openNameArea(String nome);
    void closeNameArea();

    void openDescription(String content);
    void closeDescription();

    void openOrganigramma();
    void closeOrganigramma();

    void openCouples();
    void closeCouples();

    void openCouple();
    void closeCouple();

    void openID(int ID);
    void closeID();
}//TextBuilderIF
