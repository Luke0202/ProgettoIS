package is.aziendaParser;

import is.azienda.Azienda;
import is.azienda.Employee;

import java.net.URL;
import java.util.HashSet;

/**
 * Tale classe effettua un test del parser contenuto
 * nel package is.aziendaParser. A partire da un file.txt
 * contenente i dati di un'azienda, si vuole costruire un
 * oggetto di classe Azienda.
 * @author lucab
 */
public class AziendaParserTest {
    public static void main(String[] args){
        URL url = AziendaParserTest.class.getResource("contenuto.txt");

        //Parsing
        AziendaParser parser = new AziendaParser(url.getPath());
        Azienda azienda = parser.build();

        //Visione contenuto
        System.out.println(azienda);
        System.out.println();
        HashSet<Employee> employees = azienda.getEmployees();
        for (Employee e:employees){
            System.out.println(e);
        }
    }
}//AziendaParserTest
