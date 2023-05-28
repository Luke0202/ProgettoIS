package is.organigramma;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractOrganigramma implements OrganigrammaIF {
    private ArrayList<AreaOrganizationIF> areas = new ArrayList<>();

    protected String name,description;

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
    //SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public AreaOrganizationIF getChild(int i){
        if (i<0 || i>=areas.size()) return null;
        return areas.get(i);
    }
    public boolean containsArea(AreaOrganizationIF area){
        Iterator<AreaOrganizationIF> it = iterator();
        while(it.hasNext()){
            if (it.next().equals(area)) return true;
        }
        return false;
    }
    @Override
    public void addChild(AreaOrganizationIF area) {
        areas.add(area);
    }

    @Override
    public void removeChild(int i) {
        areas.remove(i);
    }

    @Override
    public int getNChildren() {
        return areas.size();
    }

    @Override
    public Iterator<AreaOrganizationIF> iterator() {
        return new AreaIterator();
    }
    private class AreaIterator implements Iterator<AreaOrganizationIF>{
        Iterator<AreaOrganizationIF> it = areas.iterator();
        private AreaOrganizationIF cur = null;
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public AreaOrganizationIF next() {
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
