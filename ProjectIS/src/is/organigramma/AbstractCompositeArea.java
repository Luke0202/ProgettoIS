package is.organigramma;

import java.util.*;

public abstract class AbstractCompositeArea implements CompositeArea {
    private ArrayList<Area> areas = new ArrayList<>();

    protected String name,description;
    public boolean stateArea = false; //false: BOZZA  true:VALIDATA

    public AbstractCompositeArea(String name, String description){
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

    @Override
    public Area getChild(int i){
        if (i<0 || i>=areas.size()) return null;
        return areas.get(i);
    }
    @Override
    public boolean isSubArea(Area area){
        if (this.equals(area)) return false;

        Iterator<Area> it = iterator();
        while(it.hasNext()){
            if (it.next().equals(area)) return true;
        }
        return false;
    }
    @Override
    public void addChild(Area area) {
        areas.add(area);
    }

    @Override
    public void removeChild(int i) {
        areas.remove(i);
    }

    @Override
    public void removeChild(Area area) { areas.remove(area);}

    @Override
    public int getNChildren() {
        return areas.size();
    }
    private void preOrder(List<Area> ls){
        ls.add(this);
        for(Area a:areas){
            ((AbstractCompositeArea)a).preOrder(ls);
        }
    }

    @Override
    public boolean isChild(Area o){
        for (Area child:areas){
            if (child.equals(o)) return true;
        }
        return false;
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

    @Override
    public Iterator<Area> iterator() {
        return new AreaIterator();
    }
    private class AreaIterator implements Iterator<Area>{
        private Iterator<Area> it = null;
        private List<Area> ls=new ArrayList<>();
        private Area cur = null;

        public AreaIterator(){
            preOrder(ls);
            it = ls.iterator();
        }
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Area next() {
            cur = it.next();
            return cur;
        }

        @Override
        public void remove() {
            if (cur == null) throw new NoSuchElementException();
            it.remove();
            AbstractCompositeArea.this.removeChild(cur);
            cur = null;
        }
    }

}
