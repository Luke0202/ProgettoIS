package is.builder;

import is.azienda.Azienda;
import is.azienda.Employee;
import is.azienda.Organigramma;
import is.azienda.Role;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Tale classe effettua un testing del Builder definito
 * nel package is.builder. Data un'azienda e il nome di un file.txt
 * si vuole memorizzare il contenuto di un'azienda all'interno
 * del file.
 * @author lucab
 */
public class TextBuilderTest {
    //Azienda da memorizzare
    private Azienda azienda;

    private TextBuilderIF builder;
    public TextBuilderTest(String path){
        //Definizione azienda

        //Definizione organigramma
        Organigramma o1 = new Organigramma("Consiglio di amministrazione"," ");
        Organigramma o2 = new Organigramma("Area vendite"," ");
        Organigramma o3 = new Organigramma("Custom care"," ");
        Organigramma o4 = new Organigramma("Marketing"," ");
        Organigramma o5 = new Organigramma("Acquisti"," ");
        Organigramma o6 = new Organigramma("Produzione"," ");

        //Tree Structure
        o1.addChild(o2);
        o1.addChild(o5);
        o1.addChild(o6);
        o2.addChild(o3);
        o2.addChild(o4);

        //Definizione Azienda
        String psw ="1234";//Password aziendale
        azienda = new Azienda("010101", "Spacex","Florida","Aerospaziale",psw,o1);

        //Definizione ruoli
        Role r1 = new Role("direttore","Consiglio di amministrazione"," ");
        Role r2 = new Role("vice-direttore","Consiglio di amministrazione"," ");
        Role r3 = new Role("venditore","Area vendite"," ");
        Role r4 = new Role("supervisore","Custom care"," ");
        Role r5 = new Role("rappresentante","Custom care"," ");
        Role r6 = new Role("responsabile assistenza clienti","Custom care"," ");
        Role r7 = new Role("E-Commerce manager","Marketing"," ");
        Role r8 = new Role("venditore","Acquisti"," ");

        //Aggiunta ruoli
        azienda.addRole(r1);
        azienda.addRole(r2);
        azienda.addRole(r3);
        azienda.addRole(r4);
        azienda.addRole(r5);
        azienda.addRole(r6);
        azienda.addRole(r7);
        azienda.addRole(r8);


        //Definizione dipendenti
        Employee emp1 = new Employee("Luca","Granata"," ",azienda.giveID());
        azienda.addEmployee(r1,emp1);
        Employee emp2 = new Employee("Mario","Tommasini"," ",azienda.giveID());
        azienda.addEmployee(r2,emp2);
        Employee emp3 = new Employee("Gianni","Filice"," ",azienda.giveID());
        azienda.addEmployee(r3,emp3); azienda.addEmployee(r8,emp3);
        Employee emp4 = new Employee("Armando","Basta"," ",azienda.giveID());
        azienda.addEmployee(r4,emp4);
        Employee emp5 = new Employee("Roberto","Cerchiara"," ",azienda.giveID());
        azienda.addEmployee(r5,emp5);
        Employee emp6 = new Employee("Fabio","Graceffa"," ",azienda.giveID());
        azienda.addEmployee(r6,emp6);
        Employee emp7 = new Employee("Marco","Quaglio"," ",azienda.giveID());
        azienda.addEmployee(r7,emp7);
        Employee emp8 = new Employee("Tommaso","Dodaro"," ",azienda.giveID());
        azienda.addEmployee(r8,emp8);


        File file = new File(path);
        //Verifica esistenza file
        if (!file.exists()){
            try{
                file.createNewFile();
            }
            catch(IOException e){
                e.printStackTrace();
                return;
            }
        }
        PrintWriter pw=null;
        try{
            pw = new PrintWriter(new FileWriter(file.getPath()));
        }catch(IOException e){
            e.printStackTrace();
            return;
        }
        //Definizione builder
        builder = new TextBuilder(pw);

        //Memorizzazione azienda
        memorizzaAzienda();
        pw.close();

        //Lettura contenuto
        try{
            BufferedReader br = new BufferedReader(new FileReader(file.getPath()));

            String line;

            while(true){
                line = br.readLine();

                //Verifica fine file
                if (line == null) break;

                System.out.println(line);
            }

            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Tale metodo permette, attraverso l'utilizzo del
     * TextBuilder, di memorizzare dati aziendali
     * all'interno di un file.txt.
     */
    private void memorizzaAzienda(){
        HashSet<Employee> employees = azienda.getEmployees();

        //Memorizzazione azienda
        builder.openAzienda();

        //Memorizzazione dipendenti
        builder.openEmployees();
        for (Employee emp:employees){
            builder.openEmployee();
            builder.openID(emp.getID()); builder.closeID(); //Id dipendente
            builder.openName(emp.getName()); builder.closeName(); //Nome dipendente
            builder.openSurname(emp.getSurname()); builder.closeSurname(); //Cognome dipendente
            builder.openEmail(emp.getEmail()); builder.closeEmail(); //Email dipendente
            builder.closeEmployee();
        }
        builder.closeEmployees();

        builder.closeAzienda();
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digita il percorso file: ");
        String path = sc.nextLine();
        sc.close();
        TextBuilderTest test = new TextBuilderTest(path);
    }
}//Test
