package is.textParser;

import is.azienda.Organigramma;

/**
 * Tale classe fornisce un metodo di interazione con un oggetto Composite.
 * @author lucab
 */
public interface AreaVisitor {
    void visit(Organigramma organigramma);
}//AreaVisitor
