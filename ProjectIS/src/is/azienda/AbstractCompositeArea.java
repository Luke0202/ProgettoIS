package is.azienda;

import java.util.*;

/**
 * Tale classe implementa l'interfaccia CompositeArea e definisce i
 * metodi di gestione della struttura ad albero dell'organigramma.
 * La variabile booleana stateArea indica se l'area può essere
 * soggetta o meno a modifica. Se è true allora l'area risulta validata,
 * altrimenti è in bozza.
 * @author lucab
 */
public abstract class AbstractCompositeArea implements CompositeArea {

    //Lista delle sotto-aree
    private ArrayList<Area> areas = new ArrayList<>();

    //Stato dell'area: BOZZA o VALIDATA
    private boolean stateArea = false;

    //GETTERS
    public boolean getStateArea() {
        return stateArea;
    }

    //SETTERS
    public void setStateArea(boolean stateArea) {
        this.stateArea = stateArea;
    }

    /**
     * Permette di nominare una specifica area come
     * area figlia.
     * @param area area da aggiungere.
     */
    @Override
    public void addChild(Area area) {
        areas.add(area);
    }

    /**
     * Permette di rimuovere un'area specificandone la posizione.
     * L'indice di posizione fornito in ingresso deve essere valido.
     * @param i indice di posizione
     */
    @Override
    public void removeChild(int i) {
        //Verifica indice valido
        if (i>=0 && i<areas.size())
            areas.remove(i);
    }

    /**
     * Permette di rimuovere un'area, se presente, dal vettore
     * delle aree.
     * @param area area da rimuovere
     */
    @Override
    public void removeChild(Area area) {
        areas.remove(area);
    }

    /**
     * Restituisce una determinata area figlia
     * in base alla posizione nel vettore delle aree.
     * @param i posizione dell'area da restituire
     * @return area
     */
    @Override
    public Area getChild(int i){
        //Verifica indice valido
        if (i<0 || i>=areas.size()) return null;

        return areas.get(i);
    }

    /**
     * Restituisce la dimensione del vettore delle aree.
     * @return dimensione
     */
    @Override
    public int getNChildren() {
        return areas.size();
    }

    /**
     * Indica se una specifica area appartiene alla gerarchia
     * che presenta come area radice l'area corrente.
     * Se l'area da ricercare coincide con l'area corrente viene
     * restituito false.
     * @param area area da ricercare
     * @return boolean che indica la presenza o meno dell'area nella gerarchia
     */
    @Override
    public boolean isSubArea(Area area){
        //Area coincidente con quella corrente
        if (this.equals(area)) return false;

        Iterator<Area> it = iterator();

        it.next();//L'area restituita da it.next() coincide con quella corrente
        while(it.hasNext()){
            //Verifica corrispondenza aree
            if (it.next().equals(area)) return true;
        }
        return false;
    }

    /**
     * Verifica se un'area appartiene o meno al vettore delle aree.
     * @param area area da ricercare
     * @return presenza o meno dell'area nel vettore delle aree
     */
    @Override
    public boolean isChild(Area area){
        for (Area child:areas){
            if (child.equals(area)) return true;
        }
        return false;
    }

    /**
     * Verifica se l'area è cancellabile o meno.
     * Se l'area e tutte le sotto-aree presentano
     * complessivamente almeno un dipendente, allora
     * l'area non può essere rimossa.
     * @return boolean
     */
    @Override
    public boolean isRemovable(){
        for (Area a:this){
            //Verifica presenza di dipendenti
            if (a.getNEmployees()>0) return false;
        }
        return true;
    }

    /**
     * Effettua una visita pre-Order, aggiungendo gli elementi
     * visitati in una lista.
     * @param ls lista in cui salvare le aree
     */
    private void preOrder(List<Area> ls){
        ls.add(this);
        for(Area a:areas){
            ((AbstractCompositeArea)a).preOrder(ls);
        }
    }

    /**
     * Definizione del metodo iterator, per percorrere le aree dell'organigramma
     * in maniera sequenziale.
     * @return Iterator
     */
    @Override
    public Iterator<Area> iterator() {
        return new AreaIterator();
    }

    /**
     * Tale classe implementa Iterator, fornendo i metodi per percorrere
     * la struttura, esaminando ogni singola area da cui è composta.
     * @author lucab
     */
    private class AreaIterator implements Iterator<Area>{

        private Iterator<Area> it = null;

        //area corrente
        private Area cur = null;

        public AreaIterator(){
            List<Area> ls=new ArrayList<>();
            //visita pre-order dell'organigramma
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
    }//AreaIterator
}//AbstractCompositeArea
