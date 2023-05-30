package is.parser;

import is.builder.TextBuilderIF;
import is.dipendenti.Administrator;
import is.dipendenti.Employee;
import is.dipendenti.Role;
import is.organigramma.Azienda;
import is.organigramma.Couple;
import is.organigramma.Organigramma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class TextParser {
    //Analizza un file.txt e lo converte in un Azienda
    //Lettura di un file.txt e conversione in un Aziendaa
    private final String urlString;
    private final TextBuilderIF builder;
    private BufferedReader br;
    private String line,token;
    private StringTokenizer st;
    private HashSet<Couple> couples = new HashSet<>();
    public TextParser(TextBuilderIF builder, String urlString){
        this.builder=builder;
        this.urlString=urlString;
    }
    public Azienda build() {
        Azienda az=null;
        try {
            az=doParse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return az;
    }
    private Azienda doParse() {
        URL url;
        try {
            url = new URI(urlString).toURL();
            br = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (IOException e) {
            throw new IllegalArgumentException("Errore di lettura");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return readAzienda();
    }
    private String nextToken() {
        while (st == null || !st.hasMoreTokens()) {

            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (line == null)
                return null;//Fine file

            st = new StringTokenizer(line,"[><]");
        }

        return st.nextToken();//<....>

    }
    private Azienda readAzienda(){
        token = nextToken();//<Azienda>

        if (token == null)
            throw new IllegalArgumentException("Atteso token");
        if (!token.equals("Azienda"))
            throw new IllegalArgumentException(" Atteso: Azienda trovato:" + token);

        token = nextToken();//<ID>

        if (token == null)
            throw new IllegalArgumentException("Atteso token");
        if (!token.equals("ID"))
            throw new IllegalArgumentException(" Atteso: ID trovato:" + token);

        int IDAzienda = Integer.parseInt(readText());

        token = nextToken();//<Name>

        if (token == null)
            throw new IllegalArgumentException("Atteso token");
        if (!token.equals("Name"))
            throw new IllegalArgumentException(" Atteso: Name trovato:" + token);

        String nameAzienda = readText();

        LinkedList<Employee> employees = readEmployees();
        Organigramma org = readOrganigramma();
        Administrator ad = readAdmin(org);
        Azienda az = new Azienda(IDAzienda,nameAzienda,null,null,null,ad);

        //Aggiunta coppie ID emp-Role
        for (Employee emp:employees){
            int id = emp.getID();
            for (Couple c:couples){
                if (c.getID()==id){
                    az.addEmployee(c.getRole(),emp);
                }
            }
        }
        return az;
    }

    private Administrator readAdmin(Organigramma org){
        token = nextToken();//<Admin>

        if (token == null)
            throw new IllegalArgumentException("Atteso token");
        if (!token.equals("Admin"))
            throw new IllegalArgumentException(" Atteso: Admin trovato:" + token);

        token = nextToken();//<ID>

        if (token == null)
            throw new IllegalArgumentException("Atteso token");
        if (!token.equals("ID"))
            throw new IllegalArgumentException(" Atteso: ID trovato:" + token);

        int IDAdmin = Integer.parseInt(readText());

        token = nextToken();//<Name>

        if (token == null)
            throw new IllegalArgumentException("Atteso token");
        if (!token.equals("Name"))
            throw new IllegalArgumentException(" Atteso: Name trovato:" + token);

        String nameAdmin = readText();

        token = nextToken();//<Surname>

        if (token == null)
            throw new IllegalArgumentException("Atteso token");
        if (!token.equals("Surname"))
            throw new IllegalArgumentException(" Atteso: Surname trovato:" + token);

        String surnameAdmin = readText();

        token = nextToken();//<Email>

        if (token == null)
            throw new IllegalArgumentException("Atteso token");
        if (!token.equals("Email"))
            throw new IllegalArgumentException(" Atteso: Email trovato:" + token);

        String emailAdmin = readText();

        token = nextToken();//<Password>

        if (token == null)
            throw new IllegalArgumentException("Atteso token");
        if (!token.equals("Password"))
            throw new IllegalArgumentException(" Atteso: Password trovato:" + token);

        int pswAdmin = Integer.parseInt(readText());

        token = nextToken();//<MaxEmployees>

        if (token == null)
            throw new IllegalArgumentException("Atteso token");
        if (!token.equals("MaxEmployees"))
            throw new IllegalArgumentException(" Atteso: MaxEmployees trovato:" + token);

        int maxEmployeesAdmin = Integer.parseInt(readText());

        return new Administrator(maxEmployeesAdmin,org);
    }

    private Organigramma readOrganigramma(){
        token = nextToken();//<Organigramma>

        token = nextToken();
        if (token.equals("/Organigramma")) return null;
        else if (token.equals("Area")) return readArea();

        throw new IllegalArgumentException(" Atteso: Area trovato:" + token);
    }

    private Organigramma readArea(){

        token = nextToken();//<Name>

        if (token == null)
            throw new IllegalArgumentException("Atteso token");
        if (!token.equals("Name"))
            throw new IllegalArgumentException(" Atteso: Name trovato:" + token);

        String nameArea = readText();

        token = nextToken();//<Description>

        if (token == null)
            throw new IllegalArgumentException("Atteso token");
        if (!token.equals("Description"))
            throw new IllegalArgumentException(" Atteso: Description trovato:" + token);
        boolean go = true;

        String descrArea = readText();

        token = nextToken();//<Couples>

        if (token == null)
            throw new IllegalArgumentException("Atteso token");
        if (!token.equals("Couples"))
            throw new IllegalArgumentException(" Atteso: Couples trovato:" + token);

        Organigramma org = new Organigramma(nameArea,descrArea);

        Couple cou = null;
        int coupleID = 0;
        Role r = null;
        String nameCouple = null;
        String areaCouple = null;
        String descrCouple = null;
        go = true;
        while(go){
            token = nextToken();
            switch(token){
                case "ID": coupleID = Integer.parseInt(readText()); break;
                case "Name": nameCouple = readText(); break;
                case "NameArea": areaCouple = readText(); break;
                case "Description": descrCouple = readText(); break;
                case "/Role": r = new Role(nameCouple,areaCouple,descrCouple); break;
                case "/Couple": cou = new Couple(r,coupleID); couples.add(cou); break;
                case "/Couples": go = false;
                default: break;
            }
        }

        token = nextToken();//listAreas

        if (token == null)
            throw new IllegalArgumentException("Atteso token");
        if (!token.equals("ListAreas"))
            throw new IllegalArgumentException(" Atteso: ListAreas trovato:" + token);


        LinkedList<Organigramma> listAreas = new LinkedList<>();
        Organigramma area = null;
        go = true;
        while (go){
            token = nextToken();
            switch(token){
                case "Area": area = readArea(); listAreas.add(area); break;
                case "/ListAreas": go = false;
            }
        }
        nextToken();//</Area>
        for (int i=0;i<listAreas.size();i++){
            org.addChild(listAreas.get(i));
        }
        return org;
    }
    private LinkedList<Employee> readEmployees(){
        LinkedList<Employee> listEmp = new LinkedList<>();

        token = nextToken();//<Employees>

        if (token == null)
            throw new IllegalArgumentException("Atteso token");
        if (!token.equals("Employees"))
            throw new IllegalArgumentException(" Atteso: Employees trovato:" + token);

        int ID=0;
        int psw=0;
        String name=null;
        String surname=null;
        String email=null;
        boolean go = true;
        while(go){
            token = nextToken();
            switch(token){
                case "Employee": break;
                case "ID":ID = Integer.parseInt(nextToken()); break;
                case "Name": name = nextToken(); break;
                case "Surname": surname = nextToken(); break;
                case "Email": email = nextToken(); break;
                case "Password": psw = Integer.parseInt(nextToken()); break;
                case "/Employee":
                    Employee emp = new Employee(name,surname,email,ID,psw); listEmp.add(emp); break;
                case "/Employees": go = false;
            }
        }
        return listEmp;
    }
    private String readText(){
        token = nextToken();//content
        nextToken();//<...>
        if (token == null) throw new IllegalArgumentException("Atteso token");
        else return token;
    }
}
