package is.textParser;

import is.azienda.*;
import java.io.PrintWriter;

/**
 * Tale classe implementa TextBuilderIF, definendo i metodi
 * per la rappresentazione dei dati aziendali secondo il formato XML.
 * @author lucab
 */
public class TextBuilder implements TextBuilderIF {

    private final PrintWriter pw;

    public TextBuilder(PrintWriter pw){
        this.pw = pw;
    }

    /**
     * Definisce il tag di apertura dell'azienda.
     * Vengono inoltre scritte sul file le informazioni
     * generali dell'azienda.
     */
    @Override
    public void openAzienda(Azienda azienda) {
        pw.println("<Azienda>");
        pw.println("<Cod>"+azienda.getCod()+"</Cod>");
        pw.println("<Name>"+azienda.getName()+"</Name>");
        pw.println("<Headquarter>"+azienda.getHeadquarter()+"</Headquarter>");
        pw.println("<Type>"+azienda.getType()+"</Type>");
        pw.println("<Password>"+azienda.getPsw()+"</Password>");
    }

    /**
     * Definisce il tag di chiusura dell'azienda.
     */
    @Override
    public void closeAzienda() {
        pw.println("</Azienda>");
        pw.flush();
        pw.close();
    }

    /**
     * Definisce il tag di apertura dei dipendenti.
     */
    @Override
    public void openEmployees() {
        pw.println("<Employees>");
    }

    /**
     * Definisce il tag di chiusura dei dipendenti.
     */
    @Override
    public void closeEmployees() {
        pw.println("</Employees>");
    }

    /**
     * Permette la scrittura su un file dei
     * dati di un dipendente.
     */
    @Override
    public void addEmployee(Employee employee) {
        pw.println("<Employee>");
        pw.println("<ID>"+employee.getID()+"</ID>");
        pw.println("<Name>"+employee.getName()+"</Name>");
        pw.println("<Surname>"+employee.getSurname()+"</Surname>");
        pw.println("<Email>"+employee.getEmail()+"</Email>");
        pw.println("</Employee>");
    }

    /**
     * Definisce il tag di apertura dei ruoli.
     */
    @Override
    public void openRoles() {
        pw.println("<Roles>");
    }

    /**
     * Definisce il tag di chiusura dei ruoli.
     */
    @Override
    public void closeRoles() {
        pw.println("</Roles>");
    }

    /**
     * Permette la scrittura su un file dei
     * dati di un ruolo.
     */
    @Override
    public void addRole(Role role) {
        pw.println("<Role>");
        pw.println("<Name>"+role.getName()+"</Name>");
        pw.println("<NameArea>"+role.getArea()+"</NameArea>");
        pw.println("<Description>"+role.getDescription()+"</Description>");
        pw.println("</Role>");
    }

    /**
     * Definisce il tag di apertura di un'area.
     * Vengono inoltre scritte sul file le informazioni
     * generali di un'area organizzativa.
     */
    @Override
    public void openArea(Organigramma organigramma) {
        pw.println("<Area>");
        pw.println("<Name>"+organigramma.getName()+"</Name>");
        pw.println("<Description>"+organigramma.getDescription()+"</Description>");
        pw.println("<State>"+organigramma.getStateArea()+"</State>");
    }

    /**
     * Definisce il tag di chiusura di un'area.
     */
    @Override
    public void closeArea() {
        pw.println("</Area>");
    }

    /**
     * Definisce il tag di apertura della lista delle aree.
     */
    @Override
    public void openListAreas() {
        pw.println("<ListAreas>");
    }

    /**
     * Definisce il tag di chiusura della lista delle aree.
     */
    @Override
    public void closeListAreas() {
        pw.println("</ListAreas>");
    }

    /**
     * Definisce il tag di apertura dell'organigramma.
     */
    @Override
    public void openOrganigramma() { pw.println("<Organigramma>");}

    /**
     * Definisce il tag di chiusura dell'organigramma.
     */
    @Override
    public void closeOrganigramma() {
        pw.println("</Organigramma>");
    }

    /**
     * Definisce il tag di apertura delle coppie Ruolo-IdDipendente.
     */
    @Override
    public void openCouples() {pw.println("<Couples>");}

    /**
     * Definisce il tag di chiusura delle coppie Ruolo-IdDipendente.
     */
    @Override
    public void closeCouples() {
        pw.println("</Couples>");
    }

    /**
     * Vengono scritti sul file il tag di apertura della coppia
     * Ruolo-IdDipendente e l'id del dipendente.
     */
    @Override
    public void openCouple(Couple couple) {
        pw.println("<Couple>");
        pw.println("<ID>"+couple.getID()+"</ID>");
    }

    /**
     * Definisce il tag di chiusura della coppia Ruolo-IdDipendente.
     */
    @Override
    public void closeCouple() {
        pw.println("</Couple>");
    }

}//TextBuilder
