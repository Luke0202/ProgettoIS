package is.dipendenti;

public interface AdministratorIF {
    boolean addEmployee(Role role,Employee emp);
    boolean removeEmployee(int ID);
    int giveID();
}
