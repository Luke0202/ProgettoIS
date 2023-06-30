package is.textParser;

import is.builder.TextBuilderIF;
import is.azienda.Role;
import is.azienda.Couple;
import is.azienda.Organigramma;

/**
 * Tale classe implementa AreaVisitor. Definisce un metodo di visita
 * di un organigramma. L'obiettivo è la memorizzazione di un organigramma
 * in memoria secondaria, mediante l'utilizzo di un TextBuilderIF.
 * @author lucab
 */
public class DirectorVisitor implements AreaVisitor{
    private TextBuilderIF builder;

    public DirectorVisitor(TextBuilderIF builder){
        this.builder=builder;
    }

    /**
     * Tale metodo effettua la visita di un organigramma,
     * memorizzandone il contenuto in memoria secondaria.
     * @param organigramma Organigramma da visitare
     */
    @Override
    public void visit(Organigramma organigramma){
        builder.openArea();
        builder.openName(organigramma.getName()); builder.closeName(); //Nome area
        builder.openDescription(organigramma.getDescription()); builder.closeDescription(); //Descrizione area
        builder.openState(organigramma.getStateArea()); builder.closeState(); //Stato area
        //Couples Id employee-role
        builder.openCouples();
        for(Couple c:organigramma.getCouples()){
            builder.openCouple();
            builder.openID(c.getID()); builder.closeID(); //Id dipendente
            Role r = c.getRole();
            builder.openRole();
            builder.openName(r.getName()); builder.closeName(); //Nome ruolo
            builder.openNameArea(r.getArea()); builder.closeNameArea(); //Area di competenza del ruolo
            builder.openDescription(r.getDescription()); builder.closeDescription(); //Descrizione del ruolo
            builder.closeRole();
            builder.closeCouple();
        }
        builder.closeCouples();

        builder.openListAreas();
        //Per ogni sotto-area viene invocato ricorsivamente tale metodo
        for (int i = 0;i<organigramma.getNChildren();i++){
            organigramma.getChild(i).accept(this);
        }
        builder.closeListAreas();
        builder.closeArea();
    }
}//DirectorVisitor
