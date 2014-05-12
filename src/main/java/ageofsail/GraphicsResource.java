package ageofsail;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public interface GraphicsResource {
    public void draw(Graphics g, Point p);
    public Dimension getDimensions();
    public Rectangle getBounds();
}
