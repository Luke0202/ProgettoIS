package is.organigramma;

import is.visitor.AreaVisitor;

import java.util.Iterator;

public interface CompositeArea extends Area,Iterable<Area>{
    void addChild(Area area);
    void removeChild(int i);
    void removeChild(Area area);
    Area getChild(int i);
    int getNChildren();
    boolean isSubArea(Area area);
    boolean isChild(Area o);
    Iterator<Area> iterator();

}
