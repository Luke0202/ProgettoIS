package is.organigramma;

import java.util.*;

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
    public boolean isSubArea(OrganigrammaIF area){
        if (this.equals(area)) return false;

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

    public void visit(List<OrganigrammaIF> ls){
        ls.add(this);
        for(OrganigrammaIF o:areas){
            ((Organigramma)o).visit(ls);
        }
    }

    @Override
    public boolean isChild(OrganigrammaIF o){
        for (OrganigrammaIF child:areas){
            if (child.equals(o)) return true;
        }
        return false;
    }
    @Override
    public Iterator<OrganigrammaIF> iterator() {
        return new AreaIterator();
    }
    private class AreaIterator implements Iterator<OrganigrammaIF>{
        private Iterator<OrganigrammaIF> it = null;
        private List<OrganigrammaIF> ls=new ArrayList<>();
        private OrganigrammaIF cur = null;

        public AreaIterator(){
            visit(ls);
            it = ls.iterator();
        }
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
            AbstractOrganigramma.this.removeChild(cur);
            cur = null;
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organigramma that = (Organigramma) o;
        return name == that.name;
    }

    @Override
    public int hashCode() {
        int M = 23;
        int ret = 0;
        for (int i:getEmployees()){
            ret+=i*M;
        }
        return ret;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(500);
        sb.append("<"+name+">\n");
        int N = getNChildren();
        for (int i = 0;i<N;i++){
            sb.append(getChild(i).toString()+"\n");
        }
        sb.append("</"+name+">");
        return sb.toString();
    }
}
