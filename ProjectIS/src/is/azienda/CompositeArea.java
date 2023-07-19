package is.azienda;

import java.util.Iterator;

/**
 * Questa interfaccia estende l'interfaccia Area.
 * Dichiara metodi per la gestione della struttura
 * ad albero dell'organigramma.
 * Estende Iterable in modo da permettere l'iterazione
 * sulle aree.
 * @author lucab
 */
public interface CompositeArea extends Area,Iterable<Area>{
    //About tree structure
    void addChild(Area area);
    void removeChild(int i);
    void removeChild(Area area);
    Area getChild(int i);
    int getNChildren();
    boolean isSubArea(Area area);
    boolean isChild(Area area);

    //About iterator
    Iterator<Area> iterator();
}//CompositeArea
