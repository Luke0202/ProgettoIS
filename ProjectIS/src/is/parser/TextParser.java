package is.parser;

import is.builder.TextBuilderIF;
import is.dipendenti.Administrator;
import is.dipendenti.Employee;
import is.dipendenti.Role;
import is.organigramma.Azienda;
import is.organigramma.Couple;
import is.organigramma.Organigramma;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    private BufferedReader br;
    private String token;
    private StringTokenizer st;
    private HashSet<Couple> couples = new HashSet<>();
    public TextParser(String urlString){
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
        try {
            br = new BufferedReader(new FileReader(urlString));
        } catch (IOException e) {
            throw new IllegalArgumentException("Errore di lettura");
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

        token = nextToken();//<Headquarter>

        if (token == null)
            throw new IllegalArgumentException("Atteso token");
        if (!token.equals("Headquarter"))
            throw new IllegalArgumentException(" Atteso: Headquarter trovato:" + token);

        String hqAzienda = readText();

        token = nextToken();//<Type>

        if (token == null)
            throw new IllegalArgumentException("Atteso token");
        if (!token.equals("Type"))
            throw new IllegalArgumentException(" Atteso: Type trovato:" + token);

        String typeAzienda = readText();

        token = nextToken();//<Password>

        if (token == null)
            throw new IllegalArgumentException("Atteso token");
        if (!token.equals("Password"))
            throw new IllegalArgumentException(" Atteso: Password trovato:" + token);

        String psw = readText();


        LinkedList<Employee> employees = readEmployees();
        HashSet<Role> roles = readRoles();
        Organigramma org = readOrganigramma();
        Administrator ad = new Administrator(org);
        Azienda az = new Azienda(IDAzienda,nameAzienda,hqAzienda,typeAzienda,psw,ad);



        for (Role r:roles){
            az.addRole(r);
        }
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

    private HashSet<Role> readRoles(){
        HashSet<Role> roles = new HashSet<>();
        token = nextToken();//<Roles>

        if (token == null)
            throw new IllegalArgumentException("Atteso token");
        if (!token.equals("Roles"))
            throw new IllegalArgumentException(" Atteso: Roles trovato:" + token);


        String name=null;
        String area=null;
        String descr = null;
        boolean state=false;
        boolean go = true;
        while(go){
            token = nextToken();
            switch(token){
                case "Role": break;
                case "Name": name = readText(); break;
                case "Area": area = readText(); break;
                case "Description": descr = readText(); break;
                case "State": state = Boolean.getBoolean(readText());
                case "/Role":
                    Role r = new Role(name,area,descr); r.setStateRole(state); roles.add(r); break;
                case "/Roles": go = false;
            }
        }
        return roles;
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

        String descrArea = readText();

        token = nextToken();//<State>

        if (token == null)
            throw new IllegalArgumentException("Atteso token");
        if (!token.equals("State"))
            throw new IllegalArgumentException(" Atteso: State trovato:" + token);

        boolean state = Boolean.getBoolean(readText());

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
        boolean go = true;
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
                case "Area": area = readArea(); area.setStateArea(state); listAreas.add(area); break;
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
        String name=null;
        String surname=null;
        String email=null;
        boolean go = true;
        while(go){
            token = nextToken();
            switch(token){
                case "Employee": break;
                case "ID":ID = Integer.parseInt(readText()); break;
                case "Name": name = readText(); break;
                case "Surname": surname = readText(); break;
                case "Email": email = readText(); break;
                case "/Employee":
                    Employee emp = new Employee(name,surname,email,ID); listEmp.add(emp); break;
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