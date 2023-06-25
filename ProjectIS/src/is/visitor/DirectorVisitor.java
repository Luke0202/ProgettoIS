package is.visitor;

import is.builder.TextBuilderIF;
import is.organigramma.Role;
import is.organigramma.Couple;
import is.organigramma.Organigramma;

public class DirectorVisitor implements AreaVisitor{
    private TextBuilderIF builder;

    public DirectorVisitor(TextBuilderIF builder){this.builder=builder;}
    @Override
    public void visit(Organigramma organigramma){
        builder.openArea();
        builder.openName(organigramma.getName());
        builder.closeName();
        builder.openDescription(organigramma.getDescription());
        builder.closeDescription();
        builder.openState(organigramma.getStateArea());
        builder.closeState();
        //Couples Id employee-role
        builder.openCouples();
        for(Couple c:organigramma.getCouples()){
            builder.openCouple();
            builder.openID(c.getID());
            builder.closeID();
            Role r = c.getRole();
            builder.openRole();
            builder.openName(r.getName());
            builder.closeName();
            builder.openNameArea(r.getArea());
            builder.closeNameArea();
            builder.openDescription(r.getDescription());
            builder.closeDescription();
            builder.closeRole();
            builder.closeCouple();
        }
        builder.closeCouples();

        builder.openListAreas();
        for (int i = 0;i<organigramma.getNChildren();i++){
            organigramma.getChild(i).accept(this);
        }
        builder.closeListAreas();
        builder.closeArea();
    }
}
