package is.decorator;

import javax.swing.*;
import java.awt.*;

public interface ImageMod {
    void scale(double zoomFact);
    Image getImage();
    ImageIcon getImageIcon();
}
