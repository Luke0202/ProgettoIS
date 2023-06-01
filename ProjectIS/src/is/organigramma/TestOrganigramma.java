package is.organigramma;

import java.util.Iterator;
import java.util.LinkedList;

public class TestOrganigramma {
    public static void main(String[] args){
        Organigramma o = new Organigramma("Azienda","");
        Organigramma o1 = new Organigramma("Consiglio di amministrazione","");
        Organigramma o2 = new Organigramma("Area Vendite","");
        Organigramma o3 = new Organigramma("Custom Care","");
        Organigramma o4 = new Organigramma("Marketing","");
        Organigramma o5 = new Organigramma("Acquisti","");
        Organigramma o6 = new Organigramma("Produzione","");

        //Test Tree Structure
        o.addChild(o1);
        o1.addChild(o2);
        o1.addChild(o5);
        o1.addChild(o6);
        o2.addChild(o3);
        o2.addChild(o4);

        //System.out.println(o);

        //Test Iterator

        Iterator<OrganigrammaIF> it = o.iterator();

        while(it.hasNext()){
            System.out.println(((Organigramma)it.next()).getName()+"\n");
        }
    }
}
