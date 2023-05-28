package is.builder;

import java.io.PrintWriter;

public class TextBuilder implements TextBuilderIF {
    private final PrintWriter pw;

    public TextBuilder(PrintWriter pw){
        this.pw = pw;
    }

    @Override
    public void openAzienda(String ID) {
        pw.println("<Azienda ID="+ID+">");
    }

    @Override
    public void closeAzienda() {
        pw.println("</Azienda>");
        pw.flush();
        pw.close();
    }

    @Override
    public void openMaxEmployees(String max) {
        pw.println("<MaxEmployees>"+max+"</MaxEmployees>");
    }

    @Override
    public void closeMaxEmployees() {}

    @Override
    public void openAdmin(String ID) {
        pw.println("<Admin ID="+ID+">");
    }
    @Override
    public void closeAdmin() {
        pw.println("</Admin>");
    }

    @Override
    public void openName(String name) {
        pw.println("<Name>"+name+"</Name>");
    }

    @Override
    public void closeName() {}

    @Override
    public void openSurname(String surname) {
        pw.println("<Surname>"+surname+"</Surname>");
    }

    @Override
    public void closeSurname() {}

    @Override
    public void openEmail(String email) {
        pw.println("<Email>"+email+"</Email>");
    }

    @Override
    public void closeEmail() {}

    @Override
    public void openEmployee(String ID) {
        pw.println("<Employee ID="+ID+">");
    }

    @Override
    public void closeEmployee() {
        pw.println("</Employee>");
    }

    @Override
    public void openEmployees() {
        pw.println("<Employees>");
    }

    @Override
    public void closeEmployees() {
        pw.println("</Employees>");
    }

    @Override
    public void openOrganigramma() {
        pw.println("<Organigramma>");
    }

    @Override
    public void closeOrganigramma() {
        pw.println("</Organigramma>");
    }

    @Override
    public void openArea(String name) {
        pw.println("<Area>");
    }

    @Override
    public void closeArea() {
        pw.println("</Area>");
    }

    @Override
    public void openDescription(String content) {
        pw.println("<Description>"+content+"</Description>");
    }

    @Override
    public void closeDescription() {}
}
