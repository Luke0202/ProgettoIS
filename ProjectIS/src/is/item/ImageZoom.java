package is.item;

import javax.swing.*;
import java.awt.*;

/**
 * Tale classe estende ImageDecorator. Definisce un decoratore concreto.
 * È possibile manipolare, attraverso tale classe, la grandezza di un oggetto
 * ImageIcon. Per rilevare eventuali cambiamenti in merito alla grandezza sull'oggetto
 * originale, è stata definita la variabile booleana changed.
 * La variabile zoom, di default pari a 1.0, esprime di quanto l'immagine corrente è
 * stata zoomata rispetto all'immagine originale.
 * @author lucab
 */
public class ImageZoom extends ImageDecorator {
    private double zoom = 1.0;
    private boolean changed = false;

    public ImageZoom(ImageIcon imageIcon,double zoom){
        this.imageIcon=imageIcon;
        this.zoom = zoom;
        if (zoom!=1.0)
            this.changed = true;
    }
    public ImageZoom(ImageIcon imageIcon){
        this.imageIcon=imageIcon;
    }

    //SETTERS
    public void setZoom(double zoom){
        if (zoom<=0)
            throw new IllegalArgumentException("Fattore di zoom inserito non valido");
        this.zoom = zoom;
        changed=true;
    }

    /**
     * Restituisce l'immagine, effettuando eventuali
     * modifiche richieste sulla grandezza dell'immagine.
     * @return oggetto di tipo Image
     */
    @Override
    public Image getImage(){
        //Verifica su eventuali richieste di cambiamento della grandezza dell'immagine
        if (changed){
            scale();
            changed=false;
        }
        return imageIcon.getImage();
    }

    /**
     * Restituisce un oggetto di tipo ImageIcon, effettuando
     * eventuali modifiche richieste sulla grandezza dell'immagine.
     * @return oggetto di tipo ImageIcon
     */
    public ImageIcon getImageIcon(){
        //Verifica su eventuali richieste di cambiamento della grandezza dell'immagine
        if (changed){
            scale();
            changed=false;
        }
        return imageIcon;
    }


    /**
     * Effettua lo zoom dell'immagine.
    */
    private void scale() {
        Image ima = imageIcon.getImage();
        //nuove dimensioni immagine
        double newWid = ima.getWidth(null)*zoom;
        double newHei = ima.getHeight(null)*zoom;
        //modifica immagine
        this.imageIcon.setImage(ima.getScaledInstance((int)newWid, (int)newHei,java.awt.Image.SCALE_SMOOTH));
    }
}//ImageZoom
