package is.builder;

public interface TextBuilderIF {
    //Costruzione documento contenente i dati dell'azienda
    void openAzienda(String ID);
    void closeAzienda();
    void openMaxEmployees(String max);
    void closeMaxEmployees();
    void openAdmin(String ID);
    void closeAdmin();
    void openName(String name);
    void closeName();
    void openSurname(String surname);
    void closeSurname();
    void openEmail(String email);
    void closeEmail();
    void openEmployee(String ID);
    void closeEmployee();
    void openEmployees();
    void closeEmployees();
    void openOrganigramma();
    void closeOrganigramma();
    void openArea(String nome);
    void closeArea();
    void openDescription(String content);
    void closeDescription();
}
