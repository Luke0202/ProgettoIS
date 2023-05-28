package is.builder;

import java.io.PrintWriter;

public class CredTextBuilder implements CredTextBuilderIF {
    private final PrintWriter pw;

    public CredTextBuilder(PrintWriter pw){
        this.pw = pw;
    }
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
    public void OpenPassword(String psw) {
        pw.println("<Password>"+psw+"</Password>");
    }

    @Override
    public void ClosePassword() {}
}
