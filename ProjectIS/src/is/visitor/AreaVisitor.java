package is.visitor;

import is.organigramma.Organigramma;

/**
 * Tale classe fornisce un metodo di interazione con un oggetto Composite.
 * @author lucab
 */
public interface AreaVisitor {
    void visit(Organigramma organigramma);
}//AreaVisitor
