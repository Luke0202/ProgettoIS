package is.item;

import javax.swing.*;
import java.awt.*;

/**
 * Tale classe astratta estende ImageIcon.
 * È presente un riferimento a un oggetto ImageIcon.
 * @author lucab
 */
public abstract class ImageDecorator extends ImageIcon{
    protected ImageIcon imageIcon;

    @Override
    public abstract Image getImage();
}//ImageDecorator
