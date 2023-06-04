package is.decorator;

import javax.swing.*;
import java.awt.*;

public class ImageZoom implements ImageMod {
    private double zoom = 1.0;
    private ImageIcon imageIcon;
    private boolean changed = false;

    public ImageZoom(ImageIcon imageIcon,double zoom){
        this.zoom = zoom;
        this.changed = true;
        this.imageIcon = imageIcon;
    }
    public ImageZoom(ImageIcon imageIcon){
        this.imageIcon = imageIcon;
    }


    @Override
    public Image getImage(){
        if (changed){
            scale(zoom);
            changed=false;
        }
        return imageIcon.getImage();
    }
    @Override
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

    @Override
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
