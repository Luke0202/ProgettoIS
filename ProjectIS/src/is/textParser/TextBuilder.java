package is.textParser;

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
     */
    @Override
    public void openAzienda() {
        pw.println("<Azienda>");
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
     * Definisce i tag di apertura e chiusura del codice ATECO.
     * @param cod codice ATECO
     */
    @Override
    public void openCod(String cod) {
        pw.println("<Cod>"+cod+"</Cod>");
    }

    /**
     * @deprecated
     */
    @Override
    public void closeCod() {}

    /**
     * Definisce i tag di apertura e chiusura della sede centrale.
     * @param h sede centrale dell'azienda
     */
    @Override
    public void openHeadquarter(String h) {
        pw.println("<Headquarter>"+h+"</Headquarter>");
    }

    /**
     * @deprecated
     */
    @Override
    public void closeHeadquarter() {}

    /**
     * Definisce i tag di apertura e chiusura del settore.
     * @param type settore aziendale
     */
    @Override
    public void openType(String type) {
        pw.println("<Type>"+type+"</Type>");
    }

    /**
     * @deprecated
     */
    @Override
    public void closeType() {}

    /**
     * Definisce i tag di apertura e chiusura del nome.
     * @param name
     */
    @Override
    public void openName(String name) {
        pw.println("<Name>"+name+"</Name>");
    }

    /**
     * @deprecated
     */
    @Override
    public void closeName() {}

    /**
     * Definisce i tag di apertura e chiusura del cognome.
     * @param surname cognome dipendente
     */
    @Override
    public void openSurname(String surname) {
        pw.println("<Surname>"+surname+"</Surname>");
    }

    /**
     * @deprecated
     */
    @Override
    public void closeSurname() {}

    /**
     * Definisce i tag di apertura e chiusura della email.
     * @param email email dipendente
     */
    @Override
    public void openEmail(String email) {
        pw.println("<Email>"+email+"</Email>");
    }

    /**
     * @deprecated
     */
    @Override
    public void closeEmail() {}

    /**
     * Definisce i tag di apertura e chiusura della password aziendale.
     * @param psw password aziendale
     */
    @Override
    public void openPassword(String psw) {
        pw.println("<Password>"+psw+"</Password>");
    }

    /**
     * @deprecated
     */
    @Override
    public void closePassword() {}

    /**
     * Definisce il tag di apertura di un dipendente.
     */
    @Override
    public void openEmployee() {
        pw.println("<Employee>");
    }

    /**
     * Definisce il tag di chiusura di un dipendente.
     */
    @Override
    public void closeEmployee() {
        pw.println("</Employee>");
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
     * Definisce il tag di apertura di un ruolo.
     */
    @Override
    public void openRole() {
        pw.println("<Role>");
    }

    /**
     * Definisce il tag di chiusura di un ruolo.
     */
    @Override
    public void closeRole() {
        pw.println("</Role>");
    }

    /**
     * Definisce i tag di apertura e chiusura dello stato di un'area.
     * @param state stato area
     */
    @Override
    public void openState(boolean state) {
        pw.println("<State>"+state+"</State>");
    }

    /**
     * @deprecated
     */
    @Override
    public void closeState() {}

    /**
     * Definisce il tag di apertura di un'area.
     */
    @Override
    public void openArea() {
        pw.println("<Area>");
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
    public void openOrganigramma() {
        pw.println("<Organigramma>");
    }

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
    public void openCouples() {
        pw.println("<Couples>");
    }

    /**
     * Definisce il tag di chiusura delle coppie Ruolo-IdDipendente.
     */
    @Override
    public void closeCouples() {
        pw.println("</Couples>");
    }

    /**
     * Definisce il tag di apertura della coppia Ruolo-IdDipendente.
     */
    @Override
    public void openCouple() {
        pw.println("<Couple>");
    }

    /**
     * Definisce il tag di chiusura della coppia Ruolo-IdDipendente.
     */
    @Override
    public void closeCouple() {
        pw.println("</Couple>");
    }

    /**
     * Definisce i tag di apertura e chiusura di un ID.
     * @param id
     */
    @Override
    public void openID(int id) {
        pw.println("<ID>"+id+"</ID>");
    }

    /**
     * @deprecated
     */
    @Override
    public void closeID() {}

    /**
     * Definisce i tag di apertura e chiusura del nome di un'area.
     * @param nome
     */
    @Override
    public void openNameArea(String nome) {
        pw.println("<NameArea>"+nome+"</NameArea>");
    }

    /**
     * @deprecated
     */
    @Override
    public void closeNameArea() {}

    /**
     * Definisce i tag di apertura e chiusura di una descrizione.
     * @param content descrizione
     */
    @Override
    public void openDescription(String content) {
        pw.println("<Description>"+content+"</Description>");
    }

    /**
     * @deprecated
     */
    @Override
    public void closeDescription() {}
}//TextBuilder
