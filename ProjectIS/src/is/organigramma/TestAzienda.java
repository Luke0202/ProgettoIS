package is.organigramma;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

/**
 * Tale classe ha la funzione di verificare le classi definite nel
 * package is.organigramma.
 * @author lucab
 */
@TestMethodOrder(OrderAnnotation.class)
public class TestAzienda {
    //Organigramma aziendale
    private Organigramma o;

    private Azienda azienda;

    @Test
    @BeforeEach
    public void primaDiTutto(){
        //Definizione aree
        o = new Organigramma("Azienda","");
        Organigramma o1 = new Organigramma("Consiglio di amministrazione","");
        Organigramma o2 = new Organigramma("Area vendite","");
        Organigramma o3 = new Organigramma("Custom care","");
        Organigramma o4 = new Organigramma("Marketing","");
        Organigramma o5 = new Organigramma("Acquisti","");
        Organigramma o6 = new Organigramma("Produzione","");

        //Tree Structure
        o.addChild(o1);
        o1.addChild(o2);
        o1.addChild(o5);
        o1.addChild(o6);
        o2.addChild(o3);
        o2.addChild(o4);

        //Definizione Azienda
        String psw ="1234";//Password aziendale
        azienda = new Azienda("010101", "Spacex","Hawthorne","Aerospaziale",psw,o);

        //Definizione ruoli
        Role r1 = new Role("direttore","Consiglio di amministrazione","");
        Role r2 = new Role("vice-direttore","Consiglio di amministrazione","");
        Role r3 = new Role("venditore","Area vendite","");
        Role r4 = new Role("supervisore","Custom care","");
        Role r5 = new Role("rappresentante","Custom care","");
        Role r6 = new Role("responsabile assistenza clienti","Custom care","");
        Role r7 = new Role("E-Commerce manager","Marketing","");
        Role r8 = new Role("venditore","Acquisti","");

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
        Employee emp1 = new Employee("Luca","Granata","",azienda.giveID());
        azienda.addEmployee(r1,emp1);
        Employee emp2 = new Employee("Mario","Tommasini","",azienda.giveID());
        azienda.addEmployee(r2,emp2);
        Employee emp3 = new Employee("Gianni","Filice","",azienda.giveID());
        azienda.addEmployee(r3,emp3); azienda.addEmployee(r8,emp3);
        Employee emp4 = new Employee("Armando","Basta","",azienda.giveID());
        azienda.addEmployee(r4,emp4);
        Employee emp5 = new Employee("Roberto","Cerchiara","",azienda.giveID());
        azienda.addEmployee(r5,emp5);
        Employee emp6 = new Employee("Fabio","Graceffa","",azienda.giveID());
        azienda.addEmployee(r6,emp6);
        Employee emp7 = new Employee("Marco","Quaglio","",azienda.giveID());
        azienda.addEmployee(r7,emp7);
        Employee emp8 = new Employee("Tommaso","Dodaro","",azienda.giveID());
        azienda.addEmployee(r8,emp8);
    }

    @Test
    @Order(1)
    @DisplayName("Verifica struttura organigramma")
    public void primoTest(){
        Area consiglioAmministrazione = o.getChild(0);
        Area areaVendite = ((Organigramma)consiglioAmministrazione).getChild(0);

        //Verifica presenza di sottoaree
        assertFalse(o.getSubAreas().isEmpty());

        //Verifica numero di sottoaree
        assertEquals(o.getSubAreas().size(),6);

        //Verifica numero aree figlie
        assertEquals(((Organigramma) consiglioAmministrazione).getNChildren(),3);
        assertEquals(((Organigramma) areaVendite).getNChildren(),2);
    }

    @Test
    @Order(2)
    @DisplayName("Verifica associazioni ruolo-dipendente per ciascun area")
    public void secondoTest(){
        //Getting areas
        Area consiglioAmministrazione = o.getChild(0);
        Area produzione = ((Organigramma)consiglioAmministrazione).getChild(2);
        Area acquisti = ((Organigramma)consiglioAmministrazione).getChild(1);
        Area areaVendite = ((Organigramma)consiglioAmministrazione).getChild(0);
        Area customCare = ((Organigramma)areaVendite).getChild(0);
        Area marketing = ((Organigramma)areaVendite).getChild(1);

        //Ruoli Consiglio di Amministrazione
        assertEquals(consiglioAmministrazione.getRoles().size(),2);

        //Ruoli Area Vendite
        assertEquals(areaVendite.getRoles().size(),1);

        //Ruoli Acquisti
        assertEquals(acquisti.getRoles().size(),1);

        //Ruoli Produzione
        assertTrue(produzione.getRoles().isEmpty());

        //Ruoli Custom Care
        assertEquals(customCare.getRoles().size(),3);

        //Ruoli Marketing
        assertEquals(marketing.getRoles().size(),1);
    }

    @Test
    @Order(3)
    @DisplayName("Verifica Dipendenti")
    public void terzoTest(){
        //Getting areas
        Area consiglioAmministrazione = o.getChild(0);
        Area produzione = ((Organigramma)consiglioAmministrazione).getChild(2);
        Area acquisti = ((Organigramma)consiglioAmministrazione).getChild(1);
        Area areaVendite = ((Organigramma)consiglioAmministrazione).getChild(0);
        Area customCare = ((Organigramma)areaVendite).getChild(0);
        Area marketing = ((Organigramma)areaVendite).getChild(1);

        //Verifica numero dipendenti nell'area "consiglio di amministrazione"
        assertEquals(consiglioAmministrazione.getNEmployees(),2);

        //Verifica presenza di almeno un dipendente nel consiglio di amministrazione avente il ruolo di vice-direttore
        assertFalse(consiglioAmministrazione.getEmployees(new Role("vice-direttore","Consiglio di amministrazione","")).isEmpty());

        //Verifica presenza di due dipendenti nell'area "acquisti" aventi il ruolo di venditore
        assertEquals(acquisti.getEmployees(new Role("venditore","Acquisti","")).size(),2);

        //Verifica presenza di tre dipendenti nell'area "custom care"
        assertEquals(customCare.getNEmployees(),3);

        //Verifica assenza di dipendenti nell'area "produzione"
        assertTrue(produzione.getEmployees().isEmpty());

        //Verifica assenza di dipendenti nell'area "marketing" con il ruolo di responsabile
        assertTrue(marketing.getEmployees(new Role("responsabile","Marketing","")).isEmpty());
    }

    @Test
    @Order(4)
    @DisplayName("Verifica dati azienda")
    public void quartoTest(){
        //Verifica numero di ruoli
        assertEquals(azienda.getNRoles(),8);

        //Verifica numero di dipendenti
        assertEquals(azienda.getNEmployees(),8);

        //Verifica numero di aree
        assertEquals(azienda.getNAreas(),7);

        //Verifica presenza area "consiglio di amministrazione"
        assertEquals(azienda.getArea("Consiglio di amministrazione"),o.getChild(0));

        //Verifica che "azienda" è l'area padre di "consiglio di amministrazione"
        Organigramma consiglioAmministrazione = (Organigramma) o.getChild(0);
        assertEquals(azienda.getParent(consiglioAmministrazione),o);

        //Verifica numero di aree in cui lavora il dipendente "Gianni Filice"
        assertEquals(azienda.getAreasName(new Employee("Gianni","Filice","",2)).size(),2);
    }
}
