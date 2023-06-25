package is.decorator;

import javax.swing.*;
import java.awt.*;

public abstract class ImageDecorator extends ImageIcon{
    protected ImageIcon imageIcon;

    @Override
    public abstract Image getImage();
}
