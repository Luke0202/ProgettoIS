package is.parser;

import is.organigramma.Employee;
import is.organigramma.Role;
import is.organigramma.Azienda;
import is.organigramma.Couple;
import is.organigramma.Organigramma;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class AziendaParser {
    //Analizza un file.txt e lo converte in un Azienda
    //Lettura di un file.txt e conversione in un Aziendaa
    private final String path;
    private BufferedReader br;
    private String token;
    private StringTokenizer st;
    private HashSet<Couple> couples = new HashSet<>();
    public AziendaParser(String path){
        this.path=path;
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
            br = new BufferedReader(new FileReader(path));
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

    private boolean isCorrect(String expected){
        return token != null && token.equals(expected);
    }
    private Azienda readAzienda(){
        token = nextToken();//<Azienda>

        if (!isCorrect("Azienda")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        token = nextToken();//<Cod>

        if (!isCorrect("Cod")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        String codAzienda = readText();

        token = nextToken();//<Name>

        if (!isCorrect("Name")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        String nameAzienda = readText();

        token = nextToken();//<Headquarter>

        if (!isCorrect("Headquarter")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        String hqAzienda = readText();

        token = nextToken();//<Type>

        if (!isCorrect("Type")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        String typeAzienda = readText();

        token = nextToken();//<Password>

        if (!isCorrect("Password")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        String psw = readText();

        LinkedList<Employee> employees = readEmployees();
        HashSet<Role> roles = readRoles();
        Organigramma org = readOrganigramma();

        Azienda az = new Azienda(codAzienda,nameAzienda,hqAzienda,typeAzienda,psw,org);

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

        if (!isCorrect("Roles")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        String name=null;
        String area=null;
        String descr = null;
        boolean go = true;
        while(go){
            token = nextToken();
            switch(token){
                case "Role": break;
                case "Name": name = readText(); break;
                case "NameArea": area = readText(); break;
                case "Description": descr = readText(); break;
                case "/Role": Role r = new Role(name,area,descr); roles.add(r); break;
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

        throw new IllegalArgumentException("Wrong token. Token found: " + token);
    }

    private Organigramma readArea(){

        token = nextToken();//<Name>

        if (!isCorrect("Name")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        String nameArea = readText();

        token = nextToken();//<Description>

        if (!isCorrect("Description")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        String descrArea = readText();

        token = nextToken();//<State>

        if (!isCorrect("State")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        boolean state;
        String s = readText();
        if (s.equals("true")) state = true;
        else state = false;

        token = nextToken();//<Couples>

        if (!isCorrect("Couples")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

        Organigramma org = new Organigramma(nameArea,descrArea); org.setStateArea(state);

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

        token = nextToken();//<listAreas>

        if (!isCorrect("ListAreas")) throw new IllegalArgumentException("Wrong token. Token found: "+token);


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

        if (!isCorrect("Employees")) throw new IllegalArgumentException("Wrong token. Token found: "+token);

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
