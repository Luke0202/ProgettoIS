package is.decorator;

import javax.swing.*;
import java.awt.*;

public class ImageZoom extends ImageDecorator {
    private double zoom = 1.0;
    private boolean changed = false;

    public ImageZoom(ImageIcon imageIcon,double zoom){
        this.imageIcon=imageIcon;
        this.zoom = zoom;
        this.changed = true;
    }
    public ImageZoom(ImageIcon imageIcon){
        this.imageIcon=imageIcon;
    }

    @Override
    public Image getImage(){
        if (changed){
            scale(zoom);
            changed=false;
        }
        return imageIcon.getImage();
    }

    public ImageIcon getImageIcon(){
        if (changed){
            scale(zoom);
            changed=false;
        }
        return imageIcon;
    }
    public void setZoom(double zoom){
        this.zoom = zoom;
        changed=true;
    }


    public void scale(double zoomFact) {
        if (zoomFact <= 0)
            throw new IllegalArgumentException("Fattore di zoom inserito non valido");
        this.zoom*=zoomFact;
        Image ima = imageIcon.getImage();
        double newWid = ima.getWidth(null)*zoomFact;
        double newHei = ima.getHeight(null)*zoomFact;
        this.imageIcon.setImage(ima.getScaledInstance((int)newWid, (int)newHei,java.awt.Image.SCALE_SMOOTH));
    }
}
