package is.organigramma;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractOrganigramma implements OrganigrammaIF {
    private ArrayList<OrganigrammaIF> areas = new ArrayList<>();

    protected String name,description;
    public boolean stateArea = false; //false: BOZZA  true:VALIDATA

    public AbstractOrganigramma(String name,String description){
        this.name = name; this.description=description;
    }
    //GETTERS
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public boolean getStateArea() {return stateArea;}

    //SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStateArea(boolean stateArea) {this.stateArea = stateArea;}

    public OrganigrammaIF getChild(int i){
        if (i<0 || i>=areas.size()) return null;
        return areas.get(i);
    }
    public boolean containsArea(OrganigrammaIF area){
        Iterator<OrganigrammaIF> it = iterator();
        while(it.hasNext()){
            if (it.next().equals(area)) return true;
        }
        return false;
    }
    @Override
    public void addChild(OrganigrammaIF area) {
        areas.add(area);
    }

    @Override
    public void removeChild(int i) {
        areas.remove(i);
    }

    @Override
    public void removeChild(OrganigrammaIF org) { areas.remove(org);}

    @Override
    public int getNChildren() {
        return areas.size();
    }

    @Override
    public Iterator<OrganigrammaIF> iterator() {
        return new AreaIterator();
    }
    private class AreaIterator implements Iterator<OrganigrammaIF>{
        Iterator<OrganigrammaIF> it = areas.iterator();
        private OrganigrammaIF cur = null;
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public OrganigrammaIF next() {
            cur = it.next();
            return cur;
        }

        @Override
        public void remove() {
            if (cur == null) throw new NoSuchElementException();
            it.remove();
            cur = null;
        }
    }
}
