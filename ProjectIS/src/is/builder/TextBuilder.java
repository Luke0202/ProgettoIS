package is.builder;

import java.io.PrintWriter;

public class TextBuilder implements TextBuilderIF {
    private final PrintWriter pw;

    public TextBuilder(PrintWriter pw){
        this.pw = pw;
    }

    @Override
    public void openAzienda() {
        pw.println("<Azienda>");
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
    public void openAdmin() {
        pw.println("<Admin>");
    }
    @Override
    public void closeAdmin() {
        pw.println("</Admin>");
    }

    @Override
    public void openHeadquarter(String h) {pw.println("<Headquarter>"+h+"</Headquarter");}

    @Override
    public void closeHeadquarter() {}

    @Override
    public void openType(String type) {pw.println("Type"+type+"/Type");}

    @Override
    public void closeType() {}

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
    public void openPassword(int psw) {pw.println("<Password>"+psw+"</Password>");}

    @Override
    public void closePassword() {}

    @Override
    public void openEmployee() {
        pw.println("<Employee>");
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
    public void openRoles() {pw.println("<Roles>");}

    @Override
    public void closeRoles() {pw.println("</Roles>");}

    @Override
    public void openRole() {pw.println("<Role>");}

    @Override
    public void closeRole() {pw.println("</Role>");}

    @Override
    public void openArea() {pw.println("<Area>");}

    @Override
    public void closeArea() {pw.println("</Area>");}

    @Override
    public void openListAreas() {pw.println("<ListAreas>");}

    @Override
    public void closeListAreas() {pw.println("</ListAreas>");}

    @Override
    public void openOrganigramma() {pw.println("<Organigramma>");}

    @Override
    public void closeOrganigramma() {pw.println("</Organigramma>");}

    @Override
    public void openCouples() {pw.println("<Couples>");}

    @Override
    public void closeCouples() {pw.println("</Couples>");}

    @Override
    public void openCouple() {pw.println("<Couple>");}

    @Override
    public void closeCouple() {pw.println("</Couple>");}

    @Override
    public void openID(int ID) {pw.println("<ID>"+ID+"</ID>");}

    @Override
    public void closeID() {}

    @Override
    public void openNameArea(String nome) {pw.println("<NameArea>"+nome+"</NameArea>");}

    @Override
    public void closeNameArea() {}

    @Override
    public void openDescription(String content) {pw.println("<Description>"+content+"</Description>");}

    @Override
    public void closeDescription() {}
}
